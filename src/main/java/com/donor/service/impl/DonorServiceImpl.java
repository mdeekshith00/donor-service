package com.donor.service.impl;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.UUID;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.common.constants.ErrorConstants;
import com.common.dto.DonationResponseDto;
import com.common.dto.DonorResponseDto;
import com.common.enums.BloodGroupType;
import com.common.enums.DonationEligibilityStatus;
import com.common.enums.RegisterType;
import com.common.enums.StatusType;
import com.common.exception.BloodBankBusinessException;
import com.common.vo.DonationRequestVO;
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
	private final MapperHelper mapperHelper;
	private final DonationService donationService;

	
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
	   
//	        Optional.ofNullable(request.getBloodGroup()).map(enum::String).ifPresent(donor::setBloodGroup);
	        Optional.ofNullable(request.getIsAvailableToDonate()).ifPresent(donor::setIsAvailableToDonate);
	        Optional.ofNullable(request.getTotalDonations()).ifPresent(donor::setTotalDonations);
	        Optional.ofNullable(request.getTotalUnitsDonated()).ifPresent(donor::setTotalUnitsDonated);
	        Optional.ofNullable(request.getIsEligibleToDonate()).ifPresent(donor::setIsEligibleToDonate);
	        Optional.ofNullable(request.getWeightInKg()).ifPresent(donor::setWeightInKg);
	        Optional.ofNullable(request.getRecentMedications()).ifPresent(donor::setRecentMedications);
	        Optional.ofNullable(request.getMedicalConditions()).ifPresent(donor::setMedicalConditions);
	        
	        donor.setIsActive(true);
	        donor.setUserId(request.getUserId());
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
    public Donor createIfNotExists(Integer userId ) {
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

	@Override
	public DonationResponseDto validateDonateBlood(Integer donorId, DonationRequestVO donationRequest) {
		// TODO Auto-generated method stub
		Donor donor = donorRepositary.findByDonorIdAndIsActiveAndDonationEligibilityStatusAndIsEligibleToDonateAndIsVerified(donorId, true ,DonationEligibilityStatus.ELIGIBLE,true,true)
				.orElseThrow(() -> new BloodBankBusinessException(ErrorConstants.DONOR_DETAILS_NOT_FOUND ,HttpStatus.BAD_REQUEST,ErrorConstants.INVALID_DATA));
		
		if(donationRequest.getVolume() == null) {
			throw new BloodBankBusinessException(ErrorConstants.DONOTATION_SHOULD_NOT_BE_NULL ,HttpStatus.BAD_REQUEST,ErrorConstants.INVALID_DATA);
		}
		int volume = (int)Math.floor(donationRequest.getVolume());  // int i1 = (int) Math.floor(d1);
		if(donationRequest.getBloodGroup() != null) {
			donor.setBloodGroup(donationRequest.getBloodGroup().toString());
			donor.setTotalUnitsDonated(volume);
			donorRepositary.save(donor);
		}

		DonationResponseDto responseDto =  DonationResponseDto.builder()
				.bloodGroup(BloodGroupType.valueOf(donationRequest.getBloodGroup().toUpperCase()))
				.donorId(donor.getDonorId())
				.eventId(Integer.valueOf(donor.getDonorId()))
				.volume(donationRequest.getVolume())
				.build();
		
		donationService.notifyDonationServiceAsync(donationRequest);
		
		return responseDto;
	}

}
