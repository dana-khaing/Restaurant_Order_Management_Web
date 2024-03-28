package com.oaxaca.waiter_service.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableSpringDataWebSupport
public class WebConfig implements WebMvcConfigurer {
  /**
   * Provides a RestTemplate bean for making HTTP requests.
   * 
   * @return RestTemplate bean configured for making HTTP requests.
   */
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }


}
