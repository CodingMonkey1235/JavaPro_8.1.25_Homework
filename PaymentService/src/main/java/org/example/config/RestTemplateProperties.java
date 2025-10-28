package org.example.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Data
@Configuration
@ConfigurationProperties(prefix = "integrations.clients.client-products-properties")
public class RestTemplateProperties {
    private String url;
    private Duration connectTimeout;
    private Duration readTimeout;
}
