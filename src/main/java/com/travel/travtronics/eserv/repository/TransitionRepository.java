package com.travel.travtronics.eserv.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.travel.travtronics.enums.StatusActive;
import com.travel.travtronics.eserv.model.Transition;
import com.travel.travtronics.response.TransitionDto;

public interface TransitionRepository extends JpaRepository<Transition, Long> {

	Optional<Transition> findBytransitionId(Long transitionId);

	@Query(value = "SELECT a.TransitionId AS transitionId, CONCAT(b.`name`,'->',c.`name`) AS transition ,\r\n" + 
			"a.FromStatus AS fromStatus,b.name AS fromStatusName,a.ToStatus AS toStatus, c.name AS toStatusName,\r\n" + 
			"a.Organization AS organization, mo.Name AS organizationName\r\n" + 
			"FROM transition AS a\r\n" + 
			"LEFT JOIN status AS b ON b.StatusId=a.FromStatus\r\n" + 
			"LEFT JOIN status AS c ON c.StatusId=a.ToStatus\r\n" + 
			"LEFT JOIN master_organization AS mo ON mo.OrganizationId = a.Organization\r\n" + 
			"WHERE a.Organization = ?1", nativeQuery = true)
	List<Map<String, String>> findByBpfTransition(Long organization);

	@Query(value = "SELECT t.TransitionId AS transitionId,t.CreatedBy AS createdBy,t.CreatedDate AS cretedDate,\r\n"
			+ "t.DeafultStatus AS deafultStatus,t.Status AS status,t.UpdatedBy AS updatedBy,t.UpdatedDate AS upDatedDate,\r\n"
			+ "t.ModuleName AS moduleName,m.ModuleName AS moduleNames,t.FromStatus AS fromStatus,s.name AS fromStatusName,\r\n"
			+ "t.ToStatus AS toStatus,st.name AS toStatusName,t.Organization AS organization FROM transition t\r\n"
			+ "LEFT JOIN module m ON m.ModuleId = t.ModuleName\r\n"
			+ "LEFT JOIN status s ON s.StatusId = t.FromStatus\r\n"
			+ "LEFT JOIN status st ON st.StatusId = t.ToStatus\r\n" + "WHERE t.Organization=?1", nativeQuery = true)
	List<Map<String, String>> findByList(Long organizationId);

	@Query(value = "SELECT trans.transitionId,trans.fromStatus AS fromStatusId,fromstatus.name AS fromStatus,\r\n"
			+ "trans.toStatus AS toStatusId,tostatus.name AS toStatus\r\n" + "FROM transition trans\r\n"
			+ "LEFT JOIN  status fromstatus ON fromstatus.statusId=trans.fromStatus\r\n"
			+ "LEFT JOIN  status tostatus ON tostatus.statusId=trans.toStatus\r\n" + "WHERE \r\n"
			+ "trans.fromStatus=?1", nativeQuery = true)
	List<TransitionDto> getTransitionInfoByFromStatusId(Integer fromStatusId);

	@Query(value = "SELECT trans.transitionId,trans.fromStatus AS fromStatusId,fromstatus.name AS fromStatus,\r\n"
			+ "trans.toStatus AS toStatusId,tostatus.name AS toStatus\r\n" + "FROM transition trans\r\n"
			+ "LEFT JOIN  status fromstatus ON fromstatus.statusId=trans.fromStatus\r\n"
			+ "LEFT JOIN  status tostatus ON tostatus.statusId=trans.toStatus\r\n" + "WHERE \r\n"
			+ "trans.fromStatus=?1 AND trans.ModuleName = ?2", nativeQuery = true)
	List<TransitionDto> getTransitionInfoByFromStatusIdAndModuleId(Long fromStatusId, Long moduleId);

	Page<Transition> findByOrganization(Long organization, Pageable pageable);

//	Optional<Transition> findByOrganizationOrModuleNameOrFromStatusOrToStatus(Long organization, String moduleName,
//			Integer fromStatus, Integer toStatus, StatusActive active);

//	Optional<Transition> findByOrganizationOrModuleNameOrFromStatusOrToStatusOrStatus(Long organization,
//			String moduleName, Integer fromStatus, Integer toStatus, StatusActive active);

	Optional<Transition> findByOrganizationAndModuleNameAndFromStatusAndToStatusAndStatus(Long organization,
			String moduleName, Integer fromStatus, Integer toStatus, StatusActive active);

	Boolean existsByOrganizationAndModuleNameAndFromStatusAndToStatusAndStatus(Long organization, String moduleName, Integer fromStatus,
			Integer toStatus, StatusActive active);

//	Boolean existsByOrganizationAndModuleNameAndFromStatusAndToStatusAndStatus(Long organization, String moduleName,
//			Integer fromStatus, Integer toStatus, StatusActive status);
//

}
