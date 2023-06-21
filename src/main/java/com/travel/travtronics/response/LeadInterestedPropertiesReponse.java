package com.travel.travtronics.response;

import java.util.Date;
import java.util.List;

import com.travel.travtronics.enums.Status;

import lombok.Data;

@Data
public class LeadInterestedPropertiesReponse {

	private Long id;

	private Long unitId;

	private Long businessId;

	private Long organizationId;

	private List<String> images;

	private Status status;

	private Long createdBy;

	private Date createdDate;

	private Long updatedBy;

	private Date updatedDate;

	private String createdChannel;

	private String updatedChannel;

	private String businessName;

	private String organizationName;
	
	private String unitName;
	
	private String buildingName;
	
	private String floorName;
}
