package com.travel.travtronics.eserv.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.Tuple;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.travel.travtronics.eserv.model.LetterTemplateLines;
import com.travel.travtronics.util.EnumStatus;

public interface LetterTemplateLinesRepository extends JpaRepository<LetterTemplateLines, Long> {

	@Query(value = "SELECT s.Letter_Line_Id AS letterLineId, s.Letter_Id AS letterId,s.Organization_Id AS organizationId,  s.Created_By AS createdby,mo.Name AS organizationName,s.Status AS status,\r\n"
			+ "s.Created_Date AS createdDate, s.Updated_By AS updatedBy, s.Updated_Date AS updatedDate, s.Language_Id AS languageId, s.Html_Editor AS htmlEditor,lth.letter_Name AS letterName,ml.Name AS languageName,"
			+ "s.line_images as images\r\n"
			+ "FROM bpf_letter_template_lines s\r\n"
			+ "LEFT JOIN bpf_letter_template_header lth  ON lth.letter_Id = s.Letter_Id\r\n"
			+ "LEFT JOIN master_language ml ON ml.ID = s.Language_Id\r\n"
			+ "LEFT JOIN master_organization mo ON mo.OrganizationId = s.Organization_Id\r\n"
			+ "WHERE s.letter_Id =?1 AND s.Status='Active' ", nativeQuery = true)
	List<Tuple> findAllByLetterId(Long letterId);

	Long countByLetterIdAndLanguageId(Long letterId, Long languageId);

	Optional<LetterTemplateLines> findByLetterIdAndLanguageIdAndStatus(Long letterId, Long languageId, EnumStatus status);

}
