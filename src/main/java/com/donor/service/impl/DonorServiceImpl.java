package com.donor.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.common.constants.ErrorConstants;
import com.common.dto.DonorResponseDto;
import com.common.enums.DonationEligibilityStatus;
import com.common.enums.RegisterType;
import com.common.enums.StatusType;
import com.common.exception.BloodBankBusinessException;
import com.donor.dto.FullDonorResponseDto;
import com.donor.entities.Donor;
import com.donor.entities.DonorHealthCheck;
import com.donor.entities.DonorLifestyleProfile;
import com.donor.entities.DonorRewards;
import com.donor.repositary.DonorRepositary;
import com.donor.service.DonorServcie;
import com.donor.service.UserServiceClient;
import com.donor.vo.DonorHealthCheckVO;
import com.donor.vo.DonorRequestVO;
import com.donor.vo.DonorRewardsVO;
import com.donor.vo.UpadteDonorRequestVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DonorServiceImpl implements DonorServcie {
	
	private final DonorRepositary donorRepositary;
	private final UserServiceClient userServiceClient;
	private final DonorUserCacheService donorUserCacheService;
	
    public DonorResponseDto getUserDetails(Integer userId) {
        // 1. Check cache
    	DonorResponseDto user = donorUserCacheService.getUserById(userId)
    	        .orElseGet(() -> {
    	            // If not in cache, call user-service
    	        	DonorResponseDto fetchedUser = userServiceClient.getUserById(userId)
    	                    .orElseThrow(() -> new RuntimeException("User not found"));

    	            donorUserCacheService.putUser(fetchedUser); // save in cache
    	            return fetchedUser;
    	        });
    	return user;
    }
    
	@Override
	public FullDonorResponseDto fetchUserAndCreateDonor(DonorRequestVO request) {
		// TODO Auto-generated method stub
		  Optional<Donor> existingDonor = donorRepositary.findById(request.getUserId());
	        if (existingDonor.isPresent()) {
	            return DonorToVO(existingDonor.get());
	        }

	        // 2. If not in donor DB, check cache
	        DonorResponseDto user = donorUserCacheService.getUserById(request.getUserId())
	                .orElseGet(() -> {
	                    // 3. If not in cache, call user-service
	                	DonorResponseDto fetchedUser = userServiceClient.getUserById(request.getUserId())
	                            .orElseThrow(() -> new RuntimeException("User not found in user-service"));

	                    donorUserCacheService.putUser(fetchedUser);
	                    return fetchedUser;
	                });

	        Donor donor = new Donor();
	   
	        Optional.ofNullable(request.getBloodGroup()).ifPresent(donor::setBloodGroup);
	        Optional.ofNullable(request.getIsAvailableToDonate()).ifPresent(donor::setIsAvailableToDonate);
	        Optional.ofNullable(request.getTotalDonations()).ifPresent(donor::setTotalDonations);
	        Optional.ofNullable(request.getTotalUnitsDonated()).ifPresent(donor::setTotalUnitsDonated);
	        Optional.ofNullable(request.getIsEligibleToDonate()).ifPresent(donor::setIsEligibleToDonate);
	        Optional.ofNullable(request.getWeightInKg()).ifPresent(donor::setWeightInKg);
	        Optional.ofNullable(request.getRecentMedications()).ifPresent(donor::setRecentMedications);
	        Optional.ofNullable(request.getMedicalConditions()).ifPresent(donor::setMedicalConditions);
	        
	        donor.setIsActive(true);
	        donor.setUserId(request.getUserId());
	        donor.setCreatedAt(LocalDateTime.now());
	        donor.setUpdatedAt(LocalDateTime.now());
	        donor.setDonationEligibilityStatus(DonationEligibilityStatus.PEDING_APPROVAL);
	        donor.setRegisteredVia(RegisterType.APP);
	        donor.setStatus(StatusType.ACTIVE);
	        
	        donor = donorRepositary.save(donor);
	        
	        return DonorToVO(donor);
	}
	
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
        		.build();
		
	}

	@Override
	public FullDonorResponseDto updateDonorDetails(Integer donorId , UpadteDonorRequestVO request) {
		// TODO Auto-generated method stub
		Donor donor = donorRepositary.findByDonorIdAndIsActive(donorId, true).orElseThrow(() -> 
		new BloodBankBusinessException(ErrorConstants.DONOR_DETAILS_NOT_FOUND ,HttpStatus.BAD_REQUEST,ErrorConstants.INVALID_DATA));
		
		DonorHealthCheck newCheck = DonorHealthCheck.builder()
				.hemoglobinLevel(request.getDonorHealthCheck().getHemoglobinLevel())
				.bloodPressureDiastolic(request.getDonorHealthCheck().getBloodPressureDiastolic())
				.bloodPressureSystolic(request.getDonorHealthCheck().getBloodPressureSystolic())
				.temperature(request.getDonorHealthCheck().getTemperature())
				.pulseRate(request.getDonorHealthCheck().getPulseRate())
				.medicalRemarks(request.getDonorHealthCheck().getMedicalRemarks())
				.allergies(request.getDonorHealthCheck().getAllergies())
				.weight(request.getDonorHealthCheck().getWeight())
				.height(request.getDonorHealthCheck().getHeight())
				.healthNotes(request.getDonorHealthCheck().getHealthNotes())
				.screenedBy(request.getDonorHealthCheck().getScreenedBy())
				.status(request.getDonorHealthCheck().getStatus())
		        .build();
		
		DonorRewards newRewards =DonorRewards.builder()
				.type(request.getDonorRewards().getType())
				.title(request.getDonorRewards().getTitle())
				.description(request.getDonorRewards().getDescription())
				.issuedBy(request.getDonorRewards().getIssuedBy())
				.expiryDate(request.getDonorRewards().getExpiryDate())
				.status(request.getDonorRewards().getStatus())
				.issuedBy(request.getDonorRewards().getIssuedBy())
				.redeemedAt(request.getDonorRewards().getRedeemedAt())
				.build();
		
		DonorLifestyleProfile lifeStyle=   donor.getDonorLifestyleProfile();

		Optional.ofNullable(request.getDonationEligibilityStatus()).ifPresent(donor::setDonationEligibilityStatus);
		Optional.ofNullable(request.getIsAvailableToDonate()).ifPresent(donor::setIsAvailableToDonate);
		Optional.ofNullable(request.getNextEligibleDate()).ifPresent(donor::setNextEligibleDate);
		Optional.ofNullable(request.getTotalDonations()).ifPresent(donor::setTotalDonations);
		Optional.ofNullable(request.getTotalUnitsDonated()).ifPresent(donor::setTotalUnitsDonated);
		Optional.ofNullable(request.getIsEligibleToDonate()).ifPresent(donor::setIsEligibleToDonate);
		Optional.ofNullable(request.getTemporarilyIneligibleUntil()).ifPresent(donor::setTemporarilyIneligibleUntil);
		Optional.ofNullable(request.getRegisteredVia()).ifPresent(donor::setRegisteredVia);
		Optional.ofNullable(request.getWeightInKg()).ifPresent(donor::setWeightInKg);
		Optional.ofNullable(request.getHemoglobinLevel()).ifPresent(donor::setHemoglobinLevel);
		Optional.ofNullable(request.getHasChronicDiseases()).ifPresent(donor::setHasChronicDiseases);
		Optional.ofNullable(request.getStatus()).ifPresent(donor::setStatus);
		Optional.ofNullable(request.getRecentMedications()).ifPresent(donor::setRecentMedications);
		Optional.ofNullable(request.getMedicalConditions()).ifPresent(donor::setMedicalConditions);

		donor.setUpdatedAt(LocalDateTime.now());
		donor.getDonorHealthCheck().add(newCheck); 
		donor.getDonorRewards().add(newRewards);
	
		donorRepositary.save(donor);
		
		return donorToFullDonorVO(donor);
	}

	@Override
	public FullDonorResponseDto getDonorDeatils(Integer donorId) {
		// TODO Auto-generated method stub
		Donor donor = donorRepositary.findByDonorIdAndIsActive(donorId, true).orElseThrow(() -> 
	        new BloodBankBusinessException(ErrorConstants.DONOR_DETAILS_NOT_FOUND ,HttpStatus.BAD_REQUEST,ErrorConstants.INVALID_DATA));
		
		return donorToFullDonorVO(donor);

	}
	public static FullDonorResponseDto donorToFullDonorVO(Donor donor) {
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
