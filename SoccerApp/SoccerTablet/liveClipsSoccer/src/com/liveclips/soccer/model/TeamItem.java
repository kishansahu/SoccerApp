/**
 * 
 */
package com.liveclips.soccer.model;

import java.io.Serializable;

import android.graphics.drawable.Drawable;

/**
 * @author mohitkumar
 * 
 */
public class TeamItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String teamId;
	private String teamName;
	private String teamAbbreviation;
	private int teamConfigAutoId;
	private int teamLogo;
	private int wins;
	private int losses;
	private String leagueId;
	private boolean isUserFavourite;
	
	

	public String getLeagueId() {
		return leagueId;
	}

	public void setLeagueId(String leagueId) {
		this.leagueId = leagueId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public String getTeamAbbreviation() {
		return teamAbbreviation;
	}

	public void setTeamAbbreviation(String teamAbbreviation) {
		this.teamAbbreviation = teamAbbreviation;
	}

	public int getTeamConfigAutoId() {
		return teamConfigAutoId;
	}

	public void setTeamConfigAutoId(int teamConfigAutoId) {
		this.teamConfigAutoId = teamConfigAutoId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the wins
	 */
	public int getWins() {
		return wins;
	}

	/**
	 * @param wins
	 *            the wins to set
	 */
	public void setWins(int wins) {
		this.wins = wins;
	}

	/**
	 * @return the losses
	 */
	public int getLosses() {
		return losses;
	}

	/**
	 * @param losses
	 *            the losses to set
	 */
	public void setLosses(int losses) {
		this.losses = losses;
	}

	/**
	 * @return the teamName
	 */
	public String getTeamName() {
		return teamName;
	}

	/**
	 * @param teamName
	 *            the teamName to set
	 */
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	/**
	 * @return the teamLogo
	 */
	public int getTeamLogo() {
		return teamLogo;
	}

	/**
	 * @param teamLogo
	 *            the teamLogo to set
	 */
	public void setTeamLogo(int teamLogo) {
		this.teamLogo = teamLogo;
	}

	public boolean isUserFavourite() {
		return isUserFavourite;
	}

	public void setUserFavourite(boolean isUserFavourite) {
		this.isUserFavourite = isUserFavourite;
	}

}
