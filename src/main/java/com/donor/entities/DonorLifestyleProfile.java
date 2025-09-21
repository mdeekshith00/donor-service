package com.donor.entities;

import java.time.LocalDateTime;

import com.donor.enums.DietType;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
@Table(name = "donor_lifestyle_profile")
public class DonorLifestyleProfile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "donor_lifestyle_profile_id")
	private Integer DonorLifestyleProfile;
	@Embedded
	private Smoking smoking; //  (Yes/No, packs per day, since when)
	@Embedded
	private Alochol alcoholConsumption; // (Yes/No, frequency: daily/weekly/monthly, lastConsumedDate)
	@Embedded
	private Drug drugUse; // (Yes/No, type if any, lastUseDate)
	@Embedded
	private Tatoo tattoosOrPiercings; // (Yes/No, dateOfLastTattoo)
	@Embedded
	private SleepTime sleepPattern; // (Normal/Irregular, avg hours per day)
	@Enumerated(EnumType.STRING)
	@Column(nullable = true)
	private DietType dietType; // (Veg/Non-Veg/Vegan/Other)

	private String exerciseRoutine; //  (Regular/Occasional/Never)
	@Embedded
	private Habits otherhabits; // (free text – e.g., chewing tobacco, medications)

	private LocalDateTime lastUpdatedDate;

	@OneToOne
	@JoinColumn(name = "donor_id")
	@JsonBackReference
	private Donor donor;



}