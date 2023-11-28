package guru.springframework.client;

import guru.springframework.model.BeerDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.awaitility.Awaitility.await;

@SpringBootTest
class BeerClientImplTest {

    @Autowired
    BeerClient client;

    @Test
    void listBeer() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        client.listBeer().subscribe(response -> {
            atomicBoolean.set(true);
            System.out.println(response);
        });

        await().untilTrue(atomicBoolean);
    }

    @Test
    void listBeerMap() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        client.listBeerMap().subscribe(response -> {
            atomicBoolean.set(true);
            System.out.println(response);
        });

        await().untilTrue(atomicBoolean);
    }

    @Test
    void listBeerJsonNode() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        client.listBeerJsonNode().subscribe(response -> {
            atomicBoolean.set(true);
            System.out.println(response.toPrettyString());
        });

        await().untilTrue(atomicBoolean);
    }

    @Test
    void listBeerDtos() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        client.listBeerDtos().subscribe(response -> {
            atomicBoolean.set(true);
            System.out.println(response.getBeerName());
        });

        await().untilTrue(atomicBoolean);
    }

    @Test
    void getBeerById() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        client.listBeerDtos().flatMap(dto -> client.getBeerById(dto.getId()))
                .subscribe(foundDto -> {
                    System.out.println(foundDto);
                    atomicBoolean.set(true);
                });

        await().untilTrue(atomicBoolean);
    }

    @Test
    void getBeerByBeerStyle() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        client.getBeerByBeerStyle("PALE_ALE").subscribe(dto -> {
            System.out.println(dto);
            atomicBoolean.set(true);
        });

        await().untilTrue(atomicBoolean);
    }

    @Test
    void updateBeer() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        client.listBeerDtos().next().doOnNext(dto -> dto.setBeerName("Nowe"))
                .flatMap(updatedDto -> client.updateBeer(updatedDto))
                .subscribe(foundDto -> {
                    System.out.println(foundDto);
                    atomicBoolean.set(true);
                });

        await().untilTrue(atomicBoolean);
    }

    @Test
    void patchBeer() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        client.listBeerDtos().next().doOnNext(dto -> dto.setBeerName("Nowe"))
                .flatMap(updatedDto -> client.pathBeer(updatedDto))
                .subscribe(foundDto -> {
                    System.out.println(foundDto);
                    atomicBoolean.set(true);
                });

        await().untilTrue(atomicBoolean);
    }

    @Test
    void deleteBeer() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        client.listBeerDtos().next().doOnNext(beerDTO -> client.deleteBeer(beerDTO.getId()))
                .subscribe(response -> atomicBoolean.set(true));

        await().untilTrue(atomicBoolean);
    }

    @Test
    void saveNewBeer() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        client.saveNewBeer(getBeerDTO()).subscribe(dto -> {
            System.out.println(dto);
            atomicBoolean.set(true);
        });

        await().untilTrue(atomicBoolean);
    }

    private BeerDTO getBeerDTO() {
        return BeerDTO.builder()
                .beerName("Zatecky")
                .beerStyle("IPA")
                .beerUpc("1231321")
                .price(BigDecimal.TEN)
                .quantityOnHand(123)
                .build();
    }
}
