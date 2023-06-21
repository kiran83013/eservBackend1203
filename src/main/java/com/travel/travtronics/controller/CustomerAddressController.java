package com.travel.travtronics.controller;

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
import com.travel.travtronics.request.CustomerAddressRequest;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.APIResponsePaging;
import com.travel.travtronics.response.MessageStatusResponse;
import com.travel.travtronics.service.CustomerAddressService;
import com.travel.travtronics.util.ConflictException;
import com.travel.travtronics.util.NotFoundException;

@RestController
@RequestMapping("/customer_address")
public class CustomerAddressController {

	@Autowired
	CustomerAddressService customerAddressService;

	@PostMapping
	public MessageStatusResponse CreateAddress(@Valid @RequestBody CustomerAddressRequest requestModel)
			throws ConflictException {
		return customerAddressService.CreateAddress(requestModel);
	}

	@GetMapping("/customeraddressId")
	public APIResponse getAddress(@RequestParam Long addressId) throws NotFoundException {
		return customerAddressService.getById(addressId);
	}

	@GetMapping("/all")
	public APIResponse getAllCustomer(@RequestParam Long refId,@RequestParam Long organizationId) throws NotFoundException {
		return customerAddressService.getAllCustomer(refId,organizationId);

	}

	@PutMapping("/editCustomer")
	public MessageStatusResponse editAddress(@RequestParam Long addressId,
			@RequestBody CustomerAddressRequest requestModel) throws NotFoundException {
		return customerAddressService.editCustomer(addressId, requestModel);
	}
	
	@GetMapping("/pagination")
	public APIResponse getAllPage(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam("10") Integer pageSize, @RequestParam("id") String sortBy) {
		return customerAddressService.page(pageNo, pageSize, sortBy);
	}
	
	@GetMapping(value = "/list-pagination")
	public APIResponsePaging getPagenationByOrganization(@RequestParam(required = false) Long organizationId,
			@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize,
			@RequestParam(defaultValue = "addressId") String sortBy,
			@RequestParam(defaultValue = "asc", required = false) SortType sortType) {
		return customerAddressService.getPaginationByOrganization(organizationId, pageNo, pageSize, sortBy, sortType);
	}

}
