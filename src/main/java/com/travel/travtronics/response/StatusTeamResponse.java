package com.travel.travtronics.response;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StatusTeamResponse {

	@JsonProperty("team")
	public ArrayList<Team> team;
	@JsonProperty("users")
	public ArrayList<User> users;
	@JsonProperty("status")
	public Status status;

	@Getter
	public static class Status {

		@JsonProperty("createdBy")
		public int createdBy;
		@JsonProperty("updatedBy")
		public int updatedBy;
		@JsonProperty("createdDate")
		public Object createdDate;
		@JsonProperty("updatedDate")
		public Date updatedDate;
		@JsonProperty("statusId")
		public int statusId;
		@JsonProperty("description")
		public String description;
		@JsonProperty("module")
		public String module;
		@JsonProperty("name")
		public String name;
		@JsonProperty("organization")
		public String organization;
		@JsonProperty("status")
		public String status;
	}

	@Getter
	public static class Team {
		public int teamId;
		public String teamName;
	}

	@Getter
	public static class User {
		public int id;
		public String team;
		public String teamName;
		public int userId;
		public Boolean isTeamLeader;
		public String userName;
	}

}
