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
import com.travel.travtronics.eserv.model.Team;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.APIResponsePaging;
import com.travel.travtronics.service.TeamService;


@RestController
@RequestMapping("/team")
public class TeamController {
	
	@Autowired
	TeamService  tService;
	
	@PostMapping(value ="/create", consumes ="application/json",produces = "application/json")
	public APIResponse createTeam(@RequestBody Team  team) {
		return tService.createTeam(team);
	}
	
	@GetMapping(value ="/id", produces = "application/json")
	public APIResponse getteamId(@RequestParam Long teamId) {
		return tService.getteamId(teamId);
	}
	
	@PutMapping(value ="/edit", consumes ="application/json",produces = "application/json")
	public APIResponse edit(@RequestBody Team  team) {
		return tService.getedit(team);

	}
	
	@GetMapping(value = "/list", produces = "application/json")
	public  APIResponse getAll(@RequestParam Long organizationId) {
		return tService.getAll(organizationId); 
	}
	@GetMapping(value = "/list-by-organization", produces = "application/json")
	public  APIResponse getTeamsByOrganizationAndModule(@RequestParam Long organization, @RequestParam Long moduleId) {
		return tService.getTeamsByOrganizationAndModule(organization, moduleId);
	}
	
	@GetMapping(value = "/list-team-pagination")
	public APIResponsePaging getTeamPagenationByOrganization(
			@RequestParam(required = false) Long organizationId, @RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "10") int pageSize, @RequestParam(defaultValue = "teamId") String sortBy,
			@RequestParam(defaultValue = "asc", required = false) SortType sortType) {
		return tService.getTeamPagenationByOrganization(organizationId, pageNo, pageSize, sortBy,
				sortType);
	}
	
//	@GetMapping(value ="/name", produces = "application/json")
//	public APIResponse getTeamName(@RequestParam String teamName) {
//		return tService.getTeamName(teamName);
//	}
//	
//	@GetMapping(value ="/createdby", produces = "application/json")
//	public APIResponse getcreatedBy(@RequestParam String createdBy) {
//		return tService.getcreatedBy(createdBy);
//	}
//	
//	
//	
//	@GetMapping(value = "/allnames", produces="application/json")
//	public APIResponse names(@RequestParam String teamName){
//		return tService.names(teamName);
//	}
//	
//	@DeleteMapping(value = "/deleteid", produces ="application/json")
//	public MessageResponse deleteid(@RequestParam Long teamId) {
//		return tService.deleteid(teamId);
//	}
//	
//	@DeleteMapping(value = "/deletelist")
//	public MessageResponse deleteList() {
//		return tService.deleteList();
//	}
//	

}
