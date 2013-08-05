/**
 * 
 */
package com.liveclips.soccer.model;

import java.io.Serializable;
import java.util.List;

public class League implements Serializable {
	private static final long serialVersionUID = 1L;
	private String leagueId;
	private String leagueName;
	private String leagueAbbreviation;
	private List<TeamItem> teamList;
	
	public String getLeagueId() {
		return leagueId;
	}
	public void setLeagueId(String leagueId) {
		this.leagueId = leagueId;
	}
	public String getLeagueName() {
		return leagueName;
	}
	public void setLeagueName(String leagueName) {
		this.leagueName = leagueName;
	}
	public String getLeagueAbbreviation() {
		return leagueAbbreviation;
	}
	public void setLeagueAbbreviation(String leagueAbbreviation) {
		this.leagueAbbreviation = leagueAbbreviation;
	}
	public List<TeamItem> getTeamList() {
		return teamList;
	}
	public void setTeamList(List<TeamItem> teamList) {
		this.teamList = teamList;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
