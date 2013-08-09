package com.liveclips.soccer.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.liveclips.soccer.R;
import com.liveclips.soccer.adapter.SeparatedListAdapter;
import com.liveclips.soccer.adapter.TeamsPlayerListViewAdapter;
import com.liveclips.soccer.model.PlayerItem;
import com.liveclips.soccer.utils.SoccerUtils;


public class AddPlayerSelectedCategoryMenuFragment extends Fragment {
	
	ListView findPLayerByCategoryListView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle saveInstantState) {
		return inflater.inflate(R.layout.players_fragment_view_by_categories,
				container, false);

	}


	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		View activityHeaderView = getActivity().getLayoutInflater().inflate(
				R.layout.players_fragment_actionbar_header_selected_category, null);
		ActionBar actionBar = getActivity().getActionBar();
		RelativeLayout actionBarLayout = (RelativeLayout) actionBar.getCustomView();
		RelativeLayout fragMenuHeader = (RelativeLayout)actionBarLayout.findViewById(R.id.fragmentMenuHeader);
		actionBarLayout.removeView(fragMenuHeader);
		actionBarLayout.addView(activityHeaderView, 300, 52);
		final Button backToPlayerFragmentButton = (Button) activityHeaderView
				.findViewById(R.id.backToPlayerFragment);
		backToPlayerFragmentButton.setVisibility(View.VISIBLE);
		backToPlayerFragmentButton
				.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						FragmentManager fragmentManager = getFragmentManager();
						fragmentManager.popBackStack();
						/*FragmentTransaction ft = fragmentManager
								.beginTransaction();

						AddPlayersFragment addPlayersFragment = new AddPlayersFragment();
						ft.replace(R.id.menuFragment, addPlayersFragment);
						ft.commit();*/

					}
				});
		super.onActivityCreated(savedInstanceState);
	}
	
	
	@Override
	public void onStart() {

		/*final TextView playerMenuTitle = (TextView) getActivity().findViewById(
				R.id.playerMenuTitle);
		playerMenuTitle.setText("By Team");*/
		/*final Button backToPlayerFragmentButton = (Button) getActivity()
				.findViewById(R.id.backToPlayerFragment);
		backToPlayerFragmentButton.setVisibility(View.VISIBLE);
		backToPlayerFragmentButton
				.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						FragmentManager fragmentManager = getFragmentManager();
						FragmentTransaction ft = fragmentManager
								.beginTransaction();

						AddPlayersFragment addPlayersFragment = new AddPlayersFragment();
						ft.replace(R.id.menuFragment, addPlayersFragment);
						ft.commit();

					}
				});

		Button playerMenuDoneButton = (Button) getActivity().findViewById(
				R.id.playerMenuDone);
		playerMenuDoneButton.setVisibility(View.INVISIBLE);*/

		EditText searchBarPlayerByCategory = (EditText) getActivity()
				.findViewById(R.id.searchBarPlayerByCategory);
		searchBarPlayerByCategory.setVisibility(View.GONE);

		

		super.onStart();

		/*SeparatedListAdapter adapter = new SeparatedListAdapter(getActivity());
		adapter.addSection("AFC EAST", new TeamsPlayerListViewAdapter(getActivity(),
				R.layout.players_fragment_list_row_item_addplayer,
				getPlayersByTeam("AFC EAST")));
		adapter.addSection("AFC NORTH", new TeamsPlayerListViewAdapter(
				getActivity(), R.layout.players_fragment_list_row_item_addplayer,
				getPlayersByTeam("AFC NORTH")));*/

		findPLayerByCategoryListView = (ListView) getActivity().findViewById(
				R.id.findPLayerByCategoryListView);
		SoccerUtils.setTeamsContent(findPLayerByCategoryListView, getActivity(),false);

		/* On click of category specific player selection*/
		findPLayerByCategoryListView
				.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						FragmentManager fragmentManager = getFragmentManager();
						FragmentTransaction ft = fragmentManager
								.beginTransaction();

						ShowAvailablePlayersFragment showAvailablePlayersFragment = new ShowAvailablePlayersFragment();
						ft.replace(R.id.menuFragment,
								showAvailablePlayersFragment);
						ft.addToBackStack(null);
						ft.commit();

					}
				});

		//findPLayerByCategoryListView.setAdapter(adapter);

	}

	/*public List<PlayerItem> getPlayersByTeam(String teamName) {

		List<PlayerItem> playerListByTeam = null;

		if (teamName.equals("AFC EAST")) {
			final List<PlayerItem> playerList = new ArrayList<PlayerItem>();
			String playerNamesForTeam1[] = { "Buffalo Bills", "Miami Dolphins",
					"New England Patriots", "New York Jets", };

			int teamImagesForTeam1[] = { R.drawable.ne,
					R.drawable.ne, R.drawable.ne,
					R.drawable.ne };

			PlayerItem item;

			for (int i = 0; i < playerNamesForTeam1.length; i++) {
				item = new PlayerItem();
				item.setTeamLogo((teamImagesForTeam1[i]));
				item.setPlayerName(playerNamesForTeam1[i]);
				playerList.add(item);
			}
			playerListByTeam = playerList;
		} else if (teamName.equals("AFC NORTH")) {
			final List<PlayerItem> playerList = new ArrayList<PlayerItem>();
			String playerNamesForTeam1[] = { "Baltimore Ravens",
					"Cincinnati Bengals", "Cleveland Browns",
					"Pittsburgh Steelers" };

			int teamImagesForTeam1[] = { R.drawable.ne,
					R.drawable.ne, R.drawable.ne, R.drawable.ne };

			PlayerItem item;

			for (int i = 0; i < playerNamesForTeam1.length; i++) {
				item = new PlayerItem();
				item.setTeamLogo((teamImagesForTeam1[i]));
				item.setPlayerName(playerNamesForTeam1[i]);
				playerList.add(item);
			}
			playerListByTeam = playerList;
		}
		return playerListByTeam;
	}
*/
}
