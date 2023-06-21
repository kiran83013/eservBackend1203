package com.travel.travtronics.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.travel.travtronics.enums.SortType;
import com.travel.travtronics.eserv.model.BpfServiceRegister;
import com.travel.travtronics.eserv.model.PersonVerificationModel;
import com.travel.travtronics.eserv.repository.BpfServiceRegisterRepository;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.APIResponsePaging;

@Service
public class BpfServiceRegisterService {
	@Autowired
	BpfServiceRegisterRepository bpfsrRepository;

	public APIResponse create(BpfServiceRegister bpf) {
		List<BpfServiceRegister> list = new ArrayList<>(); 
		try {
			BpfServiceRegister save = bpfsrRepository.save(bpf);
			list.add(save);
			return new APIResponse(HttpStatus.CREATED.value(),HttpStatus.CREATED.name(), list);
		}catch(Exception ex) {
			ex.printStackTrace();
			return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.name(),new ArrayList<>());
		}
	}

	public APIResponse getId(Long id) {
		List<BpfServiceRegister> list = new ArrayList<>();
		try {
			Optional<BpfServiceRegister> opt = bpfsrRepository.findById(id);
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

	public APIResponse edit(BpfServiceRegister bpf) {
		List<BpfServiceRegister> list =new ArrayList<>();
		try {
			Optional<BpfServiceRegister> opt =bpfsrRepository.findById(bpf.getId());
			if(opt.isPresent()) {
				bpf.setCreatedBy(opt.get().getCreatedBy());
				BpfServiceRegister save = bpfsrRepository.save(bpf); 
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

	public APIResponse getAll(Long organizationId) {
		try {
			List<Map<String, String>> list =bpfsrRepository.findByList(organizationId);
			return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), list);
		}catch (Exception ex) {
			ex.printStackTrace();
			return new APIResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), new ArrayList<>());
			}
	}

	public APIResponsePaging getBpfServiceRegisterPagenationByOrganization(Long organizationId, int pageNo,int pageSize, String sortBy, SortType sortType) {
		Pageable pageable = PageRequest.of(pageNo, pageSize,
				sortType == SortType.asc ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
		if (organizationId != null) {
			Page<BpfServiceRegister> findByOrganizationId = bpfsrRepository
					.findByOrganizationId(organizationId, pageable);
			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), findByOrganizationId.getContent(),
					new ArrayList<>(), findByOrganizationId.getNumber(), findByOrganizationId.getTotalElements(),
					findByOrganizationId.getTotalPages());
		} else {
			Page<BpfServiceRegister> findAll = bpfsrRepository.findAll(pageable);
			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), findAll.getContent(),
					new ArrayList<>(), findAll.getNumber(), findAll.getTotalElements(), findAll.getTotalPages());
		}

	}
}
