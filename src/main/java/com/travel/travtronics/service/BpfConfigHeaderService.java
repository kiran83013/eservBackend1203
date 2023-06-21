package com.travel.travtronics.service;

import java.util.ArrayList;
import java.util.Collections;
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

import com.travel.travtronics.converter.BpfConfigConverter;
import com.travel.travtronics.enums.SortType;
import com.travel.travtronics.enums.StatusActive;
import com.travel.travtronics.eserv.model.BpfConfigHeader;
import com.travel.travtronics.eserv.model.BpfConfigLine;
import com.travel.travtronics.eserv.model.Modules;
import com.travel.travtronics.eserv.model.Status;
import com.travel.travtronics.eserv.model.SubModule;
import com.travel.travtronics.eserv.repository.BpfConfigHeaderRepository;
import com.travel.travtronics.eserv.repository.BpfConfigLineRepository;
import com.travel.travtronics.eserv.repository.LetterTemplateHeaderRepository;
import com.travel.travtronics.eserv.repository.ModulesRepository;
import com.travel.travtronics.eserv.repository.StatusRepository;
import com.travel.travtronics.eserv.repository.SubModuleRepository;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.APIResponsePaging;
import com.travel.travtronics.response.BpfConfigHeaderResponse;
import com.travel.travtronics.response.BpfConfigRequest;
import com.travel.travtronics.response.MessageResponse;

@Service
public class BpfConfigHeaderService {

	@Autowired
	BpfConfigHeaderRepository headerRepository;

	@Autowired
	BpfConfigLineRepository lineRepository;

	public MessageResponse create(BpfConfigRequest request) {

		BpfConfigHeader savedHeaderInfo = headerRepository.save(request.convertHeaderDtoToJson(true));

		lineRepository.saveAll(request.convertLineDtoToJson(savedHeaderInfo, true));

		return new MessageResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED.name());

	}

	public MessageResponse edit(BpfConfigRequest request) {
		Optional<BpfConfigHeader> findById = headerRepository.findById(request.getConfigHeader().getID());

		if (!findById.isPresent()) {
			return new MessageResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name());
		}
		BpfConfigHeader savedHeaderInfo = headerRepository.save(request.convertHeaderDtoToJson(false));
		lineRepository.saveAll(request.convertLineDtoToJson(savedHeaderInfo, false));
//
//		lineRepository.saveAll(request.getConfigLines().stream().map(line -> {
//			line.setHeaderID(save.getID());
//			return line;
//
//		}).collect(Collectors.toList()));

		return new MessageResponse(HttpStatus.OK.value(), HttpStatus.OK.name());
	}

	public APIResponse getAll(Long organizationId) {
		try {
			List<Map<String, String>> list = headerRepository.findAllList(organizationId);
			return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), list);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name(),
					new ArrayList<>());
		}
	}

	public APIResponse getById(Long id) {
		Optional<BpfConfigHeader> findById = headerRepository.findById(id);

		List<BpfConfigLine> lines = lineRepository.findAllByHeaderIDAndDeleteFlagIsNullAndStatus(id,
				StatusActive.Active);

		if (!findById.isPresent()) {
			return new APIResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), Collections.emptyList());
		}

		BpfConfigRequest response = new BpfConfigRequest();
//		response.setConfigHeader(findById.get());
//		response.setConfigLines(Objects.requireNonNull(lines));

		response.convertModelToJson(findById.get(), lines);

		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), Collections.singletonList(response));

	}

