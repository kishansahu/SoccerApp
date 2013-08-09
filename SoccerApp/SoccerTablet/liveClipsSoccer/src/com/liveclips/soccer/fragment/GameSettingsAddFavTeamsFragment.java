package com.liveclips.soccer.fragment;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.liveclips.soccer.R;
import com.liveclips.soccer.activity.UserSelectTeam;
import com.liveclips.soccer.utils.SoccerUtils;


public class GameSettingsAddFavTeamsFragment extends Fragment {
Context context;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		context = (Context) getActivity();
		
		Log.d("gamesettingAddTeam", "gamesettingAddTeamfrag");

		View gamesettingAddTeamView = inflater.inflate(
				R.layout.game_setting_addfavteam_fragment_view, container, false);
		ListView listView = (ListView) gamesettingAddTeamView
				.findViewById(R.id.teamsListForUserFavorites);
		UserSelectTeam userSelectTeam = new UserSelectTeam(); 
		SoccerUtils.setTeamsContent(listView, getActivity(),true);
	//	listView.setOnItemClickListener(listItemListener);
		return gamesettingAddTeamView;
	}

	
public void onActivityCreated(Bundle savedInstanceState) {
		
		super.onActivityCreated(savedInstanceState);
		final View newfragMenuHeader = getActivity().getLayoutInflater().inflate(
				R.layout.game_schedule_fragment_actionbar_header_schedule, null);
		ActionBar actionBar = getActivity().getActionBar();
		
		final RelativeLayout actionBarLayout = (RelativeLayout) actionBar
				.getCustomView();
		
		final RelativeLayout activityHeaderLayout = (RelativeLayout) getActivity()
				.findViewById(R.id.activityMenuHeader);
	
		final RelativeLayout commonfragMenuHeader = (RelativeLayout) actionBarLayout
				.findViewById(R.id.commonFragmentMenuHeader);

		if (commonfragMenuHeader != null) {
			commonfragMenuHeader.setVisibility(View.INVISIBLE);
		}

		RelativeLayout fragMenuHeader = (RelativeLayout) actionBarLayout
				.findViewById(R.id.fragmentMenuHeader);
		actionBarLayout.removeView(fragMenuHeader);

		actionBarLayout.addView(newfragMenuHeader, 300, 52);
		
		Button back=(Button)actionBarLayout.findViewById(R.id.backToMainScreen);
		TextView headerText =(TextView)actionBarLayout.findViewById(R.id.teams);
		headerText.setText(R.string.chooseTeamToFavHeader);
		back.setBackgroundResource(R.drawable.settings_button);
		back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {

				FragmentManager fragmentManager = getFragmentManager();
				fragmentManager.popBackStack();
				FragmentTransaction ft = fragmentManager
						.beginTransaction();
				Bundle bundle = new Bundle();
				bundle.putBoolean("showSettingFragment", true);
				GameSettingsFragment gameSettingsFragment = new GameSettingsFragment();
				gameSettingsFragment.setArguments(bundle);
				ft.replace(R.id.menuFragment, gameSettingsFragment);
				ft.commit();
			}
		});
		
	}
	
	
}
