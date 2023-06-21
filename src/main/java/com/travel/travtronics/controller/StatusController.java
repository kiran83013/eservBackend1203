package com.travel.travtronics.controller;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.travel.travtronics.enums.SortType;
import com.travel.travtronics.eserv.model.Status;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.APIResponsePaging;
import com.travel.travtronics.service.StatusService;

@RestController
@RequestMapping("/status")
public class StatusController {
	
	@Autowired
	StatusService  sService;
	
	@PostMapping(value = "/create", consumes ="application/json", produces ="application/json")
	public APIResponse createstatus(@RequestBody Status status) {
		return sService.createstatus(status);
	}
	
	@GetMapping(value = "/id", produces = "application/json")
	public APIResponse getId(@RequestParam Long statusId) {
		return sService.getId(statusId);
	}
	
	@PutMapping(value= "/edit", consumes = "application/json", produces = "application/json")
	public APIResponse edit(@RequestBody Status status) {
		return sService.edit(status);
	}
	
	@GetMapping(value ="/list", produces = "application/json")
	public APIResponse getAll(@RequestParam String organization) {
		return sService.getAll(organization);
	}
	@GetMapping(value = "/list-by-organization", produces = "application/json")
	public  APIResponse getStatusByOrganizationAndModule(@RequestParam String organization,@RequestParam String module) {
		return sService.getStatusByOrganization(organization, module);
	}
	
	@GetMapping(value = "/list-status-pagination")
	public APIResponsePaging getStatusPagenationByOrganization(
			@RequestParam(required = false) String organization, @RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "10") int pageSize, @RequestParam(defaultValue = "statusId") String sortBy,
			@RequestParam(defaultValue = "asc", required = false) SortType sortType) {
		return sService.getStatusPagenationByOrganization(organization, pageNo, pageSize, sortBy,
				sortType);
	}
	
//	@GetMapping(value = "/name", produces = "application/json")
//	public APIResponse getName(@RequestParam String name) {
//		return sService.getName(name);
//	}
//
//	@GetMapping(value = "/createdby", produces = "application/json")
//	public APIResponse getcreatedBy(@RequestParam String createdBy) {
//		return sService.getcreatedBy(createdBy);
//	}
//	
//
//	
//	@GetMapping(value = "/allnames", produces="application/json")
//	public APIResponse names(@RequestParam String name){
//		return sService.names(name);
//	}
//	
//	@DeleteMapping(value = "/deleteid", produces ="application/json")
//	public MessageResponse deleteid(@RequestParam Long statusId) {
//		return sService.deleteid(statusId);
//	}
//	
//	@DeleteMapping(value = "/deletelist")
//	public MessageResponse deleteList() {
//		return sService.deleteList();
//	}

}
