/**
 * 
 */
package com.liveclips.soccer.model;

import java.io.Serializable;

/**
 * @author mohitkumar
 *
 */
public class LeadersTypeItem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String leadersTypeName;
	
	private int leadersType;
	
	private int disclosureImage;

	/**
	 * @return the disclosureImage
	 */
	public int getDisclosureImage() {
		return disclosureImage;
	}

	/**
	 * @param disclosureImage the disclosureImage to set
	 */
	public void setDisclosureImage(int disclosureImage) {
		this.disclosureImage = disclosureImage;
	}

	/**
	 * @return the leadersTypeName
	 */
	public String getLeadersTypeName() {
		return leadersTypeName;
	}

	/**
	 * @param leadersTypeName the leadersTypeName to set
	 */
	public void setLeadersTypeName(String leadersTypeName) {
		this.leadersTypeName = leadersTypeName;
	}

	/**
	 * @return the leadersType
	 */
	public int getLeadersType() {
		return leadersType;
	}

	/**
	 * @param leadersType the leadersType to set
	 */
	public void setLeadersType(int leadersType) {
		this.leadersType = leadersType;
	}

}
