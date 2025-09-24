package com.donor.config;

import org.springframework.stereotype.Component;

import lombok.Value;

@Component
public class JwtTokenProviderClient {

//	  @Value("${SYSTEM_JWT_TOKEN}")
	    private String systemToken;

	    public String getToken() {
	        return systemToken;
	    }
}
