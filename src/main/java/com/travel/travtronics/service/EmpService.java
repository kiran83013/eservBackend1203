package com.travel.travtronics.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.travel.travtronics.converter.EmpConverter;
import com.travel.travtronics.enums.SortType;
import com.travel.travtronics.eserv.model.Employee;
import com.travel.travtronics.eserv.repository.EmpFilterRepository;
import com.travel.travtronics.eserv.repository.EmpRepository;
import com.travel.travtronics.eserv.repository.EmployeeCustomPaginationRepository;
import com.travel.travtronics.request.EmpRequest;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.APIResponsePaging;
import com.travel.travtronics.response.EmpListDto;
import com.travel.travtronics.response.EmpResponse;
import com.travel.travtronics.response.MessageStatusResponse;
import com.travel.travtronics.util.NotFoundException;

@Service
public class EmpService {

	@Autowired
	EmpRepository empRepository;

	@Autowired
	EmpFilterRepository empFilterRepository;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public APIResponse createEmp(EmpRequest request) {

		Optional<Employee> empValidation = empRepository
				.findByPrimaryEmailAndPrimaryPhoneNumber(request.getPrimaryEmail(), request.getPrimaryPhoneNumber());

		if (!empValidation.isPresent()) {
			if (request.getDesignationId() != null && request.getDesignationName() != null
					&& request.getDesignationId() == 0 && !request.getDesignationName().isEmpty()) {
				designationValidation(request.getDesignationName(), request.getDesignationName(),
						request.getDesignationName(), request.getCreatedBy().intValue(), request.getCreatedDate(),
						true);
				logger.info(".......designation validation.........");
			}
			Employee model = EmpConverter.convertRequestToModel(request);

			return new APIResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED.name(),
					Collections.singletonList(empRepository.save(model)));
		} else {
			return new APIResponse(HttpStatus.CONFLICT.value(),
					"invalid-request-employee-already-exists-with-this-information", new ArrayList<>());
		}

	}

	public APIResponse getByid(Long id) throws NotFoundException {
		Employee findById = empRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Given id %s not found", id)));
		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(),
				Collections.singletonList(EmpConverter.convertModelToResponse(findById)));
	}

	public MessageStatusResponse updateEmp(EmpRequest request, Long id) {
		Optional<Employee> findById = empRepository.findById(id);

		if (findById.isEmpty())
			return new MessageStatusResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name());
		else {
			Employee model = EmpConverter.updateRequestToModel(request, id);
			empRepository.save(model);
			return new MessageStatusResponse(HttpStatus.OK.value(), HttpStatus.OK.name());
		}

	}

	public APIResponse getAll(Long organizationId) {
		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), empRepository.findAllList(organizationId));
	}

	public void designationValidation(String name, String code, String description, Integer createdBy, Date createdDate,
			Boolean status) {
		empRepository.designationNewValues(name, code, description, createdBy, createdDate, status);

	}

	public APIResponse getSearchByValue(String search) {
		List<EmpListDto> findBySearchParameter = empRepository.findBySearchParameter(search);

		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), findBySearchParameter);

	}

	public APIResponse searchEmpContact(String firstName, String lastName, String primaryEmail,
			Long primaryPhoneNumber) {
//		if (Objects.isNull(firstName) && Objects.isNull(lastName) && Objects.isNull(primaryEmail)
//				&& Objects.isNull(primaryPhoneNumber)) {
//			return new APIResponse(HttpStatus.CONFLICT.value(),
//					String.format("Provide Atleast One Parameter For Search Criteria"), new ArrayList<>());
//
//		}
		List<Map<String, String>> searchData = empRepository
				.findByFirstNameLikeOrLastNameLikeOrPrimaryEmailLikeOrPrimaryPhoneNumberLike(firstName, lastName,
						primaryEmail, primaryPhoneNumber);

		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), searchData);
	}

	public APIResponse searchEmpCustomer(Long customersId, String firstName, String lastName, String primaryEmail,
			Long primaryPhoneNumber) {
		List<Map<String, String>> searchData = empRepository.findByCoustmerIdSearch(customersId, firstName, lastName,
				primaryEmail, primaryPhoneNumber);

		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), searchData);
	}

	public APIResponse empFilter(Long organizationId, Long businessUnitId, Long departmentId, Long telephoneNumber,
			String firstName, String designationName, String primaryEmail, Long nationality, Date dob,
			String lastName) {
		List<Employee> empFilter = empFilterRepository.empFilters(organizationId, businessUnitId, departmentId,
				telephoneNumber, firstName, designationName, primaryEmail, nationality, dob, lastName);

		List<EmpResponse> responseList = EmpConverter.convertModelsToResponse(empFilter).stream().map(model -> {
			Optional<String> deptInfo = empRepository.getDept(model.getDepartmentId());
			Optional<String> orgInfo = empRepository.getOrg(model.getOrganizationId());
			Optional<String> buInfo = empRepository.getBu(model.getBusinessUnitId());
			Optional<String> countryInfo = empRepository.getCountryName(model.getNationality());
			if (deptInfo.isPresent())
				model.setDeptName(deptInfo.get());
			if (orgInfo.isPresent())
				model.setOrgName(orgInfo.get());
			if (buInfo.isPresent())
				model.setBuName(buInfo.get());
			if (countryInfo.isPresent())
				model.setCountryName(countryInfo.get());
			return model;

		}).collect(Collectors.toCollection(() -> new ArrayList<>()));
		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), responseList);
	}

