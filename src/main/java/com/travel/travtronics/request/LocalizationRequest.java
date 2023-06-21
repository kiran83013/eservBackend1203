package com.travel.travtronics.request;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class LocalizationRequest {

	private Long id;
	
	private Long organizationId;
	
	private String roleType;
	
	private String language;
	
	private List<String> role;
	
	private com.travel.travtronics.enums.Status status;
	
	private String keyTrnsl;
	
	private String valueTrnsl;
	
	private String createdBy;
	
	private Date createdDate;
	
	private String updatedBy;
	
	private Date updatedDate;
}
