package com.donor.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.donor.dto.DonorHealthCheckDto;
import com.donor.service.DonorHealthCheckService;
import com.donor.vo.DonorHealthCheckVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/donorhealth")
@RequiredArgsConstructor
public class DonorHealthCheckController {
	
	private final DonorHealthCheckService donorHealthCheckService; 
	
	@GetMapping("/{DonorHealthCheckId}")
	public ResponseEntity<DonorHealthCheckDto>  getDonorHealthCheck(@PathVariable Integer DonorHealthCheckId) {
		DonorHealthCheckDto healthDto = 	donorHealthCheckService.getDonorHealthCheck(DonorHealthCheckId);
		return ResponseEntity.status(HttpStatus.OK).body(healthDto);
		
	}
	@PostMapping
	public ResponseEntity<DonorHealthCheckDto> addorUpdateHealthCheck(Integer donorId ,DonorHealthCheckVO donorHealthCheckVO ) {
		DonorHealthCheckDto healthDto = donorHealthCheckService.addorUpdateHealthCheck(donorId, donorHealthCheckVO);
		return ResponseEntity.status(HttpStatus.OK).body(healthDto);
	}
	@DeleteMapping
	public ResponseEntity<String> deleteHealthCheck(Integer DonorHealthCheckId) {
		donorHealthCheckService.deleteHealthCheck(DonorHealthCheckId);
		return ResponseEntity.status(HttpStatus.OK)  .body("Health check deleted successfully");
	}

}
