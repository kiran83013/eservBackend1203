package com.travel.travtronics.response;

import java.util.Date;
import java.util.List;

import com.travel.travtronics.util.EnumStatus;

import lombok.Data;

@Data
public class LocalizationResponse {

	private Long id;

	private Long organizationId;

	private String roleType;

	private String language;

	private List<String> role;

//	private com.travel.travtronics.enums.Status status;
	private EnumStatus status;

	private String keyTrnsl;

	private String valueTrnsl;

	private String createdBy;

	private Date createdDate;

	private String updatedBy;

	private Date updatedDate;
	
	private String organizationName;
	
	private String roleTypeName;
	
	private List<String> roleName;
	

}
