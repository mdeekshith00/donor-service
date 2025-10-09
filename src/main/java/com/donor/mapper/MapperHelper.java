package com.donor.mapper;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.donor.dto.FullDonorResponseDto;
import com.donor.entities.Donor;
import com.donor.vo.DonorHealthCheckVO;
import com.donor.vo.DonorRewardsVO;

@Service
public class MapperHelper {
	
	public FullDonorResponseDto DonorToVO(Donor donor) {
		
	return FullDonorResponseDto.builder()
        		.userId(donor.getUserId())
        		.bloodGroup(donor.getBloodGroup())
        		.isAvailableToDonate(donor.getIsAvailableToDonate())
        		.totalDonations(donor.getTotalDonations())
        		.totalUnitsDonated(donor.getTotalUnitsDonated())
        		.isEligibleToDonate(donor.getIsEligibleToDonate())
        		.recentMedications(donor.getRecentMedications())
        		.medicalConditions(donor.getMedicalConditions())
        		.weightInKg(donor.getWeightInKg())
        		.nextEligibleDate(donor.getNextEligibleDate())
        		.isActive(donor.getIsActive())
        		.hemoglobinLevel(donor.getHemoglobinLevel())
        		.hasChronicDiseases(donor.getHasChronicDiseases())
        		.status(donor.getStatus())
        		.medicalConditions(donor.getMedicalConditions())
        		.recentMedications(donor.getRecentMedications())
        		.build();
		
	}
	public  FullDonorResponseDto donorToFullDonorVO(Donor donor) {
		return FullDonorResponseDto.builder()
				.userId(donor.getUserId())
				.bloodGroup(donor.getBloodGroup())
				.donationEligibilityStatus(donor.getDonationEligibilityStatus())
				.isAvailableToDonate(donor.getIsAvailableToDonate())
				.lastDonationDate(donor.getLastDonationDate())
				.nextEligibleDate(donor.getNextEligibleDate())
				.totalDonations(donor.getTotalDonations())
				.totalUnitsDonated(donor.getTotalUnitsDonated())
				.isEligibleToDonate(donor.getIsEligibleToDonate())
				.ineligibilityReason(donor.getIneligibilityReason())
				.temporarilyIneligibleUntil(donor.getTemporarilyIneligibleUntil())
				.isActive(donor.getIsActive())
				.isVerified(donor.getIsVerified())
				.registeredVia(donor.getRegisteredVia())
				.weightInKg(donor.getWeightInKg())
				.hemoglobinLevel(donor.getHemoglobinLevel())
				.hasChronicDiseases(donor.getHasChronicDiseases())
				.createdAt(donor.getCreatedAt())
				.updatedAt(donor.getUpdatedAt())
				.status(donor.getStatus())
				.recentMedications(donor.getRecentMedications())
				.medicalConditions(donor.getMedicalConditions())
				.donorHealthCheck(
						 donor.getDonorHealthCheck().stream()
                         .map(health -> DonorHealthCheckVO.builder()
                                 .hemoglobinLevel(health.getHemoglobinLevel())
                                 .bloodPressureSystolic(health.getBloodPressureSystolic())
                                 .bloodPressureDiastolic(health.getBloodPressureDiastolic())
                                 .temperature(health.getTemperature())
                                 .pulseRate(health.getPulseRate())
                                 .medicalRemarks(health.getMedicalRemarks())
                                 .allergies(health.getAllergies())
                                 .weight(health.getWeight())
                                 .height(health.getHeight())
                                 .healthNotes(health.getHealthNotes())
                                 .screenedBy(health.getScreenedBy())
                                 .status(health.getStatus())
                                 .build()
                         ).collect(Collectors.toList())
                    ) 
				.donorRewards(donor.getDonorRewards().stream().map(reward -> DonorRewardsVO.builder()
						.type(reward.getType())
						.title(reward.getTitle())
						.issuedDate(reward.getIssuedDate())
						.expiryDate(reward.getExpiryDate())
						.status(reward.getStatus())
						.issuedBy(reward.getIssuedBy())
						.redeemedAt(reward.getRedeemedAt())
						.build()
						).collect(Collectors.toList())
						)

				.build();
	}

}
