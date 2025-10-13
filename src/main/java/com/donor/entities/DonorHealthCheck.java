package com.donor.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.common.enums.StatusType;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "donor_health_check")
public class DonorHealthCheck implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "donor_health_check_id")
	private Integer DonorHealthCheckId;

	@Column(nullable = true)
	private String hemoglobinLevel;

	@Column(nullable = true)
	private Integer bloodPressureSystolic;

	@Column(nullable = true)
	private Integer bloodPressureDiastolic;

	@Column(nullable = true)
	private String temperature;

	private Integer pulseRate;

	@Column(nullable = true)
	private String medicalRemarks;

	@Column(nullable = true)
	private Boolean allergies;

	@Column(nullable = true)
	private Float weight;

	@Column(nullable = true)
	private Long height;

	@Column(nullable = true)
    private String healthNotes ;  // (diabetes, anemia, etc.)

	@Column(nullable = true)
    private String screenedBy; // (userId → medical staff from Hospital-Service).

	@Enumerated(EnumType.STRING)
	@Column(nullable = true)
    private StatusType status; //  ENUM: PASSED, FAILED, RECHECK_REQUIRED)

    @ManyToOne
    @JoinColumn(name = "donor_id")
    @JsonBackReference
    private Donor donor;
    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;


}