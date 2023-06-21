package com.travel.travtronics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.travel.travtronics.enums.SortType;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.APIResponsePaging;
import com.travel.travtronics.response.BpfConfigRequest;
import com.travel.travtronics.response.MessageResponse;
import com.travel.travtronics.service.BpfConfigHeaderService;

@RestController
@RequestMapping("/bpf-config")
public class BpfConfigController {

	@Autowired
	BpfConfigHeaderService bpfcHeaderService;

	@PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
	public MessageResponse create(@RequestBody BpfConfigRequest request) {
		return bpfcHeaderService.create(request);
	}

	@PutMapping(value = "/edit", consumes = "application/json", produces = "application/json")
	public MessageResponse edit(@RequestBody BpfConfigRequest request) {
		return bpfcHeaderService.edit(request);
	}

	@GetMapping(value = "/get-all", produces = "application/json")
	public APIResponse getAll(@RequestParam Long organizationId) {
		return bpfcHeaderService.getAll(organizationId);
	}

	@GetMapping(value = "/get-by-id/{id}", produces = "application/json")
	public APIResponse getById(@PathVariable Long id) {
		return bpfcHeaderService.getById(id);
	}
	
	@GetMapping(value = "/list-bpfconfig-pagination")
	public APIResponsePaging getBpfConfigPagenationByOrganization(
			@RequestParam(required = false) Long organizationId, @RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "10") int pageSize, @RequestParam(defaultValue = "ID") String sortBy,
			@RequestParam(defaultValue = "asc", required = false) SortType sortType) {
		return bpfcHeaderService.getBpfConfigPagenationByOrganization(organizationId, pageNo, pageSize, sortBy,
				sortType);
	}

}
