package com.donor.repositary;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.common.enums.StatusType;
import com.donor.entities.Donor;

public interface DonorRepositary extends JpaRepository<Donor, Integer>{
	
	Optional<Donor> findByUserId(Integer userId);
	Optional<Donor> findByDonorIdAndIsActive(Integer donorId, boolean isActive);

}
