package com.liveclips.soccer.model;

import java.util.Comparator;

public class PlayerItem implements Comparable<PlayerItem> {
	public String playerdata1;
	public String playerdata2;
	public String playerdata3;
	public String playerdata4;
	public String playerName;
	public String playerDetails;
	public String playerGameDetails;
	public String playerImage;
	public String teamName;
	public int teamLogo;
	public String playerNumber;
	public String playerPosition;
	public int playerFavourite;
	public boolean isPLayerFavouriteActive;

	public String getPlayerGameDetails() {
		return playerGameDetails;
	}

	public void setPlayerGameDetails(String playerGameDetails) {
		this.playerGameDetails = playerGameDetails;
	}

	public boolean isPLayerFavouriteActive() {
		return isPLayerFavouriteActive;
	}

	public void setPLayerFavouriteActive(boolean isPLayerFavouriteActive) {
		this.isPLayerFavouriteActive = isPLayerFavouriteActive;
	}

	public String getPlayerNumber() {
		return playerNumber;
	}

	public void setPlayerNumber(String playerNumber) {
		this.playerNumber = playerNumber;
	}

	public String getPlayerPosition() {
		return playerPosition;
	}

	public void setPlayerPosition(String playerPosition) {
		this.playerPosition = playerPosition;
	}

	public int getPlayerFavourite() {
		return playerFavourite;
	}

	public void setPlayerFavourite(int playerFavourite) {
		this.playerFavourite = playerFavourite;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String getplayerDetails() {
		return playerDetails;
	}

	public void setplayerDetails(String playerDetails) {
		this.playerDetails = playerDetails;
	}

	public String getPlayerdata1() {
		return playerdata1;
	}

	public void setPlayerdata1(String playerdata1) {
		this.playerdata1 = playerdata1;
	}

	public String getPlayerdata2() {
		return playerdata2;
	}

	public void setPlayerdata2(String playerdata2) {
		this.playerdata2 = playerdata2;
	}

	public String getPlayerdata3() {
		return playerdata3;
	}

	public void setPlayerdata3(String playerdata3) {
		this.playerdata3 = playerdata3;
	}

	public String getPlayerdata4() {
		return playerdata4;
	}

	public void setPlayerdata4(String playerdata4) {
		this.playerdata4 = playerdata4;
	}

	public String getPlayerDetails() {
		return playerDetails;
	}

	public void setPlayerDetails(String playerDetails) {
		this.playerDetails = playerDetails;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public int getTeamLogo() {
		return teamLogo;
	}

	public void setTeamLogo(int teamLogo) {
		this.teamLogo = teamLogo;
	}

	public PlayerItem() {

	}

	public PlayerItem(String playerName, String playerDetails,
			String playerImage, String playerdata1, String playerdata2,
			String playerdata3, String playerdata4) {
		super();
		this.playerName = playerName;
		this.playerDetails = playerDetails;
		this.playerImage = playerImage;
		this.playerdata1 = playerdata1;
		this.playerdata2 = playerdata2;
		this.playerdata3 = playerdata3;
		this.playerdata4 = playerdata4;
	}

	public String getPlayerImage() {
		return playerImage;
	}

	public void setPlayerImage(String playerImage) {
		this.playerImage = playerImage;
	}

	public PlayerItem(int playerFavourite, String playerName,
			String playerNumber, String playerPosition,
			boolean isPlayerFavouriteActive) {
		super();
		this.playerFavourite = playerFavourite;
		this.playerPosition = playerPosition;
		this.playerName = playerName;
		this.playerNumber = playerNumber;
		this.isPLayerFavouriteActive = isPlayerFavouriteActive;
	}

	public PlayerItem(String teamName, int teamLogo) {
		super();
		this.teamName = teamName;
		this.teamLogo = teamLogo;

	}

	/*
	 * Comparator implementation to Player Item object based on playerName
	 */
	public static class playerNameInAscendingOrder implements
			Comparator<PlayerItem> {

		@Override
		public int compare(PlayerItem o1, PlayerItem o2) {
			return o1.playerName.compareTo(o2.playerName);
		}
	}

	/*
	 * Comparator implementation to Player Item object based on playerName
	 */
	public static class playerNameInDescendingOrder implements
			Comparator<PlayerItem> {

		@Override
		public int compare(PlayerItem o1, PlayerItem o2) {
			return o2.playerName.compareTo(o1.playerName);
		}
	}

	/*
	 * Comparator implementation to Player Item object based on playerPosition
	 */
	public static class playerPosInAscendingOrder implements
			Comparator<PlayerItem> {

		@Override
		public int compare(PlayerItem o1, PlayerItem o2) {
			return o1.playerPosition.compareTo(o2.playerPosition);
		}
	}

	/*
	 * Comparator implementation to Player Item object based on playerPosition
	 */
	public static class playerPosInDescendingOrder implements
			Comparator<PlayerItem> {

		@Override
		public int compare(PlayerItem o1, PlayerItem o2) {
			return o2.playerPosition.compareTo(o1.playerPosition);
		}
	}

	/*
	 * Comparator implementation to Player Item object based on playerNum
	 */
	public static class playerNumInAscendingOrder implements
			Comparator<PlayerItem> {

		@Override
		public int compare(PlayerItem o1, PlayerItem o2) {
			int num1 = (Integer.parseInt(o1.playerNumber));
			int num2 = (Integer.parseInt(o2.playerNumber));

			return num1 > num2 ? 1 : (num1 < num2 ? -1 : 0);
		}
	}

	/*
	 * Comparator implementation to Player Item object based on playerNum
	 */
	public static class playerNumInDescendingOrder implements
			Comparator<PlayerItem> {

		@Override
		public int compare(PlayerItem o1, PlayerItem o2) {
			int num1 = (Integer.parseInt(o1.playerNumber));
			int num2 = (Integer.parseInt(o2.playerNumber));

			return num2 > num1 ? 1 : (num2 < num1 ? -1 : 0);
		}
	}

	/*
	 * Comparator implementation to Player Item object based on playerFav
	 */
	public static class playerFavInAscendingOrder implements
			Comparator<PlayerItem> {

		@Override
		public int compare(PlayerItem o1, PlayerItem o2) {
			int num1 = o1.isPLayerFavouriteActive ? 1 : 0;
			int num2 = o2.isPLayerFavouriteActive ? 1 : 0;

			return num1 > num2 ? 1 : (num1 < num2 ? -1 : 0);
		}
	}

	/*
	 * Comparator implementation to Player Item object based on playerFav
	 */
	public static class playerFavInDescendingOrder implements
			Comparator<PlayerItem> {

		@Override
		public int compare(PlayerItem o1, PlayerItem o2) {
			int num1 = o1.isPLayerFavouriteActive ? 1 : 0;
			int num2 = o2.isPLayerFavouriteActive ? 1 : 0;

			return num2 > num1 ? 1 : (num2 < num1 ? -1 : 0);
		}
	}

	@Override
	public int compareTo(PlayerItem another) {
		// TODO Auto-generated method stub
		return 0;
	}

}
