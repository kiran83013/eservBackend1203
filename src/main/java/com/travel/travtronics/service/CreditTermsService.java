package com.travel.travtronics.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.travel.travtronics.enums.SortType;
import com.travel.travtronics.eserv.model.CreditTerms;
import com.travel.travtronics.eserv.repository.CreditTermsRepository;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.APIResponsePaging;
import com.travel.travtronics.response.MessageResponse;


@Service
public class CreditTermsService {

	@Autowired
	CreditTermsRepository credittermsrepository;

	public APIResponse creategetCreditTerms(CreditTerms model) {
		try {
			List<CreditTerms > list=new ArrayList<>();
//			if(model.getCompany()==0 || model.getCompany()==null)
//				return new APIResponse(HttpStatus.BAD_REQUEST.value(), " Company Should Not Be Empty Or Zero", new ArrayList<>());
//			else if(model.getCostCenter() == 0 || model.getCostCenter() == null)
//				return new APIResponse(HttpStatus.BAD_REQUEST.value(), " CostCenter Should Not Be Empty Or Zero", new ArrayList<>());
//			else if(model.getLocation() == 0 || model.getLocation() == null)
//				return new APIResponse(HttpStatus.BAD_REQUEST.value(), " Location Should Not Be Empty Or Zero", new ArrayList<>());
//			else if(model.getStartDate() == null)
//				return new APIResponse(HttpStatus.BAD_REQUEST.value(), " StartDate Should Not Be Empty Or Zero", new ArrayList<>());
//			else if(model.getCustomerId()==0 || model.getCustomerId()==null)
//				return new APIResponse(HttpStatus.BAD_REQUEST.value(), " CustomerId Should Not Be Empty Or Zero", new ArrayList<>());
			CreditTerms updatemodel = credittermsrepository.save(model);
			list.add(updatemodel);
			return new APIResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED.name(),list );

		} catch (Exception ex) {
			// ex.printStackTrace();
			return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name(), new ArrayList<>());
		}
	}

	public APIResponse getCreditTermsId(Long id) {
		try {
			Optional<CreditTerms> opt = credittermsrepository.findById(id);
			List<CreditTerms> list = new ArrayList<>();
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

	public APIResponse getCreditTerms( Long organization) {
		try {
			List<CreditTerms> list = credittermsrepository.findAllByOrganization(organization);
			return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), list);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name(),
					new ArrayList<>());
		}
	}

	public MessageResponse updatePax(CreditTerms model) {
		try {
//			if(model.getCompany()==0 || model.getCompany()==null)
//				return new MessageResponse(HttpStatus.BAD_REQUEST.value(), " Company Should Not Be Empty Or Zero");
//			else if(model.getCostCenter() == 0 || model.getCostCenter() == null)
//				return new MessageResponse(HttpStatus.BAD_REQUEST.value(), " CostCenter Should Not Be Empty Or Zero");
//			else if(model.getLocation() == 0 || model.getLocation() == null)
//				return new MessageResponse(HttpStatus.BAD_REQUEST.value(), " Location Should Not Be Empty Or Zero");
//			else if(model.getStartDate() == null)
//				return new MessageResponse(HttpStatus.BAD_REQUEST.value(), " StartDate Should Not Be Empty Or Zero");
//			else if(model.getCustomerId()==0 || model.getCustomerId()==null)
//				return new MessageResponse(HttpStatus.BAD_REQUEST.value(), " CustomerId Should Not Be Empty Or Zero");
			Optional<CreditTerms> def = credittermsrepository.findById(model.getId());
			if (def.isPresent()) {
				model.setCreatedBy(def.get().getCreatedBy());
				credittermsrepository.save(model);
				return new MessageResponse(HttpStatus.OK.value(), HttpStatus.OK.name());
			} else {
				return new MessageResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name());
			}

		} catch (Exception ex) {

			return new MessageResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
					HttpStatus.INTERNAL_SERVER_ERROR.name());
		}
		}

		public APIResponse getCustomerList(Long customerId, Long organization) {
		try {
			Optional<CreditTerms> opt = credittermsrepository.findByCustomerIdAndOrganization(customerId,organization);
			List<CreditTerms> list = new ArrayList<>();
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

	public APIResponsePaging getPaginationByOrganization(Long organization, int pageNo, int pageSize, String sortBy,SortType sortType) {
		Pageable pageable = PageRequest.of(pageNo, pageSize,
				sortType == SortType.asc ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
		if (organization != null) {
			Page<CreditTerms> findByOrganizationId = credittermsrepository.findByOrganization(organization, pageable);
			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), findByOrganizationId.getContent(),
					new ArrayList<>(), findByOrganizationId.getNumber(), findByOrganizationId.getTotalElements(),
					findByOrganizationId.getTotalPages());
		} else {
			Page<CreditTerms> findAll = credittermsrepository.findAll(pageable);
			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), findAll.getContent(),
					new ArrayList<>(), findAll.getNumber(), findAll.getTotalElements(), findAll.getTotalPages());
		}

	}
}

