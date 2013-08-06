package com.liveclips.soccer.fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import android.app.ActionBar;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.liveclips.soccer.R;
import com.liveclips.soccer.activity.SignUpOptionsActivity;
import com.liveclips.soccer.adapter.SeparatedListAdapter;
import com.liveclips.soccer.adapter.TopicMenuArrayAdapter;
import com.liveclips.soccer.commons.UserTypeEnum;
import com.liveclips.soccer.database.DatabaseHelper;
import com.liveclips.soccer.model.LiveClipsContentListItem;
import com.liveclips.soccer.model.TeamItem;
import com.liveclips.soccer.model.User;
import com.liveclips.soccer.utils.PropertyReader;
import com.liveclips.soccer.utils.SharedPreferencesUtil;


public class GameSettingsFragment extends Fragment {
	ListView listView;
	FragmentManager fragmentManager;
	Fragment mainMenuFragment, addPlayersFragment;
	FragmentTransaction ft;
	Properties appCommonProperties;
	List<LiveClipsContentListItem> favouriteTeamSectionList;
	List<LiveClipsContentListItem> favouritePlayerSectionList;
	Gson gson;
	User user;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		appCommonProperties = PropertyReader.getPropertiesFormAssetDirectory(
				"appcommonproperties.properties", getActivity());
		Log.d("gamessettings", "gamesettingsfragment");

		gson = new Gson();
		String json= SharedPreferencesUtil.getStringPreferences(getActivity(),appCommonProperties.getProperty("userObject"));
		user= (User) gson.fromJson(json, User.class);
		
		fragmentManager = getFragmentManager();
		favouriteTeamSectionList = new ArrayList<LiveClipsContentListItem>();
		favouritePlayerSectionList = new ArrayList<LiveClipsContentListItem>();
		List<TeamItem> teamItems = new DatabaseHelper((Context) getActivity())
				.getAllTeams();
		List<String> favTeams = SharedPreferencesUtil
				.getFavouriteInSharedPreferencesList(getActivity(), "team");
		for (String favTeamId : favTeams) {
			LiveClipsContentListItem favouriteTeamItem = new LiveClipsContentListItem();
			favouriteTeamItem.setCategoryType("favouriteTeams");
			favouriteTeamItem.setEntityId(favTeamId);
			favouriteTeamItem.setShowLeftSideDefaultIcon(true);

			for (TeamItem teamItem : teamItems) {
				if (teamItem.getTeamId().equalsIgnoreCase(favTeamId)) {
					favouriteTeamItem.setRowText(teamItem.getTeamName());
					break;
				}
			}

			favouriteTeamSectionList.add(favouriteTeamItem);
		}
		LiveClipsContentListItem liveClipsContentListItem = new LiveClipsContentListItem();
		liveClipsContentListItem.setCategoryType("AddTeam");
		liveClipsContentListItem.setRowText("Add a Team");
		favouriteTeamSectionList.add(liveClipsContentListItem);

		List<String> favPlayers = SharedPreferencesUtil
				.getFavouriteInSharedPreferencesList(getActivity(), "player");
		for (String favPlayerId : favPlayers) {
			LiveClipsContentListItem favouriteTeamItem = new LiveClipsContentListItem();
			favouriteTeamItem.setCategoryType("favouritePlayers");
			favouriteTeamItem.setEntityId(favPlayerId);
			favouriteTeamItem.setRowText(favPlayerId);
			favouriteTeamItem.setShowLeftSideDefaultIcon(true);
			favouritePlayerSectionList.add(favouriteTeamItem);

		}
		LiveClipsContentListItem AddplayerListItem = new LiveClipsContentListItem();
		AddplayerListItem.setCategoryType("AddPlayer");
		AddplayerListItem.setRowText("Add a Player");
		favouritePlayerSectionList.add(AddplayerListItem);

		View gameSettingView = inflater.inflate(
				R.layout.game_setting_fragment_view, container, false);
		TextView userInfo = (TextView) gameSettingView
				.findViewById(R.id.account_holder_name);
		
		if (!user.getName().isEmpty() && user.getName() != null) {
			userInfo.setText(user.getName());
		} else {
			if (!user.getEmail().isEmpty() && user.getEmail() != null) {
				userInfo.setText(user.getEmail());
			} else {
				userInfo.setText("Guest User");
			}
		}
		ImageView signoutButton = (ImageView) gameSettingView
				.findViewById(R.id.signout_button);
		signoutButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				if(user.getUserType().equals(UserTypeEnum.FACEBOOKUSER)){
					SignUpOptionsActivity.logOutFromFaceBook();
				}
				
