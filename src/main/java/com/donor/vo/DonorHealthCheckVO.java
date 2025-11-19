package com.donor.vo;

import com.common.enums.StatusType;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DonorHealthCheckVO {
	
	private Integer donorHealthCheckId;

	
	private String hemoglobinLevel;


	private Integer bloodPressureSystolic;


	private Integer bloodPressureDiastolic;


	private String temperature;

	private Integer pulseRate;


	private String medicalRemarks;


	private Boolean allergies;


	private Float weight;

	private Long height;


    private String healthNotes ;  // (diabetes, anemia, etc.)


    private String screenedBy; // (userId → medical staff from Hospital-Service).

    private StatusType status; //  ENUM: PASSED, FAILED, RECHECK_REQUIRED)


}
