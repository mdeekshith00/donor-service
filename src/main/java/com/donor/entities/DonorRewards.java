package com.donor.entities;

import java.io.Serializable;
import java.time.LocalDate;

import com.common.enums.StatusType;
import com.donor.enums.CERTIFICATETYPE;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
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
@Table(name ="donor_rewards")
public class DonorRewards implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "donor_rewards_id")
	private Integer DonorRewardsId;

	@Enumerated(EnumType.STRING)
	@Column(nullable = true)
	private CERTIFICATETYPE type ; // CERTIFICATE, COUPON, VOUCHER, BADGE, POINTS).

	private String title; //  "Certificate of Appreciation" / "Free Coffee Voucher".

	private String description ;

	private LocalDate issuedDate;

	private LocalDate expiryDate ;

	@Enumerated(EnumType.STRING)
	@Column(nullable = true)
	private StatusType status; // ENUM: ACTIVE, USED, EXPIRED, REVOKED).

	private String issuedBy ; // (userId → Admin who issued OR system automation).

	private String redeemedAt ; //  if coupon/voucher, store partner/vendor.

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY )
	@JoinColumn(name = "donor_id")
	@JsonBackReference
	private Donor donor;



}
