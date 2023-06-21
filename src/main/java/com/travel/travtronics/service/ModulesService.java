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
import com.travel.travtronics.eserv.model.Modules;
import com.travel.travtronics.eserv.model.PersonModel;
import com.travel.travtronics.eserv.repository.ModulesRepository;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.APIResponsePaging;


//import com.travel.bpf.response.MessageResponse;



@Service
public class ModulesService {
	
	@Autowired
	ModulesRepository mRepository;

	public APIResponse create(Modules modules) {
		List<Modules> list =new ArrayList<>();
		try {
			Modules save = mRepository.save(modules);
			list.add(save);
			return new APIResponse(HttpStatus.CREATED.value(),HttpStatus.CREATED.name(),list);
		}catch(Exception ex) {
			ex.printStackTrace();
			return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.name(), new ArrayList<>());
		}
	}

	public APIResponse getmoduleId(Long moduleId) {
		List<Modules> list = new ArrayList<>();
		try {
			Optional<Modules> opt = mRepository.findBymoduleId(moduleId);
			if(opt.isPresent()) {
				list.add(opt.get());
				return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), list);
			}else {
				return new APIResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), new ArrayList<>());
			}
		}catch (Exception ex) {
			ex.printStackTrace();
			return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name(), new ArrayList<>());
		}
	}
	
	public APIResponse edit(Modules modules) {
		List<Modules> list =new ArrayList<>();
		try {
			Optional<Modules> opt =mRepository.findBymoduleId(modules.getModuleId());
			if(opt.isPresent()) {
				modules.setCreatedBy(opt.get().getCreatedBy());
				Modules save = mRepository.save(modules); 
				list.add(save);
				return  new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(),list);
			}else {
				return new APIResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), new ArrayList<>());
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name(), new ArrayList<>());
		}
	}

	public APIResponse getlist() {
		try {
			List<Map<Object, Object>> list =mRepository.findListAll();
		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), list);
	}catch (Exception ex) {
		ex.printStackTrace();
		return new APIResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), new ArrayList<>());
		}
	}

	public APIResponsePaging getModulePagenationByOrganization( Long moduleId, int pageNo, int pageSize,String sortBy, SortType sortType) {
		Pageable pageable = PageRequest.of(pageNo, pageSize,
				sortType == SortType.asc ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
		if (moduleId != null) {
			Page<Modules> findByOrganizationId = mRepository.findByModuleId(moduleId,pageable);
			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), findByOrganizationId.getContent(),
					new ArrayList<>(), findByOrganizationId.getNumber(), findByOrganizationId.getTotalElements(),
					findByOrganizationId.getTotalPages());
		} else {
			Page<Modules> findAll = mRepository.findAll(pageable);
			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), findAll.getContent(),
					new ArrayList<>(), findAll.getNumber(), findAll.getTotalElements(), findAll.getTotalPages());
		}
	}

//	public APIResponse getname(String name) {
//		List<Modules> list = new ArrayList<>();
//		try {
//			Optional<Modules>opt = mRepository.findByModuleName(name);
//			if(opt.isPresent()) {
//				list.add(opt.get());
//				return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), list);
//			}else {
//				return new APIResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), new ArrayList<>());
//			}
//		}catch(Exception ex) {
//			ex.printStackTrace();
//			return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.name(),new ArrayList<>());
//		}
//	}
//
//	public APIResponse getcreatedBy(String createdBy) {
//		List<Modules> list = new ArrayList<>();
//		try {
//			Optional<Modules> opt = mRepository.findBycreatedBy(createdBy);
//			if(opt.isPresent()) {
//				list.add(opt.get());
//				return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), list);
//			}else {
//				return new APIResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), new ArrayList<>());
//			}
//		}catch(Exception ex) {
//			ex.printStackTrace();
//			return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name(), new ArrayList<>());
//		}
//	}
//
//	
//
//	public APIResponse names(String name) {
//		try {
//			List<Map<String,Object>> list = mRepository.findBynames(name);
//			if(!list.isEmpty()) {
//			return new APIResponse(HttpStatus.OK.value(),HttpStatus.OK.name(),list);
//	   }else {
//		   return new APIResponse(HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND.name(),new ArrayList<>());
//	   }
//		
//	}catch (Exception ex) {
//		ex.printStackTrace();
//		return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.name(),new ArrayList<>());
//	}
//	}
//
//	public MessageResponse deleteid(Long moduleId) {
//		Optional<Modules> opt = mRepository.findBymoduleId(moduleId);
//		if(opt.isPresent()) {
//			mRepository.deleteById(moduleId);
//			return new MessageResponse(HttpStatus.OK.value(), String.format("delete with this id:%d is deleted", moduleId));
//		}else {
//			return new MessageResponse(HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND.name());
//		}
//	}
//
//	public MessageResponse deleteList() {
//		try {
//			mRepository.deleteAll();
//			return new MessageResponse(HttpStatus.OK.value(),String.format("you have delete all the list")	);
//		}catch(Exception ex) {
//			ex.printStackTrace();
//			return new MessageResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name());
//		}
//	}
}
