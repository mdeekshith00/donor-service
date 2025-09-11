package com.donor.entities;

import java.time.LocalDate;

public class PreDonationCheckup {
	//- `donorId` (FK → Donor)
	//- `donationEventId` (FK → Donation-Service)
	private Integer PreDonationCheckupId;
	private String bloodPressure;
	private String hemoglobinLevel;
	private String pulseRate;
	private String temperature;
	private String weightAtDonation;
	private String remarks; // (fit/unfit, reason)
	private String checkedBy; //  (Doctor/Nurse ID)
	private LocalDate  checkupDate;
	

}


