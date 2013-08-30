package com.liveclips.soccer.dto;

import java.util.ArrayList;
import java.util.List;

import com.liveclips.soccer.R;
import com.liveclips.soccer.model.PlayerItem;
import com.liveclips.soccer.model.ScheduleItem;
import com.liveclips.soccer.model.StatsItem;

/**
 * This Class Supplies the necessary Data to all the Classes.
 * 
 * @author qainfotech
 * 
 */
public class SoccerData {

	static List<PlayerItem> playerList;

	static PlayerItem playeritem;

	static String playerId[] = { "0", "1", "2", "3" };
	static String offensivePlayerNamesForTeam1[] = { "Messi", "Ronaldo",
			"David Villa", "Wayne Rooney" };
	static String offensivePlayerNumbersForTeam1[] = { "#10 | Midfielder",
			"#9 | Winger", "#24 | Forward", "#24 | Forward " };
	static String offensivePlayerData1ForTeam1[] = { "G 1", "G 2", "G 0", "G 0" };
	static String offensivePlayerData2ForTeam1[] = { "A 0", "A 5", "A 10",
			"A 10" };
	static String offensivePlayerData3ForTeam1[] = { "SG 1", "SG 2", "SG 5",
			"SG 5" };

	static String offensivPlayerImagesForTeam1[] = {
			"http://www.messi.com/images/headlines/messi-profile.jpg",
			"http://3.bp.blogspot.com/-eWrRNjbd5O0/T0tCMmG97cI/AAAAAAAAADo/NR03H4TPqk4/s1600/CutRonaldo.png",
			"http://findanewclub.co.uk/blog/wp-content/uploads/2012/06/David-Villa11.jpg",
			"http://static.guim.co.uk/sys-images/Media/Pix/pictures/2007/06/25/rooney128.jpg" };

	public static List<PlayerItem> getPlayersForAddition() {

		playerList = new ArrayList<PlayerItem>();
		int playerId[] = { 0, 1, 2, 3, 4, 5, 6, 7 };
		String playerNames[] = { "Messi", "Ronaldo", "David Villa",
				"David Villa" };
		String playerNumber[] = { "48", "26", "24", "88" };
		String playerPosition[] = { "LS", "DB", "CB", "TE" };

		PlayerItem item;

		for (int i = 0; i < playerNames.length; i++) {
			item = new PlayerItem();
			item.setPlayerId("" + playerId[i]);
			item.setPlayerName(playerNames[i]);
			item.setPlayerPosition(playerPosition[i]);
			item.setPlayerNumber(playerNumber[i]);
			playerList.add(item);
		}

		return playerList;
	}

	public static List<PlayerItem> getPlayers(String teamName, String teamType) {

		playerList = new ArrayList<PlayerItem>();

		if (teamName.equals("team1") && teamType.equals("offensive")) {

			for (int i = 0; i < offensivePlayerNamesForTeam1.length; i++) {
				playeritem = new PlayerItem();
				playeritem.setPlayerId(playerId[i]);
				playeritem.setPlayerdata1(offensivePlayerData1ForTeam1[i]);
				playeritem.setPlayerdata2(offensivePlayerData2ForTeam1[i]);
				playeritem.setPlayerdata3(offensivePlayerData3ForTeam1[i]);
				playeritem.setPlayerImage(offensivPlayerImagesForTeam1[i]);
				playeritem.setPlayerName(offensivePlayerNamesForTeam1[i]);
				playeritem.setplayerDetails(offensivePlayerNumbersForTeam1[i]);
				playerList.add(playeritem);
			}

		} else if (teamName.equals("team1") && teamType.equals("defensive")) {

			for (int i = offensivePlayerNamesForTeam1.length - 1; i >= 0; i--) {
				playeritem = new PlayerItem();
				playeritem.setPlayerId(playerId[i]);
				playeritem.setPlayerdata1(offensivePlayerData1ForTeam1[i]);
				playeritem.setPlayerdata2(offensivePlayerData2ForTeam1[i]);
				playeritem.setPlayerdata3(offensivePlayerData3ForTeam1[i]);
				playeritem.setPlayerImage(offensivPlayerImagesForTeam1[i]);
				playeritem.setPlayerName(offensivePlayerNamesForTeam1[i]);
				playeritem.setplayerDetails(offensivePlayerNumbersForTeam1[i]);
				playerList.add(playeritem);
			}
		}

		else if (teamName.equals("team2") && teamType.equals("offensive")) {

			for (int i = offensivePlayerNamesForTeam1.length - 1; i >= 0; i--) {
				playeritem = new PlayerItem();
				playeritem.setPlayerId(playerId[i]);
				playeritem.setPlayerdata1(offensivePlayerData1ForTeam1[i]);
				playeritem.setPlayerdata2(offensivePlayerData2ForTeam1[i]);
				playeritem.setPlayerdata3(offensivePlayerData3ForTeam1[i]);
				playeritem.setPlayerImage(offensivPlayerImagesForTeam1[i]);
				playeritem.setPlayerName(offensivePlayerNamesForTeam1[i]);
				playeritem.setplayerDetails(offensivePlayerNumbersForTeam1[i]);
				playerList.add(playeritem);
			}

		} else if (teamName.equals("team2") && teamType.equals("defensive")) {

			for (int i = 0; i < offensivePlayerNamesForTeam1.length; i++) {
				playeritem = new PlayerItem();
				playeritem.setPlayerId(playerId[i]);
				playeritem.setPlayerdata1(offensivePlayerData1ForTeam1[i]);
				playeritem.setPlayerdata2(offensivePlayerData2ForTeam1[i]);
				playeritem.setPlayerdata3(offensivePlayerData3ForTeam1[i]);
				playeritem.setPlayerImage(offensivPlayerImagesForTeam1[i]);
				playeritem.setPlayerName(offensivePlayerNamesForTeam1[i]);
				playeritem.setplayerDetails(offensivePlayerNumbersForTeam1[i]);
				playerList.add(playeritem);
			}

		}
		return playerList;
	}

