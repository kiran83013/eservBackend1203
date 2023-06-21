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
import com.travel.travtronics.eserv.model.SanctionGroup;
import com.travel.travtronics.eserv.repository.SanctionGroupRepository;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.APIResponsePaging;

@Service
public class SanctionGroupService {

	@Autowired
	SanctionGroupRepository sanctionGroupRepository;

	public APIResponse create(SanctionGroup model) {
		try {
			List<SanctionGroup> list = new ArrayList<>();
			SanctionGroup save = sanctionGroupRepository.save(model);
			list.add(save);
			return new APIResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED.name(), list);

		} catch (Exception ex) {
			ex.printStackTrace();
			return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name(),
					new ArrayList<>());
		}
	}

	public APIResponse update(SanctionGroup model) {
		try {
			Optional<SanctionGroup> opt = sanctionGroupRepository.findById(model.getId());
			if (opt.isPresent()) {
				model.setCreatedBy(opt.get().getCreatedBy());
				model.setCreatedDate(opt.get().getCreatedDate());
				sanctionGroupRepository.save(model);
				return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), new ArrayList<>());
			} else {
				return new APIResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), new ArrayList<>());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name(),
					new ArrayList<>());
		}
	}

	public APIResponse getById(Long id) {
		try {
			Optional<SanctionGroup> opt = sanctionGroupRepository.findById(id);
			List<SanctionGroup> list = new ArrayList<>();
			if (opt.isPresent()) {
				list.add(opt.get());
				return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), list);
			} else {
				return new APIResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), new ArrayList<>());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name(),
					new ArrayList<>());
		}
	}

	public APIResponse getByOrgId(Long orgId) {
		try {
			List<Map<String, String>> list = sanctionGroupRepository.findByOrgIdList(orgId);
			if (!list.isEmpty()) {
				return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), list);
			} else {
				return new APIResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), new ArrayList<>());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name(),
					new ArrayList<>());
		}
	}

	public APIResponsePaging getPaginationByOrganization(Long orgId, String name, int pageNo, int pageSize, String sortBy,
			SortType sortType) {
		Pageable pageable = PageRequest.of(pageNo, pageSize,
				sortType == SortType.asc ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
		if (name != null) {
			Page<Map<String, String>> findByOrganizationId = sanctionGroupRepository.findByOrgIdAndSanctionNameOrBusinessName(orgId, name, pageable);
			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), findByOrganizationId.getContent(),
					new ArrayList<>(), findByOrganizationId.getNumber(), findByOrganizationId.getTotalElements(),
					findByOrganizationId.getTotalPages());
		} else {
			Page<SanctionGroup> findByOrganizationId = sanctionGroupRepository.findByOrgId(orgId, pageable);
			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), findByOrganizationId.getContent(),
					new ArrayList<>(), findByOrganizationId.getNumber(), findByOrganizationId.getTotalElements(),
					findByOrganizationId.getTotalPages());
		}
	}
	
	
}
