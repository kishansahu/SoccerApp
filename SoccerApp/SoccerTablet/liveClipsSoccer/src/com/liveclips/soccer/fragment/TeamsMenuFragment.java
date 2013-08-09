package com.liveclips.soccer.fragment;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
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
import android.widget.TextView;

import com.liveclips.soccer.R;
import com.liveclips.soccer.activity.GameActivity;
import com.liveclips.soccer.utils.SoccerUtils;


public class TeamsMenuFragment extends Fragment {

	ListView listView ;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle saveInstantState) {
		Log.d("TeamsMenuFragment", "onCreateView TeamsMenuFragment");
		View teamsView =inflater.inflate(R.layout.main_fragment_view, container, false);
		listView = (ListView) teamsView
				.findViewById(R.id.globalNavigationListView);
		SoccerUtils.setTeamsContent(listView, getActivity(),false);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int arg2,
					long arg3) {
				TextView teamId= (TextView) view.findViewById(R.id.team_id);
				Intent intent = new Intent(getActivity(), GameActivity.class);
				Bundle mBundle = new Bundle();
				mBundle.putString("favouriteTeamId", teamId.getText().toString());
				intent.putExtras(mBundle);
				startActivity(intent);
			}
		});
		return teamsView;
		
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		
		super.onActivityCreated(savedInstanceState);
		
				
		final View newfragMenuHeader = getActivity().getLayoutInflater().inflate(
				R.layout.teams_fragment_actionbar_header_show_teams, null);
		ActionBar actionBar = getActivity().getActionBar();
		
		final RelativeLayout actionBarLayout = (RelativeLayout) actionBar
				.getCustomView();
		
		final RelativeLayout activityHeaderLayout = (RelativeLayout) getActivity()
				.findViewById(R.id.activityMenuHeader);
	
		RelativeLayout commonfragMenuHeader = (RelativeLayout) actionBarLayout
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
