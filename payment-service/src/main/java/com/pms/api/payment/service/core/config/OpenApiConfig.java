package com.pms.api.payment.service.core.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for OpenAPI specification.
 */
@Configuration
public class OpenApiConfig {
    /**
     * Defines the OpenAPI specification for the API.
     *
     * @return a customized {@link OpenAPI} specification
     */
    @Bean
    public OpenAPI configureOpenApi() {
        // Creating the API specification properties
        Info info = new Info();
        License license = new License();
        Server server = new Server();

        // Setting the license
        license.setName("MIT License");
        license.setUrl("https://opensource.org/license/MIT");

        // Updating the information
        info.setTitle("Payment Service");
        info.setVersion("1.0-SNAPSHOT");
        info.setLicense(license);

        // Adding servers
        server.setDescription("Payment Service API");

        // Returning specification
        return new OpenAPI().info(info).addServersItem(server);
    }
}
