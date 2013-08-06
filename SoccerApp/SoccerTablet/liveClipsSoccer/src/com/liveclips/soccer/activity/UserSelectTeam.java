package com.liveclips.soccer.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;
import com.liveclips.soccer.R;
import com.liveclips.soccer.adapter.SeparatedListAdapter;
import com.liveclips.soccer.adapter.TeamMenuListViewAdapter;
import com.liveclips.soccer.commons.UserTypeEnum;
import com.liveclips.soccer.database.DatabaseHelper;
import com.liveclips.soccer.dto.LeagueTeamDto;
import com.liveclips.soccer.model.League;
import com.liveclips.soccer.model.TeamItem;
import com.liveclips.soccer.model.TeamMenuItems;
import com.liveclips.soccer.model.User;
import com.liveclips.soccer.utils.ImageProcessingUtil;
import com.liveclips.soccer.utils.PropertyReader;
import com.liveclips.soccer.utils.SharedPreferencesUtil;

public class UserSelectTeam extends Activity {
	private Activity activity;
	private Context context;
	Button linkToSetAlertsButton;
	private Properties appCommonProperties;
	ActionBar actionBar;
	Gson gson;
	User user;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		activity = this;
		context = this;
		appCommonProperties= PropertyReader
				.getPropertiesFormAssetDirectory("appcommonproperties.properties",
						activity);
		List<String> favouriteTeamIds= SharedPreferencesUtil.getFavouriteInSharedPreferencesList(
				context,
				appCommonProperties.getProperty("teamEntityName"));
		gson = new Gson();
		String json= SharedPreferencesUtil.getStringPreferences(activity,appCommonProperties.getProperty("userObject"));
		user= (User) gson.fromJson(json, User.class);
		
		if (!favouriteTeamIds.isEmpty() || user.getUserType().equals(UserTypeEnum.GUESTUSER)) {
			UserSelectTeam.this.startActivity(new Intent(
					UserSelectTeam.this, GameActivity.class));
		}
		setContentView(R.layout.user_select_favourite_teams);
		
		actionBar = getActionBar();
		actionBar.hide();
		
		ListView listView = (ListView) findViewById(R.id.available_team_list);
		setTeamsContent(listView, context);
		linkToSetAlertsButton = (Button) findViewById(R.id.linkToSetAlertsButton);
		linkToSetAlertsButton.setOnClickListener(new View.OnClickListener() {

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
				}

			}
		});
	}

	public void setTeamsContent(ListView listView, Context context) {

		DatabaseHelper databaseHelper = new DatabaseHelper(context);
		LeagueTeamDto leagueTeamDto = databaseHelper.getLeagueWithTeams();

		List<String> favouriteTeamsList = SharedPreferencesUtil
				.getFavouriteInSharedPreferencesList(context, "team");
		SeparatedListAdapter adapter = new SeparatedListAdapter(context);

		boolean isUserFavouriteTeam = false;

		for (League league : leagueTeamDto.getLeagueList()) {
			List<TeamMenuItems> rowItemsForQ = new ArrayList<TeamMenuItems>();
			for (TeamItem teamItem : league.getTeamList()) {
				isUserFavouriteTeam = false;
				if (favouriteTeamsList.contains(teamItem.getTeamId())) {
					isUserFavouriteTeam = true;
				}
				int teamLogo = ImageProcessingUtil
						.getTeamLogoImageResourceByTeamId(activity,
								teamItem.getTeamId());
				TeamMenuItems teamMenuItems = new TeamMenuItems(teamLogo,
						teamItem.getTeamName(), isUserFavouriteTeam,
						teamItem.getTeamId());
				rowItemsForQ.add(teamMenuItems);
			}
			adapter.addSection(league.getLeagueName(),
					new TeamMenuListViewAdapter(context,
							R.layout.team_menu_list_item, rowItemsForQ));
		}
		listView.setAdapter(adapter);
	}
}
