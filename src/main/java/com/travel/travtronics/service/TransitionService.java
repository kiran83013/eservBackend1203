package com.travel.travtronics.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.travel.travtronics.converter.StatusOwnerMappingConverter;
import com.travel.travtronics.enums.SortType;
import com.travel.travtronics.eserv.model.Transition;
import com.travel.travtronics.eserv.repository.TransitionRepository;
import com.travel.travtronics.request.TransitionRequest;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.APIResponsePaging;
import com.travel.travtronics.response.MessageStatusResponse;
import com.travel.travtronics.response.TransitionDto;
import com.travel.travtronics.response.TrasitionResponse;
import com.travel.travtronics.response.TrasitionResponseModel;

@Service
public class TransitionService {

	@Autowired
	TransitionRepository tRepository;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
//	public APIResponse createTransition(Transition transition) {
//		List<Transition> list = new ArrayList<>();
//		try {
//			Transition save = tRepository.save(transition);
//			list.add(save);
//			return new APIResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED.name(), list);
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name(),
//					new ArrayList<>());
//		}
//	}

	public APIResponse getId(Long transitionId) {
		List<Transition> list = new ArrayList<>();
		try {
			Optional<Transition> opt = tRepository.findBytransitionId(transitionId);
			if (opt.isPresent()) {
				list.add(opt.get());
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
	
	
	public MessageStatusResponse edit(TransitionRequest request) {
		Optional<Transition> validateShiftSlotModel = tRepository
				.findById(request.getTransitionId());
		if (!validateShiftSlotModel.isPresent())
			return new MessageStatusResponse(HttpStatus.NOT_FOUND.value(),
					String.format("data with this id: %d is not found in our system", request.getTransitionId()));
		else {
			Boolean validation = tRepository.existsByOrganizationAndModuleNameAndFromStatusAndToStatusAndStatus(
					request.getOrganization(), request.getModuleName(), request.getFromStatus(),
					request.getToStatus(), request.getStatus().Active);
			if (validation) {
				return new MessageStatusResponse(HttpStatus.CONFLICT.value(),
						"invalid request received..change details and try again..!!!");
			} else {

				tRepository.save(StatusOwnerMappingConverter.convertDtoToModel(request));
				return new MessageStatusResponse(HttpStatus.OK.value(), "successfully data saved in the system");
			}
		}
	}

//	public APIResponse edit(TransitionRequest transition) {
//		List<Transition> list = new ArrayList<>();
//		
//			Optional<Transition> opt = tRepository.findBytransitionId(transition.getTransitionId());
//			if (opt.isPresent()) {
//				Optional<Transition> model = tRepository.findByOrganizationAndModuleNameAndFromStatusAndToStatusAndStatus(
//						opt.get().getOrganization(), opt.get().getModuleName(), opt.get().getFromStatus(),
//						opt.get().getToStatus(), opt.get().getStatus().Active);
//				if(!model.isPresent()) {
//				transition.setCreatedBy(opt.get().getCreatedBy());
////				Transition trans = StatusOwnerMappingConverter.convertDtoToModel(transition);
//				Transition saved = tRepository.saveAll(opt);
//				list.add(saved);
//				return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), list);
//			} else {
//				return new APIResponse(HttpStatus.CONFLICT.value(), "data already exist..!!!");
//			}
//			}
//			return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name(),
//					new ArrayList<>());
//			
//		
//	}

	public APIResponse getAll(Long organizationId) {
		try {
			List<Map<String, String>> list = tRepository.findByList(organizationId);
			return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), list);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name(),
					new ArrayList<>());
		}

	}

