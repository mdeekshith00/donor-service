package com.donor.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.common.constants.ErrorConstants;
import com.common.exception.BloodBankBusinessException;
import com.donor.dto.DonorHealthCheckDto;
import com.donor.entities.Donor;
import com.donor.entities.DonorHealthCheck;
import com.donor.mapper.MapperHelper;
import com.donor.repositary.DonorHealthCheckRepositary;
import com.donor.repositary.DonorRepositary;
import com.donor.service.DonorHealthCheckService;
import com.donor.vo.DonorHealthCheckVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class DonorHealthCheckServiceImpl implements DonorHealthCheckService {
	
	private final DonorHealthCheckRepositary donorHealthCheckRepositary;
	private final MapperHelper mapperHelper;
	private final DonorRepositary DonorRepositary;

	@Override
	public DonorHealthCheckDto addorUpdateHealthCheck(Integer donorId, DonorHealthCheckVO donorHealthCheckVO) {
		// TODO Auto-generated method stub
		 Donor donor =  DonorRepositary.findByDonorIdAndIsActive(donorId, true)
				 .orElseThrow(() -> new BloodBankBusinessException(ErrorConstants.DONOR_DETAILS_NOT_FOUND , HttpStatus.BAD_REQUEST , ErrorConstants.DATA_NOT_FOUND));
		 
		 DonorHealthCheck healthCheckup = null;
		 if(donorHealthCheckVO.getDonorHealthCheckId() != null) {
			 healthCheckup =  donorHealthCheckRepositary.findByDonorHealthCheckId(donorHealthCheckVO.getDonorHealthCheckId())
					 .orElseThrow(() -> new BloodBankBusinessException(ErrorConstants.DONOR_HEALTH_CHECKUP_DETAILS_NOT_FOUND , HttpStatus.BAD_REQUEST , ErrorConstants.DATA_NOT_FOUND));
		 healthCheckup = mapperHelper.voToDonorHealthCheckUpEntity(healthCheckup ,donorHealthCheckVO);
			 donorHealthCheckRepositary.save(healthCheckup);
			 
		 } 
		 healthCheckup = new DonorHealthCheck();
		 healthCheckup = mapperHelper.voToDonorHealthCheckUpEntity(healthCheckup ,donorHealthCheckVO);
		 healthCheckup.setDonor(donor);
		 donorHealthCheckRepositary.save(healthCheckup);
		 
			 return mapperHelper.DonorHealthEntityToDto(healthCheckup);
	}

	@Override
	public DonorHealthCheckDto getDonorHealthCheck(Integer DonorHealthCheckId) {
		// TODO Auto-generated method stub
		DonorHealthCheck healthCheckup =  donorHealthCheckRepositary.findByDonorHealthCheckId(DonorHealthCheckId)
				 .orElseThrow(() -> new BloodBankBusinessException(ErrorConstants.DONOR_HEALTH_CHECKUP_DETAILS_NOT_FOUND , HttpStatus.BAD_REQUEST , ErrorConstants.DATA_NOT_FOUND));
		 return mapperHelper.DonorHealthEntityToDto(healthCheckup);
		
	}

	@Override
	public String deleteHealthCheck(Integer DonorHealthCheckId) {
		// TODO Auto-generated method stub
		DonorHealthCheck healthCheckup =  donorHealthCheckRepositary.findByDonorHealthCheckId(DonorHealthCheckId)
				 .orElseThrow(() -> new BloodBankBusinessException(ErrorConstants.DONOR_HEALTH_CHECKUP_DETAILS_NOT_FOUND , HttpStatus.BAD_REQUEST , ErrorConstants.DATA_NOT_FOUND));
		donorHealthCheckRepositary.delete(healthCheckup);
		
		return "Deleted DonorHealthCheck Details From DB:";
	}
	

	

}
