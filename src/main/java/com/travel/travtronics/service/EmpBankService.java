package com.travel.travtronics.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.travel.travtronics.converter.EmpBankConverter;
import com.travel.travtronics.enums.SortType;
import com.travel.travtronics.eserv.model.EmpAddress;
import com.travel.travtronics.eserv.model.EmpBank;
import com.travel.travtronics.eserv.repository.EmpBankRepository;
import com.travel.travtronics.request.EmpBankDto;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.APIResponsePaging;
import com.travel.travtronics.response.MessageStatusResponse;




@Service
public class EmpBankService {
	
	@Autowired
	EmpBankRepository empBankRepository;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public APIResponse createEmpBank(EmpBankDto model) {
		EmpBank bank = EmpBankConverter.convertJsonToModel(model);
		EmpBank save = empBankRepository.save(bank);
		return new APIResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED.name(), Collections.singletonList(save));
	}

	public MessageStatusResponse modifyempBank(EmpBankDto model, Long id) {
		Optional<EmpBank> opt = empBankRepository.findById(id);
		if (opt.isPresent()) {
			EmpBank updateJsonToModel = EmpBankConverter.updateJsonToModel(model, id);
			empBankRepository.save(updateJsonToModel);
			return new MessageStatusResponse(HttpStatus.OK.value(), HttpStatus.OK.name());
		} else
			return new MessageStatusResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name());
	}

//	public APIResponse getAll() {
//		List<EmpBankResponse> responses = EmpBankConverter.convertModelsToResponses(empBankRepository.findAll());
//		responses.forEach(model -> {
//
//			fetchNamesById(model);
//
//			logger.info("fetching names by ids from db");
//
//		});
//		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), responses);
//
//	}
//
//	private void fetchNamesById(EmpBankResponse model) {
//		String bankAccountTye = empBankRepository.getBankAccountTye(Long.valueOf(model.getBankAccountType()));
//		String currency = empBankRepository.getCurrency(Long.valueOf(model.getCurrency()));
//
//		model.setBankAccountTypeName(bankAccountTye);
//		model.setCurrencyName(currency);
//		
//	}

	public APIResponse fetchDataByRefId(Long refId) {
		List<EmpBank> bankList = empBankRepository.findByRefId(refId);
		if (bankList != null && !bankList.isEmpty()) {

			return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(),
					EmpBankConverter.convertModelsToResponses(bankList));
		} else
			return new APIResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), Collections.emptyList());
	}

	public APIResponse fetchDataById(Long id) {
		Optional<EmpBank> findById = empBankRepository.findById(id);

		if (findById.isPresent()) {
			return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(),
					Collections.singletonList(EmpBankConverter.convertModelToResponse(findById.get())));

		} else {
			return new APIResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), Collections.emptyList());
		}
	}

	public APIResponse getAll(Long organizationId) {
		List<EmpBank> list = empBankRepository.findAllByOrganizationId(organizationId);
		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(),list);
	}

	public APIResponsePaging getPagenationByOrganization(Long organizationId, Long refId, int pageNo, int pageSize, String sortBy,SortType sortType) {
		Pageable pageable = PageRequest.of(pageNo, pageSize,
				sortType == SortType.asc ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
		if (organizationId != null) {
			Page<Map<String, String>> menuData = empBankRepository.findByOrganizationIdAndRefId(organizationId, refId, pageable);
			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), menuData.getContent(),
					new ArrayList<>(), menuData.getNumber(), menuData.getTotalElements(), menuData.getTotalPages());
		} else {
			Page<EmpBank> esRegister = empBankRepository.findAll(pageable);
			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), esRegister.getContent(),
					new ArrayList<>(), esRegister.getNumber(), esRegister.getTotalElements(),
					esRegister.getTotalPages());
		}
	}

}
