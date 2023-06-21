package com.travel.travtronics.converter;

import com.travel.travtronics.eserv.model.PersonLanguageModel;
import com.travel.travtronics.eserv.model.PersonPidModel;
import com.travel.travtronics.eserv.model.PersonQualificationModel;
import com.travel.travtronics.eserv.model.PersonRelationsModel;
import com.travel.travtronics.eserv.model.PersonSocialModel;
import com.travel.travtronics.eserv.model.PersonVerificationModel;
import com.travel.travtronics.response.PersonLanguageResponse;
import com.travel.travtronics.response.PersonPidResponse;
import com.travel.travtronics.response.PersonQualificationResponse;
import com.travel.travtronics.response.PersonRelationsResponse;
import com.travel.travtronics.response.PersonSocialResponse;
import com.travel.travtronics.response.PersonVerificationResponse;

public class PersonConverter {
	public static PersonSocialResponse convertPersonSocialModelToResponse(PersonSocialModel model) {
		PersonSocialResponse response = new PersonSocialResponse();
		response.setCreatedBy(model.getCreatedBy());
		response.setCreatedDate(model.getCreatedDate());
		response.setFromDate(model.getFromDate());
		response.setId(model.getId());
		response.setOrganizationId(model.getOrganizationId());
		response.setRefId(model.getRefId());
		response.setStatus(model.getStatus());
		response.setToDate(model.getToDate());
		response.setType(model.getType());
		response.setUpdatedBy(model.getUpdatedBy());
		response.setUpdatedDate(model.getUpdatedDate());
		response.setValue(model.getValue());
		return response;
	}

	public static PersonPidResponse convertPersonPidToResponse(PersonPidModel model) {
		PersonPidResponse response = new PersonPidResponse();
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

	public static PersonLanguageResponse convertPersonLanguageModelToResponse(PersonLanguageModel model) {
		PersonLanguageResponse response = new PersonLanguageResponse();
		response.setCreatedBy(model.getCreatedBy());
		response.setCreatedDate(model.getCreatedDate());
		response.setId(model.getId());
		response.setOrganizationId(model.getOrganizationId());
		response.setRefId(model.getRefId());
		response.setStatus(model.getStatus());
		response.setUpdatedBy(model.getUpdatedBy());
		response.setUpdatedDate(model.getUpdatedDate());
		response.setLanguageId(model.getLanguageId());
		response.setRead(model.getRead());
		response.setWrite(model.getWrite());
		response.setSpeak(model.getSpeak());
		return response;
	}

	public static PersonVerificationResponse convertPersonVerficationModelToResponse(PersonVerificationModel model) {
		PersonVerificationResponse response = new PersonVerificationResponse();
		response.setCreatedBy(model.getCreatedBy());
		response.setCreatedDate(model.getCreatedDate());
		response.setId(model.getId());
		response.setOrganizationId(model.getOrganizationId());
		response.setRefId(model.getRefId());
		response.setStatus(model.getStatus());
		response.setUpdatedBy(model.getUpdatedBy());
		response.setUpdatedDate(model.getUpdatedDate());
		response.setType(model.getType());
		response.setValue(model.getValue());
		response.setToDate(model.getToDate());
		response.setFromDate(model.getFromDate());
		return response;
	}

	public static PersonQualificationResponse convertQualificationModelToResponse(PersonQualificationModel model) {
		PersonQualificationResponse response = new PersonQualificationResponse();
		response.setCreatedBy(model.getCreatedBy());
		response.setCreatedDate(model.getCreatedDate());
		response.setId(model.getId());
		response.setOrganizationId(model.getOrganizationId());
		response.setRefId(model.getRefId());
		response.setStatus(model.getStatus());
		response.setUpdatedBy(model.getUpdatedBy());
		response.setUpdatedDate(model.getUpdatedDate());
		response.setCertificationNumber(model.getCertificationNumber());
		response.setCollege(model.getCollege());
		response.setQualification(model.getQualification());
		response.setQualificationType(model.getQualificationType());
		response.setToDate(model.getToDate());
		response.setFromDate(model.getFromDate());
		return response;
	}

	public static PersonRelationsResponse convertPersonRelationModelToResponse(PersonRelationsModel model) {
		PersonRelationsResponse response = new PersonRelationsResponse();
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
