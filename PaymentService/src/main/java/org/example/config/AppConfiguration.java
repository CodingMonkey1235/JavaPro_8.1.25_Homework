package org.example.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Configuration
public class AppConfiguration {

    @Bean
    public RestTemplate restTemplateClientProductsClient(RestTemplateProperties clientProductsProperties) {
        return new RestTemplateBuilder()
                .rootUri(clientProductsProperties.getUrl())
                .setConnectTimeout(clientProductsProperties.getConnectTimeout())
                .setReadTimeout(clientProductsProperties.getReadTimeout())
                .build();
    }

}
