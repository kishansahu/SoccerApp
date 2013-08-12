package com.liveclips.soccer.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.liveclips.soccer.R;
import com.liveclips.soccer.adapter.GameScheduleListViewAdaptor;
import com.liveclips.soccer.adapter.SeparatedListAdapter;
import com.liveclips.soccer.model.GameSchedule;


public class GameScheduleFragment extends Fragment {

	public List<GameSchedule> getGameScheduleForWeek5() {

		String firstTeamNames[] = { "SD", "SEA", "ALT", "GB", "IND" };
		String secondTeamNames[] = { "NYG", "JAC", "HOU", "NE", "TEN" };
		int firstTeamImageNames[] = { R.drawable.teamlogo_atl_mad_id, R.drawable.teamlogo_bay_lev_id,
				R.drawable.teamlogo_bor_dor_id, R.drawable.teamlogo_bor_mon_id, R.drawable.teamlogo_che_id };
		int secondTeamImageNames[] = { R.drawable.teamlogo_ein_fra_id,
				R.drawable.teamlogo_fc_bar_id, R.drawable.teamlogo_fc_bay_mun_id,
				R.drawable.teamlogo_ham_sv_id, R.drawable.teamlogo_liv_id };
		int firstTeamScores[] = { 3, 34, 24, 21, 14 };
		int secondTeamsScores[] = { 28, 3, 24, 14, 14 };
		boolean isGameInFuture[] = { true, true, true, true, true };
		String timeLeft[] = { "4TH 8:14", "3RD 6:11", "4TH 3:12", "4TH 6:38",
				"3RD 5:23" };

		List<GameSchedule> gameScheduleList = new ArrayList<GameSchedule>();

		for (int i = 0; i < 5; i++) {
			GameSchedule gameSchedule = new GameSchedule();
			gameSchedule.setFirstTeamName(firstTeamNames[i]);
			gameSchedule.setFirstTeamImageId(firstTeamImageNames[i]);
			gameSchedule.setSecondTeamName(secondTeamNames[i]);
			gameSchedule.setSecondTeamImageId(secondTeamImageNames[i]);
			gameSchedule.setFirstTeamScore(firstTeamScores[i]);
			gameSchedule.setSecondTeamScore(secondTeamsScores[i]);
			gameSchedule.setGameInFuture(isGameInFuture[i]);
			gameSchedule.setTimeLeft(timeLeft[i]);
			gameScheduleList.add(gameSchedule);
		}

		return gameScheduleList;
	}

	public OnItemClickListener listItemListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> adapterView, View view,
				int lineNo, long arg3) {
//			TextView firstTeamNameTextView = (TextView) view
//					.findViewById(R.id.first_team_shortname);
//			TextView secondTeamNameTextView = (TextView) view
//					.findViewById(R.id.second_team_shortname);
//			String firstTeamName = (String) firstTeamNameTextView.getText();
//			String secondTeamName = (String) secondTeamNameTextView.getText();
//			Intent intent = new Intent(getActivity(), GameActivity.class);
//			intent.putExtra("firstTeamName", firstTeamName);
//			intent.putExtra("secondTeamName", secondTeamName);
//			startActivity(intent);
			
			FragmentManager fm = getActivity().getFragmentManager();
			fm.popBackStack();
		}
	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.d("gameschedule", "gameschedulfragment");

		View gameScheduleView = inflater.inflate(
				R.layout.game_schedule_fragment_view, container, false);
		ListView listView = (ListView) gameScheduleView
				.findViewById(R.id.gameScheduleList);
		SeparatedListAdapter adapter = new SeparatedListAdapter(getActivity());
		adapter.addSection("WEEK5", new GameScheduleListViewAdaptor(
				getActivity(), R.layout.game_popover_list_row__item_player,
				getGameScheduleForWeek5()));
		listView.setOnItemClickListener(listItemListener);
		listView.setAdapter(adapter);
		return gameScheduleView;
	}

	
public void onActivityCreated(Bundle savedInstanceState) {
		
		super.onActivityCreated(savedInstanceState);
		final View newfragMenuHeader = getActivity().getLayoutInflater().inflate(
				R.layout.game_schedule_fragment_actionbar_header_schedule_by_weeks, null);
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
		back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {

				FragmentManager fragmentManager = getActivity()
						.getFragmentManager();
				FragmentTransaction ft = fragmentManager.beginTransaction();
				Fragment topicMenuFragment = new TopicMenuFragment();
				ft.replace(R.id.menuFragment, topicMenuFragment);
				ft.commit();
				actionBarLayout.removeView(newfragMenuHeader);
				actionBarLayout.findViewById(R.id.commonFragmentMenuHeader).setVisibility(View.VISIBLE);
				activityHeaderLayout.setVisibility(View.VISIBLE);
				}
		});
		
	}
	
	
}