	public APIResponse bpfTransitions(Long organization) {
		try {
			List<Map<String, String>> list = tRepository.findByBpfTransition(organization);
			return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), list);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name(),
					new ArrayList<>());
		}

	}

	public APIResponse getTransitionTree(Integer fromstatusId) {
		List<TransitionDto> transition = tRepository.getTransitionInfoByFromStatusId(fromstatusId);

		List<TrasitionResponseModel> response = new ArrayList<>();
		if (transition.size() > 0) {
			for (int i = 0; i < transition.size(); i++) {
				TrasitionResponseModel dto = new TrasitionResponseModel();

				Integer innerFromStatusId = transition.get(i).getFromStatusId();
				dto.setTransitionId(transition.get(i).getTransitionId());
				dto.setFromStatusId(innerFromStatusId);
				dto.setFromStatus(transition.get(i).getFromStatus());
				dto.setToStatusId(transition.get(i).getToStatusId());
				dto.setToStatus(transition.get(i).getToStatus());
				List<TrasitionResponseModel> collect = generateTransitionTree(transition.get(i).getToStatusId());
				dto.setTransitionDto(collect);

				response.add(dto);
			}
		}

		List<Integer> duplicateRemovalArray = new ArrayList<>();
		response.stream().forEach(model -> {

			if (!model.getTransitionDto().isEmpty()) {

				model.getTransitionDto().stream().map(subtransition -> {
					TrasitionResponseModel innerrespone = new TrasitionResponseModel();
					innerrespone.setTransitionId(subtransition.getTransitionId());
					innerrespone.setFromStatusId(subtransition.getFromStatusId());
					innerrespone.setFromStatus(subtransition.getFromStatus());
					innerrespone.setToStatusId(subtransition.getToStatusId());
					innerrespone.setToStatus(subtransition.getToStatus());
					duplicateRemovalArray.addAll(Arrays.asList(subtransition.getToStatusId()));

					if (subtransition.getToStatus().equalsIgnoreCase("Cancelled by Biz")
							|| subtransition.getToStatus().equalsIgnoreCase("Cancelled by User")
							|| subtransition.getToStatus().equalsIgnoreCase("Closed")
							|| subtransition.getToStatus().equalsIgnoreCase("Cleared")) {
						subtransition.setTransitionDto(Collections.emptyList());
					}

					else if (subtransition.getToStatusId() != subtransition.getFromStatusId()
							&& !duplicateRemovalArray.containsAll(
									Arrays.asList(subtransition.getToStatusId(), subtransition.getFromStatusId()))
							&& tRepository.getTransitionInfoByFromStatusId(subtransition.getToStatusId()).size() > 0) {
						subtransition.setTransitionDto(generateTransitionTree(subtransition.getToStatusId()));

						if (!subtransition.getTransitionDto().isEmpty()) {

							subtransition.getTransitionDto().stream().map(grandModel -> {

								List<TrasitionResponseModel> grandChildernTransition = new ArrayList<>();

								if (!duplicateRemovalArray.contains(grandModel.getToStatusId()))
									grandChildernTransition = generateTransitionTree(grandModel.getToStatusId());

								grandModel.setTransitionDto(grandChildernTransition);

								if (!grandModel.getTransitionDto().isEmpty()) {

									grandModel.getTransitionDto().stream().map(grandChildModel -> {

										List<TrasitionResponseModel> innerGrandChildren = new ArrayList<>();

										if (!duplicateRemovalArray.contains(grandChildModel.getToStatusId()))
											innerGrandChildren = generateTransitionTree(
													grandChildModel.getToStatusId());
										grandChildModel.setTransitionDto(innerGrandChildren);

										List<TrasitionResponseModel> innerGrandGrandChildren = new ArrayList<>();
										if (!grandChildModel.getTransitionDto().isEmpty())
											innerGrandGrandChildren = generateTransitionTree(
													grandChildModel.getToStatusId());
										grandChildModel.setTransitionDto(innerGrandGrandChildren);
										return grandChildModel;

									}).collect(Collectors.toList());
								}

								return grandModel;
							}).collect(Collectors.toList());

						}

					} else {
						subtransition.setTransitionDto(Collections.emptyList());
					}

					return innerrespone;
				}).collect(Collectors.toList());

			}
		});
		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), response);
	}

	public List<TrasitionResponseModel> generateTransitionTree(int fromstatusId) {
		List<TransitionDto> transition = tRepository.getTransitionInfoByFromStatusId(fromstatusId);

		List<TrasitionResponseModel> response = new LinkedList<>();
		if (transition.size() > 0) {

			List<Integer> duplicateRemovalArray = new ArrayList<>();

			for (int i = 0; i < transition.size(); i++) {
				TrasitionResponseModel dto = new TrasitionResponseModel();
				dto.setTransitionId(transition.get(i).getTransitionId());
				dto.setFromStatusId(transition.get(i).getFromStatusId());
				dto.setFromStatus(transition.get(i).getFromStatus());
				dto.setToStatusId(transition.get(i).getToStatusId());
				dto.setToStatus(transition.get(i).getToStatus());
				duplicateRemovalArray
						.addAll(Arrays.asList(transition.get(i).getFromStatusId(), transition.get(i).getToStatusId()));

				if (transition.get(i).getToStatus().equalsIgnoreCase("Cancelled by Biz")
						|| transition.get(i).getToStatus().equalsIgnoreCase("Cancelled by User")
						|| transition.get(i).getToStatus().equalsIgnoreCase("Closed")
						|| transition.get(i).getToStatus().equalsIgnoreCase("Cleared")) {
					break;
				}

				else if (!duplicateRemovalArray.containsAll(
						Arrays.asList(transition.get(i).getToStatusId(), transition.get(i).getFromStatusId()))
						&& tRepository.getTransitionInfoByFromStatusId(transition.get(i).getToStatusId()).size() > 0) {
					dto.setTransitionDto(generateTransitionTree(transition.get(i).getToStatusId()));
				} else {
					dto.setTransitionDto(Collections.emptyList());
				}

				response.add(dto);

			}

		}
		return response;
	}

	@Deprecated
	public List<TrasitionResponseModel> generateInnerTransition(int fromstatusId) {
		List<TransitionDto> transitions = tRepository.getTransitionInfoByFromStatusId(fromstatusId);
		List<Integer> duplicateRemovalArray = new ArrayList<>();
		List<TrasitionResponseModel> response = new LinkedList<>();

		if (!transitions.isEmpty()) {

			for (TransitionDto transition : transitions) {

				if (!duplicateRemovalArray.containsAll(Arrays.asList(transition.getTransitionId().intValue(),
						transition.getToStatusId(), transition.getFromStatusId()))) {
					TrasitionResponseModel dto = new TrasitionResponseModel();
					dto.setTransitionId(transition.getTransitionId());
					dto.setFromStatusId(transition.getFromStatusId());
					dto.setFromStatus(transition.getFromStatus());
					dto.setToStatusId(transition.getToStatusId());
					dto.setToStatus(transition.getToStatus());
					duplicateRemovalArray.addAll(Arrays.asList(transition.getTransitionId().intValue(),
							transition.getToStatusId(), transition.getFromStatusId()));

					if (transition.getToStatus().equalsIgnoreCase("Cancelled by Biz")
							|| transition.getToStatus().equalsIgnoreCase("Cancelled by User")
							|| transition.getToStatus().equalsIgnoreCase("Closed")
							|| transition.getToStatus().equalsIgnoreCase("Cleared")) {
						break;
					} else {
						List<TransitionDto> innerTransition = tRepository
								.getTransitionInfoByFromStatusId(transition.getToStatusId());
						dto.setTransitionDto(
								innerTransition.size() > 0 ? generateInnerTransition(transition.getToStatusId())
										: Collections.emptyList());
					}

//
//					/*
//					 * check inner transitions
//					 */
//					List<Integer> innertransitionDuplicateArray = new ArrayList<>();
//
//					for (TrasitionResponseModel innertransition : dto.getTransitionDto()) {
//						if (!dto.getTransitionDto().isEmpty() && !innertransitionDuplicateArray
//								.containsAll(Arrays.asList(dto.getToStatusId(), dto.getFromStatusId()))) {
//							TrasitionResponseModel innerdto = new TrasitionResponseModel();
//							innerdto.setTransitionId(transition.getTransitionId());
//							innerdto.setFromStatusId(transition.getFromStatusId());
//							innerdto.setFromStatus(transition.getFromStatus());
//							innerdto.setToStatusId(transition.getToStatusId());
//							innerdto.setToStatus(transition.getToStatus());
//							if (innerdto.getToStatus().equalsIgnoreCase("Cancelled by Biz")
//									&& innerdto.getToStatus().equalsIgnoreCase("Cancelled by User")
//									&& innerdto.getToStatus().equalsIgnoreCase("Closed")
//									&& innerdto.getToStatus().equalsIgnoreCase("Cleared")) {
//                             break;
//							}else {
//								 List<TransitionDto> subtransition = tRepository
//									.getTransitionInfoByFromStatusId(innertransition.getToStatusId());
//							dto.setTransitionDto(
//									subtransition.size() > 0 ? generateTransitionTree1(innertransition.getToStatusId())
//											: Collections.emptyList());
//							}
//						}
//					}

					response.add(dto);
				}

			}
			return response;
		} else {
			return Collections.emptyList();

		}

	}

	public List<TrasitionResponse> getTransitionTreeSecondOne(Integer fromstatusId, Integer tostatusId) {
		List<TransitionDto> parentTransition = tRepository.getTransitionInfoByFromStatusId(tostatusId);
		Set<String> transitionDuplicateRemovalArray = new HashSet<String>();
		transitionDuplicateRemovalArray.add(fromstatusId + "-" + tostatusId);
		List<TrasitionResponse> responseList = new ArrayList<>();
		for (TransitionDto eachtransition : parentTransition) {
			// Set<String> transitionDuplicateRemovalArray = new HashSet<String>();
			/*
			 * initial-level
			 */
			TrasitionResponse reponse = new TrasitionResponse();
			reponse.setFromStatus(eachtransition.getFromStatus());
			reponse.setToStatus(eachtransition.getToStatus());

			transitionDuplicateRemovalArray
					.add(eachtransition.getFromStatusId() + "-" + eachtransition.getToStatusId());
			/*
			 * second level
			 */
			List<TrasitionResponse> firstChild = tRepository
					.getTransitionInfoByFromStatusId(eachtransition.getToStatusId()).stream()
					.filter(secondchild -> !transitionDuplicateRemovalArray
							.contains(secondchild.getFromStatusId() + "-" + secondchild.getToStatusId()))
					.map(st -> {
						TrasitionResponse innerreponse = new TrasitionResponse();
						innerreponse.setFromStatus(st.getFromStatus());
						innerreponse.setToStatus(st.getToStatus());
						transitionDuplicateRemovalArray.add(String.valueOf(st.getFromStatusId()).concat("-")
								.concat(String.valueOf(st.getToStatusId())));
						/*
						 * third level
						 */
						List<TrasitionResponse> secondChild = tRepository
								.getTransitionInfoByFromStatusId(st.getToStatusId()).stream()
								.filter(thirdChild -> !transitionDuplicateRemovalArray
										.contains(thirdChild.getFromStatusId() + "-" + thirdChild.getToStatusId()))
								.map(tt -> {
									TrasitionResponse innersubreponse = new TrasitionResponse();
									innersubreponse.setFromStatus(tt.getFromStatus());
									innersubreponse.setToStatus(tt.getToStatus());

									transitionDuplicateRemovalArray.add(String.valueOf(tt.getFromStatusId()).concat("-")
											.concat(String.valueOf(tt.getToStatusId())));
									/*
									 * fourth level
									 */
									List<TrasitionResponse> thirdChild = tRepository
											.getTransitionInfoByFromStatusId(tt.getToStatusId()).stream()
											.filter(forthChild -> !transitionDuplicateRemovalArray.contains(
													forthChild.getFromStatusId() + "-" + forthChild.getToStatusId()))
											.map(ft -> {

												TrasitionResponse innercosubreponse = new TrasitionResponse();
												innercosubreponse.setFromStatus(ft.getFromStatus());
												innercosubreponse.setToStatus(ft.getToStatus());
												transitionDuplicateRemovalArray.add(String.valueOf(ft.getFromStatusId())
														.concat("-").concat(String.valueOf(ft.getToStatusId())));

												/*
												 * fifth level
												 */

												List<TrasitionResponse> fourthChild = tRepository
														.getTransitionInfoByFromStatusId(ft.getToStatusId()).stream()
														.filter(fifthChild -> !transitionDuplicateRemovalArray
																.contains(fifthChild.getFromStatusId() + "-"
																		+ fifthChild.getToStatusId()))
														.map(sit -> {
															TrasitionResponse innercolevelsubreponse = new TrasitionResponse();
															innercolevelsubreponse.setFromStatus(sit.getFromStatus());
															innercolevelsubreponse.setToStatus(sit.getToStatus());
															transitionDuplicateRemovalArray.add(String
																	.valueOf(sit.getFromStatusId()).concat("-")
																	.concat(String.valueOf(sit.getToStatusId())));

															/*
															 * sixth level
															 */

															List<TrasitionResponse> fifthChild = tRepository
																	.getTransitionInfoByFromStatusId(
																			sit.getToStatusId())
																	.stream()
																	.filter(sixthChild -> !transitionDuplicateRemovalArray
																			.contains(sixthChild.getFromStatusId() + "-"
																					+ sixthChild.getToStatusId()))
																	.map(set -> {
																		TrasitionResponse innerseventhresponse = new TrasitionResponse();
																		innerseventhresponse
																				.setFromStatus(set.getFromStatus());
																		innerseventhresponse
																				.setToStatus(set.getToStatus());
																		transitionDuplicateRemovalArray.add(String
																				.valueOf(sit.getFromStatusId())
																				.concat("-").concat(String
																						.valueOf(sit.getToStatusId())));
																		/*
																		 * seventh level
																		 */

																		/*
																		 * List<TrasitionResponse> sixthchild =
																		 * tRepository .getTransitionInfoByFromStatusId(
																		 * set.getToStatusId()) .stream()
																		 * .filter(sevengthtChild ->
																		 * !transitionDuplicateRemovalArray
																		 * .contains(sevengthtChild .getFromStatusId() +
																		 * "-" + sevengthtChild .getToStatusId()))
																		 * .map(eig -> { TrasitionResponse
																		 * innereightresponse = new TrasitionResponse();
																		 * innereightresponse.setFromStatus(
																		 * eig.getFromStatus());
																		 * innereightresponse.setToStatus(
																		 * eig.getToStatus());
																		 * transitionDuplicateRemovalArray
																		 * .add(String.valueOf(eig .getFromStatusId())
																		 * .concat("-") .concat(String .valueOf(eig
																		 * .getToStatusId()))); return
																		 * innereightresponse;
																		 * }).collect(Collectors.toList());
																		 * 
																		 * innerseventhresponse
																		 * .setTransitionDto(sixthchild);
																		 */
																		return innerseventhresponse;
																	}).collect(Collectors.toList());

															innercolevelsubreponse.setTransitionDto(fifthChild);
															return innercolevelsubreponse;
														}).collect(Collectors.toList());
												innercosubreponse.setTransitionDto(fourthChild);
												return innercosubreponse;
											}).collect(Collectors.toList());

									innersubreponse.setTransitionDto(thirdChild);
									return innersubreponse;
								}).collect(Collectors.toList());
						innerreponse.setTransitionDto(secondChild);
						return innerreponse;
					}).collect(Collectors.toList());

			reponse.setTransitionDto(firstChild);
			responseList.add(reponse);
		}

		return responseList;
	}

	public APIResponse getTransitionTreeInfo(Integer fromstatusId) {
		List<TransitionDto> transitionList = tRepository.getTransitionInfoByFromStatusId(fromstatusId);
		List<TrasitionResponse> transitionCollection = transitionList.stream().map(transition -> {
			TrasitionResponse reponse = new TrasitionResponse();
			reponse.setFromStatus(transition.getFromStatus());
			reponse.setToStatus(transition.getToStatus());
			reponse.setTransitionDto(
					getTransitionTreeSecondOne(transition.getFromStatusId(), transition.getToStatusId()));
			return reponse;
		}).collect(Collectors.toList());
		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), transitionCollection);
	}

	public APIResponse getStatusInfo(Integer fromstatusId) {
		List<TransitionDto> transitionInfoByFromStatusId = tRepository.getTransitionInfoByFromStatusId(fromstatusId);
		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), transitionInfoByFromStatusId);
	}

	public APIResponsePaging getTransitionPagenationByOrganization(Long organization, int pageNo, int pageSize,
			String sortBy, SortType sortType) {
		Pageable pageable = PageRequest.of(pageNo, pageSize,
				sortType == SortType.asc ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
		if (organization != null) {
			Page<Transition> findByOrganizationId = tRepository.findByOrganization(organization, pageable);
			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), findByOrganizationId.getContent(),
					new ArrayList<>(), findByOrganizationId.getNumber(), findByOrganizationId.getTotalElements(),
					findByOrganizationId.getTotalPages());
		} else {
			Page<Transition> findAll = tRepository.findAll(pageable);
			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), findAll.getContent(),
					new ArrayList<>(), findAll.getNumber(), findAll.getTotalElements(), findAll.getTotalPages());
		}
	}

