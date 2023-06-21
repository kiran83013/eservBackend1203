package com.travel.travtronics.response;

import java.util.List;

import com.travel.travtronics.eserv.model.SlotHeaderModel;
import com.travel.travtronics.eserv.model.SlotLinesModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SlotRequestResponseModel {

	private SlotHeaderModel slotHeaderModel;

	private List<SlotLinesModel> slotLineModels;

}
