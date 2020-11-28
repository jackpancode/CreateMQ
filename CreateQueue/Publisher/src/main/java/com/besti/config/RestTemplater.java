package com.besti.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author Jack Pan
 * @version 1.0 2020/11/28
 */
@Configuration
public class RestTemplater {

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
