package guru.springframework.client;

import com.fasterxml.jackson.databind.JsonNode;
import guru.springframework.model.BeerDTO;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface BeerClient {

    Flux<String> listBeer();

    Flux<Map> listBeerMap();

    Flux<JsonNode> listBeerJsonNode();
    Flux<BeerDTO> listBeerDtos();

    Mono<BeerDTO> getBeerById(String id);

    Flux<BeerDTO> getBeerByBeerStyle(String beerStyle);

    Mono<BeerDTO> saveNewBeer(BeerDTO beerDTO);

    Mono<BeerDTO> updateBeer(BeerDTO dto);

    Mono<BeerDTO> pathBeer(BeerDTO dto);

    Mono<Void> deleteBeer(String id);
}
