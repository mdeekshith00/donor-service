package com.donor.service;

import com.donor.dto.DonorLifeStyleProfileDto;
import com.donor.vo.DonorLifestyleProfileVO;

public interface DonorLifestyleProfileService {
	
	public DonorLifeStyleProfileDto getDonorLifeStyleProfileById(Integer donorLifestyleProfileId);
    public DonorLifeStyleProfileDto addorUpdateDonorLifeStyle(Integer donorId ,  DonorLifestyleProfileVO  donorLifestyleProfileVO);
}
