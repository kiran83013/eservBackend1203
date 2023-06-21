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
import com.travel.travtronics.eserv.model.Brand;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.APIResponsePaging;
import com.travel.travtronics.service.BrandService;



@RestController
@RequestMapping("/brand/")
public class BrandController {

	@Autowired
	BrandService brandservice;
	
	@PostMapping(value = "create", consumes = "application/json", produces = "application/json")
	public APIResponse createBrand(@RequestBody Brand model) {
		return brandservice.createBrand(model);
	}
	
	@GetMapping(value = "getList", produces = "application/json")
	public APIResponse listBrand(@RequestParam Long  organizationId) {
		return brandservice.listBrand(organizationId);
	}
	
	@GetMapping(value = "getById", produces = "application/json")
	public APIResponse getById(@RequestParam Long id) {
		return brandservice.getById(id);
	}
	
	@PutMapping(value = "editBrand", consumes = "application/json", produces = "application/json")
	public APIResponse editBrand(@RequestBody Brand model) {
		return brandservice.editBrand(model);
	}
	
	@GetMapping(value = "customerId", produces = "application/json")
	public APIResponse getBycustomerId(@RequestParam Long customerId) {
		return brandservice.getBycustomerId(customerId);
	}
	
	@GetMapping(value = "/list-bank-pagination")
	public APIResponsePaging getPagenationByOrganization(@RequestParam(required = false) Long organizationId,
			@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize,
			@RequestParam(defaultValue = "id") String sortBy,
			@RequestParam(defaultValue = "asc", required = false) SortType sortType) {
		return brandservice.getPaginationByOrganization(organizationId, pageNo, pageSize, sortBy, sortType);
	}
}
