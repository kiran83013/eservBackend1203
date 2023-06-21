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
import com.travel.travtronics.eserv.model.TeamLeadertoTeam;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.APIResponsePaging;
import com.travel.travtronics.service.TeamLeadertoTeamService;


@RestController
@RequestMapping("/team_leader_to_team")
public class TeamLeadertoTeamController {
	
	@Autowired
	TeamLeadertoTeamService tlttService;
	
	@PostMapping(value = "/create", consumes ="application/json", produces ="application/json")
	public APIResponse createTeamLeader(@RequestBody TeamLeadertoTeam team ) {
		return tlttService. createTeamLeader(team);
	}
	
	@GetMapping(value = "/id", produces = "application/json")
	public APIResponse getId(@RequestParam Long tId) {
		return tlttService.getId(tId);
	}
	
	@PutMapping(value= "/edit", consumes = "application/json", produces = "application/json")
	public APIResponse edit(@RequestBody TeamLeadertoTeam team) {
		return tlttService.edit(team);
	}
	
	@GetMapping(value ="/list", produces = "application/json")
	public APIResponse getAll(@RequestParam String organization) {
		return tlttService.getAll(organization);
	}
	
	@GetMapping(value = "/list-teamleader-to-team-pagination")
	public APIResponsePaging getTeamLeaderToTeamPagenationByOrganization(
			@RequestParam(required = false) String organization, @RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "10") int pageSize, @RequestParam(defaultValue = "tId") String sortBy,
			@RequestParam(defaultValue = "asc", required = false) SortType sortType) {
		return tlttService.getTeamLeaderToTeamPagenationByOrganization(organization, pageNo, pageSize, sortBy,
				sortType);
	}


//	@GetMapping(value = "/createdby", produces = "application/json")
//	public APIResponse getcreatedBy(@RequestParam Long createdBy) {
//		return tlttService.getcreatedBy(createdBy);
//	}
//	
//	
//	
//	@DeleteMapping(value = "/deleteid", produces ="application/json")
//	public MessageResponse deleteid(@RequestParam Long tLtTId) {
//		return tlttService.deleteid(tLtTId);
//	}
//	
//	@DeleteMapping(value = "/deletelist")
//	public MessageResponse deleteList() {
//		return tlttService.deleteList();
//	}

}
