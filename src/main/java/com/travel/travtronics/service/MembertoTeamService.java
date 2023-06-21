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
//import com.travel.bpf.response.MessageResponse;

import com.travel.travtronics.enums.SortType;
import com.travel.travtronics.eserv.model.Bank;
import com.travel.travtronics.eserv.model.MembertoTeam;
import com.travel.travtronics.eserv.repository.MembertoTeamRepository;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.APIResponsePaging;


@Service
public class MembertoTeamService {
	
	@Autowired
	MembertoTeamRepository  mtRepository;

	public APIResponse createMembertoTeam(List<MembertoTeam> mtteam) {
		List<MembertoTeam> list = new ArrayList<>(); 
		try {
			List<MembertoTeam> save = mtRepository.saveAll(mtteam);
			list.addAll(save);
			return new APIResponse(HttpStatus.CREATED.value(),HttpStatus.CREATED.name(), list);
		}catch(Exception ex) {
			ex.printStackTrace();
			return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.name(),new ArrayList<>());
		}
	}

	public APIResponse getMtoTId(Long mtoTId) {
		List<MembertoTeam> list = new ArrayList<>(); 
		try {
			Optional<MembertoTeam> opt = mtRepository.findByMtoTId(mtoTId);
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
	
	public APIResponse getedit(MembertoTeam mtteam) {
		List<MembertoTeam> list = new ArrayList<>(); 
		try {
			Optional<MembertoTeam> opt =mtRepository.findByMtoTId(mtteam.getMtoTId());
			if(opt.isPresent()) {
			mtteam.setCreatedBy(opt.get().getCreatedBy());
			MembertoTeam save = mtRepository.save(mtteam);
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
			List<Map<String,String>> list = mtRepository.findByOrganizationId(organizationId);
			return new APIResponse(HttpStatus.OK.value(),HttpStatus.OK.name(), list);
	}catch(Exception ex) {
		ex.printStackTrace();
		return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.name(),new ArrayList<>());
	}
	}

	public APIResponse getTeamId(Long teamId) {
		try {
			List<Map<String, String>> list = mtRepository.findByTeamId(teamId);
			if(!list.isEmpty()) {
				return new APIResponse(HttpStatus.OK.value(),HttpStatus.OK.name(),list);
			}else {
				return new APIResponse(HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND.name(),new ArrayList<>());
			}
		}catch (Exception ex) {
			ex.printStackTrace();
			return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.name(),new ArrayList<>());
		}
	}

	public APIResponse updateAndSave(List<MembertoTeam> mtteam) {
		List<MembertoTeam> list = mtRepository.saveAll(mtteam);
		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), list);
	}

	public APIResponsePaging getPaginationByOrganization(Long organizationId, int pageNo, int pageSize, String sortBy,SortType sortType) {
		Pageable pageable = PageRequest.of(pageNo, pageSize,
				sortType == SortType.asc ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
		if (organizationId != null) {
			Page<MembertoTeam> findByOrganizationId = mtRepository.findByOrganizationId(organizationId, pageable);
			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), findByOrganizationId.getContent(),
					new ArrayList<>(), findByOrganizationId.getNumber(), findByOrganizationId.getTotalElements(),
					findByOrganizationId.getTotalPages());
		} else {
			Page<MembertoTeam> findAll = mtRepository.findAll(pageable);
			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), findAll.getContent(),
					new ArrayList<>(), findAll.getNumber(), findAll.getTotalElements(), findAll.getTotalPages());
		}
	}
	
	

	
	

//	public APIResponse getuserName(String userName) {
//		List<MembertoTeam> list = new ArrayList<>(); 
//		try {
//			Optional<MembertoTeam> opt = mtRepository.findByUserName(userName);
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

//	public APIResponse getcreatedBy(String createdBy) {
//		List<MembertoTeam> list = new ArrayList<>(); 
//		try {
//			Optional<MembertoTeam> opt = mtRepository.findByCreatedBy(createdBy);
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

	

//	public APIResponse names(String userName) {
//		try {
//			List<Map<String, Object>> list = mtRepository.findByUserNames(userName);
//			if(!list.isEmpty()) {
//				return new APIResponse(HttpStatus.OK.value(),HttpStatus.OK.name(),list);
//			}else {
//				return new APIResponse(HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND.name(),new ArrayList<>());
//			}
//		}		catch (Exception ex) {
//				ex.printStackTrace();
//				return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.name(),new ArrayList<>());
//		}
//	}

//	public MessageResponse deleteid(Long mtoTId) {
//		Optional<MembertoTeam> opt = mtRepository.findByMtoTId(mtoTId);
//		if(opt.isPresent()) {
//			mtRepository.deleteById(mtoTId);
//			return new MessageResponse(HttpStatus.OK.value(),String.format("data with this id : %d was removed ",mtoTId));
//	}else {
//		return new MessageResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name());
//	}
//	}
//
//	public MessageResponse deleteList() {
//		try {
//			mtRepository.deleteAll();
//			return new MessageResponse(HttpStatus.OK.value(),String.format("you have delete all the list"));
//		}catch (Exception ex) {
//			ex.printStackTrace();
//			return new MessageResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name());
//		}
//	}

}
