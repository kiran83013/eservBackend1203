package com.travel.travtronics.eserv.repository;

import java.util.List;

import com.travel.travtronics.request.SearchBusinessDto;
import com.travel.travtronics.response.CustomerSearchInfoResponse;

public interface CustomerSearchRepository {

//	List<CustomerInfoModel> businessSearch(String businessName, Integer type, Integer category, Integer vertical,
//			Integer industry, Integer custRegistrationNumber, Date startDate, Date endDate, Long organization, Long businessUnit, Long costCenter, Long location);

List<CustomerSearchInfoResponse> searchUnitInfo(SearchBusinessDto searchDto);

}
