package com.donor.entities;

public class DonorHealthCheck {
	
	private Integer DonorHealthCheckId;
	private String hemoglobinLevel;
	private Integer bloodPressureSystolic;
	private Integer bloodPressureDiastolic;
	private String temperature;
	private Integer pulseRate;
	private String medicalRemarks;
//	private String lifestyleFactors ; //  (smoking, alcohol, medication)
//	private String chronicDiseases; //  (diabetes, hypertension, etc.)
	private Boolean allergies;
	private Float weight;
	private Long height;
    private String healthNotes ;  // (diabetes, anemia, etc.)
    private String screenedBy; // (userId → medical staff from Hospital-Service).
    private String status; //  ENUM: PASSED, FAILED, RECHECK_REQUIRED)
    
    // donor refrence

}