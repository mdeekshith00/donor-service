package com.donor.service;

import com.donor.dto.FullDonorResponseDto;
import com.donor.vo.DonorRequestVO;
import com.donor.vo.UpadteDonorRequestVO;

public interface DonorServcie {
	 
	public FullDonorResponseDto fetchUserAndCreateDonor(DonorRequestVO request);
	public FullDonorResponseDto getDonorDeatils(Integer donorId);
	public FullDonorResponseDto updateDonorDetails(Integer donorId , UpadteDonorRequestVO request);


}
