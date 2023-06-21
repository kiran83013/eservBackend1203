package com.travel.travtronics.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.travel.travtronics.enums.SortType;
import com.travel.travtronics.eserv.model.PersonAddreesModel;
import com.travel.travtronics.eserv.model.PersonLanguageModel;
import com.travel.travtronics.eserv.model.PersonModel;
import com.travel.travtronics.eserv.model.PersonPidModel;
import com.travel.travtronics.eserv.model.PersonQualificationModel;
import com.travel.travtronics.eserv.model.PersonRelationsModel;
import com.travel.travtronics.eserv.model.PersonSocialModel;
import com.travel.travtronics.eserv.model.PersonVerificationModel;
import com.travel.travtronics.request.PersonContactRequest;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.APIResponsePaging;
import com.travel.travtronics.response.MessageStatusResponse;
import com.travel.travtronics.service.PersonService;
import com.travel.travtronics.util.NotFoundException;

@RestController
@RequestMapping("/person")
public class PersonController {

	@Autowired
	PersonService personService;

	/*
	 * person-apis
	 */

	@PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
	public APIResponse createPerson(@RequestBody PersonModel request) {
		return personService.createPerson(request);
	}

	@GetMapping(value = "/get-person-id", produces = "application/json")
	public APIResponse getByid(@RequestParam Long id) throws NotFoundException {
		return personService.getByid(id);
	}

	@GetMapping(value = "/get-person-all", produces = "application/json")
	public APIResponse getAll(@RequestParam Long orgId) {
		return personService.getAll(orgId);
	}

	@PutMapping(value = "/modify", consumes = "application/json", produces = "application/json")
	public MessageStatusResponse updatePerson(@RequestBody PersonModel model) {
		return personService.updatePerson(model);
	}

	@GetMapping(value = "/get-person-search/{search}", produces = "application/json")
	public APIResponse getSearchByValue(@PathVariable String search) {
		return personService.getSearchByValue(search);
	}

	@GetMapping(value = "/search", produces = "application/json")
	public APIResponse search(@RequestParam(required = false) String firstName,
			@RequestParam(required = false) String lastName, @RequestParam(required = false) Long phoneNo,
			@RequestParam(required = false) String email, @RequestParam(required = false) Long customerId) {
		return personService.search(firstName, lastName, phoneNo, email, customerId);
	}

