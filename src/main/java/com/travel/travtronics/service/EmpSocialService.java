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

import com.travel.travtronics.converter.EmpAddressConverter;
import com.travel.travtronics.enums.SortType;
import com.travel.travtronics.eserv.model.EmpSocial;
import com.travel.travtronics.eserv.repository.EmpSocialRepository;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.APIResponsePaging;
import com.travel.travtronics.response.EmpSocialResponse;
import com.travel.travtronics.response.MessageResponse;

@Service
public class EmpSocialService {

	@Autowired
	EmpSocialRepository empSocialRepository;

	public APIResponse createEmpSocial(EmpSocial model) {
		try {

			List<EmpSocial> social = new ArrayList<>();
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

			else {
				EmpSocial save = empSocialRepository.save(model);
				social.add(save);
				return new APIResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED.name(), social);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name(),
					new ArrayList<>());
		}
	}

	public APIResponse getEmpSocialId(Long id) {
		try {
			Optional<EmpSocial> sos = empSocialRepository.findById(id);
			List<EmpSocial> list = new ArrayList<>();
			if (sos.isPresent()) {
				list.add(sos.get());
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

	public APIResponse getEmpSocialList(Long organizationId) {
		try {
			List<EmpSocial> list = empSocialRepository.findAllByOrganizationId(organizationId);
			return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), list);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name(),
					new ArrayList<>());
		}
	}

	public APIResponse getByEmpSocialRefId(Long refId) {
		try {
			List<Map<String, String>> list = empSocialRepository.findAllByRefId(refId);
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

	public MessageResponse updateEmpSocial(EmpSocial model) {
		try {
			if (model.getRefId() == 0 || model.getRefId() == null)
				return new MessageResponse(HttpStatus.BAD_REQUEST.value(), " refId Should Not Be Empty Or Zero");
			else if (model.getType() == 0 || model.getType() == null)
				return new MessageResponse(HttpStatus.BAD_REQUEST.value(), " Type Should Not Be Empty Or Zero");
			else if (model.getValue() == null || model.getValue().isBlank())
				return new MessageResponse(HttpStatus.BAD_REQUEST.value(), "FisrtName Should Not Be Empty ");
			else if (model.getFromDate() == null)
				return new MessageResponse(HttpStatus.BAD_REQUEST.value(), "FromDate Should Not Be Empty ");

			Optional<EmpSocial> def = empSocialRepository.findById(model.getId());
			if (def.isPresent()) {

				model.setCreatedBy(def.get().getCreatedBy());
				empSocialRepository.save(model);
				return new MessageResponse(HttpStatus.OK.value(), HttpStatus.OK.name());
			} else {
				return new MessageResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name());
			}

		} catch (Exception ex) {

			return new MessageResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
					HttpStatus.INTERNAL_SERVER_ERROR.name());
		}
	}

	public APIResponse createMultipleEmpSocials(List<EmpSocial> models) {
		List<EmpSocial> social = new ArrayList<>();
		for (EmpSocial model : models) {

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

			else {
				EmpSocial save = empSocialRepository.save(model);
				social.add(save);

			}

		}
		return new APIResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED.name(), social);
	}

	public APIResponsePaging getPagenationByOrganization(Long organizationId, Long refId, int pageNo, int pageSize, String sortBy,
			SortType sortType) {
		Pageable pageable = PageRequest.of(pageNo, pageSize,
				sortType == SortType.asc ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
		if (organizationId != null) {
			Page<EmpSocial> findByOrganizationId = empSocialRepository.findByOrganizationIdAndRefId(organizationId, refId, pageable);
			Page<EmpSocialResponse> item = findByOrganizationId.map(model -> {
				EmpSocialResponse response = EmpAddressConverter.convertEmpSocialModelToResponse(model);
				Optional<String> typeName = empSocialRepository.getSocialMediaTypeName(response.getType());
				Optional<String> ref = empSocialRepository.getBusinessName(response.getRefId());
				Optional<String> org = empSocialRepository.getorganizationName(response.getOrganizationId());
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
			Page<EmpSocial> findByOrganizationId = empSocialRepository.findAll(pageable);
			Page<EmpSocialResponse> item = findByOrganizationId.map(model -> {
				EmpSocialResponse response = EmpAddressConverter.convertEmpSocialModelToResponse(model);
				Optional<String> typeName = empSocialRepository.getSocialMediaTypeName(response.getType());
				Optional<String> ref = empSocialRepository.getBusinessName(response.getRefId());
				Optional<String> org = empSocialRepository.getorganizationName(response.getOrganizationId());
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
