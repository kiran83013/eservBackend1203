package com.travel.travtronics.converter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.apache.commons.lang3.StringUtils;

@Converter
public class IntegerListConverter implements AttributeConverter<List<Integer>, String> {

	@Override
	public String convertToDatabaseColumn(List<Integer> list) {
		return list == null ? null : StringUtils.join(list, ",");
	}

	@Override
	public List<Integer> convertToEntityAttribute(String dbData) {
		if (StringUtils.isBlank(dbData))
			return Collections.emptyList();
		try (Stream<String> stream = Arrays.stream(dbData.split(","))) {
			return stream.map(Integer::parseInt).collect(Collectors.toList());
		}
	}

}
