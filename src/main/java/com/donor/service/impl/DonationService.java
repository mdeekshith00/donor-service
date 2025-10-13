package com.donor.service.impl;


import java.time.Duration;

import org.apache.http.HttpHeaders;
import org.hibernate.validator.internal.util.logging.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.common.vo.DonationRequestVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

@Slf4j
@Service
@RequiredArgsConstructor
public class DonationService {
	
    private final WebClient donationWebClient;
    private static final String DONATION_URL = "http://localhost:8082/donation-event";
    private static final String SERVICE_TOKEN = "my-shared-secret";


    /**
     * Asynchronously notifies the Donation Service about a new donation event.
     * Includes retry logic and proper error handling.
     */
    public void notifyDonationServiceAsync(DonationRequestVO donationRequest) {
        log.info(">>> Sending donation event to Donation Service for donorId: {}", donationRequest.getVolume());

        donationWebClient.post()
                .uri(DONATION_URL)
                .header("X-Service-Token", SERVICE_TOKEN)
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
