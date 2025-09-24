package com.donor.repositary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.donor.entities.DonorRewards;

public interface DonorRewardsRepositary extends JpaRepository<DonorRewards, Integer>{

}