//	public APIResponsePaging getPagenationByOrganization(Long organizationId, int pageNo, int pageSize, String sortBy, SortType sortType) {
//		Pageable pageable = PageRequest.of(pageNo, pageSize,
//				sortType == SortType.asc ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
//		if (organizationId != null ) {
//			Page<Employee> menuData = empRepository.findByOrganizationId(organizationId, pageable);
//			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), menuData.getContent(),
//					new ArrayList<>(), menuData.getNumber(), menuData.getTotalElements(), menuData.getTotalPages());
//		} else {
//			Page<Employee> esRegister = empRepository.findAll(pageable);
//			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), esRegister.getContent(),
//					new ArrayList<>(), esRegister.getNumber(), esRegister.getTotalElements(),
//					esRegister.getTotalPages());
//		}
//	}

	@Autowired
	EmployeeCustomPaginationRepository employeeCustomPaginationRepository;

	public APIResponsePaging getPagenationByOrganization(Long organizationId, Long businessUnitId, Long departmentId,
			Long primaryPhoneNumber, String firstName, String designationName, String primaryEmail, Long nationality,
			Date dob, String lastName, int pageNo, int pageSize, String sortBy, SortType sortType) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);

		Page<Employee> serviceMenuTypeData = employeeCustomPaginationRepository.fetchEmployeePagination(organizationId,
				businessUnitId, departmentId, primaryPhoneNumber, firstName, designationName, primaryEmail, nationality,
				dob, lastName, pageable, sortBy, sortType);

		Page<EmpResponse> pageEmpReponse = serviceMenuTypeData.map(entiy -> {

			EmpResponse response = EmpConverter.convertModelToResponse(entiy);
			Optional<String> deptInfo = empRepository.getDept(response.getDepartmentId());
			Optional<String> orgInfo = empRepository.getOrg(response.getOrganizationId());
			Optional<String> buInfo = empRepository.getBu(response.getBusinessUnitId());
			Optional<String> countryInfo = empRepository.getCountryName(response.getNationality());
			if (deptInfo.isPresent())
				response.setDeptName(deptInfo.get());
			if (orgInfo.isPresent())
				response.setOrgName(orgInfo.get());
			if (buInfo.isPresent())
				response.setBuName(buInfo.get());
			if (countryInfo.isPresent())
				response.setCountryName(countryInfo.get());
			return response;

		});

		/*
		 * List<EmpResponse> responseList =
		 * EmpConverter.convertModelsToResponses(serviceMenuTypeData).stream()
		 * .map(model -> { Optional<String> deptInfo =
		 * empRepository.getDept(model.getDepartmentId()); Optional<String> orgInfo =
		 * empRepository.getOrg(model.getOrganizationId()); Optional<String> buInfo =
		 * empRepository.getBu(model.getBusinessUnitId()); Optional<String> countryInfo
		 * = empRepository.getCountryName(model.getNationality()); if
		 * (deptInfo.isPresent()) model.setDeptName(deptInfo.get()); if
		 * (orgInfo.isPresent()) model.setOrgName(orgInfo.get()); if
		 * (buInfo.isPresent()) model.setBuName(buInfo.get()); if
		 * (countryInfo.isPresent()) model.setCountryName(countryInfo.get()); return
		 * model;
		 * 
		 * }).collect(Collectors.toCollection(() -> new ArrayList<>()));
		 */
		return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), pageEmpReponse.getContent(),
				new ArrayList<>(), pageEmpReponse.getNumber(), pageEmpReponse.getTotalElements(),
				pageEmpReponse.getTotalPages());
	}

}
