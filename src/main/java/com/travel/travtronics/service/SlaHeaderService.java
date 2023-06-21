package com.travel.travtronics.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import javax.persistence.criteria.Predicate;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.travel.travtronics.converter.SlaConverter;
import com.travel.travtronics.enums.SortType;
import com.travel.travtronics.eserv.model.SlaHeaderMappingModel;
import com.travel.travtronics.eserv.model.SlaHeaderModel;
import com.travel.travtronics.eserv.model.SlaLinesModel;
import com.travel.travtronics.eserv.repository.SlaHeaderMappingRepository;
import com.travel.travtronics.eserv.repository.SlaHeaderRepository;
import com.travel.travtronics.eserv.repository.SlaLinesRepository;
import com.travel.travtronics.request.SlaHeaderRequestData;
import com.travel.travtronics.request.SlaLinesRequestData;
import com.travel.travtronics.request.SlaRequestData;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.APIResponsePaging;
import com.travel.travtronics.response.SlaResponseData;

@Service
public class SlaHeaderService {

	@Autowired
	SlaHeaderRepository slaHeaderRepository;

	@Autowired
	SlaLinesRepository slaLinesRepository;
	
	@Autowired
	SlaHeaderMappingRepository slaHeaderMappingRepository;

	public APIResponse createSlaData(SlaRequestData requestData) {
		
		SlaResponseData response = new SlaResponseData();
		
		SlaHeaderModel headerInfo = slaHeaderRepository.save(SlaConverter.convertSlaRequestDataToHeaderModel(requestData));
		
		if(headerInfo != null && headerInfo.getId() > 0) {
			if(requestData.getSlaLines() != null && requestData.getSlaLines().size() > 0) {
				
				response.setId(headerInfo.getId());
				response.setOrgId(headerInfo.getOrgId());
				response.setBusinessUnit(headerInfo.getBusinessUnit());
				response.setBusiness(headerInfo.getBusiness());
				response.setLocation(headerInfo.getLocation());
				response.setCostCenter(headerInfo.getCostCenter());
				response.setSerivceType(headerInfo.getSerivceType());
				response.setShiftId(headerInfo.getShiftId());
				response.setSlaTime(headerInfo.getSlaTime());
				response.setStartDate(headerInfo.getStartDate());
				response.setEndDate(headerInfo.getEndDate());
				response.setCreatedBy(headerInfo.getCreatedBy());
				response.setCreatedDate(headerInfo.getCreatedDate());
				
				List<SlaLinesRequestData> lineReponse = new ArrayList<>();
				
				for(SlaLinesRequestData line : requestData.getSlaLines()) {
					
					Optional<SlaLinesModel> lineSysData = null;
					if(line.getId() != null && line.getId() > 0) {
						lineSysData = slaLinesRepository.findById(line.getId());
					}
					
					SlaLinesModel lineData = slaLinesRepository.save(SlaConverter.convertSlaLinesRequestDataToLinesModel(headerInfo.getId(),line, lineSysData));
					if(lineData != null && lineData.getId() > 0) {
						lineReponse.add(SlaConverter.convertLinesModelToResponseData(headerInfo.getId(), lineData));
					}
					response.setSlaLines(lineReponse);
				}
			}
		}		
		
		return new APIResponse(HttpStatus.CREATED.value(), "Successfully created", Collections.singletonList(response));
	}

	public APIResponse updateSlaData(Long headerId, SlaRequestData requestData) {
		
		SlaResponseData response = new SlaResponseData();
		
		Optional<SlaHeaderModel> slaHeaderInfo = slaHeaderRepository.findById(headerId);
		if(slaHeaderInfo.isPresent() == false) {
			return new APIResponse(HttpStatus.NOT_FOUND.value(), "No data found in the system with provided id", Collections.emptyList());
		}
		
		SlaHeaderModel headerInfo = slaHeaderRepository.save(SlaConverter.convertSlaUpdateRequestDataToHeaderModel(requestData, slaHeaderInfo.get()));
		
		if(headerInfo != null && headerInfo.getId() > 0) {
			if(requestData.getSlaLines() != null && requestData.getSlaLines().size() > 0) {
				
				response.setId(headerInfo.getId());
				response.setOrgId(headerInfo.getOrgId());
				response.setBusinessUnit(headerInfo.getBusinessUnit());
				response.setBusiness(headerInfo.getBusiness());
				response.setLocation(headerInfo.getLocation());
				response.setCostCenter(headerInfo.getCostCenter());
				response.setSerivceType(headerInfo.getSerivceType());
				response.setShiftId(headerInfo.getShiftId());
				response.setSlaTime(headerInfo.getSlaTime());
				response.setStartDate(headerInfo.getStartDate());
				response.setEndDate(headerInfo.getEndDate());
				response.setCreatedBy(headerInfo.getCreatedBy());
				response.setCreatedDate(headerInfo.getCreatedDate());
				
				List<SlaLinesRequestData> lineReponse = new ArrayList<>();
				
				for(SlaLinesRequestData line : requestData.getSlaLines()) {
					
					Optional<SlaLinesModel> dbLineInfo = Optional.empty();
					
					if(line.getId() != null && line.getId() > 0) {
						dbLineInfo = slaLinesRepository.findById(line.getId());
					}
					
					SlaLinesModel lineData = slaLinesRepository.save(SlaConverter.convertSlaLinesUpdateRequestDataToLinesModel(line, dbLineInfo));
					if(lineData != null && lineData.getId() > 0) {
						lineReponse.add(SlaConverter.convertLinesModelToResponseData(headerInfo.getId(), lineData));
					}
					response.setSlaLines(lineReponse);
				}
			}
		}		
		
		return new APIResponse(HttpStatus.OK.value(), "Successfully data updated", Collections.singletonList(response));
		
	}
	
