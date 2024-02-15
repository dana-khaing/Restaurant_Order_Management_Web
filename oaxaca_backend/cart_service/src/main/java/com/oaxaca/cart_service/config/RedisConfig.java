package com.oaxaca.cart_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.oaxaca.cart_service.model.Cart;

@Configuration
public class RedisConfig {

    @Bean
    RedisTemplate<String, Cart> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Cart> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        return template;
    }

}
