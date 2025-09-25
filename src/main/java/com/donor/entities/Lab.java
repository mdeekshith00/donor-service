package com.donor.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@Entity
@Table(name = "lab")
public class Lab implements Serializable {

	    private static final long serialVersionUID = 1L;

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "lab_id")
	    private Integer labId;

	    @Column(nullable = false)
	    private String labName;

	    @Column(nullable = false)
	    private String location;

	    @Column(nullable = false)
	    private String contactPerson;

	    @Column(nullable = false)
	    private String contactEmail;

	    @Column(nullable = false)
	    private String contactPhone;

	    @Column(nullable = true)
	    private String registrationNumber; // optional govt registration ID

	    @Column(nullable = false)
	    private LocalDateTime createdAt;

	    @Column(nullable = false)
	    private LocalDateTime updatedAt;

	    // 1 lab can process multiple blood tests
	    @OneToMany(mappedBy = "lab", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	    private List<BloodTestResult> bloodTestResults = new ArrayList<>();

	    // Constructors, getters, setters
	

}
