package com.donor.repositary;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.common.enums.CERTIFICATETYPE;
import com.common.enums.StatusType;
import com.donor.entities.DonorRewards;

public interface DonorRewardsRepositary extends JpaRepository<DonorRewards, Integer> {
	
	Optional<DonorRewards> findByDonorRewardsId(Integer donorRewardsId);
	Optional<DonorRewards> findByDonorRewardsIdAndType(Integer donorRewardsId , CERTIFICATETYPE tye);
	Optional<DonorRewards> findByDonorRewardsIdAndTypeAndStatus(Integer donorRewardsId , CERTIFICATETYPE tye ,StatusType status);

}
