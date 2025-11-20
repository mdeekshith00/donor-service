package com.donor.dto;


import java.time.LocalDate;
import lombok.Data;

@Data
public class SearchDonorDTO {

    private Integer donorId;
    private String bloodGroup;

    private String donationEligibilityStatus;
    private Boolean isAvailableToDonate;

    private LocalDate lastDonationDate;
    private LocalDate nextEligibleDate;

    private Integer totalDonations;
    private Integer totalUnitsDonated;

    private Boolean isEligibleToDonate;
    private String ineligibilityReason;

    private Boolean isActive;
    private Boolean isVerified;

    private Integer userId;
}
