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
import com.travel.travtronics.eserv.model.BpfMailTemplate;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.APIResponsePaging;
import com.travel.travtronics.service.BpfMailTemplateService;

@RestController
@RequestMapping("bpf_mail_template")
public class BpfMailTemplateController {
	
	@Autowired
	BpfMailTemplateService bpfmtService;
	
	@PostMapping(value ="/create",consumes = "application/json",produces ="application/json")
	public APIResponse create(@RequestBody BpfMailTemplate bpfmTemplate) {
		return bpfmtService.create(bpfmTemplate);
	}
	
	@GetMapping(value = "/id", produces = "application/json")
	public APIResponse getId(@RequestParam Long id) {
		return bpfmtService.getId(id);
	}
	
	@PutMapping(value= "/edit", consumes = "application/json", produces = "application/json")
	public APIResponse edit(@RequestBody BpfMailTemplate bpfmTemplate) {
		return bpfmtService.edit(bpfmTemplate);
	}
	
	@GetMapping(value ="/list", produces = "application/json")
	public APIResponse getAll(Long organizationId) {
		return bpfmtService.getAll(organizationId);
	}
	
	@GetMapping(value = "/list-personprofile-pagination")
	public APIResponsePaging getPersonProfilePagenationByOrganization(
			@RequestParam(required = false) Long organizationId, @RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "10") int pageSize, @RequestParam(defaultValue = "id") String sortBy,
			@RequestParam(defaultValue = "asc", required = false) SortType sortType) {
		return bpfmtService.getPersonProfilePagenationByOrganization(organizationId, pageNo, pageSize, sortBy,
				sortType);
	}
}

