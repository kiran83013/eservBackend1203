package com.travel.travtronics.converter;

import com.travel.travtronics.eserv.model.CustomerActivity;
import com.travel.travtronics.eserv.model.CustomerRelations;
import com.travel.travtronics.request.CustomerRelationsRequest;
import com.travel.travtronics.response.CustomerActivityResponse;
import com.travel.travtronics.response.CustomerRelationsResponse;


public class CustomerRelationsConverter {

	public static CustomerRelations convertRequestToModel(CustomerRelationsRequest relationRequest) {
		CustomerRelations customerRelations = new CustomerRelations();
		customerRelations.setOrganizationId(relationRequest.getOrganizationId());
		customerRelations.setCreatedBy(relationRequest.getCreatedBy());
		customerRelations.setCreatedDate(relationRequest.getCreatedDate());
		customerRelations.setFirstPartyId(relationRequest.getFirstPartyId());
		customerRelations.setSecondPartyTypeId(relationRequest.getSecondPartyTypeId());
		customerRelations.setSecondPartyId(relationRequest.getSecondPartyId());
		customerRelations.setFromDate(relationRequest.getFromDate());
		customerRelations.setFromRelationId(relationRequest.getFromRelationId());
		customerRelations.setToRelationId(relationRequest.getToRelationId());
		customerRelations.setToDate(relationRequest.getToDate());
		customerRelations.setStatus(relationRequest.getStatus());
		return customerRelations;
	}

	public static CustomerRelations updateRequestToModel(CustomerRelationsRequest relationRequest) {
		CustomerRelations customerRelations = new CustomerRelations();
		if (relationRequest.getRelationShipId() != 0)
			customerRelations.setRelationShipId(relationRequest.getRelationShipId());
		customerRelations.setOrganizationId(relationRequest.getOrganizationId());
		customerRelations.setCreatedBy(relationRequest.getCreatedBy());
		customerRelations.setCreatedDate(relationRequest.getCreatedDate());
		customerRelations.setFirstPartyId(relationRequest.getFirstPartyId());
		customerRelations.setSecondPartyTypeId(relationRequest.getSecondPartyTypeId());
		customerRelations.setSecondPartyId(relationRequest.getSecondPartyId());
		customerRelations.setFromDate(relationRequest.getFromDate());
		customerRelations.setFromRelationId(relationRequest.getFromRelationId());
		customerRelations.setToRelationId(relationRequest.getToRelationId());
		customerRelations.setToDate(relationRequest.getToDate());
		customerRelations.setStatus(relationRequest.getStatus());
		customerRelations.setUpdatedBy(relationRequest.getUpdatedBy());
		customerRelations.setUpdatedDate(relationRequest.getUpdatedDate());
		return customerRelations;
	}

	
	public static CustomerRelationsResponse convertResponseToModel(CustomerRelations model) {
		
		CustomerRelationsResponse response =new CustomerRelationsResponse();
		response.setOrganizationId(model.getOrganizationId());
		response.setCreatedBy(model.getCreatedBy());
		response.setCreatedDate(model.getCreatedDate());
		response.setFirstPartyId(model.getFirstPartyId());
		response.setSecondPartyTypeId(model.getSecondPartyTypeId());
		response.setSecondPartyId(model.getSecondPartyId());
		response.setFromDate(model.getFromDate());
		response.setFromRelationId(model.getFromRelationId());
		response.setToRelationId(model.getToRelationId());
		response.setToDate(model.getToDate());
		response.setStatus(model.getStatus());
		response.setRelationShipId(model.getRelationShipId());
		response.setUpdatedBy(model.getUpdatedBy());
		response.setUpdatedDate(model.getUpdatedDate());
		return response;
	}

	public static CustomerActivityResponse convertCustomerActivitiyModelToRequest(CustomerActivity model) {
		CustomerActivityResponse response= new CustomerActivityResponse();
		response.setActivityId(model.getActivityId());
		response.setCreatedBy(model.getCreatedBy());
		response.setCreatedDate(model.getCreatedDate());
		response.setCustomerId(model.getCustomerId());
		response.setEndDate(model.getEndDate());
		response.setId(model.getId());
		response.setOrganizationId(model.getId());
		response.setSegementId(model.getSegementId());
		response.setStartDate(model.getStartDate());
		response.setStatus(model.getStatus());
		response.setSubActivityId(model.getSubActivityId());
		response.setUpdatedBy(model.getUpdatedBy());
		response.setUpdatedDate(model.getUpdatedDate());
		return response;
	}
}
