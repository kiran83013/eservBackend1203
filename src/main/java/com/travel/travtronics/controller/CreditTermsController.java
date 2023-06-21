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
import com.travel.travtronics.eserv.model.CreditTerms;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.APIResponsePaging;
import com.travel.travtronics.response.MessageResponse;
import com.travel.travtronics.service.CreditTermsService;



@RestController
@RequestMapping("/creditterms")
public class CreditTermsController {

	@Autowired
	CreditTermsService credittermsservice;
	
	@PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
	public APIResponse creategetCreditTerms(@RequestBody CreditTerms model) {
		return credittermsservice.creategetCreditTerms(model);
	}
	
	@GetMapping(value = "/id", produces = "application/json")
	public APIResponse getCreditTermsId(@RequestParam Long id){
		return credittermsservice.getCreditTermsId(id);
	}
	
	@GetMapping(value = "/list", produces = "application/json")
	public APIResponse getCreditTerms(@RequestParam  Long organization){
		return credittermsservice.getCreditTerms(organization);
	}
	
	@PutMapping(value = "/update", consumes = "application/json", produces = "application/json")
	public MessageResponse updatePax(@RequestBody CreditTerms model) {
		return credittermsservice.updatePax(model);
	}
	
	@GetMapping(value = "/customerlist", produces = "application/json")
	public APIResponse getCustomerList(@RequestParam Long customerId, Long organization){
		return credittermsservice.getCustomerList(customerId, organization);
	}
	
	@GetMapping(value = "/list-pagination")
	public APIResponsePaging getPagenationByOrganization(@RequestParam(required = false) Long organization,
			@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize,
			@RequestParam(defaultValue = "id") String sortBy,
			@RequestParam(defaultValue = "asc", required = false) SortType sortType) {
		return credittermsservice.getPaginationByOrganization(organization, pageNo, pageSize, sortBy, sortType);
	}
}