	public APIResponse getSlaInfoById(Long headerId) {
		
		SlaResponseData response = new SlaResponseData();
		
		Optional<SlaHeaderModel> headerInfo = slaHeaderRepository.findById(headerId);
		
		if (headerInfo.isPresent()) {
			
			response.setId(headerInfo.get().getId());
			response.setOrgId(headerInfo.get().getOrgId());
			response.setBusinessUnit(headerInfo.get().getBusinessUnit());
			response.setBusiness(headerInfo.get().getBusiness());
			response.setLocation(headerInfo.get().getLocation());
			response.setCostCenter(headerInfo.get().getCostCenter());
			response.setSerivceType(headerInfo.get().getSerivceType());
			response.setShiftId(headerInfo.get().getShiftId());
			response.setSlaTime(headerInfo.get().getSlaTime());
			response.setStartDate(headerInfo.get().getStartDate());
			response.setEndDate(headerInfo.get().getEndDate());
			response.setCreatedBy(headerInfo.get().getCreatedBy());
			response.setCreatedDate(headerInfo.get().getCreatedDate());
			response.setUpdatedBy(headerInfo.get().getUpdatedBy());
			response.setUpdatedDate(headerInfo.get().getUpdatedDate());
			
			List<SlaLinesModel> linesData = slaLinesRepository.findByHeaderId(headerId);
			
			if(linesData != null && linesData.size() > 0) {
				response.setSlaLines(SlaConverter.convertLinesModelToResponseLinesListData(linesData));
			}
			
			return new APIResponse(HttpStatus.OK.value(), "Sla list data", Collections.singletonList(response));
			
		} else {
			return new APIResponse(HttpStatus.NOT_FOUND.value(), "No data found in the system", Collections.emptyList());
		}
	}
	
