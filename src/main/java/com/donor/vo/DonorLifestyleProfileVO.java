package com.donor.vo;

import java.time.LocalDateTime;

import com.donor.entities.Alochol;
import com.donor.entities.Drug;
import com.donor.entities.Habits;
import com.donor.entities.SleepTime;
import com.donor.entities.Smoking;
import com.donor.entities.Tatoo;
import com.donor.enums.DietType;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NegativeOrZero;
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

	private DietType dietType; // (Veg/Non-Veg/Vegan/Other)

	private String exerciseRoutine; //  (Regular/Occasional/Never)
	@Embedded
	private Habits otherhabits; // (free text – e.g., chewing tobacco, medications)

	private LocalDateTime lastUpdatedDate;
	
	

}
