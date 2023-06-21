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
import com.travel.travtronics.eserv.model.Branch;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.APIResponsePaging;
import com.travel.travtronics.response.MessageResponse;
import com.travel.travtronics.service.BranchService;

@RestController
@RequestMapping("/branch")
public class BranchController {

	@Autowired
	BranchService branchservice;

	@PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
	public APIResponse createBranch(@RequestBody Branch model) {
		return branchservice.createBranch(model);
	}

	@GetMapping(value = "/id", produces = "application/json")
	public APIResponse getBranchId(@RequestParam Long id) {
		return branchservice.getBranchId(id);
	}

	@GetMapping(value = "/list", produces = "application/json")
	public APIResponse getBranch(@RequestParam Long orgId) {
		return branchservice.getBranch(orgId);
	}

	@PutMapping(value = "/update", consumes = "application/json", produces = "application/json")
	public MessageResponse updateBranch(@RequestBody Branch model) {
		return branchservice.updateBranch(model);
	}

	@GetMapping(value = "/list-pagination")
	public APIResponsePaging getPagenationByOrganization(@RequestParam(required = false) Long organizationId,
			@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize,
			@RequestParam(defaultValue = "id") String sortBy,
			@RequestParam(defaultValue = "asc", required = false) SortType sortType) {
		return branchservice.getPaginationByOrganization(organizationId, pageNo, pageSize, sortBy, sortType);
	}
}
