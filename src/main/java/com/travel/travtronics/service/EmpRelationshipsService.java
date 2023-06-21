package com.travel.travtronics.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.travel.travtronics.converter.EmpBankConverter;
import com.travel.travtronics.converter.EmpRelationShipsConverter;
import com.travel.travtronics.enums.SortType;
import com.travel.travtronics.eserv.model.EmpRelationships;
import com.travel.travtronics.eserv.repository.EmpRelationshipsReository;
import com.travel.travtronics.request.EmpRelationShipsRequest;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.APIResponsePaging;
import com.travel.travtronics.response.EmpRelationshipsResponse;
import com.travel.travtronics.response.MessageStatusResponse;
import com.travel.travtronics.util.NotFoundException;

@Service
public class EmpRelationshipsService {

	@Autowired
	EmpRelationshipsReository empRelationshipsReository;

	public MessageStatusResponse save(@Valid EmpRelationShipsRequest requestModel) {
		empRelationshipsReository.save(EmpRelationShipsConverter.convertRequestToModel(requestModel));
		return new MessageStatusResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED.name());
	}

	public MessageStatusResponse editRelationShips(@Valid EmpRelationShipsRequest requestModel)
			throws NotFoundException {

		Optional<EmpRelationships> request = empRelationshipsReository
				.findByRelationShipId(requestModel.getRelationShipId());

		if (request.isPresent()) {
			empRelationshipsReository.save(EmpRelationShipsConverter.updateRequestToModel(requestModel));
			return new MessageStatusResponse(HttpStatus.OK.value(), HttpStatus.OK.name());
		} else {
			throw new NotFoundException(
					String.format("Given relationShipId : %s not found", requestModel.getRelationShipId()));
		}
	}

	public APIResponse getInfoById(Long relationShipId) throws NotFoundException {
		Optional<EmpRelationships> request = empRelationshipsReository.findByRelationShipId(relationShipId);
		if (request.isPresent()) {

			return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(),
					Collections.singletonList(request.get()));
		} else {
			throw new NotFoundException(String.format("Given relationShipId : %s not found", relationShipId));
		}
	}

	public APIResponse getByFirstPartyId(Long firstPartyId) {
		List<Map<String, String>> list = empRelationshipsReository.findByFirstPartyId(firstPartyId);
		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), list);
	}

	public APIResponsePaging getPagenationByOrganization(Long organizationId, Long firstPartyId, int pageNo, int pageSize, String sortBy,
			SortType sortType) {
		Pageable pageable = PageRequest.of(pageNo, pageSize,
				sortType == SortType.asc ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
		if (organizationId != null) {
			Page<EmpRelationships> findByOrganizationId = empRelationshipsReository.findByOrganizationIdAndFirstPartyId(organizationId, firstPartyId,
					pageable);
			Page<EmpRelationshipsResponse> item = findByOrganizationId.map(model -> {
				EmpRelationshipsResponse response = EmpBankConverter.convertEmpRelationModelToResponse(model);
				Optional<String> firstParty = empRelationshipsReository.getFirstPartyName(response.getFirstPartyId());
				Optional<String> secondParty = empRelationshipsReository
						.getSecondPartyName(response.getSecondPartyId());
				Optional<String> fromRelation = empRelationshipsReository
						.getFromRelationName(response.getFromRelationId());
				Optional<String> toRelation = empRelationshipsReository.getToRelationName(response.getToRelationId());
				if (firstParty.isPresent())
					response.setFirstPartyIdName(firstParty.get());
				if (secondParty.isPresent())
					response.setSecondPartyIdName(secondParty.get());
				if (fromRelation.isPresent())
					response.setFromRelationIdName(fromRelation.get());
				if (toRelation.isPresent())
					response.setToRelationIdName(toRelation.get());
				return response;
			});
			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), item.getContent(),
					new ArrayList<>(), item.getNumber(), item.getTotalElements(), item.getTotalPages());
		} else {
			Page<EmpRelationships> findByOrganizationId = empRelationshipsReository.findAll(pageable);
			Page<EmpRelationshipsResponse> item = findByOrganizationId.map(model -> {
				EmpRelationshipsResponse response = EmpBankConverter.convertEmpRelationModelToResponse(model);
				Optional<String> firstParty = empRelationshipsReository.getFirstPartyName(response.getFirstPartyId());
				Optional<String> secondParty = empRelationshipsReository
						.getSecondPartyName(response.getSecondPartyId());
				Optional<String> fromRelation = empRelationshipsReository
						.getFromRelationName(response.getFromRelationId());
				Optional<String> toRelation = empRelationshipsReository.getToRelationName(response.getToRelationId());
				if (firstParty.isPresent())
					response.setFirstPartyIdName(firstParty.get());
				if (secondParty.isPresent())
					response.setSecondPartyIdName(secondParty.get());
				if (fromRelation.isPresent())
					response.setFromRelationIdName(fromRelation.get());
				if (toRelation.isPresent())
					response.setToRelationIdName(toRelation.get());
				return response;
			});
			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), item.getContent(),
					new ArrayList<>(), item.getNumber(), item.getTotalElements(), item.getTotalPages());
		}
	}
}
