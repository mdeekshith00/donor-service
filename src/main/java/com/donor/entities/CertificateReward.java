package com.donor.entities;

import java.time.LocalDate;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("CERTIFICATE")
@Getter
@Setter
@NoArgsConstructor
public class CertificateReward  extends  DonorRewards{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	  private String certificateUrl;   // Path to the generated PDF
	    private String templateUsed;     // e.g., "Appreciation_Template_1"
	    private LocalDate donationDate;  // Certificate linked to which donation
}
