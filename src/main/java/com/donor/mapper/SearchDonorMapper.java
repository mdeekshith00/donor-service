package com.donor.mapper;

import com.common.dto.SearchDonorDTO;
import com.donor.entities.Donor;

public class SearchDonorMapper {

    public static SearchDonorDTO toDTO(Donor donor) {

        if (donor == null) {
            return null;
        }

        SearchDonorDTO dto = new SearchDonorDTO();

        dto.setDonorId(donor.getDonorId());
        dto.setBloodGroup(donor.getBloodGroup());

        dto.setDonationEligibilityStatus(
                donor.getDonationEligibilityStatus() != null
                        ? donor.getDonationEligibilityStatus().name()
                        : null
        );

        dto.setIsAvailableToDonate(donor.getIsAvailableToDonate());
        dto.setLastDonationDate(donor.getLastDonationDate());
        dto.setNextEligibleDate(donor.getNextEligibleDate());
        dto.setTotalDonations(donor.getTotalDonations());
        dto.setTotalUnitsDonated(donor.getTotalUnitsDonated());
        dto.setIsEligibleToDonate(donor.getIsEligibleToDonate());
        dto.setIneligibilityReason(donor.getIneligibilityReason());
        dto.setIsActive(donor.getIsActive());
        dto.setIsVerified(donor.getIsVerified());
        dto.setUserId(donor.getUserId());

        return dto;
    }
}
