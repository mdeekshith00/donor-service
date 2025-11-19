package com.donor.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.common.dto.DonationResponseDto;
import com.common.vo.DonationRequestVO;
import com.common.vo.MinDonorVo;
import com.donor.dto.FullDonorResponseDto;
import com.donor.entities.Donor;
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
    private final String serviceToken = "my-shared-secret";

	
   @PostMapping("/add")
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
   @PostMapping("/internal/api/donors")
   public ResponseEntity<?> createIfDonor(@RequestBody MinDonorVo cmd, @RequestParam String token) {

	       log.info(">>> Received token in DonorController: [{}]", token);

	       if (!serviceToken.equals(token)) {
	           log.warn(">>> Unauthorized request, invalid service token!");
	           return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid service token");
	       }

	       if (cmd.getRole() == null || !cmd.getRole().equalsIgnoreCase("DONOR")) {
	           return ResponseEntity.badRequest().body("user is not donor role");
	       }

       Donor donor = (Donor) donorService.createIfNotExists(cmd.getUserId());
       return ResponseEntity.status(HttpStatus.CREATED)
               .body(Map.of("donorId", donor.getDonorId(), "userId", donor.getUserId()));
   }
   
   @PostMapping("/{donorId}/donate")
   public ResponseEntity<DonationResponseDto> validateDonateBlood(@PathVariable Integer donorId, @RequestBody DonationRequestVO donationRequest) {
	   DonationResponseDto responseDto =  donorService.validateDonateBlood(donorId, donationRequest);
	   return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseDto);
	   
   }


}
