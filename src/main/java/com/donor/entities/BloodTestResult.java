package com.donor.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.common.enums.BloodGroupType;
import com.donor.enums.TestResult;
import com.fasterxml.jackson.annotation.JsonBackReference;

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
@Entity
@Table(name = "blood_test_result")
public class BloodTestResult implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blood_test_result_id")
    private Integer BloodTestResultId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "donor_id", nullable = false)
    @JsonBackReference
    private Donor donor;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lab_id")
    private Lab lab;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BloodGroupType bloodGroupConfirmed;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TestResult HIVTest;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TestResult HepatitisBTest;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TestResult HepatitisCTest;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TestResult SyphilisTest;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TestResult MalariaTest;

    @Column(length = 200)
    private String otherTests; // free-text for additional tests

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TestResult resultStatus; // SAFE / REJECTED

    @Column(length = 50, nullable = false)
    private String testedBy; // labtech id or name

    @Column(nullable = false)
    private LocalDateTime testDateTime;
}
