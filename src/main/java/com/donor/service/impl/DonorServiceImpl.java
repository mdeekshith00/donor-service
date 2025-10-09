package com.donor.service.impl;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
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
import com.donor.mapper.MapperHelper;
import com.donor.repositary.DonorHealthCheckRepositary;
import com.donor.repositary.DonorLifestyleProfileRepositary;
import com.donor.repositary.DonorRepositary;
import com.donor.repositary.DonorRewardsRepositary;
import com.donor.service.DonorServcie;
import com.donor.service.UserServiceClient;
import com.donor.vo.DonorRequestVO;
import com.donor.vo.UpadteDonorRequestVO;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class DonorServiceImpl implements DonorServcie {
	
	private final DonorRepositary donorRepositary;
	private final UserServiceClient userServiceClient;
	private final DonorUserCacheService donorUserCacheService;
	private final DonorHealthCheckRepositary DonarHealthCheckRepositary; 
	private final DonorLifestyleProfileRepositary DonorLifestyleProfileRepositary;
	private final DonorRewardsRepositary DonorRewardsRepositary;
	private final MapperHelper mapperHelper;

	
    public DonorResponseDto getUserDetails(Integer userId) {
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
	        	return mapperHelper.DonorToVO(existingDonor.get());
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
	        donor.setDonationEligibilityStatus(DonationEligibilityStatus.ELIGIBLE);
	        donor.setRegisteredVia(RegisterType.APP);
	        donor.setStatus(StatusType.ACTIVE);
	        
	        donor = donorRepositary.save(donor);
	        
	        return mapperHelper.DonorToVO(donor);
	}
	


	@Override
	public FullDonorResponseDto updateDonorDetails(Integer donorId , UpadteDonorRequestVO request) {
		// TODO Auto-generated method stub
		Donor donor = donorRepositary.findByDonorIdAndIsActive(donorId, true).orElseThrow(() -> 
		new BloodBankBusinessException(ErrorConstants.DONOR_DETAILS_NOT_FOUND ,HttpStatus.BAD_REQUEST,ErrorConstants.INVALID_DATA));

		
		Optional.ofNullable(request.getDonorHealthCheck()).ifPresent(checkRequest -> {
	        DonorHealthCheck newCheck = DonorHealthCheck.builder()
	                .hemoglobinLevel(checkRequest.getHemoglobinLevel())
	                .bloodPressureDiastolic(checkRequest.getBloodPressureDiastolic())
	                .bloodPressureSystolic(checkRequest.getBloodPressureSystolic())
	                .temperature(checkRequest.getTemperature())
	                .pulseRate(checkRequest.getPulseRate())
	                .medicalRemarks(checkRequest.getMedicalRemarks())
	                .allergies(checkRequest.getAllergies())
	                .weight(checkRequest.getWeight())
	                .height(checkRequest.getHeight())
	                .healthNotes(checkRequest.getHealthNotes())
	                .screenedBy(checkRequest.getScreenedBy())
	                .status(checkRequest.getStatus())
	                .donor(donor)
	                .build();

	        DonarHealthCheckRepositary.save(newCheck);

//	        if (donor.getDonorHealthCheck() == null) {
//	            donor.setDonorHealthCheck(new HashSet<>());
//	        }
	        donor.getDonorHealthCheck().add(newCheck);
	    });

	    // 3️⃣ Handle DonorRewards (optional)
	    Optional.ofNullable(request.getDonorRewards()).ifPresent(rewardRequest -> {
	        DonorRewards newReward = DonorRewards.builder()
	                .type(rewardRequest.getType())
	                .title(rewardRequest.getTitle())
	                .description(rewardRequest.getDescription())
	                .issuedBy(rewardRequest.getIssuedBy())
	                .expiryDate(rewardRequest.getExpiryDate())
	                .status(rewardRequest.getStatus())
	                .redeemedAt(rewardRequest.getRedeemedAt())
	                .donor(donor)
	                .build();

	        DonorRewardsRepositary.save(newReward);

//	        if (donor.getDonorRewards() == null) {
//	            donor.setDonorRewards(new HashSet<>());
//	        }
	        donor.getDonorRewards().add(newReward);
	    });

	    // 4️⃣ Handle DonorLifestyleProfile (optional)
	    Optional.ofNullable(request.getDonorLifestyleProfile()).ifPresent(lifestyleRequest -> {
	        DonorLifestyleProfile lifeStyle = donor.getDonorLifestyleProfile();
	        if (lifeStyle == null) {
	            lifeStyle = new DonorLifestyleProfile();
	            lifeStyle.setDonor(donor);
	        }
	        lifeStyle.setDietType(lifestyleRequest.getDietType());
	        lifeStyle.setExerciseRoutine(lifestyleRequest.getExerciseRoutine());
	        lifeStyle.setAlcoholConsumption(lifestyleRequest.getAlcoholConsumption());
	        lifeStyle.setSmoking(lifestyleRequest.getSmoking());
	        lifeStyle.setSleepPattern(lifestyleRequest.getSleepPattern());
//	        lifeStyle.setHabits(lifestyleRequest.getHabits());
	        lifeStyle.setOtherHabitsDetails(lifestyleRequest.getOtherHabitsDetails());
	        lifeStyle.setTattoosOrPiercings(lifestyleRequest.getTattoosOrPiercings());

	        DonorLifestyleProfileRepositary.save(lifeStyle);
	        donor.setDonorLifestyleProfile(lifeStyle);
	    });

	    // 5️⃣ Update basic donor fields safely
	    Optional.ofNullable(request.getDonationEligibilityStatus()).ifPresent(donor::setDonationEligibilityStatus);
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

	    donorRepositary.save(donor);

	    return mapperHelper.donorToFullDonorVO(donor);
	}

	@Override
	public FullDonorResponseDto getDonorDeatils(Integer donorId) {
		// TODO Auto-generated method stub
		Donor donor = donorRepositary.findByDonorIdAndIsActive(donorId, true).orElseThrow(() -> 
	        new BloodBankBusinessException(ErrorConstants.DONOR_DETAILS_NOT_FOUND ,HttpStatus.BAD_REQUEST,ErrorConstants.INVALID_DATA));
		return mapperHelper.donorToFullDonorVO(donor);

	}

    @Transactional
    public Donor createIfNotExists(Integer userId) {
        return donorRepositary.findByUserId(userId).orElseGet(() -> {
            try {
                Donor d = Donor.builder()
                        .userId(userId)
                        .isActive(true)
                        .status(StatusType.ACTIVE)
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build();
                return donorRepositary.save(d);
            } catch (DataIntegrityViolationException ex) {
                // race: another process inserted; fetch existing
                return donorRepositary.findByUserId(userId).orElseThrow(() -> ex);
            }
        });
    }

}
