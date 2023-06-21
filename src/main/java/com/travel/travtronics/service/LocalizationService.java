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

import com.travel.travtronics.converter.LocalizationConverter;
import com.travel.travtronics.enums.SortType;
import com.travel.travtronics.eserv.model.Localization;
import com.travel.travtronics.eserv.repository.LocalizationRepository;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.APIResponsePaging;
import com.travel.travtronics.response.LocalizationResponse;

@Service
public class LocalizationService {

	@Autowired
	LocalizationRepository localizationRepository;

	public APIResponse create(Localization model) {
		try {
			List<Localization> list = new ArrayList<>();
			Localization save = localizationRepository.save(model);
			list.add(save);
			return new APIResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED.name(), list);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name(), new ArrayList<>());
		}
	}

	public APIResponse update(Localization model) {
		List<Localization> list = new ArrayList<>();
		try {
			Optional<Localization> opt = localizationRepository.findById(model.getId());
			if (opt.isPresent()) {
				model.setCreatedBy(opt.get().getCreatedBy());
				model.setCreatedDate(opt.get().getCreatedDate());
				Localization save = localizationRepository.save(model);
				list.add(save);
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

	public APIResponse findById(Long id) {
		List<Localization> list = new ArrayList<>();
		try {
			Optional<Localization> opt = localizationRepository.findById(id);
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

	public APIResponse findAll() {
		try {
			List<Localization> list = localizationRepository.findAll();
			return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), list);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name(),
					new ArrayList<>());
		}
	}

	public APIResponsePaging getPaginationByOrganization(Long organizationId, int pageNo, int pageSize, String sortBy,
			SortType sortType) {
		Pageable pageable = PageRequest.of(pageNo, pageSize,
				sortType == SortType.asc ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
//		if (organizationId != null) {
//			Page<Localization> findByOrganizationId = localizationRepository.findByOrganizationId(organizationId, pageable);
//			Page<LocalizationResponse> item = findByOrganizationId.map(model -> {
//				LocalizationResponse response = LocalizationConverter.convertLocalizationToResponse(model);
////				Optional<String> orgName = localizationRepository.getOrganizationName(model.getOrganizationId());
////				if (orgName.isPresent())
////					response.setOrganizationName(orgName.get());
////				List<String> roleName = localizationRepository.getRoleName(model.getRole());
////				if (!roleName.isEmpty())
////					response.setRoleName(roleName);
////				Optional<String> roleTypeName = localizationRepository.getRoleTypeName(model.getRoleType());
////				if (roleTypeName.isPresent())
////					response.setRoleTypeName(roleTypeName.get());
//				return response;
//			});
//			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), item.getContent(),
//					new ArrayList<>(), item.getNumber(), item.getTotalElements(),
//					item.getTotalPages());
//		} else {
//			Page<Localization> findAll = localizationRepository.findAll(pageable);
//			Page<LocalizationResponse> item = findAll.map(model -> {
//				LocalizationResponse response = LocalizationConverter.convertLocalizationToResponse(model);
////				Optional<String> orgName = localizationRepository.getOrganizationName(model.getOrganizationId());
////				if (orgName.isPresent())
////					response.setOrganizationName(orgName.get());
////				List<String> roleName = localizationRepository.getRoleName(model.getRole());
////				if (!roleName.isEmpty())
////					response.setRoleName(roleName);
////				Optional<String> roleTypeName = localizationRepository.getRoleTypeName(model.getRoleType());
////				if (roleTypeName.isPresent())
////					response.setRoleTypeName(roleTypeName.get());
//				return response;
//			});
		Page<Map<String, String>> item = localizationRepository.getOrganizationId(organizationId, pageable);
		
			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), item.getContent(),
					new ArrayList<>(), item.getNumber(), item.getTotalElements(), item.getTotalPages());
	}

	public APIResponse findByRoleTypeId(Long roleTypeId) {
		try {
			List<Map<String, String>> opt = localizationRepository.findByRoleTypeId(roleTypeId);
				return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), opt);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new APIResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(),
					new ArrayList<>());
		}
	}

	public APIResponse findAllRoleType() {
		try {
			List<Map<String, String>> opt = localizationRepository.findAllList();
				return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), opt);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new APIResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(),
					new ArrayList<>());
		}
	}

	public APIResponse findAllRoles(Long roleTypeId) {
		try {
			List<Map<String, String>> opt = localizationRepository.findAllRolesList(roleTypeId);
				return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), opt);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new APIResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(),
					new ArrayList<>());
		}
	}
	
	
}