	@GetMapping(value = "/list-personprofile-pagination")
	public APIResponsePaging getPersonProfilePagenationByOrganization(
			@RequestParam(required = false) Long organizationId, @RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "10") int pageSize, @RequestParam(defaultValue = "id") String sortBy,
			@RequestParam(defaultValue = "asc", required = false) SortType sortType) {
		return personService.getPersonProfilePagenationByOrganization(organizationId, pageNo, pageSize, sortBy,
				sortType);
	}

	/*
	 * adress-apis
	 */
	@PostMapping(value = "/create-address", consumes = "application/json", produces = "application/json")
	public APIResponse createPersonAddress(@RequestBody PersonAddreesModel request) {
		return personService.createPersonAddress(request);
	}

	@GetMapping(value = "/get-address-id", produces = "application/json")
	public APIResponse getAddressByid(@RequestParam Long id) throws NotFoundException {
		return personService.getAddressByid(id);
	}

	@GetMapping("/all-address")
	public APIResponse getAllAddressByPersonInfo(@RequestParam Long refId, @RequestParam Long orgId)
			throws NotFoundException {
		return personService.getAllAddressByPersonInfo(refId, orgId);

	}

	@GetMapping(value = "/list-address-pagination")
	public APIResponsePaging getPagenationByOrganization(@RequestParam(required = false) Long organizationId,
			@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize,
			@RequestParam(defaultValue = "addressId") String sortBy,
			@RequestParam(defaultValue = "asc", required = false) SortType sortType) {
		return personService.getPaginationByOrganization(organizationId, pageNo, pageSize, sortBy, sortType);
	}

	@PutMapping("/edit-address")
	public MessageStatusResponse editPersonAddress(@RequestBody PersonAddreesModel model) throws NotFoundException {
		return personService.editPersonAddress(model);
	}

	/*
	 * relation apis
	 */
	@PostMapping(value = "/create-relation", consumes = "application/json", produces = "application/json")
	public MessageStatusResponse createRealation(@RequestBody PersonRelationsModel request) {
		return personService.createRealation(request);
	}

	@GetMapping(value = "/get-relation-id", produces = "application/json")
	public APIResponse getRelationByid(@RequestParam Long id) throws NotFoundException {
		return personService.getRelationByid(id);
	}

	@PutMapping(value = "/modify-realtion", consumes = "application/json", produces = "application/json")
	public MessageStatusResponse modifyRealation(@RequestBody PersonRelationsModel request) throws NotFoundException {
		return personService.modifyRelation(request);
	}

	@GetMapping(value = "/get-relations", produces = "application/json")
	public APIResponse getByFirstPartyId(@RequestParam Long firstPartyId, @RequestParam Long orgId)
			throws NotFoundException {
		return personService.getByFirstPartyId(firstPartyId, orgId);
	}

	@GetMapping(value = "/list-relation-pagination")
	public APIResponsePaging getRelationPagenationByOrganization(@RequestParam(required = false) Long organizationId,
			@RequestParam(required = false) Long firstPartyId, @RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "10") int pageSize,
			@RequestParam(defaultValue = "relationShipId") String sortBy,
			@RequestParam(defaultValue = "asc", required = false) SortType sortType) {
		return personService.getRelationPagenationByOrganization(organizationId, firstPartyId, pageNo, pageSize, sortBy,
				sortType);
	}

	/*
	 * social apis
	 */

	@PostMapping(value = "/create-social", consumes = "application/json", produces = "application/json")
	public MessageStatusResponse createSocial(@RequestBody List<PersonSocialModel> requests) {
		return personService.createSocial(requests);
	}

	@GetMapping(value = "/get-social-id", produces = "application/json")
	public APIResponse getSocialByid(@RequestParam Long id) throws NotFoundException {
		return personService.getSocialByid(id);
	}

	@PutMapping(value = "/modify-social", consumes = "application/json", produces = "application/json")
	public MessageStatusResponse updateSocial(@RequestBody PersonSocialModel model) throws NotFoundException {
		return personService.updateSocial(model);
	}

	@GetMapping(value = "/get-social-by-refid", produces = "application/json")
	public APIResponse getSocialByRefId(@RequestParam Long refId, @RequestParam Long orgId) {
		return personService.getSocialByRefId(refId, orgId);
	}

	@GetMapping(value = "/list-social-pagination")
	public APIResponsePaging getSocialPagenationByOrganization(@RequestParam(required = false) Long organizationId,
			@RequestParam(required = false) Long refId, @RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "10") int pageSize, @RequestParam(defaultValue = "id") String sortBy,
			@RequestParam(defaultValue = "asc", required = false) SortType sortType) {
		return personService.getSocialPagenationByOrganization(organizationId, refId, pageNo, pageSize, sortBy,
				sortType);
	}

	/*
	 * pid apis
	 */

	@PostMapping(value = "/create-pid", consumes = "application/json", produces = "application/json")
	public MessageStatusResponse createPid(@RequestBody List<PersonPidModel> models) {
		return personService.createPid(models);
	}

	@GetMapping(value = "/get-pid-id", produces = "application/json")
	public APIResponse getPidByid(@RequestParam Long id) throws NotFoundException {
		return personService.getPidByid(id);
	}

	@PutMapping(value = "/modify-pid", consumes = "application/json", produces = "application/json")
	public MessageStatusResponse updatePid(@RequestBody PersonPidModel model) throws NotFoundException {
		return personService.updatePid(model);
	}

	@GetMapping(value = "/get-pid-by-refid", produces = "application/json")
	public APIResponse getPidByRefId(@RequestParam Long refId, @RequestParam Long orgId) {
		return personService.getPidByRefId(refId, orgId);
	}

	@GetMapping(value = "/list-pid-pagination")
	public APIResponsePaging getPidPagenationByOrganization(@RequestParam(required = false) Long organizationId,
			@RequestParam(required = false) Long refId, @RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "10") int pageSize, @RequestParam(defaultValue = "id") String sortBy,
			@RequestParam(defaultValue = "asc", required = false) SortType sortType) {
		return personService.getPidPagenationByOrganization(organizationId, refId, pageNo, pageSize, sortBy, sortType);
	}

	/*
	 * language-apis
	 */
	@PostMapping(value = "/create-language", consumes = "application/json", produces = "application/json")
	public MessageStatusResponse createLanguage(@RequestBody List<PersonLanguageModel> models) {
		return personService.createLanguage(models);
	}

	@GetMapping(value = "/get-language-id", produces = "application/json")
	public APIResponse getLanguageByid(@RequestParam Long id) throws NotFoundException {
		return personService.getLanguageByid(id);
	}

	@PutMapping(value = "/modify-language", consumes = "application/json", produces = "application/json")
	public MessageStatusResponse modifyLanguage(@RequestBody List<PersonLanguageModel> model) {
		return personService.modifyLanguage(model);
	}

	@GetMapping(value = "/get-language-by-refid", produces = "application/json")
	public APIResponse getLanguageByRefId(@RequestParam Long refId, @RequestParam Long orgId) {
		return personService.getLanguageByRefId(refId, orgId);
	}

	@GetMapping(value = "/list-language-pagination")
	public APIResponsePaging getLangugaePagenationByOrganization(@RequestParam(required = false) Long organizationId,
			@RequestParam(required = false) Long refId, @RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "10") int pageSize, @RequestParam(defaultValue = "id") String sortBy,
			@RequestParam(defaultValue = "asc", required = false) SortType sortType) {
		return personService.getLangugaePagenationByOrganization(organizationId, refId, pageNo, pageSize, sortBy,
				sortType);
	}
	/*
	 * verification-apis
	 */

	@PostMapping(value = "/create-verification", consumes = "application/json", produces = "application/json")
	public MessageStatusResponse cretaeVerifications(@RequestBody List<PersonVerificationModel> models) {
		return personService.cretaeVerifications(models);
	}

	@GetMapping(value = "get-verification-by-id", produces = "application/json")
	public APIResponse getVerfificationId(@RequestParam Long id) throws NotFoundException {
		return personService.getVerfificationId(id);
	}

	@PutMapping(value = "/modify-verification", consumes = "application/json", produces = "application/json")
	public MessageStatusResponse modifyVerifications(@RequestBody List<PersonVerificationModel> models) {
		return personService.modifyVerifications(models);
	}

	@GetMapping(value = "/get-verification-by-refid", produces = "application/json")
	public APIResponse getVerificationByRefId(@RequestParam Long refId, @RequestParam Long orgId) {
		return personService.getVerificationByRefId(refId, orgId);
	}

	@GetMapping(value = "/list-verification-pagination")
	public APIResponsePaging getVerificationPagenationByOrganization(
			@RequestParam(required = false) Long organizationId, @RequestParam(required = false) Long refId,
			@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize,
			@RequestParam(defaultValue = "id") String sortBy,
			@RequestParam(defaultValue = "asc", required = false) SortType sortType) {
		return personService.getVerificationPagenationByOrganization(organizationId, refId, pageNo, pageSize, sortBy,
				sortType);
	}

	/*
	 * qualification-apis
	 */

	@PostMapping(value = "/create-qualification", consumes = "application/json", produces = "application/json")
	public MessageStatusResponse cretaeQualification(@RequestBody List<PersonQualificationModel> models) {
		return personService.cretaeQualification(models);
	}

	@GetMapping(value = "get-qualification-by-id", produces = "application/json")
	public APIResponse getQualificationId(@RequestParam Long id) throws NotFoundException {
		return personService.getQualificationId(id);
	}

	@PutMapping(value = "/modify-qualification", consumes = "application/json", produces = "application/json")
	public MessageStatusResponse modifyQualification(@RequestBody List<PersonQualificationModel> models) {
		return personService.modifyQualification(models);
	}

	@GetMapping(value = "/get-qualification-by-refid", produces = "application/json")
	public APIResponse getQualificationByRefId(@RequestParam Long refId, @RequestParam Long orgId) {
		return personService.getQualificationByRefId(refId, orgId);
	}

	@GetMapping(value = "/list-qualification-pagination")
	public APIResponsePaging getQualificationPagenationByOrganization(
			@RequestParam(required = false) Long organizationId, @RequestParam(required = false) Long refId,
			@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize,
			@RequestParam(defaultValue = "id") String sortBy,
			@RequestParam(defaultValue = "asc", required = false) SortType sortType) {
		return personService.getQualificationPagenationByOrganization(organizationId, refId, pageNo, pageSize, sortBy,
				sortType);
	}

	/*
	 * person-contact-wrapper-apis
	 */
	@PostMapping(value = "/person-contact-wrapper", consumes = "application/json", produces = "application/json")
	public APIResponse personContactWrapper(@RequestBody PersonContactRequest personContactModel) throws Exception {
		return personService.personContactWrapper(personContactModel);
	}

	@GetMapping(value = "/get-loc-bu-org-cc-info", produces = "application/json")
	public APIResponse getLocationInfo(@RequestParam Long BuId, @RequestParam Long orgId) {
		return personService.getLocationInfo(BuId, orgId);
	}

	
}
