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
import com.travel.travtronics.eserv.model.BpfServiceRegister;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.APIResponsePaging;
import com.travel.travtronics.service.BpfServiceRegisterService;

@RestController
@RequestMapping("/bpf_service_register")
public class BpfServiceRegisterController {
	
	@Autowired
	BpfServiceRegisterService  bpfsrService;
	
	@PostMapping(value ="/create",consumes = "application/json",produces ="application/json")
	public APIResponse create(@RequestBody BpfServiceRegister bpf) {
		return bpfsrService.create(bpf);
	}
	
	@GetMapping(value = "/id", produces = "application/json")
	public APIResponse getId(@RequestParam Long id) {
		return bpfsrService.getId(id);
	}
	
	@PutMapping(value= "/edit", consumes = "application/json", produces = "application/json")
	public APIResponse edit(@RequestBody BpfServiceRegister bpf) {
		return bpfsrService.edit(bpf);
	}
	
	@GetMapping(value ="/list", produces = "application/json")
	public APIResponse getAll(@RequestParam Long organizationId) {
		return bpfsrService.getAll(organizationId);
	}
	
	@GetMapping(value = "/list-bpfservice-register-pagination")
	public APIResponsePaging getBpfServiceRegisterPagenationByOrganization(
			@RequestParam(required = false) Long organizationId, @RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "10") int pageSize, @RequestParam(defaultValue = "id") String sortBy,
			@RequestParam(defaultValue = "asc", required = false) SortType sortType) {
		return bpfsrService.getBpfServiceRegisterPagenationByOrganization(organizationId, pageNo, pageSize, sortBy,
				sortType);
	}

}
