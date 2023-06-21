package com.travel.travtronics.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.travel.travtronics.converter.ContactHelper;
import com.travel.travtronics.converter.EmpConverter;
import com.travel.travtronics.enums.SortType;
import com.travel.travtronics.eserv.model.CustomerContactNew;
import com.travel.travtronics.eserv.model.CustomerSearchEntity;
import com.travel.travtronics.eserv.model.Employee;
import com.travel.travtronics.eserv.model.Pax;
import com.travel.travtronics.eserv.repository.CustomerContactNewCustom;
import com.travel.travtronics.eserv.repository.CustomerContactNewRepository;
import com.travel.travtronics.eserv.repository.CustomerInfoRepository;
import com.travel.travtronics.eserv.repository.CustomerSearchEntityRepository;
import com.travel.travtronics.eserv.repository.EmpRepository;
import com.travel.travtronics.eserv.repository.PaxRepository;
import com.travel.travtronics.request.EmpContactRequest;
import com.travel.travtronics.request.PaxContactRequest;
import com.travel.travtronics.request.SearchResponseDto;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.APIResponsePaging;
import com.travel.travtronics.response.ContactSearchResponse;
import com.travel.travtronics.response.CustomerSearchDto;
import com.travel.travtronics.response.EmpContactResponse;
import com.travel.travtronics.response.EmpResponse;
import com.travel.travtronics.response.MessageResponse;
import com.travel.travtronics.response.PaxContactResponse;
import com.travel.travtronics.util.SearchEntityContact;

@Service

public class CustomerContactNewService {

	@Autowired
	CustomerContactNewRepository customercontactnewrepository;

	@Autowired
	PaxRepository paxrepository;

	@Autowired
	EmpRepository empRepository;

	@Autowired
	CustomerContactNewCustom customercontactRepossitory;

	@Autowired
	CustomerInfoRepository customerRepository;
	@Autowired
	CustomerSearchEntityRepository customerSearchEntityRepository;

	public APIResponse createCustomerContact(CustomerContactNew model) {

		Optional<CustomerContactNew> customer = customercontactnewrepository
				.findByPrimaryEmailOrPrimaryPhoneNumber(model.getPrimaryEmail(), model.getPrimaryPhoneNumber());

		if (!customer.isPresent()) {
			CustomerContactNew save = customercontactnewrepository.save(model);
			return new APIResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED.name(),
					Collections.singletonList(save));
		} else {

			return new APIResponse(HttpStatus.CONFLICT.value(),
					String.format("Please Check Duplicate Entry Mail and Phone Number"), new ArrayList<>());
		}

	}

	public APIResponse getCustomerContact(Long id) {
		try {
			List<CustomerContactNew> opt = customercontactnewrepository.findAllById(id);
//			List<CustomerContactNew> list = new ArrayList<>();
			if (!opt.isEmpty()) {

//				list.add(opt.get());
				return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), opt);
			} else {
				return new APIResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), new ArrayList<>());
			}

		} catch (Exception ex) {
			// ex.printStackTrace();
			return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name(),
					new ArrayList<>());
		}
	}

	public APIResponse getCustomerContactList(Long organizationId, Long customerId) {
		try {
			List<Map<String, String>> list = customercontactnewrepository.findAllList(organizationId, customerId);
			return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), list);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name(),
					new ArrayList<>());
		}
	}

	public MessageResponse updatePax(CustomerContactNew model) {
		try {
			if (model.getPrefix() == 0 || model.getPrefix() == null)
				return new MessageResponse(HttpStatus.BAD_REQUEST.value(), " Prefix Should Not Be Empty Or Zero");

			if (model.getCustomerId() == 0 || model.getCustomerId() == null)
				return new MessageResponse(HttpStatus.BAD_REQUEST.value(), " CustomerId Should Not Be Empty Or Zero");
			if (model.getFirstName() == null || model.getFirstName().isBlank())
				return new MessageResponse(HttpStatus.BAD_REQUEST.value(), "FirstName Should Not Be Empty ");

			Optional<CustomerContactNew> def = customercontactnewrepository.findById(model.getId());
			if (def.isPresent()) {

				model.setPersonId(def.get().getPersonId() != null ? def.get().getPersonId() : 0L);
				model.setEmpId(def.get().getEmpId() != null ? def.get().getEmpId() : 0L);

				customercontactnewrepository.save(model);
				return new MessageResponse(HttpStatus.OK.value(), HttpStatus.OK.name());
			} else {
				return new MessageResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name());
			}

		} catch (Exception ex) {

			return new MessageResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
					HttpStatus.INTERNAL_SERVER_ERROR.name());
		}
	}

