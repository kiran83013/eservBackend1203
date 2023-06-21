package com.travel.travtronics.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.travel.travtronics.enums.SortType;
import com.travel.travtronics.eserv.model.EmpVerification;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.APIResponsePaging;
import com.travel.travtronics.service.EmpVerificationService;
import com.travel.travtronics.util.NotFoundException;


@RestController
@RequestMapping("/emp_verification")
public class EmpVerificationController {
	
	@Autowired
	EmpVerificationService empVerificationService;
	
	@PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
	public APIResponse createMultiVerfy(@RequestBody List<EmpVerification> models) {
		return empVerificationService.createMultiVerfy(models);
	}
	
	@GetMapping(value = "id", produces = "application/json")
	public APIResponse getVerfificationId(@RequestParam Long id) {
		return empVerificationService.getVerfificationId(id);
	}
	
	@GetMapping(value = "list", produces = "application/json")
	public APIResponse getAllVerfification(@RequestParam Long organizationId) {
		return empVerificationService.getAllVerfification(organizationId);
	}
	
	@GetMapping(value = "empbyrefid", produces = "application/json")
	public APIResponse getListByRefId(@RequestParam Long refId) {
		return empVerificationService.getListByRefId(refId);
	}
	
	@PutMapping(value = "edit", produces = "application/json")
	public APIResponse editByEmpVerification(@RequestBody List<EmpVerification> models) throws NotFoundException {
		return empVerificationService.editByEmpVerification(models);
	}
	
	@GetMapping(value = "/get-pagination")
    public APIResponsePaging getPagenationByOrganization(@RequestParam(required = false) Long organizationId, @RequestParam(required = false) Long refId,
                                                @RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize,
                                                @RequestParam(defaultValue = "id") String sortBy,@RequestParam(defaultValue = "asc", required = false) SortType sortType) {
        return  empVerificationService.getPagenationByOrganization(organizationId, refId, pageNo, pageSize, sortBy, sortType);
    }
}
