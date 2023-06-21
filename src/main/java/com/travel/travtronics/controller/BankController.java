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
import com.travel.travtronics.eserv.model.Bank;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.APIResponsePaging;
import com.travel.travtronics.response.MessageResponse;
import com.travel.travtronics.service.BankService;

@RestController
@RequestMapping("/bank")
public class BankController {

	@Autowired
	BankService bankservice;

	@PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
	public APIResponse createBank(@RequestBody Bank model) {
		return bankservice.createBank(model);
	}

	@GetMapping(value = "/id", produces = "application/json")
	public APIResponse getBankId(@RequestParam Long id) {
		return bankservice.getBankId(id);
	}

	@GetMapping(value = "/list", produces = "application/json")
	public APIResponse getBank(@RequestParam Long orgId) {
		return bankservice.getBank(orgId);
	}

	@PutMapping(value = "/update", consumes = "application/json", produces = "application/json")
	public MessageResponse updateBank(@RequestBody Bank model) {
		return bankservice.updateBank(model);
	}

	@GetMapping(value = "/list-pagination")
	public APIResponsePaging getPagenationByOrganization(@RequestParam(required = false) Long organizationId,
			@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize,
			@RequestParam(defaultValue = "id") String sortBy,
			@RequestParam(defaultValue = "asc", required = false) SortType sortType) {
		return bankservice.getPaginationByOrganization(organizationId, pageNo, pageSize, sortBy, sortType);
	}

//	@GetMapping(value = "/idedefef", produces = "application/json")
//	public APIResponse getCheck(@RequestParam Integer id) {
//		return bankservice.getCheck(id);
//	}
}
