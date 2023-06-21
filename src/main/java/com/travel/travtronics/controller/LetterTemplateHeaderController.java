package com.travel.travtronics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.travel.travtronics.eserv.model.LetterTemplateHeader;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.service.LetterTemplateHeaderService;

@RestController
@RequestMapping("letter-template-header")
public class LetterTemplateHeaderController {

	@Autowired
	LetterTemplateHeaderService letterTemplateHeaderService;

	@PostMapping(path = "create")
	public APIResponse letterTemplateHeader(@RequestBody LetterTemplateHeader model) {
		return letterTemplateHeaderService.letterTemplateHeader(model);
	}

	@GetMapping(value = "id", produces = "application/json")
	public APIResponse GetById(@RequestParam Long id) {
		return letterTemplateHeaderService.GetById(id);
	}

	@PutMapping(value = "edit", consumes = "application/json", produces = "application/json")
	public APIResponse editletterTemplateHeader(@RequestBody LetterTemplateHeader model) {
		return letterTemplateHeaderService.editletterTemplateHeader(model);
	}

	@GetMapping(value = "list", produces = "application/json")
	public APIResponse getAll(Long organizationId) {
		return letterTemplateHeaderService.getAll(organizationId);
	}

}
