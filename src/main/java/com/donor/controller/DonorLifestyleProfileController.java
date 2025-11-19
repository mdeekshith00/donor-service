package com.donor.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.donor.dto.DonorLifeStyleProfileDto;
import com.donor.service.DonorLifestyleProfileService;
import com.donor.vo.DonorLifestyleProfileVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/donorlifestyle")
@RestController
public class DonorLifestyleProfileController {
	
	private final DonorLifestyleProfileService donorLifestyleProfileService;
	
	@GetMapping("/{donorId}")
	public ResponseEntity<DonorLifeStyleProfileDto> getDonorLifeStyleProfileById(@PathVariable Integer donorLifestyleProfileId) {
		DonorLifeStyleProfileDto lifeStyleDto =	donorLifestyleProfileService.getDonorLifeStyleProfileById(donorLifestyleProfileId);
		return ResponseEntity.status(HttpStatus.OK).body(lifeStyleDto);  
		
	}
	@PostMapping("/add-update/{donorId}")
    public  ResponseEntity<DonorLifeStyleProfileDto>  addorUpdateDonorLifeStyle(@PathVariable Integer donorId , @RequestBody DonorLifestyleProfileVO  donorLifestyleProfileVO) {
		DonorLifeStyleProfileDto lifeStyleDto =	donorLifestyleProfileService.addorUpdateDonorLifeStyle(donorId, donorLifestyleProfileVO);
	    return ResponseEntity.status(HttpStatus.OK).body(lifeStyleDto);  
    	
    }

}
