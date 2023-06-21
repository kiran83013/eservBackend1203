package com.travel.travtronics.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.travel.travtronics.converter.EmpVerficationConverter;
import com.travel.travtronics.enums.SortType;
import com.travel.travtronics.eserv.model.EmpVerification;
import com.travel.travtronics.eserv.model.MasterVerificationType;
import com.travel.travtronics.eserv.repository.EmpVerificationRepository;
import com.travel.travtronics.eserv.repository.MasterVerificationTypeRepository;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.APIResponsePaging;
import com.travel.travtronics.response.EmpVerificationResponse;

@Service
public class EmpVerificationService {

	@Autowired
	EmpVerificationRepository empVerificationRepository;

	public APIResponse createMultiVerfy(List<EmpVerification> models) {
		List<EmpVerification> ver = new ArrayList<>();
		for (EmpVerification model : models) {

			if (model.getRefId() == 0 || model.getRefId() == null)
				return new APIResponse(HttpStatus.BAD_REQUEST.value(), " refId Should Not Be Empty Or Zero",
						new ArrayList<>());
			else if (model.getType() == 0 || model.getType() == null)
				return new APIResponse(HttpStatus.BAD_REQUEST.value(), " Type Should Not Be Empty Or Zero",
						new ArrayList<>());
			else if (model.getValue() == null || model.getValue().isBlank())
				return new APIResponse(HttpStatus.BAD_REQUEST.value(), " Value is Mandatory", new ArrayList<>());
			else if (model.getCreatedBy() == 0 || model.getCreatedBy() == null)
				return new APIResponse(HttpStatus.BAD_REQUEST.value(), "CreatedBy Should Not Be Empty Or Zero ",
						new ArrayList<>());
			else {
				EmpVerification save = empVerificationRepository.save(model);
				ver.add(save);
			}
		}
		return new APIResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED.name(), ver);
	}

	public APIResponse getVerfificationId(Long id) {
		try {
			Optional<EmpVerification> opt = empVerificationRepository.findById(id);
			List<EmpVerification> list = new ArrayList<>();
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

	public APIResponse getAllVerfification(Long organizationId) {
		try {
			List<Map<String, String>> list = empVerificationRepository.findAllByOrganizationId(organizationId);
			return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), list);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name(),
					new ArrayList<>());
		}
	}

	public APIResponse getListByRefId(Long refId) {
		try {
			List<Map<String, String>> list = empVerificationRepository.findAllByRefId(refId);
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

	public APIResponse editByEmpVerification(List<EmpVerification> models) {
		List<EmpVerification> list = new ArrayList<>();
		for (EmpVerification model : models) {
			if (model.getRefId() == 0 || model.getRefId() == null)
				return new APIResponse(HttpStatus.BAD_REQUEST.value(), "RefId Should Not Be Empty Or Zero",
						new ArrayList<>());
			else if (model.getType() == 0 || model.getType() == null)
				return new APIResponse(HttpStatus.BAD_REQUEST.value(), "Type Should Not Be Empty Or Zero",
						new ArrayList<>());
			else if (model.getValue() == null || model.getValue().isBlank())
				return new APIResponse(HttpStatus.BAD_REQUEST.value(), "Value Sis Mandatory", new ArrayList<>());
			else if (model.getUpdatedBy() == 0 || model.getUpdatedBy() == null)
				return new APIResponse(HttpStatus.BAD_REQUEST.value(), "UpdatedBy Should Not Be Empty Or Zero ",
						new ArrayList<>());

			Optional<EmpVerification> opt = empVerificationRepository.findById(model.getId());

			if (opt.isPresent()) {
				model.setCreatedBy(opt.get().getCreatedBy());
				EmpVerification updateModel = empVerificationRepository.save(model);
				list.add(updateModel);
			} else {
				return new APIResponse(HttpStatus.NOT_FOUND.value(),
						String.format("Given Id : %s not found", model.getId()), new ArrayList<>());
			}

		}
		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), list);
	}

	/*
	 * public APIResponsePaging getPagenationByOrganization(Long organizationId, int
	 * pageNo, int pageSize, String sortBy,SortType sortType) { Pageable pageable =
	 * PageRequest.of(pageNo, pageSize, sortType == SortType.asc ?
	 * Sort.by(sortBy).ascending() : Sort.by(sortBy).descending()); if
	 * (organizationId != null) { Page<EmpVerification> menuData =
	 * empVerificationRepository.findByOrganizationId(organizationId, pageable);
	 * return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(),
	 * menuData.getContent(), new ArrayList<>(), menuData.getNumber(),
	 * menuData.getTotalElements(), menuData.getTotalPages()); } else {
	 * Page<EmpVerification> esRegister =
	 * empVerificationRepository.findAll(pageable); return new
	 * APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(),
	 * esRegister.getContent(), new ArrayList<>(), esRegister.getNumber(),
	 * esRegister.getTotalElements(), esRegister.getTotalPages()); } }
	 */
	
	@Autowired
	MasterVerificationTypeRepository  masterVerificationTypeRepository;
	
	public APIResponsePaging getPagenationByOrganization(Long organizationId, Long refId, int pageNo, int pageSize, String sortBy,SortType sortType) {
		Pageable pageable = PageRequest.of(pageNo, pageSize,sortType == SortType.asc ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
		if (organizationId != null) {
			
			List<EmpVerification> findByOrganizationId = empVerificationRepository.findByOrganizationIdAndRefId(organizationId, refId);
			List<EmpVerificationResponse> collectedResponse = EmpVerficationConverter.convertVerficationJsonHeaderToModels(findByOrganizationId)
					.stream().map(tax -> {
						Optional<MasterVerificationType> findById = masterVerificationTypeRepository.findById(tax.getType());
						tax.setTypeName(findById.isPresent() ? findById.get().getName() :null);
						return tax;
					}).collect(Collectors.toList());
			
			Long count = empVerificationRepository.countByOrganizationIdAndRefId(organizationId);
			
			PageImpl<EmpVerificationResponse> response =  new PageImpl<>(collectedResponse, pageable, count);
			
			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), response.getContent(),
					new ArrayList<>(), response.getNumber(), response.getTotalElements(), response.getTotalPages());
		}
		else {
			List<EmpVerificationResponse> collectedResponse = EmpVerficationConverter.convertVerficationJsonHeaderToModels(empVerificationRepository.findAll()).stream().map(tax -> {
				Optional<MasterVerificationType> findById = masterVerificationTypeRepository.findById(tax.getType());
				tax.setTypeName(findById.isPresent() ? findById.get().getName() : null);
				return tax;
			}).collect(Collectors.toList());
			
			Long count = empVerificationRepository.countAll();
			
			PageImpl<EmpVerificationResponse> response = new PageImpl<>(collectedResponse, pageable, count);

			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), response.getContent(),
					new ArrayList<>(), response.getNumber(), response.getTotalElements(), response.getTotalPages());
		}
	}
}
