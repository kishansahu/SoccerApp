package com.liveclips.soccer.database;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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
				team.setId(Integer.parseInt(cursor.getString(0)));
				team.setTeamId(cursor.getString(1));
				team.setTeamName(cursor.getString(2));
				team.setTeamAbbreviation(cursor.getString(3));
				team.setTeamConfigAutoId(cursor.getInt(4));
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
						+ "/databases/college_app.db", null, 0);
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

	public void abc() {
		
		String MY_QUERY = "SELECT * FROM conference conf INNER JOIN team t ON conf.id=t.team_conf_auto_id";
		openDataBase();
		Cursor cursor = database.rawQuery(MY_QUERY, null);
	//	List<TeamItem> teamList = new ArrayList<TeamItem>();
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
		/*//		TeamItem team = new TeamItem();
				team.setId(Integer.parseInt(cursor.getString(0)));
				team.setTeamId(cursor.getString(1));
				team.setTeamName(cursor.getString(2));
				team.setTeamAbbreviation(cursor.getString(3));
				team.setTeamConfigAutoId(cursor.getInt(4));
				// Adding contact to list
				teamList.add(team);*/
				System.out.println(cursor.getString(0) + "*" + cursor.getString(1) + "*" + cursor.getString(2)+"*" + cursor.getString(3) + "*" + cursor.getString(4)+
						"#" + cursor.getString(5) +"#" + cursor.getString(6) +"#" + cursor.getString(7) +"#" + cursor.getString(8) +"#" + cursor.getString(9));
			} while (cursor.moveToNext());
		}
		cursor.close();
		close();

	}

}
