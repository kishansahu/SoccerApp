package com.liveclips.soccer.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.liveclips.soccer.R;



public class GameActivity extends BaseActivity {

	protected Context context = GameActivity.this;
	FragmentManager fragmentManager;
	Fragment mainMenuFragment;
	FragmentTransaction ft;
	boolean showSlider;
	ListView listView;
	
	TextView allPlaysTextView;
	TextView topPlaysTextView;
	TextView topRatedTextView;
	TextView watchAllTextView;
	RelativeLayout playCardLayout;
	VideoView playCardVideoView;
	TextView team1BtnPlayers;
	TextView team2BtnPlayers;
	LayoutInflater layoutInflater;
	View fragmentMenuHeaderView;
	View activityMenuHeaderView;
	int playCardVideoId = 0;
	LinearLayout statTab, playerTab, drivesTab, matchScoreBoardTabContainer;
	private Activity activity;
	private String leftSideTeamId = "cs_id";
	private String rightSideTeamId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_activity);

		activity = this;
		context = this;
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
		    rightSideTeamId = extras.getString("favouriteTeamId");
		}else{
			rightSideTeamId = "ks_id";
		}
		
		

	}

}