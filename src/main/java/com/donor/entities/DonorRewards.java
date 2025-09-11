package com.donor.entities;

import java.rmi.server.LoaderHandler;
import java.time.LocalDate;
import java.util.UUID;

public class DonorRewards {
	private UUID DonorRewardsId= UUID.randomUUID();
	private String type ; // CERTIFICATE, COUPON, VOUCHER, BADGE, POINTS).
	private String title; //  "Certificate of Appreciation" / "Free Coffee Voucher".
	private String description ;
	private LocalDate issuedDate;
	private LocalDate expiryDate ;
	private String  status; // ENUM: ACTIVE, USED, EXPIRED, REVOKED).
	private String issuedBy ; // (userId → Admin who issued OR system automation).
	private String redeemedAt ; //  if coupon/voucher, store partner/vendor.
	
	
	
	// donor reference

}