				SharedPreferencesUtil.clearAllSharedPreferencesList(getActivity());
				Intent intent = new Intent(getActivity(),
						SignUpOptionsActivity.class);
				getActivity().startActivity(intent);
			}
		});

		listView = (ListView) gameSettingView
				.findViewById(R.id.gameSettingList);
		final SeparatedListAdapter adapter = new SeparatedListAdapter(
				getActivity());
		adapter.addSection("Favourite Teams", new TopicMenuArrayAdapter(
				getActivity(), R.layout.common_list_row_item,
				favouriteTeamSectionList));
		adapter.addSection("Favourite Players", new TopicMenuArrayAdapter(
				getActivity(), R.layout.common_list_row_item,
				favouritePlayerSectionList));
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapterView,
					final View view, final int position, long arg3) {

				try {
					if (favouriteTeamSectionList
							.contains(favouriteTeamSectionList
									.get(position - 1))) {
						if (favouriteTeamSectionList.get(position - 1)
								.getCategoryType().equalsIgnoreCase("AddTeam")) {
							FragmentManager fragmentManager = getFragmentManager();
							FragmentTransaction ft = fragmentManager
									.beginTransaction();
							Fragment gameSettingsAddFavTeamsFragment = new GameSettingsAddFavTeamsFragment();
							ft.replace(R.id.menuFragment,
									gameSettingsAddFavTeamsFragment);
							ft.commit();
						} else if (favouriteTeamSectionList.get(position - 1)
								.getCategoryType()
								.equalsIgnoreCase("favouriteTeams")) {
							final ImageView deleteIcon = (ImageView) view
									.findViewById(R.id.ListRightImageView);
							deleteIcon.setVisibility(View.VISIBLE);
							deleteIcon
									.setOnClickListener(new View.OnClickListener() {

										@Override
										public void onClick(View v) {

											SharedPreferencesUtil
													.removeFavouriteFromSharedPreferencesList(
															(Context) getActivity(),
															favouriteTeamSectionList
																	.get(position - 1)
																	.getEntityId(),
															"team");

											FragmentManager fragmentManager = getFragmentManager();
											FragmentTransaction ft = fragmentManager
													.beginTransaction();
											Fragment gameSettingsFragment = new GameSettingsFragment();
											ft.replace(R.id.menuFragment,
													gameSettingsFragment);
											ft.commit();
										}
									});
						}
					}
				} catch (Exception e) {
					System.out
							.println("element is not present in favourite team section list");
				}

				try {
					final int positionInPlayerList = position - 2
							- favouriteTeamSectionList.size();
					if (favouritePlayerSectionList
							.contains(favouritePlayerSectionList
									.get(positionInPlayerList))) {
						if (favouritePlayerSectionList
								.get(positionInPlayerList).getCategoryType()
								.equalsIgnoreCase("AddPlayer")) {

							ft = fragmentManager.beginTransaction();
							addPlayersFragment = new AddPlayersFragment();
							Bundle bundle = new Bundle();
							bundle.putBoolean("showSettingFragment", true);
							addPlayersFragment.setArguments(bundle);
							ft.replace(R.id.menuFragment, addPlayersFragment);
							ft.commit();

						} else if (favouritePlayerSectionList
								.get(positionInPlayerList).getCategoryType()
								.equalsIgnoreCase("favouritePlayers")) {
							final ImageView deleteIcon = (ImageView) view
									.findViewById(R.id.ListRightImageView);
							deleteIcon.setVisibility(View.VISIBLE);
							deleteIcon
									.setOnClickListener(new View.OnClickListener() {

										@Override
										public void onClick(View v) {
											SharedPreferencesUtil
													.removeFavouriteFromSharedPreferencesList(
															(Context) getActivity(),
															favouritePlayerSectionList
																	.get(positionInPlayerList)
																	.getEntityId(),
															"player");

											FragmentManager fragmentManager = getFragmentManager();
											FragmentTransaction ft = fragmentManager
													.beginTransaction();
											Fragment gameSettingsFragment = new GameSettingsFragment();
											ft.replace(R.id.menuFragment,
													gameSettingsFragment);
											ft.commit();
										}
									});
						}

					}
				} catch (Exception e) {
					System.out
							.println("element is not present in favourite favouritePlayerSectionList"
									+ e);
				}
			}
		});
		listView.setAdapter(adapter);
		return gameSettingView;
	}

	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);
		final View newfragMenuHeader = getActivity().getLayoutInflater()
				.inflate(R.layout.game_setting_fragment_actionbar_header, null);
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

		Button back = (Button) actionBarLayout
				.findViewById(R.id.backToMainScreen);
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

	public OnItemClickListener listItemListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> adapterView, View view,
				final int position, long arg3) {
		}
	};

}