//	public APIResponse getTransitionTreeInfo(Integer fromstatusId) {
//
//		Set<String> duplicateRemovalArray = new HashSet<>();
//		List<TransitionDto> transitionList = tRepository.getTransitionInfoByFromStatusId(fromstatusId);
//		List<TrasitionResponse> transitionCollection = transitionList.stream().map(transition -> {
//			TrasitionResponse initreponse = new TrasitionResponse();
//			initreponse.setFromStatus(transition.getFromStatus());
//			initreponse.setToStatus(transition.getToStatus());
//			initreponse.setFromStatusId(transition.getFromStatusId());
//			initreponse.setToStatusId(transition.getToStatusId());
//			duplicateRemovalArray.add(transition.getFromStatusId() + "-" + transition.getToStatusId());
//			// reponse.setTransitionDto(getTransitionTreeSecondOne(transition.getToStatusId()));
//			return initreponse;
//		}).collect(Collectors.toList());
//
//		/*
//		 * intial transition
//		 */
//
//		List<TrasitionResponse> intialTransitionList = transitionCollection.stream().map(intialtransition -> {
//
//			List<TrasitionResponse> firstTransitionChildDto = tRepository
//					.getTransitionInfoByFromStatusId(intialtransition.getToStatusId()).stream().map(firstChild -> {
//						TrasitionResponse firstChildreponse = new TrasitionResponse();
//						if (duplicateRemovalArray
//								.contains(firstChild.getFromStatusId() + "-" + firstChild.getToStatusId())) {
//
//							firstChildreponse.setFromStatus(firstChild.getFromStatus());
//							firstChildreponse.setToStatus(firstChild.getToStatus());
//							firstChildreponse.setFromStatusId(firstChild.getFromStatusId());
//							firstChildreponse.setToStatusId(firstChild.getToStatusId());
//							firstChildreponse.setRepeatedFlag(true);
//							return firstChildreponse;
//						} else {
//							firstChildreponse.setFromStatus(firstChild.getFromStatus());
//							firstChildreponse.setToStatus(firstChild.getToStatus());
//							firstChildreponse.setFromStatusId(firstChild.getFromStatusId());
//							firstChildreponse.setToStatusId(firstChild.getToStatusId());
//							firstChildreponse.setRepeatedFlag(false);
//							duplicateRemovalArray.add(firstChild.getFromStatusId() + "-" + firstChild.getToStatusId());
//							return firstChildreponse;
//						}
//
//					}).collect(Collectors.toList());
//
//			intialtransition.setTransitionDto(firstTransitionChildDto);
//			return intialtransition;
//		}).collect(Collectors.toList());
//
//		/*
//		 * second transition
//		 */
//
//		List<TrasitionResponse> secondTransitionList = intialTransitionList.stream().map(firstTransition -> {
//
//			firstTransition.getTransitionDto().stream().map(secondTransition -> {
//
//				List<TrasitionResponse> secondTransitionChildDto = tRepository
//						.getTransitionInfoByFromStatusId(secondTransition.getToStatusId()).stream().map(secondChild -> {
//							TrasitionResponse secondChildreponse = new TrasitionResponse();
//							if (duplicateRemovalArray
//									.contains(secondChild.getFromStatusId() + "-" + secondChild.getToStatusId())) {
//								secondChildreponse.setFromStatus(secondChild.getFromStatus());
//								secondChildreponse.setToStatus(secondChild.getToStatus());
//								secondChildreponse.setFromStatusId(secondChild.getFromStatusId());
//								secondChildreponse.setToStatusId(secondChild.getToStatusId());
//								secondChildreponse.setRepeatedFlag(true);
//								return secondChildreponse;
//							} else {
//								secondChildreponse.setFromStatus(secondChild.getFromStatus());
//								secondChildreponse.setToStatus(secondChild.getToStatus());
//								secondChildreponse.setFromStatusId(secondChild.getFromStatusId());
//								secondChildreponse.setToStatusId(secondChild.getToStatusId());
//								secondChildreponse.setRepeatedFlag(false);
//								duplicateRemovalArray
//										.add(secondChild.getFromStatusId() + "-" + secondChild.getToStatusId());
//								return secondChildreponse;
//							}
//
//						}).collect(Collectors.toList());
//
//				secondTransition.setTransitionDto(secondTransitionChildDto);
//
//				return secondTransition;
//			}).collect(Collectors.toList());
//			return firstTransition;
//		}).collect(Collectors.toList());
//
//		/*
//		 * third transition
//		 */
//
//		List<TrasitionResponse> thirdTransitionList = secondTransitionList.stream().map(firstChild -> {
//
//			firstChild.getTransitionDto().stream().map(secondChild -> {
//
//				secondChild.getTransitionDto().stream().map(thirdTransition -> {
//
//					List<TrasitionResponse> thirdTransitionChildDto = tRepository
//							.getTransitionInfoByFromStatusId(thirdTransition.getToStatusId()).stream()
//							.map(thirdChild -> {
//								TrasitionResponse thirdChildreponse = new TrasitionResponse();
//								if (duplicateRemovalArray
//										.contains(thirdChild.getFromStatusId() + "-" + thirdChild.getToStatusId())) {
//									thirdChildreponse.setFromStatus(thirdChild.getFromStatus());
//									thirdChildreponse.setToStatus(thirdChild.getToStatus());
//									thirdChildreponse.setFromStatusId(thirdChild.getFromStatusId());
//									thirdChildreponse.setToStatusId(thirdChild.getToStatusId());
//									thirdChildreponse.setRepeatedFlag(true);
//
//									return thirdChildreponse;
//								} else {
//									thirdChildreponse.setFromStatus(thirdChild.getFromStatus());
//									thirdChildreponse.setToStatus(thirdChild.getToStatus());
//									thirdChildreponse.setFromStatusId(thirdChild.getFromStatusId());
//									thirdChildreponse.setToStatusId(thirdChild.getToStatusId());
//									thirdChildreponse.setRepeatedFlag(false);
//									duplicateRemovalArray
//											.add(thirdChild.getFromStatusId() + "-" + thirdChild.getToStatusId());
//									return thirdChildreponse;
//								}
//
//							}).collect(Collectors.toList());
//
//					thirdTransition.setTransitionDto(thirdTransitionChildDto);
//
//					return thirdTransition;
//				}).collect(Collectors.toList());
//				return secondChild;
//
//			}).collect(Collectors.toList());
//			return firstChild;
//		}).collect(Collectors.toList());
//
//		/*
//		 * fourth transition
//		 */
//
//		List<TrasitionResponse> fourthChildDto = thirdTransitionList.stream().map(firstChild -> {
//
//			firstChild.getTransitionDto().stream().map(secondChild -> {
//
//				secondChild.getTransitionDto().stream().map(thirdChild -> {
//
//					List<TrasitionResponse> fourthTransitionChildDto = tRepository
//							.getTransitionInfoByFromStatusId(thirdChild.getToStatusId()).stream()
//							.map(forthTransition -> {
//								TrasitionResponse fourthChildreponse = new TrasitionResponse();
//								if (duplicateRemovalArray.contains(
//										forthTransition.getFromStatusId() + "-" + forthTransition.getToStatusId())) {
//									fourthChildreponse.setFromStatus(forthTransition.getFromStatus());
//									fourthChildreponse.setToStatus(forthTransition.getToStatus());
//									fourthChildreponse.setFromStatusId(forthTransition.getFromStatusId());
//									fourthChildreponse.setToStatusId(forthTransition.getToStatusId());
//									fourthChildreponse.setRepeatedFlag(true);
//									return fourthChildreponse;
//								} else {
//									fourthChildreponse.setFromStatus(forthTransition.getFromStatus());
//									fourthChildreponse.setToStatus(forthTransition.getToStatus());
//									fourthChildreponse.setFromStatusId(forthTransition.getFromStatusId());
//									fourthChildreponse.setToStatusId(forthTransition.getToStatusId());
//									duplicateRemovalArray.add(
//											forthTransition.getFromStatusId() + "-" + forthTransition.getToStatusId());
//									return fourthChildreponse;
//								}
//							}).collect(Collectors.toList());
//
//					thirdChild.setTransitionDto(fourthTransitionChildDto);
//					return thirdChild;
//				}).collect(Collectors.toList());
//				return secondChild;
//			}).collect(Collectors.toList());
//			return firstChild;
//		}).collect(Collectors.toList());
//
//		/*
//		 * fifth transition
//		 */
//
//		fourthChildDto.stream().map(firstChild -> {
//
//			return firstChild;
//		}).collect(Collectors.toList());
//
//		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), transitionCollection);
//	}

