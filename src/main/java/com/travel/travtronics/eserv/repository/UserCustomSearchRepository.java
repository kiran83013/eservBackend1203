package com.travel.travtronics.eserv.repository;

import java.util.List;

import com.travel.travtronics.request.SearchUserDto;
import com.travel.travtronics.response.UserSearchResponse;

public interface UserCustomSearchRepository {

	List<UserSearchResponse> searchUserShiftInfo(SearchUserDto search);

}
