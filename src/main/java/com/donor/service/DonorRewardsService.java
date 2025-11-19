package com.donor.service;

import com.donor.dto.DonorRewardsDto;
import com.donor.entities.Donor;
import com.donor.entities.DonorRewards;

public interface DonorRewardsService {
	
	public DonorRewardsDto getRewards(Integer donorRewardsId);
	public DonorRewardsDto addRewardToDonor(Integer donorId);
    DonorRewards issueReward(Donor donor);


}