	public APIResponsePaging getPaginationByOrganization(Integer orgId,  Integer businessUnitId, Integer costCenterId, Integer locationId, int pageNo, int pageSize, String sortBy,
			SortType sortType) {
		
		Pageable pageable = PageRequest.of(pageNo, pageSize,
				sortType == SortType.asc ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
		
		if (orgId != null) {			
			
			Page<SlaHeaderMappingModel> slaHeadersList = slaHeaderMappingRepository.findAll((root, query, criteriaBuilder) -> {
				
				Collection<Predicate> predicates = new ArrayList<>();
				
				predicates.add(criteriaBuilder.isTrue(criteriaBuilder.literal(Boolean.TRUE)));
				
				if (Objects.nonNull(orgId) && orgId != 0) {
					predicates.add(criteriaBuilder.equal(root.get("orgId"), orgId));
				}				
				if (Objects.nonNull(locationId) && locationId != 0) {
					predicates.add(criteriaBuilder.equal(root.get("location"), locationId));
				}
				if (Objects.nonNull(businessUnitId) && businessUnitId != 0) {
					predicates.add(criteriaBuilder.equal(root.get("businessUnit"), businessUnitId));
				}
				if (Objects.nonNull(costCenterId) && costCenterId != 0) {
					predicates.add(criteriaBuilder.equal(root.get("costCenter"), costCenterId));
				}

				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
				
			}, pageable);			
			
			return new APIResponsePaging(HttpStatus.OK.value(), "Sla list details", slaHeadersList.getContent(),
					new ArrayList<>(), slaHeadersList.getNumber(), slaHeadersList.getTotalElements(),
					slaHeadersList.getTotalPages());
		}else {
			return new APIResponsePaging(HttpStatus.NOT_FOUND.value(), "No data found in the system", Collections.emptyList(), new ArrayList<>());
		}		
	}

	public APIResponse getSlaInfoByOrgId(Long orgId) {

		List<Map<String, String>> listData = slaHeaderRepository.findByOrgIdList(orgId);
		
		if (listData != null && listData.size() > 0) {
			
			return new APIResponse(HttpStatus.OK.value(), "Sla list details", listData);
		} else {
			
			return new APIResponse(HttpStatus.NOT_FOUND.value(), "No data found in the system", Collections.emptyList());
		}

	}
	
	public APIResponse createSlaHeaderData(@Valid SlaHeaderRequestData requestData) {

		SlaHeaderModel headerInfo = slaHeaderRepository.save(SlaConverter.convertSlaHeaderRequestDataToHeaderModel(requestData));

		if (headerInfo != null && headerInfo.getId() > 0) {
			return new APIResponse(HttpStatus.CREATED.value(), "Successfully created", Collections.singletonList(headerInfo));
		}else {
			return new APIResponse(HttpStatus.FAILED_DEPENDENCY.value(), "Failed, Data not saved", Collections.emptyList());
		}
		
	}
	
	public APIResponse updateSlaHeaderData(Long headerId, @Valid SlaHeaderRequestData requestData) {
		
		Optional<SlaHeaderModel> slaHeaderInfo = slaHeaderRepository.findById(headerId);
		if(slaHeaderInfo.isPresent() == false) {
			return new APIResponse(HttpStatus.NOT_FOUND.value(), "No data found in the system with provided header id", Collections.emptyList());
		}
		
		SlaHeaderModel headerInfo = slaHeaderRepository.save(SlaConverter.convertSlaHeaderUpdateRequestDataToHeaderModel(headerId, requestData));

		if (headerInfo != null && headerInfo.getId() > 0) {
			return new APIResponse(HttpStatus.CREATED.value(), "Successfully updated", Collections.singletonList(headerInfo));
		}else {
			return new APIResponse(HttpStatus.FAILED_DEPENDENCY.value(), "Failed, Data not updated", Collections.emptyList());
		}
	}
	
	public APIResponse createSlaHeaderLinesData(Long headerId, @Valid List<SlaLinesRequestData> requestData) {
		
		Optional<SlaHeaderModel> slaHeaderInfo = slaHeaderRepository.findById(headerId);
		if(slaHeaderInfo.isPresent() == false) {
			return new APIResponse(HttpStatus.NOT_FOUND.value(), "No data found in the system with provided header id", Collections.emptyList());
		}
		
		if(requestData != null && requestData.size() > 0) {
			
			List<SlaLinesModel> response = new ArrayList<SlaLinesModel>();
			
			for(SlaLinesRequestData line : requestData) {
				
				Optional<SlaLinesModel> lineSysData = null;
				if(line.getId() != null && line.getId() > 0) {
					lineSysData = slaLinesRepository.findById(line.getId());
				}
				
				SlaLinesModel lineData = slaLinesRepository.save(SlaConverter.convertSlaLinesRequestDataToLinesModel(headerId,line, lineSysData));
				if(lineData != null && lineData.getId() > 0) {
					response.add(lineData);
				}
			}
			
			return new APIResponse(HttpStatus.CREATED.value(), "Lines data saved", response);
			
		}else {
			return new APIResponse(HttpStatus.FAILED_DEPENDENCY.value(), "Invalid request data", Collections.emptyList());
		}
		
	}
	
	
	
	
	
	
//  ==============================================================================================
//	==================================== Sla Lines old code ======================================
//  ==============================================================================================	

	public APIResponse createLine(List<SlaLinesModel> model) {
		try {

			List<SlaLinesModel> list = slaLinesRepository.saveAll(model);
			return new APIResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED.name(), list);

		} catch (Exception ex) {
			ex.printStackTrace();
			return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name(),
					new ArrayList<>());
		}
	}

	public APIResponse updateLine(List<SlaLinesModel> model) {
		try {
			List<SlaLinesModel> list = slaLinesRepository.saveAll(model);
			return new APIResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED.name(), list);

		} catch (Exception ex) {
			ex.printStackTrace();
			return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name(),
					new ArrayList<>());
		}
	}

	public APIResponse getByLineId(Long id) {
		try {
			Optional<SlaLinesModel> opt = slaLinesRepository.findById(id);
			List<SlaLinesModel> list = new ArrayList<>();
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

	public APIResponse getByHeaderId(Long headerId) {
		try {
			List<SlaLinesModel> list = slaLinesRepository.findByHeaderId(headerId);
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

	public APIResponse getByOrgLineId(Long orgId) {
		try {
			List<Map<String, String>> list = slaLinesRepository.findByOrgIdList(orgId);
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

	public APIResponsePaging getPagenationByLineOrganization(Long orgId, int pageNo, int pageSize, String sortBy,
			SortType sortType) {
		Pageable pageable = PageRequest.of(pageNo, pageSize,
				sortType == SortType.asc ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
		if (orgId != null) {
			Page<Map<String, String>> findByOrganizationId = slaLinesRepository.findByOrgId(orgId, pageable);
			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), findByOrganizationId.getContent(),
					new ArrayList<>(), findByOrganizationId.getNumber(), findByOrganizationId.getTotalElements(),
					findByOrganizationId.getTotalPages());
		} 
		return null;
	}

	

	

	
}
