package com.donor.service;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.common.dto.DonorResponseDto;
import com.github.benmanes.caffeine.cache.Cache;

import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class UserServiceClient {

    private final WebClient.Builder webClientBuilder;
    private final Cache<Integer, DonorResponseDto> userCache;
    private final String systemToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJBbnUiLCJVc2VyX1Bob25lTnVtYmVyICI6Ijk4N"
    		+ "zY1MTMyMTMiLCJVc2VyX1JvbGU6IjpbIkRPTk9SIl0sIkpXdF9Vc2VySWQgOiI6IjE"
    		+ "iLCJpYXQiOjE3NTg4MTc2ODIsImV4cCI6MTc1ODg1MzY4Mn0.dkiiXQ9mDKZf9kvX-uxjhi4qg4Uz7cJReTeN_Tu09b4";

//     Fetch user info from user-service by userId. Synchronous call for now; fallback can be implemented later.

    public Optional<DonorResponseDto> getUserById(Integer userId) {
        // 1. Try cache first
    	DonorResponseDto cachedUser = userCache.getIfPresent(userId);
        if (cachedUser != null) {
            return Optional.of(cachedUser);
        }

        // 2. Call user-service if not cached
        DonorResponseDto user = webClientBuilder.build()
                .get()
                .uri("http://user-service/user/donor-details/{id}", userId)
                .header("Authorization", "Bearer " + systemToken)
                .retrieve()
                .bodyToMono(DonorResponseDto.class)
//                .blockOptional(); // synchronous call
                .block();

        // 3. Store in cache
        if (user != null) {
            userCache.put(userId, user);
        }

        return Optional.ofNullable(user);
    }
}
