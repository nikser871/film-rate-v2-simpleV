package org.example.filmratev2simplev.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties("custom")
public class AppProperties {
    private String baseUrl;
}
