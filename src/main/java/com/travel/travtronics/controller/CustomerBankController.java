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
import com.travel.travtronics.request.CustomerBankDto;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.APIResponsePaging;
import com.travel.travtronics.response.MessageStatusResponse;
import com.travel.travtronics.service.CustomerBankService;

@RestController
@RequestMapping("/customer_bank")
public class CustomerBankController {

	@Autowired
	CustomerBankService customerBankService;

	@PostMapping(value = "customer-bank/create", consumes = "application/json", produces = "application/json")
	public APIResponse createCustomerBank(@RequestBody CustomerBankDto model) {
		return customerBankService.createCustomerBank(model);
	}

	@PutMapping(value = "customer-bank/modify", consumes = "application/json", produces = "application/json")
	public MessageStatusResponse modifyCustomerBank(@RequestBody CustomerBankDto model, @RequestParam Long id) {
		return customerBankService.modifyCustomerBank(model, id);
	}
	
	@GetMapping(value = "customer-bank/all")
	public APIResponse getAll() {
		return customerBankService.getAll();
	}
	
	@GetMapping(value = "customer-bank/refId")
	public APIResponse getAllByRefId(@RequestParam Long refId, @RequestParam Long organizationId) {
		return customerBankService.fetchDataByRefId(refId,organizationId);
	}
	
	@GetMapping(value = "customer-bank/id")
	public APIResponse getById(@RequestParam Long id) {
		return customerBankService.getById(id);
	}
	
	@GetMapping(value = "/list-pagination")
	public APIResponsePaging getPagenationByOrganization(@RequestParam(required = false) Long organizationId, @RequestParam(required = false) Long refId,
			@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize,
			@RequestParam(defaultValue = "id") String sortBy,
			@RequestParam(defaultValue = "asc", required = false) SortType sortType) {
		return customerBankService.getPaginationByOrganization(organizationId, refId, pageNo, pageSize, sortBy, sortType);
	}


}
