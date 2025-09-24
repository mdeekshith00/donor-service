package com.donor.vo;

import java.time.LocalDate;

import com.donor.enums.Remarks;
import com.donor.enums.TestedBy;

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
public class PreDonationCheckupVO {
	

	private String bloodPressure;


	private String hemoglobinLevel;

	private String pulseRate;

	private String temperature;

	private String weightAtDonation;

	private Remarks remarks; // (fit/unfit, reason)

	private TestedBy checkedBy; //  (Doctor/Nurse ID)

	private LocalDate  checkupDate;

}
