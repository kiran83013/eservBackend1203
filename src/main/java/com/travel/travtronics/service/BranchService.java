package com.travel.travtronics.service;

import java.util.ArrayList;
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

import com.travel.travtronics.converter.BranchConverter;
import com.travel.travtronics.enums.SortType;
import com.travel.travtronics.eserv.model.Bank;
import com.travel.travtronics.eserv.model.Branch;
import com.travel.travtronics.eserv.repository.BankRepository;
import com.travel.travtronics.eserv.repository.BranchRepository;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.APIResponsePaging;
import com.travel.travtronics.response.BranchResponse;
import com.travel.travtronics.response.MessageResponse;

@Service
public class BranchService {

	@Autowired
	BranchRepository branchrepository;

	public APIResponse createBranch(Branch model) {
		try {
			List<Branch> list = new ArrayList<>();
			if (model.getBank() == null || model.getBank() == 0)
				return new APIResponse(HttpStatus.BAD_REQUEST.value(), " Bank Should Not Be Empty Or Zero",
						new ArrayList<>());
			else if (model.getBranch() == null || model.getBranch().isBlank())
				return new APIResponse(HttpStatus.BAD_REQUEST.value(), " Branch Should Not Be Empty",
						new ArrayList<>());
			else if (model.getShortName() == null || model.getShortName().isBlank())
				return new APIResponse(HttpStatus.BAD_REQUEST.value(), " ShortName Should Not Be Empty",
						new ArrayList<>());
			else if (model.getStartDate() == null)
				return new APIResponse(HttpStatus.BAD_REQUEST.value(), " StartDate Should Not Be Empty",
						new ArrayList<>());
			Branch updatemodel = branchrepository.save(model);
			list.add(updatemodel);
			return new APIResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED.name(), list);

		} catch (Exception ex) {
//		 ex.printStackTrace();
			return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name(),
					new ArrayList<>());
		}
	}

	public APIResponse getBranchId(Long id) {
		try {
			Optional<Branch> opt = branchrepository.findById(id);
			List<Branch> list = new ArrayList<>();
			if (opt.isPresent()) {

				list.add(opt.get());
				return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), list);
			} else {
				return new APIResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), new ArrayList<>());
			}

		} catch (Exception ex) {
			// ex.printStackTrace();
			return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name(),
					new ArrayList<>());
		}
	}

	public APIResponse getBranch(Long orgId) {
		try {
			List<Map<String, String>> list = branchrepository.findAllList(orgId);
			return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), list);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name(),
					new ArrayList<>());
		}
	}

	public MessageResponse updateBranch(Branch model) {
		try {
			if (model.getBank() == null || model.getBank() == 0)
				return new MessageResponse(HttpStatus.BAD_REQUEST.value(), " Bank Should Not Be Empty Or Zero");
			else if (model.getBranch() == null || model.getBranch().isBlank())
				return new MessageResponse(HttpStatus.BAD_REQUEST.value(), " Branch Should Not Be Empty");
			else if (model.getShortName() == null || model.getShortName().isBlank())
				return new MessageResponse(HttpStatus.BAD_REQUEST.value(), " ShortName Should Not Be Empty");
			else if (model.getStartDate() == null)
				return new MessageResponse(HttpStatus.BAD_REQUEST.value(), " StartDate Should Not Be Empty");
			Optional<Branch> opt = branchrepository.findById(model.getId());
			if (opt.isPresent()) {
				model.setCreatedBy(opt.get().getCreatedBy());
				branchrepository.save(model);
				return new MessageResponse(HttpStatus.OK.value(), HttpStatus.OK.name());
			} else {
				return new MessageResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name());
			}

		} catch (Exception ex) {

			return new MessageResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
					HttpStatus.INTERNAL_SERVER_ERROR.name());
		}
	}

	@Autowired
	BankRepository bankRepository;

	public APIResponsePaging getPaginationByOrganization(Long organizationId, int pageNo, int pageSize, String sortBy,
			SortType sortType) {
		Pageable pageable = PageRequest.of(pageNo, pageSize,
				sortType == SortType.asc ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
//		if (organizationId != null) {
//			Page<Branch> findByOrganizationId = branchrepository.findByOrganizationId(organizationId, pageable);
//			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), findByOrganizationId.getContent(),
//					new ArrayList<>(), findByOrganizationId.getNumber(), findByOrganizationId.getTotalElements(),
//					findByOrganizationId.getTotalPages());
//		} else {
//			Page<Branch> findAll = branchrepository.findAll(pageable);
//			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), findAll.getContent(),
//					new ArrayList<>(), findAll.getNumber(), findAll.getTotalElements(), findAll.getTotalPages());
//		}
		if (organizationId != null) {
			Page<Branch> findByOrganizationId = branchrepository.findByOrganizationId(organizationId, pageable);
			Page<BranchResponse> item = findByOrganizationId.map(model -> {
				BranchResponse response = BranchConverter.convertBranchModelToResponse(model);
				Optional<Bank> findById = bankRepository.findById(response.getBank());
				Optional<String> branchCatName = branchrepository.getBranchCategoryName(response.getBrCtgy());
				Optional<String> branchTypeName = branchrepository.getBranchTypeName(response.getBrType());
				Optional<String> countryName = branchrepository.getCountryName(response.getCountry());
				Optional<String> currencyName = branchrepository.getCurrencyName(response.getCurrency());
				response.setBankName(findById.isPresent() ? findById.get().getName() : null);
				if (branchCatName.isPresent())
					response.setBranchCategoryName(branchCatName.get());
				if (branchTypeName.isPresent())
					response.setBranchTypeName(branchTypeName.get());
				if (countryName.isPresent())
					response.setCountryName(countryName.get());
				if (currencyName.isPresent())
					response.setCurrencyName(currencyName.get());
				return response;
			});
			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), item.getContent(),
					new ArrayList<>(), item.getNumber(), item.getTotalElements(), item.getTotalPages());
		} else {
			Page<Branch> findByOrganizationId = branchrepository.findAll(pageable);
			Page<BranchResponse> item = findByOrganizationId.map(model -> {
				BranchResponse response = BranchConverter.convertBranchModelToResponse(model);
				Optional<Bank> findById = bankRepository.findById(response.getBank());
				Optional<String> branchCatName = branchrepository.getBranchCategoryName(response.getBrCtgy());
				Optional<String> branchTypeName = branchrepository.getBranchTypeName(response.getBrType());
				Optional<String> countryName = branchrepository.getCountryName(response.getCountry());
				Optional<String> currencyName = branchrepository.getCurrencyName(response.getCurrency());
				response.setBankName(findById.isPresent() ? findById.get().getName() : null);
				if (branchCatName.isPresent())
					response.setBranchCategoryName(branchCatName.get());
				if (branchTypeName.isPresent())
					response.setBranchTypeName(branchTypeName.get());
				if (countryName.isPresent())
					response.setCountryName(countryName.get());
				if (currencyName.isPresent())
					response.setCurrencyName(currencyName.get());
				return response;
			});
			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), item.getContent(),
					new ArrayList<>(), item.getNumber(), item.getTotalElements(), item.getTotalPages());
		}
	}
}
