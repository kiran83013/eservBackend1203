package com.travel.travtronics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.travel.travtronics.eserv.model.PriceType;
import com.travel.travtronics.eserv.model.PriceTypeItems;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.MessageStatusResponse;
import com.travel.travtronics.service.PricingService;

@RestController
@RequestMapping("/price-type-master")
public class PricingMasterController {

	@Autowired
	PricingService pricingService;

	@PostMapping("/price-type")
	public MessageStatusResponse createModifyPricingType(@RequestBody PriceType request) {
		return pricingService.createModifyPricingType(request);
	}

	@GetMapping("/price-type")
	public APIResponse getPricingType(@RequestParam Long id) throws Exception {
		return pricingService.getPricingType(id);
	}

	@GetMapping("/price-types")
	public APIResponse getPricingTypes() {
		return pricingService.getPricingTypes();
	}

	@PostMapping("/price-type-items")
	public MessageStatusResponse createModifyPricingTypeItems(@RequestBody PriceTypeItems request) {
		return pricingService.createModifyPricingTypeItems(request);
	}

	@GetMapping("/price-type-item")
	public APIResponse getPricingTypeItems(@RequestParam Long id) throws Exception {
		return pricingService.getPricingTypeItems(id);
	}

	@GetMapping("/price-type-items-info")
	public APIResponse getPricingTypeItems() {
		return pricingService.getPricingTypeItems();
	}

}
