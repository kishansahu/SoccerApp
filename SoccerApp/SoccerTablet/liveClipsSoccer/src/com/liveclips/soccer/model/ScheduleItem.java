package com.liveclips.soccer.model;

public class ScheduleItem {

	private String teamName;
	private int teamLogo;
	private String teamStatus;
	private String weekText;
	private String versusText;
	private String teamId;

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

	public ScheduleItem(String teamName,String teamStatus,
			String weekText, String versusText) {
		//super();
		this.teamName = teamName;
		/*this.teamLogo = teamLogo;*/
		this.teamStatus = teamStatus;
		this.weekText = weekText;
		this.versusText = versusText;
	}

	public int getTeamLogo() {
		return teamLogo;
	}

	public void setTeamLogo(int teamLogo) {
		this.teamLogo = teamLogo;
	}

	public String getTeamStatus() {
		return teamStatus;
	}

	public void setTeamStatus(String teamStatus) {
		this.teamStatus = teamStatus;
	}

	public String getWeekText() {
		return weekText;
	}

	public void setWeekText(String weekText) {
		this.weekText = weekText;
	}

	public String getVersusText() {
		return versusText;
	}

	public void setVersusText(String versusText) {
		this.versusText = versusText;
	}

}
