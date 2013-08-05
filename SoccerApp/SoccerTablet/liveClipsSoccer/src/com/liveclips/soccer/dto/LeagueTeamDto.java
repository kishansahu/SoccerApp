package com.liveclips.soccer.dto;

import java.util.List;

import com.liveclips.soccer.model.League;

public class LeagueTeamDto {

	List<League> leagueList;

	@Override
	public String toString() {
		return "LeagueTeamDto [leagueList=" + leagueList + "]";
	}

	public List<League> getLeagueList() {
		return leagueList;
	}

	public void setLeagueList(List<League> leagueList) {
		this.leagueList = leagueList;
	}
	
}
