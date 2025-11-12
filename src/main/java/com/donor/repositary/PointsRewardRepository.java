package com.donor.repositary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.donor.entities.PointsReward;

public interface PointsRewardRepository extends JpaRepository<PointsReward, Integer> {}