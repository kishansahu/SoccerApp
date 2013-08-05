/**
 * 
 */
package com.liveclips.soccer.model;

import java.io.Serializable;

public class TeamItem implements Serializable {
	private static final long serialVersionUID = 1L;
	private String teamId;
	private String teamName;
	private String teamAbbreviation;
	private String leagueId;

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getTeamAbbreviation() {
		return teamAbbreviation;
	}

	public void setTeamAbbreviation(String teamAbbreviation) {
		this.teamAbbreviation = teamAbbreviation;
	}

	public String getLeagueId() {
		return leagueId;
	}

	public void setLeagueId(String leagueId) {
		this.leagueId = leagueId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
