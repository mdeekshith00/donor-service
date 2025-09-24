package com.donor.repositary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.donor.entities.PreDonationCheckup;

public interface PreDonationCheckupRepositary extends JpaRepository<PreDonationCheckup, Integer>{

}
