package com.liveclips.soccer.activity;

import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.google.gson.Gson;
import com.liveclips.soccer.R;
import com.liveclips.soccer.database.DatabaseHelper;
import com.liveclips.soccer.model.TeamAlertSetting;
import com.liveclips.soccer.model.TeamItem;
import com.liveclips.soccer.utils.SharedPreferencesUtil;
import com.liveclips.soccer.utils.SoccerUtils;


public class AlertSettingForVideos extends Activity {

	private ToggleButton allPLaysToggleButton, topPLaysToggleButton,
			scoringPLaysToggleButton, savesToggleButton,
			redCardToggleButton,yellowCardToggleButton, playOfTheGameToggleButton;

	private SeekBar basicAlertLevelSeekbar, passingPlayAlertLevelSeekbar,
			rushingPlayAlertLevelSeekbar;

	Button openSelectedTeamPlayButton, backToAvailableTeamListButton;
	Context context;
	Activity activity;
	String previousTeamId, presentFavouriteTeamId, nextTeamId, json;
	SharedPreferences appSharedPrefs;
	Editor prefsEditor;
	Gson gson;
	List<String> favTeamList;
	ToggleButton alertsForAllteams;
	TextView teamNameGameAlerts, alertsHeader, alertPerGameTextview;
	TeamAlertSetting teamAlertSetting;
	String favouriteTeamId;
	int index = 0;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_settings_view_alerts);
		context = this;
		activity = this;
		appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(this
				.getApplicationContext());
		
		favTeamList = SharedPreferencesUtil
				.getFavouriteInSharedPreferencesList(context, "team");
		setContent();
		Collections.sort(favTeamList);
		
	}

	private void setContent() {

		teamAlertSetting = new TeamAlertSetting();
		teamNameGameAlerts = (TextView) findViewById(R.id.teamNameGameAlerts);
		alertsHeader = (TextView) findViewById(R.id.alertsHeader);
		alertPerGameTextview = (TextView) findViewById(R.id.alert_per_game_textview);
		openSelectedTeamPlayButton = (Button) findViewById(R.id.openSelectedTeamPlayButton);
		backToAvailableTeamListButton = (Button) findViewById(R.id.backToAvailableTeamListButton);
		alertsForAllteams = (ToggleButton) findViewById(R.id.alerts_for_allteams_toggle_button);
		DatabaseHelper databaseHelper = new DatabaseHelper((Context)activity);
		TeamItem teamItem=databaseHelper.getTeamInfoByTeamId(favTeamList.get(index));
		teamNameGameAlerts.setText(teamItem.getTeamName() + " Game Alerts");
		openSelectedTeamPlayButton
				.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						gson = new Gson();
						json = gson.toJson(teamAlertSetting);
						prefsEditor = appSharedPrefs.edit();
						prefsEditor.putString("teamAlertSettingObject"
								+ favTeamList.get(index), json);
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
							index = favTeamList.size();
						}
						
						index = index + 1;
						if (index == favTeamList.size()) {
							Intent intent = new Intent(context,
									GameActivity.class);

							AlertSettingForVideos.this.startActivity(intent);
							finish();
						} else {
							setContentView(R.layout.user_settings_view_alerts);
							setContent();
							String teamAlertSettingFromSharedprefJson = appSharedPrefs
									.getString("teamAlertSettingObject"
											+ favTeamList.get(index), "");
							if (teamAlertSettingFromSharedprefJson.length() > 0) {
								teamAlertSetting = gson.fromJson(
										teamAlertSettingFromSharedprefJson,
										TeamAlertSetting.class);
								SoccerUtils.setAlertSettingForJsonString(activity,
										teamAlertSetting);
							}

						}
					
					}

				});

		backToAvailableTeamListButton
				.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						index = index - 1;
						if (index >= 0) {
							String teamAlertSettingFromSharedprefJson = appSharedPrefs
									.getString("teamAlertSettingObject"
											+ favTeamList.get(index), "");
							teamAlertSetting = gson.fromJson(
									teamAlertSettingFromSharedprefJson,
									TeamAlertSetting.class);
							SoccerUtils.setAlertSettingForJsonString(activity,
									teamAlertSetting);
						}
						if (index < 0) {
							onBackPressed();
						}
					}
				});

		allPLaysToggleButton = (ToggleButton) findViewById(R.id.all_plays_toggle_button);

		allPLaysToggleButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				teamAlertSetting.setAllPlays(allPLaysToggleButton.isChecked());
			}
		});

		topPLaysToggleButton = (ToggleButton) findViewById(R.id.top_plays_toggle_button);
		topPLaysToggleButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				teamAlertSetting.setTopPlays(topPLaysToggleButton.isChecked());
			}
		});

		scoringPLaysToggleButton = (ToggleButton) findViewById(R.id.scoring_plays_toggle_button);
		scoringPLaysToggleButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				teamAlertSetting.setScoringPlays(scoringPLaysToggleButton
						.isChecked());
			}
		});

		savesToggleButton = (ToggleButton) findViewById(R.id.saves_toggle_button);
		savesToggleButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				teamAlertSetting.setTurnOversPlays(savesToggleButton
						.isChecked());
			}
		});
		redCardToggleButton = (ToggleButton) findViewById(R.id.red_card_toggle_button);
		redCardToggleButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				teamAlertSetting.setRedZonePlays(redCardToggleButton
						.isChecked());
			}
		});
		
		yellowCardToggleButton = (ToggleButton) findViewById(R.id.yellow_cards_toggle_button);
		yellowCardToggleButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		
		playOfTheGameToggleButton = (ToggleButton) findViewById(R.id.play_of_the_game_toggle_button);
		playOfTheGameToggleButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				teamAlertSetting.setPlaysOfGame(playOfTheGameToggleButton
						.isChecked());
			}
		});

		/**
		 * Basic alert seek bar handler
		 */

		basicAlertLevelSeekbar = (SeekBar) findViewById(R.id.basic_alertlevel_seekBar);
		basicAlertLevelSeekbar
				.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
					int progressChanged = 0;
					int progressBarMaxRange = 0;

					public void onProgressChanged(SeekBar seekBar,
							int progress, boolean fromUser) {
						progressChanged = progress;
						progressBarMaxRange = seekBar.getMax();
						if (progress==1) {
							alertsHeader.setText("GB at NE Alerts: Low");
							alertPerGameTextview.setText("  5-10 Alerts per game");
														
							allPLaysToggleButton.setChecked(false);
							topPLaysToggleButton.setChecked(true);
							scoringPLaysToggleButton.setChecked(true);
							savesToggleButton.setChecked(false);
							redCardToggleButton.setChecked(false);
		                    playOfTheGameToggleButton.setChecked(true);
							
						}else if (progress==2) {
							alertsHeader.setText("GB at NE Alerts: Medium");
							alertPerGameTextview.setText("  50-60 Alerts per game");
							
							allPLaysToggleButton.setChecked(false);
							topPLaysToggleButton.setChecked(true);
							scoringPLaysToggleButton.setChecked(true);
							savesToggleButton.setChecked(true);
							redCardToggleButton.setChecked(true);
		                    playOfTheGameToggleButton.setChecked(true);
							
						}else if (progress==3) {
							alertsHeader.setText("GB at NE Alerts: High");
							alertPerGameTextview.setText("  100+ Alerts per game");
							
							allPLaysToggleButton.setChecked(true);
							topPLaysToggleButton.setChecked(false);
							scoringPLaysToggleButton.setChecked(false);
							savesToggleButton.setChecked(false);
							redCardToggleButton.setChecked(false);
		                    playOfTheGameToggleButton.setChecked(true);
						}
						
					}

					public void onStopTrackingTouch(SeekBar seekBar) {
						teamAlertSetting.setBasicAlert(progressChanged);
					}

					@Override
					public void onStartTrackingTouch(SeekBar seekBar) {
						// TODO Auto-generated method stub

					}
				});
	}

}
