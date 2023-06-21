package com.travel.travtronics.request;

import java.util.Date;
import java.util.List;

import com.travel.travtronics.enums.Status;

import lombok.Data;

@Data
public class LeadInterestedPropertiesRequest {

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
	
}