	public static PlayerItem getPlayer(String id) {
		playeritem = new PlayerItem();
		int index = Integer.parseInt(id);
		playeritem.setPlayerId(playerId[index]);
		playeritem.setPlayerdata1(offensivePlayerData1ForTeam1[index]);
		playeritem.setPlayerdata2(offensivePlayerData2ForTeam1[index]);
		playeritem.setPlayerdata3(offensivePlayerData3ForTeam1[index]);
		playeritem.setPlayerImage(offensivPlayerImagesForTeam1[index]);
		playeritem.setPlayerName(offensivePlayerNamesForTeam1[index]);
		playeritem.setplayerDetails(offensivePlayerNumbersForTeam1[index]);
		return playeritem;

	}

	static List<ScheduleItem> scheduleList = new ArrayList<ScheduleItem>();

	static String[] teamNames = { "Nowrwich City", "Schalke", "Queens Park",
			"Reading", "Man Utd", "Fulham", "Montpellier", "Everton" };
	int[] teamLogo = { R.drawable.teamlogo_ars_id,
			R.drawable.teamlogo_ast_vil_id, R.drawable.teamlogo_car_cit_id,
			R.drawable.teamlogo_che_id, R.drawable.teamlogo_liv_id,
			R.drawable.teamlogo_man_cit_id, R.drawable.teamlogo_eve_id,
			R.drawable.teamlogo_hul_cit_id };
	static String[] matchDate = { "Aug 12", "Aug 13", "Aug 14", "Aug 15",
			"Aug 19", "Aug 18", "Aug 17", "Aug 16" };
	static String[] teamStatus = { "W 3-1", "L 3-1", "L 4-2", "W 1-0",
			"Live 2-1", "4:05 PM", "4:25 PM", "1:00 PM" };
	static String[] versusTexts = { "@", "vs", "vs", "@", "@", "@", "vs", "@" };
	static String[] teamIds = { "wf_id", "ns_id", "mh_id", "tc_id", "bb_id",
			"kj_id", "ks_id", "ss_id" };

	public static List<ScheduleItem> getSchedule() {
		for (int i = 0; i < 8; i++) {

			ScheduleItem scheduleItem = new ScheduleItem(teamNames[i],
					teamStatus[i], matchDate[i], versusTexts[i]);
			scheduleItem.setTeamId(teamIds[i]);
			scheduleList.add(scheduleItem);

		}
		return scheduleList;
	}

	static List<StatsItem> rowItemsForTeamStats = new ArrayList<StatsItem>();
	static List<StatsItem> rowItemsForKeyPlays = new ArrayList<StatsItem>();

	static String[] teamStatsType = { "Goals", "Shots on Goal", "Shots",
			"Saves", "Corner Kicks", "Penalty Kicks", "Fouls Committed",
			"Offsides", "Yellow Cards", "Red Cards" };
	static String[] teamStatsScore1 = { "2", "6", "14", "1", "6", "1", "15",
			"2", "1", "1" };
	static String[] teamStatsScore2 = { "0", "2", "7", "4", "1", "0", "15",
			"3", "2", "0" };

	static String[] keyPlaysStatstype = { "Crossovers", "Headers" };
	static String[] keyPlaysStatsScore1 = { "3", "5" };
	static String[] keyPlaysStatsScore2 = { "2", "3" };

	public static List<StatsItem> getTeamStats() {
		for (int i = 0; i < teamStatsType.length; i++) {
			StatsItem item = new StatsItem(teamStatsType[i],
					teamStatsScore1[i], teamStatsScore2[i],
					R.drawable.disclosure);

			rowItemsForTeamStats.add(item);
		}
		return rowItemsForTeamStats;
	}

	public static List<StatsItem> getKeyPlaysStats() {

		for (int i = 0; i < keyPlaysStatstype.length; i++) {
			StatsItem item = new StatsItem(keyPlaysStatstype[i],
					keyPlaysStatsScore1[i], keyPlaysStatsScore2[i],
					R.drawable.disclosure);

			rowItemsForKeyPlays.add(item);
		}
		return rowItemsForKeyPlays;
	}

}
