package com.paway.spring.data.kmoneta.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class OpenAPIConfig {
  @Bean
  public OpenAPI myOpenAPI() {
    return new OpenAPI()
            .info(new Info()
                    .title("Kmoneta API")
                    .description("Kmoneta application REST API documentation.")
                    .version("v1.0.0")
                    .license(new License()
                            .name("Apache 2.0")
                            .url("https://www.apache.org/licenses/LICENSE-2.0.html")))
            .externalDocs(new ExternalDocumentation()
                    .description("Kmoneta Wiki Documentation")
                    .url("https://Kmoneta.wiki.github.io/docs"));
  }
}

