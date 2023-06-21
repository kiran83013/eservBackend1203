package com.travel.travtronics.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import com.travel.travtronics.eserv.model.BpfConfigHeader;
import com.travel.travtronics.response.BpfConfigHeaderResponse;

public class BpfConfigConverter {

	public static BpfConfigHeaderResponse convertConfigModelToResponse(BpfConfigHeader model) {
		BpfConfigHeaderResponse response = new BpfConfigHeaderResponse();
		response.setID(model.getID());
		response.setActionType(model.getActionType());
		response.setConfigName(model.getConfigName());
		response.setModule(model.getModule());
		response.setOrganizationId(model.getOrganizationId());
		response.setStatus(model.getStatus());
		response.setSubModule(model.getSubModule());
		response.setTransitionID(model.getTransitionID());
		response.setStatusFrom(model.getStatusFrom());
		response.setStatusTo(model.getStatusTo());
		response.setCreatedDate(model.getCreatedDate());
		response.setCreatedBy(model.getCreatedBy());
		response.setUpdatedBy(model.getUpdatedBy());
		response.setUpdatedDate(model.getUpdatedDate());
		return response;
	}

	public static List<BpfConfigHeaderResponse> convertConfigToResponse(Page<BpfConfigHeader> requests) {
		// TODO Auto-generated method stub convertConfigModelToResponse
		return requests.stream().map(model -> convertConfigModelToResponse(model)).collect(Collectors.toList());
	}

}
