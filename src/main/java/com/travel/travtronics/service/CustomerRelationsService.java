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

import com.travel.travtronics.converter.CustomerRelationsConverter;
import com.travel.travtronics.enums.SortType;
import com.travel.travtronics.eserv.model.CustomerRelations;
import com.travel.travtronics.eserv.repository.CustomerRelationsRepository;
import com.travel.travtronics.request.CustomerRelationsRequest;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.APIResponsePaging;
import com.travel.travtronics.response.CustomerRelationsResponse;
import com.travel.travtronics.response.MessageStatusResponse;
import com.travel.travtronics.util.NotFoundException;

@Service
public class CustomerRelationsService {

	@Autowired
	CustomerRelationsRepository customerRelationsRepository;

	public MessageStatusResponse createCustomerRelations(@Valid CustomerRelationsRequest requestModel) {
		customerRelationsRepository.save(CustomerRelationsConverter.convertRequestToModel(requestModel));
		return new MessageStatusResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED.name());
	}

	public MessageStatusResponse editCustomerRelations(@Valid CustomerRelationsRequest requestModel)
			throws NotFoundException {
		Optional<CustomerRelations> request = customerRelationsRepository
				.findByRelationShipId(requestModel.getRelationShipId());

		if (request.isPresent()) {
			customerRelationsRepository.save(CustomerRelationsConverter.updateRequestToModel(requestModel));
			return new MessageStatusResponse(HttpStatus.OK.value(), HttpStatus.OK.name());
		} else {
			throw new NotFoundException(
					String.format("Given relationShipId : %s not found", requestModel.getRelationShipId()));
		}
	}

	public APIResponse getRelationsId(Long relationShipId) throws NotFoundException {
		Optional<CustomerRelations> request = customerRelationsRepository.findByRelationShipId(relationShipId);
		if (request.isPresent()) {

			return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(),
					Collections.singletonList(request.get()));
		} else {
			throw new NotFoundException(String.format("Given relationShipId : %s not found", relationShipId));
		}

	}

	public APIResponse getListByFirstPartyId(Long firstPartyId, Long organizationId) {
		List<Map<String, String>> list = customerRelationsRepository.findByFirstPartyIdAndOrganizationId(firstPartyId,organizationId);
		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), list);
	}

	public APIResponsePaging getPaginationByOrganization(Long organizationId, Long firstPartyId, int pageNo, int pageSize, String sortBy,
			SortType sortType) {
		Pageable pageable = PageRequest.of(pageNo, pageSize,
				sortType == SortType.asc ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
		if (organizationId != null) {
			Page<CustomerRelations> findByOrganizationId = customerRelationsRepository
					.findByOrganizationIdAndFirstPartyId(organizationId, firstPartyId, pageable);
			Page<CustomerRelationsResponse> item = findByOrganizationId.map(model -> {
				CustomerRelationsResponse response = CustomerRelationsConverter.convertResponseToModel(model);
				Optional<String> firstParty = customerRelationsRepository.getFirstPartyName(response.getFirstPartyId());
				Optional<String> secondParty = customerRelationsRepository
						.getSecondPartyName(response.getSecondPartyId());
				Optional<String> fromRelation = customerRelationsRepository
						.getFromRelationName(response.getFromRelationId());
				Optional<String> toRelation = customerRelationsRepository.getToRelationName(response.getToRelationId());
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
			Page<CustomerRelations> findByOrganizationId = customerRelationsRepository.findAll(pageable);
			Page<CustomerRelationsResponse> item = findByOrganizationId.map(model -> {
				CustomerRelationsResponse response = CustomerRelationsConverter.convertResponseToModel(model);
				Optional<String> firstParty = customerRelationsRepository.getFirstPartyName(response.getFirstPartyId());
				Optional<String> secondParty = customerRelationsRepository
						.getSecondPartyName(response.getSecondPartyId());
				Optional<String> fromRelation = customerRelationsRepository
						.getFromRelationName(response.getFromRelationId());
				Optional<String> toRelation = customerRelationsRepository.getToRelationName(response.getToRelationId());
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
