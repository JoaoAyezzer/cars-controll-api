package br.edu.unicesumar.carscontrollapi.config;


import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class SwaggerConfig {

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Cars Control API")
                        .version("beta")
                );
    }
    @Bean
    public GroupedOpenApi foodsAndroidApi() {
        return GroupedOpenApi.builder()
                .group("v1")
                .packagesToScan("br.edu.unicesumar.carscontrollapi.resource")
                .build();
    }

}
