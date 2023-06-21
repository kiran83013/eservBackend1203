package com.travel.travtronics.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.travtronics.eserv.model.PriceType;
import com.travel.travtronics.eserv.model.PriceTypeItems;
import com.travel.travtronics.eserv.repository.PriceTypeItemsRepository;
import com.travel.travtronics.eserv.repository.PricingTypeRepository;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.MessageStatusResponse;

@Service
public class PricingService {

	@Autowired
	PricingTypeRepository pricingTypeRepository;

	@Autowired
	PriceTypeItemsRepository priceTypeItemsRepository;

	public MessageStatusResponse createModifyPricingType(PriceType request) {

		if (pricingTypeRepository.existsById(request.getId())) {
			pricingTypeRepository.save(request);
			return new MessageStatusResponse(200, "OK");

		} else {

			pricingTypeRepository.save(request);
			return new MessageStatusResponse(201, "CREATED");
		}
	}

	public APIResponse getPricingType(Long id) throws Exception {

		PriceType Data = pricingTypeRepository.findById(id).orElseThrow(() -> new Exception("Data Not Found"));

		return new APIResponse(200, "OK", List.of(Data));
	}

	public APIResponse getPricingTypes() {

		return new APIResponse(200, "OK", pricingTypeRepository.findAllByStatusTrue());
	}

	public MessageStatusResponse createModifyPricingTypeItems(PriceTypeItems request) {
		if (priceTypeItemsRepository.existsById(request.getId())) {
			priceTypeItemsRepository.save(request);
			return new MessageStatusResponse(200, "OK");

		} else {

			priceTypeItemsRepository.save(request);
			return new MessageStatusResponse(201, "CREATED");
		}
	}

	public APIResponse getPricingTypeItems(Long id) throws Exception {
		PriceTypeItems Data = priceTypeItemsRepository.findById(id).orElseThrow(() -> new Exception("Data Not Found"));

		return new APIResponse(200, "OK", List.of(Data));
	}

	public APIResponse getPricingTypeItems() {
		return new APIResponse(200, "OK", priceTypeItemsRepository.findAllByStatusTrue());
	}

}
