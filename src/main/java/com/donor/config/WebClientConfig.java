package com.donor.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import jakarta.ws.rs.core.HttpHeaders;

@Configuration
public class WebClientConfig  {

    @Bean
    @LoadBalanced
    WebClient donationWebClient(WebClient.Builder builder) {
        return builder
                .baseUrl("http://localhost:8082/donation-event/donate")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }


}
