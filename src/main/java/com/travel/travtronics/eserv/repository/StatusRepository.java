package com.travel.travtronics.eserv.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.travel.travtronics.enums.StatusActive;
import com.travel.travtronics.eserv.model.Status;


public interface StatusRepository extends JpaRepository<Status, Long>{

	Optional<Status> findBystatusId(Long statusId);
	
	@Query(value ="SELECT * FROM status WHERE StatusId = ?1 AND Module = ?2",nativeQuery = true)
	Optional<Status> getStatusInfoByStatusIdAndModuleId(Long statusId, Long moduleId);

	@Query(value ="SELECT s.StatusId AS statusId,s.CreatedBy AS createdBy,s.CreatedDate AS cretedDate,\r\n" + 
			"s.Description AS description,s.name AS name,s.Status AS status,s.UpdatedBy AS updatedBy,s.UpdatedDate AS updatedDate,\r\n" + 
			"s.isOpen, s.isCancel, s.isClose, s.isExternal, s.isCheckList, s.checkList,\r\n" + 
			"s.Module AS module,m.ModuleName AS moduleName,s.Organization AS organization,mo.name AS organizationName\r\n" + 
			"FROM status s\r\n" + 
			"LEFT JOIN module m ON m.ModuleId =s.Module\r\n" + 
			"LEFT JOIN master_organization mo ON mo.OrganizationId = s.Organization\r\n" + 
			"WHERE organization =?1",nativeQuery = true)
	List<Map<String, String>> findAllList(String organization);

	List<Status> findByOrganizationAndModuleAndStatus(String organizationId,String module, StatusActive status);


	Page<Status> findByOrganization(String organization, Pageable pageable);

	
	


}
