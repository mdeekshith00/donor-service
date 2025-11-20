//package com.donor.service.impl;
//
//package com.donor.service.impl;
//
//import java.time.LocalDate;
//
//import org.springframework.stereotype.Service;
//
//import com.common.enums.CERTIFICATETYPE;
//import com.common.enums.StatusType;
//import com.donor.entities.CertificateReward;
//import com.donor.entities.Donor;
//import com.donor.repositary.CertificateRewardRepository;
//
//import lombok.RequiredArgsConstructor;
//
//@Service
//@RequiredArgsConstructor
//public class CertificateRewardService {
//
//    private final CertificateRewardRepository certificateRewardRepository;
//
//    public CertificateReward issueCertificate(Donor donor, Donor donation) {
//        String pdfPath = CertificateGenerator.generateCertificate(donor, donation);
//
//        CertificateReward reward = new CertificateReward();
//        reward.setType(CERTIFICATETYPE.CERTIFICATE);
//        reward.setTitle("Certificate of Appreciation");
//        reward.setDescription("Awarded for your life-saving blood donation.");
//        reward.setCertificateUrl(pdfPath);
//        reward.setDonationDate(donation.getDonationDate());
//        reward.setIssuedDate(LocalDate.now());
//        reward.setIssuedBy("SYSTEM_AUTO");
//        reward.setStatus(StatusType.ACTIVE);
//        reward.setDonor(donor);
//
//        return certificateRewardRepository.save(reward);
//    }
//}
//
