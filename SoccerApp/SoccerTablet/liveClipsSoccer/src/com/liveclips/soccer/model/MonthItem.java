/**
 * 
 */
package com.liveclips.soccer.model;

import java.io.Serializable;

/**
 * @author mohitkumar
 *
 */
public class MonthItem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String monthName;

	/**
	 * @return the weekName
	 */
	public String getWeekName() {
		return monthName;
	}

	/**
	 * @param weekName the weekName to set
	 */
	public void setWeekName(String weekName) {
		this.monthName = weekName;
	}
	

}
