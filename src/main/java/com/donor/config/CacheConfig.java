package com.donor.config;

import java.util.concurrent.TimeUnit;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.common.dto.UserDto;

@Configuration
public class CacheConfig {

    @Bean
    Cache<Integer, UserDto> userCache() {
        return Caffeine.newBuilder()
                .expireAfterWrite(10, TimeUnit.MINUTES) // cache expires after 10 mins
                .maximumSize(1000) // store up to 1000 users
                .build();
    }

}
