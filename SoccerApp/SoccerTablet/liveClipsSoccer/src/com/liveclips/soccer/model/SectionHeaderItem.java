/**
 * 
 */
package com.liveclips.soccer.model;

import java.io.Serializable;

/**
 * @author mohitkumar
 *
 */
public class SectionHeaderItem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String headingText1;
	
	private String headingText2;
	
	private String headingText3;

	/**
	 * @return the headingText1
	 */
	public String getHeadingText1() {
		return headingText1;
	}

	/**
	 * @param headingText1 the headingText1 to set
	 */
	public void setHeadingText1(String headingText1) {
		this.headingText1 = headingText1;
	}

	/**
	 * @return the headingText2
	 */
	public String getHeadingText2() {
		return headingText2;
	}

	/**
	 * @param headingText2 the headingText2 to set
	 */
	public void setHeadingText2(String headingText2) {
		this.headingText2 = headingText2;
	}

	/**
	 * @return the headingText3
	 */
	public String getHeadingText3() {
		return headingText3;
	}

	/**
	 * @param headingText3 the headingText3 to set
	 */
	public void setHeadingText3(String headingText3) {
		this.headingText3 = headingText3;
	}

}
