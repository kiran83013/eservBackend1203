package com.travel.travtronics.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
//import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.travel.travtronics.enums.SortType;
import com.travel.travtronics.eserv.model.PersonVerificationModel;
import com.travel.travtronics.eserv.model.TeamLeadertoTeam;
import com.travel.travtronics.eserv.repository.TeamLeadertoTeamRepository;
//import com.travel.bpf.response.MessageResponse;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.APIResponsePaging;



@Service
public class TeamLeadertoTeamService {
	
	@Autowired
	TeamLeadertoTeamRepository tlttRepository;

	public APIResponse createTeamLeader(TeamLeadertoTeam team) {
		List<TeamLeadertoTeam> list = new ArrayList<>();
		try {
			TeamLeadertoTeam save = tlttRepository.save(team);
			list.add(save);
			return new APIResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED.name(), list);
		}catch(Exception ex) {
			ex.printStackTrace();
			return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.name(),new ArrayList<>());
		}
	}

	public APIResponse getId(Long tId) {
		List<TeamLeadertoTeam> list = new ArrayList<>();
		try {
			Optional<TeamLeadertoTeam> opt = tlttRepository.findBytId(tId);
			if(opt.isPresent()) {
			list.add(opt.get());
			return new APIResponse(HttpStatus.OK.value(),HttpStatus.OK.name(), list);
		}else {
			return new APIResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), new ArrayList<>());
		}
	}catch(Exception ex) {
		ex.printStackTrace();
		return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.name(), new ArrayList<>());
	}
	}
	
	public APIResponse edit(TeamLeadertoTeam team) {
		List<TeamLeadertoTeam> list = new ArrayList<>();
		try {
			Optional<TeamLeadertoTeam> opt = tlttRepository.findBytId(team.gettId());
			if(opt.isPresent()) {
				team.setCreatedBy(opt.get().getCreatedBy());
				TeamLeadertoTeam save = tlttRepository.save(team);
				list.add(save);
				return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(),list);
			}else {
				return new APIResponse(HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND.name(),new ArrayList<>());
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name(),new ArrayList<>());
		}
	}

	public APIResponse getAll(String Organization) {
		try {
			List<Map<String, String>> list = tlttRepository.findByList(Organization);
			return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(),list);
		}catch(Exception ex) {
			ex.printStackTrace();
			return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.name(),new ArrayList<>());
		}
		}
//
//	public APIResponse getcreatedBy(Long createdBy) {
//		List<TeamLeadertoTeam> list = new ArrayList<>();
//		try {
//			Optional<TeamLeadertoTeam> opt = tlttRepository.findBycreatedBy(createdBy);
//			if(opt.isPresent()) {
//				list.add(opt.get());
//				return new APIResponse(HttpStatus.OK.value(),HttpStatus.OK.name(),list);
//			}else {
//				return new APIResponse(HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND.name(),new ArrayList<>());
//			}
//		}catch(Exception ex) {
//			ex.printStackTrace();
//			return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.name(),new ArrayList<>());
//		}
//	}
//
//
//
//	public MessageResponse deleteid(Long tId) {
//		Optional<TeamLeadertoTeam> opt = tlttRepository.findBytId(tId);
//
//		if (opt.isPresent()) {
//			tlttRepository.deleteById(tId);
//			return new MessageResponse(HttpStatus.OK.value(),
//					String.format("data with this id : %d was removed ", tId));
//		} else {
//			return new MessageResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name());
//		}
//	}
//
//	public MessageResponse deleteList() {
//		try {
//			tlttRepository.deleteAll();
//			return new MessageResponse(HttpStatus.OK.value(),String.format(" you have delete the list"));
//		}catch (Exception ex) {
//			return new MessageResponse(HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND.name());
//		}
//	}findByOrganizationId

	public APIResponsePaging getTeamLeaderToTeamPagenationByOrganization(String organization, int pageNo, int pageSize,String sortBy, SortType sortType) {
		Pageable pageable = PageRequest.of(pageNo, pageSize,
				sortType == SortType.asc ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
		if (organization != null && !organization.isEmpty()) {
			Page<TeamLeadertoTeam> findByOrganizationId = tlttRepository.findByOrganization(organization, pageable);
			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), findByOrganizationId.getContent(),
					new ArrayList<>(), findByOrganizationId.getNumber(), findByOrganizationId.getTotalElements(),
					findByOrganizationId.getTotalPages());
		} else {
			Page<TeamLeadertoTeam> findAll = tlttRepository.findAll(pageable);
			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), findAll.getContent(),
					new ArrayList<>(), findAll.getNumber(), findAll.getTotalElements(), findAll.getTotalPages());
		}

	}
	
}
	
