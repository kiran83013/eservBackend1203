package com.travel.travtronics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.service.SanctionConfigService;

@RestController
public class SanctionController {

	@Autowired
	SanctionConfigService sanctionConfigService;

	@GetMapping(value = "sanctions/set-up-list", produces = "application/json")
	public APIResponse getAllSactionSetUp(@RequestParam(required = false) Long id) {
		return sanctionConfigService.getAllSactionSetUp(id);
	}

}
