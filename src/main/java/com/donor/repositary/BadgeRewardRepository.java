package com.donor.repositary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.donor.entities.BadgeReward;

public interface BadgeRewardRepository extends JpaRepository<BadgeReward, Integer> {}
