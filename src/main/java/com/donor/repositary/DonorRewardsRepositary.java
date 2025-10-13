package com.donor.repositary;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.common.enums.CERTIFICATETYPE;
import com.common.enums.StatusType;
import com.donor.entities.DonorRewards;

public interface DonorRewardsRepositary extends JpaRepository<DonorRewards, Integer>{
	
	Optional<DonorRewards> findByDonorRewardsId(Integer DonorRewardsId);
	Optional<DonorRewards> findByDonorRewardsIdAndType(Integer DonorRewardsId , CERTIFICATETYPE tye);
	Optional<DonorRewards> findByDonorRewardsIdAndTypeAndStatus(Integer DonorRewardsId , CERTIFICATETYPE tye ,StatusType status);

}
