package com.donor.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.common.dto.UserDto;
import com.donor.entities.Donor;
import com.donor.repositary.DonorRepositary;
import com.donor.service.DonorServcie;
import com.donor.service.UserServiceClient;
import com.donor.vo.DonorRequestVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DonorServiceImpl implements DonorServcie{
	
	private final DonorRepositary donorRepositary;
	private final UserServiceClient userServiceClient;
	private final DonorUserCacheService donorUserCacheService;
	
    public UserDto getUserDetails(Integer userId) {
        // 1. Check cache
    	UserDto user = donorUserCacheService.getUserById(userId)
    	        .orElseGet(() -> {
    	            // If not in cache, call user-service
    	            UserDto fetchedUser = userServiceClient.getUserById(userId)
    	                    .orElseThrow(() -> new RuntimeException("User not found"));

    	            donorUserCacheService.putUser(fetchedUser); // save in cache
    	            return fetchedUser;
    	        });
    	return user;
    }
    


	@Override
	public Donor createDonor(DonorRequestVO request) {
		// TODO Auto-generated method stub
		  Optional<Donor> existingDonor = donorRepositary.findById(request.getUserId());
	        if (existingDonor.isPresent()) {
	            return existingDonor.get();
	        }

	        // 2. If not in donor DB, check cache
	        UserDto user = donorUserCacheService.getUserById(request.getUserId())
	                .orElseGet(() -> {
	                    // 3. If not in cache, call user-service
	                    UserDto fetchedUser = userServiceClient.getUserById(request.getUserId())
	                            .orElseThrow(() -> new RuntimeException("User not found in user-service"));

	                    donorUserCacheService.putUser(fetchedUser);
	                    return fetchedUser;
	                });

	        // 4. Create new donor in donor DB
	        Donor donor = new Donor();
	        donor.setUserId(user.getUserId());
//	        donor.setName(user.getName());
//	        donor.setEmail(user.getEmail());
//	        donor.setPhone(user.getPhone());
//	        donor.setBloodGroup(bloodGroup);
//	        donor.setLastDonationDate(null);

	        return donorRepositary.save(donor);
	}

}
