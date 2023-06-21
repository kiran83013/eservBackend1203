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
import com.travel.travtronics.eserv.model.CustomerContactNew;
import com.travel.travtronics.request.EmpContactRequest;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.APIResponsePaging;
import com.travel.travtronics.response.MessageResponse;
import com.travel.travtronics.service.CustomerContactNewService;

@RestController
@RequestMapping("/customerContactNew")
public class CustomerContactNewController {

	@Autowired
	CustomerContactNewService customercontactnewservice;

	@PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
	public APIResponse createCustomerContact(@RequestBody CustomerContactNew model) {
		return customercontactnewservice.createCustomerContact(model);
	}

	@GetMapping(value = "id", produces = "application/json")
	public APIResponse getCustomerContactId(@RequestParam Long id) {
		return customercontactnewservice.getCustomerContact(id);
	}

	@GetMapping(value = "list", produces = "application/json")
	public APIResponse getCustomerContact(@RequestParam Long organizationId, @RequestParam Long customerId) {
		return customercontactnewservice.getCustomerContactList(organizationId,customerId);
	}

	@PutMapping(value = "update", consumes = "application/json", produces = "application/json")
	public MessageResponse updatePax(@RequestBody CustomerContactNew model) {
		return customercontactnewservice.updatePax(model);
	}

//	@PutMapping(value = "updateByCustomerId", consumes = "application/json", produces = "application/json")
//	public MessageResponse updateByCustomerId(@RequestParam Long id, @RequestParam Long customerId, @RequestBody CustomerContactNew model) {
//		return customercontactnewservice.updateByCustomerId(id, customerId, model);
//	}

	@GetMapping(value = "contact/search", produces = "application/json")
	public APIResponse searchCustomerContact(@RequestParam(required = false) String firstName,
			@RequestParam(required = false) String lastName, @RequestParam(required = false) String primaryEmail,
			@RequestParam(required = false) Long primaryPhoneNumber, @RequestParam Long customerId) {
		return customercontactnewservice.searchCustomerContact(firstName, lastName, primaryEmail, primaryPhoneNumber,
				customerId);
	}

	@GetMapping(value = "customerId", produces = "application/json")
	APIResponse getCustomerId(@RequestParam Long customerId,@RequestParam Long organizationId) {
		return customercontactnewservice.getCustomerId(customerId,organizationId);
	}

//	@PostMapping(value = "/contactWrapper", consumes = "application/json", produces = "application/json")
//	public APIResponse createCustomerContactWrapper(@RequestBody PaxContactRequest request) {
//		return customercontactnewservice.createCustomerContactWrapper(request);
//	}

	@GetMapping(value = "/search-emp", produces = "application/json")
	public APIResponse searchContact(@RequestParam(required = false) String name,
			@RequestParam(required = false) Long customerId, @RequestParam(required = false) String primaryEmail,
			@RequestParam(required = false) Long primaryPhoneNumber, @RequestParam(required = false) String firstName,
			@RequestParam(required = false) String lastName) {
		return customercontactnewservice.searchContact(name, customerId, primaryEmail, primaryPhoneNumber, firstName,
				lastName);
	}

	@PostMapping(value = "/custContactWrapper", consumes = "application/json", produces = "application/json")
	public APIResponse custContactWrapper(@RequestBody EmpContactRequest request) {
		return customercontactnewservice.custContactWrapper(request);
	}

	@GetMapping(value = "search-contact-by-customer", produces = "application/json")
	APIResponse searchContact(@RequestParam String searchParam, @RequestParam Long customerId) {
		return customercontactnewservice.searchContact(searchParam, customerId);
	}
	
	@GetMapping(value = "/search", produces = "application/json")
	public APIResponse search(@RequestParam(required = false) String firstName,
	@RequestParam(required = false) String lastName, @RequestParam(required = false) String primaryEmail,
	@RequestParam(required = false) Long primaryPhoneNumber, @RequestParam(required = false) Long customerId) {
		return customercontactnewservice.search(firstName, lastName, primaryEmail, primaryPhoneNumber,
				customerId);
	}
	
	@GetMapping(value = "/list-pagination-customer-contact-new")
	public APIResponsePaging getPagenationByOrganization(@RequestParam(required = false) Long organizationId,
			@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize,
			@RequestParam(defaultValue = "id") String sortBy,
			@RequestParam(defaultValue = "asc", required = false) SortType sortType) {
		return customercontactnewservice.getPaginationByOrganization(organizationId, pageNo, pageSize, sortBy, sortType);
	}

}
