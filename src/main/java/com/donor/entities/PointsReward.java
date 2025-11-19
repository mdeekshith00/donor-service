package com.donor.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("POINTS")
@Getter
@Setter
@NoArgsConstructor
public class PointsReward extends DonorRewards {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer pointsEarned;
    private Integer totalPoints; // cumulative for the donor
    private String reason;       // e.g., "Blood donation", "Referral"
}