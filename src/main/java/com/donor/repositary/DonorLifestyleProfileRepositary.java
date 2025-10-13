package com.donor.repositary;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.donor.entities.DonorLifestyleProfile;

public interface DonorLifestyleProfileRepositary extends JpaRepository<DonorLifestyleProfile, Integer>{
	
	Optional<DonorLifestyleProfile> findByDonorLifestyleProfileId(Integer DonorLifestyleProfileId);

}
