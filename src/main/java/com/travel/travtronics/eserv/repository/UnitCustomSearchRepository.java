package com.travel.travtronics.eserv.repository;

import java.util.List;

import com.travel.travtronics.request.SearchUnitDto;
import com.travel.travtronics.request.UnitSearchDto;
import com.travel.travtronics.response.UnitSearchResponse;
import com.travel.travtronics.response.UnitSearchResponseDto;

public interface UnitCustomSearchRepository {
	
	List<UnitSearchResponse> searchUnitShiftInfo(UnitSearchDto searchDto);

	List<UnitSearchResponseDto> searchUnitInfo(SearchUnitDto searchDto);
}
