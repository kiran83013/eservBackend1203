package com.travel.travtronics.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.travel.travtronics.eserv.model.CustomerInfoModel;
import com.travel.travtronics.request.PersonContactRequest;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.StatusTeamResponse;
import com.travel.travtronics.service.CustomerService;
import com.travel.travtronics.service.PersonService;
import com.travel.travtronics.service.StatusOwnerMappingService;
import com.travel.travtronics.util.ConflictException;

@RestController
@RequestMapping("lead")
public class LeadController {

	@Autowired
	CustomerService customerService;

	@Autowired
	PersonService personService;

	@Autowired
	StatusOwnerMappingService statusOwnerMappingService;

	@PostMapping("/leadCustomer")
	public ResponseEntity<CustomerInfoModel> saveCustomerInfoLead(@RequestBody CustomerInfoModel info)
			throws ConflictException {
		return customerService.saveCustomerInfoLead(info);
	}

	@PostMapping(value = "/person", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> person(@RequestBody PersonContactRequest personContactModel) throws Exception {
		APIResponse person = personService.personContactLead(personContactModel);

		return new ResponseEntity<APIResponse>(person, HttpStatus.OK);

	}

	@GetMapping(value = "/team-status", produces = "application/json")
	public ResponseEntity<?> getTeamAndStatusInfo(@RequestParam Long deafultStatus, @RequestParam Long organization,
			@RequestParam Long module) {
		APIResponse teamAndStatusInfoDetails = statusOwnerMappingService.getTeamInfo(deafultStatus, organization,
				module);

		ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		StatusTeamResponse statusTeamResponse = mapper.convertValue(teamAndStatusInfoDetails.getData().get(0),
				StatusTeamResponse.class);

		Map<String, Integer> map = new HashMap<>();
		map.put("teamId", statusTeamResponse.getTeam().get(0).getTeamId());
		map.put("statusId", statusTeamResponse.getStatus().getStatusId());
		map.put("wiw", statusTeamResponse.getUsers().stream().filter(u -> u.getIsTeamLeader() == true).findFirst().get()
				.getUserId());
		return new ResponseEntity<>(map, HttpStatus.OK);

	}
	
	

}
