package com.travel.travtronics.service;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.travel.travtronics.converter.CustomerAddressConverter;
import com.travel.travtronics.enums.SortType;
import com.travel.travtronics.enums.Status;
import com.travel.travtronics.eserv.model.Bank;
import com.travel.travtronics.eserv.model.CustomerAddress;
import com.travel.travtronics.eserv.repository.CustomerAddressRepository;
import com.travel.travtronics.request.CustomerAddressRequest;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.APIResponsePaging;
import com.travel.travtronics.response.MessageStatusResponse;
import com.travel.travtronics.util.NotFoundException;

@Service
public class CustomerAddressService {

	@Autowired
	CustomerAddressRepository customerAddressRepository;

	public MessageStatusResponse CreateAddress(@Valid CustomerAddressRequest requestModel) {
		customerAddressRepository.save(CustomerAddressConverter.convertSaveJsonToModel(requestModel));
		return new MessageStatusResponse(CREATED.value(), CREATED.name());
	}

	public APIResponse getById(Long addressId) throws NotFoundException {
		CustomerAddress model = customerAddressRepository.findById(addressId)
				.orElseThrow(() -> new NotFoundException(String.format("Given id :%id not found", addressId)));
		return new APIResponse(OK.value(), OK.name(),
				Collections.singletonList(CustomerAddressConverter.convertModelToJson(model)));
	}

	public APIResponse getAllCustomer(Long refId,Long organizationId) throws NotFoundException {
		List<Map<String,String>> addressList =
				customerAddressRepository.findAllByRefIdAndStatus(refId, organizationId,Status.Active);

		if (refId != 0 && refId != null && addressList != null && organizationId!=0 && organizationId != null ) {
			return new APIResponse(OK.value(), OK.name(), addressList);
		} else {
			throw new NotFoundException(String.format("Given id :%id not found", refId));
		}

	}

	public MessageStatusResponse editCustomer(Long addressId, CustomerAddressRequest requestModel)
			throws NotFoundException {
		CustomerAddress model = customerAddressRepository.findById(addressId)
				.orElseThrow(() -> new NotFoundException(String.format("Given id :%id not found", addressId)));
		customerAddressRepository.save(CustomerAddressConverter.convertUpdateJsonToModel(requestModel, model));
		return new MessageStatusResponse(OK.value(), OK.name());
	}

	public APIResponse page(Integer pageNo, Integer pageSize, String sortBy) {
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		return new APIResponse(OK.value(), OK.name(), customerAddressRepository.findAll(pageable).stream()
				.map(CustomerAddressConverter::convertModelToJson).collect(Collectors.toList()));
	}

	public APIResponsePaging getPaginationByOrganization(Long organizationId, int pageNo, int pageSize, String sortBy,SortType sortType) {
		Pageable pageable = PageRequest.of(pageNo, pageSize,
				sortType == SortType.asc ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
		if (organizationId != null) {
			Page<CustomerAddress> findByOrganizationId = customerAddressRepository.findByOrganizationId(organizationId, pageable);
			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), findByOrganizationId.getContent(),
					new ArrayList<>(), findByOrganizationId.getNumber(), findByOrganizationId.getTotalElements(),
					findByOrganizationId.getTotalPages());
		} else {
			Page<CustomerAddress> findAll = customerAddressRepository.findAll(pageable);
			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), findAll.getContent(),
					new ArrayList<>(), findAll.getNumber(), findAll.getTotalElements(), findAll.getTotalPages());
		}

	}
}
