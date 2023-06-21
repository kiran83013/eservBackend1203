package com.travel.travtronics.request;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.travel.travtronics.enums.Status;

import lombok.Data;

@Data
public class EmpRelationShipsRequest {
	
	private Long relationShipId;
	
	@NotNull
	private Long firstPartyId;
	
	@NotNull
	private Long secondPartyId;
	
	private Long organizationId;
	
	private String secondPartyTypeId;

	private Long fromRelationId;
	
	private Long toRelationId;
	
	private Date fromDate;

	
	private Date toDate;

	
	private Status status;

	
	private Long createdBy;

	private Long updatedBy;
	
	private Date createdDate;
	
	private Date updatedDate;
}
