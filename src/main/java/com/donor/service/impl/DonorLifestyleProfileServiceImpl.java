 package com.donor.service.impl;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.common.constants.ErrorConstants;
import com.common.exception.BloodBankBusinessException;
import com.donor.dto.DonorLifeStyleProfileDto;
import com.donor.entities.Donor;
import com.donor.entities.DonorLifestyleProfile;
import com.donor.mapper.MapperHelper;
import com.donor.repositary.DonorLifestyleProfileRepositary;
import com.donor.repositary.DonorRepositary;
import com.donor.service.DonorLifestyleProfileService;
import com.donor.vo.DonorLifestyleProfileVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class DonorLifestyleProfileServiceImpl implements DonorLifestyleProfileService {
	
	private final DonorLifestyleProfileRepositary donorLifestyleProfileRepositary;
	private final DonorRepositary donorRepositary;
	private final MapperHelper mapperHelper;
	
	@Override
	public DonorLifeStyleProfileDto getDonorLifeStyleProfileById(Integer DonorLifestyleProfileId) {
		// TODO Auto-generated method stub
	DonorLifestyleProfile lifeStyle = 	donorLifestyleProfileRepositary.findByDonorLifestyleProfileId(DonorLifestyleProfileId)
		 .orElseThrow(() -> new BloodBankBusinessException(ErrorConstants.DONOR_LIFETSTYLE_PROFILE_DETAILS_NOT_FOUND , HttpStatus.BAD_REQUEST , ErrorConstants.DATA_NOT_FOUND));
		return mapperHelper.lifeStleToLifeStyleDto(lifeStyle);
	}

	@Override
	public DonorLifeStyleProfileDto addorUpdateDonorLifeStyle(Integer donorId,DonorLifestyleProfileVO donorLifestyleProfileVO ) {
		// TODO Auto-generated method stub
		Donor donor = donorRepositary.findByDonorIdAndIsActive(donorId, true).orElseThrow(() -> 
        new BloodBankBusinessException(ErrorConstants.DONOR_DETAILS_NOT_FOUND ,HttpStatus.BAD_REQUEST,ErrorConstants.INVALID_DATA));
	
		DonorLifestyleProfile profile = null;
		if(donorLifestyleProfileVO.getDonorLifestyleProfileId() != null) {
			 profile = 	donorLifestyleProfileRepositary.findByDonorLifestyleProfileId(donorLifestyleProfileVO.getDonorLifestyleProfileId())
					 .orElseThrow(() -> new BloodBankBusinessException(ErrorConstants.DONOR_LIFETSTYLE_PROFILE_DETAILS_NOT_FOUND , HttpStatus.BAD_REQUEST , ErrorConstants.DATA_NOT_FOUND));
			 profile =  mapperHelper.addorUpdateDonorLifeStyle(profile ,donorLifestyleProfileVO);
		}
			profile = new DonorLifestyleProfile();
			profile.setCreatedAt(LocalDateTime.now());
			profile =  mapperHelper.addorUpdateDonorLifeStyle(profile ,donorLifestyleProfileVO);
	     	profile.setDonor(donor);
	     	
		     profile =  donorLifestyleProfileRepositary.save(profile);

			donorLifestyleProfileRepositary.save(profile);
			 return  mapperHelper.lifeStleToLifeStyleDto(profile);
		
	
	}

}
