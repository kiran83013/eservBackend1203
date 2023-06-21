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
import com.travel.travtronics.eserv.model.EmpSocial;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.APIResponsePaging;
import com.travel.travtronics.response.MessageResponse;
import com.travel.travtronics.service.EmpSocialService;

@RestController
@RequestMapping("/emp_social")
public class EmpSocialController {
	
	@Autowired
	EmpSocialService empSocialService;
	
	@PostMapping(value = "create", consumes = "application/json", produces = "application/json")
	public APIResponse createSocial(@RequestBody EmpSocial model) {
		return empSocialService.createEmpSocial(model);
	}
	
	@GetMapping(value = "id", produces = "application/json")
	public APIResponse getEmpSocialId(@RequestParam Long id) {
		return empSocialService.getEmpSocialId(id);
	}
	
	@GetMapping(value = "list", produces = "application/json")
	public APIResponse getEmpSocialList(@RequestParam Long organizationId) {
		return empSocialService.getEmpSocialList(organizationId);
	}
	
	@GetMapping(value = "empbyrefid", produces = "application/json")
	public APIResponse getByEmpSocialRefId(@RequestParam Long refId) {
		return empSocialService.getByEmpSocialRefId(refId);
	}
	
	@PutMapping(value = "update", consumes = "application/json", produces = "application/json")
	public MessageResponse updateEmpSocial(@RequestBody EmpSocial model) {
		return empSocialService.updateEmpSocial(model);
	}
	
	@PostMapping(value = "/multipleSocial", consumes = "application/json", produces = "application/json")
	public APIResponse createMultipleEmpSocials(@RequestBody List<EmpSocial> model) {
		return empSocialService.createMultipleEmpSocials(model);
	}
	
	@GetMapping(value = "/get-pagination")
    public APIResponsePaging getPagenationByOrganization(@RequestParam(required = false) Long organizationId, @RequestParam(required = false) Long refId,
                                                @RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize,
                                                @RequestParam(defaultValue = "id") String sortBy,@RequestParam(defaultValue = "asc", required = false) SortType sortType) {
        return  empSocialService.getPagenationByOrganization(organizationId, refId, pageNo, pageSize, sortBy, sortType);
    }
}
