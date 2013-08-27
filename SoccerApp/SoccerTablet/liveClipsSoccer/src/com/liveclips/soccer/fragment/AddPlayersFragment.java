package com.liveclips.soccer.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.liveclips.soccer.R;
import com.liveclips.soccer.adapter.AddPlayerCategoriesBySettingsListAdapter;
import com.liveclips.soccer.database.DatabaseHelper;
import com.liveclips.soccer.model.LiveClipsContentListItem;
import com.liveclips.soccer.model.TeamItem;
import com.liveclips.soccer.utils.SharedPreferencesUtil;

public class AddPlayersFragment extends Fragment {

	View currentView;
	Activity currentActivity;
	 ArrayAdapter<String> adapterForCategoryList;
	static ArrayAdapter<String> adapterForSearchList;
	Context context;
	List<LiveClipsContentListItem> addPlayerContentList;
	String[] anySearchForPlayersMenuItems = { "Tom Brady", "Rob Gronkowski", };
	ListView findPLayerByCategoryListView;
	RelativeLayout fullScreenView;
	boolean showSettingFragment;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle saveInstantState) {

		return inflater.inflate(R.layout.players_fragment_view_by_categories,
				container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		context = (Context) getActivity();
		addPlayerContentList = new ArrayList<LiveClipsContentListItem>();
		fullScreenView = (RelativeLayout) getActivity().findViewById(R.id.fullScreenView);
		
		for (String teamId : SharedPreferencesUtil
				.getFavouriteInSharedPreferencesList(context, "team")) {
			TeamItem teamItem= new DatabaseHelper((Context) getActivity())
			.getTeamInfoByTeamId( teamId);
			LiveClipsContentListItem liveClipsContentListItem = new LiveClipsContentListItem();
			liveClipsContentListItem.setRowText(teamItem.getTeamName());
			liveClipsContentListItem.setEntityId(teamId);
			liveClipsContentListItem.setCategoryType("team");
			addPlayerContentList.add(liveClipsContentListItem);
		}
		LiveClipsContentListItem topPlayer = new LiveClipsContentListItem();
		topPlayer.setRowText("Top Players");
		topPlayer.setCategoryType("Top Players");
		addPlayerContentList.add(topPlayer);

		LiveClipsContentListItem playerByTeam = new LiveClipsContentListItem();
		playerByTeam.setRowText("By Team");
		playerByTeam.setCategoryType("By Team");
		addPlayerContentList.add(playerByTeam);

		LiveClipsContentListItem playerByPosition = new LiveClipsContentListItem();
		playerByPosition.setRowText("By Position");
		playerByPosition.setCategoryType("By Position");
		addPlayerContentList.add(playerByPosition);

		LiveClipsContentListItem playerByCollege = new LiveClipsContentListItem();
		playerByCollege.setRowText("By College");
		playerByCollege.setCategoryType("By College");
		addPlayerContentList.add(playerByCollege);

		final View newfragMenuHeader;

		ActionBar actionBar = getActivity().getActionBar();
		final RelativeLayout actionBarLayout = (RelativeLayout) actionBar
				.getCustomView();
		Bundle bundle = this.getArguments();
		
		if (bundle != null) {
			showSettingFragment = bundle.getBoolean("showSettingFragment",
					false);
		}
		if (showSettingFragment) {
			newfragMenuHeader = getActivity()
					.getLayoutInflater()
					.inflate(
							R.layout.setting_players_fragment_actionbar_header_find_player,
							null);

			ImageView backToSettingsFragmentButton = (ImageView) newfragMenuHeader
					.findViewById(R.id.backToSettingsFragment);
			backToSettingsFragmentButton.setVisibility(View.VISIBLE);
			backToSettingsFragmentButton
					.setOnClickListener(new View.OnClickListener() {

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
			
		} else {
			newfragMenuHeader = getActivity().getLayoutInflater().inflate(
					R.layout.players_fragment_actionbar_header_find_player,
					null);
		}

		final RelativeLayout activityHeaderLayout = (RelativeLayout) getActivity()
				.findViewById(R.id.activityMenuHeader);
		activityHeaderLayout.setVisibility(View.INVISIBLE);
		final RelativeLayout fragMenuHeader = (RelativeLayout) actionBarLayout
				.findViewById(R.id.fragmentMenuHeader);
		if (fragMenuHeader != null) {
			actionBarLayout.removeView(fragMenuHeader);
		}
		RelativeLayout commonfragMenuHeader = (RelativeLayout) actionBarLayout
				.findViewById(R.id.commonFragmentMenuHeader);
		if (commonfragMenuHeader != null) {
			commonfragMenuHeader.setVisibility(View.INVISIBLE);
		}
		actionBarLayout.addView(newfragMenuHeader, 300, 52);
		Button playerMenuDoneButton = (Button) actionBarLayout
				.findViewById(R.id.playerMenuDone);
		if (playerMenuDoneButton != null) {
			playerMenuDoneButton.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					FragmentManager fragmentManager = getActivity()
							.getFragmentManager();
					FragmentTransaction ft = fragmentManager.beginTransaction();
					Fragment frg = fragmentManager
							.findFragmentById(R.id.menuFragment);
					ft.hide(frg);
					ft.commit();
					actionBarLayout.removeView(newfragMenuHeader);
					View sliderView = activityHeaderLayout
							.findViewById(R.id.sliderView);
					if (sliderView.getVisibility() != View.VISIBLE)
						sliderView.setVisibility(View.VISIBLE);
					activityHeaderLayout.setVisibility(View.VISIBLE);
					fullScreenView.setVisibility(View.INVISIBLE);
				}
			});
		}
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onResume() {
		super.onResume();
		currentActivity = getActivity();
		findPLayerByCategoryListView = (ListView) getActivity().findViewById(
				R.id.findPLayerByCategoryListView);

		AddPlayerCategoriesBySettingsListAdapter addPlayerCategoriesBySettingsListAdapter = new AddPlayerCategoriesBySettingsListAdapter(
				addPlayerContentList, (Context) getActivity());

		findPLayerByCategoryListView
				.setAdapter(addPlayerCategoriesBySettingsListAdapter);

		adapterForSearchList = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1,
				anySearchForPlayersMenuItems);

		findPLayerByCategoryListView
				.setOnItemClickListener(findPLayerByCategoryListViewItemListener);

		EditText searchBarPlayer = (EditText) getActivity().findViewById(
				R.id.searchBarPlayerByCategory);
		searchBarPlayer.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence charSequence, int arg1,
					int arg2, int arg3) {
				// When user changed the Text
				AddPlayersFragment.adapterForSearchList.getFilter().filter(
						charSequence);
				findPLayerByCategoryListView.setAdapter(adapterForSearchList);
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {

				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable arg0) {

				if (arg0.length() == 0) {
					findPLayerByCategoryListView
							.setAdapter(adapterForCategoryList);
				}
			}
		});

		ActionBar actionBar = currentActivity.getActionBar();
		View customView = actionBar.getCustomView();

	}

	/**
	 * Category selected to choose player, to add player
	 */

	private OnItemClickListener findPLayerByCategoryListViewItemListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int position,
				long arg3) {
			LiveClipsContentListItem itemSelected = addPlayerContentList
					.get(position);
			FragmentManager fragmentManager = getFragmentManager();
			FragmentTransaction ft = fragmentManager.beginTransaction();
			if (itemSelected.getCategoryType().equalsIgnoreCase("team")) {
				Fragment addPlayerFromFavouriteTeamMenuFragment = new AddPlayerFromFavouriteTeamMenuFragment();
				Bundle bundle = new Bundle();
				bundle.putString("favouriteTeamId", itemSelected.getEntityId());
				bundle.putBoolean("showSettingFragment", showSettingFragment);
				
				addPlayerFromFavouriteTeamMenuFragment.setArguments(bundle);
				ft.replace(R.id.menuFragment,
						addPlayerFromFavouriteTeamMenuFragment);

			} else {

				// TextView selectedtextView = (TextView) arg1;
				// if (selectedtextView.getText().equals("By Team")) {

				Fragment addPlayerSelectedCategoryMenuFragment = new AddPlayerSelectedCategoryMenuFragment();
				ft.replace(R.id.menuFragment,
						addPlayerSelectedCategoryMenuFragment);

			}
			ft.addToBackStack(null);
			ft.commit();
		}

	};

}
