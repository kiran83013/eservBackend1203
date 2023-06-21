package com.travel.travtronics.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
//import java.util.Map;
import java.util.Optional;

import com.travel.travtronics.enums.SortType;
import com.travel.travtronics.enums.StatusActive;
import com.travel.travtronics.eserv.model.PersonModel;
import com.travel.travtronics.eserv.model.Status;
import com.travel.travtronics.eserv.model.Team;
import com.travel.travtronics.eserv.repository.StatusRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
//import com.travel.bpf.response.MessageResponse;

import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.APIResponsePaging;

@Service
public class StatusService {
	
	@Autowired
	StatusRepository  sRepository;

	public APIResponse createstatus(Status status) {
		List<Status> list = new ArrayList<>();
		try {
			Status save = sRepository.save(status);
			list.add(save);
			return new APIResponse(HttpStatus.CREATED.value(),HttpStatus.CREATED.name(),list);
		}catch(Exception ex) {
			ex.printStackTrace();
			return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.name(),new ArrayList<>()); 
		}
	}

	public APIResponse getId(Long statusId) {
		List<Status> list = new ArrayList<>();
		try {
			Optional<Status>opt = sRepository.findBystatusId(statusId);
			if(opt.isPresent()) {
				list.add(opt.get());
				return new APIResponse(HttpStatus.OK.value(),HttpStatus.OK.name(),list);
			}else {
				return new APIResponse(HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND.name(),new ArrayList<>());
			}
			}catch(Exception ex) {
				ex.printStackTrace();
				return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name(),new ArrayList<>());
		}
	}
	
	public APIResponse edit(Status status) {
		List<Status> list = new ArrayList<>();
		try {
			Optional<Status> opt = sRepository.findBystatusId(status.getStatusId());
			if(opt.isPresent()) {
				status.setCreatedBy(opt.get().getCreatedBy());
				Status save = sRepository.save(status);
				list.add(save);
				return new APIResponse(HttpStatus.OK.value(),HttpStatus.OK.name(), list);
				}else {
					return new APIResponse(HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND.name(),new ArrayList<>());
				}
			}catch(Exception ex) {
				return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.name(), new ArrayList<>());
			}
	}

	public APIResponse getAll(String organization) {
		try {
			List<Map<String, String>> list = sRepository.findAllList(organization);
			return new APIResponse(HttpStatus.OK.value(),HttpStatus.OK.name(),list);
		}catch(Exception ex) {
			ex.printStackTrace();
			return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name(), new ArrayList<>());
		}
	}
	public APIResponse getStatusByOrganization(String organizationId, String module) {
		try {
			List<Status> list =sRepository.findByOrganizationAndModuleAndStatus(organizationId,module, StatusActive.Active);
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

	public APIResponsePaging getStatusPagenationByOrganization(String organization, int pageNo, int pageSize, String sortBy, SortType sortType) {
		Pageable pageable = PageRequest.of(pageNo, pageSize,
				sortType == SortType.asc ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
		if (organization != null && !organization.isEmpty()) {
			Page<Status> findByOrganizationId = sRepository.findByOrganization(organization, pageable);
			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), findByOrganizationId.getContent(),
					new ArrayList<>(), findByOrganizationId.getNumber(), findByOrganizationId.getTotalElements(),
					findByOrganizationId.getTotalPages());
		} else {
			Page<Status> findAll = sRepository.findAll(pageable);
			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), findAll.getContent(),
					new ArrayList<>(), findAll.getNumber(), findAll.getTotalElements(), findAll.getTotalPages());
		}
	}

//	public APIResponse getName(String name) {
//		List<Status> list = new ArrayList<>();
//		try {
//			Optional<Status>opt = sRepository.findByname(name);
//			if(opt.isPresent()) {
//				list.add(opt.get());
//				return new APIResponse(HttpStatus.OK.value(),HttpStatus.OK.name(),list);
//			}else {
//				return new APIResponse(HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND.name(),new ArrayList<>());
//			}
//			}catch(Exception ex) {
//				ex.printStackTrace();
//				return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name(),new ArrayList<>());
//		}
//	}
//
//	public APIResponse getcreatedBy(String createdBy) {
//		List<Status> list = new ArrayList<>();
//		try {
//			Optional<Status>opt = sRepository.findBycreatedBy(createdBy);
//			if(opt.isPresent()) {
//				list.add(opt.get());
//				return new APIResponse(HttpStatus.OK.value(),HttpStatus.OK.name(),list);
//			}else {
//				return new APIResponse(HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND.name(),new ArrayList<>());
//			}
//			}catch(Exception ex) {
//				ex.printStackTrace();
//				return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name(),new ArrayList<>());
//		}
//
//	}
//
//	
//
//	public APIResponse names(String name) {
//		try {
//			List<Map<String, Object>> list = sRepository.findBynames(name);
//			if(!list.isEmpty()) {
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
//	public MessageResponse deleteid(Long statusId) {
//		Optional<Status> opt= sRepository.findBystatusId(statusId);
//		if(opt.isPresent()) {
//			sRepository.deleteById(statusId);
//			return new MessageResponse(HttpStatus.OK.value(), String.format("data with this id : %d was removed", statusId));
//		}else {
//			return new MessageResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name());
//		}
//	}
//
//	public MessageResponse deleteList() {
//		try {
//			sRepository.deleteAll();
//			return new MessageResponse(HttpStatus.OK.value(),String.format(" you have delete the list"));
//		}catch (Exception ex) {
//			return new MessageResponse(HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND.name());
//		}
//	}
//	

}
