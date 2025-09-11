package com.donor.entities;

import java.time.LocalDateTime;

public class DonorLifestyleProfile {
	private Integer DonorLifestyleProfile;
	private Smoking smoking; //  (Yes/No, packs per day, since when)
	private Alochol alcoholConsumption; // (Yes/No, frequency: daily/weekly/monthly, lastConsumedDate)
	private Drug drugUse; // (Yes/No, type if any, lastUseDate)
	private Tatoo tattoosOrPiercings; // (Yes/No, dateOfLastTattoo)
	private SleepTime sleepPattern; // (Normal/Irregular, avg hours per day)
	private String dietType; // (Veg/Non-Veg/Vegan/Other)
	private String exerciseRoutine; //  (Regular/Occasional/Never)
	private Habits otherhabits; // (free text – e.g., chewing tobacco, medications)
	private LocalDateTime lastUpdatedDate;
	
	

}