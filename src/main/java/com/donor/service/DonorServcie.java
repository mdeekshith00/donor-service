package com.donor.service;

import com.donor.entities.Donor;
import com.donor.vo.DonorRequestVO;
import com.donor.vo.DonorResponseVO;

public interface DonorServcie {
	
//	public Donor getOrCreateDonor(Integer userId, String bloodGroup);
	public Donor createDonor(DonorRequestVO request);


}
