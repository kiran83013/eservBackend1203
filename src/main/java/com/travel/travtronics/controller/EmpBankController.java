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
import com.travel.travtronics.request.EmpBankDto;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.APIResponsePaging;
import com.travel.travtronics.response.MessageStatusResponse;
import com.travel.travtronics.service.EmpBankService;

@RestController
@RequestMapping("/emp_bank")
public class EmpBankController {
	
	@Autowired
	EmpBankService  empBankService;
	
	@PostMapping(value = "emp-bank/create", consumes = "application/json", produces = "application/json")
	public APIResponse createBank(@RequestBody EmpBankDto model) {
		return empBankService.createEmpBank(model);
	}
	
	@PutMapping(value = "emp-bank/modify", consumes = "application/json", produces = "application/json")
	public MessageStatusResponse modifyBank(@RequestBody EmpBankDto model, @RequestParam Long id) {
		return empBankService.modifyempBank(model, id);
	}
	
	@GetMapping(value = "emp-bank/all")
	public APIResponse getAll(@RequestParam Long organizationId) {
		return empBankService.getAll(organizationId);
	}
	
	@GetMapping(value = "emp-bank/refId")
	public APIResponse getAllByRefId(@RequestParam Long refId) {
		return empBankService.fetchDataByRefId(refId);
	}
	
	@GetMapping(value = "emp-bank/id")
	public APIResponse getDataById(@RequestParam Long id) {
		return empBankService.fetchDataById(id);
	}
	
	@GetMapping(value = "/get-pagination")
    public APIResponsePaging getPagenationByOrganization(@RequestParam(required = false) Long organizationId, @RequestParam(required = false) Long refId,
                                                @RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize,
                                                @RequestParam(defaultValue = "id") String sortBy,@RequestParam(defaultValue = "asc", required = false) SortType sortType) {
        return  empBankService.getPagenationByOrganization(organizationId, refId, pageNo, pageSize, sortBy, sortType);
    }


}
