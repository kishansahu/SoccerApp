package com.liveclips.soccer.model;

public class GameSchedule {

	private String firstTeamName;
	private String secondTeamName;
	private int firstTeamImageId;
	private int secondTeamImageId;
	private int firstTeamScore;
	private int secondTeamScore;
	private boolean isGameInFuture;
	private String timeLeft;

	public int getFirstTeamImageId() {
		return firstTeamImageId;
	}

	public void setFirstTeamImageId(int firstTeamImageId) {
		this.firstTeamImageId = firstTeamImageId;
	}

	public int getSecondTeamImageId() {
		return secondTeamImageId;
	}

	public void setSecondTeamImageId(int secondTeamImageId) {
		this.secondTeamImageId = secondTeamImageId;
	}

	public int getFirstTeamScore() {
		return firstTeamScore;
	}

	public void setFirstTeamScore(int firstTeamScore) {
		this.firstTeamScore = firstTeamScore;
	}

	public int getSecondTeamScore() {
		return secondTeamScore;
	}

	public void setSecondTeamScore(int secondTeamScore) {
		this.secondTeamScore = secondTeamScore;
	}

	public String getTimeLeft() {
		return timeLeft;
	}

	public void setTimeLeft(String timeLeft) {
		this.timeLeft = timeLeft;
	}

	public String getFirstTeamName() {
		return firstTeamName;
	}

	public void setFirstTeamName(String firstTeamName) {
		this.firstTeamName = firstTeamName;
	}

	public String getSecondTeamName() {
		return secondTeamName;
	}

	public void setSecondTeamName(String secondTeamName) {
		this.secondTeamName = secondTeamName;
	}

	public boolean isGameInFuture() {
		return isGameInFuture;
	}

	public void setGameInFuture(boolean isGameInFuture) {
		this.isGameInFuture = isGameInFuture;
	}

}
