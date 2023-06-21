package com.travel.travtronics.controller;

import javax.validation.Valid;

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
import com.travel.travtronics.request.EmpRelationShipsRequest;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.APIResponsePaging;
import com.travel.travtronics.response.MessageStatusResponse;
import com.travel.travtronics.service.EmpRelationshipsService;
import com.travel.travtronics.util.NotFoundException;

@RestController
@RequestMapping("/emp_relationships")
public class EmpRelationshipsController {
	
	@Autowired
	EmpRelationshipsService empRelationshipsService;
	
	@PostMapping
	public MessageStatusResponse createAddress(@Valid @RequestBody EmpRelationShipsRequest requestModel) {
		return empRelationshipsService.save(requestModel);
	}
	
	@PutMapping("/update")
	public MessageStatusResponse editRelationShips(@Valid @RequestBody EmpRelationShipsRequest requestModel)
			throws NotFoundException {
		return empRelationshipsService.editRelationShips(requestModel);
	}
	
	@GetMapping("/{relationShipId}")
	public APIResponse getInfoById(@PathVariable Long relationShipId) throws NotFoundException {
		return empRelationshipsService.getInfoById(relationShipId);
	}
	
	@GetMapping("/getbyfirstpartyid")
	public APIResponse getByFirstPartyId(@RequestParam Long firstPartyId)
			throws NotFoundException {
		return empRelationshipsService.getByFirstPartyId(firstPartyId);
	}
	
	@GetMapping(value = "/get-pagination")
    public APIResponsePaging getPagenationByOrganization(@RequestParam(required = false) Long organizationId,@RequestParam(required = false) Long firstPartyId,
                                                @RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize,
                                                @RequestParam(defaultValue = "relationShipId") String sortBy,@RequestParam(defaultValue = "asc", required = false) SortType sortType) {
        return  empRelationshipsService.getPagenationByOrganization(organizationId, firstPartyId, pageNo, pageSize, sortBy, sortType);
    }

}
