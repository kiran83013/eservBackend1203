package com.travel.travtronics.converter;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.travel.travtronics.eserv.model.CustomerAddress;
import com.travel.travtronics.eserv.model.CustomerPid;
import com.travel.travtronics.request.CustomerAddressRequest;
import com.travel.travtronics.response.CustomerAddressResponse;
import com.travel.travtronics.response.CustomerPidResponse;

public class CustomerAddressConverter {

	public static CustomerAddress convertSaveJsonToModel(CustomerAddressRequest request) {
		CustomerAddress model = new CustomerAddress();
		model.setRefId(request.getRefId());
		model.setCustomerAddressType(request.getCustomerAddressType());
		model.setSupplierAddressType(request.getSupplierAddressType());
		model.setRegAddress(request.getRegAddress());
		model.setsEmail(request.getsEmail());
		model.setBillingAndStreet(request.getBillingAndStreet());
		model.setArea(request.getArea());
		model.setOrganizationId(request.getOrganizationId());
		model.setEmail(request.getEmail());
		model.setCity(request.getCity());
		model.setState(request.getState());
		model.setCountry(request.getCountry());
		model.setZipCode(request.getZipCode());
		model.setLandMark(request.getLandMark());
		model.setGeoLocation(request.getGeoLocation());
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

	public static CustomerAddressResponse convertModelToJson(CustomerAddress model) {
		CustomerAddressResponse response = new CustomerAddressResponse();
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
		response.setsEmail(model.getsEmail());
		return response;
	}

	public static List<CustomerAddressResponse> convertListModelToListJson(List<CustomerAddress> models) {
		return models.stream().map(CustomerAddressConverter::convertModelToJson).collect(Collectors.toList());
	}

	public static CustomerAddress convertUpdateJsonToModel(CustomerAddressRequest request, CustomerAddress model) {
		model.setRefId(request.getRefId());
		model.setCustomerAddressType(request.getCustomerAddressType());
		model.setSupplierAddressType(request.getSupplierAddressType());
		model.setRegAddress(request.getRegAddress());
		model.setBillingAndStreet(request.getBillingAndStreet());
		model.setArea(request.getArea());
		model.setOrganizationId(request.getOrganizationId());
		model.setPhone1(request.getPhone1());
		model.setPhone2(request.getPhone2());
		model.setCity(request.getCity());
		model.setState(request.getState());
		model.setEmail(request.getEmail());
		model.setCountry(request.getCountry());
		model.setZipCode(request.getZipCode());
		model.setLandMark(request.getLandMark());
		model.setGeoLocation(request.getGeoLocation());
		model.setWfStatus(request.getWfStatus());
		model.setStatus(request.getStatus());
		model.setPrimaryContactId(request.getPrimaryContactId());
		model.setCreatedBy(request.getCreatedBy());
		model.setUpdatedBy(request.getUpdatedBy());
		model.setUpdatedDate(new Date());
		model.setsEmail(request.getsEmail());
		model.setPrimaryAddressCustomer(request.getPrimaryAddressCustomer());
		model.setPrimaryAddressSupplier(request.getPrimaryAddressSupplier());
		return model;
	}

	public static CustomerPidResponse convertCustomerPidModelToResponse(CustomerPid model) {
		CustomerPidResponse response = new CustomerPidResponse();
		response.setCreatedBy(model.getCreatedBy());
		response.setCreatedDate(model.getCreatedDate());
		response.setFromDate(model.getFromDate());
		response.setId(model.getId());
		response.setNotes(model.getNotes());
		response.setOrganizationId(model.getOrganizationId());
		response.setRefId(model.getRefId());
		response.setStatus(model.getStatus());
		response.setToDate(model.getToDate());
		response.setType(model.getType());
		response.setUpdatedBy(model.getUpdatedBy());
		response.setUpdatedDate(model.getUpdatedDate());
		response.setUrl(model.getUrl());
		response.setValue(model.getValue());
		return response;
	}

}
