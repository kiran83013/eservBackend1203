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

import com.travel.travtronics.converter.CustomerBankConverter;
import com.travel.travtronics.enums.SortType;
import com.travel.travtronics.eserv.model.CustomerBank;
import com.travel.travtronics.eserv.repository.CustomerBankRepository;
import com.travel.travtronics.request.CustomerBankDto;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.APIResponsePaging;
import com.travel.travtronics.response.MessageStatusResponse;

@Service
public class CustomerBankService {

	@Autowired
	CustomerBankRepository customerBankRepository;

	public APIResponse createCustomerBank(CustomerBankDto model) {
		CustomerBank bank = CustomerBankConverter.convertJsonToModel(model);
		CustomerBank save = customerBankRepository.save(bank);
		return new APIResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED.name(), Collections.singletonList(save));
	}

	public MessageStatusResponse modifyCustomerBank(CustomerBankDto model, Long id) {
		Optional<CustomerBank> opt = customerBankRepository.findById(id);
		if (opt.isPresent()) {
			CustomerBank updateJsonToModel = CustomerBankConverter.updateJsonToModel(model, id);
			customerBankRepository.save(updateJsonToModel);
			return new MessageStatusResponse(HttpStatus.OK.value(), HttpStatus.OK.name());
		} else
			return new MessageStatusResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name());
	}

	public APIResponse getAll() {
		List<Map<String,String>> list = customerBankRepository.findAllList();
		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), list);

	}

	public APIResponse fetchDataByRefId(Long refId,Long organizationId) {
		List<CustomerBank> bankList = customerBankRepository.findByRefIdAndOrganizationId(refId,organizationId);
		if (bankList != null && !bankList.isEmpty()) {

			return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(),
					CustomerBankConverter.convertModelsToResponses(bankList));
		} else
			return new APIResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), Collections.emptyList());
	
	}

	public APIResponse getById(Long id) {
		Optional<CustomerBank> findById = customerBankRepository.findById(id);

		if (findById.isPresent()) {
			return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(),
					Collections.singletonList(CustomerBankConverter.convertModelToResponse(findById.get())));

		} else {
			return new APIResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), Collections.emptyList());
		}
	}

	public APIResponsePaging getPaginationByOrganization(Long organizationId, Long refId, int pageNo, int pageSize, String sortBy,SortType sortType) {
		Pageable pageable = PageRequest.of(pageNo, pageSize,
				sortType == SortType.asc ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
		if (organizationId != null) {
			Page<Map<String, String>> findByOrganizationId = customerBankRepository.findByOrganizationIdAndRefId(organizationId, refId, pageable);
			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), findByOrganizationId.getContent(),
					new ArrayList<>(), findByOrganizationId.getNumber(), findByOrganizationId.getTotalElements(),
					findByOrganizationId.getTotalPages());
		} else {
			Page<Map<String, String>> findAll = customerBankRepository.findAllList(pageable);
			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), findAll.getContent(),
					new ArrayList<>(), findAll.getNumber(), findAll.getTotalElements(), findAll.getTotalPages());
		}

	}
}
