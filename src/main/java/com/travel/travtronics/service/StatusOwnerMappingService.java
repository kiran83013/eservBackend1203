package com.travel.travtronics.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
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

import com.travel.travtronics.converter.StatusOwnerMappingConverter;
import com.travel.travtronics.enums.SortType;
import com.travel.travtronics.enums.Status;
import com.travel.travtronics.eserv.model.Modules;
import com.travel.travtronics.eserv.model.StatusOwnerMapping;
import com.travel.travtronics.eserv.model.Team;
import com.travel.travtronics.eserv.repository.ModulesRepository;
import com.travel.travtronics.eserv.repository.StatusOwnerMappingRepository;
import com.travel.travtronics.eserv.repository.StatusRepository;
import com.travel.travtronics.eserv.repository.TeamLeadertoTeamRepository;
import com.travel.travtronics.eserv.repository.TeamRepository;
import com.travel.travtronics.eserv.repository.TransitionRepository;
import com.travel.travtronics.request.StatusOwnerMappingRequest;
import com.travel.travtronics.request.UserTeamDto;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.APIResponsePaging;
import com.travel.travtronics.response.StatusOwnerMappingResponse;
import com.travel.travtronics.response.TransitionDto;
import com.travel.travtronics.response.UserTeamLeaderResponse;

@Service
public class StatusOwnerMappingService {
	@Autowired
	StatusOwnerMappingRepository mappingRepository;
	@Autowired
	StatusRepository statusRepository;

	@Autowired
	TeamRepository teamRepository;

	@Autowired
	TeamLeadertoTeamRepository tltoTeamRepository;

	@Autowired
	TransitionRepository tRepository;

	@Autowired
	ModulesRepository moduleRepository;

