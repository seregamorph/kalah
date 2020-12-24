package com.backbase.kalah.config;

import com.backbase.kalah.utils.ClasspathResources;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.val;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi openApi() {
        return GroupedOpenApi.builder()
                .group("Kalah APIs")
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    public OpenAPI springShopOpenAPI() {
        val restApiDescription = ClasspathResources.readString("rest-api.md");
        return new OpenAPI()
                .info(new Info()
                        .title("Kalah API Documentation")
                        .description(restApiDescription)
                        .version("1.0"))
                .externalDocs(new ExternalDocumentation()
                        .description("Kalah")
                        .url("https://backbase.com/"));
    }}
