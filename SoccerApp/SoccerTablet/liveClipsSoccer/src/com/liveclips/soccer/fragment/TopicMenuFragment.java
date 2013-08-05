package com.liveclips.soccer.fragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.liveclips.soccer.R;
import com.liveclips.soccer.activity.GameActivity;
import com.liveclips.soccer.adapter.TopicMenuArrayAdapter;
import com.liveclips.soccer.database.DatabaseHelper;
import com.liveclips.soccer.model.LiveClipsContentListItem;
import com.liveclips.soccer.model.TeamItem;
import com.liveclips.soccer.utils.PropertyReader;
import com.liveclips.soccer.utils.SharedPreferencesUtil;



public class TopicMenuFragment extends Fragment {

	TopicMenuArrayAdapter adapter;
	ListView listView1;
	DatabaseHelper databaseHelper;
	private Properties properties, appCommonProperties;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle saveInstantState) {
		Log.d("Fragment TopicMenuFragment", "onCreateView");

		return inflater.inflate(R.layout.main_fragment_view, container, false);

	}

	@Override
	public void onStart() {
		super.onStart();
		Log.d("Fragment TopicMenuFragment", "onStart");

		//getActivity().findViewById(
		
		
		listView1 = (ListView) getActivity().findViewById(
				R.id.globalNavigationListView);

		if (properties == null) {
			properties = PropertyReader.getPropertiesFormAssetDirectory(
					"soccer_main_menu.properties", getActivity());
		}
		if (appCommonProperties == null) {
			appCommonProperties = PropertyReader
					.getPropertiesFormAssetDirectory(
							"appcommonproperties.properties", getActivity());
		}

		final List<LiveClipsContentListItem> liveClipsContentListItems = new ArrayList<LiveClipsContentListItem>();

		List<TeamItem> teamItems = new DatabaseHelper((Context)getActivity()).getAllTeams();
		
		List<String> favTeams = SharedPreferencesUtil
				.getFavouriteInSharedPreferencesList(getActivity(), "team");
		for (String favTeamId : favTeams) {
			LiveClipsContentListItem liveClipsContentListItem = new LiveClipsContentListItem();
			liveClipsContentListItem.setCategoryType("favouriteTeams");
			liveClipsContentListItem.setLeftSideImage(favTeamId);
			liveClipsContentListItem.setEntityId(favTeamId);
			liveClipsContentListItem.setRowText(favTeamId);
			for(TeamItem teamItem:teamItems){
				if(teamItem.getTeamId().equalsIgnoreCase(favTeamId)){
					liveClipsContentListItem.setRowText(teamItem.getTeamName());
					break;
				}
			}
			
			liveClipsContentListItems.add(liveClipsContentListItem);
		}

		List<String> topicListKey = new ArrayList<String>();
		Set<Object> keys = properties.keySet();
		for (Object ob : keys) {
			topicListKey.add((String) ob);
		}
		Collections.sort(topicListKey);

		/**
		 * Get User favourite team by teamId from shared preference if not
		 * available set to default team
		 */
		

		for (String key : topicListKey) {
			LiveClipsContentListItem liveClipsContentListItem = new LiveClipsContentListItem();
			liveClipsContentListItem.setCategoryType(key);
			liveClipsContentListItem.setTopicMenu(true);
			liveClipsContentListItem.setLeftSideImage(key);
			liveClipsContentListItem.setRowText(properties.getProperty(key));
			liveClipsContentListItems.add(liveClipsContentListItem);
		}

		adapter = new TopicMenuArrayAdapter(getActivity(),
				R.layout.common_list_row_item, liveClipsContentListItems);

		listView1.setAdapter(adapter);

		listView1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapterView, View view,
					int position, long arg3) {/*

				LiveClipsContentListItem selectedItemFromList = (LiveClipsContentListItem) (liveClipsContentListItems
						.get(position));
				int selectedIndex = adapter.getSelectedIndex();

				if (position != 3 && position != 4 && position != 5
						&& selectedIndex == position) {
					return;
				}

				view.findViewById(R.id.parentTextView).setSelected(true);

				if (selectedIndex != -1) {
					adapterView.getChildAt(selectedIndex)
							.findViewById(R.id.parentTextView)
							.setSelected(false);
				}

				adapter.setSelectedIndex(position);
				if (selectedItemFromList.getCategoryType().equalsIgnoreCase(
						"favouriteTeams")) {
					Intent gameActivityIntent= new Intent(getActivity(), GameActivity.class);
					gameActivityIntent.putExtra("favouriteTeamId", selectedItemFromList.getEntityId());
					startActivity(gameActivityIntent);
				} else if (selectedItemFromList.getCategoryType()
						.equalsIgnoreCase("2players")) {
					startActivity(new Intent(getActivity(),
							PlayersActivity.class));
				} else if (selectedItemFromList.getCategoryType()
						.equalsIgnoreCase("3highlights")) {
					startActivity(new Intent(getActivity(),
							NFLHighlightsActivity.class));
				} else if (selectedItemFromList.getCategoryType()
						.equalsIgnoreCase("4divisions")) {
					FragmentManager fragmentManager = getFragmentManager();
					FragmentTransaction ft = fragmentManager.beginTransaction();
					Fragment divisionMenuFragment = new DivisionMenuFragment();
					ft.replace(R.id.menuFragment, divisionMenuFragment);
					ft.commit();
				} else if (selectedItemFromList.getCategoryType()
						.equalsIgnoreCase("5teams")) {
					FragmentManager fragmentManager = getFragmentManager();
					FragmentTransaction ft = fragmentManager.beginTransaction();
					Fragment teamMenuFragment = new TeamsMenuFragment();
					ft.replace(R.id.menuFragment, teamMenuFragment);

					ft.commit();
				} else if (selectedItemFromList.getCategoryType()
						.equalsIgnoreCase("6gameSchedule")) {
					FragmentManager fragmentManager = getFragmentManager();
					FragmentTransaction ft = fragmentManager.beginTransaction();
					Fragment gameScheduleFragment = new GameScheduleFragment();
					ft.replace(R.id.menuFragment, gameScheduleFragment);
					ft.commit();

				} else if (selectedItemFromList.getCategoryType()
						.equalsIgnoreCase("7settings")) {
					FragmentManager fragmentManager = getFragmentManager();
					FragmentTransaction ft = fragmentManager.beginTransaction();
					Fragment gameSettingsFragment = new GameSettingsFragment();
					ft.replace(R.id.menuFragment, gameSettingsFragment);
					ft.commit();

				}

			*/}

		});
	}
}
