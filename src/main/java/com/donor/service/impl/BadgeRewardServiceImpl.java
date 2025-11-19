////package com.donor.service.impl;
////
////import java.time.LocalDate;
////
////import org.springframework.stereotype.Service;
////
////import com.common.enums.CERTIFICATETYPE;
////import com.donor.dto.DonorRewardsDto;
////import com.donor.entities.BadgeReward;
////import com.donor.entities.Donor;
////import com.donor.repositary.BadgeRewardRepository;
////import com.donor.service.DonorRewardsService;
////
////import lombok.RequiredArgsConstructor;
////
////@Service
////@RequiredArgsConstructor
////public class BadgeRewardService implements DonorRewardsService {
////
////    private final BadgeRewardRepository badgeRewardRepository;
////
////    @Override
////    public BadgeReward issueReward(Donor donor) {
////        int donations = donor.getTotalDonations().size();
////        String level = getBadgeLevel(donations);
////
////        BadgeReward badge = new BadgeReward();
////        badge.setType(CERTIFICATETYPE.BADGE);
////        badge.setBadgeLevel(level);
////        badge.setThreshold(donations);
////        badge.setTitle(level + " Donor Badge");
////        badge.setImageUrl("/badges/" + level.toLowerCase() + ".png");
////        badge.setDonor(donor);
////        badge.setIssuedDate(LocalDate.now());
////        return badgeRewardRepository.save(badge);
////    }
////
////    private String getBadgeLevel(int count) {
////        if (count >= 10) return "GOLD";
////        if (count >= 5) return "SILVER";
////        if (count >= 3) return "BRONZE";
////        return "FIRST-TIME";
////    }
////
////	@Override
////	public DonorRewardsDto getRewards(Integer donorRewardsId) {
////		// TODO Auto-generated method stub
////		return null;
////	}
////
////	@Override
////	public DonorRewardsDto addRewardToDonor(Integer donorId) {
////		// TODO Auto-generated method stub
////		return null;
////	}
////}
////@Service
//@RequiredArgsConstructor
//public class PointsRewardService implements DonorRewardService {
//
//    private final PointsRewardRepository pointsRewardRepository;
//
//    @Override
//    public PointsReward issueReward(Donor donor) {
//        PointsReward points = new PointsReward();
//        points.setType(CERTIFICATETYPE.POINTS);
//        points.setPointsEarned(50); // e.g., 50 points per donation
//        points.setReason("Blood donation");
//        points.setIssuedDate(LocalDate.now());
//        points.setDonor(donor);
//        return pointsRewardRepository.save(points);
//    }
//}
//
