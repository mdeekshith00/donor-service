package com.donor.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DonorResponseVO {
	
	   private Integer donorId;
	    private String name;
	    private String email;
	    private String bloodGroup;

}
