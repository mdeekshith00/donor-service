package com.donor.repositary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.donor.entities.DonorHealthCheck;

public interface DonorHealthCheckRepositary extends JpaRepository<DonorHealthCheck, Integer>{

}
