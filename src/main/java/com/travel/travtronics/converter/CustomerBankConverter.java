package com.travel.travtronics.converter;

import java.util.List;
import java.util.stream.Collectors;

import com.travel.travtronics.eserv.model.CustomerBank;
import com.travel.travtronics.eserv.model.CustomerInfoModel;
import com.travel.travtronics.request.CustomerBankDto;
import com.travel.travtronics.response.CustomerBankResponse;
import com.travel.travtronics.response.CustomerInfoResponse;

public class CustomerBankConverter {

	public static CustomerBank convertJsonToModel(CustomerBankDto request) {
		CustomerBank model = new CustomerBank();
		model.setOrganizationId(request.getOrganizationId());
		model.setBankName(request.getBankName());
		model.setBranchId(request.getBranchId());
		model.setBranchAddress(request.getBranchAddress());
		model.setBankAccountType(request.getBankAccountType());
		model.setBankAccountName(request.getBankAccountName());
		model.setBankAccountNumber(request.getBankAccountNumber());
		model.setChequeDigits(request.getChequeDigits());
		model.setCurrency(request.getCurrency());
		model.setSwiftCode(request.getSwiftCode());
		model.setNotes(request.getNotes());
		model.setFileUrl(request.getFileUrl());
		model.setStatus(request.getStatus());
		model.setCreatedBy(request.getCreatedBy());
		model.setCreatedDate(request.getCreatedDate());
		model.setStatus(request.getStatus());
		model.setRefId(request.getRefId());
		model.setBankId(request.getBankId());
		model.setBranchName(request.getBranchName());
		return model;
	}

	public static CustomerBank updateJsonToModel(CustomerBankDto request, Long id) {
		CustomerBank model = new CustomerBank();
		if (id != null && id != 0)
			model.setId(id);
		model.setOrganizationId(request.getOrganizationId());
		model.setBankName(request.getBankName());
		model.setBranchId(request.getBranchId());
		model.setBranchAddress(request.getBranchAddress());
		model.setBankAccountType(request.getBankAccountType());
		model.setBankAccountName(request.getBankAccountName());
		model.setBankAccountNumber(request.getBankAccountNumber());
		model.setChequeDigits(request.getChequeDigits());
		model.setCurrency(request.getCurrency());
		model.setSwiftCode(request.getSwiftCode());
		model.setNotes(request.getNotes());
		model.setFileUrl(request.getFileUrl());
		model.setStatus(request.getStatus());
		model.setUpdatedBy(request.getUpdatedBy());
		model.setUpdatedDate(request.getUpdatedDate());
		model.setRefId(request.getRefId());
		model.setBankId(request.getBankId());
		model.setBranchName(request.getBranchName());
		return model;
	}

	public static List<CustomerBankResponse> convertModelsToResponses(List<CustomerBank> models) {
		return models.stream().map(CustomerBankConverter::convertModelToResponse).collect(Collectors.toList());

	}

	public static CustomerBankResponse convertModelToResponse(CustomerBank model) {
		CustomerBankResponse response = new CustomerBankResponse();
		response.setOrganizationId(model.getOrganizationId());
		response.setBankName(model.getBankName());
		response.setBranchId(model.getBranchId());
		response.setBranchAddress(model.getBranchAddress());
		response.setBankAccountType(model.getBankAccountType());
		response.setBankAccountName(model.getBankAccountName());
		response.setBankAccountNumber(model.getBankAccountNumber());
		response.setChequeDigits(model.getChequeDigits());
		response.setCurrency(model.getCurrency());
		response.setSwiftCode(model.getSwiftCode());
		response.setNotes(model.getNotes());
		response.setFileUrl(model.getFileUrl());
		response.setStatus(model.getStatus());
		response.setCreatedBy(model.getCreatedBy());
		response.setCreatedDate(model.getCreatedDate());
		response.setStatus(model.getStatus());
		response.setRefId(model.getRefId());
		response.setId(model.getId());
		response.setBankId(model.getBankId());
		response.setBranchName(model.getBranchName());
		return response;
	}

	public static CustomerInfoResponse convertCustomerInfoModelToResponse(CustomerInfoModel model) {
		CustomerInfoResponse response = new CustomerInfoResponse();
		response.setBusinessName(model.getBusinessName());
		response.setCategory(model.getCategory());
		response.setCode(model.getCode());
		response.setCreatedBy(model.getCreatedBy());
		response.setCreatedDate(model.getCreatedDate());
		response.setCustomerId(model.getCustomerId());
		response.setCustRegistrationNumber(model.getCustRegistrationNumber());
		response.setEndDate(model.getEndDate());
		response.setIndustry(model.getIndustry());
		response.setIsCustomer(model.getIsCustomer());
		response.setIsSupplier(model.getIsSupplier());
		response.setLegacyID(model.getLegacyID());
		response.setOrganizationId(model.getOrganizationId());
		response.setRating(model.getRating());
		response.setShortName(model.getShortName());
		response.setStartDate(model.getStartDate());
		response.setStatus(model.getStatus());
		response.setSubIndustry(model.getSubIndustry());
		response.setSupplRegistrationNumber(model.getSupplRegistrationNumber());
		response.setType(model.getType());
		response.setUpdatedBy(model.getUpdatedBy());
		response.setUpdatedDate(model.getUpdatedDate());
		response.setWfStatus(model.getWfStatus());
		return response;
	}

}
