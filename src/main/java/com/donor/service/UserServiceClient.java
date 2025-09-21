package com.donor.service;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.common.dto.UserDto;
import com.github.benmanes.caffeine.cache.Cache;

import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class UserServiceClient {

    private final WebClient.Builder webClientBuilder;
    private final Cache<Integer, UserDto> userCache;

    /**
     * Fetch user info from user-service by userId.
     * Synchronous call for now; fallback can be implemented later.
     */

    public Optional<UserDto> getUserById(Integer userId) {
        // 1. Try cache first
        UserDto cachedUser = userCache.getIfPresent(userId);
        if (cachedUser != null) {
            return Optional.of(cachedUser);
        }

        // 2. Call user-service if not cached
        UserDto user = webClientBuilder.build()
                .get()
                .uri("http://user-service/api/users/{id}", userId)
                .retrieve()
                .bodyToMono(UserDto.class)
                .block();

        // 3. Store in cache
        if (user != null) {
            userCache.put(userId, user);
        }

        return Optional.ofNullable(user);
    }
}
