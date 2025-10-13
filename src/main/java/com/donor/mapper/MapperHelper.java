package com.donor.mapper;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.donor.dto.DonorHealthCheckDto;
import com.donor.dto.DonorLifeStyleProfileDto;
import com.donor.dto.FullDonorResponseDto;
import com.donor.entities.Donor;
import com.donor.entities.DonorHealthCheck;
import com.donor.entities.DonorLifestyleProfile;
import com.donor.vo.DonorHealthCheckVO;
import com.donor.vo.DonorLifestyleProfileVO;
import com.donor.vo.DonorRewardsVO;

@Component
public class MapperHelper {
	
	public FullDonorResponseDto DonorToVO(Donor donor) {
		
	return FullDonorResponseDto.builder()
        		.userId(donor.getUserId())
//        		.bloodGroup(donor.getBloodGroup().toString()
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
//				.bloodGroup(donor.getBloodGroup())
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
//				.createdAt(donor.getCreatedAt())
//				.updatedAt(donor.getUpdatedAt())
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
	
	public 	DonorHealthCheck voToDonorHealthCheckUpEntity(DonorHealthCheck donorHealthCheck , DonorHealthCheckVO vo) {
		if(vo == null) {
			return null;
		}
		Optional.ofNullable(vo.getHemoglobinLevel()).ifPresent(donorHealthCheck::setHemoglobinLevel);
		Optional.ofNullable(vo.getBloodPressureDiastolic()).ifPresent(donorHealthCheck::setBloodPressureDiastolic);
		Optional.ofNullable(vo.getBloodPressureSystolic()).ifPresent(donorHealthCheck::setBloodPressureSystolic);
		Optional.ofNullable(vo.getTemperature()).ifPresent(donorHealthCheck::setTemperature);
		Optional.ofNullable(vo.getPulseRate()).ifPresent(donorHealthCheck::setPulseRate);
		Optional.ofNullable(vo.getMedicalRemarks()).ifPresent(donorHealthCheck::setMedicalRemarks);
		Optional.ofNullable(vo.getAllergies()).ifPresent(donorHealthCheck::setAllergies);
		Optional.ofNullable(vo.getWeight()).ifPresent(donorHealthCheck::setWeight);
		Optional.ofNullable(vo.getHeight()).ifPresent(donorHealthCheck::setHeight);
		Optional.ofNullable(vo.getHealthNotes()).ifPresent(donorHealthCheck::setHealthNotes);
		Optional.ofNullable(vo.getScreenedBy()).ifPresent(donorHealthCheck::setScreenedBy);
		Optional.ofNullable(vo.getStatus()).ifPresent(donorHealthCheck::setStatus);
		
		return donorHealthCheck;	
	}
	public DonorLifeStyleProfileDto lifeStleToLifeStyleDto(DonorLifestyleProfile lifeStyle) {
		if(lifeStyle == null) {
			return null;
		}
	  return   DonorLifeStyleProfileDto.builder()
			           .DonorLifestyleProfileId(lifeStyle.getDonorLifestyleProfileId())
                       .smoking(lifeStyle.getSmoking())
                       .alcoholConsumption(null)
                       .drugUse(lifeStyle.getDrugUse())
                        .tattoosOrPiercings(lifeStyle.getTattoosOrPiercings())
                    .sleepPattern(lifeStyle.getSleepPattern().toString())
              	    .dietType(lifeStyle.getDietType().toString())
                   .exerciseRoutine(lifeStyle.getExerciseRoutine())
                      .habits(lifeStyle.getHabits().toString())
                   .otherHabitsDetails(lifeStyle.getOtherHabitsDetails())
                    .lastUpdatedDate(lifeStyle.getUpdatedAt())
                      .build();
	}
	public void addorUpdateDonorLifeStyle(DonorLifestyleProfile profile , DonorLifestyleProfileVO donorLifestyleProfileVO)
	{
		Optional.ofNullable(donorLifestyleProfileVO.getSmoking()).ifPresent(profile::setSmoking);
		Optional.ofNullable(donorLifestyleProfileVO.getAlcoholConsumption()).ifPresent(profile::setAlcoholConsumption);
		Optional.ofNullable(donorLifestyleProfileVO.getDrugUse()).ifPresent(profile::setDrugUse);
		Optional.ofNullable(donorLifestyleProfileVO.getTattoosOrPiercings()).ifPresent(profile::setTattoosOrPiercings);
		Optional.ofNullable(donorLifestyleProfileVO.getSleepPattern()).ifPresent(profile::setSleepPattern);
		Optional.ofNullable(donorLifestyleProfileVO.getDietType()).ifPresent(profile::setDietType);
		Optional.ofNullable(donorLifestyleProfileVO.getExerciseRoutine()).ifPresent(profile::setExerciseRoutine);
		Optional.ofNullable(donorLifestyleProfileVO.getHabits()).ifPresent(profile::setHabits);
		Optional.ofNullable(donorLifestyleProfileVO.getOtherHabitsDetails()).ifPresent(profile::setOtherHabitsDetails);
		Optional.ofNullable(donorLifestyleProfileVO.getLastUpdatedDate()).ifPresent(profile::setUpdatedAt);
		
		profile.setUpdatedAt(LocalDateTime.now());
	}
	
	public DonorHealthCheckDto DonorHealthEntityToDto(DonorHealthCheck healthCheckup) {
		if(healthCheckup == null) return null;
	 return DonorHealthCheckDto.builder()
				.DonorHealthCheckId(healthCheckup.getDonorHealthCheckId())
				.hemoglobinLevel(healthCheckup.getHemoglobinLevel())
				.bloodPressureSystolic(healthCheckup.getBloodPressureSystolic())
				.bloodPressureDiastolic(healthCheckup.getBloodPressureDiastolic())
				.temperature(healthCheckup.getTemperature())
		        .pulseRate(healthCheckup.getPulseRate())
		        .medicalRemarks(healthCheckup.getMedicalRemarks())
             .allergies(healthCheckup.getAllergies())
             .weight(healthCheckup.getWeight())
             .height(healthCheckup.getHeight())
             .healthNotes(healthCheckup.getHealthNotes())
             .screenedBy(healthCheckup.getScreenedBy())
             .status(healthCheckup.getStatus().toString())
		        .build();
	}
}
