package com.travel.travtronics.controller;

import com.travel.travtronics.enums.SortType;
import com.travel.travtronics.eserv.model.StatusOwnerMapping;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.APIResponsePaging;
import com.travel.travtronics.service.StatusOwnerMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/status-owner-mapping")
public class StatusOwnerMappingController {

    @Autowired
    StatusOwnerMappingService mappingService;

    @PostMapping(value = "create", consumes = "application/json", produces = "application/json")
    public APIResponse create(@RequestBody StatusOwnerMapping model) {
        return mappingService.create(model);
    }

    @PutMapping(value="/update", consumes= "application/json", produces ="application/json")
    public APIResponse update(@RequestBody StatusOwnerMapping data) {
        return mappingService.update(data);
    }

    @GetMapping(value="/id", produces ="application/json")
    public APIResponse getId(@RequestParam Long id) {
        return mappingService.findById(id);
    }

    @GetMapping(value = "/get-all-by-pagings", produces ="application/json")
    public APIResponsePaging getAllByPaging(	@RequestParam(required = false) Long organization,
                                                @RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize,
                                                @RequestParam(defaultValue = "id") String sortBy, @RequestParam(defaultValue = "asc", required = false) SortType sortType) {
        return  mappingService.getAllByPaging(organization,pageNo, pageSize, sortBy, sortType);
    }

    @GetMapping(value="/get-all-by-organization", produces ="application/json")
    public APIResponse getAllStatusByOrganization(@RequestParam Long organization) {
        return mappingService.getAllStatusByOrganization(organization);
    }

    @GetMapping(value="/get-all", produces ="application/json")
    public APIResponse getAll(@RequestParam Long organization) {
        return mappingService.getAll(organization);
    }
    
    @GetMapping(value="/get-team-info", produces ="application/json")
    public APIResponse getTeamInfo(@RequestParam Long deafultStatus, @RequestParam Long organization, @RequestParam Long module) {
        return mappingService.getTeamInfo(deafultStatus, organization, module);
    }
    /**already written by some one**/
    @GetMapping(value = "/get-all-by-paging", produces ="application/json")
    public APIResponsePaging getAllByPaging(
                                                @RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize,
                                                @RequestParam(defaultValue = "id") String sortBy, @RequestParam(defaultValue = "asc", required = false) SortType sortType) {
        return  mappingService.getAllByPaging(pageNo, pageSize, sortBy, sortType);
    }
    
    /**
	 * Get Team and status info and Status transitions info also by passing StatusId, OrganizationId & ModuleId
	 * Combined two calls into one call - added Nag
	 * @param statusId
	 * @param organizationId
	 * @param moduleId
	 * @return
	 */
    @GetMapping(value="/get-team-and-status-info", produces ="application/json")
    public APIResponse getTeamAndStatusInfo(@RequestParam Long deafultStatus, @RequestParam Long organization, @RequestParam Long module) {
        return mappingService.getTeamAndStatusInfoDetails(deafultStatus, organization, module);
    }


}
