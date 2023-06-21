package com.travel.travtronics.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
//import com.travel.bpf.response.MessageResponse;

import com.travel.travtronics.enums.SortType;
import com.travel.travtronics.eserv.model.MembertoTeam;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.APIResponsePaging;
import com.travel.travtronics.service.MembertoTeamService;


@RestController
@RequestMapping("/member_to_team")
public class MembertoTeamController {
	
	@Autowired
	MembertoTeamService mtService;
	
	@PostMapping(value ="/create", consumes ="application/json",produces = "application/json")
	public APIResponse createMembertoTeam(@RequestBody List<MembertoTeam>  mtteam) {
		return mtService.createMembertoTeam(mtteam);
	}
	
	@GetMapping(value ="/id", produces = "application/json")
	public APIResponse getMtoTId(@RequestParam Long MtoTId) {
		return mtService.getMtoTId(MtoTId);
	}
	
	@PutMapping(value ="/edit", consumes ="application/json",produces = "application/json")
	public APIResponse edit(@RequestBody MembertoTeam  mtteam) {
		return mtService.getedit(mtteam);

	}
	
	@PutMapping(value ="/editAndSave", consumes ="application/json",produces = "application/json")
	public APIResponse updateAndSave(@RequestBody List<MembertoTeam>  mtteam) {
		return mtService.updateAndSave(mtteam);

	}
	
	@GetMapping(value = "/list", produces = "application/json")
	public  APIResponse getAll(@RequestParam Long organizationId ) {
		return mtService.getAll(organizationId); 
	}
	
	@GetMapping(value ="/teamid", produces = "application/json")
	public APIResponse getTeamId(@RequestParam Long teamId) {
		return mtService.getTeamId(teamId);
	}
	
	@GetMapping(value = "/list-pagination")
	public APIResponsePaging getPagenationByOrganization(@RequestParam(required = false) Long organizationId,
			@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize,
			@RequestParam(defaultValue = "mtoTId") String sortBy,
			@RequestParam(defaultValue = "asc", required = false) SortType sortType) {
		return mtService.getPaginationByOrganization(organizationId, pageNo, pageSize, sortBy, sortType);
	}
	
//	@GetMapping(value ="/name", produces = "application/json")
//	public APIResponse getUserName(@RequestParam String userName) {
//		return mtService.getuserName(userName);
//	}
	
//	@GetMapping(value ="/createdby", produces = "application/json")
//	public APIResponse getcreatedBy(@RequestParam String createdBy) {
//		return mtService.getcreatedBy(createdBy);
//	}
//	
	
	
//	@GetMapping(value = "/allnames", produces="application/json")
//	public APIResponse names(@RequestParam String userName){
//		return mtService.names(userName);
//	}
//	
//	@DeleteMapping(value = "/deleteid", produces ="application/json")
//	public MessageResponse deleteid(@RequestParam Long userId) {
//		return mtService.deleteid(userId);
//	}
//	
//	@DeleteMapping(value = "/deletelist")
//	public MessageResponse deleteList() {
//		return mtService.deleteList();
//	}

}
