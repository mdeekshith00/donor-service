package com.donor.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.donor.dto.FullDonorResponseDto;
import com.donor.service.DonorServcie;
import com.donor.vo.DonorRequestVO;
import com.donor.vo.UpadteDonorRequestVO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequiredArgsConstructor
@RequestMapping("/donor")
@Slf4j
public class DonorController {

	private final DonorServcie donorService;
	
   @PostMapping
	public ResponseEntity<FullDonorResponseDto> fetchUserAndCreateDonor(@RequestBody @Valid DonorRequestVO request) {
		return  ResponseEntity.status(HttpStatus.OK).body(donorService.fetchUserAndCreateDonor(request));
		
	}
   @PutMapping("/update-details/{donorId}")
   public ResponseEntity<FullDonorResponseDto> updateDonorDetails(@PathVariable Integer donorId , @RequestBody UpadteDonorRequestVO request) {
	   return  ResponseEntity.status(HttpStatus.OK).body(donorService.updateDonorDetails(donorId , request));
	   
   }
   @GetMapping("/{donorId}")
   public ResponseEntity<FullDonorResponseDto> getDonorDeatils(@PathVariable Integer donorId) {
	   return  ResponseEntity.status(HttpStatus.OK).body(donorService.getDonorDeatils(donorId));

   }

}
