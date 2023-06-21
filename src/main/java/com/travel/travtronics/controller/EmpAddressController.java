package com.travel.travtronics.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.travel.travtronics.enums.SortType;
import com.travel.travtronics.request.EmpAddressRequest;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.APIResponsePaging;
import com.travel.travtronics.response.MessageStatusResponse;
import com.travel.travtronics.service.EmpAddressService;
import com.travel.travtronics.util.ConflictException;
import com.travel.travtronics.util.NotFoundException;

@RestController
@RequestMapping("/emp_address")
@Validated
public class EmpAddressController {

	@Autowired
	EmpAddressService empAddressService;

	@PostMapping
	public MessageStatusResponse createAddress(@Valid @RequestBody EmpAddressRequest requestModel)
			throws ConflictException {
		return empAddressService.save(requestModel);
	}

	@GetMapping("/empaddressId")
	public APIResponse getEmpAddress(@RequestParam Long addressId) throws NotFoundException {
		return empAddressService.getById(addressId);
	}

	@GetMapping("/allEmp")
	public APIResponse getAllEmp(@RequestParam Long refId, @RequestParam Long organizationId) throws NotFoundException {
		return empAddressService.getAllEmp(refId, organizationId);

	}

	@PutMapping("/editEmp")
	public MessageStatusResponse editEmpAddress(@RequestParam Long addressId,
			@RequestBody EmpAddressRequest requestModel) throws NotFoundException {
		return empAddressService.editEmpAddress(addressId, requestModel);
	}

	@GetMapping("/pagination")
	public APIResponse getAllEmpPage(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam("10") Integer pageSize, @RequestParam("id") String sortBy) {
		return empAddressService.Emppage(pageNo, pageSize, sortBy);
	}
	
	@GetMapping(value = "/get-pagination")
    public APIResponsePaging getPagenationByOrganization(@RequestParam(required = false) Long organizationId,
                                                @RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize,
                                                @RequestParam(defaultValue = "addressId") String sortBy,@RequestParam(defaultValue = "asc", required = false) SortType sortType) {
        return  empAddressService.getPagenationByOrganization(organizationId, pageNo, pageSize, sortBy, sortType);
    }

}
