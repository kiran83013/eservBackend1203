package com.travel.travtronics.converter;

import java.util.List;
import java.util.stream.Collectors;

import com.travel.travtronics.eserv.model.EmpBank;
import com.travel.travtronics.eserv.model.EmpLanguage;
import com.travel.travtronics.eserv.model.EmpQualification;
import com.travel.travtronics.eserv.model.EmpRelationships;
import com.travel.travtronics.request.EmpBankDto;
import com.travel.travtronics.response.EmpBankResponse;
import com.travel.travtronics.response.EmpLanguageResponse;
import com.travel.travtronics.response.EmpQualificationResponse;
import com.travel.travtronics.response.EmpRelationshipsResponse;


public class EmpBankConverter {

	public static EmpBank convertJsonToModel(EmpBankDto request) {
		EmpBank model= new EmpBank();
		model.setBankName(request.getBankName());
		model.setId(request.getId());
		model.setBranchId(request.getBranchId());
		model.setOrganizationId(request.getOrganizationId());
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

	public static EmpBank updateJsonToModel(EmpBankDto request, Long id) {
		EmpBank model = new EmpBank();
		if (id != null && id != 0)
			model.setId(id);
		model.setBankName(request.getBankName());
		model.setBranchId(request.getBranchId());
		model.setOrganizationId(request.getOrganizationId());
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

	public static List<EmpBankResponse> convertModelsToResponses(List<EmpBank> models) {
		return models.stream().map(EmpBankConverter::convertModelToResponse).collect(Collectors.toList());
	}

	public static EmpBankResponse convertModelToResponse(EmpBank model) {
		EmpBankResponse response = new EmpBankResponse();
		
	
		response.setOrganizationId(model.getOrganizationId());
		response.setBankName(model.getBankName());
		response.setOrganizationId(model.getOrganizationId());
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

	public static EmpLanguageResponse convertEmpLanguageModelToResponse(EmpLanguage model) {
		EmpLanguageResponse  response = new EmpLanguageResponse();
		response.setCreatedBy(model.getCreatedBy());
		response.setCreatedDate(model.getCreatedDate());
		response.setId(model.getId());
		response.setLanguageId(model.getLanguageId());
		response.setOrganizationId(model.getOrganizationId());
		response.setRead(model.getRead());
		response.setRefId(model.getRefId());
		response.setSpeak(model.getSpeak() );
		response.setWrite(model.getWrite());
		response.setStatus(model.getStatus());
		response.setUpdatedBy(model.getUpdatedBy());
		response.setUpdatedDate(model.getUpdatedDate());
		return response;
	}

	public static EmpQualificationResponse convertEmpQualificationModelToResponse(EmpQualification model) {
		EmpQualificationResponse response = new EmpQualificationResponse();
		response.setCertificationNumber(model.getCertificationNumber());
		response.setCollege(model.getCollege());
		response.setCreatedBy(model.getCreatedBy());
		response.setCreatedDate(model.getCreatedDate());
		response.setFromDate(model.getFromDate());
		response.setToDate(model.getToDate());
		response.setId(model.getId());
		response.setOrganizationId(model.getOrganizationId());
		response.setQualification(model.getQualification());
		response.setQualificationType(model.getQualificationType());
		response.setRefId(model.getRefId());
		response.setStatus(model.getStatus());
		response.setUpdatedBy(model.getUpdatedBy());
		response.setUpdatedDate(model.getUpdatedDate());
		return response;
	}

	public static EmpRelationshipsResponse convertEmpRelationModelToResponse(EmpRelationships model) {
		EmpRelationshipsResponse response= new EmpRelationshipsResponse();
		response.setOrganizationId(model.getOrganizationId());
		response.setCreatedBy(model.getCreatedBy());
		response.setCreatedDate(model.getCreatedDate());
		response.setFirstPartyId(model.getFirstPartyId());
		response.setSecondPartyTypeId(model.getSecondPartyTypeId());
		response.setSecondPartyId(model.getSecondPartyId());
		response.setFromDate(model.getFromDate());
		response.setFromRelationId(model.getFromRelationId());
		response.setToRelationId(model.getToRelationId());
		response.setToDate(model.getToDate());
		response.setStatus(model.getStatus());
		response.setRelationShipId(model.getRelationShipId());
		response.setUpdatedBy(model.getUpdatedBy());
		response.setUpdatedDate(model.getUpdatedDate());
		return response;
	}

}
