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

import com.travel.travtronics.converter.EmpBankConverter;
import com.travel.travtronics.enums.SortType;
import com.travel.travtronics.eserv.model.EmpLanguage;
import com.travel.travtronics.eserv.repository.EmpLanguageRepository;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.APIResponsePaging;
import com.travel.travtronics.response.EmpLanguageResponse;

@Service
public class EmpLanguageService {

	@Autowired
	EmpLanguageRepository empLanguageRepository;

	public APIResponse getLangId(Long id) {
		try {
			Optional<EmpLanguage> opt = empLanguageRepository.findById(id);
			List<EmpLanguage> list = new ArrayList<>();
			if (opt.isPresent()) {
				list.add(opt.get());
				return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), list);
			} else {
				return new APIResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), new ArrayList<>());
			}

		} catch (Exception ex) {
			// ex.printStackTrace();
			return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name(),
					new ArrayList<>());
		}
	}

	public APIResponse getAllLang(Long organizationId) {
		try {
			List<EmpLanguage> list = empLanguageRepository.findAllByOrganizationId(organizationId);

			return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), list);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name(),
					new ArrayList<>());
		}
	}

//	public static APIResponse getListByEmpLang(Long refId) {
//		try {
//			List<Map<String,String>> list = empLanguageRepository.getListByrefId();
//			return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), list);
//		}catch (Exception ex) {
//			ex.printStackTrace();
//			return new APIResponse(HttpStatus.NOT_FOUND.value(),HttpStatus.OK.name(),new ArrayList<>());
//					}
//	
//	}

	public APIResponse editByLang(List<EmpLanguage> models) {
		List<EmpLanguage> list = new ArrayList<>();
		for (EmpLanguage model : models) {
			if (model.getRefId() == 0 || model.getRefId() == null)
				return new APIResponse(HttpStatus.BAD_REQUEST.value(), " RefId Should Not Be Empty Or Zero",
						new ArrayList<>());
			else if (model.getLanguageId() == 0 || model.getLanguageId() == null)
				return new APIResponse(HttpStatus.BAD_REQUEST.value(), "LanguageId Should Not Be Empty Or Zero ",
						new ArrayList<>());
			else if (model.getUpdatedBy() == 0 || model.getUpdatedBy() == null)
				return new APIResponse(HttpStatus.BAD_REQUEST.value(), "UpdatedBy Should Not Be Empty Or Zero ",
						new ArrayList<>());

			Optional<EmpLanguage> opt = empLanguageRepository.findById(model.getId());

			if (opt.isPresent()) {
				model.setCreatedBy(opt.get().getCreatedBy());
				EmpLanguage updateModel = empLanguageRepository.save(model);
				list.add(updateModel);
			} else {
				return new APIResponse(HttpStatus.NOT_FOUND.value(),
						String.format("Given Id : %s not found", model.getId()), new ArrayList<>());
			}

		}
		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), list);
	}

	public APIResponse createMultiLan(List<EmpLanguage> models) {
		List<EmpLanguage> lan = new ArrayList<>();
		for (EmpLanguage model : models) {

			if (model.getRefId() == 0 || model.getRefId() == null)
				return new APIResponse(HttpStatus.BAD_REQUEST.value(), " RefId Should Not Be Empty Or Zero",
						new ArrayList<>());
			else if (model.getLanguageId() == 0 || model.getLanguageId() == null)
				return new APIResponse(HttpStatus.BAD_REQUEST.value(), "LanguageId Should Not Be Empty Or Zero ",
						new ArrayList<>());
			else if (model.getCreatedBy() == 0 || model.getCreatedBy() == null)
				return new APIResponse(HttpStatus.BAD_REQUEST.value(), "CreatedBy Should Not Be Empty Or Zero ",
						new ArrayList<>());
			else {
				EmpLanguage save = empLanguageRepository.save(model);
				lan.add(save);
			}
		}
		return new APIResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED.name(), lan);
	}

	public APIResponse getListByRefId(Long refId) {
		try {
			List<Map<String, String>> list = empLanguageRepository.findByRefId(refId);
			return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), list);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new APIResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.OK.name(), new ArrayList<>());
		}
	}

	public APIResponsePaging getPagenationByOrganization(Long organizationId, Long refId, int pageNo, int pageSize, String sortBy,
			SortType sortType) {
		Pageable pageable = PageRequest.of(pageNo, pageSize,
				sortType == SortType.asc ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
		if (organizationId != null) {
			Page<EmpLanguage> findByOrganizationId = empLanguageRepository.findByOrganizationIdAndRefId(organizationId, refId,
					pageable);
			Page<EmpLanguageResponse> item = findByOrganizationId.map(model -> {
				EmpLanguageResponse request = EmpBankConverter.convertEmpLanguageModelToResponse(model);
				Optional<String> language = empLanguageRepository.getLanguageName(request.getLanguageId());
				Optional<String> ref = empLanguageRepository.getBusinessName(request.getRefId());
				Optional<String> org = empLanguageRepository.getOrganizationName(request.getOrganizationId());
				if (language.isPresent())
					request.setLanguageName(language.get());
				if (ref.isPresent())
					request.setBusinessName(ref.get());
				if (org.isPresent())
					request.setOrganizationName(org.get());
				return request;
			});
			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), item.getContent(),
					new ArrayList<>(), item.getNumber(), item.getTotalElements(), item.getTotalPages());
		} else {
			Page<EmpLanguage> findByOrganizationId = empLanguageRepository.findAll(pageable);
			Page<EmpLanguageResponse> item = findByOrganizationId.map(model -> {
				EmpLanguageResponse request = EmpBankConverter.convertEmpLanguageModelToResponse(model);
				Optional<String> language = empLanguageRepository.getLanguageName(request.getLanguageId());
				Optional<String> ref = empLanguageRepository.getBusinessName(request.getRefId());
				Optional<String> org = empLanguageRepository.getOrganizationName(request.getOrganizationId());
				if (language.isPresent())
					request.setLanguageName(language.get());
				if (ref.isPresent())
					request.setBusinessName(ref.get());
				if (org.isPresent())
					request.setOrganizationName(org.get());
				return request;
			});
			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), item.getContent(),
					new ArrayList<>(), item.getNumber(), item.getTotalElements(), item.getTotalPages());
		}
	}

}
