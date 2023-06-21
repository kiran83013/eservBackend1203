package com.travel.travtronics.eserv.repository;
import java.util.List;
import java.util.Map;
//
//import java.util.List;
//import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Query;

import com.travel.travtronics.eserv.model.MembertoTeam;

public interface MembertoTeamRepository extends JpaRepository<MembertoTeam, Long> {

	Optional<MembertoTeam> findByMtoTId(Long mtoTId);

//	List<MembertoTeam> findByTeamId(Long teamId);
	
	@Query(value = "SELECT mt.MtoTId AS mtoTId,\r\n" + 
			"mt.CreatedBy AS createdBy,\r\n" + 
			"mt.CreatedDate AS createdDate,\r\n" + 
			"mt.EndDate AS endDate,\r\n" + 
			"mt.StartDate AS startDate,\r\n" + 
			"mt.Status AS status,\r\n" + 
			"mt.TeamId AS teamId,\r\n" + 
			"t.TeamName AS teamName,\r\n" + 
			"mt.UpDatedBy AS upDatedBy,\r\n" + 
			"mt.UpDatedDate AS upDatedDate,\r\n" + 
			"mt.UserName AS userId,\r\n" + 
			"ep.username AS userName,mt.OrganizationId AS organizationId,\r\n" + 
			"mo.Name AS organizationName\r\n" + 
			"FROM member_to_team AS mt\r\n" + 
			"LEFT JOIN master_organization AS mo ON mo.OrganizationId = mt.OrganizationId\r\n" + 
			"LEFT JOIN team t ON t.TeamId = mt.TeamId\r\n" + 
			"LEFT JOIN e_services_be_user.a3m_account AS ep ON ep.id = mt.UserName\r\n" + 
			"WHERE\r\n" + 
			"mt.TeamId=?1", nativeQuery = true)
	List<Map<String, String>> findByTeamId(Long teamId);
	
	@Query(value = "SELECT mt.MtoTId AS mtoTid,mt.CreatedBy AS createdBy,mt.CreatedDate AS createdDate,\r\n" + 
			"mt.EndDate AS endDate,mt.StartDate AS startDate,mt.Status AS status,mt.OrganizationId AS organizationId,\r\n" + 
			"mo.Name AS organizationName,\r\n" + 
			"mt.UpDatedBy AS updatedBy,mt.UpDatedDate AS updatedDate, \r\n" + 
			"t.TeamName AS teamName,mt.TeamId AS teamId,mt.userName AS userId,ep.username AS userName\r\n" + 
			"FROM member_to_team mt\r\n" + 
			"LEFT JOIN master_organization AS mo ON mo.OrganizationId = mt.OrganizationId\r\n" + 
			"LEFT JOIN team t ON t.TeamId = mt.teamId\r\n" + 
			"LEFT JOIN e_services_be_user.a3m_account AS ep ON ep.id = mt.UserName WHERE mt.OrganizationId =?1",nativeQuery = true)
	List<Map<String, String>> findByOrganizationId(Long organizationId);

	Page<MembertoTeam> findByOrganizationId(Long organizationId, Pageable pageable);

}
