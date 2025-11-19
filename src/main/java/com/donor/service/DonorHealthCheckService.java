package com.donor.service;

import com.donor.dto.DonorHealthCheckDto;
import com.donor.vo.DonorHealthCheckVO;

public interface DonorHealthCheckService {
	
	public DonorHealthCheckDto addorUpdateHealthCheck(Integer donorId ,DonorHealthCheckVO donorHealthCheckVO );
	public DonorHealthCheckDto getDonorHealthCheck(Integer DonorHealthCheckId);
	public String deleteHealthCheck(Integer DonorHealthCheckId);
	

}
