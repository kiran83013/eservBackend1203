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
import com.travel.travtronics.eserv.model.CustomerPid;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.APIResponsePaging;
import com.travel.travtronics.response.MessageResponse;
import com.travel.travtronics.service.CustomerPidService;

@RestController
@RequestMapping("/customer_pid")
public class CustomerPidController {

	@Autowired
	CustomerPidService customerPidService;

	@PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
	public APIResponse createCustomerPid(@RequestBody CustomerPid model) {
		return customerPidService.createCustomerPid(model);
	}

	@GetMapping(value = "/id", produces = "application/json")
	public APIResponse getByPidId(@RequestParam Long id) {
		return customerPidService.getByPidId(id);
	}

	@GetMapping(value = "/list", produces = "application/json")
	public APIResponse getAllPid() {
		return customerPidService.getAllPid();
	}

	@GetMapping(value = "/pid-by-refid", produces = "application/json")
	public APIResponse getPidByRefId(@RequestParam Long refId, @RequestParam Long organizationId) {
		return customerPidService.getPidByRefId(refId,organizationId);
	}

	@PutMapping(value = "/update", consumes = "application/json", produces = "application/json")
	public MessageResponse updateCustomerPid(@RequestBody CustomerPid model) {
		return customerPidService.updateCustomerPid(model);
	}

	@PostMapping(value = "/multiple-customer-Pid", consumes = "application/json", produces = "application/json")
	public APIResponse createMultipleCustomerPids(@RequestBody List<CustomerPid> models) {
		return customerPidService.createMultipleCustomerPids(models);
	}
	
	@GetMapping(value = "/list-pagination")
	public APIResponsePaging getPagenationByOrganization(@RequestParam(required = false) Long organizationId,@RequestParam(required = false) Long refId,
			@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize,
			@RequestParam(defaultValue = "id") String sortBy,
			@RequestParam(defaultValue = "asc", required = false) SortType sortType) {
		return customerPidService.getPaginationByOrganization(organizationId, refId, pageNo, pageSize, sortBy, sortType);
	}

}
