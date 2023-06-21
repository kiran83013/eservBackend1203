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

import com.travel.travtronics.converter.EmpConverter;
import com.travel.travtronics.enums.SortType;
import com.travel.travtronics.eserv.model.EmpPid;
import com.travel.travtronics.eserv.repository.EmpPidRepository;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.APIResponsePaging;
import com.travel.travtronics.response.EmpPidResponse;
import com.travel.travtronics.response.MessageResponse;

@Service
public class EmpPidService {

	@Autowired
	EmpPidRepository empPidRepository;

	public APIResponse createPid(EmpPid model) {
		try {

			List<EmpPid> pid = new ArrayList<>();
			if (model.getRefId() == 0 || model.getRefId() == null)
				return new APIResponse(HttpStatus.BAD_REQUEST.value(), " RefId Should Not Be Empty Or Zero",
						new ArrayList<>());
			else if (model.getType() == 0 || model.getType() == null)
				return new APIResponse(HttpStatus.BAD_REQUEST.value(), " Type Should Not Be Empty Or Zero",
						new ArrayList<>());
			else if (model.getValue() == null || model.getValue().isBlank())
				return new APIResponse(HttpStatus.BAD_REQUEST.value(), "FisrtName Should Not Be Empty ",
						new ArrayList<>());
			else if (model.getFromDate() == null)
				return new APIResponse(HttpStatus.BAD_REQUEST.value(), "FromDate Should Not Be Empty ",
						new ArrayList<>());
			else if (model.getToDate() == null)
				return new APIResponse(HttpStatus.BAD_REQUEST.value(), "ToDate Should Not Be Empty ",
						new ArrayList<>());
			else if (model.getCreatedBy() == 0 || model.getCreatedBy() == null)
				return new APIResponse(HttpStatus.BAD_REQUEST.value(), "CreatedBy Should Not Be Empty Or Zero ",
						new ArrayList<>());
			else {
				EmpPid save = empPidRepository.save(model);
				pid.add(save);
				return new APIResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED.name(), pid);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name(),
					new ArrayList<>());
		}
	}

	public APIResponse getEmpPidId(Long id) {
		try {
			Optional<EmpPid> pid = empPidRepository.findById(id);
			List<EmpPid> list = new ArrayList<>();
			if (pid.isPresent()) {
				list.add(pid.get());
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

	public APIResponse getListByEmpRefId(Long refId) {
		try {
			List<Map<String, String>> list = empPidRepository.getAllListByEmpRefId(refId);
			return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), list);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new APIResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), new ArrayList<>());
		}
	}

	public MessageResponse updateEmpPid(EmpPid model) {
		try {
			if (model.getRefId() == 0 || model.getRefId() == null)
				return new MessageResponse(HttpStatus.BAD_REQUEST.value(), " RefId Should Not Be Empty Or Zero");
			else if (model.getType() == 0 || model.getType() == null)
				return new MessageResponse(HttpStatus.BAD_REQUEST.value(), " Type Should Not Be Empty Or Zero");
			else if (model.getValue() == null || model.getValue().isBlank())
				return new MessageResponse(HttpStatus.BAD_REQUEST.value(), "FisrtName Should Not Be Empty ");
			else if (model.getFromDate() == null)
				return new MessageResponse(HttpStatus.BAD_REQUEST.value(), "FromDate Should Not Be Empty ");
			else if (model.getToDate() == null)
				return new MessageResponse(HttpStatus.BAD_REQUEST.value(), "ToDate Should Not Be Empty ");
			else if (model.getUpdatedBy() == 0 || model.getUpdatedBy() == null)
				return new MessageResponse(HttpStatus.BAD_REQUEST.value(), "UpdatedBy Should Not Be Empty Or Zero ");
			Optional<EmpPid> def = empPidRepository.findById(model.getId());
			if (def.isPresent()) {

				model.setCreatedBy(def.get().getCreatedBy());
				empPidRepository.save(model);
				return new MessageResponse(HttpStatus.OK.value(), HttpStatus.OK.name());
			} else {
				return new MessageResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name());
			}

		} catch (Exception ex) {

			return new MessageResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
					HttpStatus.INTERNAL_SERVER_ERROR.name());
		}
	}

	public APIResponse createMultipleEmpPids(List<EmpPid> models) {
		List<EmpPid> pid = new ArrayList<>();
		for (EmpPid model : models) {

			if (model.getRefId() == 0 || model.getRefId() == null)
				return new APIResponse(HttpStatus.BAD_REQUEST.value(), " refId Should Not Be Empty Or Zero",
						new ArrayList<>());
			else if (model.getType() == 0 || model.getType() == null)
				return new APIResponse(HttpStatus.BAD_REQUEST.value(), " Type Should Not Be Empty Or Zero",
						new ArrayList<>());
			else if (model.getValue() == null || model.getValue().isBlank())
				return new APIResponse(HttpStatus.BAD_REQUEST.value(), "FisrtName Should Not Be Empty ",
						new ArrayList<>());
			else if (model.getFromDate() == null)
				return new APIResponse(HttpStatus.BAD_REQUEST.value(), "FromDate Should Not Be Empty ",
						new ArrayList<>());
			else if (model.getToDate() == null)
				return new APIResponse(HttpStatus.BAD_REQUEST.value(), "ToDate Should Not Be Empty ",
						new ArrayList<>());
			else if (model.getCreatedBy() == 0 || model.getCreatedBy() == null)
				return new APIResponse(HttpStatus.BAD_REQUEST.value(), "CreatedBy Should Not Be Empty Or Zero ",
						new ArrayList<>());
			else {
				EmpPid save = empPidRepository.save(model);
				pid.add(save);

			}

		}
		return new APIResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED.name(), pid);
	}

	public APIResponse getEmpPid(Long organizationId) {
		try {
			List<EmpPid> list = empPidRepository.findAllByOrganizationId(organizationId);
			return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), list);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name(),
					new ArrayList<>());
		}
	}

	public APIResponsePaging getPagenationByOrganization(Long organizationId, Long refId, int pageNo, int pageSize, String sortBy,
			SortType sortType) {
		Pageable pageable = PageRequest.of(pageNo, pageSize,
				sortType == SortType.asc ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
		if (organizationId != null) {
			Page<EmpPid> findByOrganizationId = empPidRepository.findByOrganizationIdAndRefId(organizationId, refId, pageable);
			Page<EmpPidResponse> item = findByOrganizationId.map(model -> {
				EmpPidResponse response = EmpConverter.convertEmpPidModelToResponse(model);
				Optional<String> typeName = empPidRepository.getTypeName(response.getType());
				Optional<String> ref = empPidRepository.getBusinessName(response.getRefId());
				Optional<String> org = empPidRepository.getOrganizationName(response.getOrganizationId());
				if (typeName.isPresent())
					response.setTypeName(typeName.get());
				if (ref.isPresent())
					response.setBusinessName(ref.get());
				if (org.isPresent())
					response.setOrganizationName(org.get());
				return response;
			});
			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), item.getContent(),
					new ArrayList<>(), item.getNumber(), item.getTotalElements(), item.getTotalPages());
		} else {
			Page<EmpPid> findByOrganizationId = empPidRepository.findAll(pageable);
			Page<EmpPidResponse> item = findByOrganizationId.map(model -> {
				EmpPidResponse response = EmpConverter.convertEmpPidModelToResponse(model);
				Optional<String> typeName = empPidRepository.getTypeName(response.getType());
				Optional<String> ref = empPidRepository.getBusinessName(response.getRefId());
				Optional<String> org = empPidRepository.getOrganizationName(response.getOrganizationId());
				if (typeName.isPresent())
					response.setTypeName(typeName.get());
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
