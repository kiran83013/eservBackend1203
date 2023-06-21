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
import com.travel.travtronics.eserv.model.Product;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.APIResponsePaging;
import com.travel.travtronics.service.ProductService;



@RestController
@RequestMapping("/product/")
public class ProductController {

	
	@Autowired
	ProductService productservice;
	
	@PostMapping(value = "create", consumes = "application/json", produces = "application/json")
	public APIResponse createProduct(@RequestBody Product model) {
		return productservice.createProduct(model);
	}
	
	@GetMapping(value = "list", produces = "application/json")
	public APIResponse listOfProducts() {
		return productservice.listOfProducts();
	}
	
	@GetMapping(value = "id", produces = "application/json")
	public APIResponse getById(@RequestParam Long id) {
		return productservice.getById(id);
	}
	
	@PutMapping(value = "edit", consumes = "application/json", produces = "application/json")
	public APIResponse editProduct(@RequestBody Product model) {
		return productservice.editProduct(model);
	}
	
	@GetMapping(value = "coustomerId", produces = "application/json")
	public APIResponse coustomerList(@RequestParam Long customerId,@RequestParam Long organizationId) {
		return productservice.coustomerList(customerId,organizationId);
	}
	
	@GetMapping(value = "/list-pagination")
	public APIResponsePaging getPagenationByOrganization(@RequestParam(required = false) Long organizationId,
			@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize,
			@RequestParam(defaultValue = "id") String sortBy,
			@RequestParam(defaultValue = "asc", required = false) SortType sortType) {
		return productservice.getPaginationByOrganization(organizationId, pageNo, pageSize, sortBy, sortType);
	}
	
	
}
