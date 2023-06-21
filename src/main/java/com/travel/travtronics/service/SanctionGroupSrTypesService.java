package com.travel.travtronics.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.travel.travtronics.eserv.model.SanctionGroupSrTypes;
import com.travel.travtronics.eserv.repository.SanctionGroupSrTypesRepository;
import com.travel.travtronics.response.APIResponse;

@Service
public class SanctionGroupSrTypesService {

	@Autowired
	SanctionGroupSrTypesRepository sanctionGroupSrTypesRepository;

	public APIResponse create(List<SanctionGroupSrTypes> model) {
		try {
			 List<SanctionGroupSrTypes> save = sanctionGroupSrTypesRepository.saveAll(model);
			return new APIResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED.name(), save);

		} catch (Exception ex) {
			ex.printStackTrace();
			return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name(),
					new ArrayList<>());
		}
	}

	public APIResponse update(List<SanctionGroupSrTypes> model) {
		try {
			 List<SanctionGroupSrTypes> save = sanctionGroupSrTypesRepository.saveAll(model);
			return new APIResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED.name(), save);

		} catch (Exception ex) {
			ex.printStackTrace();
			return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name(),
					new ArrayList<>());
		}
	}

	public APIResponse getBySanctionGroup(Long sanctionGroup) {
		try {
			List<Map<String, String>> list = sanctionGroupSrTypesRepository.findBySanctionGroupList(sanctionGroup);
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
}
