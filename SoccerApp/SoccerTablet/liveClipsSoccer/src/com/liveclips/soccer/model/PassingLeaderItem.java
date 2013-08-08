/**
 * 
 */
package com.liveclips.soccer.model;

import java.io.Serializable;

/**
 * @author mohitkumar
 *
 */
public class PassingLeaderItem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String leaderName;
	
	private TeamItem leaderTeam;
	
	private int yards;

	/**
	 * @return the leaderName
	 */
	public String getLeaderName() {
		return leaderName;
	}

	/**
	 * @param leaderName the leaderName to set
	 */
	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}

	/**
	 * @return the leaderTeam
	 */
	public TeamItem getLeaderTeam() {
		return leaderTeam;
	}

	/**
	 * @param leaderTeam the leaderTeam to set
	 */
	public void setLeaderTeam(TeamItem leaderTeam) {
		this.leaderTeam = leaderTeam;
	}

	/**
	 * @return the yards
	 */
	public int getYards() {
		return yards;
	}

	/**
	 * @param yards the yards to set
	 */
	public void setYards(int yards) {
		this.yards = yards;
	}

}
