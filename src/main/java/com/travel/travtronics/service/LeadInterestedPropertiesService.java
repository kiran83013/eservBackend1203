package com.travel.travtronics.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.travel.travtronics.converter.LeadInterestedPropertiesConverter;
import com.travel.travtronics.enums.SortType;
import com.travel.travtronics.enums.Status;
import com.travel.travtronics.eserv.model.LeadInterestedProperties;
import com.travel.travtronics.eserv.repository.LeadInterestedPropertiesRepository;
import com.travel.travtronics.request.LeadInterestedPropertiesRequest;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.APIResponsePaging;
import com.travel.travtronics.response.LeadInterestedPropertiesReponse;

@Service
public class LeadInterestedPropertiesService {

	@Autowired
	LeadInterestedPropertiesRepository leadInterestedPropertiesRepository;

	public APIResponse createOld(List<LeadInterestedProperties> model) {
		try {
			List<LeadInterestedProperties> saveAll = leadInterestedPropertiesRepository.saveAll(model);
			return new APIResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED.name(), saveAll);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name(),
					new ArrayList<>());
		}
	}

	public APIResponse create(List<@Valid LeadInterestedPropertiesRequest> model) {
		for (LeadInterestedPropertiesRequest line : model) {
			line.getStatus();
			if (leadInterestedPropertiesRepository.countByUnitIdAndBusinessIdAndOrganizationIdAndStatus(line.getUnitId(),
					line.getBusinessId(), line.getOrganizationId(), Status.Active) > 0) {
				return new APIResponse(HttpStatus.CONFLICT.value(), "record already exists in our system");
			}
		}
		List<LeadInterestedProperties> save = leadInterestedPropertiesRepository.saveAll(
				model.stream().map(LeadInterestedPropertiesConverter::convertModelToResponses).collect(Collectors.toList()));
		return new APIResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED.name(), save);

	}

	public APIResponse upadte(List<LeadInterestedProperties> model) {
		try {
			List<LeadInterestedProperties> saveAll = leadInterestedPropertiesRepository.saveAll(model);
			return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), saveAll);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new APIResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), new ArrayList<>());
		}
	}

	public APIResponsePaging getByBusinessIdOld(Long businessId, Long organizationId, int pageNo, int pageSize,
			String sortBy, SortType sortType) {
		Pageable pageable = PageRequest.of(pageNo, pageSize,
				sortType == SortType.asc ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
		Page<Map<String, String>> serviceRequestByWiwData = leadInterestedPropertiesRepository
				.findAllByBusinessId(businessId, organizationId, pageable);
		return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), serviceRequestByWiwData.getContent(),
				new ArrayList<>(), serviceRequestByWiwData.getNumber(), serviceRequestByWiwData.getTotalElements(),
				serviceRequestByWiwData.getTotalPages());
	}

	public APIResponsePaging getByBusinessId(Long organizationId, Long businessId, int pageNo, int pageSize,
			String sortBy, SortType sortType) {
		Pageable pageable = PageRequest.of(pageNo, pageSize,
				sortType == SortType.asc ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
		if (organizationId != null) {
			Page<LeadInterestedProperties> findByOrganizationId = leadInterestedPropertiesRepository
					.findByOrganizationIdAndBusinessIdAndStatus(organizationId, businessId, Status.Active, pageable);
			Page<LeadInterestedPropertiesReponse> item = findByOrganizationId.map(model -> {
				LeadInterestedPropertiesReponse response = LeadInterestedPropertiesConverter
						.convertLIPModelToResponse(model);
				Optional<String> businessName = leadInterestedPropertiesRepository.getBusinessName(response.getBusinessId());
				Optional<String> unitName = leadInterestedPropertiesRepository.getUnitName(response.getUnitId());
				Optional<String> org = leadInterestedPropertiesRepository.getOrganizationName(response.getOrganizationId());
				Optional<String> bul = leadInterestedPropertiesRepository.getBuildingName(response.getUnitId());
				Optional<String> flor = leadInterestedPropertiesRepository.getFloorName(response.getUnitId());
				if (businessName.isPresent())
					response.setBusinessName(businessName.get());
				if (unitName.isPresent())
					response.setUnitName(unitName.get());
				if (org.isPresent())
					response.setOrganizationName(org.get());
				if (bul.isPresent())
					response.setBuildingName(bul.get());
				if (flor.isPresent())
					response.setFloorName(flor.get());
				return response;
			});
			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), item.getContent(),
					new ArrayList<>(), item.getNumber(), item.getTotalElements(), item.getTotalPages());
		} else {
			Page<LeadInterestedProperties> findByOrganizationId = leadInterestedPropertiesRepository.findAll(pageable);
			Page<LeadInterestedPropertiesReponse> item = findByOrganizationId.map(model -> {
				LeadInterestedPropertiesReponse response = LeadInterestedPropertiesConverter
						.convertLIPModelToResponse(model);
				Optional<String> businessName = leadInterestedPropertiesRepository.getBusinessName(response.getBusinessId());
				Optional<String> unitName = leadInterestedPropertiesRepository.getUnitName(response.getUnitId());
				Optional<String> org = leadInterestedPropertiesRepository.getOrganizationName(response.getOrganizationId());
				Optional<String> bul = leadInterestedPropertiesRepository.getBuildingName(response.getUnitId());
				Optional<String> flor = leadInterestedPropertiesRepository.getFloorName(response.getUnitId());
				if (businessName.isPresent())
					response.setBusinessName(businessName.get());
				if (unitName.isPresent())
					response.setUnitName(unitName.get());
				if (org.isPresent())
					response.setOrganizationName(org.get());
				if (bul.isPresent())
					response.setBuildingName(bul.get());
				if (flor.isPresent())
					response.setFloorName(flor.get());
				return response;
			});
			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), item.getContent(),
					new ArrayList<>(), item.getNumber(), item.getTotalElements(), item.getTotalPages());
		}
	}

}
