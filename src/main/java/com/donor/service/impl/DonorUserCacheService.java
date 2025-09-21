package com.donor.service.impl;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.common.dto.UserDto;

@Service
public class DonorUserCacheService {
	private final Map<Integer, UserDto> userCache = new ConcurrentHashMap<>();
	
    public Optional<UserDto> getUserById(Integer userId) {
        return Optional.ofNullable(userCache.get(userId));
    }

    public void putUser(UserDto user) {
        if (user != null && user.getUserId() != null) {
            userCache.put(user.getUserId(), user);
        }
    }

    public void evictUser(Integer userId) {
        userCache.remove(userId);
    }
}
