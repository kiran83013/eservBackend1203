package com.travel.travtronics.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.travel.travtronics.converter.CustomerBankConverter;
import com.travel.travtronics.converter.CustomerInfoConverter;
import com.travel.travtronics.cpuser.repository.FeUserModelRepository;
import com.travel.travtronics.enums.SortType;
import com.travel.travtronics.eserv.model.CustomerInfoModel;
import com.travel.travtronics.eserv.repository.CustomerInfoRepository;
import com.travel.travtronics.eserv.repository.CustomerSearchRepository;
import com.travel.travtronics.request.CustomerInfo;
import com.travel.travtronics.request.SearchBusinessDto;
import com.travel.travtronics.request.SupplierContactDto;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.APIResponsePaging;
import com.travel.travtronics.response.CustomerInfoResponse;
import com.travel.travtronics.response.CustomerSearchInfoResponse;
import com.travel.travtronics.response.MessageStatusResponse;
import com.travel.travtronics.util.ConflictException;
import com.travel.travtronics.util.NotFoundException;

@Service
public class CustomerService {

	private final CustomerInfoRepository customerInfoRepository;

	private final FeUserModelRepository userRepository;

	public CustomerService(CustomerInfoRepository customerInfoRepository, FeUserModelRepository userRepository) {
		super();
		this.customerInfoRepository = customerInfoRepository;
		this.userRepository = userRepository;
	}

