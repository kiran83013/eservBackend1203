package com.travel.travtronics.converter;

import com.travel.travtronics.eserv.model.LeadInterestedProperties;
import com.travel.travtronics.request.LeadInterestedPropertiesRequest;
import com.travel.travtronics.response.LeadInterestedPropertiesReponse;

public class LeadInterestedPropertiesConverter {

	public static LeadInterestedPropertiesReponse convertLIPModelToResponse(LeadInterestedProperties model) {
		LeadInterestedPropertiesReponse response =new LeadInterestedPropertiesReponse();
		response.setId(model.getId());
		response.setUnitId(model.getUnitId());
		response.setBusinessId(model.getBusinessId());
		response.setOrganizationId(model.getOrganizationId());
		response.setImages(model.getImages());
		response.setStatus(model.getStatus());
		response.setCreatedBy(model.getCreatedBy());
		response.setCreatedDate(model.getCreatedDate());
		response.setUpdatedBy(model.getUpdatedBy());
		response.setUpdatedDate(model.getUpdatedDate());
		response.setCreatedChannel(model.getCreatedChannel());
		response.setUpdatedChannel(model.getUpdatedChannel());
		return response;
	}
	
	public static LeadInterestedProperties convertModelToResponses(LeadInterestedPropertiesRequest request) {
		LeadInterestedProperties model = new LeadInterestedProperties();
		model.setId(request.getId());
		model.setUnitId(request.getUnitId());
		model.setBusinessId(request.getBusinessId());
		model.setOrganizationId(request.getOrganizationId());
		model.setImages(request.getImages());
		model.setStatus(request.getStatus());
		model.setCreatedBy(request.getCreatedBy());
		model.setCreatedDate(request.getCreatedDate());
		model.setUpdatedBy(request.getUpdatedBy());
		model.setUpdatedDate(request.getUpdatedDate());
		model.setCreatedChannel(request.getCreatedChannel());
		model.setUpdatedChannel(request.getUpdatedChannel());
		return model;
	}
}
