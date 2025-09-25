package com.donor.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.common.enums.BloodGroupType;
import com.common.enums.DonationEligibilityStatus;
import com.common.enums.RegisterType;
import com.common.enums.StatusType;
import com.donor.vo.DonorHealthCheckVO;
import com.donor.vo.DonorLifestyleProfileVO;
import com.donor.vo.DonorRewardsVO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class FullDonorResponseDto {
	
	 private Integer userId;
	
	 private BloodGroupType bloodGroup;
	 
	 private DonationEligibilityStatus donationEligibilityStatus; // eligible, not eligible, pending approval

		private Boolean isAvailableToDonate;

	    private LocalDate lastDonationDate;

		 private LocalDate nextEligibleDate;

		 private Integer totalDonations;

		 private Integer totalUnitsDonated;

		 private Boolean isEligibleToDonate;

		 private String ineligibilityReason; // e.g., "Low hemoglobin", "Medical condition"

		 private LocalDate temporarilyIneligibleUntil;

		 private Boolean isActive; // If donor is still participating

		 private Boolean isVerified; // If user has passed eligibility verification
		 
		 private RegisterType registeredVia; // e.g., "app", "web", "camp"

		 private Double weightInKg;

		 private Double hemoglobinLevel;       // g/dL

		 private Boolean hasChronicDiseases;   // e.g., diabetes, hypertension

		 private LocalDateTime createdAt;

		 private LocalDateTime updatedAt;

		 private StatusType status; // (ENUM: ACTIVE, INACTIVE, DECEASED)

		 private String recentMedications;
		 
		 private String medicalConditions;

		 private List<DonorHealthCheckVO> donorHealthCheck;

		 private DonorLifestyleProfileVO donorLifestyleProfile;

		 private List<DonorRewardsVO> donorRewards;

}
