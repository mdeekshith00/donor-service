package com.donor.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DonorHealthCheckDto {
	
		private Integer DonorHealthCheckId;

		
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

	    private String status; //  ENUM: PASSED, FAILED, RECHECK_REQUIRED)

}