	public APIResponse create(StatusOwnerMapping data) {
		try {
			if (data.getStatus() == Status.InActive) {
				StatusOwnerMapping response = mappingRepository.save(data);
				return new APIResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED.name(),
						Collections.singletonList(response));
			} else {
				List<StatusOwnerMapping> findData = mappingRepository
						.findByOrganizationAndDefaultStatusAndTeamAndModuleAndStatus(data.getOrganization(),
								data.getDefaultStatus(), data.getTeam(), data.getModule(), Status.Active);
				if (findData.isEmpty()) {
					StatusOwnerMapping response = mappingRepository.save(data);
					return new APIResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED.name(),
							Collections.singletonList(response));
				} else {
					return new APIResponse(409, "Configuration is already there", new ArrayList<>());
				}

			}

		} catch (Exception ex) {
			// ex.printStackTrace();
			return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name(),
					new ArrayList<>());
		}

	}

	public APIResponse update(StatusOwnerMapping data) {
		try {
			Optional<StatusOwnerMapping> findData = mappingRepository.findById(data.getId());
			if (findData.isPresent()) {
				if (data.getStatus() == Status.InActive) {
					StatusOwnerMapping response = mappingRepository.save(data);
					return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(),
							Collections.singletonList(response));
				} else {
					List<StatusOwnerMapping> find = mappingRepository
							.findByOrganizationAndDefaultStatusAndTeamAndModuleAndStatus(data.getOrganization(),
									data.getDefaultStatus(), data.getTeam(), data.getModule(), Status.Active);
					if (find.isEmpty()) {
						StatusOwnerMapping response = mappingRepository.save(data);
						return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(),
								Collections.singletonList(response));
					} else {
						return new APIResponse(409, "Configuration is already there", new ArrayList<>());
					}
				}

			} else {
				return new APIResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), new ArrayList<>());
			}
		} catch (Exception ex) {
			// ex.printStackTrace();
			return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name(),
					new ArrayList<>());
		}
	}

	public APIResponse findById(Long id) {
		try {
			Optional<StatusOwnerMapping> findData = mappingRepository.findById(id);
			if (findData.isPresent()) {

				return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(),
						Collections.singletonList(findData));
			} else {
				return new APIResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), new ArrayList<>());
			}
		} catch (Exception ex) {
			// ex.printStackTrace();
			return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name(),
					new ArrayList<>());
		}
	}

	public APIResponsePaging getAllByPaging(Long organization, int pageNo, int pageSize, String sortBy,
			SortType sortType) {
		Pageable pageable = PageRequest.of(pageNo, pageSize,
				sortType == SortType.asc ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
		if (organization != null) {
			Page<StatusOwnerMapping> findByOrganizationId = mappingRepository.findByOrganization(organization,
					pageable);
			Page<StatusOwnerMappingRequest> item = findByOrganizationId.map(model -> {
				StatusOwnerMappingRequest response = StatusOwnerMappingConverter.convertConfigModelToResponse(model);

				Optional<Modules> findById = moduleRepository.findById(response.getModule());
				response.setModuleName(findById.isPresent() ? findById.get().getModuleName() : null);

				Optional<com.travel.travtronics.eserv.model.Status> status = statusRepository
						.findById(response.getDefaultStatus());
				response.setDefaultStatusName(status.isPresent() ? status.get().getName() : null);

				Optional<Team> team = teamRepository.findByTeamId(response.getTeam());
				response.setTeamName(team.isPresent() ? team.get().getTeamName() : null);
				return response;
			});
			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), item.getContent(),
					new ArrayList<>(), item.getNumber(), item.getTotalElements(), item.getTotalPages());
		} else {
			Page<StatusOwnerMapping> findByOrganizationId = mappingRepository.findAll(pageable);
			Page<StatusOwnerMappingRequest> item = findByOrganizationId.map(model -> {
				StatusOwnerMappingRequest response = StatusOwnerMappingConverter.convertConfigModelToResponse(model);

				Optional<Modules> findById = moduleRepository.findById(response.getModule());
				response.setModuleName(findById.isPresent() ? findById.get().getModuleName() : null);

				Optional<com.travel.travtronics.eserv.model.Status> status = statusRepository
						.findById(response.getDefaultStatus());
				response.setDefaultStatusName(status.isPresent() ? status.get().getName() : null);

				Optional<Team> team = teamRepository.findById(response.getTeam());
				response.setTeamName(team.isPresent() ? team.get().getTeamName() : null);
				return response;
			});
			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), item.getContent(),
					new ArrayList<>(), item.getNumber(), item.getTotalElements(), item.getTotalPages());
		}

	}

	public APIResponse getAll(Long organization) {
		try {
			List<Map<String, String>> data = mappingRepository.findByOrganization(organization, Status.Active);
			return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), data);
		} catch (Exception ex) {
			// ex.printStackTrace();
			return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name(),
					new ArrayList<>());
		}
	}

	public APIResponse getAllStatusByOrganization(Long organization) {
		try {
			List<?> data = mappingRepository.getAllStatusByOrganization(organization);
			return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), data);
		} catch (Exception ex) {
			// ex.printStackTrace();
			return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name(),
					new ArrayList<>());
		}
	}

	public APIResponse getTeamInfo(Long deafultStatus, Long organization, Long module) {

		List<UserTeamDto> statusOwnerInfo = mappingRepository.getStatusOwnerInfo(deafultStatus, organization, module);

		Optional<com.travel.travtronics.eserv.model.Status> statusData = statusRepository.findBystatusId(deafultStatus);

		List<Map<String, String>> teamInfo = mappingRepository.getTeamInfo(deafultStatus, organization, module);

		List<UserTeamLeaderResponse> collect = statusOwnerInfo.stream().map(model -> {
			UserTeamLeaderResponse response = new UserTeamLeaderResponse();
			Optional<String> teamLeaderId = tltoTeamRepository.getTeamLeaderId(String.valueOf(model.getTeam()),
					String.valueOf(organization), String.valueOf(module));
			System.out.println(Integer.valueOf(teamLeaderId.get()) + "fdg" + model.getUserId());
			if (Integer.valueOf(teamLeaderId.get()) == model.getUserId()) {
				response.setId(model.getId());
				response.setIsTeamLeader(true);
				response.setTeam(model.getTeam());
				response.setTeamName(model.getTeamName());
				response.setUserId(model.getUserId());
				response.setUserName(model.getUserName());
			} else {
				response.setId(model.getId());
				response.setIsTeamLeader(false);
				response.setTeam(model.getTeam());
				response.setTeamName(model.getTeamName());
				response.setUserId(model.getUserId());
				response.setUserName(model.getUserName());
			}
			return response;
		}).collect(Collectors.toCollection(ArrayList::new));
		// }).filter(model -> model.getIsTeamLeader() ==
		// true).collect(Collectors.toCollection(ArrayList::new));

		Map<String, Object> map = new HashMap<>();
		map.put("users", collect);
		map.put("team", teamInfo);

		/*
		 * map.put("status",statusData);
		 */

		/*
		 * When to Not Return Optional as return Type---> Because Optional is a wrapper
		 * and value-based class, there are some operations that can't be done against
		 * Optional object. Many times, it's just simply better to return the actual
		 * type rather than an Optional type.
		 * 
		 * Generally speaking, for getters in POJOs, it's more suitable to return the
		 * actual type, not an Optional type. Particularly, it's important for Entity
		 * Beans, Data Models, and DTOs to have traditional getters. this cause impact
		 * on serialization and deserialization
		 */
		map.put("status", statusData.isPresent() ? statusData.get() : new Object());
		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), Arrays.asList(map));
	}

	/**
	 * Get Team and status info and Status transitions info also by passing
	 * StatusId, OrganizationId & ModuleId
	 * 
	 * @param statusId
	 * @param organizationId
	 * @param moduleId
	 * @return
	 */
	public APIResponse getTeamAndStatusInfoDetails(Long statusId, Long organizationId, Long moduleId) {

		if (statusId == null || statusId <= 0) {
			return new APIResponse(HttpStatus.BAD_REQUEST.value(), "Invalid statusId", Collections.EMPTY_LIST);
		}
		if (organizationId == null || organizationId <= 0) {
			return new APIResponse(HttpStatus.BAD_REQUEST.value(), "Invalid organizationId", Collections.EMPTY_LIST);
		}
		if (moduleId == null || moduleId <= 0) {
			return new APIResponse(HttpStatus.BAD_REQUEST.value(), "Invalid moduleId", Collections.EMPTY_LIST);
		}

		List<UserTeamDto> statusOwnerInfo = mappingRepository.getStatusOwnerInfo(statusId, organizationId, moduleId);

		Optional<com.travel.travtronics.eserv.model.Status> statusData = statusRepository
				.getStatusInfoByStatusIdAndModuleId(statusId, moduleId);

		List<Map<String, String>> teamInfo = mappingRepository.getTeamInfo(statusId, organizationId, moduleId);

		List<UserTeamLeaderResponse> usersList = new ArrayList<UserTeamLeaderResponse>();
		if (statusOwnerInfo.size() > 0) {

			usersList = statusOwnerInfo.stream().map(model -> {

				UserTeamLeaderResponse response = new UserTeamLeaderResponse();

				Optional<String> teamLeaderId = tltoTeamRepository.getTeamLeaderId(String.valueOf(model.getTeam()),
						String.valueOf(organizationId), String.valueOf(moduleId));

				System.out.println(Integer.valueOf(teamLeaderId.get()) + "fdg" + model.getUserId());

				if (Integer.valueOf(teamLeaderId.get()) == model.getUserId()) {
					response.setId(model.getId());
					response.setIsTeamLeader(true);
					response.setTeam(model.getTeam());
					response.setTeamName(model.getTeamName());
					response.setUserId(model.getUserId());
					response.setUserName(model.getUserName());
				} else {
					response.setId(model.getId());
					response.setIsTeamLeader(false);
					response.setTeam(model.getTeam());
					response.setTeamName(model.getTeamName());
					response.setUserId(model.getUserId());
					response.setUserName(model.getUserName());
				}
				return response;
			}).collect(Collectors.toCollection(ArrayList::new));
		}

		List<TransitionDto> transitionInfoByFromStatusId = tRepository
				.getTransitionInfoByFromStatusIdAndModuleId(statusId, moduleId);

		Map<String, Object> response = new HashMap<>();
		if (usersList.size() > 0) {
			response.put("users", usersList);
		}

		if (teamInfo.size() > 0) {
			response.put("team", teamInfo);
		}

		if (statusData.isPresent()) {
			response.put("statusInfo", statusData);
		}

		if (transitionInfoByFromStatusId.size() > 0) {
			response.put("transitions", transitionInfoByFromStatusId);
		}
		if (response.size() > 0) {
			return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), Arrays.asList(response));
		} else {
			return new APIResponse(HttpStatus.NOT_FOUND.value(), "Not found any data in the system.",
					new ArrayList<>());
		}
	}

	/** already written by some one **/
	public APIResponsePaging getAllByPaging(int pageNo, int pageSize, String sortBy, SortType sortType) {
		Pageable pageable = PageRequest.of(pageNo, pageSize,
				sortType == SortType.asc ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
		Page<StatusOwnerMapping> pageData = mappingRepository.findAll(pageable);
		List<StatusOwnerMappingResponse> data = changePagingResponse(pageData.getContent());
		return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), data, new ArrayList<>(),
				pageData.getNumber(), pageData.getTotalElements(), pageData.getTotalPages());

	}

	private List<StatusOwnerMappingResponse> changePagingResponse(List<StatusOwnerMapping> input) {

		List<StatusOwnerMappingResponse> data = input.stream().map(v -> {

			StatusOwnerMappingResponse swp = new StatusOwnerMappingResponse();
			Optional<Team> team = teamRepository.findByTeamId(v.getTeam());
			Optional<com.travel.travtronics.eserv.model.Status> status = statusRepository
					.findBystatusId(v.getDefaultStatus());
			swp.setId(v.getId());
			swp.setOrganization(v.getOrganization());
			swp.setModule(v.getModule());
			/*
			 * swp.setDefaultStatus(v.getDefaultStatus()); swp.setTeam(v.getTeam());
			 */
			swp.setStatus(v.getStatus());
			swp.setCreatedBy(v.getCreatedBy());
			swp.setCreatedDate(v.getCreatedDate());
			swp.setUpdatedBy(v.getUpdatedBy());
			swp.setUpdatedDate(v.getUpdatedDate());
			swp.setDefaultStatus(status.get().getName());
			swp.setTeam(team.get().getTeamName());

			return swp;
		}).collect(Collectors.toList());

		return data;
	}
}
