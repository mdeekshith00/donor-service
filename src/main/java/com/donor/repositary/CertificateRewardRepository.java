package com.donor.repositary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.donor.entities.CertificateReward;

public interface CertificateRewardRepository extends JpaRepository<CertificateReward, Integer> {}