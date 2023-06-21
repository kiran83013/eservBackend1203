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
import com.travel.travtronics.eserv.model.EmpPid;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.APIResponsePaging;
import com.travel.travtronics.response.MessageResponse;
import com.travel.travtronics.service.EmpPidService;



@RestController
@RequestMapping("/emp_pid")
public class EmpPidController {
	
	@Autowired
	EmpPidService empPidService;
	
	@PostMapping(value = "create", consumes = "application/json", produces = "application/json")
	public APIResponse createPid(@RequestBody EmpPid model) {
		return empPidService.createPid(model);
	}
	
	@GetMapping(value = "id", produces = "application/json")
	public APIResponse getEmpPidId(@RequestParam Long id) {
		return empPidService.getEmpPidId(id);
	}
	
	@GetMapping(value = "emplistbyrefid", produces = "application/json")
	public APIResponse getListByEmpRefId(@RequestParam Long refId) {
		return empPidService.getListByEmpRefId(refId);
	}
	
	@PutMapping(value = "update", consumes = "application/json", produces = "application/json")
	public MessageResponse updateEmpPid(@RequestBody EmpPid model) {
		return empPidService.updateEmpPid(model);
	}
	@PostMapping(value = "/multipleempPid", consumes = "application/json", produces = "application/json")
	public APIResponse createMultipleEmpPids(@RequestBody List<EmpPid> models) {
		return empPidService.createMultipleEmpPids(models);
	}
	
	@GetMapping(value = "list", produces = "application/json")
	public APIResponse getEmpPid(@RequestParam Long organizationId) {
		return empPidService.getEmpPid(organizationId);
	}
	
	@GetMapping(value = "/get-pagination")
    public APIResponsePaging getPagenationByOrganization(@RequestParam(required = false) Long organizationId, @RequestParam(required = false) Long refId,
                                                @RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize,
                                                @RequestParam(defaultValue = "id") String sortBy,@RequestParam(defaultValue = "asc", required = false) SortType sortType) {
        return  empPidService.getPagenationByOrganization(organizationId, refId, pageNo, pageSize, sortBy, sortType);
    }

}