//	public APIResponsePaging getBpfConfigPagenationByOrganization(Long organizationId, int pageNo, int pageSize,String sortBy, SortType sortType) {
//		Pageable pageable = PageRequest.of(pageNo, pageSize,
//				sortType == SortType.asc ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
//		if (organizationId != null) {
//			Page<BpfConfigHeader> findByOrganizationId = headerRepository.findByOrganizationId(organizationId,
//					pageable);
//			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), findByOrganizationId.getContent(),
//					new ArrayList<>(), findByOrganizationId.getNumber(), findByOrganizationId.getTotalElements(),
//					findByOrganizationId.getTotalPages());
//		} else {
//			Page<BpfConfigHeader> findAll = headerRepository.findAll(pageable);
//			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), findAll.getContent(),
//					new ArrayList<>(), findAll.getNumber(), findAll.getTotalElements(), findAll.getTotalPages());
//		}
//	}

	@Autowired
	ModulesRepository moduleRepository;

	@Autowired
	SubModuleRepository smRepository;

	@Autowired
	StatusRepository sRepository;

	@Autowired
	LetterTemplateHeaderRepository letterTemplateHeaderRepository;

	public APIResponsePaging getBpfConfigPagenationByOrganization(Long organizationId, int pageNo, int pageSize,
			String sortBy, SortType sortType) {
		Pageable pageable = PageRequest.of(pageNo, pageSize,
				sortType == SortType.asc ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
//		if (organizationId != null) {
//			Page<BpfConfigHeader> findByOrganizationId = headerRepository.findByOrganizationId(organizationId, pageable);
//			List<BpfConfigHeaderResponse> collectedResponse = BpfConfigConverter.convertConfigToResponse(findByOrganizationId)
//					.stream().map(bpf -> {
//						Optional<Modules> findById = moduleRepository.findById(bpf.getModule());
//						bpf.setModuleName(findById.isPresent() ? findById.get().getModuleName() :null);
//						return bpf;
//					}).collect(Collectors.toList());
//			Long count = headerRepository.countByOrganizationId(organizationId);
//			PageImpl<BpfConfigHeaderResponse> item = new PageImpl<>(collectedResponse,pageable, count);
//			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), collectedResponse.getContent(),
//					new ArrayList<>(), collectedResponse.getNumber(), collectedResponse.getTotalElements(), collectedResponse.getTotalPages());		
//		}else {
//			
//		}
//		}

		if (organizationId != null) {

			Page<BpfConfigHeader> findByOrganizationId = headerRepository.findByOrganizationId(organizationId,
					pageable);

			Page<BpfConfigHeaderResponse> item = findByOrganizationId.map(model -> {
				BpfConfigHeaderResponse response = BpfConfigConverter.convertConfigModelToResponse(model);
				Optional<Modules> findById = moduleRepository.findById(response.getModule());
				response.setModuleName(findById.isPresent() ? findById.get().getModuleName() : null);
				Optional<SubModule> subModule = smRepository.findById(response.getSubModule());
				response.setSubModuleName(subModule.isPresent() ? subModule.get().getName() : null);
				Optional<Status> status = sRepository.findById(response.getStatusFrom());
				response.setStatusFromName(status.isPresent() ? status.get().getName() : null);
				Optional<Status> statusTo = sRepository.findById(response.getStatusTo());
				response.setStatusToName(statusTo.isPresent() ? statusTo.get().getName() : null);
				Optional<String> letter = headerRepository.getLetterTemplateName(response.getLetterTemplate());
				if (letter.isPresent())
					response.setLetterTemplateName(letter.get());
				return response;
			});
			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), item.getContent(),
					new ArrayList<>(), item.getNumber(), item.getTotalElements(), item.getTotalPages());
		} else {
			Page<BpfConfigHeader> findByOrganizationId = headerRepository.findAll(pageable);
			Page<BpfConfigHeaderResponse> itemPage = findByOrganizationId.map(model -> {
				BpfConfigHeaderResponse response = BpfConfigConverter.convertConfigModelToResponse(model);
				Optional<Modules> findById = moduleRepository.findById(response.getModule());
				response.setModuleName(findById.isPresent() ? findById.get().getModuleName() : null);
				Optional<SubModule> subModule = smRepository.findById(response.getSubModule());
				response.setSubModuleName(subModule.isPresent() ? subModule.get().getName() : null);
				Optional<Status> status = sRepository.findById(response.getStatusFrom());
				response.setStatusFromName(status.isPresent() ? status.get().getName() : null);
				Optional<Status> statusTo = sRepository.findById(response.getStatusTo());
				response.setStatusToName(statusTo.isPresent() ? statusTo.get().getName() : null);
				Optional<String> letter = headerRepository.getLetterTemplateName(response.getLetterTemplate());
				if (letter.isPresent())
					response.setLetterTemplateName(letter.get());
				return response;
			});

			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), itemPage.getContent(),
					new ArrayList<>(), itemPage.getNumber(), itemPage.getTotalElements(), itemPage.getTotalPages());
		}
	}
}
