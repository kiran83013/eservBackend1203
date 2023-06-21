package com.travel.travtronics.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.travel.travtronics.eserv.model.SanctionGroupSrTypes;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.service.SanctionGroupSrTypesService;

@RestController
@RequestMapping("/sanctionGroupSrTypes")
public class SanctionGroupSrTypesController {

	@Autowired
	SanctionGroupSrTypesService sanctionGroupSrTypesService;
	
	
	@PostMapping(value = "create", consumes = "application/json", produces = "application/json")
	public APIResponse create(@RequestBody List<SanctionGroupSrTypes> model) {
		return sanctionGroupSrTypesService.create(model);
	}
	
	@PutMapping(value = "update", consumes = "application/json", produces = "application/json")
	public APIResponse update(@RequestBody List<SanctionGroupSrTypes> model) {
		return sanctionGroupSrTypesService.update(model);
	}
	
	@GetMapping(value = "sanctionGroup", produces = "application/json")
	public APIResponse getBySanctionGroup(@RequestParam Long sanctionGroup) {
		return sanctionGroupSrTypesService.getBySanctionGroup(sanctionGroup);
	}
}
