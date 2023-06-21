package com.travel.travtronics.converter;

import com.travel.travtronics.eserv.model.CustomerInfoModel;
import com.travel.travtronics.request.CustomerInfo;

public class CustomerInfoConverter {
	public static CustomerInfoModel convertJsonToModel(CustomerInfo info) {
		CustomerInfoModel model = new CustomerInfoModel();
		if (info.getCustomerId() != null)
			model.setCustomerId(Long.valueOf(info.getCustomerId()));
		model.setOrganizationId(info.getOrganizationId());
		model.setBusinessName(info.getBusinessName());
		model.setCategory(info.getCategory());
		model.setShortName(info.getShortName());
		model.setType(info.getType());
		model.setIndustry(info.getIndustry());
		model.setSubIndustry(info.getSubIndustry());
		model.setStartDate(info.getStartDate());
		model.setEndDate(info.getEndDate());
		model.setIsCustomer(info.getIsCustomer());
		model.setIsSupplier(info.getIsSupplier());
		model.setCustRegistrationNumber(info.getCustRegistrationNumber());
		model.setSupplRegistrationNumber(info.getSupplRegistrationNumber());
		model.setRating(info.getRating());
		model.setLegacyID(info.getLegacyID());
		model.setWfStatus(info.getWfStatus());
		model.setStatus(info.getStatus());
		model.setCode(info.getCode());
		model.setCreatedBy(info.getCreatedBy());
		model.setCreatedDate(info.getCreatedDate());
		model.setUpdatedBy(info.getUpdatedBy());
		model.setUpdatedDate(info.getUpdatedDate());
		model.setSrId(info.getSrId());

		return model;
	}

	public static CustomerInfo convertModelToJson(CustomerInfoModel model) {
		CustomerInfo info = new CustomerInfo();
		info.setOrganizationId(model.getOrganizationId());
		info.setCustomerId(Long.valueOf(model.getCustomerId()));
		info.setBusinessName(model.getBusinessName());
		info.setCategory(model.getCategory());
		info.setShortName(model.getShortName());
		info.setType(model.getType());
		info.setIndustry(model.getIndustry());
		info.setSubIndustry(model.getSubIndustry());
		info.setStartDate(model.getStartDate());
		info.setEndDate(model.getEndDate());
		info.setIsCustomer(model.getIsCustomer());
		info.setIsSupplier(model.getIsSupplier());
		info.setCustRegistrationNumber(model.getCustRegistrationNumber());
		info.setSupplRegistrationNumber(model.getSupplRegistrationNumber());
		info.setRating(model.getRating());
		info.setLegacyID(model.getLegacyID());
		info.setWfStatus(model.getWfStatus());
		info.setStatus(model.getStatus());
		info.setCode(model.getCode());
		info.setCreatedBy(model.getCreatedBy());
		info.setCreatedDate(model.getCreatedDate());
		info.setUpdatedBy(model.getUpdatedBy());
		info.setUpdatedDate(model.getUpdatedDate());
		info.setSrId(model.getSrId());

		return info;
	}

}
