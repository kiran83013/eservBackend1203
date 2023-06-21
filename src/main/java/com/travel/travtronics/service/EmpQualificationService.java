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
import com.travel.travtronics.eserv.model.EmpQualification;
import com.travel.travtronics.eserv.repository.EmpQualificationRepository;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.APIResponsePaging;
import com.travel.travtronics.response.EmpQualificationResponse;
import com.travel.travtronics.response.MessageResponse;

@Service
public class EmpQualificationService {

	@Autowired
	EmpQualificationRepository empQualificationRepository;

	public APIResponse createEmpQualification(List<EmpQualification> models) {
		List<EmpQualification> list = new ArrayList<>();
		for (EmpQualification model : models) {
			EmpQualification save = empQualificationRepository.save(model);
			list.add(save);
		}
		return new APIResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED.name(), list);
	}

	public APIResponse getEmpQualificationList(Long organizationId) {
		try {
			List<EmpQualification> list = empQualificationRepository.findAllByOrganizationId(organizationId);
			return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), list);
		} catch (Exception e) {
			return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name(),
					new ArrayList<>());
		}
	}

	public APIResponse getEmpQualificationById(Long id) {
		try {
			Optional<EmpQualification> opt = empQualificationRepository.findById(id);
			List<EmpQualification> list = new ArrayList<>();
			if (opt.isPresent()) {
				list.add(opt.get());
				return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), list);
			} else {
				return new APIResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), new ArrayList<>());
			}
		} catch (Exception e) {
			return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name(),
					new ArrayList<>());
		}
	}

	public MessageResponse updateEmpQualification(EmpQualification model) {
		try {
			Optional<EmpQualification> opt = empQualificationRepository.findById(model.getId());
			if (opt.isPresent()) {
				model.setCreatedBy(opt.get().getCreatedBy());
				empQualificationRepository.save(model);
				return new MessageResponse(HttpStatus.OK.value(), HttpStatus.OK.name());
			} else {
				return new MessageResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name());
			}
		} catch (Exception e) {
			return new MessageResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
					HttpStatus.INTERNAL_SERVER_ERROR.name());
		}
	}

	public APIResponse getEmpQualificationByRefId(Long refId) {

		List<Map<String, String>> listByPax = empQualificationRepository.findAllByRefId(refId);

		if (!listByPax.isEmpty()) {
			return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), listByPax);
		} else {
			return new APIResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), new ArrayList<>());
		}
	}

	public APIResponsePaging getPagenationByOrganization(Long organizationId, Long refId, int pageNo, int pageSize, String sortBy,
			SortType sortType) {
		Pageable pageable = PageRequest.of(pageNo, pageSize,
				sortType == SortType.asc ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
		if (organizationId != null) {
			Page<EmpQualification> findByOrganizationId = empQualificationRepository
					.findByOrganizationIdAndRefId(organizationId, refId, pageable);
			Page<EmpQualificationResponse> item = findByOrganizationId.map(model -> {
				EmpQualificationResponse response = EmpBankConverter.convertEmpQualificationModelToResponse(model);
				Optional<String> qualificationTypeName = empQualificationRepository.getQualificationtypeName(response.getQualificationType());
				Optional<String> ref = empQualificationRepository.getBusinessName(response.getRefId());
				Optional<String> org = empQualificationRepository.getOrganizationName(response.getOrganizationId());
				if (qualificationTypeName.isPresent())
					response.setQualificationTypeName(qualificationTypeName.get());
				if (ref.isPresent())
					response.setBusinessName(ref.get());
				if (org.isPresent())
					response.setOrganizationName(org.get());
				return response;
			});
			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), item.getContent(),
					new ArrayList<>(), item.getNumber(), item.getTotalElements(), item.getTotalPages());
		} else {
			Page<EmpQualification> findByOrganizationId = empQualificationRepository.findAll(pageable);
			Page<EmpQualificationResponse> item = findByOrganizationId.map(model -> {
				EmpQualificationResponse response = EmpBankConverter.convertEmpQualificationModelToResponse(model);
				Optional<String> qualificationTypeName = empQualificationRepository.getQualificationtypeName(response.getQualificationType());
				Optional<String> ref = empQualificationRepository.getBusinessName(response.getRefId());
				Optional<String> org = empQualificationRepository.getOrganizationName(response.getOrganizationId());
				if (qualificationTypeName.isPresent())
					response.setQualificationTypeName(qualificationTypeName.get());
				if (ref.isPresent())
					response.setBusinessName(ref.get());
				if (org.isPresent())
					response.setOrganizationName(org.get());
				return response;
			});
			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), item.getContent(),
					new ArrayList<>(), item.getNumber(), item.getTotalElements(), item.getTotalPages());
		}
	}
}
