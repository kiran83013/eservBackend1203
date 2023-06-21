package com.travel.travtronics.converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class StringConverter implements AttributeConverter<List<String>, String> {

	@Override
	public String convertToDatabaseColumn(List<String> list) {

		return String.join(",", list);

	}

	@Override
	public List<String> convertToEntityAttribute(String joined) {
		return joined != null ? new ArrayList<>(Arrays.asList(joined.split(","))) : Collections.emptyList();
	}

}
