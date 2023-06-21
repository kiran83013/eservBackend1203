package com.travel.travtronics.eserv.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.data.annotation.Immutable;

import com.travel.travtronics.converter.StringConverter;

import lombok.Getter;

@Immutable
@Entity
@Getter
public class SanctionSetUpEntity {

	@Id
	@Column(name = "id", insertable = false, updatable = false)
	private Long id;

	@Column(name = "view_name", insertable = false, updatable = false)
	private String viewName;

	@Convert(converter = StringConverter.class)
	@Column(name = "view_names_content", insertable = false, updatable = false)
	private List<String> viewContent;

}
