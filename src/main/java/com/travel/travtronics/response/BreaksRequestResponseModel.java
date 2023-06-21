package com.travel.travtronics.response;

import java.util.List;

import com.travel.travtronics.eserv.model.BreaksHeaderModel;
import com.travel.travtronics.eserv.model.BreaksLinesModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BreaksRequestResponseModel {

	private BreaksHeaderModel breaksHeaderModel;

	private List<BreaksLinesModel> breaksLinesModels;

}
