package com.ingeneo.logistica.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfigSwagger {

	@Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("INGENEO - Logistica")
                        .description("Documentación de la API 'logistica' por parte de Departamento de Desarrollo de INGENEO")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Johann Andres Agamez Ferres")
                                .email("ingjohannagamez@gmail.com")
                                .url("https://ingeneo.com.co/")));
    }
}
