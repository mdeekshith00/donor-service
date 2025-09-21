package com.donor.entities;

import java.time.LocalDateTime;

public class BloodTestResult {
	private Integer BloodTestResultId;
	private String bloodGroupConfirmed;
	private String HIVTest; // (Positive/Negative)
	private String HepatitisBTest;
	private String HepatitisCTest;
	private String SyphilisTest;
	private String MalariaTest;
	private String otherTests; // (extra as per govt norms)

	private String resultStatus; //  (Safe / Rejected)
	private String testedBy; // (labtech iD)
	private LocalDateTime testDateTime;


}
