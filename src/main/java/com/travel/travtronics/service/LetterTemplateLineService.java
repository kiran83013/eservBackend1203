package com.travel.travtronics.service;

import java.math.BigInteger;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.travel.travtronics.converter.BankConverter;
import com.travel.travtronics.eserv.model.LetterTemplateLines;
import com.travel.travtronics.eserv.repository.LetterTemplateLinesRepository;
import com.travel.travtronics.request.LetterTemplateLinesRequest;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.LetterLineResponse;

@Service
public class LetterTemplateLineService {

	@Autowired
	LetterTemplateLinesRepository letterTemplateLinesRepository;

	public APIResponse GetById(Long letterId) {

		List<LetterLineResponse> list = letterTemplateLinesRepository.findAllByLetterId(letterId).stream().map(t -> {

			LetterLineResponse r = new LetterLineResponse();

			r.setLetterLineId(t.get("letterLineId", BigInteger.class));
			r.setLetterId(t.get("letterId", BigInteger.class));
			r.setOrganizationId(t.get("organizationId", BigInteger.class));
			r.setCreatedBy(t.get("createdby", BigInteger.class));
			r.setUpdatedBy(t.get("updatedBy", BigInteger.class));
			r.setLanguageId(t.get("languageId", BigInteger.class));
			r.setCreatedDate(t.get("createdDate", Date.class));
			r.setUpdatedDate(t.get("updatedDate", Date.class));
			r.setOrganizationName(t.get("organizationName", String.class));
			r.setStatus(t.get("status", String.class));
			r.setHtmlEditor(t.get("htmlEditor", String.class));
			r.setLetterName(t.get("letterName", String.class));
			r.setLanguageName(t.get("languageName", String.class));
			String images = t.get("images", String.class);
			if (Objects.nonNull(images) && !images.isBlank()) {

				r.setImages(images.split(","));
			}

			return r;
		}).collect(Collectors.toList());

		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), list);

	}

//	public APIResponse editletterTemplateLines(List<LetterTemplateLinesRequest> model) {
//		List<LetterTemplateLines> list = letterTemplateLinesRepository.saveAll(
//				model.stream().map(BankConverter::convertLetterTemplteLinesToModel).collect(Collectors.toList()));
//		System.out.println("LetterTempltelines-modified");
//		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), list);
//	}

	public APIResponse editLetterTemplateLines(List<@Valid LetterTemplateLinesRequest> model) {

		List<LetterTemplateLines> lines = new ArrayList<>();
		for (LetterTemplateLinesRequest line : model) {

			if (line.getStatus().equals(com.travel.travtronics.util.EnumStatus.InActive)) {
				LetterTemplateLines deactivatedLine = BankConverter.convertLetterTemplteLinesToModel(line);
				lines.add(deactivatedLine);
			} else {

				if (letterTemplateLinesRepository.countByLetterIdAndLanguageId(line.getLetterId(),
						line.getLanguageId()) > 0) {
					return new APIResponse(HttpStatus.CONFLICT.value(), "letter template already exists in our system");
				} else {
					LetterTemplateLines activatedLine = BankConverter.convertLetterTemplteLinesToModel(line);
					lines.add(activatedLine);
				}
			}
		}

		List<LetterTemplateLines> list = letterTemplateLinesRepository.saveAll(lines);
		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), list);

	}

	public APIResponse saveLines(List<@Valid LetterTemplateLinesRequest> model) {

		for (LetterTemplateLinesRequest line : model) {
			if (letterTemplateLinesRepository.countByLetterIdAndLanguageId(line.getLetterId(),
					line.getLanguageId()) > 0) {
				return new APIResponse(HttpStatus.CONFLICT.value(), "letter template already exists in our system");
			}
		}
		List<LetterTemplateLines> save = letterTemplateLinesRepository.saveAll(
				model.stream().map(BankConverter::convertLetterTemplteLinesToModel).collect(Collectors.toList()));
		return new APIResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED.name(), save);

	}

}
