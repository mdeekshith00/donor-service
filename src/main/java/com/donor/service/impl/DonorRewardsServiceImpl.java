package com.donor.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.common.constants.ErrorConstants;
import com.common.enums.CERTIFICATETYPE;
import com.common.enums.StatusType;
import com.common.exception.BloodBankBusinessException;
import com.common.util.DonorRewardUtil;
import com.donor.dto.DonorRewardsDto;
import com.donor.entities.CertificateReward;
import com.donor.entities.Donor;
import com.donor.entities.DonorRewards;
import com.donor.repositary.CertificateRewardRepository;
import com.donor.repositary.DonorRepositary;
import com.donor.repositary.DonorRewardsRepositary;
import com.donor.service.DonorRewardsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class DonorRewardsServiceImpl implements DonorRewardsService {
	
	private final DonorRepositary donorRepo;
	private final DonorRewardsRepositary donorRewardsRepositary;
    private final CertificateRewardRepository certificateRewardRepository;


	@Override
	public DonorRewardsDto getRewards(Integer DonorRewardsId) {
		// TODO Auto-generated method stub
		DonorRewards  rewards = donorRewardsRepositary.findByDonorRewardsId(DonorRewardsId)
		 .orElseThrow(() -> new BloodBankBusinessException(ErrorConstants.DONOR_REWARDS_DETAILS_NOT_FOUND , HttpStatus.BAD_REQUEST , ErrorConstants.DATA_NOT_FOUND));

		return DonorRewardsDto.builder()
				.type(rewards.getType().toString())
				.title(rewards.getTitle())
				.description(rewards.getDescription())
				.issuedDate(rewards.getIssuedDate())
				.issuedBy(rewards.getIssuedBy())
				.expiryDate(rewards.getExpiryDate())
				.status(rewards.getStatus().toString())
				.issuedBy(rewards.getIssuedBy())
				.redeemedAt(rewards.getRedeemedAt())
				.build();
	}
	 public DonorRewardsDto addRewardToDonor(Integer donorId) {
		 Donor donor = donorRepo.findByDonorIdAndIsActiveAndIsVerified(donorId, true , true).orElseThrow(() -> 
	        new BloodBankBusinessException(ErrorConstants.DONOR_DETAILS_NOT_FOUND ,HttpStatus.BAD_REQUEST,ErrorConstants.INVALID_DATA));
		 
		 Integer totalDonations = donor.getTotalDonations();
		 DonorRewardUtil.Reward reward = DonorRewardUtil.getRewardByTotalDonations(totalDonations);

		 DonorRewards donorRewards = new DonorRewards();
		 donorRewards.setType(reward.getCertificateType());
		 donorRewards.setTitle(reward.getTitle());
		 donorRewards.setDescription("Awarded for outstanding contribution and saving lives through blood donation.");
		 donorRewards.setIssuedDate(LocalDate.now());
		 donorRewards.setExpiryDate(LocalDate.now().plusMonths(6));
		 donorRewards.setStatus(StatusType.ACTIVE);
		 donorRewards.setIssuedBy("SYSTEM_AUTOMATION");
		 donorRewards.setCreatedAt(LocalDateTime.now());
		 donorRewards.setUpdatedAt(LocalDateTime.now());
		 donorRewards.setDonor(donor);
		 donorRewardsRepositary.save(donorRewards);

		return null;
		 
	 }
	  @Override
	    public CertificateReward issueReward(Donor donor) {
	        CertificateReward reward = new CertificateReward();
	        reward.setType(CERTIFICATETYPE.CERTIFICATE);
	        reward.setTitle("Certificate of Appreciation");
	        reward.setDescription("Thank you for donating blood and saving lives!");
	        reward.setIssuedDate(LocalDate.now());
	        reward.setStatus(StatusType.ACTIVE);
	        reward.setDonor(donor);
	        reward.setCertificateUrl(generateCertificatePDF(donor));
	        return certificateRewardRepository.save(reward);
	    }

	    private String generateCertificatePDF(Donor donor) {
	        // logic for generating PDF using iText or OpenPDF
	        return "/certificates/" + donor.getDonorId() + "_appreciation.pdf";
	    }
	

}
