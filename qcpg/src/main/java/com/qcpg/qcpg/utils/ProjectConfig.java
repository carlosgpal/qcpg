package com.qcpg.qcpg.utils;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration class for the QCPG project.
 * Includes bean definitions and global settings such as CORS configuration.
 */
@Configuration
@EnableNeo4jRepositories(basePackages = {
        "com.qcpg.qcpg.repository.graphCreation",
        "com.qcpg.qcpg.repository.graphAnalysis"
})
public class ProjectConfig {

    /**
     * Creates a `ModelMapper` bean for object mapping.
     *
     * @return A configured instance of `ModelMapper`.
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    /**
     * Creates a `RestTemplate` bean for REST API communication.
     *
     * @return A new instance of `RestTemplate`.
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * Configures Cross-Origin Resource Sharing (CORS) settings for the application.
     * Allows the frontend to interact with backend APIs.
     *
     * @return A `WebMvcConfigurer` with custom CORS settings.
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Apply to all endpoints
                        .allowedOrigins("http://localhost:4200") // Allow frontend origin
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS") // Specify allowed methods
                        .allowedHeaders("*") // Allow all headers
                        .allowCredentials(true); // Enable cookies or authorization headers
            }
        };
    }
}
