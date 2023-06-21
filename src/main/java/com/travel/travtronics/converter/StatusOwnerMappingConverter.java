package com.travel.travtronics.converter;

import com.travel.travtronics.eserv.model.StatusOwnerMapping;
import com.travel.travtronics.eserv.model.Transition;
import com.travel.travtronics.request.StatusOwnerMappingRequest;
import com.travel.travtronics.request.TransitionRequest;

public class StatusOwnerMappingConverter {

	public static StatusOwnerMappingRequest convertConfigModelToResponse(StatusOwnerMapping model) {
		StatusOwnerMappingRequest response = new StatusOwnerMappingRequest();
		response.setId(model.getId());
		response.setCreatedBy(model.getCreatedBy());
		response.setCreatedDate(model.getCreatedDate());
		response.setDefaultStatus(model.getDefaultStatus());
		response.setEndDate(model.getEndDate());
		response.setModule(model.getModule());
		response.setOrganization(model.getOrganization());
		response.setStartDate(model.getStartDate());
		response.setStatus(model.getStatus());
		response.setTeam(model.getTeam());
		response.setUpdatedBy(model.getUpdatedBy());
		response.setUpdatedDate(model.getUpdatedDate());
		return response;
	}

	public static Transition convertDtoToModel(TransitionRequest request) {
		Transition model = new Transition();
		model.setOrganization(request.getOrganization()!= null ? request.getOrganization() : 0);
		model.setModuleName(request.getModuleName().toLowerCase());
		model.setFromStatus(request.getFromStatus()!= null ? request.getFromStatus() :0);
		model.setToStatus(request.getToStatus()!=null ? request.getToStatus() :0);
		
		model.setTransitionId(request.getTransitionId());
		model.setStatus(request.getStatus());
		model.setCreatedBy(request.getCreatedBy());
		model.setCreatedDate(request.getCreatedDate());
		model.setUpdatedBy(request.getUpdatedBy());
		model.setUpdatedDate(request.getUpdatedDate());
		model.setDeafultStatus(request.getDeafultStatus());
		return model;
	}

}
