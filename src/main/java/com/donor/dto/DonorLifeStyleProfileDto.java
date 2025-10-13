package com.donor.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DonorLifeStyleProfileDto {
	
	private Integer DonorLifestyleProfileId;
    private Boolean smoking;
    private Boolean alcoholConsumption;
    private Boolean drugUse;
    private Boolean tattoosOrPiercings;   
    private String sleepPattern; 
	private String dietType; 
	private String exerciseRoutine; 
	private String habits ; 
    private String otherHabitsDetails; 
	private LocalDateTime lastUpdatedDate;

}
