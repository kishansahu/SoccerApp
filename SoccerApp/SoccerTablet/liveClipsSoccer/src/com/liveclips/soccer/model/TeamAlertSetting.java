package com.liveclips.soccer.model;

import java.io.Serializable;

public class TeamAlertSetting implements Serializable {

	private static final long serialVersionUID = 1L;
	private String teamId;
	private boolean allPlays;
	private boolean topPlays;
	private boolean scoringPlays;
	private boolean turnOversPlays;
	private boolean redZonePlays;
	private boolean playsOfGame;
	private int basicAlert;
	private int passingPlays;
	private int rushingPlays;

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public boolean isAllPlays() {
		return allPlays;
	}

	public void setAllPlays(boolean allPlays) {
		this.allPlays = allPlays;
	}

	public boolean isTopPlays() {
		return topPlays;
	}

	public void setTopPlays(boolean topPlays) {
		this.topPlays = topPlays;
	}

	public boolean isScoringPlays() {
		return scoringPlays;
	}

	public void setScoringPlays(boolean scoringPlays) {
		this.scoringPlays = scoringPlays;
	}

	public boolean isTurnOversPlays() {
		return turnOversPlays;
	}

	public void setTurnOversPlays(boolean turnOversPlays) {
		this.turnOversPlays = turnOversPlays;
	}

	public boolean isRedZonePlays() {
		return redZonePlays;
	}

	public void setRedZonePlays(boolean redZonePlays) {
		this.redZonePlays = redZonePlays;
	}

	public boolean isPlaysOfGame() {
		return playsOfGame;
	}

	public void setPlaysOfGame(boolean playsOfGame) {
		this.playsOfGame = playsOfGame;
	}

	public int getBasicAlert() {
		return basicAlert;
	}

	public void setBasicAlert(int basicAlert) {
		this.basicAlert = basicAlert;
	}

	public int getPassingPlays() {
		return passingPlays;
	}

	public void setPassingPlays(int passingPlays) {
		this.passingPlays = passingPlays;
	}

	public int getRushingPlays() {
		return rushingPlays;
	}

	public void setRushingPlays(int rushingPlays) {
		this.rushingPlays = rushingPlays;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
