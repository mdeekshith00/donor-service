package com.donor.vo;

import java.time.LocalDateTime;

import com.donor.enums.DietType;
import com.donor.enums.HabitsType;
import com.donor.enums.SleepType;

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
public class DonorLifestyleProfileVO {
	
	private Integer donorLifestyleProfileId;
    
    private Boolean smoking;
  
    private Boolean alcoholConsumption;
    private Boolean drugUse;
    
    private Boolean tattoosOrPiercings;
    
    private SleepType sleepPattern; // (Normal/Irregular, avg hours per day)

	private DietType dietType; // (Veg/Non-Veg/Vegan/Other)

	private String exerciseRoutine; //  (Regular/Occasional/Never)

	private HabitsType habits ; 

    private String otherHabitsDetails; // Free text for habits not in enum

	private LocalDateTime lastUpdatedDate;
	
	

}
