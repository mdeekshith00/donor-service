package com.donor.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.donor.enums.DietType;
import com.donor.enums.HabitsType;
import com.donor.enums.SleepType;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
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
public class DonorLifestyleProfile implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "donor_lifestyle_profile_id")
	private Integer DonorLifestyleProfile;
    @Column
    private Boolean smoking;
    @Column
    private Boolean alcoholConsumption;
    @Column
    private Boolean drugUse;
    @Column
    private Boolean tattoosOrPiercings;
    @Column
    @Enumerated(EnumType.STRING)
    private SleepType sleepPattern; // (Normal/Irregular, avg hours per day)
    
	@Enumerated(EnumType.STRING)
	@Column(nullable = true)
	private DietType dietType; // (Veg/Non-Veg/Vegan/Other)

	private String exerciseRoutine; //  (Regular/Occasional/Never)
 
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "donor_habits", joinColumns = @JoinColumn(name = "profile_id"))
    @Column(name = "habit")
	private HabitsType habits ; 
    
    @Column(length = 200)
    private String otherHabitsDetails; // Free text for habits not in enum

	private LocalDateTime lastUpdatedDate;

	@OneToOne
	@JoinColumn(name = "donor_id")
	@JsonBackReference
	private Donor donor;

}
//@Embedded
//private SleepTime sleepPattern;
//@Embedded
//private Tatoo tattoosOrPiercings;
//@Embedded
//private Smoking smoking; //  (Yes/No, packs per day, since when)
//@Embedded
//private Alochol alcoholConsumption; // (Yes/No, frequency: daily/weekly/monthly, lastConsumedDate)
//@Embedded
//private Drug drugUse; // (Yes/No, type if any, lastUseDate)