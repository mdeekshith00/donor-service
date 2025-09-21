package com.donor.entities;

import java.io.Serializable;
import java.time.LocalDate;

import com.donor.enums.Remarks;
import com.donor.enums.TestedBy;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
@Table(name = "pre_donation_checkup")
@Entity
public class PreDonationCheckup implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pre_donation_checkup_id" , nullable = true)
	private Integer PreDonationCheckupId;

	@Column(nullable = true)
	private String bloodPressure;

	@Column(nullable = true)
	private String hemoglobinLevel;

	@Column(nullable = true)
	private String pulseRate;

	@Column(nullable = true)
	private String temperature;

	@Column(nullable = true)
	private String weightAtDonation;

	@Enumerated(EnumType.STRING)
	@Column(nullable = true)
	private Remarks remarks; // (fit/unfit, reason)

	@Enumerated(EnumType.STRING)
	@Column(nullable = true)
	private TestedBy checkedBy; //  (Doctor/Nurse ID)

	@Column(nullable = true)
	private LocalDate  checkupDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "donor_id")
	@JsonManagedReference
	private Donor donor;


}


