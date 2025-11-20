package com.donor.repositary;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.common.enums.DonationEligibilityStatus;
import com.donor.entities.Donor;

public interface DonorRepositary extends JpaRepository<Donor, Integer>{
	
	Optional<Donor> findByUserId(Integer userId);
	Page<Donor> findByBloodGroup(String bloodGroup, Pageable pageable);

    Page<Donor> findByBloodGroupAndLocation(String bloodGroup, String location, Pageable pageable);
	Optional<Donor> findByDonorIdAndIsActive(Integer donorId, boolean isActive);
	Optional<Donor> findByDonorIdAndIsActiveAndIsVerified(Integer donorId, boolean isActive , boolean isVerified);
	Optional<Donor> findByDonorIdAndIsActiveAndDonationEligibilityStatusAndIsEligibleToDonateAndIsVerified(Integer donorId , boolean isActive , DonationEligibilityStatus ELIGIBLE , boolean isEligibleToDonate , boolean isVerified);

}
