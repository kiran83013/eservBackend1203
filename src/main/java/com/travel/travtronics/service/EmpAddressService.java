package com.travel.travtronics.service;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

//import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.travel.travtronics.converter.EmpAddressConverter;
import com.travel.travtronics.enums.SortType;
import com.travel.travtronics.enums.Status;
import com.travel.travtronics.eserv.model.EmpAddress;
import com.travel.travtronics.eserv.repository.EmpAddressRepository;
import com.travel.travtronics.request.EmpAddressRequest;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.APIResponsePaging;
import com.travel.travtronics.response.EmpAddressResponse;
import com.travel.travtronics.response.MessageStatusResponse;
import com.travel.travtronics.util.ConflictException;
import com.travel.travtronics.util.NotFoundException;

@Service
public class EmpAddressService {

	@Autowired
	EmpAddressRepository empAddressRepository;

	public MessageStatusResponse save(EmpAddressRequest requestModel) throws ConflictException {
		empAddressRepository.save(EmpAddressConverter.covertSaveJsonToModel(requestModel));
		return new MessageStatusResponse(CREATED.value(), CREATED.name());
	}

	public APIResponse getById(Long addressId) throws NotFoundException {
		EmpAddress model = empAddressRepository.findById(addressId)
				.orElseThrow(() -> new NotFoundException(String.format("Given id :%id not found", addressId)));
		return new APIResponse(OK.value(), OK.name(),
				Collections.singletonList(EmpAddressConverter.convertModelToJson(model)));
	}

	public APIResponse getAllEmp(Long refId, Long organizationId) throws NotFoundException {
		List<EmpAddressResponse> addressList = EmpAddressConverter
				.convertListModelToListJson(empAddressRepository.findAllByRefIdAndOrganizationIdAndStatus(refId, organizationId, Status.Active));

		if (refId != 0 && refId != null && addressList != null) {
			return new APIResponse(OK.value(), OK.name(), addressList);
		} else {
			throw new NotFoundException(String.format("Given id :%id not found", refId));
		}

	}

	public MessageStatusResponse editEmpAddress(Long addressId, EmpAddressRequest requestModel)
			throws NotFoundException {
		EmpAddress model = empAddressRepository.findById(addressId)
				.orElseThrow(() -> new NotFoundException(String.format("Given id :%id not found", addressId)));
		empAddressRepository.save(EmpAddressConverter.convertUpdateJsonToModel(requestModel, model));
		return new MessageStatusResponse(OK.value(), OK.name());
	}

	public APIResponse Emppage(Integer pageNo, Integer pageSize, String sortBy) {
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		return new APIResponse(OK.value(), OK.name(), empAddressRepository.findAll(pageable).stream()
				.map(EmpAddressConverter::convertModelToJson).collect(Collectors.toList()));
	}

	public APIResponsePaging getPagenationByOrganization(Long organizationId, int pageNo, int pageSize, String sortBy,
			SortType sortType) {
		Pageable pageable = PageRequest.of(pageNo, pageSize,
				sortType == SortType.asc ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
		if (organizationId != null) {
			Page<EmpAddress> menuData = empAddressRepository.findByOrganizationId(organizationId, pageable);
			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), menuData.getContent(),
					new ArrayList<>(), menuData.getNumber(), menuData.getTotalElements(), menuData.getTotalPages());
		} else {
			Page<EmpAddress> esRegister = empAddressRepository.findAll(pageable);
			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), esRegister.getContent(),
					new ArrayList<>(), esRegister.getNumber(), esRegister.getTotalElements(),
					esRegister.getTotalPages());
		}
	}

}
