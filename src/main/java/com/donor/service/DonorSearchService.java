package com.donor.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.common.dto.SearchDonorDTO;

public interface DonorSearchService {

    Page<SearchDonorDTO> search(String bloodGroup, String location, Pageable pageable);
}
