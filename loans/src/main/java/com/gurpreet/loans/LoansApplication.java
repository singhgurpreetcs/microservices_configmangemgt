package com.gurpreet.loans;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
        info = @Info(
                title = "Loans microservice REST API Documentation",
                description = "Bank Loans microservice REST API Documentation",
                version = "v1",
                contact = @Contact(
                        name = "Gurpreet Singh",
                        email = "gurpreet.cs.tsys@gmail.com",
                        url = "abc@gmail.com"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "abc@gmail.com"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "Bank Loans microservice REST API Documentation",
                url = "abc@gmail.com"
        )
)
public class LoansApplication {
    public static void main(String[] args) {
        SpringApplication.run(LoansApplication.class, args);
    }
}
