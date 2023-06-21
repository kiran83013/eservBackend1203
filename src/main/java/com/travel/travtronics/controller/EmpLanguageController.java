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
import com.travel.travtronics.eserv.model.EmpLanguage;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.APIResponsePaging;
import com.travel.travtronics.service.EmpLanguageService;
import com.travel.travtronics.util.NotFoundException;


@RestController
@RequestMapping("/emp_language")
public class EmpLanguageController {
	
	@Autowired
	EmpLanguageService empLanguageService;
	
	@PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
	public APIResponse createMultiLan(@RequestBody List<EmpLanguage> models) {
		return empLanguageService.createMultiLan(models);
	}
	
	@GetMapping(value = "id", produces = "application/json")
	public APIResponse getLangId(@RequestParam Long id) {
		return empLanguageService.getLangId(id);
	}
	
	@GetMapping(value = "list", produces = "application/json")
	public APIResponse getAlLang(@RequestParam Long organizationId) {
		return empLanguageService.getAllLang(organizationId);
	}
	
	@GetMapping(value = "emplistbyrefid", produces = "application/json")
	public APIResponse getListByRefId(@RequestParam Long refId) {
		return empLanguageService.getListByRefId(refId);
	}
	
	@PutMapping(value = "edit", produces = "application/json")
	public APIResponse editByLang(@RequestBody List<EmpLanguage> models) throws NotFoundException {
		return empLanguageService.editByLang(models);
	}
	
	@GetMapping(value = "/get-pagination")
    public APIResponsePaging getPagenationByOrganization(@RequestParam(required = false) Long organizationId, @RequestParam(required = false) Long refId,
                                                @RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize,
                                                @RequestParam(defaultValue = "id") String sortBy,@RequestParam(defaultValue = "asc", required = false) SortType sortType) {
        return  empLanguageService.getPagenationByOrganization(organizationId, refId, pageNo, pageSize, sortBy, sortType);
    }


}
