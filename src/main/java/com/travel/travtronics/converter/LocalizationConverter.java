package com.travel.travtronics.converter;

import com.travel.travtronics.eserv.model.Localization;
import com.travel.travtronics.response.LocalizationResponse;

public class LocalizationConverter {

	public static LocalizationResponse convertLocalizationToResponse(Localization model) {
		LocalizationResponse response =new LocalizationResponse();
		response.setCreatedBy(model.getCreatedBy());
		response.setCreatedDate(model.getCreatedDate());
		response.setId(model.getId());
		response.setOrganizationId(model.getOrganizationId());
		response.setStatus(model.getStatus());
		response.setUpdatedBy(model.getUpdatedBy());
		response.setUpdatedDate(model.getUpdatedDate());
		response.setRoleType(model.getRoleType());
		response.setRole(model.getRole());
		response.setKeyTrnsl(model.getKeyTrnsl());
		response.setValueTrnsl(model.getValueTrnsl());
		return response;
	}
}
