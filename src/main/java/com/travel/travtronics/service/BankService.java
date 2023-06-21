package com.travel.travtronics.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.travel.travtronics.converter.BankConverter;
import com.travel.travtronics.enums.SortType;
import com.travel.travtronics.eserv.model.Bank;
import com.travel.travtronics.eserv.repository.BankRepository;
import com.travel.travtronics.eserv.repository.TimeZoneRepository;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.APIResponsePaging;
import com.travel.travtronics.response.BankResponse;
import com.travel.travtronics.response.MessageResponse;

@Service
public class BankService {

	@Autowired
	BankRepository bankrepository;

	public APIResponse createBank(Bank model) {
		try {
			List<Bank> list = new ArrayList<>();
			if (model.getName() == null || model.getName().isBlank())
				return new APIResponse(HttpStatus.BAD_REQUEST.value(), " Name Should Not Be Empty", new ArrayList<>());
			else if (model.getShortName() == null || model.getShortName().isBlank())
				return new APIResponse(HttpStatus.BAD_REQUEST.value(), " ShortName Should Not Be Empty",
						new ArrayList<>());
			else if (model.getCountry() == null || model.getCountry() == 0)
				return new APIResponse(HttpStatus.BAD_REQUEST.value(), " Country Should Not Be Null Or Zero",
						new ArrayList<>());
			else if (model.getBankType() == null || model.getBankType() == 0)
				return new APIResponse(HttpStatus.BAD_REQUEST.value(), " BankType Should Not Be Null Or Zero",
						new ArrayList<>());
			else if (model.getBiz() == null || model.getBiz() == 0)
				return new APIResponse(HttpStatus.BAD_REQUEST.value(), " Business Should Not Be Null Or Zero",
						new ArrayList<>());
			else if (model.getStartDate() == null)
				return new APIResponse(HttpStatus.BAD_REQUEST.value(), " StartDate Should Not Be Empty",
						new ArrayList<>());
			Bank updatemodel = bankrepository.save(model);
			list.add(updatemodel);
			return new APIResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED.name(), list);

		} catch (Exception ex) {
			// ex.printStackTrace();
			return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name(),
					new ArrayList<>());
		}
	}

	public APIResponse getBankId(Long id) {
		try {
			Optional<Bank> opt = bankrepository.findById(id);
			List<Bank> list = new ArrayList<>();
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

	public APIResponse getBank(Long orgId) {
		try {
			List<Map<String, String>> list = bankrepository.findAllList(orgId);
			return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), list);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name(),
					new ArrayList<>());
		}
	}

	public MessageResponse updateBank(Bank model) {
		try {
			if (model.getName() == null || model.getName().isBlank())
				return new MessageResponse(HttpStatus.BAD_REQUEST.value(), " Name Should Not Be Empty");
			else if (model.getShortName() == null || model.getShortName().isBlank())
				return new MessageResponse(HttpStatus.BAD_REQUEST.value(), " ShortName Should Not Be Empty");
			else if (model.getCountry() == null || model.getCountry() == 0)
				return new MessageResponse(HttpStatus.BAD_REQUEST.value(), " Country Should Not Be Null Or Zero");
			else if (model.getBankType() == null || model.getBankType() == 0)
				return new MessageResponse(HttpStatus.BAD_REQUEST.value(), " BankType Should Not Be Null Or Zero");
			else if (model.getBiz() == null || model.getBiz() == 0)
				return new MessageResponse(HttpStatus.BAD_REQUEST.value(), " Business Should Not Be Null Or Zero");
			else if (model.getStartDate() == null)
				return new MessageResponse(HttpStatus.BAD_REQUEST.value(), " StartDate Should Not Be Empty");
			Optional<Bank> opt = bankrepository.findById(model.getId());
			if (opt.isPresent()) {
				model.setCreatedBy(opt.get().getCreatedBy());
				bankrepository.save(model);
				return new MessageResponse(HttpStatus.OK.value(), HttpStatus.OK.name());
			} else {
				return new MessageResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name());
			}

		} catch (Exception ex) {

			return new MessageResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
					HttpStatus.INTERNAL_SERVER_ERROR.name());
		}
	}

	public APIResponse getCheck(Integer id) {
		List<Map<String, String>> list = bankrepository.getList(id);
		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), list);
	}

	public APIResponsePaging getPaginationByOrganization(Long organizationId, int pageNo, int pageSize, String sortBy,
			SortType sortType) {
		Pageable pageable = PageRequest.of(pageNo, pageSize,
				sortType == SortType.asc ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
		if (organizationId != null) {
			List<Bank> findByOrganizationId = bankrepository.findByOrganizationId(organizationId);
			List<BankResponse> collectedResponse = BankConverter.convertModelToResponse(findByOrganizationId).stream()
					.map(bank -> {
						Optional<String> orgInfo = bankrepository.getOrg(bank.getOrganizationId());
						Optional<String> country = bankrepository.getCountryName(bank.getCountry());
						Optional<String> currency = bankrepository.getCurrencyName(bank.getCurrency());
						Optional<String> bankType = bankrepository.getBankTypeName(bank.getBankType());
						Optional<String> bankCategory = bankrepository.getBankCtgyName(bank.getBankCtgy());
						Optional<String> bizName = bankrepository.getBizName(bank.getBiz());
						if (orgInfo.isPresent())
							bank.setOrgName(orgInfo.get());
						if (country.isPresent())
							bank.setCountryName(country.get());
						if (currency.isPresent())
							bank.setCurrencyName(currency.get());
						if (bankType.isPresent())
							bank.setBankTypeName(bankType.get());
						if (bankCategory.isPresent())
							bank.setBankCtgyName(bankCategory.get());
						if (bizName.isPresent())
							bank.setBizName(bizName.get());
						return bank;
					}).collect(Collectors.toList());
			Long count = bankrepository.countByOrganizationId(organizationId);
			PageImpl<BankResponse> response = new PageImpl<>(collectedResponse, pageable, count);
			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), response.getContent(),
					new ArrayList<>(), response.getNumber(), response.getTotalElements(), response.getTotalPages());
		} else {
			List<BankResponse> collectedResponse = BankConverter.convertModelToResponse(bankrepository.findAll())
					.stream().map(bank -> {
						Optional<String> orgInfo = bankrepository.getOrg(bank.getOrganizationId());
						Optional<String> country = bankrepository.getCountryName(bank.getCountry());
						Optional<String> currency = bankrepository.getCurrencyName(bank.getCurrency());
						Optional<String> bankType = bankrepository.getBankTypeName(bank.getBankType());
						Optional<String> bankCategory = bankrepository.getBankCtgyName(bank.getBankCtgy());
						Optional<String> bizName = bankrepository.getBizName(bank.getBiz());
						if (orgInfo.isPresent())
							bank.setOrgName(orgInfo.get());
						if (country.isPresent())
							bank.setCountryName(country.get());
						if (currency.isPresent())
							bank.setCurrencyName(currency.get());
						if (bankType.isPresent())
							bank.setBankTypeName(bankType.get());
						if (bankCategory.isPresent())
							bank.setBankCtgyName(bankCategory.get());
						if (bizName.isPresent())
							bank.setBizName(bizName.get());
						return bank;
					}).collect(Collectors.toList());
			Long count = bankrepository.countAll();
			PageImpl<BankResponse> response = new PageImpl<>(collectedResponse, pageable, count);
			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), response.getContent(),
					new ArrayList<>(), response.getNumber(), response.getTotalElements(), response.getTotalPages());
		}

	}

	
	@Autowired
	TimeZoneRepository timeZoneRepository;

	public APIResponse getAllTimeZoneInfo() {
		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(),
				StreamSupport.stream(timeZoneRepository.findAll().spliterator(), false).collect(Collectors.toList()));
	}

}
