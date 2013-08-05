package com.liveclips.soccer.database;

import java.util.ArrayList;
import java.util.List;

import android.R.string;
import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.liveclips.soccer.dto.LeagueTeamDto;
import com.liveclips.soccer.model.League;
import com.liveclips.soccer.model.TeamItem;

public class DatabaseHelper

{
	private static final String TAG = "DatabaseHelper";

	private SQLiteDatabase database;
	private Context context;

	@SuppressLint("SdCardPath")
	public DatabaseHelper(Context context) {

		Log.i(TAG, "database helper cons.. called");
		this.context = context;

	}

	private void close() {
		Log.i(TAG, "database helper close.. called");
		if (database != null) {
			database.close();
		}
	}

	// Getting All Teams
	public List<TeamItem> getAllTeams() {
		String selectQuery = "SELECT  * FROM  team";
		openDataBase();
		Cursor cursor = database.rawQuery(selectQuery, null);
		List<TeamItem> teamList = new ArrayList<TeamItem>();
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {

				TeamItem team = new TeamItem();
				team.setTeamId(cursor.getString(0));
				team.setTeamName(cursor.getString(1));
				team.setTeamAbbreviation(cursor.getString(2));
				team.setLeagueId(cursor.getString(3));

				// Adding contact to list
				teamList.add(team);
			} while (cursor.moveToNext());
		}
		cursor.close();
		close();

		// return contact list
		return teamList;
	}

	private void openDataBase() {
		database = SQLiteDatabase.openDatabase(
				"/data/data/" + context.getPackageName()
						+ "/databases/soccer_app.sqlite", null, 0);
	}

	/**
	 * get team info by id
	 * 
	 * @param teamId
	 * @return
	 */
	public TeamItem getTeamInfoByTeamId(String teamId) {
		Log.i(TAG, "get team Inforamtion by team_id");
		openDataBase();
		Cursor cursor = database.query("team", new String[] { "team_name",
				"team_abbreviation" }, "team_id" + "=?",
				new String[] { teamId }, null, null, null);
		TeamItem teamItem = new TeamItem();

		if (cursor != null) {
			if (cursor.moveToFirst()) {
				teamItem.setTeamName(cursor.getString(0));
				teamItem.setTeamAbbreviation(cursor.getString(1));
			}
		}

		cursor.close();
		close();
		return teamItem;
	}

	public LeagueTeamDto getLeagueWithTeams() {

		String MY_QUERY = "SELECT * FROM league leag INNER JOIN team t ON leag.league_id=t.league_id";
		openDataBase();
		Cursor cursor = database.rawQuery(MY_QUERY, null);

		LeagueTeamDto leagueTeamDto = new LeagueTeamDto();

		List<League> leagueList = new ArrayList<League>();

	//	List<TeamItem> teamList = new ArrayList<TeamItem>();

		String leagueName="undefined";
		boolean leagueChanged = false;
		
		League league =null;
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				if( (!leagueName.equals(cursor.getString(1))) || league==null){
					leagueName = cursor.getString(1);
										
					league = new League();
					league.setTeamList(new ArrayList<TeamItem>());
					league.setLeagueId(cursor.getString(0));
					league.setLeagueName(cursor.getString(1));
					league.setLeagueAbbreviation(cursor.getString(2));
					
					if(league!=null){
						leagueChanged=true;
					}
					
				}
				TeamItem team = new TeamItem();
				team.setTeamId(cursor.getString(3));
				team.setTeamName(cursor.getString(4));
				team.setTeamAbbreviation(cursor.getString(5));
				team.setLeagueId(cursor.getString(6));
				
				league.getTeamList().add(team);
				// Adding contact to list

				if(leagueChanged){
					leagueList.add(league);
					leagueChanged=false;
				}

				System.out.println(cursor.getString(0) + "*"
						+ cursor.getString(1) + "*" + cursor.getString(2) + "*"
						+ cursor.getString(3) + "*" + cursor.getString(4) + "*" + cursor.getString(5)+ "*" + cursor.getString(6));
			} while (cursor.moveToNext());
		}
		leagueTeamDto.setLeagueList(leagueList);
		
		System.out.println(leagueList.toString());
		System.out.println(leagueTeamDto.toString());
		
		cursor.close();
		close();
		return leagueTeamDto;
	}
}
