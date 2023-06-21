package com.travel.travtronics.converter;

import java.util.List;
import java.util.stream.Collectors;



import com.travel.travtronics.eserv.model.EmpVerification;
import com.travel.travtronics.response.EmpVerificationResponse;

public class EmpVerficationConverter {

	public static List<EmpVerificationResponse> convertVerficationJsonHeaderToModels(List<EmpVerification> requests) {
        return requests.stream().map(model -> convertTaxModelToResponse(model)).collect(Collectors.toList());

	}

	private static EmpVerificationResponse convertTaxModelToResponse(EmpVerification model) {
		EmpVerificationResponse emp = new EmpVerificationResponse();
		emp.setId(model.getId());
		emp.setRefId(model.getRefId());
		emp.setType(model.getType());
		emp.setOrganizationId(model.getOrganizationId());
		emp.setValue(model.getValue());
		emp.setStatus(model.getStatus());
		emp.setFromDate(model.getFromDate());
		emp.setToDate(model.getToDate());
		emp.setCreatedBy(model.getCreatedBy());
		emp.setUpdatedBy(model.getUpdatedBy());
		emp.setCreatedDate(model.getCreatedDate());
		emp.setUpdatedDate(model.getUpdatedDate());
		return emp;
	}

}