//	public APIResponse getTransitionTreeInfoList(Integer fromstatusId) {
//		/*
//		 * inital level
//		 */
//		List<TrasitionResponse> responseList = new ArrayList<>();
//		List<TransitionDto> parentTransition = tRepository.getTransitionInfoByFromStatusId(fromstatusId);
//
//		parentTransition.stream().forEach(intialTransition -> {
//			Set<Integer> duplicateRemovalArray = new HashSet<>();
//			TrasitionResponse firstTransitionResponse = new TrasitionResponse();
//			firstTransitionResponse.setFromStatus(intialTransition.getFromStatus());
//			firstTransitionResponse.setToStatus(intialTransition.getToStatus());
//			firstTransitionResponse.setFromStatusId(intialTransition.getFromStatusId());
//			firstTransitionResponse.setToStatusId(intialTransition.getFromStatusId());
//			duplicateRemovalArray.add(intialTransition.getFromStatusId());
//
//			/*
//			 * second level
//			 */
//			List<TrasitionResponse> secondChildDto = tRepository
//					.getTransitionInfoByFromStatusId(intialTransition.getToStatusId()).stream()
//					.filter(second -> !duplicateRemovalArray.contains(second.getFromStatusId())).map(second -> {
//						duplicateRemovalArray.add(intialTransition.getToStatusId());
//						TrasitionResponse secondTransitionResponse = new TrasitionResponse();
//
//						secondTransitionResponse.setFromStatus(second.getFromStatus());
//						secondTransitionResponse.setToStatus(second.getToStatus());
//						secondTransitionResponse.setFromStatusId(second.getFromStatusId());
//						secondTransitionResponse.setToStatusId(second.getFromStatusId());
//						duplicateRemovalArray.add(second.getFromStatusId());
//						return secondTransitionResponse;
//					}).collect(Collectors.toList());
//			firstTransitionResponse.setTransitionDto(secondChildDto);
//			responseList.add(firstTransitionResponse);
//		});
//
//		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), responseList);
//	}

	public APIResponse createTransition(TransitionRequest transition) {
		List<Transition> list = new ArrayList<>();
		
			Optional<Transition> model = tRepository.findByOrganizationAndModuleNameAndFromStatusAndToStatusAndStatus(
					transition.getOrganization(), transition.getModuleName(), transition.getFromStatus(),
					transition.getToStatus(), transition.getStatus().Active);
			if (!model.isPresent()) {
				Transition trans = StatusOwnerMappingConverter.convertDtoToModel(transition);
				Transition saved = tRepository.save(trans);
				list.add(saved);
				return new APIResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED.name(),list);
			}
	
		return new APIResponse(HttpStatus.CONFLICT.value(), "data already exist..!!!");
	}
	
