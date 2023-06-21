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

import com.travel.travtronics.enums.SortType;
import com.travel.travtronics.eserv.model.LeadInterestedProperties;
import com.travel.travtronics.request.LeadInterestedPropertiesRequest;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.APIResponsePaging;
import com.travel.travtronics.service.LeadInterestedPropertiesService;

@RestController
@RequestMapping("/LIP")
public class LeadInterestedPropertiesController {

	@Autowired
	LeadInterestedPropertiesService leadInterestedPropertiesService;

	@PostMapping(value = "create-old", consumes = "application/json", produces = "application/json")
	public APIResponse createOld(@RequestBody List<LeadInterestedProperties> model) {
		return leadInterestedPropertiesService.createOld(model);
	}

	@PostMapping(value = "create", consumes = "application/json", produces = "application/json")
	public APIResponse create(@RequestBody List<@Valid LeadInterestedPropertiesRequest> model) {
		return leadInterestedPropertiesService.create(model);
	}
	
	@PutMapping(value = "update", consumes = "application/json", produces = "application/json")
	public APIResponse upadte(@RequestBody List<LeadInterestedProperties> model) {
		return leadInterestedPropertiesService.upadte(model);
	}

	@GetMapping(value = "/get-by-businessId-old")
	public APIResponsePaging getByBusinessIdOld(@RequestParam Long businessId, @RequestParam Long organizationId,
			@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize,
			@RequestParam(defaultValue = "id") String sortBy,
			@RequestParam(defaultValue = "asc", required = false) SortType sortType) {
		return leadInterestedPropertiesService.getByBusinessIdOld(businessId, organizationId, pageNo, pageSize, sortBy,
				sortType);
	}

	@GetMapping(value = "/get-by-businessId")
	public APIResponsePaging getByBusinessId(@RequestParam Long businessId,
			@RequestParam Long organizationId, @RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "10") int pageSize, @RequestParam(defaultValue = "id") String sortBy,
			@RequestParam(defaultValue = "asc", required = false) SortType sortType) {
		return leadInterestedPropertiesService.getByBusinessId(organizationId, businessId, pageNo, pageSize, sortBy, sortType);
	}
}
