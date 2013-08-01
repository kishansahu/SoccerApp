package com.liveclips.soccer.model;

public class TeamMenuItems {
	public int teamLogo;
	public String teamShortName;
	public boolean isUsersFavourite;
	public String teamId;
	
	
	public TeamMenuItems( int teamLogo,String teamShortName) {
		super();
		this.teamShortName = teamShortName;
		this.teamLogo = teamLogo;
	}
	
	public TeamMenuItems( int teamLogo,String teamShortName, boolean isUsersFavourite, String teamId) {
		super();
		this.teamShortName = teamShortName;
		this.teamLogo = teamLogo;
		this.isUsersFavourite = isUsersFavourite;
		this.teamId = teamId;
	}

}
