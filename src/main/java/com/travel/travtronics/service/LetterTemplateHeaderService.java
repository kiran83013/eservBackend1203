package com.travel.travtronics.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.travel.travtronics.eserv.model.LetterTemplateHeader;
import com.travel.travtronics.eserv.repository.LetterTemplateHeaderRepository;
import com.travel.travtronics.response.APIResponse;

@Service
public class LetterTemplateHeaderService {
 
	@Autowired
	LetterTemplateHeaderRepository  letterTemplateHeaderRepository;

	public APIResponse letterTemplateHeader(LetterTemplateHeader model) {
		try {
			List<LetterTemplateHeader> list = new ArrayList<>();
			LetterTemplateHeader save = letterTemplateHeaderRepository.save(model);
			list.add(save);
			return new APIResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED.name(), list);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name(),
					new ArrayList<>());
		}
	}

	public APIResponse GetById(Long id) {
		try {
			Optional<LetterTemplateHeader> opt = letterTemplateHeaderRepository.findById(id);
			List<LetterTemplateHeader> list = new ArrayList<>();
			if (opt.isPresent()) {
				list.add(opt.get());
				return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), list);
			} else {
				return new APIResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), new ArrayList<>());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name(),
					new ArrayList<>());
		}
	}

	public APIResponse editletterTemplateHeader(LetterTemplateHeader model) {
		List<LetterTemplateHeader> list = new ArrayList<>();
		try {
			Optional<LetterTemplateHeader> opt = letterTemplateHeaderRepository.findById(model.getLetterId());
			if (opt.isPresent()) {
				model.setCreatedBy(opt.get().getCreatedBy());
				LetterTemplateHeader save = letterTemplateHeaderRepository.save(model);
				list.add(save);
				return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), list);
			} else {
				return new APIResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), new ArrayList<>());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name(),
					new ArrayList<>());
		}
	}

	public APIResponse getAll(Long organizationId) {
		try {
			List<LetterTemplateHeader> list = letterTemplateHeaderRepository.findAllByOrganizationId(organizationId);
			return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), list);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new APIResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), new ArrayList<>());
		}
	}
	
	
}
