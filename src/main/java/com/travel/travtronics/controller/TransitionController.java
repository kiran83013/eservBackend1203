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
import com.travel.travtronics.request.TransitionRequest;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.APIResponsePaging;
import com.travel.travtronics.response.MessageStatusResponse;
import com.travel.travtronics.service.TransitionService;

@RestController
@RequestMapping("/Transition")
public class TransitionController {

	@Autowired
	TransitionService tService;

	@PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
	public APIResponse createTransition(@RequestBody TransitionRequest transition) {
		return tService.createTransition(transition);
	}

	@GetMapping(value = "/id", produces = "application/json")
	public APIResponse getId(@RequestParam Long transitionId) {
		return tService.getId(transitionId);
	}

	@PutMapping(value = "/edit", consumes = "application/json", produces = "application/json")
	public MessageStatusResponse edit(@RequestBody TransitionRequest request) {
		return tService.edit(request);
	}

	@GetMapping(value = "/list", produces = "application/json")
	public APIResponse getAll(@RequestParam Long organizationId) {
		return tService.getAll(organizationId);
	}
	
	@GetMapping(value = "/list-transition-pagination")
	public APIResponsePaging getTransitionPagenationByOrganization(
			@RequestParam(required = false) Long organization, @RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "10") int pageSize, @RequestParam(defaultValue = "transitionId") String sortBy,
			@RequestParam(defaultValue = "asc", required = false) SortType sortType) {
		return tService.getTransitionPagenationByOrganization(organization, pageNo, pageSize, sortBy,
				sortType);
	}

	@GetMapping(value = "/bpftransitions", produces = "application/json")
	public APIResponse bpfTransitions(@RequestParam Long organization) {
		return tService.bpfTransitions(organization);
	}

	@GetMapping("/get-statusInfo-by-status-id")
	public APIResponse getStatusInfo(@RequestParam Integer fromstatusId) {
		return tService.getStatusInfo(fromstatusId);
	}

	

	@GetMapping("/transition-tree")
	public APIResponse getTransitionTree(@RequestParam Integer fromstatusId) {
		return tService.getTransitionTree(fromstatusId);
	}

	@GetMapping("/generate-transition-tree")
	public APIResponse generateTransitionTree(@RequestParam Integer fromstatusId) {
		return tService.getTransitionTreeInfo(fromstatusId);
	}

	// TrasitionResponse
//	@GetMapping(value = "/name", produces = "application/json")
//	public APIResponse getName(@RequestParam String moduleName) {
//		return tService.getName(moduleName);
//	}
//
//	@GetMapping(value = "/createdby", produces = "application/json")
//	public APIResponse getcreatedBy(@RequestParam String createdBy) {
//		return tService.getcreatedBy(createdBy);
//	}
//	

}
