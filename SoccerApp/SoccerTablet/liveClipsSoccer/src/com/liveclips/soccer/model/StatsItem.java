package com.liveclips.soccer.model;

public class StatsItem {

	private String statType;
	private String statScore1;
	private String statScore2;
	private int disclosureImage;
	

	public StatsItem(String statType, String statScore1, String statScore2,
			int disclosureImage) {
		super();
		this.statType = statType;
		this.statScore1 = statScore1;
		this.statScore2 = statScore2;
		this.disclosureImage = disclosureImage;
	}
	public String getStatType() {
		return statType;
	}
	public void setStatType(String statType) {
		this.statType = statType;
	}
	public String getStatScore1() {
		return statScore1;
	}
	public void setStatScore1(String statScore1) {
		this.statScore1 = statScore1;
	}
	public String getStatScore2() {
		return statScore2;
	}
	public void setStatScore2(String statScore2) {
		this.statScore2 = statScore2;
	}
	public int getDisclosureImage() {
		return disclosureImage;
	}
	public void setDisclosureImage(int disclosureImage) {
		this.disclosureImage = disclosureImage;
	}
	
	
}
