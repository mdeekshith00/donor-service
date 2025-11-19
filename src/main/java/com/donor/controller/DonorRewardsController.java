package com.donor.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.donor.dto.DonorRewardsDto;
import com.donor.service.DonorRewardsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/donorrewards")
@RestController
@RequiredArgsConstructor
public class DonorRewardsController {
	
	private final DonorRewardsService donorRewardsServicea;
	
	@GetMapping("/{DonorRewardsId}")
	public ResponseEntity<DonorRewardsDto>  getRewards(@PathVariable Integer DonorRewardsId) {
		DonorRewardsDto rewardsDto = donorRewardsServicea.getRewards(DonorRewardsId);
		return ResponseEntity.status(HttpStatus.OK).body(rewardsDto);
		
	}
	@GetMapping
	public ResponseEntity<DonorRewardsDto>  addRewardToDonor(@PathVariable Integer donorId) {
		DonorRewardsDto rewardsDto = donorRewardsServicea.addRewardToDonor(donorId);
		return ResponseEntity.status(HttpStatus.OK).body(rewardsDto);
	}
	

}
