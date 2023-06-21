package com.travel.travtronics.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.travel.travtronics.converter.CustomerAddressConverter;
import com.travel.travtronics.enums.SortType;
import com.travel.travtronics.eserv.model.CustomerPid;
import com.travel.travtronics.eserv.repository.CustomerPidRepository;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.APIResponsePaging;
import com.travel.travtronics.response.CustomerPidResponse;
import com.travel.travtronics.response.MessageResponse;

@Service
public class CustomerPidService {

	@Autowired
	CustomerPidRepository customerPidRepository;

	public APIResponse createCustomerPid(CustomerPid model) {
		try {

			List<CustomerPid> pid = new ArrayList<>();
			if (model.getRefId() == 0 || model.getRefId() == null)
				return new APIResponse(HttpStatus.BAD_REQUEST.value(), " CustomerPId Should Not Be Empty Or Zero",
						new ArrayList<>());
			else if (model.getType() == 0 || model.getType() == null)
				return new APIResponse(HttpStatus.BAD_REQUEST.value(), " Type Should Not Be Empty Or Zero",
						new ArrayList<>());
			else if (model.getValue() == null || model.getValue().isBlank())
				return new APIResponse(HttpStatus.BAD_REQUEST.value(), "FisrtName Should Not Be Empty ",
						new ArrayList<>());
			else if (model.getFromDate() == null)
				return new APIResponse(HttpStatus.BAD_REQUEST.value(), "FromDate Should Not Be Empty ",
						new ArrayList<>());
			else if (model.getToDate() == null)
				return new APIResponse(HttpStatus.BAD_REQUEST.value(), "ToDate Should Not Be Empty ",
						new ArrayList<>());
			else if (model.getCreatedBy() == 0 || model.getCreatedBy() == null)
				return new APIResponse(HttpStatus.BAD_REQUEST.value(), "CreatedBy Should Not Be Empty Or Zero ",
						new ArrayList<>());
			else {
				CustomerPid save = customerPidRepository.save(model);
				pid.add(save);
				return new APIResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED.name(), pid);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name(),
					new ArrayList<>());
		}
	}

	public APIResponse getByPidId(Long id) {
		try {
			Optional<CustomerPid> pid = customerPidRepository.findById(id);
			List<CustomerPid> list = new ArrayList<>();
			if (pid.isPresent()) {
				list.add(pid.get());
				return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), list);
			} else {
				return new APIResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), new ArrayList<>());
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name(),
					new ArrayList<>());
		}
	}

	public APIResponse getAllPid() {
		try {
			List<Map<String,String>> list = customerPidRepository.findAllList();
			return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), list);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name(),
					new ArrayList<>());
		}
	}

	public APIResponse getPidByRefId(Long refId,Long organizationId) {
		try {
			List<Map<String,String>> list = customerPidRepository.findByRefIdAndOrganizationId(refId,organizationId);
			if (!list.isEmpty()) {

				return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), list);
			} else {
				return new APIResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), new ArrayList<>());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name(),
					new ArrayList<>());
		}
	}

	public MessageResponse updateCustomerPid(CustomerPid model) {
		try {
			if (model.getRefId() == 0 || model.getRefId() == null)
				return new MessageResponse(HttpStatus.BAD_REQUEST.value(), " PId Should Not Be Empty Or Zero");
			else if (model.getType() == 0 || model.getType() == null)
				return new MessageResponse(HttpStatus.BAD_REQUEST.value(), " Type Should Not Be Empty Or Zero");
			else if (model.getValue() == null || model.getValue().isBlank())
				return new MessageResponse(HttpStatus.BAD_REQUEST.value(), "FisrtName Should Not Be Empty ");
			else if (model.getFromDate() == null)
				return new MessageResponse(HttpStatus.BAD_REQUEST.value(), "FromDate Should Not Be Empty ");
			else if (model.getToDate() == null)
				return new MessageResponse(HttpStatus.BAD_REQUEST.value(), "ToDate Should Not Be Empty ");
			else if (model.getUpdatedBy() == 0 || model.getUpdatedBy() == null)
				return new MessageResponse(HttpStatus.BAD_REQUEST.value(), "UpdatedBy Should Not Be Empty Or Zero ");
			Optional<CustomerPid> def = customerPidRepository.findById(model.getId());
			if (def.isPresent()) {

				model.setCreatedBy(def.get().getCreatedBy());
				customerPidRepository.save(model);
				return new MessageResponse(HttpStatus.OK.value(), HttpStatus.OK.name());
			} else {
				return new MessageResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name());
			}

		} catch (Exception ex) {

			return new MessageResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
					HttpStatus.INTERNAL_SERVER_ERROR.name());
		}
	}

	public APIResponse createMultipleCustomerPids(List<CustomerPid> models) {
		List<CustomerPid> pid = new ArrayList<>();
		for (CustomerPid model : models) {

			if (model.getRefId() == 0 || model.getRefId() == null)
				return new APIResponse(HttpStatus.BAD_REQUEST.value(), " refId Should Not Be Empty Or Zero",
						new ArrayList<>());
			else if (model.getType() == 0 || model.getType() == null)
				return new APIResponse(HttpStatus.BAD_REQUEST.value(), " Type Should Not Be Empty Or Zero",
						new ArrayList<>());
			else if (model.getValue() == null || model.getValue().isBlank())
				return new APIResponse(HttpStatus.BAD_REQUEST.value(), "FisrtName Should Not Be Empty ",
						new ArrayList<>());
			else if (model.getFromDate() == null)
				return new APIResponse(HttpStatus.BAD_REQUEST.value(), "FromDate Should Not Be Empty ",
						new ArrayList<>());
			else if (model.getToDate() == null)
				return new APIResponse(HttpStatus.BAD_REQUEST.value(), "ToDate Should Not Be Empty ",
						new ArrayList<>());
			else if (model.getCreatedBy() == 0 || model.getCreatedBy() == null)
				return new APIResponse(HttpStatus.BAD_REQUEST.value(), "CreatedBy Should Not Be Empty Or Zero ",
						new ArrayList<>());
			else {
				CustomerPid save = customerPidRepository.save(model);
				pid.add(save);

			}

		}
		return new APIResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED.name(), pid);
	}

	public APIResponsePaging getPaginationByOrganization(Long organizationId, Long refId, int pageNo, int pageSize, String sortBy,SortType sortType) {
		Pageable pageable = PageRequest.of(pageNo, pageSize,
				sortType == SortType.asc ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
		if (organizationId != null) {
			Page<CustomerPid> findByOrganizationId = customerPidRepository.findByOrganizationIdAndRefId(organizationId, refId, pageable);
			Page<CustomerPidResponse> item = findByOrganizationId.map(model -> {
				CustomerPidResponse response = CustomerAddressConverter.convertCustomerPidModelToResponse(model);
				Optional<String> type= customerPidRepository.getTypeName(response.getType());
				Optional<String> cust= customerPidRepository.getBusinessName(response.getRefId());
				Optional<String> org= customerPidRepository.getOrganizationName(response.getOrganizationId());
				if(type.isPresent())
					response.setTypeName(type.get());
				if(cust.isPresent())
					response.setBusinessName(cust.get());
				if(org.isPresent())
					response.setOrganizationName(org.get());
				return response;
			});
			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), item.getContent(),
					new ArrayList<>(), item.getNumber(), item.getTotalElements(), item.getTotalPages());
		}else {
			Page<CustomerPid> findByOrganizationId = customerPidRepository.findAll(pageable);
			Page<CustomerPidResponse> item = findByOrganizationId.map(model -> {
				CustomerPidResponse response = CustomerAddressConverter.convertCustomerPidModelToResponse(model);
				Optional<String> type= customerPidRepository.getTypeName(response.getType());
				Optional<String> cust= customerPidRepository.getBusinessName(response.getRefId());
				Optional<String> org= customerPidRepository.getOrganizationName(response.getOrganizationId());
				if(type.isPresent())
					response.setTypeName(type.get());
				if(cust.isPresent())
					response.setBusinessName(cust.get());
				if(org.isPresent())
					response.setOrganizationName(org.get());
				return response;
			});
			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), item.getContent(),
					new ArrayList<>(), item.getNumber(), item.getTotalElements(), item.getTotalPages());
		}
	}
}
