package com.donor.repositary;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.common.enums.StatusType;
import com.donor.entities.DonorHealthCheck;

public interface DonorHealthCheckRepositary extends JpaRepository<DonorHealthCheck, Integer>{

	Optional<DonorHealthCheck> findByDonorHealthCheckId(Integer donorHealthCheckId);
	Optional<DonorHealthCheck> findByDonorHealthCheckIAndStatus(Integer donorHealthCheckId ,String PASSED );

}
