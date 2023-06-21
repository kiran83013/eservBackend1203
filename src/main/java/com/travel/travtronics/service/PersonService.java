package com.travel.travtronics.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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
import org.springframework.transaction.annotation.Transactional;

import com.travel.travtronics.converter.ContactHelper;
import com.travel.travtronics.converter.PersonConverter;
import com.travel.travtronics.enums.SortType;
import com.travel.travtronics.enums.Status;
import com.travel.travtronics.eserv.model.CustomerContactNew;
import com.travel.travtronics.eserv.model.PersonAddreesModel;
import com.travel.travtronics.eserv.model.PersonLanguageModel;
import com.travel.travtronics.eserv.model.PersonModel;
import com.travel.travtronics.eserv.model.PersonPidModel;
import com.travel.travtronics.eserv.model.PersonQualificationModel;
import com.travel.travtronics.eserv.model.PersonRelationsModel;
import com.travel.travtronics.eserv.model.PersonSocialModel;
import com.travel.travtronics.eserv.model.PersonVerificationModel;
import com.travel.travtronics.eserv.repository.CustomerContactNewRepository;
import com.travel.travtronics.eserv.repository.PersonAddreesRepository;
import com.travel.travtronics.eserv.repository.PersonLanguageRepository;
import com.travel.travtronics.eserv.repository.PersonModelRepository;
import com.travel.travtronics.eserv.repository.PersonPidRepository;
import com.travel.travtronics.eserv.repository.PersonQualificationRepository;
import com.travel.travtronics.eserv.repository.PersonRelationsRepository;
import com.travel.travtronics.eserv.repository.PersonSocialRepository;
import com.travel.travtronics.eserv.repository.PersonVerificationRepository;
import com.travel.travtronics.request.PersonContactRequest;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.APIResponsePaging;
import com.travel.travtronics.response.ContactPersonResponse;
import com.travel.travtronics.response.MessageStatusResponse;
import com.travel.travtronics.response.PersonLanguageResponse;
import com.travel.travtronics.response.PersonPidResponse;
import com.travel.travtronics.response.PersonQualificationResponse;
import com.travel.travtronics.response.PersonRelationsResponse;
import com.travel.travtronics.response.PersonSocialResponse;
import com.travel.travtronics.response.PersonVerificationResponse;
import com.travel.travtronics.util.NotFoundException;
import com.travel.travtronics.util.SearchEntity;

@Service
public class PersonService {

	@Autowired
	PersonModelRepository personRepository;

	@Autowired
	PersonAddreesRepository addressRepository;

	@Autowired
	PersonRelationsRepository relationRepository;
	@Autowired
	PersonSocialRepository personSocialRepository;
	@Autowired
	PersonPidRepository pidRepository;

	@Autowired
	PersonLanguageRepository languageRepository;
	@Autowired
	PersonVerificationRepository verificationRepository;

	@Autowired
	PersonQualificationRepository qualificationRepository;

	@Autowired
	CustomerContactNewRepository contactRepository;

