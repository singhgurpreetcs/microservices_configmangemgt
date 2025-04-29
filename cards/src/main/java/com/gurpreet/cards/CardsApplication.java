package com.gurpreet.cards;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
        info = @Info(
                title = "Cards microservice REST API Documentation",
                description = "Bank Cards microservice REST API Documentation",
                version = "v1",
                contact = @Contact(
                        name = "Gurpreet Singh",
                        email = "gurpreet.cs.tsys@gmail.com",
                        url = "www.gmail.com"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "www.gmail.com"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "Bank Cards microservice REST API Documentation",
                url = "www.gmail.com"
        )
)
public class CardsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CardsApplication.class, args);
    }
}