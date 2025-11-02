package com.donor.service.impl;



import java.time.Duration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.common.dto.DonationResponseDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

@Slf4j
@Service
@RequiredArgsConstructor
public class DonationService {
	
	@Qualifier("donationWebClient")
    private final WebClient donationWebClient;
	
    public void notifyDonationServiceAsync(DonationResponseDto donationRequest) {
        log.info(">>> Sending donor event to Donation Service for donorId: {}", donationRequest.getDonorId());

        donationWebClient.post()
                .bodyValue(donationRequest)
                .retrieve()
                .onStatus(status -> status.value() == 401,
                        response -> {
                            log.error(">>> Unauthorized: Invalid service token while sending donation notification");
                            return Mono.error(new RuntimeException("Unauthorized: service token rejected"));
                        })
                .bodyToMono(Void.class)
                .retryWhen(
                        Retry.backoff(3, Duration.ofSeconds(2))
                                .maxBackoff(Duration.ofSeconds(10))
                                .doBeforeRetry(signal ->
                                        log.warn(">>> Retrying to send donation event... attempt {}", signal.totalRetries() + 1))
                )
                .doOnSuccess(v -> log.info(">>> Successfully notified Donation Service"))
                .doOnError(err -> log.error(">>> Failed to notify Donation Service: {}", err.getMessage()))
                .subscribe();

   }
}
