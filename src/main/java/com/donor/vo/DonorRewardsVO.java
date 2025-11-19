package com.donor.vo;

import java.time.LocalDate;

import com.common.enums.CERTIFICATETYPE;
import com.common.enums.StatusType;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class DonorRewardsVO {
	
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


}
