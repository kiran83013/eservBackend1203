package com.travel.travtronics.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.travel.travtronics.converter.CustomerRelationsConverter;
import com.travel.travtronics.enums.SortType;
import com.travel.travtronics.eserv.model.CustomerActivity;
import com.travel.travtronics.eserv.repository.CustomerActivityRepository;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.APIResponsePaging;
import com.travel.travtronics.response.CustomerActivityResponse;

@Service
public class CustomerActivityService {

	@Autowired
	CustomerActivityRepository activityRepository;

	public APIResponse customerActivity(List<CustomerActivity> models) {

		ArrayList<CustomerActivity> collectCustomerActivities = models.stream().map(activityRepository::save)
				.collect(Collectors.toCollection(ArrayList::new));
		return new APIResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED.name(), collectCustomerActivities);
	}

	public APIResponse getCustomerActivity(Long id) {
		Optional<CustomerActivity> activityById = activityRepository.findById(id);
		if (activityById.isPresent())
			return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(),
					Collections.singletonList(activityById));
		else
			return new APIResponse(HttpStatus.NO_CONTENT.value(), HttpStatus.NO_CONTENT.name(), new ArrayList<>());
	}

	public APIResponse getCustomerActivityList() {
		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), activityRepository.getAllActivities());
	}

	public APIResponse modifyCustomerActivity(List<CustomerActivity> model) {
//		Optional<CustomerActivity> activityById = activityRepository.getActivityById(model.getActivityId());
//		if (activityById.isEmpty()) {
		try {
			Iterable<CustomerActivity> savedModel = activityRepository.saveAll(model);

			return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), Collections.singletonList(savedModel));
		} catch (Exception ex) {
			ex.printStackTrace();
			return new APIResponse(HttpStatus.NO_CONTENT.value(), HttpStatus.NO_CONTENT.name(), new ArrayList<>());
		}
	}

	public APIResponse getCustomerActivityByCustomerId(Integer customerId, Long organizationId) {
		List<Map<String, String>> activitiesByCustomerId = activityRepository.findByCustomerIdList(customerId,
				organizationId);
//		if (!activitiesByCustomerId.isEmpty())
		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), activitiesByCustomerId);
//		else
//			return new APIResponse(HttpStatus.NO_CONTENT.value(), HttpStatus.NO_CONTENT.name(), new ArrayList<>());
	}

	/*
	 * note : for every join placing one conditional statement is bad practice. It
	 * has to be done in trasaction apis not in reports.. just do alteration for
	 * resolving bug w.r.t sub-activities
	 */
	public APIResponsePaging getPaginationByOrganization(Long organizationId, Long customerId, int pageNo, int pageSize,
			String sortBy, SortType sortType) {
		Pageable pageable = PageRequest.of(pageNo, pageSize,
				sortType == SortType.asc ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
//		if (organizationId != null) {
//			Page<CustomerActivity> findByOrganizationId = activityRepository.findByOrganizationId(organizationId,
//					pageable);
//			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), findByOrganizationId.getContent(),
//					new ArrayList<>(), findByOrganizationId.getNumber(), findByOrganizationId.getTotalElements(),
//					findByOrganizationId.getTotalPages());
//		} else {
//			Page<CustomerActivity> findAll = activityRepository.findAll(pageable);
//			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), findAll.getContent(),
//					new ArrayList<>(), findAll.getNumber(), findAll.getTotalElements(), findAll.getTotalPages());
//		}
		if (organizationId != null) {
			Page<CustomerActivity> findByOrganizationId = activityRepository
					.findByOrganizationIdAndCustomerId(organizationId, customerId, pageable);
			Page<CustomerActivityResponse> item = findByOrganizationId.map(model -> {
				CustomerActivityResponse response = CustomerRelationsConverter
						.convertCustomerActivitiyModelToRequest(model);
//				Optional<String> findById = activityRepository.getSubActivityName(response.getSubActivityId());
				Optional<String> customerName = activityRepository.getCustomerName(response.getCustomerId());
				Optional<String> segmentName = activityRepository.getSegmentName(response.getSegementId());
				Optional<String> activityName = activityRepository.getActivityName(response.getActivityId());
//				if (findById.isPresent())
//					response.setSubActivityIdName(findById.get());
				List<String> subActivityNameInfo = activityRepository.getSubActivityNameInfo(model.getSubActivityId());

				response.setSubActivityIdName(subActivityNameInfo.stream().collect(Collectors.joining(",")));
				if (customerName.isPresent())
					response.setCustomerIdName(customerName.get());
				if (segmentName.isPresent())
					response.setSegementIdName(segmentName.get());
				if (activityName.isPresent())
					response.setActivityName(activityName.get());
				return response;
			});
			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), item.getContent(),
					new ArrayList<>(), item.getNumber(), item.getTotalElements(), item.getTotalPages());
		} else {
			Page<CustomerActivity> findByOrganizationId = activityRepository.findAll(pageable);
			Page<CustomerActivityResponse> item = findByOrganizationId.map(model -> {
				CustomerActivityResponse response = CustomerRelationsConverter
						.convertCustomerActivitiyModelToRequest(model);
//				Optional<String> findById = activityRepository.getSubActivityName(response.getSubActivityId());
				Optional<String> customerName = activityRepository.getCustomerName(response.getCustomerId());
				Optional<String> segmentName = activityRepository.getSegmentName(response.getSegementId());
				Optional<String> activityName = activityRepository.getActivityName(response.getActivityId());

				List<String> subActivityNameInfo = activityRepository.getSubActivityNameInfo(model.getSubActivityId());

				response.setSubActivityIdName(subActivityNameInfo.stream().collect(Collectors.joining(",")));
//				if (findById.isPresent())
//					response.setSubActivityIdName(findById.get());
				if (customerName.isPresent())
					response.setCustomerIdName(customerName.get());
				if (segmentName.isPresent())
					response.setSegementIdName(segmentName.get());
				if (activityName.isPresent())
					response.setActivityName(activityName.get());
				return response;
			});
			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), item.getContent(),
					new ArrayList<>(), item.getNumber(), item.getTotalElements(), item.getTotalPages());
		}
	}
}