	public APIResponse createPerson(PersonModel request) {

		Optional<PersonModel> personValidation = personRepository
				.findByPrimaryEmailOrPrimaryPhoneNumber(request.getPrimaryEmail(), request.getPrimaryPhoneNumber());
		if (!personValidation.isPresent()) {
			PersonModel savedPerson = personRepository.save(request);
			return new APIResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED.name(),
					Collections.singletonList(savedPerson));
		} else {
			return new APIResponse(HttpStatus.CONFLICT.value(),
					"invalid-request-person-already-exists-with-this-information", new ArrayList<>());
		}

	}

	public APIResponse getByid(Long id) throws NotFoundException {
		PersonModel personModel = personRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Given id %s not found", id)));
		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), Collections.singletonList(personModel));
	}

	public APIResponse getAll(Long orgId) {
		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(),
				personRepository.findAllByOrganizationId(orgId));
	}

	public MessageStatusResponse updatePerson(PersonModel model) {
		Optional<PersonModel> findById = personRepository.findById(model.getId());

		if (findById.isPresent()) {
			PersonModel savedPerson = personRepository.save(model);
			return new MessageStatusResponse(HttpStatus.OK.value(), HttpStatus.OK.name());
		} else
			return new MessageStatusResponse(HttpStatus.NO_CONTENT.value(), HttpStatus.NO_CONTENT.name());
	}

	public APIResponse getSearchByValue(String search) {
		List<PersonModel> findBySearchParameter = personRepository.findBySearchParameter(search);
		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), findBySearchParameter);
	}

	public APIResponse search(String firstName, String lastName, Long phoneNo, String email, Long customerId) {
		if (firstName == null && lastName == null && phoneNo == null && email == null && customerId == null) {
			return new APIResponse(HttpStatus.OK.value(), "unable-to-search-provide-atleast-one-paarmeter",
					new ArrayList<>());
		}
		List<PersonModel> searchedData = personRepository
				.findAll(SearchEntity.findByPersonSpecifications(firstName, lastName, phoneNo, email, customerId));
		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), searchedData);
	}

	public APIResponse createPersonAddress(PersonAddreesModel request) {
		PersonAddreesModel savedAddress = addressRepository.save(request);
		return new APIResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED.name(),
				Collections.singletonList(savedAddress));
	}

	public APIResponse getAddressByid(Long id) throws NotFoundException {
		PersonAddreesModel adreesModel = addressRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Given id %s not found", id)));
		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), Collections.singletonList(adreesModel));

	}

	public APIResponse getAllAddressByPersonInfo(Long refId, Long orgId) {
		List<PersonAddreesModel> adreesList = addressRepository.findAllByRefIdAndOrganizationIdAndStatus(refId, orgId,
				Status.Active);
		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), adreesList);
	}

	public MessageStatusResponse editPersonAddress(PersonAddreesModel model) throws NotFoundException {
		PersonAddreesModel adressModel = addressRepository.findById(model.getAddressId())
				.orElseThrow(() -> new NotFoundException(String.format("Given id %s not found", model.getAddressId())));
		PersonAddreesModel savedModel = addressRepository.save(model);
		return new MessageStatusResponse(HttpStatus.OK.value(), HttpStatus.OK.name());
	}

	public MessageStatusResponse createRealation(PersonRelationsModel request) {
		PersonRelationsModel savedRelations = relationRepository.save(request);
		return new MessageStatusResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED.name());
	}

	public APIResponse getRelationByid(Long id) throws NotFoundException {
		PersonRelationsModel relationInfo = relationRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Given id %s not found", id)));
		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), Collections.singletonList(relationInfo));
	}

	public MessageStatusResponse modifyRelation(PersonRelationsModel request) throws NotFoundException {
		PersonRelationsModel relationInfo = relationRepository.findById(request.getRelationShipId()).orElseThrow(
				() -> new NotFoundException(String.format("Given id %s not found", request.getRelationShipId())));

		relationRepository.save(request);
		return new MessageStatusResponse(HttpStatus.OK.value(), HttpStatus.OK.name());
	}

	public APIResponse getByFirstPartyId(Long firstPartyId, Long orgId) {
		List<Map<String, String>> findByFirstPartyId = relationRepository
				.findByFirstPartyIdAndOrganizationId(firstPartyId, orgId);
		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), findByFirstPartyId);
	}

	public MessageStatusResponse createSocial(List<PersonSocialModel> requests) {
		requests.stream().forEach(personSocialRepository::save);
		return new MessageStatusResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED.name());
	}

	public APIResponse getSocialByid(Long id) throws NotFoundException {
		PersonSocialModel socialInfo = personSocialRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Given id %s not found", id)));
		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), Collections.singletonList(socialInfo));
	}

	public MessageStatusResponse updateSocial(PersonSocialModel model) throws NotFoundException {
		PersonSocialModel socialInfo = personSocialRepository.findById(model.getId())
				.orElseThrow(() -> new NotFoundException(String.format("Given id %s not found", model.getId())));
		personSocialRepository.save(model);
		return new MessageStatusResponse(HttpStatus.OK.value(), HttpStatus.OK.name());
	}

	public APIResponse getSocialByRefId(Long refId, Long orgId) {
		List<Map<String, String>> socialInfo = personSocialRepository.getSocialByRefId(refId, orgId);
		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), socialInfo);
	}

	public MessageStatusResponse createPid(List<PersonPidModel> models) {
		models.stream().forEach(pidRepository::save);
		return new MessageStatusResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED.name());
	}

	public APIResponse getPidByid(Long id) throws NotFoundException {
		PersonPidModel pidInfo = pidRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Given id %s not found", id)));
		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), Collections.singletonList(pidInfo));
	}

	public MessageStatusResponse updatePid(PersonPidModel model) throws NotFoundException {
		PersonPidModel pidInfo = pidRepository.findById(model.getId())
				.orElseThrow(() -> new NotFoundException(String.format("Given id %s not found", model.getId())));
		pidRepository.save(model);
		return new MessageStatusResponse(HttpStatus.OK.value(), HttpStatus.OK.name());
	}

	public APIResponse getPidByRefId(Long refId, Long orgId) {
		List<Map<String, String>> pidInfo = pidRepository.getPidByRefId(refId, orgId);
		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), pidInfo);
	}

	public MessageStatusResponse createLanguage(List<PersonLanguageModel> models) {
		models.stream().forEach(model -> {
			if (model.getRead() == null)
				model.setRead(false);
			if (model.getWrite() == null)
				model.setWrite(false);
			if (model.getSpeak() == null)
				model.setSpeak(false);
			languageRepository.save(model);
		});
		return new MessageStatusResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED.name());
	}

	public APIResponse getLanguageByid(Long id) throws NotFoundException {
		PersonLanguageModel langInfo = languageRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Given id %s not found", id)));
		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), Collections.singletonList(langInfo));
	}

	public MessageStatusResponse modifyLanguage(List<PersonLanguageModel> models) {

		models.forEach(model -> {

			try {
				PersonLanguageModel langInfo = languageRepository.findById(model.getId()).orElseThrow(
						() -> new NotFoundException(String.format("Given id %s not found", model.getId())));

				if (model.getRead() == null)
					model.setRead(false);
				if (model.getWrite() == null)
					model.setWrite(false);
				if (model.getSpeak() == null)
					model.setSpeak(false);
				languageRepository.save(model);
			} catch (NotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});

		return new MessageStatusResponse(HttpStatus.OK.value(), HttpStatus.OK.name());
	}

	public APIResponse getLanguageByRefId(Long refId, Long orgId) {
		List<Map<String, String>> languageByrefId = languageRepository.getLanguageByrefId(refId, orgId);
		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), languageByrefId);
	}

	public APIResponse getVerfificationId(Long id) throws NotFoundException {
		PersonVerificationModel verificationinfo = verificationRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Given id %s not found", id)));
		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(),
				Collections.singletonList(verificationinfo));
	}

	public MessageStatusResponse cretaeVerifications(List<PersonVerificationModel> models) {
		models.forEach(verificationRepository::save);
		return new MessageStatusResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED.name());
	}

	public MessageStatusResponse modifyVerifications(List<PersonVerificationModel> models) {
		models.forEach(model -> {
			try {
				PersonVerificationModel verificationinfo = verificationRepository.findById(model.getId()).orElseThrow(
						() -> new NotFoundException(String.format("Given id %s not found", model.getId())));
				verificationRepository.save(verificationinfo);
			} catch (NotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		return new MessageStatusResponse(HttpStatus.OK.value(), HttpStatus.OK.name());
	}

	public APIResponse getVerificationByRefId(Long refId, Long orgId) {
		List<Map<String, String>> verificationByRefId = verificationRepository.getVerificationByRefId(refId, orgId);
		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), verificationByRefId);
	}

	public MessageStatusResponse cretaeQualification(List<PersonQualificationModel> models) {
		models.forEach(qualificationRepository::save);
		return new MessageStatusResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED.name());
	}

	public APIResponse getQualificationId(Long id) throws NotFoundException {
		PersonQualificationModel qualificationInfo = qualificationRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Given id %s not found", id)));
		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(),
				Collections.singletonList(qualificationInfo));
	}

	public MessageStatusResponse modifyQualification(List<PersonQualificationModel> models) {
		models.forEach(model -> {
			try {
				PersonQualificationModel qualificationInfo = qualificationRepository.findById(model.getId())
						.orElseThrow(
								() -> new NotFoundException(String.format("Given id %s not found", model.getId())));
				qualificationRepository.save(model);
			} catch (NotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		return new MessageStatusResponse(HttpStatus.OK.value(), HttpStatus.OK.name());
	}

	public APIResponse getQualificationByRefId(Long refId, Long orgId) {
		List<Map<String, String>> qualificationByrefId = qualificationRepository.getQualificationByrefId(refId, orgId);
		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), qualificationByrefId);
	}

	@Transactional(rollbackFor = Exception.class)
	public APIResponse personContactWrapper(PersonContactRequest personContactRequestModel) throws Exception {

		Optional<PersonModel> personValidation = personRepository.findById(personContactRequestModel.getPersonId());

		if (personValidation.isPresent()) {

			String contactUniqId = personContactRequestModel.getContactInfo().getFirstName() + "-"
					+ personContactRequestModel.getContactInfo().getLastName() + "-"
					+ personContactRequestModel.getContactInfo().getPrimaryPhoneNumber();
			Optional<CustomerContactNew> contactvalidation = contactRepository.validateContact(contactUniqId);

//			Optional<CustomerContactNew> contactvalidation = contactRepository.findByPrimaryEmailOrPrimaryPhoneNumber(
//					personContactRequestModel.getContactInfo().getPrimaryEmail(),
//					personContactRequestModel.getContactInfo().getPrimaryPhoneNumber());
			// contact not present
			if (!contactvalidation.isPresent()) {
				personContactRequestModel.getContactInfo().setPersonId(personValidation.get().getId());

				Optional<CustomerContactNew> phoneNoContact = contactRepository
						.findByPrimaryPhoneNumber(personContactRequestModel.getContactInfo().getPrimaryPhoneNumber());
				if (!phoneNoContact.isPresent()) {
					CustomerContactNew savedContact = contactRepository
							.save(personContactRequestModel.getContactInfo());
					if (savedContact.getDesignationName() != null && savedContact.getDesignationId() != null
							&& savedContact.getDesignationId() == 0 && !savedContact.getDesignationName().isEmpty()) {

						String name = savedContact.getDesignationName();
						String code = savedContact.getDesignationName();
						String description = savedContact.getDesignationName();
						Integer createdBy = Integer.valueOf(savedContact.getCreatedBy().intValue());
						Date createdDate = savedContact.getCreatedDate();
						personRepository.addDesignationInfo(name, code, description, createdBy, createdDate);

					}

					ContactPersonResponse response = new ContactPersonResponse();
					response.setPersonInfo(personValidation.get());
					response.setContactInfo(savedContact);

					return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(),
							Collections.singletonList(response));
				}
			}

			else if (contactvalidation.isPresent()) {
				personContactRequestModel.getContactInfo().setPersonId(personValidation.get().getId());
				CustomerContactNew savedContact = contactRepository.save(personContactRequestModel.getContactInfo());
				if (savedContact.getDesignationName() != null && savedContact.getDesignationId() != null
						&& savedContact.getDesignationId() == 0 && !savedContact.getDesignationName().isEmpty()) {

					String name = savedContact.getDesignationName();
					String code = savedContact.getDesignationName();
					String description = savedContact.getDesignationName();
					Integer createdBy = Integer.valueOf(savedContact.getCreatedBy().intValue());
					Date createdDate = savedContact.getCreatedDate();
					personRepository.addDesignationInfo(name, code, description, createdBy, createdDate);

				}

				ContactPersonResponse response = new ContactPersonResponse();
				response.setPersonInfo(personValidation.get());
				response.setContactInfo(savedContact);

				return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(),
						Collections.singletonList(response));

			} else {
				throw new Exception(String.format("Contact Already Exists with mobile no :%s ",
						personContactRequestModel.getContactInfo().getPrimaryPhoneNumber()));
			}

		} else {

			String personUniqId = personContactRequestModel.getPersonInfo().getFirstName() + "-"
					+ personContactRequestModel.getPersonInfo().getLastName() + "-"
					+ personContactRequestModel.getPersonInfo().getPrimaryPhoneNumber();

			Optional<PersonModel> validatedPerson = personRepository.validatePerson(personUniqId);

//					personRepository.findByPrimaryEmailOrPrimaryPhoneNumber(
//					personContactRequestModel.getPersonInfo().getPrimaryEmail(),
//					personContactRequestModel.getPersonInfo().getPrimaryPhoneNumber());
			Optional<CustomerContactNew> contactvalidation = contactRepository.validateContact(personUniqId);

//					contactRepository.findByPrimaryEmailOrPrimaryPhoneNumber(
//					personContactRequestModel.getPersonInfo().getPrimaryEmail(),
//					personContactRequestModel.getPersonInfo().getPrimaryPhoneNumber());

			if (!validatedPerson.isPresent()) {
				PersonModel savedPersonInfo = personRepository.save(personContactRequestModel.getPersonInfo());

				if (savedPersonInfo.getDesignationName() != null && savedPersonInfo.getDesignationId() != null
						&& savedPersonInfo.getDesignationId() == 0 && !savedPersonInfo.getDesignationName().isEmpty()) {
					String name = savedPersonInfo.getDesignationName();
					String code = savedPersonInfo.getDesignationName();
					String description = savedPersonInfo.getDesignationName();
					Integer createdBy = Integer.valueOf(savedPersonInfo.getCreatedBy().intValue());
					Date createdDate = savedPersonInfo.getCreatedDate();
					personRepository.addDesignationInfo(name, code, description, createdBy, createdDate);
				}

				ContactPersonResponse response = new ContactPersonResponse();
				response.setPersonInfo(savedPersonInfo);
				if (!contactvalidation.isPresent()) {
					CustomerContactNew customerContactData = contactRepository
							.save(ContactHelper.ConvertPersonToContact(savedPersonInfo));
					response.setContactInfo(customerContactData);
				}

				return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(),
						Collections.singletonList(response));

			} else if (validatedPerson.isPresent()) {
				ContactPersonResponse response = new ContactPersonResponse();
				response.setPersonInfo(validatedPerson.get());
				if (!contactvalidation.isPresent()) {
					CustomerContactNew customerContactData = contactRepository
							.save(ContactHelper.ConvertPersonToContact(validatedPerson.get()));
					response.setContactInfo(customerContactData);
				} else {
					throw new Exception(String.format("Contact Already Exists with mobile no :%s ",
							personContactRequestModel.getPersonInfo().getPrimaryPhoneNumber()));
				}

				return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(),
						Collections.singletonList(response));
			} else {
				throw new Exception(String.format("Contact Already Exists with mobile no :%s ",
						personContactRequestModel.getPersonInfo().getPrimaryPhoneNumber()));
			}
		}
		return null;

	}

	public APIResponse personContactLead(PersonContactRequest personContactRequestModel) throws Exception {
		return personContactWrapper(personContactRequestModel);

	}

	public APIResponse getLocationInfo(Long buId, Long orgId) {
		List<Map<String, Object>> orgBuLocCcInfo = personRepository.getOrgBuLocCcInfo(buId, orgId);
		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), orgBuLocCcInfo);
	}

	public APIResponsePaging getPaginationByOrganization(Long organizationId, int pageNo, int pageSize, String sortBy,
			SortType sortType) {
		Pageable pageable = PageRequest.of(pageNo, pageSize,
				sortType == SortType.asc ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
		if (organizationId != null) {
			Page<PersonAddreesModel> findByOrganizationId = addressRepository.findByOrganizationId(organizationId,
					pageable);
			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), findByOrganizationId.getContent(),
					new ArrayList<>(), findByOrganizationId.getNumber(), findByOrganizationId.getTotalElements(),
					findByOrganizationId.getTotalPages());
		} else {
			Page<PersonAddreesModel> findAll = addressRepository.findAll(pageable);
			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), findAll.getContent(),
					new ArrayList<>(), findAll.getNumber(), findAll.getTotalElements(), findAll.getTotalPages());
		}
	}

	public APIResponsePaging getPersonProfilePagenationByOrganization(Long organizationId, int pageNo, int pageSize,
			String sortBy, SortType sortType) {
		Pageable pageable = PageRequest.of(pageNo, pageSize,
				sortType == SortType.asc ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
		if (organizationId != null) {
			Page<PersonModel> findByOrganizationId = personRepository.findByOrganizationId(organizationId, pageable);
			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), findByOrganizationId.getContent(),
					new ArrayList<>(), findByOrganizationId.getNumber(), findByOrganizationId.getTotalElements(),
					findByOrganizationId.getTotalPages());
		} else {
			Page<PersonModel> findAll = personRepository.findAll(pageable);
			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), findAll.getContent(),
					new ArrayList<>(), findAll.getNumber(), findAll.getTotalElements(), findAll.getTotalPages());
		}
	}

	public APIResponsePaging getLangugaePagenationByOrganization(Long organizationId, Long refId, int pageNo,
			int pageSize, String sortBy, SortType sortType) {
		Pageable pageable = PageRequest.of(pageNo, pageSize,
				sortType == SortType.asc ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
		if (organizationId != null) {
			Page<PersonLanguageModel> findByOrganizationId = languageRepository
					.findByOrganizationIdAndRefId(organizationId, refId, pageable);
			Page<PersonLanguageResponse> item = findByOrganizationId.map(model -> {
				PersonLanguageResponse response = PersonConverter.convertPersonLanguageModelToResponse(model);
				Optional<String> lang = languageRepository.getLanguageName(response.getLanguageId());
				Optional<String> ref = languageRepository.getBusinessName(response.getRefId());
				Optional<String> org = languageRepository.getOrganizationName(response.getOrganizationId());
				if (lang.isPresent())
					response.setLanguageName(lang.get());
				if (ref.isPresent())
					response.setBusinessName(ref.get());
				if (org.isPresent())
					response.setOrganizationName(org.get());
				return response;
			});
			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), item.getContent(),
					new ArrayList<>(), item.getNumber(), item.getTotalElements(), item.getTotalPages());
		} else {
			Page<PersonLanguageModel> findByOrganizationId = languageRepository.findAll(pageable);
			Page<PersonLanguageResponse> item = findByOrganizationId.map(model -> {
				PersonLanguageResponse response = PersonConverter.convertPersonLanguageModelToResponse(model);
				Optional<String> lang = languageRepository.getLanguageName(response.getLanguageId());
				Optional<String> ref = languageRepository.getBusinessName(response.getRefId());
				Optional<String> org = languageRepository.getOrganizationName(response.getOrganizationId());
				if (lang.isPresent())
					response.setLanguageName(lang.get());
				if (ref.isPresent())
					response.setBusinessName(ref.get());
				if (org.isPresent())
					response.setOrganizationName(org.get());
				return response;
			});
			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), item.getContent(),
					new ArrayList<>(), item.getNumber(), item.getTotalElements(), item.getTotalPages());
		}
	}

	public APIResponsePaging getRelationPagenationByOrganization(Long organizationId, Long firstPartyId, int pageNo,
			int pageSize, String sortBy, SortType sortType) {
		Pageable pageable = PageRequest.of(pageNo, pageSize,
				sortType == SortType.asc ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
		if (organizationId != null) {
			Page<PersonRelationsModel> findByOrganizationId = relationRepository
					.findByOrganizationIdAndFirstPartyId(organizationId, firstPartyId, pageable);
			Page<PersonRelationsResponse> item = findByOrganizationId.map(model -> {
				PersonRelationsResponse response = PersonConverter.convertPersonRelationModelToResponse(model);
				Optional<String> firstParty = relationRepository.getFirstPartyName(response.getFirstPartyId());
				Optional<String> secondParty = relationRepository.getSecondPartyName(response.getSecondPartyId());
				Optional<String> fromRelation = relationRepository.getFromRelationName(response.getFromRelationId());
				Optional<String> toRelation = relationRepository.getToRelationName(response.getToRelationId());
				if (firstParty.isPresent())
					response.setFirstPartyIdName(firstParty.get());
				if (secondParty.isPresent())
					response.setSecondPartyIdName(secondParty.get());
				if (fromRelation.isPresent())
					response.setFromRelationIdName(fromRelation.get());
				if (toRelation.isPresent())
					response.setToRelationIdName(toRelation.get());
				return response;
			});
			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), item.getContent(),
					new ArrayList<>(), item.getNumber(), item.getTotalElements(), item.getTotalPages());
		} else {
			Page<PersonRelationsModel> findByOrganizationId = relationRepository.findAll(pageable);
			Page<PersonRelationsResponse> item = findByOrganizationId.map(model -> {
				PersonRelationsResponse response = PersonConverter.convertPersonRelationModelToResponse(model);
				Optional<String> firstParty = relationRepository.getFirstPartyName(response.getFirstPartyId());
				Optional<String> secondParty = relationRepository.getSecondPartyName(response.getSecondPartyId());
				Optional<String> fromRelation = relationRepository.getFromRelationName(response.getFromRelationId());
				Optional<String> toRelation = relationRepository.getToRelationName(response.getToRelationId());
				if (firstParty.isPresent())
					response.setFirstPartyIdName(firstParty.get());
				if (secondParty.isPresent())
					response.setSecondPartyIdName(secondParty.get());
				if (fromRelation.isPresent())
					response.setFromRelationIdName(fromRelation.get());
				if (toRelation.isPresent())
					response.setToRelationIdName(toRelation.get());
				return response;
			});
			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), item.getContent(),
					new ArrayList<>(), item.getNumber(), item.getTotalElements(), item.getTotalPages());
		}
	}

	public APIResponsePaging getSocialPagenationByOrganization(Long organizationId, Long refId, int pageNo,
			int pageSize, String sortBy, SortType sortType) {
		Pageable pageable = PageRequest.of(pageNo, pageSize,
				sortType == SortType.asc ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
		if (organizationId != null) {
			Page<PersonSocialModel> findByOrganizationId = personSocialRepository
					.findByOrganizationIdAndRefId(organizationId, refId, pageable);
			Page<PersonSocialResponse> item = findByOrganizationId.map(model -> {
				PersonSocialResponse response = PersonConverter.convertPersonSocialModelToResponse(model);
				Optional<String> type = personSocialRepository.getTypeName(response.getType());
				Optional<String> ref = personSocialRepository.getBusinessName(response.getRefId());
				Optional<String> org = personSocialRepository.getOrganizationName(response.getOrganizationId());
				if (type.isPresent())
					response.setTypeName(type.get());
				if (ref.isPresent())
					response.setBusinessName(ref.get());
				if (org.isPresent())
					response.setOrganizationName(org.get());
				return response;
			});
			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), item.getContent(),
					new ArrayList<>(), item.getNumber(), item.getTotalElements(), item.getTotalPages());
		} else {
			Page<PersonSocialModel> findByOrganizationId = personSocialRepository.findAll(pageable);
			Page<PersonSocialResponse> item = findByOrganizationId.map(model -> {
				PersonSocialResponse response = PersonConverter.convertPersonSocialModelToResponse(model);
				Optional<String> type = personSocialRepository.getTypeName(response.getType());
				Optional<String> ref = personSocialRepository.getBusinessName(response.getRefId());
				Optional<String> org = personSocialRepository.getOrganizationName(response.getOrganizationId());
				if (type.isPresent())
					response.setTypeName(type.get());
				if (ref.isPresent())
					response.setBusinessName(ref.get());
				if (org.isPresent())
					response.setOrganizationName(org.get());
				return response;
			});
			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), item.getContent(),
					new ArrayList<>(), item.getNumber(), item.getTotalElements(), item.getTotalPages());
		}
	}

	public APIResponsePaging getPidPagenationByOrganization(Long organizationId, Long refId, int pageNo, int pageSize,
			String sortBy, SortType sortType) {

		Pageable pageable = PageRequest.of(pageNo, pageSize,
				sortType == SortType.asc ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
		if (organizationId != null) {
			Page<PersonPidModel> findByOrganizationId = pidRepository.findByOrganizationIdAndRefId(organizationId,
					refId, pageable);
			Page<PersonPidResponse> item = findByOrganizationId.map(model -> {
				PersonPidResponse response = PersonConverter.convertPersonPidToResponse(model);
				Optional<String> type = pidRepository.getTypeName(response.getType());
				Optional<String> ref = pidRepository.getBusinessName(response.getRefId());
				Optional<String> org = pidRepository.getOrganizationName(response.getOrganizationId());
				if (type.isPresent())
					response.setTypeName(type.get());
				if (ref.isPresent())
					response.setBusinessName(ref.get());
				if (org.isPresent())
					response.setOrganizationName(org.get());
				return response;
			});
			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), item.getContent(),
					new ArrayList<>(), item.getNumber(), item.getTotalElements(), item.getTotalPages());
		} else {
			Page<PersonPidModel> findByOrganizationId = pidRepository.findAll(pageable);
			Page<PersonPidResponse> item = findByOrganizationId.map(model -> {
				PersonPidResponse response = PersonConverter.convertPersonPidToResponse(model);
				Optional<String> type = pidRepository.getTypeName(response.getType());
				Optional<String> ref = pidRepository.getBusinessName(response.getRefId());
				Optional<String> org = pidRepository.getOrganizationName(response.getOrganizationId());
				if (type.isPresent())
					response.setTypeName(type.get());
				if (ref.isPresent())
					response.setBusinessName(ref.get());
				if (org.isPresent())
					response.setOrganizationName(org.get());
				return response;
			});
			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), item.getContent(),
					new ArrayList<>(), item.getNumber(), item.getTotalElements(), item.getTotalPages());
		}
	}

	public APIResponsePaging getQualificationPagenationByOrganization(Long organizationId, Long refId, int pageNo,
			int pageSize, String sortBy, SortType sortType) {
		Pageable pageable = PageRequest.of(pageNo, pageSize,
				sortType == SortType.asc ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
		if (organizationId != null) {
			Page<PersonQualificationModel> findByOrganizationId = qualificationRepository
					.findByOrganizationIdAndRefId(organizationId, refId, pageable);
			Page<PersonQualificationResponse> item = findByOrganizationId.map(model -> {
				PersonQualificationResponse response = PersonConverter.convertQualificationModelToResponse(model);
				Optional<String> type = qualificationRepository.getQualificationName(response.getQualificationType());
				Optional<String> ref = qualificationRepository.getBusinessName(response.getRefId());
				Optional<String> org = qualificationRepository.getOrganizationName(response.getOrganizationId());
				if (type.isPresent())
					response.setQualificationTypeName(type.get());
				if (ref.isPresent())
					response.setBusinessName(ref.get());
				if (org.isPresent())
					response.setOrganizationName(org.get());
				return response;
			});
			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), item.getContent(),
					new ArrayList<>(), item.getNumber(), item.getTotalElements(), item.getTotalPages());
		} else {
			Page<PersonQualificationModel> findByOrganizationId = qualificationRepository.findAll(pageable);
			Page<PersonQualificationResponse> item = findByOrganizationId.map(model -> {
				PersonQualificationResponse response = PersonConverter.convertQualificationModelToResponse(model);
				Optional<String> type = qualificationRepository.getQualificationName(response.getQualificationType());
				Optional<String> ref = qualificationRepository.getBusinessName(response.getRefId());
				Optional<String> org = qualificationRepository.getOrganizationName(response.getOrganizationId());
				if (type.isPresent())
					response.setQualificationTypeName(type.get());
				if (ref.isPresent())
					response.setBusinessName(ref.get());
				if (org.isPresent())
					response.setOrganizationName(org.get());
				return response;
			});
			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), item.getContent(),
					new ArrayList<>(), item.getNumber(), item.getTotalElements(), item.getTotalPages());
		}
	}

	public APIResponsePaging getVerificationPagenationByOrganization(Long organizationId, Long refId, int pageNo,
			int pageSize, String sortBy, SortType sortType) {
		Pageable pageable = PageRequest.of(pageNo, pageSize,
				sortType == SortType.asc ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
		if (organizationId != null) {
			Page<PersonVerificationModel> findByOrganizationId = verificationRepository
					.findByOrganizationIdAndRefId(organizationId, refId, pageable);
			Page<PersonVerificationResponse> item = findByOrganizationId.map(model -> {
				PersonVerificationResponse response = PersonConverter.convertPersonVerficationModelToResponse(model);
				Optional<String> type = verificationRepository.getTypeName(response.getType());
				Optional<String> ref = verificationRepository.getBusinessName(response.getRefId());
				Optional<String> org = verificationRepository.getOrganizationName(response.getOrganizationId());
				if (type.isPresent())
					response.setTypeName(type.get());
				if (ref.isPresent())
					response.setBusinessName(ref.get());
				if (org.isPresent())
					response.setOrganizationName(org.get());
				return response;
			});
			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), item.getContent(),
					new ArrayList<>(), item.getNumber(), item.getTotalElements(), item.getTotalPages());
		} else {
			Page<PersonVerificationModel> findByOrganizationId = verificationRepository.findAll(pageable);
			Page<PersonVerificationResponse> item = findByOrganizationId.map(model -> {
				PersonVerificationResponse response = PersonConverter.convertPersonVerficationModelToResponse(model);
				Optional<String> type = verificationRepository.getTypeName(response.getType());
				Optional<String> ref = verificationRepository.getBusinessName(response.getRefId());
				Optional<String> org = verificationRepository.getOrganizationName(response.getOrganizationId());
				if (type.isPresent())
					response.setTypeName(type.get());
				if (ref.isPresent())
					response.setBusinessName(ref.get());
				if (org.isPresent())
					response.setOrganizationName(org.get());
				return response;
			});
			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), item.getContent(),
					new ArrayList<>(), item.getNumber(), item.getTotalElements(), item.getTotalPages());
		}
	}

}