//	public APIResponse edit(TransitionRequest request) {
//		List<TransitionRequest> list = new ArrayList<>();
//		for (TransitionRequest eachAssign : request) {
//			Optional<Transition> opt = tRepository.findBytransitionId(eachAssign.getTransitionId());
//			if (opt.isPresent()) {
//				Boolean existsByOrganizationAndModuleNameAndFromStatusAndToStatusAndStatus = tRepository.existsByOrganizationAndModuleNameAndFromStatusAndToStatusAndStatus(eachAssign.getOrganization(), eachAssign.getModuleName(),eachAssign.getFromStatus(),eachAssign.getToStatus(),eachAssign.getStatus());
//				if (existsByOrganizationAndModuleNameAndFromStatusAndToStatusAndStatus) {
//					return new APIResponse(HttpStatus.CONFLICT.value(),"invalid request received..change details and try again..!!!");
//			}else {
//				list.add(eachAssign);
//			}
//			}else {
//				return new APIResponse(HttpStatus.NOT_FOUND.value(),
//						String.format("data with this id : %d is not found in our system ", eachAssign.getTransitionId()));
//			}
//		}
//		tRepository.save(StatusOwnerMappingConverter.convertDtoToModel(request));
//		return new APIResponse(HttpStatus.OK.value(), "successfully data saved in the system");
//
//	}
}
