package com.donor.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor 
@Builder
@Entity
@Table(name = "donor")
public class Donor {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer donorId;
		
		private Integer userId; // refering from user
		
		private String bloodGroup;
		
	    private String donationEligibilityStatus; // eligible, not eligible, pending approval
		 
		private Boolean isAvailableToDonate;
		
	    private LocalDate lastDonationDate;
	   
		 private LocalDate nextEligibleDate;
		 
		 private Integer totalDonations;
		    
		 private Integer totalUnitsDonated;
		     
		 private Boolean isEligibleToDonate;
		 
		 private String ineligibilityReason; // e.g., "Low hemoglobin", "Medical condition"
		 
		 private LocalDate temporarilyIneligibleUntil;
		    
		 private Boolean isActive; // If donor is still participating
		 
		 private Boolean isVerified; // If user has passed eligibility verification
		 
		 private String registeredVia; // e.g., "app", "web", "camp"

		 private Double weightInKg;
		 
		 private Double hemoglobinLevel;       // g/dL
		 
		 private Boolean hasChronicDiseases;   // e.g., diabetes, hypertension
		 
		 private String recentMedications;
		 
		 private String medicalConditions; 
		 
		 private String healthNotes ;  // (diabetes, anemia, etc.)
		      
		 private LocalDateTime createdAt;
		 
		 private LocalDateTime updatedAt;
		 
		 private String status; // (ENUM: ACTIVE, INACTIVE, DECEASED)

	}



