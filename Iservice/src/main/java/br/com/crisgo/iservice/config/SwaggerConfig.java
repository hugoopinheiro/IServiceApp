package br.com.crisgo.iservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    OpenAPI customAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Example API")
                        .version("v1")
                        .license(new License()
                                .name("Licença do Sistema")
                                .url("http://www.techdevbrazil.com")));
    }
}
