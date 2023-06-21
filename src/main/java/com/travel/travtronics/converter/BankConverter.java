package com.travel.travtronics.converter;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.travel.travtronics.eserv.model.Bank;
import com.travel.travtronics.eserv.model.LetterTemplateLines;
import com.travel.travtronics.eserv.model.UnitShiftsSlotAssignmentModel;
import com.travel.travtronics.request.LetterTemplateLinesRequest;
import com.travel.travtronics.response.BankResponse;
import com.travel.travtronics.response.UnitShiftsSlotAssignmentResponse;

public class BankConverter {
	public static BankResponse convertModelToResponses(Bank bank) {
		BankResponse response = new BankResponse();
		response.setId(bank.getId());
		response.setName(bank.getName());
		response.setShortName(bank.getShortName());
		response.setCode(bank.getCode());
		response.setCountry(bank.getCountry());
		response.setCurrency(bank.getCurrency());
		response.setBankType(bank.getBankType());
		response.setOrganizationId(bank.getOrganizationId());
		response.setBankCtgy(bank.getBankCtgy());
		response.setUrl(bank.getUrl());
		response.setBiz(bank.getBiz());
		response.setCode1(bank.getCode1());
		response.setCode2(bank.getCode2());
		response.setCode3(bank.getCode3());
		response.setCode4(bank.getCode4());
		response.setCode5(bank.getCode5());
		response.setStartDate(bank.getStartDate());
		response.setEndDate(bank.getEndDate());
		response.setStatus(bank.getStatus());
		response.setCreatedBy(bank.getCreatedBy());
		response.setCreatedDate(bank.getCreatedDate());
		response.setUpdatedBy(bank.getUpdatedBy());
		response.setUpdatedDate(bank.getUpdatedDate());
		return response;
	}

	public static List<BankResponse> convertModelToResponse(List<Bank> requests) {
		return requests.stream().map(model -> convertModelToResponses(model)).collect(Collectors.toList());
	}

	public static LetterTemplateLines convertLetterTemplteLinesToModel(LetterTemplateLinesRequest request) {

		LetterTemplateLines lines = new LetterTemplateLines();

		if (request.getLetterLineId() != null && request.getLetterLineId() != 0)
			lines.setLetterLineId(request.getLetterLineId());
		if (request.getLetterId() != null && request.getLetterId() != 0)
			lines.setLetterId(request.getLetterId());
		if (request.getOrganizationId() != null && request.getOrganizationId() != 0)
			lines.setOrganizationId(request.getOrganizationId());
		if (request.getLanguageId() != null && request.getLanguageId() != 0)
			lines.setLanguageId(request.getLanguageId());
		if (request.getHtmlEditor() != null && !request.getHtmlEditor().isEmpty())
			lines.setHtmlEditor(request.getHtmlEditor());
		if (request.getCreatedBy() != null && request.getCreatedBy() != 0)
			lines.setCreatedBy(request.getCreatedBy());
		if (request.getCreatedDate() != null)
			lines.setCreatedDate(request.getCreatedDate());
		if (request.getUpdatedBy() != null && request.getUpdatedBy() != 0)
			lines.setUpdatedBy(request.getUpdatedBy());
		if (request.getUpdatedDate() != null)
			lines.setUpdatedDate(request.getUpdatedDate());
		if (request.getStatus() != null)
			lines.setStatus(request.getStatus());
		if (Objects.nonNull(request.getImages()) && !request.getImages().isEmpty())
			lines.setImages(request.getImages());
		return lines;
	}

	public static UnitShiftsSlotAssignmentResponse convertUnitShiftSlotModelToResponse(UnitShiftsSlotAssignmentModel model) {
		UnitShiftsSlotAssignmentResponse response = new UnitShiftsSlotAssignmentResponse();
		response.setId(model.getId());
		response.setUnitId(model.getUnitId());
		response.setOrgId(model.getOrgId());
		response.setShiftId(model.getShiftId());
		response.setSlotTemplateId(model.getSlotTemplateId());
		response.setStartDate(model.getStartDate());
		response.setEndDate(model.getEndDate());
		response.setStatus(model.getStatus());
		response.setCreatedBy(model.getCreatedBy());
		response.setCreatedDate(model.getCreatedDate());
		response.setUpdatedBy(model.getUpdatedBy());
		response.setUpdatedDate(model.getUpdatedDate());
		return response;
	}
}
