package guru.springframework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.*;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;

@Configuration
public class SecurityConfig {

    @Bean
    public ReactiveOAuth2AuthorizedClientManager auth2AuthorizedClientManager(ReactiveClientRegistrationRepository clientRegistrationRepository,
                                                                              ReactiveOAuth2AuthorizedClientService authorizedClientService) {
        ReactiveOAuth2AuthorizedClientProvider auth2AuthorizedClientProvider =
                ReactiveOAuth2AuthorizedClientProviderBuilder.builder()
                        .clientCredentials()
                        .build();

        AuthorizedClientServiceReactiveOAuth2AuthorizedClientManager auth2AuthorizedClientManager =
                new AuthorizedClientServiceReactiveOAuth2AuthorizedClientManager(clientRegistrationRepository, authorizedClientService);

        auth2AuthorizedClientManager.setAuthorizedClientProvider(auth2AuthorizedClientProvider);

        return auth2AuthorizedClientManager;
    }
}
