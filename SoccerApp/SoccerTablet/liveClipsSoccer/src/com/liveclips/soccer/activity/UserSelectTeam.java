package com.liveclips.soccer.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.liveclips.soccer.R;
import com.liveclips.soccer.adapter.SeparatedListAdapter;
import com.liveclips.soccer.adapter.TeamMenuListViewAdapter;
import com.liveclips.soccer.model.TeamMenuItems;
import com.liveclips.soccer.utils.SharedPreferencesUtil;

public class UserSelectTeam extends Activity {
private Activity activity;
private Context context;
Button linkToSetAlertsButton;
private Properties appCommonProperties;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
	//	appCommonProperties = PropertyReader.getPropertiesFormAssetDirectory("appcommonproperties.properties", (Activity) context);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_select_favourite_teams);
		activity = this;
		context = this;
		ListView listView = (ListView) findViewById(R.id.available_team_list);
		setTeamsContent(listView, context);
		linkToSetAlertsButton = (Button) findViewById(R.id.linkToSetAlertsButton);
		linkToSetAlertsButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {/*
				if(SharedPreferencesUtil.getFavouriteInSharedPreferencesList(context,  "team").size()!=0){
				Intent userSelectTeamActivityIntent = new Intent(
						UserSelectTeam.this,
						AlertSettingForVideos.class);
				userSelectTeamActivityIntent.putExtra("previousTeamId","");
				userSelectTeamActivityIntent.putExtra("presentFavouriteTeamId","");
				UserSelectTeam.this
						.startActivity(userSelectTeamActivityIntent);
			}
			*/}
		});
	}

	public void setTeamsContent(ListView listView, Context context) {

		List<TeamMenuItems> rowItemsForQ1 = new ArrayList<TeamMenuItems>();
		List<TeamMenuItems> rowItemsForQ2 = new ArrayList<TeamMenuItems>();
		List<TeamMenuItems> rowItemsForQ3 = new ArrayList<TeamMenuItems>();
		List<TeamMenuItems> rowItemsForQ4 = new ArrayList<TeamMenuItems>();

		String[] teamShortNamesForQ1 = { "Buffalo Bills", "Miami Dolphins",
				"New England Partriots", "New York Jets" };
		String[] teamShortNamesForQ2 = { "Batimore Ravens",
				"Cincinnati Bengals", "Clevelend Browns", "Pittsburgh Steelers" };
		String[] teamShortNamesForQ3 = { "Houston Texans",
				"Indianapolis Colts", "Jacksonville Jaguars",
				"Tennessee Titans" };
		String[] teamShortNamesForQ4 = { "Denver Broncos",
				"Kansas City Chicfs", "Buffalo Bills", "Cincinnati Bengals" };
		
		List<String> favTeamList = SharedPreferencesUtil.getFavouriteInSharedPreferencesList(context,  "team");
		
		String[] teamIdForQ1 = { "bb_id", "ccc_id","cs_id", "gw_id" };
		String[] teamIdForQ2 = { "is_id", "kj_id","ks_id", "mh_id" };
		String[] teamIdForQ3 = { "nc_id", "ps_id","sk_id", "ss_id" };
		String[] teamIdForQ4 = { "su_id", "tc_id","wf_id", "csb_id" };
		

		int[] teamLogoForQ1 = { R.drawable.buf, R.drawable.mia, R.drawable.ne,
				R.drawable.nyj };

		int[] teamLogoForQ2 = { R.drawable.blt, R.drawable.cin, R.drawable.cle,
				R.drawable.pit };

		int[] teamLogoForQ3 = { R.drawable.sl, R.drawable.ind, R.drawable.jac,
				R.drawable.ten };

		int[] teamLogoForQ4 = { R.drawable.den, R.drawable.kc, R.drawable.kc,
				R.drawable.kc };
		
		boolean[] teamFavForQ1  = new boolean[4];
		boolean[] teamFavForQ2  = new boolean[4];
		boolean[] teamFavForQ3  = new boolean[4];
		boolean[] teamFavForQ4  = new boolean[4];
		int q2=0;
		for(String teamId : teamIdForQ2){
			if(favTeamList.contains(teamId)){
				teamFavForQ2[q2] = true;
			}else{
				teamFavForQ2[q2] = false;
			}
			q2=q2+1;
		}
		int q3=0;
		for(String teamId : teamIdForQ3){
			if(favTeamList.contains(teamId)){
				teamFavForQ3[q3] = true;
			}else{
				teamFavForQ3[q3] = false;
			}
			q3=q3+1;
		}
		int q4=0;
		for(String teamId : teamIdForQ4){
			if(favTeamList.contains(teamId)){
				teamFavForQ4[q4] = true;
			}else{
				teamFavForQ4[q4] = false;
			}
			q4=q4+1;
		}
		int q1=0;
		for(String teamId : teamIdForQ1){
			if(favTeamList.contains(teamId)){
				teamFavForQ1[q1] = true;
			}else{
				teamFavForQ1[q1] = false;
			}
			q1=q1+1;
		}

		for (int i = 0; i < teamShortNamesForQ1.length; i++) {
			TeamMenuItems item = new TeamMenuItems(teamLogoForQ1[i],
					teamShortNamesForQ1[i], teamFavForQ1[i], teamIdForQ1[i]);

			rowItemsForQ1.add(item);
		}
		for (int i = 0; i < teamShortNamesForQ2.length; i++) {
			TeamMenuItems item = new TeamMenuItems(teamLogoForQ2[i],
					teamShortNamesForQ2[i], teamFavForQ2[i], teamIdForQ2[i]);

			rowItemsForQ2.add(item);
		}
		for (int i = 0; i < teamShortNamesForQ3.length; i++) {
			TeamMenuItems item = new TeamMenuItems(teamLogoForQ3[i],
					teamShortNamesForQ3[i], teamFavForQ3[i], teamIdForQ3[i]);

			rowItemsForQ3.add(item);
		}
		for (int i = 0; i < teamShortNamesForQ4.length; i++) {
			TeamMenuItems item = new TeamMenuItems(teamLogoForQ4[i],
					teamShortNamesForQ4[i], teamFavForQ4[i], teamIdForQ4[i]);

			rowItemsForQ4.add(item);
		}

		SeparatedListAdapter adapter = new SeparatedListAdapter(context);
		
		adapter.addSection("AFC EAST", new TeamMenuListViewAdapter(
				context, R.layout.team_menu_list_item, rowItemsForQ1));
		adapter.addSection("AFC NORTH", new TeamMenuListViewAdapter(
				context, R.layout.team_menu_list_item, rowItemsForQ2));
		adapter.addSection("AFC SOUTH", new TeamMenuListViewAdapter(
				context, R.layout.team_menu_list_item, rowItemsForQ3));
		adapter.addSection("Denver Broncos", new TeamMenuListViewAdapter(
				context, R.layout.team_menu_list_item, rowItemsForQ4));
		listView.setAdapter(adapter);
	}

}