//	public MessageResponse updateByCustomerId(Long id, Long customerId, CustomerContactNew model) {
//		try {
//			if (model.getPrefix() == 0 || model.getPrefix() == null)
//				return new MessageResponse(HttpStatus.BAD_REQUEST.value(), " Prefix Should Not Be Empty Or Zero");
//			if (model.getCustomerId() == 0 || model.getCustomerId() == null)
//				return new MessageResponse(HttpStatus.BAD_REQUEST.value(), " CustomerId Should Not Be Empty Or Zero");
//			if (model.getFirstName() == null || model.getFirstName().isBlank())
//				return new MessageResponse(HttpStatus.BAD_REQUEST.value(), "FirstName Should Not Be Empty ");
//			Optional<CustomerContactNew> def = customercontactnewrepository.findByIdAndCustomerId(model.getId(),
//					model.getCustomerId());
//
//			if (def.isPresent()) {
//				Optional<CustomerContactNew> paxValidation = customercontactnewrepository
//						.findByPrimaryEmailAndPrimaryPhoneNumber(def.get().getPrimaryEmail(),
//								def.get().getPrimaryPhoneNumber());
//
//				if (!paxValidation.isPresent()) {
//					customercontactnewrepository.save(model);
//					return new MessageResponse(HttpStatus.OK.value(), HttpStatus.OK.name());
//				}else {
//					return new MessageResponse(HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT.name());}
//			} else {
//				return new MessageResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name());
//			}
//		} catch (Exception ex) {
//
//			return new MessageResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
//					HttpStatus.INTERNAL_SERVER_ERROR.name());
//		}
//	}

	public APIResponse searchCustomerContact(String firstName, String lastName, String primaryEmail,
			Long primaryPhoneNumber, Long customersId) {
		List<SearchResponseDto> searchData = customercontactRepossitory.findAllBySearch(firstName, lastName,
				primaryEmail, primaryPhoneNumber, customersId);
		if (firstName == null && lastName == null && primaryEmail == null && primaryPhoneNumber == null) {
			return new APIResponse(HttpStatus.CONFLICT.value(), String.format("Provide Atleast One Parameter"),
					new ArrayList<>());
		} else if (searchData != null && !searchData.isEmpty()) {
			List<SearchResponseDto> validatedSearchData = new ArrayList<>();
			for (SearchResponseDto dto : searchData) {
				if (Objects.isNull(dto.getContactId())) {
					validatedSearchData.add(dto);
					return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), validatedSearchData);
				} else if (customersId != dto.getCustomersId() || (customersId == dto.getCustomersId()
						&& (dto.getContactStatus() == false || Objects.isNull(dto.getContactStatus())))) {
					validatedSearchData.add(dto);
					return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), validatedSearchData);
				} else {
					return new APIResponse(HttpStatus.OK.value(), String.format("Contact Creation Not Allowed"),
							validatedSearchData);
				}
			}
			return new APIResponse();

		} else {
			return new APIResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), searchData);

		}
	}

	public APIResponse getCustomerId(Long customerId, Long organizationId) {
		try {
			List<Map<String, String>> list = customercontactnewrepository.findByCustomerIdAndOrganizationId(customerId,
					organizationId);
			return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), list);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name(),
					new ArrayList<>());
		}
	}

	// @Transactional
	public APIResponse createCustomerContactWrapper(PaxContactRequest request) {

		Optional<Pax> pax = paxrepository.findById(request.getPaxId());

		if (pax.isPresent()) {

			Optional<CustomerContactNew> contactvalidation = customercontactnewrepository
					.findByPrimaryEmailAndPrimaryPhoneNumber(request.getContactModel().getPrimaryEmail(),
							Long.valueOf(request.getContactModel().getPrimaryPhoneNumber()));

			if (!contactvalidation.isPresent()) {
				CustomerContactNew save = customercontactnewrepository.save(request.getContactModel());

				if (save.getDesignationName() != null && save.getDesignationId() != null && save.getDesignationId() == 0
						&& !save.getDesignationName().isEmpty()) {

					String name = save.getDesignationName();
					String code = save.getDesignationName();
					String description = save.getDesignationName();
					Integer createdBy = Integer.valueOf(save.getCreatedBy().intValue());
					Date createdDate = save.getCreatedDate();
					designationValidation(name, code, description, createdBy, createdDate);

				}

				PaxContactResponse response = new PaxContactResponse();
				save.setEmpId(request.getPaxId());
				response.setContactModel(save);
				return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(),
						Collections.singletonList(response));
			} else
				return new APIResponse(HttpStatus.CONFLICT.value(),
						String.format("Data Already Exists with primaryEmail :%s and primaryPhoneNumber :%s ",
								request.getContactModel().getPrimaryEmail(),
								request.getContactModel().getPrimaryPhoneNumber()),
						new ArrayList<>());

		} else {

			Optional<Pax> paxValidation = paxrepository.findByPrimaryEmailAndPrimaryPhoneNumber(
					request.getPaxModel().getPrimaryEmail(),
					Long.valueOf(request.getPaxModel().getPrimaryPhoneNumber()));
			Optional<CustomerContactNew> contactvalidation = customercontactnewrepository
					.findByPrimaryEmailAndPrimaryPhoneNumber(request.getPaxModel().getPrimaryEmail(),
							request.getPaxModel().getPrimaryPhoneNumber());

			if (!paxValidation.isPresent()) {
				Pax paxData = paxrepository.save(request.getPaxModel());
				if (paxData.getDesignationName() != null && paxData.getDesignationId() != null
						&& paxData.getDesignationId() == 0 && !paxData.getDesignationName().isEmpty()) {

					String name = paxData.getDesignationName();
					String code = paxData.getDesignationName();
					String description = paxData.getDesignationName();
					Integer createdBy = Integer.valueOf(paxData.getCreatedBy().intValue());
					Date createdDate = paxData.getCreatedDate();
					designationValidation(name, code, description, createdBy, createdDate);

				}

				PaxContactResponse response = new PaxContactResponse();
//				if (!contactvalidation.isPresent()) {
//					CustomerContactNew customerContactData = customercontactnewrepository
//							.save(ContactHelper.ConvertPaxToContact(paxData));
//					response.setContactModel(customerContactData);
//				}

				response.setPaxModel(paxData);
				return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(),
						Collections.singletonList(response));
			} else
				return new APIResponse(HttpStatus.CONFLICT.value(),
						String.format("Data Already Exists with primaryEmail :%s and primaryPhoneNumber :%s ",
								request.getPaxModel().getPrimaryEmail(), request.getPaxModel().getPrimaryPhoneNumber()),
						new ArrayList<>());
		}

	}

	public void designationValidation(String name, String code, String description, Integer createdBy,
			Date createdDate) {
		paxrepository.designationNewValues(name, code, description, createdBy, createdDate);

	}

	public APIResponse searchContact(String name, Long customerId, String primaryEmail, Long primaryPhoneNumber,
			String firstName, String lastName) {
		if (Objects.isNull(name) && Objects.isNull(customerId) && Objects.isNull(primaryEmail)
				&& Objects.isNull(primaryPhoneNumber) && firstName == null && lastName == null) {
			return new APIResponse(HttpStatus.CONFLICT.value(),
					String.format("Provide Atleast One Parameter For Search Criteria"), new ArrayList<>());

		}

		List<EmpResponse> searchData = customercontactRepossitory
				.findContactBySearchParameters(name, customerId, primaryEmail, primaryPhoneNumber, firstName, lastName)
				.stream().map(EmpConverter::convertModelToResponse).peek(emp -> {
					String customerName = emp.getCustomersId() != null && emp.getCustomersId() != 0
							? customerRepository.findBusinessInfoByCustomerId(emp.getCustomersId()).get()
							: null;
					emp.setCustomerName(customerName);
				}).collect(Collectors.toCollection(ArrayList::new));

		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), searchData);

	}

	public APIResponse custContactWrapper(EmpContactRequest request) {

		Optional<Employee> emp = empRepository.findById(request.getEmpId());

		if (emp.isPresent()) {
			Optional<CustomerContactNew> contactvalidation = customercontactnewrepository
					.findByPrimaryEmailAndPrimaryPhoneNumber(request.getContactModel().getPrimaryEmail(),
							Long.valueOf(request.getContactModel().getPrimaryPhoneNumber()));
			if (!contactvalidation.isPresent()) {
//						request.getContactModel().setEmpId(request.getEmpId());
				CustomerContactNew save = customercontactnewrepository.save(request.getContactModel());
//						save.setEmpId(request.getEmpId());
				if (save.getDesignationName() != null && save.getDesignationId() != null && save.getDesignationId() == 0
						&& !save.getDesignationName().isEmpty()) {

					String name = save.getDesignationName();
					String code = save.getDesignationName();
					String description = save.getDesignationName();
					Integer createdBy = Integer.valueOf(save.getCreatedBy().intValue());
					Date createdDate = save.getCreatedDate();
					designationValidation(name, code, description, createdBy, createdDate);

				}
				EmpContactResponse response = new EmpContactResponse();
				save.setEmpId(request.getEmpId());
				response.setContactModel(save);
				return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(),
						Collections.singletonList(response));
			} else {
				return new APIResponse(HttpStatus.CONFLICT.value(),
						String.format("Data Already Exists with primaryEmail :%s and primaryPhoneNumber :%s ",
								request.getContactModel().getPrimaryEmail(),
								request.getContactModel().getPrimaryPhoneNumber()),
						new ArrayList<>());
			}
		} else {
			Optional<Employee> empValidation = empRepository.findByPrimaryEmailAndPrimaryPhoneNumber(
					request.getEmpModel().getPrimaryEmail(),
					Long.valueOf(request.getEmpModel().getPrimaryPhoneNumber()));
			Optional<CustomerContactNew> contactvalidation = customercontactnewrepository
					.findByPrimaryEmailAndPrimaryPhoneNumber(request.getEmpModel().getPrimaryEmail(),
							request.getEmpModel().getPrimaryPhoneNumber());
			if (!empValidation.isPresent()) {
				Employee empData = empRepository.save(request.getEmpModel());
				if (empData.getDesignationName() != null && empData.getDesignationId() != null
						&& empData.getDesignationId() == 0 && !empData.getDesignationName().isEmpty()) {

					String name = empData.getDesignationName();
					String code = empData.getDesignationName();
					String description = empData.getDesignationName();
					Integer createdBy = Integer.valueOf(empData.getCreatedBy().intValue());
					Date createdDate = empData.getCreatedDate();
					designationValidation(name, code, description, createdBy, createdDate);

				}
				EmpContactResponse response = new EmpContactResponse();
				if (!contactvalidation.isPresent()) {
					CustomerContactNew customerContactData = customercontactnewrepository
							.save(ContactHelper.ConvertEmpToContact(empData));
					response.setContactModel(customerContactData);
				}
				response.setEmpModel(empData);
				return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(),
						Collections.singletonList(response));
			} else {
				return new APIResponse(HttpStatus.CONFLICT.value(),
						String.format("Data Already Exists with primaryEmail :%s and primaryPhoneNumber :%s ",
								request.getEmpModel().getPrimaryEmail(), request.getEmpModel().getPrimaryPhoneNumber()),
						new ArrayList<>());
			}
		}
	}

	public APIResponse searchContact(String searchParam, Long customerId) {
		List<ContactSearchResponse> searchedInfo = customercontactnewrepository
				.findBySearchParamAndCustomerId(searchParam, customerId).stream().map(model -> {
					ContactSearchResponse searchResponse = new ContactSearchResponse();

					BeanUtils.copyProperties(model, searchResponse);
					CustomerSearchDto buCCOrdLocInfo = customercontactnewrepository
							.getBuCCOrdLocInfo(model.getPersonId() != null ? model.getPersonId() : 0L);

					if (Objects.nonNull(buCCOrdLocInfo)) {
						searchResponse.setBuId(buCCOrdLocInfo.getBuId());
						searchResponse.setBuName(buCCOrdLocInfo.getBuName());
						searchResponse.setCcId(buCCOrdLocInfo.getCcId());

						searchResponse.setCcName(buCCOrdLocInfo.getCcName());
						searchResponse.setLocId(buCCOrdLocInfo.getLocId());
						searchResponse.setLocName(buCCOrdLocInfo.getLocName());
						searchResponse.setOrgId(buCCOrdLocInfo.getOrgId());
						searchResponse.setOrgName(buCCOrdLocInfo.getOrgName());
					}
					return searchResponse;
				}).collect(Collectors.toList());
		if (!searchedInfo.isEmpty())
			return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), searchedInfo);
		else
			return new APIResponse(HttpStatus.OK.value(),
					"you're-not-allowed-to-create-user-here-create-contact-first-then-follow-the-instructions",
					searchedInfo);
	}

	public APIResponse search(String firstName, String lastName, String primaryEmail, Long primaryPhoneNumber,
			Long customerId) {
		if (firstName == null && lastName == null && primaryEmail == null && primaryPhoneNumber == null
				&& customerId == null) {
			return new APIResponse(HttpStatus.OK.value(), "unable-to-search-provide-atleast-one-paarmeter",
					new ArrayList<>());
		}
		List<CustomerSearchEntity> searchedData = customerSearchEntityRepository.findAll(SearchEntityContact
				.findByContactSpecifications(firstName, lastName, primaryEmail, primaryPhoneNumber, customerId));
		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), searchedData);
	}

	public APIResponsePaging getPaginationByOrganization(Long organizationId, int pageNo, int pageSize, String sortBy,
			SortType sortType) {
		Pageable pageable = PageRequest.of(pageNo, pageSize,
				sortType == SortType.asc ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
		if (organizationId != null) {
			Page<CustomerContactNew> findByOrganizationId = customercontactnewrepository
					.findByOrganizationId(organizationId, pageable);
			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), findByOrganizationId.getContent(),
					new ArrayList<>(), findByOrganizationId.getNumber(), findByOrganizationId.getTotalElements(),
					findByOrganizationId.getTotalPages());
		} else {
			Page<CustomerContactNew> findAll = customercontactnewrepository.findAll(pageable);
			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), findAll.getContent(),
					new ArrayList<>(), findAll.getNumber(), findAll.getTotalElements(), findAll.getTotalPages());
		}
	}
}