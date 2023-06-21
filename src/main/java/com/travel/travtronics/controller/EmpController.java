package com.travel.travtronics.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.travel.travtronics.enums.SortType;
import com.travel.travtronics.request.EmpRequest;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.APIResponsePaging;
import com.travel.travtronics.response.MessageStatusResponse;
import com.travel.travtronics.service.EmpService;
import com.travel.travtronics.util.NotFoundException;

@RestController
public class EmpController {

	@Autowired
	EmpService empService;

	@PostMapping(value = "emp-create", consumes = "application/json", produces = "application/json")
	public APIResponse createEmp(@RequestBody EmpRequest request) {
		return empService.createEmp(request);
	}

	@GetMapping(value = "get-emp-id", produces = "application/json")
	public APIResponse getByid(@RequestParam Long id) throws NotFoundException {
		return empService.getByid(id);
	}

	@PutMapping(value = "emp-modify", consumes = "application/json", produces = "application/json")
	public MessageStatusResponse updateEmp(@RequestBody EmpRequest request, @RequestParam Long id) {
		return empService.updateEmp(request, id);
	}

	@GetMapping(value = "get-emp-all", produces = "application/json")
	public APIResponse getAll(@RequestParam Long organizationId) {
		return empService.getAll(organizationId);
	}

	@GetMapping(value = "/emp-search/{search}", produces = "application/json")
	public APIResponse getSearchByValue(@PathVariable String search) {
		return empService.getSearchByValue(search);
	}

	@GetMapping(value = "contact/search-contact", produces = "application/json")
	public APIResponse searchEmpContact(@RequestParam(required = false) String firstName,
			@RequestParam(required = false) String lastName, @RequestParam(required = false) String primaryEmail,
			@RequestParam(required = false) Long primaryPhoneNumber) {
		return empService.searchEmpContact(firstName, lastName, primaryEmail, primaryPhoneNumber);
	}

	@GetMapping(value = "contact/search-coustmer", produces = "application/json")
	public APIResponse searchEmpCustomer(@RequestParam(required = false) Long customersId,
			@RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName,
			@RequestParam(required = false) String primaryEmail,
			@RequestParam(required = false) Long primaryPhoneNumber) {
		return empService.searchEmpCustomer(customersId, firstName, lastName, primaryEmail, primaryPhoneNumber);
	}

	@GetMapping(value = "/filters", produces = "application/json")
	public APIResponse empFilters(@RequestParam(required = false) Long organizationId,
			@RequestParam(required = false) Long businessUnitId, @RequestParam(required = false) Long departmentId,
			@RequestParam(required = false) Long telephoneNumber, @RequestParam(required = false) String firstName,
			@RequestParam(required = false) String designationName, @RequestParam(required = false) String primaryEmail,
			@RequestParam(required = false) Long nationality, @RequestParam(required = false) String lastName,
			@RequestParam(value = "dob", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dob) {
		return empService.empFilter(organizationId, businessUnitId, departmentId, telephoneNumber, firstName,
				designationName, primaryEmail, nationality, dob, lastName);
	}
	
//	  @GetMapping(value = "/get-pagination")
//	    public APIResponsePaging getPagenationByOrganization(@RequestParam(required = false) Long organizationId,
//	                                                @RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize,
//	                                                @RequestParam(defaultValue = "id") String sortBy,@RequestParam(defaultValue = "asc", required = false) SortType sortType) {
//	        return  empService.getPagenationByOrganization(organizationId, pageNo, pageSize, sortBy, sortType);
//	    }
	
	 @GetMapping(value = "/get-pagination")
	    public APIResponsePaging getPagenationByOrganization(@RequestParam(required = false) Long organizationId,
	    		@RequestParam(required = false) Long businessUnitId, @RequestParam(required = false) Long departmentId,
				@RequestParam(required = false) Long primaryPhoneNumber, @RequestParam(required = false) String firstName,
				@RequestParam(required = false) String designationName, @RequestParam(required = false) String primaryEmail,
				@RequestParam(required = false) Long nationality, @RequestParam(required = false) String lastName,
				@RequestParam(value = "dob", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dob,
	            @RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize,
	            @RequestParam(defaultValue = "id") String sortBy,@RequestParam(defaultValue = "asc", required = false) SortType sortType) {
	        return  empService.getPagenationByOrganization(organizationId,businessUnitId, departmentId, primaryPhoneNumber, firstName,
					designationName, primaryEmail, nationality, dob, lastName, pageNo, pageSize, sortBy, sortType);
	    }
}
