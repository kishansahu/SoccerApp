package com.liveclips.soccer.activity;

import java.util.List;
import java.util.Properties;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;
import com.liveclips.soccer.R;
import com.liveclips.soccer.commons.UserTypeEnum;
import com.liveclips.soccer.model.User;
import com.liveclips.soccer.utils.PropertyReader;
import com.liveclips.soccer.utils.SharedPreferencesUtil;
import com.liveclips.soccer.utils.SoccerUtils;

public class UserSelectTeam extends Activity {
	private Activity activity;
	private Context context;
	Button linkToSetAlertsButton;
	Properties appCommonProperties;
	ActionBar actionBar;
	Gson gson;
	User user;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		activity = this;
		context = this;
		appCommonProperties = PropertyReader.getPropertiesFormAssetDirectory(
				"appcommonproperties.properties", activity);
		List<String> favouriteTeamIds = SharedPreferencesUtil
				.getFavouriteInSharedPreferencesList(context,
						appCommonProperties.getProperty("teamEntityName"));
		gson = new Gson();
		String json = SharedPreferencesUtil.getStringPreferences(activity,
				appCommonProperties.getProperty("userObject"));
		user = (User) gson.fromJson(json, User.class);

		/**
		 * Check if User is required to select any team for favorites
		 */
		if (!favouriteTeamIds.isEmpty()
				|| user.getUserType().equals(UserTypeEnum.GUESTUSER)) {
			UserSelectTeam.this.startActivity(new Intent(UserSelectTeam.this,
					GameActivity.class));
		} else {
			setContentView(R.layout.user_select_favourite_teams);
			getActionBar().hide();
			ListView listView = (ListView) findViewById(R.id.available_team_list);
			SoccerUtils.setTeamsContent(listView, activity, true);
	
		actionBar = getActionBar();
		actionBar.hide();
		SoccerUtils.setTeamsContent(listView, activity, true);
		linkToSetAlertsButton = (Button) findViewById(R.id.linkToSetAlertsButton);
		linkToSetAlertsButton.setOnClickListener(new View.OnClickListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				if (SharedPreferencesUtil.getFavouriteInSharedPreferencesList(
						context, "team").size() != 0) {
					Intent userSelectTeamActivityIntent = new Intent(
							UserSelectTeam.this, AlertSettingForVideos.class);
					userSelectTeamActivityIntent.putExtra("previousTeamId", "");
					userSelectTeamActivityIntent.putExtra(
							"presentFavouriteTeamId", "");
					UserSelectTeam.this
							.startActivity(userSelectTeamActivityIntent);
				} else {
					AlertDialog alertDialog = new AlertDialog.Builder(
	                        UserSelectTeam.this).create();
					alertDialog.setTitle("Select Favorite Team");
					alertDialog.setMessage("Please select at least one team to proceed");
					alertDialog.setButton("OK",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									// here you can add functions
								}
							});
					alertDialog.setIcon(R.drawable.tick);
					alertDialog.show();
				}

			}
		});
	}
	}
}
