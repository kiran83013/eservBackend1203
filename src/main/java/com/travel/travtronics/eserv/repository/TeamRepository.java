package com.travel.travtronics.eserv.repository;

import java.util.List;
import java.util.Map;
//import java.util.List;
//import java.util.Map;
import java.util.Optional;

import com.travel.travtronics.enums.Status;
import com.travel.travtronics.enums.StatusActive;
import com.travel.travtronics.eserv.model.Team;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Query;


public interface TeamRepository extends JpaRepository<Team, Long>{

	Optional<Team> findByTeamId(Long teamId);
	
	@Query(value = "SELECT t.TeamId AS teamId ,t.EndDate AS endDate,t.StartDate AS startDate,t.Status AS status,\r\n" + 
			"t.TeamName AS teamName,t.TeamEmail AS teamEmail,t.CreatedBy AS createdBy,t.CreatedDate AS createdDate ,\r\n" + 
			"t.UpDatedBy AS upDatedBy,t.UpDatedDate AS upDatedDate,t.moduleId,m.ModuleName AS moduleName ,t.departmentId,\r\n" + 
			"md.name AS departmentName,t.organizationId,mo.name AS organizationName,t.businessUnit,mb.name AS businessName\r\n" + 
			"FROM team t\r\n" + 
			"LEFT JOIN module m ON m.ModuleId = t.ModuleId\r\n" + 
			"LEFT JOIN master_departments md ON md.DepartmentId = t.DepartmentId\r\n" + 
			"LEFT JOIN master_organization mo ON mo.OrganizationId = t.OrganizationId\r\n" + 
			"LEFT JOIN business_unit mb ON mb.BusinessUnitId = t.BusinessUnit\r\n" + 
			"WHERE t.OrganizationId =?1",nativeQuery = true)
	List<Map<String, String>> findByList(Long organizationId);

	List<Team> findByOrganizationIdAndModuleIdAndStatus(Long organizationId, Long moduleId,StatusActive status);

	Page<Team> findByOrganizationId(Long OrganizationId,Pageable page);

	Optional<Team> findByTeamName(String teamName);
}
