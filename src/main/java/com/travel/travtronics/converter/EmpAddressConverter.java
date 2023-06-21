package com.travel.travtronics.converter;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.travel.travtronics.eserv.model.EmpAddress;
import com.travel.travtronics.eserv.model.EmpSocial;
import com.travel.travtronics.request.EmpAddressRequest;
import com.travel.travtronics.response.EmpAddressResponse;
import com.travel.travtronics.response.EmpSocialResponse;

public class EmpAddressConverter {

	public static EmpAddress covertSaveJsonToModel(EmpAddressRequest request) {
		EmpAddress model = new EmpAddress();
		model.setRefId(request.getRefId());
		model.setCustomerAddressType(request.getCustomerAddressType());
		model.setSupplierAddressType(request.getSupplierAddressType());
		model.setRegAddress(request.getRegAddress());
		model.setSEmail(request.getSEmail());
		model.setBillingAndStreet(request.getBillingAndStreet());
		model.setArea(request.getArea());
		model.setEmail(request.getEmail());
		model.setCity(request.getCity());
		model.setState(request.getState());
		model.setCountry(request.getCountry());
		model.setZipCode(request.getZipCode());
		model.setLandMark(request.getLandMark());
		model.setGeoLocation(request.getGeoLocation());
		model.setOrganizationId(request.getOrganizationId());
		model.setWfStatus(request.getWfStatus());
		model.setStatus(request.getStatus());
		model.setPrimaryContactId(request.getPrimaryContactId());
		model.setCreatedBy(request.getCreatedBy());
		model.setPhone1(request.getPhone1());
		model.setPhone2(request.getPhone2());
		model.setUpdatedBy(request.getUpdatedBy());
		model.setPrimaryAddressCustomer(request.getPrimaryAddressCustomer());
		model.setPrimaryAddressSupplier(request.getPrimaryAddressSupplier());
		return model;
	}

	public static EmpAddressResponse convertModelToJson(EmpAddress model) {
		EmpAddressResponse response = new EmpAddressResponse();
		response.setAddressId(model.getAddressId());
		response.setOrganizationId(model.getOrganizationId());
		response.setRefId(model.getRefId());
		response.setCustomerAddressType(model.getCustomerAddressType());
		response.setSupplierAddressType(model.getSupplierAddressType());
		response.setRegAddress(model.getRegAddress());
		response.setPrimarySupplier(model.getPrimarySupplier());
		response.setBillingAndStreet(model.getBillingAndStreet());
		response.setArea(model.getArea());
		response.setCity(model.getCity());
		response.setState(model.getState());
		response.setCountry(model.getCountry());
		response.setZipCode(model.getZipCode());
		response.setLandMark(model.getLandMark());
		response.setGeoLocation(model.getGeoLocation());
		response.setEmail(model.getEmail());
		response.setWfStatus(model.getWfStatus());
		response.setStatus(model.getStatus());
		response.setPrimaryContactId(model.getPrimaryContactId());
		response.setCreatedBy(model.getCreatedBy());
		response.setUpdatedBy(model.getUpdatedBy());
		response.setPrimaryAddressCustomer(model.getPrimaryAddressCustomer());
		response.setPrimaryAddressSupplier(model.getPrimaryAddressSupplier());
		response.setPhone1(model.getPhone1());
		response.setPhone2(model.getPhone2());
		response.setEmail(model.getEmail());
		response.setSEmail(model.getSEmail());
		return response;
	}

	public static List<EmpAddressResponse> convertListModelToListJson(List<EmpAddress> models) {
		return models.stream().map(EmpAddressConverter::convertModelToJson).collect(Collectors.toList());

	}

	public static EmpAddress convertUpdateJsonToModel(EmpAddressRequest request, EmpAddress model) {
		model.setRefId(request.getRefId());
		model.setCustomerAddressType(request.getCustomerAddressType());
		model.setSupplierAddressType(request.getSupplierAddressType());
		model.setRegAddress(request.getRegAddress());
		model.setBillingAndStreet(request.getBillingAndStreet());
		model.setArea(request.getArea());
		model.setPhone1(request.getPhone1());
		model.setPhone2(request.getPhone2());
		model.setCity(request.getCity());
		model.setPrimarySupplier(request.getPrimarySupplier());
		model.setState(request.getState());
		model.setEmail(request.getEmail());
		model.setCountry(request.getCountry());
		model.setZipCode(request.getZipCode());
		model.setLandMark(request.getLandMark());
		model.setOrganizationId(request.getOrganizationId());
		model.setGeoLocation(request.getGeoLocation());
		model.setWfStatus(request.getWfStatus());
		model.setStatus(request.getStatus());
		model.setPrimaryContactId(request.getPrimaryContactId());
		model.setCreatedBy(request.getCreatedBy());
		model.setUpdatedBy(request.getUpdatedBy());
		model.setUpdatedDate(new Date());
		model.setSEmail(request.getSEmail());
		model.setPrimaryAddressCustomer(request.getPrimaryAddressCustomer());
		model.setPrimaryAddressSupplier(request.getPrimaryAddressSupplier());
		return model;
	}

	public static EmpSocialResponse convertEmpSocialModelToResponse(EmpSocial model) {
		EmpSocialResponse response = new EmpSocialResponse();
		response.setCreatedBy(model.getCreatedBy());
		response.setCreatedDate(model.getCreatedDate());
		response.setFromDate(model.getFromDate());
		response.setToDate(model.getToDate());
		response.setId(model.getId());
		response.setOrganizationId(model.getOrganizationId());
		response.setRefId(model.getRefId());
		response.setStatus(model.getStatus() );
		response.setUpdatedBy(model.getUpdatedBy());
		response.setUpdatedDate(model.getUpdatedDate());
		response.setValue(model.getValue());
		response.setType(model.getType());
		return response;
	}
	
}
