package com.travel.travtronics.controller;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.travel.travtronics.enums.SortType;
import com.travel.travtronics.eserv.model.Modules;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.APIResponsePaging;
import com.travel.travtronics.service.ModulesService;


@RestController
@RequestMapping("/Modules")
public class ModulesController {
	
	@Autowired
	ModulesService mService;
	
	@PostMapping(value ="/create",consumes = "application/json",produces ="application/json")
	public APIResponse create(@RequestBody Modules modules) {
		return mService.create(modules);
	}
	
	@GetMapping(value="/id", produces ="application/json")
	public APIResponse getmoduleId(@RequestParam Long moduleId) {
		return mService.getmoduleId(moduleId);
	}
	
	@PutMapping(value="/edit", consumes= "application/json", produces ="application/json")
	public APIResponse edit(@RequestBody Modules modules) {
		return mService.edit(modules);
	}
	
	@GetMapping(value="/list",produces = "application/json")
	public APIResponse getlist() {
		return mService.getlist();
	}
	
	@GetMapping(value = "/list-module-pagination")
	public APIResponsePaging getModulePagenationByOrganization(
			@RequestParam(required = false) Long moduleId, @RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "10") int pageSize, @RequestParam(defaultValue = "moduleId") String sortBy,
			@RequestParam(defaultValue = "asc", required = false) SortType sortType) {
		return mService.getModulePagenationByOrganization(moduleId, pageNo, pageSize, sortBy,
				sortType);
	}
	
//	@GetMapping(value="/name", produces ="application/json")
//	public APIResponse getname(@RequestParam String name) {
//		return mService.getname(name);
//	}
//	
//	@GetMapping(value="/creatdby", produces ="application/json")
//	public APIResponse getcreatedBy(@RequestParam String createdBy) {
//		return mService.getcreatedBy(createdBy);
//	}
//	
//	
//	
//	@GetMapping(value = "/allnames", produces="application/json")
//	public APIResponse names(@RequestParam String name){
//		return mService.names(name);
//	}
//	
//	@DeleteMapping(value = "/deleteid", produces ="application/json")
//	public MessageResponse deleteid(@RequestParam Long moduleId) {
//		return mService.deleteid(moduleId);
//	}
//	
//	@DeleteMapping(value = "/deletelist")
//	public MessageResponse deleteList() {
//		return mService.deleteList();
//	}
}
