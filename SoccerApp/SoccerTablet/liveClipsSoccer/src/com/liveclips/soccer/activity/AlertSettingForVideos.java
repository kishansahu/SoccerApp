package com.liveclips.soccer.activity;

import java.util.Collections;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.google.gson.Gson;
import com.liveclips.soccer.R;
import com.liveclips.soccer.model.TeamAlertSetting;
import com.liveclips.soccer.utils.NflUtils;
import com.liveclips.soccer.utils.SharedPreferencesUtil;

public class AlertSettingForVideos extends Activity {
	Button openSelectedTeamPlayButton, backToAvailableTeamListButton;
	Context context;
	Activity activity;
	String previousTeamId, presentFavouriteTeamId, nextTeamId, json;
	SharedPreferences appSharedPrefs;
	Editor prefsEditor;
	Gson gson;
	List<String> favTeamList;
	ToggleButton alertsForAllteams;
	TextView teamNameGameAlerts;
	TeamAlertSetting teamAlertSetting;
	ActionBar actionBar;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_settings_view_alerts);
		
		actionBar = getActionBar();
		actionBar.hide();

		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			previousTeamId = extras.getString("previousTeamId");
			presentFavouriteTeamId = extras.getString("presentFavouriteTeamId");
		}

		context = this;
		activity = this;
		appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(this
				.getApplicationContext());

		favTeamList = SharedPreferencesUtil
				.getFavouriteInSharedPreferencesList(context, "team");
		Collections.sort(favTeamList);
		int favTeamIndex = 0;
		String favouriteTeamId = favTeamList.get(0);

		gson = new Gson();
		prefsEditor = appSharedPrefs.edit();
		if (!presentFavouriteTeamId.isEmpty()) {
			favouriteTeamId = presentFavouriteTeamId;
		}
		String teamAlertSettingFromSharedprefJson = appSharedPrefs.getString(
				"teamAlertSettingObject" + favouriteTeamId, "");

		if (!teamAlertSettingFromSharedprefJson.isEmpty()) {
			teamAlertSetting = gson.fromJson(
					teamAlertSettingFromSharedprefJson, TeamAlertSetting.class);
		} else {
			teamAlertSetting = new TeamAlertSetting();
			teamAlertSetting.setTeamId(favouriteTeamId);
		}

		presentFavouriteTeamId = favouriteTeamId;
		teamAlertSetting = NflUtils.customizeAlertSettingForTeam(activity,
				teamAlertSetting);

		favTeamIndex = favTeamList.indexOf(favouriteTeamId);
		if ((favTeamIndex) != 0) {
			previousTeamId = favTeamList.get(favTeamIndex - 1);
		}
		if ((favTeamList.size() - 1) != favTeamIndex) {
			nextTeamId = favTeamList.get(favTeamIndex + 1);
		}

		teamNameGameAlerts = (TextView) findViewById(R.id.teamNameGameAlerts);
		teamNameGameAlerts.setText(presentFavouriteTeamId + " Game Alerts");
		openSelectedTeamPlayButton = (Button) findViewById(R.id.openSelectedTeamPlayButton);
		backToAvailableTeamListButton = (Button) findViewById(R.id.backToAvailableTeamListButton);
		alertsForAllteams = (ToggleButton) findViewById(R.id.alerts_for_allteams_toggle_button);
		// backToAvailableTeamListButton.setText("<<Teams");
		openSelectedTeamPlayButton
				.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						json = gson.toJson(teamAlertSetting);
						prefsEditor.putString("teamAlertSettingObject"
								+ presentFavouriteTeamId, json);
						prefsEditor.commit();
						if (alertsForAllteams.isChecked()) {
							for (String favTeamId : favTeamList) {
								TeamAlertSetting singleTeamAlertSetting = new TeamAlertSetting();
								singleTeamAlertSetting = teamAlertSetting;
								singleTeamAlertSetting.setTeamId(favTeamId);
								json = gson.toJson(singleTeamAlertSetting);
								prefsEditor.putString("teamAlertSettingObject"
										+ favTeamId, json);
								prefsEditor.commit();
							}
							nextTeamId = "";
						}
						if (nextTeamId != null && !nextTeamId.isEmpty()) {
							Intent intent = new Intent(
									AlertSettingForVideos.this,
									AlertSettingForVideos.class);
							intent.putExtra("previousTeamId",
									presentFavouriteTeamId);
							intent.putExtra("presentFavouriteTeamId",
									nextTeamId);
							AlertSettingForVideos.this.startActivity(intent);
						} else {

							
							  AlertSettingForVideos.this.startActivity(new
							  Intent(AlertSettingForVideos.this,
							  GameActivity.class));
							 
						}

					}
				});

		backToAvailableTeamListButton
				.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {

						if (!previousTeamId.isEmpty()) {
							Intent intent = new Intent(
									AlertSettingForVideos.this,
									AlertSettingForVideos.class);
							intent.putExtra("previousTeamId", previousTeamId);
							intent.putExtra("presentFavouriteTeamId",
									presentFavouriteTeamId);
							AlertSettingForVideos.this.startActivity(intent);
						} else {
							Intent intent = new Intent(
									AlertSettingForVideos.this,
									UserSelectTeam.class);
							AlertSettingForVideos.this.startActivity(intent);
						}
					}
				});
	}
}
