package com.travel.travtronics.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.travel.travtronics.request.LetterTemplateLinesRequest;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.service.LetterTemplateLineService;

@RestController
@RequestMapping("letter-template-lines")
public class LetterTemplateLineController {

	@Autowired
	LetterTemplateLineService letterTemplateLineService;

//    @PostMapping(value = "/save-lines", consumes = "application/json", produces = "application/json")
//    public APIResponse saveLines(@RequestBody List<LetterTemplateLines> model) {
//    	return letterTemplateLineService.saveLines(model);
//    }

	@GetMapping(value = "/get-letterId", produces = "application/json")
	public APIResponse GetById(@RequestParam Long letterId) {
		return letterTemplateLineService.GetById(letterId);
	}

//	@PutMapping(value = "/edit-lines", consumes = "application/json", produces = "application/json")
//	public APIResponse editletterTemplateLines(@RequestBody List<LetterTemplateLinesRequest> model) {
//		return letterTemplateLineService.editletterTemplateLines(model);
//	}

	@PostMapping(value = "/save-lines", consumes = "application/json", produces = "application/json")
	public APIResponse saveLines(@RequestBody List<@Valid LetterTemplateLinesRequest> model) {
		return letterTemplateLineService.saveLines(model);
	}

	@PutMapping(value = "/edit-lines", consumes = "application/json", produces = "application/json")
	public APIResponse editLetterTemplateLines(@RequestBody List<@Valid LetterTemplateLinesRequest> model) {
		return letterTemplateLineService.editLetterTemplateLines(model);
	}

}
