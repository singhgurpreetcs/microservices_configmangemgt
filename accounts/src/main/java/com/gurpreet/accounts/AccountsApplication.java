package com.gurpreet.accounts;

import com.gurpreet.accounts.dto.AccountContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// this annotation is to perform auto configuration and scan all the beans in the spring boot application
@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl" )
@EnableConfigurationProperties(value = AccountContactInfoDto.class)
@OpenAPIDefinition(
        info = @Info(
                title = "Accounts microservice REST API documentation",
                description = "Bank accounts microservice REST API documentation",
                version = "v1",
                contact = @Contact(
                        name = "Gurpreet Singh",
                        email = "gurpreet.cs.tsys@gmail.com"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "www.gmail.com"
                )
        ),
        externalDocs =  @ExternalDocumentation(
                description = "Bank accounts microserrvice REST API documentation",
                url = "www.gmail.com"
        )
)
public class AccountsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountsApplication.class, args);
    }
}
