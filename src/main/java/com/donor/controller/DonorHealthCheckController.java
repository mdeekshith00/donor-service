package com.donor.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.donor.dto.DonorHealthCheckDto;
import com.donor.service.DonorHealthCheckService;
import com.donor.vo.DonorHealthCheckVO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/donorhealth")
@RequiredArgsConstructor
public class DonorHealthCheckController {
	
	private final DonorHealthCheckService donorHealthCheckService; 
	
	@GetMapping("/{donorHealthCheckId}")
	public ResponseEntity<DonorHealthCheckDto>  getDonorHealthCheck(@PathVariable Integer donorHealthCheckId) {
		DonorHealthCheckDto healthDto = 	donorHealthCheckService.getDonorHealthCheck(donorHealthCheckId);
		return ResponseEntity.status(HttpStatus.OK).body(healthDto);
		
	}
	@PostMapping("/add-update/{donorId}")
	public ResponseEntity<DonorHealthCheckDto> addorUpdateHealthCheck(@PathVariable Integer donorId , @RequestBody @Valid DonorHealthCheckVO donorHealthCheckVO ) {
		DonorHealthCheckDto healthDto = donorHealthCheckService.addorUpdateHealthCheck(donorId, donorHealthCheckVO);
		return ResponseEntity.status(HttpStatus.OK).body(healthDto);
	}
	@DeleteMapping("/delete/{donorHealthCheckId}")
	public ResponseEntity<String> deleteHealthCheck(@PathVariable Integer donorHealthCheckId) {
		donorHealthCheckService.deleteHealthCheck(donorHealthCheckId);
		return ResponseEntity.status(HttpStatus.OK)  .body("Health check deleted successfully");
	}

}
