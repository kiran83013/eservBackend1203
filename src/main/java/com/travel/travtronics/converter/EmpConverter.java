package com.travel.travtronics.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import com.travel.travtronics.eserv.model.EmpPid;
import com.travel.travtronics.eserv.model.Employee;
import com.travel.travtronics.request.EmpRequest;
import com.travel.travtronics.response.EmpPidResponse;
import com.travel.travtronics.response.EmpResponse;

public class EmpConverter {

	public static Employee convertRequestToModel(EmpRequest request) {
		Employee emp = new Employee();
		emp.setPrefix(request.getPrefix());
		emp.setFirstName(request.getFirstName());
		emp.setLastName(request.getLastName());
		emp.setMiddleName(request.getMiddleName());
		emp.setOrganizationId(request.getOrganizationId());
		emp.setDepartmentId(request.getDepartmentId());
		emp.setBusinessUnitId(request.getBusinessUnitId());
		emp.setDesignationId(request.getDesignationId());
		emp.setDesignationName(request.getDesignationName());
		emp.setPrimaryEmail(request.getPrimaryEmail());
		emp.setPrimaryPhoneNumber(request.getPrimaryPhoneNumber());
		emp.setSecondaryEmail(request.getSecondaryEmail());
		emp.setSecondaryPhoneNumber(request.getSecondaryPhoneNumber());
		emp.setTelephoneNumber(request.getTelephoneNumber());
		emp.setNationality(request.getNationality());
		emp.setDob(request.getDob());
		emp.setPassport(request.getPassport());
		emp.setIssuedCountry(request.getIssuedCountry());
		emp.setRemarksAndNotes(request.getRemarksAndNotes());
		emp.setStartDate(request.getStartDate());
		emp.setEndDate(request.getEndDate());
		emp.setDpImageUrl(request.getDpImageUrl());
		emp.setStatus(request.getStatus());
		emp.setCreatedBy(request.getCreatedBy());
		emp.setCreatedDate(request.getCreatedDate());
		emp.setPrimaryCcp(request.getPrimaryCcp());
		emp.setSecondaryCcp(request.getSecondaryCcp());
		emp.setCustomersId(request.getCustomersId());
		return emp;
	}

	public static Employee updateRequestToModel(EmpRequest request, Long id) {

		Employee emp = new Employee();
		if (id != null && id != 0)
			emp.setId(id);
		emp.setPrefix(request.getPrefix());
		emp.setFirstName(request.getFirstName());
		emp.setLastName(request.getLastName());
		emp.setMiddleName(request.getMiddleName());
		emp.setOrganizationId(request.getOrganizationId());
		emp.setDepartmentId(request.getDepartmentId());
		emp.setBusinessUnitId(request.getBusinessUnitId());
		emp.setDesignationId(request.getDesignationId());
		emp.setDesignationName(request.getDesignationName());
		emp.setPrimaryEmail(request.getPrimaryEmail());
		emp.setPrimaryPhoneNumber(request.getPrimaryPhoneNumber());
		emp.setSecondaryEmail(request.getSecondaryEmail());
		emp.setSecondaryPhoneNumber(request.getSecondaryPhoneNumber());
		emp.setTelephoneNumber(request.getTelephoneNumber());
		emp.setNationality(request.getNationality());
		emp.setDob(request.getDob());
		emp.setPassport(request.getPassport());
		emp.setIssuedCountry(request.getIssuedCountry());
		emp.setRemarksAndNotes(request.getRemarksAndNotes());
		emp.setStartDate(request.getStartDate());
		emp.setEndDate(request.getEndDate());
		emp.setDpImageUrl(request.getDpImageUrl());
		emp.setStatus(request.getStatus());
		emp.setUpdatedBy(request.getUpdatedBy());
		emp.setUpdatedDate(request.getUpdatedDate());
		emp.setPrimaryCcp(request.getPrimaryCcp());
		emp.setSecondaryCcp(request.getSecondaryCcp());
		emp.setCustomersId(request.getCustomersId());

		return emp;

	}

	public static EmpResponse convertModelToResponse(Employee request) {
		EmpResponse emp = new EmpResponse();
		emp.setId(request.getId());
		emp.setPrefix(request.getPrefix());
		emp.setFirstName(request.getFirstName());
		emp.setLastName(request.getLastName());
		emp.setMiddleName(request.getMiddleName());
		emp.setOrganizationId(request.getOrganizationId());
		emp.setDepartmentId(request.getDepartmentId());
		emp.setBusinessUnitId(request.getBusinessUnitId());
		emp.setDesignationId(request.getDesignationId());
		emp.setDesignationName(request.getDesignationName());
		emp.setPrimaryEmail(request.getPrimaryEmail());
		emp.setPrimaryPhoneNumber(request.getPrimaryPhoneNumber());
		emp.setSecondaryEmail(request.getSecondaryEmail());
		emp.setSecondaryPhoneNumber(request.getSecondaryPhoneNumber());
		emp.setTelephoneNumber(request.getTelephoneNumber());
		emp.setNationality(request.getNationality());
		emp.setDob(request.getDob());
		emp.setPassport(request.getPassport());
		emp.setIssuedCountry(request.getIssuedCountry());
		emp.setRemarksAndNotes(request.getRemarksAndNotes());
		emp.setStartDate(request.getStartDate());
		emp.setEndDate(request.getEndDate());
		emp.setDpImageUrl(request.getDpImageUrl());
		emp.setStatus(request.getStatus());
		emp.setCreatedBy(request.getCreatedBy());
		emp.setCreatedDate(request.getCreatedDate());
		emp.setUpdatedBy(request.getUpdatedBy());
		emp.setUpdatedDate(request.getUpdatedDate());
		emp.setPrimaryCcp(request.getPrimaryCcp());
		emp.setSecondaryCcp(request.getSecondaryCcp());
		emp.setCustomersId(request.getCustomersId());
		return emp;
	}

	public static List<EmpResponse> convertModelsToResponse(List<Employee> requests) {
		return requests.stream().map(model -> convertModelToResponse(model)).collect(Collectors.toList());
	}

	public static List<EmpResponse> convertModelsToResponses(Page<Employee> serviceMenuTypeData) {
		return serviceMenuTypeData.stream().map(model -> convertModelToResponse(model)).collect(Collectors.toList());
	}

	public static EmpPidResponse convertEmpPidModelToResponse(EmpPid model) {
		EmpPidResponse response =new EmpPidResponse();
		response.setCreatedBy(model.getCreatedBy());
		response.setCreatedDate(model.getCreatedDate());
		response.setFromDate(model.getFromDate());
		response.setToDate(model.getToDate());
		response.setId(model.getId());
		response.setOrganizationId(model.getOrganizationId());
		response.setRefId(model.getRefId());
		response.setStatus(model.getStatus());
		response.setType(model.getType());
		response.setUpdatedBy(model.getUpdatedBy());
		response.setUpdatedDate(model.getUpdatedDate());
		response.setValue(model.getValue());
		response.setUrl(model.getUrl());
		response.setNotes(model.getNotes());
		return response;
	}

}
