package com.donor.service;

import com.common.dto.DonationResponseDto;
import com.common.vo.DonationRequestVO;
import com.donor.dto.FullDonorResponseDto;
import com.donor.entities.Donor;
import com.donor.vo.DonorRequestVO;
import com.donor.vo.UpadteDonorRequestVO;

public interface DonorServcie {
	 
	public FullDonorResponseDto fetchUserAndCreateDonor(DonorRequestVO request);
	public FullDonorResponseDto getDonorDeatils(Integer donorId);
	public FullDonorResponseDto updateDonorDetails(Integer donorId , UpadteDonorRequestVO request);
	public Donor createIfNotExists(Integer userId);
    public DonationResponseDto validateDonateBlood(Integer donorId,  DonationRequestVO donationRequest);


}
