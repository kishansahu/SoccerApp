package com.liveclips.soccer.fragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.liveclips.soccer.R;
import com.liveclips.soccer.adapter.AddPlayerListAdapter;
import com.liveclips.soccer.model.PlayerItem;


public class ShowAvailablePlayersFragment extends Fragment {

	View currentView;
	Activity currentActivity;
	ListView addPlayersToFavListView;
	List playerForAddition;
	List<PlayerItem> playersList = null;
	AddPlayerListAdapter adapter;
	private String nameColumnSortOrder = "none";
	private String playerNumColumnSortOrder = "none";
	private String playerPosColumnSortOrder = "none";
	private String playerFavColumnSortOrder = "none";
	ImageView playerNameSortArrow, playerNumSortArrow, playerPosSortArrow,
			playerFavSortArrow;
	RelativeLayout playerNameinAddInFavouriteBanner,
			playerNumSubheaderinAddInFavouriteBanner,
			playerPosSubheaderinAddInFavouriteBanner,
			playerFavSubheaderinAddInFavouriteBanner;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle saveInstantState) {

		return inflater.inflate(R.layout.players_fragment_view_favorites_team_player,
				container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		final View newfragMenuHeader = getActivity().getLayoutInflater().inflate(
				R.layout.players_fragment_actionbar_header_show_available_players, null);
		ActionBar actionBar = getActivity().getActionBar();
		final RelativeLayout actionBarLayout = (RelativeLayout) actionBar
				.getCustomView();
		final RelativeLayout activityHeaderLayout = (RelativeLayout) getActivity()
				.findViewById(R.id.activityMenuHeader);
		RelativeLayout fragMenuHeader = (RelativeLayout) actionBarLayout
				.findViewById(R.id.fragmentMenuHeader);
		actionBarLayout.removeView(fragMenuHeader);
		actionBarLayout.addView(newfragMenuHeader, 300, 52);
		Button playerMenuDoneButton = (Button) actionBarLayout.findViewById(
				R.id.playerAddToFavDoneButton);
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
				View commonfragMenuHeader = getActivity().getLayoutInflater()
						.inflate(R.layout.common_fragment_menu_header, null);
				actionBarLayout.addView(commonfragMenuHeader, 300, 52);
			}
		});

		Button backToPlayerCategoryFragmentButton = (Button) actionBarLayout
				.findViewById(R.id.backToPlayerCategoryFragment);
		backToPlayerCategoryFragmentButton
				.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						FragmentManager fragmentManager = getFragmentManager();
						fragmentManager.popBackStack();
						/*FragmentTransaction ft = fragmentManager
								.beginTransaction();
						Fragment addPlayerSelectedCategoryMenuFragment = new AddPlayerSelectedCategoryMenuFragment();
						ft.replace(R.id.menuFragment,
								addPlayerSelectedCategoryMenuFragment);
						ft.commit();*/

					}
				});

		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onStart() {
		super.onStart();
		playerForAddition = getPlayersForAddition("teamName");
		adapter = new AddPlayerListAdapter(getActivity(), playerForAddition);

		addPlayersToFavListView = (ListView) getActivity().findViewById(
				R.id.addPlayersToFavListView);
		addPlayersToFavListView.setAdapter(adapter);
		playerNameinAddInFavouriteBanner = (RelativeLayout) getActivity()
				.findViewById(R.id.playerNameinAddInFavouriteBanner);
		playerNameinAddInFavouriteBanner
				.setOnClickListener(nameColumnClickedListener);

		playerNumSubheaderinAddInFavouriteBanner = (RelativeLayout) getActivity()
				.findViewById(R.id.playerNuminAddInFavouriteBanner);
		playerNumSubheaderinAddInFavouriteBanner
				.setOnClickListener(playerNumColumnClickedListener);

		playerPosSubheaderinAddInFavouriteBanner = (RelativeLayout) getActivity()
				.findViewById(R.id.playerPosinAddInFavouriteBanner);
		playerPosSubheaderinAddInFavouriteBanner
				.setOnClickListener(playerPosColumnClickedListener);

		playerFavSubheaderinAddInFavouriteBanner = (RelativeLayout) getActivity()
				.findViewById(R.id.playerFavinAddInFavouriteBanner);
		playerFavSubheaderinAddInFavouriteBanner
				.setOnClickListener(playerFavColumnClickedListener);

		playerNameSortArrow = (ImageView) getActivity().findViewById(
				R.id.playerNameSortArrow);
		playerNumSortArrow = (ImageView) getActivity().findViewById(
				R.id.playerNumSortArrow);
		playerPosSortArrow = (ImageView) getActivity().findViewById(
				R.id.playerPosSortArrow);
		playerFavSortArrow = (ImageView) getActivity().findViewById(
				R.id.playerFavSortArrow);

		/*
		 * Button playerMenuDoneButton = (Button) getActivity().findViewById(
		 * R.id.playerAddToFavDoneButton);
		 * playerMenuDoneButton.setOnClickListener(new OnClickListener() {
		 * 
		 * @Override public void onClick(View v) { FragmentManager
		 * fragmentManager = getActivity() .getFragmentManager();
		 * FragmentTransaction ft = fragmentManager.beginTransaction(); Fragment
		 * frg = fragmentManager .findFragmentById(R.id.menuFragment);
		 * ft.hide(frg); ft.commit(); } });
		 * 
		 * Button backToPlayerCategoryFragmentButton = (Button) getActivity()
		 * .findViewById(R.id.backToPlayerCategoryFragment);
		 * backToPlayerCategoryFragmentButton .setOnClickListener(new
		 * View.OnClickListener() {
		 * 
		 * @Override public void onClick(View v) { FragmentManager
		 * fragmentManager = getFragmentManager(); FragmentTransaction ft =
		 * fragmentManager .beginTransaction(); Fragment
		 * addPlayerSelectedCategoryMenuFragment = new
		 * AddPlayerSelectedCategoryMenuFragment();
		 * ft.replace(R.id.menuFragment, addPlayerSelectedCategoryMenuFragment);
		 * ft.commit();
		 * 
		 * } });
		 */
	}

	public List<PlayerItem> getPlayersForAddition(String teamName) {

		final List<PlayerItem> playerList = new ArrayList<PlayerItem>();
		String playerNames[] = { "Ballard, Jake", "Aiken, Danny",
				"Allen, Will", "Arrignton, Kyle", "Barrett, Josh",
				"Bequette, Jake", "Bolden, Brandon", "Brace, Ron" };
		String playerNumber[] = { "48", "26", "24", "88", "30", "92", "38",
				"97" };
		String playerPosition[] = { "LS", "DB", "CB", "TE", "SS", "DE", "RB",
				"DT" };

		boolean isPlayerFavouriteActive[] = { false, false, true, false, false,
				true, false, false };
		PlayerItem item;

		for (int i = 0; i < playerNames.length; i++) {
			item = new PlayerItem();
			item.setPlayerName(playerNames[i]);
			item.setPlayerPosition(playerPosition[i]);
			item.setPlayerNumber(playerNumber[i]);
			item.setPLayerFavouriteActive(isPlayerFavouriteActive[i]);
			playerList.add(item);
		}
		playersList = playerList;
		return playersList;
	}

	OnClickListener nameColumnClickedListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			hideSortingIcons();
			playerNameSortArrow.setVisibility(View.VISIBLE);
			if (nameColumnSortOrder.equalsIgnoreCase("none")
					|| nameColumnSortOrder.equalsIgnoreCase("descending")) {
				// set in Ascending Order
				Collections.sort(playersList,
						new PlayerItem.playerNameInAscendingOrder());
				adapter = new AddPlayerListAdapter(getActivity(), playersList);
				addPlayersToFavListView.setAdapter(adapter);
				playerNameSortArrow
						.setImageResource(R.drawable.icon_popover_arrow_up);
				nameColumnSortOrder = "ascending";

			} else if (nameColumnSortOrder.equalsIgnoreCase("ascending")) {
				// set in Descending Order
				Collections.sort(playersList,
						new PlayerItem.playerNameInDescendingOrder());
				adapter = new AddPlayerListAdapter(getActivity(), playersList);
				addPlayersToFavListView.setAdapter(adapter);
				playerNameSortArrow
						.setImageResource(R.drawable.icon_popover_arrow_down);
				nameColumnSortOrder = "descending";
			}
		}
	};

	OnClickListener playerNumColumnClickedListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			hideSortingIcons();
			playerNumSortArrow.setVisibility(View.VISIBLE);
			// set player Name in alphabetical order
			Collections.sort(playersList,
					new PlayerItem.playerNameInAscendingOrder());

			if (playerNumColumnSortOrder.equalsIgnoreCase("none")
					|| playerNumColumnSortOrder.equalsIgnoreCase("descending")) {
				// set in Ascending Order
				Collections.sort(playersList,
						new PlayerItem.playerNumInAscendingOrder());
				adapter = new AddPlayerListAdapter(getActivity(), playersList);
				addPlayersToFavListView.setAdapter(adapter);
				playerNumSortArrow
						.setImageResource(R.drawable.icon_popover_arrow_up);
				playerNumColumnSortOrder = "ascending";

			} else if (playerNumColumnSortOrder.equalsIgnoreCase("ascending")) {
				// set in Descending Order
				Collections.sort(playersList,
						new PlayerItem.playerNumInDescendingOrder());
				adapter = new AddPlayerListAdapter(getActivity(), playersList);
				addPlayersToFavListView.setAdapter(adapter);
				playerNumSortArrow
						.setImageResource(R.drawable.icon_popover_arrow_down);
				playerNumColumnSortOrder = "descending";
			}

		}
	};

	OnClickListener playerPosColumnClickedListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			hideSortingIcons();
			playerPosSortArrow.setVisibility(View.VISIBLE);
			// set player Name in alphabetical order
			Collections.sort(playersList,
					new PlayerItem.playerNameInAscendingOrder());

			if (playerPosColumnSortOrder.equalsIgnoreCase("none")
					|| playerPosColumnSortOrder.equalsIgnoreCase("descending")) {
				// set in Ascending Order
				Collections.sort(playersList,
						new PlayerItem.playerPosInAscendingOrder());
				adapter = new AddPlayerListAdapter(getActivity(), playersList);
				addPlayersToFavListView.setAdapter(adapter);
				playerPosSortArrow
						.setImageResource(R.drawable.icon_popover_arrow_up);
				playerPosColumnSortOrder = "ascending";

			} else if (playerPosColumnSortOrder.equalsIgnoreCase("ascending")) {
				// set in Descending Order
				Collections.sort(playersList,
						new PlayerItem.playerPosInDescendingOrder());
				adapter = new AddPlayerListAdapter(getActivity(), playersList);
				addPlayersToFavListView.setAdapter(adapter);
				playerPosSortArrow
						.setImageResource(R.drawable.icon_popover_arrow_down);
				playerPosColumnSortOrder = "descending";
			}

		}
	};

	OnClickListener playerFavColumnClickedListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			hideSortingIcons();
			playerFavSortArrow.setVisibility(View.VISIBLE);
			// set player Name in alphabetical order
			Collections.sort(playersList,
					new PlayerItem.playerNameInAscendingOrder());

			if (playerFavColumnSortOrder.equalsIgnoreCase("descending")) {
				// set in Ascending Order
				Collections.sort(playersList,
						new PlayerItem.playerFavInAscendingOrder());
				adapter = new AddPlayerListAdapter(getActivity(), playersList);
				addPlayersToFavListView.setAdapter(adapter);
				playerFavSortArrow
						.setImageResource(R.drawable.icon_popover_arrow_up);
				playerFavColumnSortOrder = "ascending";

			} else if (playerFavColumnSortOrder.equalsIgnoreCase("none")
					|| playerFavColumnSortOrder.equalsIgnoreCase("ascending")) {
				// set in Descending Order
				Collections.sort(playersList,
						new PlayerItem.playerFavInDescendingOrder());
				adapter = new AddPlayerListAdapter(getActivity(), playersList);
				addPlayersToFavListView.setAdapter(adapter);
				playerFavSortArrow
						.setImageResource(R.drawable.icon_popover_arrow_down);
				playerFavColumnSortOrder = "descending";
			}

		}
	};

	private void hideSortingIcons() {
		if (playerNameSortArrow.getVisibility() == View.VISIBLE) {
			playerNameSortArrow.setVisibility(View.GONE);
		}
		if (playerNumSortArrow.getVisibility() == View.VISIBLE) {
			playerNumSortArrow.setVisibility(View.GONE);
		}
		if (playerPosSortArrow.getVisibility() == View.VISIBLE) {
			playerPosSortArrow.setVisibility(View.GONE);
		}
		if (playerFavSortArrow.getVisibility() == View.VISIBLE) {
			playerFavSortArrow.setVisibility(View.GONE);
		}
	}
}
