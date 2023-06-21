package com.travel.travtronics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.travel.travtronics.enums.SortType;
import com.travel.travtronics.eserv.model.SubModule;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.APIResponsePaging;
import com.travel.travtronics.service.SubModuleService;


@RestController
@RequestMapping("/sub_module")
public class SubModuleController {
	
	@Autowired
	SubModuleService  smService;
	

	@PostMapping(value ="/create",consumes = "application/json",produces ="application/json")
	public APIResponse create(@RequestBody SubModule sm) {
		return smService.create(sm);
	}
	
	@GetMapping(value="/id", produces ="application/json")
	public APIResponse getId(@RequestParam Long id) {
		return smService.getId(id);
	}
	
	@PutMapping(value="/edit", consumes= "application/json", produces ="application/json")
	public APIResponse edit(@RequestBody SubModule sm) {
		return smService.edit(sm);
	}
	
	@GetMapping(value="/list",produces = "application/json")
	public APIResponse getlist(@RequestParam Long organizationId) {
		return smService.getlist(organizationId);
	}
	
	@GetMapping(value = "/list-submodule-pagination")
	public APIResponsePaging getSubModulePagenationByOrganization(
			@RequestParam(required = false) Long organizationId, @RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "10") int pageSize, @RequestParam(defaultValue = "id") String sortBy,
			@RequestParam(defaultValue = "asc", required = false) SortType sortType) {
		return smService.getSubModulePagenationByOrganization(organizationId, pageNo, pageSize, sortBy,
				sortType);
	}

}
