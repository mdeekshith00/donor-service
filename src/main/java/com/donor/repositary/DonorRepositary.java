package com.donor.repositary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.donor.entities.Donor;

public interface DonorRepositary extends JpaRepository<Donor, Integer>{

}
