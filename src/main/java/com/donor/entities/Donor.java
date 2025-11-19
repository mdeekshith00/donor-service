package com.donor.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.common.enums.BloodGroupType;
import com.common.enums.DonationEligibilityStatus;
import com.common.enums.RegisterType;
import com.common.enums.StatusType;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
public class Donor implements Serializable {

		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "donor_id")
		private Integer donorId;
 
		private String bloodGroup;

		@Enumerated(EnumType.STRING)
		@Column(nullable = true)
	    private DonationEligibilityStatus donationEligibilityStatus; // eligible, not eligible, pending approval

		private Boolean isAvailableToDonate;

	    private LocalDate lastDonationDate;

		 private LocalDate nextEligibleDate;

		 private Integer totalDonations;

		 private Integer totalUnitsDonated;  // 1 unit = ~450 ml of blood.

		 private Boolean isEligibleToDonate;

		 private String ineligibilityReason; // e.g., "Low hemoglobin", "Medical condition"

		 private LocalDate temporarilyIneligibleUntil;

		 private Boolean isActive; // If donor is still participating

		 private Boolean isVerified; // If user has passed eligibility verification
		 
		 @Enumerated(EnumType.STRING)
		 @Column(nullable = true)
		 private RegisterType registeredVia; // e.g., "app", "web", "camp"

		 private Double weightInKg;

		 private Double hemoglobinLevel;       // g/dL

		 private Boolean hasChronicDiseases;   // e.g., diabetes, hypertension

		 @CreatedDate
		 private LocalDateTime createdAt;

		 @LastModifiedDate
		 private LocalDateTime updatedAt;
		 @Enumerated(EnumType.STRING)
		 @Column
		 private StatusType status; // (ENUM: ACTIVE, INACTIVE, DECEASED)
         @Column(unique = true, nullable = false)
		 private Integer userId; // refering from user
		 
		 private String recentMedications;
		 
		 private String medicalConditions;

	     @OneToMany(mappedBy = "donor", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
		 @JsonManagedReference
		 private List<DonorHealthCheck> DonorHealthCheck = new ArrayList<>();

		 @OneToOne(mappedBy = "donor", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
		 @JsonManagedReference
		 private DonorLifestyleProfile donorLifestyleProfile;

		 @OneToMany(mappedBy = "donor", cascade = CascadeType.ALL, orphanRemoval = true)
		 @JsonManagedReference
		 private List<DonorRewards> donorRewards = new ArrayList<>();


}



