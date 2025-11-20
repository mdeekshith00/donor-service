package com.donor.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.common.dto.SearchDonorDTO;
import com.donor.service.DonorSearchService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping
public class DonorSearchController {

    private final DonorSearchService donorSearch;

    @GetMapping("/donors/search")
    public Page<SearchDonorDTO> searchDonors(@RequestParam String bloodGroup,@RequestParam(required = false) String location,Pageable pageable) {
        return donorSearch.search(bloodGroup, location, pageable);
    }
}
