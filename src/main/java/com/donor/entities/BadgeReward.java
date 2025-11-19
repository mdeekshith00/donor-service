package com.donor.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("BADGE")
@Getter
@Setter
@NoArgsConstructor
public class BadgeReward extends DonorRewards {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String badgeLevel; // e.g., BRONZE, SILVER, GOLD
    private String imageUrl;   // For displaying badge image in UI
    private int threshold;     // e.g., 3 donations for BRONZE, etc.
}