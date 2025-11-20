package com.donor.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.common.dto.SearchDonorDTO;
import com.donor.entities.Donor;
import com.donor.mapper.SearchDonorMapper;
import com.donor.repositary.DonorRepositary;
import com.donor.service.DonorSearchService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class DonorSearchServiceImpl implements DonorSearchService {

    private final DonorRepositary donorRepository;

    @Override
    public Page<SearchDonorDTO> search(String bloodGroup, String location, Pageable pageable) {

        Page<Donor> donors;

        if (location != null && !location.isEmpty()) {
            donors = donorRepository.findByBloodGroupAndLocation(bloodGroup, location, pageable);
        } else {
            donors = donorRepository.findByBloodGroup(bloodGroup, pageable);
        }

        return donors.map(SearchDonorMapper::toDTO);
    }
}
