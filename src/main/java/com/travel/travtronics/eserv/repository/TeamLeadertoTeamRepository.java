package com.travel.travtronics.eserv.repository;

import java.util.List;
import java.util.Map;
//import java.util.List;
//import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import com.travel.travtronics.eserv.model.TeamLeadertoTeam;

public interface TeamLeadertoTeamRepository extends JpaRepository<TeamLeadertoTeam, Long> {

	Optional<TeamLeadertoTeam> findBytId(Long tId);

	@Query(value = "SELECT tl.TId AS tId,tl.CreatedBy AS createdBy,tl.CreatedDate AS createdDate,tl.EndDate AS endDate,tl.StartDate AS startDate,\r\n" + 
			"tl.Status AS status, tl.UpDatedBy AS upDatedBy,tl.UpDatedDate AS updatedDate,m.ModuleName AS moduleName,tl.Module AS module,\r\n" + 
			"mo.name AS organizationName,tl.Organization AS organization,tl.Team AS team, t.TeamName AS teamName,\r\n" + 
			"ep.username AS teamLeaderName,tl.TeamLeader AS teamLeader\r\n" + 
			"FROM team_leader_to_team tl\r\n" + 
			"LEFT JOIN module m ON m.ModuleId = tl.Module\r\n" + 
			"LEFT JOIN master_organization mo ON mo.OrganizationId = tl.Organization\r\n" + 
			"LEFT JOIN team t ON t.TeamId = tl.Team\r\n" + 
			"LEFT JOIN e_services_be_user.a3m_account AS ep ON ep.id = tl.teamLeader\r\n" + 
			"WHERE organization =?1", nativeQuery = true)
	List<Map<String, String>> findByList(String organization);

	/*@Query("select tltm.teamLeader from TeamLeadertoTeam tltm where tltm.team= :team")
	Optional<String> getTeamLeaderId(@Param("team") String team);*/
	@Query(value = "SELECT tltm.TeamLeader FROM team_leader_to_team tltm WHERE tltm.team=?1 AND tltm.organization =?2 AND tltm.module =?3 AND tltm.Status='Active'", nativeQuery = true)
	Optional<String> getTeamLeaderId(String team, String organization, String module);
	
	Page<TeamLeadertoTeam> findByOrganization(String organization, Pageable pageable);

}