	public APIResponse getAllCustomers(Long organizationId) {

		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(),
				customerInfoRepository.findAllCustomersByOrganizationId(organizationId));
	}

	public APIResponse saveCustomerInfo(CustomerInfo customerInfo) throws ConflictException {
		CustomerInfoModel customerCode = customerInfoRepository.findByBusinessName(customerInfo.getBusinessName());
		if (customerCode == null) {
			CustomerInfoModel model = CustomerInfoConverter.convertJsonToModel(customerInfo);
			customerInfoRepository.save(model);
			return new APIResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED.name(),
					Collections.singletonList(model));
		} else {
			throw new ConflictException(
					String.format("Given business name : %s already  available", customerInfo.getBusinessName()));
		}
	}

	public APIResponse getByCustomerId(Long customerId) throws NotFoundException {
		CustomerInfoModel model = customerInfoRepository.findByCustomerId(customerId);
		if (model != null) {
			CustomerInfo info = CustomerInfoConverter.convertModelToJson(model);
			return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), Collections.singletonList(info));
		} else {
			throw new NotFoundException(String.format("Given customerId : %s not found", customerId));
		}
	}

	public MessageStatusResponse updateCustomerInfo(CustomerInfo customerInfo) throws NotFoundException {
		CustomerInfoModel customerCode = customerInfoRepository
				.findByCustomerId(Long.valueOf(customerInfo.getCustomerId()));
		if (customerCode != null) {
			CustomerInfoModel model = CustomerInfoConverter.convertJsonToModel(customerInfo);
			customerInfoRepository.save(model);
			return new MessageStatusResponse(HttpStatus.OK.value(), HttpStatus.OK.name());
		} else {
			throw new NotFoundException(
					String.format("Given customer id : %s not valid", customerInfo.getCustomerId()));
		}
	}

	public APIResponse getCustomerByBusinessName(String businessName) {

		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(),
				customerInfoRepository.findByBusinessNameContainsAndIsCustomerTrue(businessName));
	}

	public APIResponse getSupplierByBusinessName(String businessName) {
		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(),
				customerInfoRepository.findByBusinessNameContainsAndIsSupplierTrue(businessName));
	}

	public APIResponse getSupplier() {
//		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(),
//				customerInfoRepository.findAllByIsSupplierTrue());
		List<CustomerInfo> collectedSupplierInfo = customerInfoRepository.findAllByIsSupplierTrue().stream()
				.map(CustomerInfoConverter::convertModelToJson).peek(supplier -> {

					SupplierContactDto supplierInfo = customerInfoRepository.getSupplierInfo(supplier.getCustomerId());

					if (supplierInfo != null) {
						supplier.setPrimaryContact(supplierInfo.getContact());
						supplier.setPrimaryEmail(supplierInfo.getPrimaryEmail());
						supplier.setPrimaryPhoneNumber(supplierInfo.getPrimaryPhoneNumber());
						supplier.setContactId(supplierInfo.getContactId());
					}
				}).collect(Collectors.toCollection(ArrayList::new));

		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), collectedSupplierInfo);
	}

	public APIResponse getUserCustomerContactInfo(Integer contactId, Integer customerId, Integer channelId) {

		Map<String, Object> mapData = new HashMap<>();
		List<Map<String, Object>> customerContactInfo = userRepository.findByContactIdAndCustomerId(contactId,
				customerId, channelId);
		List<Map<String, Object>> primaryContactInfo = userRepository.findByCustomerIdForPrimaryContact(customerId);

		mapData.put("customerContactInfo", customerContactInfo);
		mapData.put("primaryContactInfo", primaryContactInfo);
		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), Collections.singletonList(mapData));
	}

	public APIResponsePaging getPagenationByOrganization(Long organizationId, int pageNo, int pageSize, String sortBy,
			SortType sortType) {
		Pageable pageable = PageRequest.of(pageNo, pageSize,
				sortType == SortType.asc ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
		if (organizationId != null) {
			Page<CustomerInfoModel> findByOrganizationId = customerInfoRepository.findByOrganizationId(organizationId,
					pageable);
			Page<CustomerInfoResponse> item = findByOrganizationId.map(model -> {
				CustomerInfoResponse response = CustomerBankConverter.convertCustomerInfoModelToResponse(model);
				Optional<String> category = customerInfoRepository.getCategoryName(response.getCategory());
				Optional<String> type = customerInfoRepository.getTypeName(response.getType());
				Optional<String> indus = customerInfoRepository.getIndustryName(response.getIndustry());
//				Optional<String> srId = customerInfoRepository.getSrName(response.getSrId());
				if (category.isPresent())
					response.setCategoryName(category.get());
				;
				if (type.isPresent())
					response.setTypeName(type.get());
				if (indus.isPresent())
					response.setIndustryName(indus.get());
//				if (srId.isPresent())
//					response.setSrName(srId.get());
				return response;
			});
			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), item.getContent(),
					new ArrayList<>(), item.getNumber(), item.getTotalElements(), item.getTotalPages());
		} else {
			Page<CustomerInfoModel> findByOrganizationId = customerInfoRepository.findAll(pageable);
			Page<CustomerInfoResponse> item = findByOrganizationId.map(model -> {
				CustomerInfoResponse response = CustomerBankConverter.convertCustomerInfoModelToResponse(model);
				Optional<String> category = customerInfoRepository.getCategoryName(response.getCategory());
				Optional<String> type = customerInfoRepository.getTypeName(response.getType());
				Optional<String> indus = customerInfoRepository.getIndustryName(response.getIndustry());
//				Optional<String> srId = customerInfoRepository.getSrName(response.getSrId());
				if (category.isPresent())
					response.setCategoryName(category.get());
				
				if (type.isPresent())
					response.setTypeName(type.get());
				if (indus.isPresent())
					response.setIndustryName(indus.get());
//				if (srId.isPresent())
//					response.setSrName(srId.get());
				return response;
			});
			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), item.getContent(),
					new ArrayList<>(), item.getNumber(), item.getTotalElements(), item.getTotalPages());
		}
	}

	public ResponseEntity<CustomerInfoModel> saveCustomerInfoLead(@Valid CustomerInfoModel info)
			throws ConflictException {

		CustomerInfoModel customerCode = customerInfoRepository.findByBusinessName(info.getBusinessName());
		if (customerCode == null) {

			CustomerInfoModel savedCustomer = customerInfoRepository.save(info);

			return new ResponseEntity<CustomerInfoModel>(savedCustomer, HttpStatus.CREATED);

		} else {
			throw new ConflictException(
					String.format("please change the business name you Given business name : %s is already  available in the system ", info.getBusinessName()));
		}
	}

	@Autowired
	CustomerSearchRepository customerSearchRepository;

	public ResponseEntity<?> searchBusiness(SearchBusinessDto searchDto) {
		List<CustomerSearchInfoResponse> searchBusinessInfo = customerSearchRepository.searchUnitInfo(searchDto);
		return new ResponseEntity<>(searchBusinessInfo, HttpStatus.OK);

	}
}
