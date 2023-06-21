package com.travel.travtronics.converter;

import com.travel.travtronics.eserv.model.EmpRelationships;
import com.travel.travtronics.request.EmpRelationShipsRequest;

public class EmpRelationShipsConverter {

	public static EmpRelationships convertRequestToModel( EmpRelationShipsRequest relationRequest) {
		EmpRelationships empRelationShips=new EmpRelationships();
		empRelationShips.setOrganizationId(relationRequest.getOrganizationId());
		empRelationShips.setCreatedBy(relationRequest.getCreatedBy());
		empRelationShips.setCreatedDate(relationRequest.getCreatedDate());
		empRelationShips.setFirstPartyId(relationRequest.getFirstPartyId());
		empRelationShips.setSecondPartyId(relationRequest.getSecondPartyId());
		empRelationShips.setFromDate(relationRequest.getFromDate());
		empRelationShips.setFromRelationId(relationRequest.getFromRelationId());
		empRelationShips.setToRelationId(relationRequest.getToRelationId());
		empRelationShips.setToDate(relationRequest.getToDate());
		empRelationShips.setStatus(relationRequest.getStatus());
		empRelationShips.setSecondPartyTypeId(relationRequest.getSecondPartyTypeId());
		return empRelationShips;
	}

	public static EmpRelationships updateRequestToModel( EmpRelationShipsRequest relationRequest) {
		EmpRelationships empRelationShips=new EmpRelationships();
		if(relationRequest.getRelationShipId()!=0)
			empRelationShips.setRelationShipId(relationRequest.getRelationShipId());
		empRelationShips.setOrganizationId(relationRequest.getOrganizationId());
		empRelationShips.setCreatedBy(relationRequest.getCreatedBy());
		empRelationShips.setCreatedDate(relationRequest.getCreatedDate());
		empRelationShips.setFirstPartyId(relationRequest.getFirstPartyId());
		empRelationShips.setSecondPartyId(relationRequest.getSecondPartyId());
		empRelationShips.setFromDate(relationRequest.getFromDate());
		empRelationShips.setFromRelationId(relationRequest.getFromRelationId());
		empRelationShips.setToRelationId(relationRequest.getToRelationId());
		empRelationShips.setToDate(relationRequest.getToDate());
		empRelationShips.setStatus(relationRequest.getStatus());
		empRelationShips.setUpdatedBy(relationRequest.getUpdatedBy());
		empRelationShips.setUpdatedDate(relationRequest.getUpdatedDate());
		empRelationShips.setSecondPartyTypeId(relationRequest.getSecondPartyTypeId());
		return empRelationShips;
		
	}

}
