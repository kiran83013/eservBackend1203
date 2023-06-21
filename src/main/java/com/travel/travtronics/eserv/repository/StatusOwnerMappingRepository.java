package com.travel.travtronics.eserv.repository;

import com.travel.travtronics.enums.Status;
import com.travel.travtronics.eserv.model.StatusOwnerMapping;
import com.travel.travtronics.request.UserTeamDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface StatusOwnerMappingRepository extends JpaRepository<StatusOwnerMapping, Long> {
	List<StatusOwnerMapping> findByOrganizationAndStatus(Long organization,Status string);

	List<StatusOwnerMapping> findByOrganizationAndDefaultStatusAndTeamAndModuleAndStatus(Long organization, Long defaultStatus,
			Long team, Long module,Status string);

	@Query(value = "SELECT som.id,som.team,t.TeamName AS teamName,emp.ID AS userId,emp.userName \r\n" + 
			"FROM e_services.status_owner_mapping som\r\n" + 
			"LEFT JOIN e_services.team t ON t.TeamId=som.team\r\n" + 
			"LEFT JOIN e_services.member_to_team mtt ON som.team=mtt.TeamId\r\n" + 
			"LEFT JOIN e_services_be_user.a3m_account emp ON mtt.UserName=emp.id\r\n" + 
			"WHERE som.default_status=?1 AND som.status='Active' AND som.organization =?2 AND som.module =?3", nativeQuery = true)
	List<UserTeamDto> getStatusOwnerInfo(Long defaultStatus, Long organization, Long module);

	@Query(value = "SELECT team.TeamId  AS teamId,team.TeamName AS teamName \n" +
			"FROM status_owner_mapping sow \n" +
			"LEFT JOIN team ON sow.team=team.TeamId \n" +
			"WHERE team.Status='Active' AND sow.default_status=?1 AND sow.organization =?2 AND sow.module =?3", nativeQuery = true)
	List<Map<String, String>> getTeamInfo(Long deafultStatus, Long organization, Long module);

//	
	@Query(value= "SELECT som.default_status AS id,st.name FROM status_owner_mapping som LEFT JOIN status AS st ON som.default_status = st.StatusId WHERE  som.status='Active' AND som.organization=?1", nativeQuery = true)
	List<Map<String, String>> getAllStatusByOrganization(Long organization);
	
	@Query(value = "SELECT som.id AS id, som.default_status AS defaultStatus,som.organization,som.module,som.team,som.end_date AS endDate, som.start_date AS startDate, som.status, som.created_by AS createdBy,\r\n" + 
			"som.created_date AS createdDate, som.updated_by AS updatedBy, som.updated_date AS updatedDate,\r\n" + 
			"s.name AS deafultStatusName,m.ModuleName AS moduleName,t.TeamName AS teamName FROM status_owner_mapping som\r\n" + 
			"LEFT JOIN e_services.status s ON s.StatusId = som.default_status\r\n" + 
			"LEFT JOIN e_services.module m ON m.ModuleId = som.module\r\n" + 
			"LEFT JOIN e_services.team t ON t.TeamId = som.team WHERE som.status = 'ACTIVE' AND som.organization =?1",nativeQuery = true)
	List<Map<String, String>> findByOrganization(Long organization, Status active);

	Page<StatusOwnerMapping> findByOrganization(Long organization, Pageable pageable);

	


}
