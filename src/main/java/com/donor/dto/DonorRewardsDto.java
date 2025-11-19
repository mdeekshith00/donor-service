package com.donor.dto;

import java.time.LocalDate;

import com.common.enums.CERTIFICATETYPE;
import com.common.enums.StatusType;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DonorRewardsDto {
	
	private String type ; 
	private String title; 
	private String description ;
	private LocalDate issuedDate;
	private LocalDate expiryDate ;
	private String status; 
	private String issuedBy ; 
	private String redeemedAt ; 

}
