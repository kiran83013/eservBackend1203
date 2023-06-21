package com.travel.travtronics.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.travel.travtronics.enums.SortType;
import com.travel.travtronics.eserv.model.BpfMailTemplate;
import com.travel.travtronics.eserv.repository.BpfMailTemplateRepository;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.APIResponsePaging;

@Service
public class BpfMailTemplateService {
	
	@Autowired
	BpfMailTemplateRepository bpfmtRepository;

	public APIResponse create(BpfMailTemplate bpfmTemplate) {
		List<BpfMailTemplate> list = new ArrayList<>(); 
		try {
			BpfMailTemplate save = bpfmtRepository.save(bpfmTemplate);
			list.add(save);
			return new APIResponse(HttpStatus.CREATED.value(),HttpStatus.CREATED.name(), list);
		}catch(Exception ex) {
			ex.printStackTrace();
			return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.name(),new ArrayList<>());
		}
	}

	public APIResponse getId(Long id) {
		List<BpfMailTemplate> list = new ArrayList<>();
		try {
			Optional<BpfMailTemplate> opt = bpfmtRepository.findById(id);
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

	public APIResponse edit(BpfMailTemplate bpfmTemplate) {
		List<BpfMailTemplate> list =new ArrayList<>();
		try {
			Optional<BpfMailTemplate> opt =bpfmtRepository.findById(bpfmTemplate.getId());
			if(opt.isPresent()) {
				bpfmTemplate.setCreatedBy(opt.get().getCreatedBy());
				BpfMailTemplate save = bpfmtRepository.save(bpfmTemplate); 
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
			List<BpfMailTemplate> list =bpfmtRepository.findAllByOrganizationId(organizationId);
			return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), list);
		}catch (Exception ex) {
			ex.printStackTrace();
			return new APIResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), new ArrayList<>());
			}
	}

	public APIResponsePaging getPersonProfilePagenationByOrganization(Long organizationId, int pageNo, int pageSize,String sortBy, SortType sortType) {
		Pageable pageable = PageRequest.of(pageNo, pageSize,
				sortType == SortType.asc ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
		if (organizationId != null) {
			Page<BpfMailTemplate> findByOrganizationId = bpfmtRepository.findByOrganizationId(organizationId,
					pageable);
			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), findByOrganizationId.getContent(),
					new ArrayList<>(), findByOrganizationId.getNumber(), findByOrganizationId.getTotalElements(),
					findByOrganizationId.getTotalPages());
		} else {
			Page<BpfMailTemplate> findAll = bpfmtRepository.findAll(pageable);
			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), findAll.getContent(),
					new ArrayList<>(), findAll.getNumber(), findAll.getTotalElements(), findAll.getTotalPages());
		}
	}

}
