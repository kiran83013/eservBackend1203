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
import com.travel.travtronics.request.CustomerRelationsRequest;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.APIResponsePaging;
import com.travel.travtronics.response.MessageStatusResponse;
import com.travel.travtronics.service.CustomerRelationsService;
import com.travel.travtronics.util.NotFoundException;

@RestController
@RequestMapping("/customer_relations")
public class CustomerRelationsController {

	@Autowired
	CustomerRelationsService customerRelationsService;

	@PostMapping
	public MessageStatusResponse createCustomerRelations(@Valid @RequestBody CustomerRelationsRequest requestModel) {
		return customerRelationsService.createCustomerRelations(requestModel);
	}

	@PutMapping("/update")
	public MessageStatusResponse editCustomerRelations(@Valid @RequestBody CustomerRelationsRequest requestModel)
			throws NotFoundException {
		return customerRelationsService.editCustomerRelations(requestModel);
	}

	@GetMapping("/relationsid")
	public APIResponse getRelationsId(@RequestParam Long relationShipId) throws NotFoundException {
		return customerRelationsService.getRelationsId(relationShipId);
	}

	@GetMapping("/all")
	public APIResponse getListByFirstPartyId(@RequestParam Long firstPartyId,Long organizationId) throws NotFoundException {
		return customerRelationsService.getListByFirstPartyId(firstPartyId,organizationId);
	}
	
	@GetMapping(value = "/list-pagination")
	public APIResponsePaging getPagenationByOrganization(@RequestParam(required = false) Long organizationId, @RequestParam(required = false) Long firstPartyId,
			@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize,
			@RequestParam(defaultValue = "relationShipId") String sortBy,
			@RequestParam(defaultValue = "asc", required = false) SortType sortType) {
		return customerRelationsService.getPaginationByOrganization(organizationId, firstPartyId, pageNo, pageSize, sortBy, sortType);
	}

}
