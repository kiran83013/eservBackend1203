package com.travel.travtronics.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.travel.travtronics.enums.SortType;
import com.travel.travtronics.request.CustomerInfo;
import com.travel.travtronics.request.SearchBusinessDto;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.APIResponsePaging;
import com.travel.travtronics.response.MessageStatusResponse;
import com.travel.travtronics.service.CustomerService;
import com.travel.travtronics.util.ConflictException;
import com.travel.travtronics.util.NotFoundException;

@RestController
@RequestMapping("/customer")
@Validated
public class CustomerController {

	private final CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@GetMapping("/all-byorganization")
	public APIResponse getAllCustomerInfo(@RequestParam Long organizationId) {
		return customerService.getAllCustomers(organizationId);
	}

	@PostMapping()
	public APIResponse saveCustomerInfo(@Valid @RequestBody CustomerInfo info) throws ConflictException {
		return customerService.saveCustomerInfo(info);
	}

	@GetMapping("/{customerId}")
	public APIResponse getByCustomerId(@PathVariable Long customerId) throws NotFoundException {
		return customerService.getByCustomerId(customerId);
	}

	@PutMapping()
	public MessageStatusResponse updateCustomerInfo(@Valid @RequestBody CustomerInfo info) throws NotFoundException {
		return customerService.updateCustomerInfo(info);
	}

	@GetMapping("/businessName")
	public APIResponse getCustomerByBusinessName(@RequestParam String businessName) {
		return customerService.getCustomerByBusinessName(businessName);
	}

	@GetMapping("supplier/businessName")
	public APIResponse getSupplierByBusinessName(@RequestParam String businessName) {
		return customerService.getSupplierByBusinessName(businessName);
	}

	@GetMapping("supplier/all")
	public APIResponse getSupplier() {
		return customerService.getSupplier();
	}

	@GetMapping("/{contactId}/{customerId}/{channelId}")
	public APIResponse getUserCustomerContactInfo(@PathVariable Integer contactId, @PathVariable Integer customerId,
			@PathVariable Integer channelId) {
		return customerService.getUserCustomerContactInfo(contactId, customerId, channelId);
	}

	@GetMapping(value = "/get")
	public APIResponsePaging getPagenationByOrganization(@RequestParam(required = false) Long organizationId,
			@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize,
			@RequestParam(defaultValue = "customerId") String sortBy,
			@RequestParam(defaultValue = "asc", required = false) SortType sortType) {
		return customerService.getPagenationByOrganization(organizationId, pageNo, pageSize, sortBy, sortType);
	}

	@PostMapping("/search-business")
	public ResponseEntity<?> searchBusiness(@RequestBody SearchBusinessDto searchDto) {

		return customerService.searchBusiness(searchDto);
	}

}
