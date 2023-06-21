package com.travel.travtronics.service;

import com.travel.travtronics.enums.SortType;
import com.travel.travtronics.enums.StatusActive;
import com.travel.travtronics.eserv.model.PersonVerificationModel;
import com.travel.travtronics.eserv.model.Team;
import com.travel.travtronics.eserv.repository.TeamRepository;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.APIResponsePaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;



@Service
public class TeamService {
	
	@Autowired
	TeamRepository  tRepository;

	public APIResponse createTeam(Team team) {
		List<Team> list = new ArrayList<>(); 
		try {
			Optional<Team> findname = tRepository.findByTeamName(team.getTeamName());
			if (findname.isPresent()) {
				return new APIResponse(HttpStatus.CONFLICT.value(), "Team name is already exists", new ArrayList<>());
			} else {
				Team save = tRepository.save(team);
				list.add(save);
				return new APIResponse(HttpStatus.CREATED.value(),HttpStatus.CREATED.name(), list);
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
			return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.name(),new ArrayList<>());
		}
	}

	public APIResponse getteamId(Long teamId) {
		List<Team> list = new ArrayList<>(); 
		try {
			Optional<Team> opt = tRepository.findByTeamId(teamId);
			if(opt .isPresent()) {
				list.add(opt.get());
				return new APIResponse(HttpStatus.OK.value(),HttpStatus.OK.name(),list);
			}else {
				return new APIResponse(HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND.name(),new ArrayList<>());
			}
		}catch (Exception ex) {
			ex.printStackTrace();
			return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.name(),new ArrayList<>());
		}
	}
	
	public APIResponse getedit(Team team) {
		List<Team> list = new ArrayList<>(); 
		try {
			Optional<Team> opt = tRepository.findByTeamId(team.getTeamId());
			if(opt.isPresent()) {
				team.setCreatedBy(opt.get().getCreatedBy());
				Team save = tRepository.save(team);
				list.add(save);
				return new APIResponse(HttpStatus.OK.value(),HttpStatus.OK.name(), list);
			}else {
				return new APIResponse(HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND.name(),new ArrayList<>());
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
			return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.name(),new ArrayList<>());
		}
	}

	public APIResponse getAll(Long organizationId) {
		try {
			List<Map<String, String>> list =tRepository.findByList(organizationId);
			return new APIResponse(HttpStatus.OK.value(),HttpStatus.OK.name(), list);
		}catch (Exception ex) {
			ex.printStackTrace();
			return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.name(),new ArrayList<>());
		} 
			

	}

	public APIResponse getTeamsByOrganizationAndModule(Long organizationId, Long moduleId) {
		try {
			List<Team> list =tRepository.findByOrganizationIdAndModuleIdAndStatus(organizationId, moduleId,StatusActive.Active);
			if(list.isEmpty()) {
				return new APIResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.OK.name(), new ArrayList<>());
			} else {
				return new APIResponse(HttpStatus.OK.value(),HttpStatus.OK.name(), list);
			}

		}catch (Exception ex) {
			ex.printStackTrace();
			return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.name(),new ArrayList<>());
		}


	}

	public APIResponsePaging getTeamPagenationByOrganization(Long organizationId, int pageNo, int pageSize,
			String sortBy, SortType sortType) {
		Pageable pageable = PageRequest.of(pageNo, pageSize,
				sortType == SortType.asc ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
		if (organizationId != null) {
		Page<Team> findByOrganizationId = tRepository.findByOrganizationId(organizationId, pageable);
			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), findByOrganizationId.getContent(),
					new ArrayList<>(), findByOrganizationId.getNumber(), findByOrganizationId.getTotalElements(),
					findByOrganizationId.getTotalPages());
		} else {
		Page<Team> findAll = tRepository.findAll(pageable);
			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), findAll.getContent(),
					new ArrayList<>(), findAll.getNumber(), findAll.getTotalElements(), findAll.getTotalPages());
		}

	}

//	public APIResponse getTeamName(String teamName) {
//		List<Team> list = new ArrayList<>(); 
//		try {
//			Optional<Team> opt = tRepository.findByTeamName(teamName);
//			if(opt .isPresent()) {
//				list.add(opt.get());
//				return new APIResponse(HttpStatus.OK.value(),HttpStatus.OK.name(),list);
//			}else {
//				return new APIResponse(HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND.name(),new ArrayList<>());
//			}
//		}catch (Exception ex) {
//			ex.printStackTrace();
//			return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.name(),new ArrayList<>());
//		}
//	}
//
//	public APIResponse getcreatedBy(String createdBy) {
//		List<Team> list = new ArrayList<>(); 
//		try {
//			Optional<Team> opt = tRepository.findBycreatedBy(createdBy);
//			if(opt .isPresent()) {
//				list.add(opt.get());
//				return new APIResponse(HttpStatus.OK.value(),HttpStatus.OK.name(),list);
//			}else {
//				return new APIResponse(HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND.name(),new ArrayList<>());
//			}
//		}catch (Exception ex) {
//			ex.printStackTrace();
//			return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.name(),new ArrayList<>());
//		}
//	}
//
//	
//
//	public APIResponse names(String teamName) {
//		try {
//			List<Map<String, Object>> list = tRepository.findByteamNames(teamName);
//			if(!list.isEmpty()) {
//				return new APIResponse(HttpStatus.OK.value(),HttpStatus.OK.name(),list);
//			}else {
//				return new APIResponse(HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND.name(),new ArrayList<>());
//			}
//		}		catch (Exception ex) {
//				ex.printStackTrace();
//				return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.NOT_FOUND.name(),new ArrayList<>());
//	}
//	}
//
//	public MessageResponse deleteid(Long teamId) {
//		Optional<Team> opt = tRepository.findByTeamId(teamId);
//		if(opt.isPresent()) {
//			tRepository.deleteById(teamId);
//			return new MessageResponse(HttpStatus.OK.value(),String.format("data with this id : %d was removed ",teamId));
//	}else {
//		return new MessageResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name());
//	}
//	}
//
//	public MessageResponse deleteList() {
//		try {
//			tRepository.deleteAll();
//			return new MessageResponse(HttpStatus.OK.value(),String.format("you have delete all the list"));
//		}catch (Exception ex) {
//			ex.printStackTrace();
//			return new MessageResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name());
//		}
//	}

}
