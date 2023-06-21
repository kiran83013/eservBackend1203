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
import com.travel.travtronics.eserv.model.EmpQualification;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.APIResponsePaging;
import com.travel.travtronics.response.MessageResponse;
import com.travel.travtronics.service.EmpQualificationService;


@RestController
@RequestMapping("/emp_qualification")
public class EmpQualificationController {
	
	@Autowired
	EmpQualificationService empQualificationService;
	

	@PostMapping(value = "/createqualification", consumes = "application/json", produces = "application/json")
	public APIResponse createEmpQualification(@RequestBody List<EmpQualification> models) {
		return empQualificationService.createEmpQualification(models);
	}
	
	@GetMapping(value = "/list", produces = "application/json")
	public APIResponse getEmpQualificationList(@RequestParam Long organizationId) {
		return empQualificationService.getEmpQualificationList(organizationId);

	}
	
	@GetMapping(value = "/id", produces = "application/json")
	public APIResponse getEmpQualificationById(@RequestParam Long id) {
		return empQualificationService.getEmpQualificationById(id);
	}
	
	@PutMapping(value = "update", consumes = "application/json", produces = "application/json")
	public MessageResponse updateEmpQualification(@RequestBody EmpQualification model) {
		return empQualificationService.updateEmpQualification(model);
	}
	
	@GetMapping(value = "/refid", produces = "application/json")
	public APIResponse getEmpQualificationByRefId(@RequestParam Long refId) {
		return empQualificationService.getEmpQualificationByRefId(refId);
	}
	
	@GetMapping(value = "/get-pagination")
    public APIResponsePaging getPagenationByOrganization(@RequestParam(required = false) Long organizationId, @RequestParam(required = false) Long refId,
                                                @RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize,
                                                @RequestParam(defaultValue = "id") String sortBy,@RequestParam(defaultValue = "asc", required = false) SortType sortType) {
        return  empQualificationService.getPagenationByOrganization(organizationId, refId, pageNo, pageSize, sortBy, sortType);
    }

}
