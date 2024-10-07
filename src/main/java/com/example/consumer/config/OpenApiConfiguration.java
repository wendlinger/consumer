package com.example.consumer.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {

    @Value("${open_api.title}")
    private String title;
    @Value("${open_api.description}")
    private String description;
    @Value("${open_api.version}")
    private String version;
    @Value("${open_api.license.name}")
    private String licenseName;
    @Value("${open_api.license.url}")
    private String licenseUrl;

    @Bean
    public OpenAPI config() {
        return new OpenAPI()
                .info(new Info().title(this.title)
                        .description(this.description)
                        .version(this.version)
                        .license(new License().name(this.licenseName).url(this.licenseUrl)));
    }
}
