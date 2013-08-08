/**
 * 
 */
package com.liveclips.soccer.model;

import java.io.Serializable;

/**
 * @author mohitkumar
 *
 */
public class WeekItem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String weekName;

	/**
	 * @return the weekName
	 */
	public String getWeekName() {
		return weekName;
	}

	/**
	 * @param weekName the weekName to set
	 */
	public void setWeekName(String weekName) {
		this.weekName = weekName;
	}
	

}
