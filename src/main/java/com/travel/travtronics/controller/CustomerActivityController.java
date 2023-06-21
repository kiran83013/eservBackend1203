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

import com.travel.travtronics.enums.SortType;
import com.travel.travtronics.eserv.model.CustomerActivity;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.APIResponsePaging;
import com.travel.travtronics.service.CustomerActivityService;

@RestController
@RequestMapping("/customer")
public class CustomerActivityController {

	@Autowired
	CustomerActivityService activityService;

	@PostMapping(path = "/activity", consumes = "application/json", produces = "application/json")
	public APIResponse customerActivity(@RequestBody List<CustomerActivity> models) {
		return activityService.customerActivity(models);
	}

	@GetMapping(path = "/get-id", produces = "application/json")
	public APIResponse getCustomerActivity(@RequestParam Long id) {
		return activityService.getCustomerActivity(id);
	}

	@GetMapping(path = "/get-activities", produces = "application/json")
	public APIResponse getCustomerActivityList() {
		return activityService.getCustomerActivityList();
	}

	@PutMapping(path = "/modify-activity", consumes = "application/json", produces = "application/json")
	public APIResponse modifyCustomerActivity(@RequestBody List<CustomerActivity> model) {
		return activityService.modifyCustomerActivity(model);
	}

	@GetMapping(path = "/get-activity-by-customer-organization", produces = "application/json")
	public APIResponse getCustomerActivityByCustomerId(@RequestParam Integer customerId, @RequestParam Long organizationId) {
		return activityService.getCustomerActivityByCustomerId(customerId,organizationId);
	}
	
	@GetMapping(value = "/list-pagination")
	public APIResponsePaging getPagenationByOrganization(@RequestParam(required = false) Long organizationId,@RequestParam Long customerId,
			@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize,
			@RequestParam(defaultValue = "id") String sortBy,
			@RequestParam(defaultValue = "asc", required = false) SortType sortType) {
		return activityService.getPaginationByOrganization(organizationId, customerId, pageNo, pageSize, sortBy, sortType);
	}

}