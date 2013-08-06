package com.liveclips.soccer.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.liveclips.soccer.R;
import com.liveclips.soccer.adapter.AddPlayerCategoriesBySettingsListAdapter;
import com.liveclips.soccer.adapter.SeparatedListAdapter;
import com.liveclips.soccer.model.LiveClipsContentListItem;
import com.liveclips.soccer.utils.ImageProcessingUtil;
import com.liveclips.soccer.utils.SharedPreferencesUtil;


public class AddPlayerFromFavouriteTeamMenuFragment extends Fragment {

	ListView findPLayerByCategoryListView;
	String favouriteTeamId;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle saveInstantState) {
		
		Bundle args = getArguments();
	    if(args != null) {
	    	favouriteTeamId = args.getString("favouriteTeamId");
	    }
		/*
		if (bundle != null) {
		    //favouriteTeamId = saveInstantState.getBundle("favouriteTeamId");
		//	bundle.gets
		}		
		*/return inflater.inflate(R.layout.setting_players_fragment_view_by_favteam,
				container, false);

	}


	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		
		View activityHeaderView = getActivity().getLayoutInflater().inflate(
				R.layout.setting_players_fragment_actionbar_header_selected_favourite_team, null);
		ActionBar actionBar = getActivity().getActionBar();
		RelativeLayout actionBarLayout = (RelativeLayout) actionBar.getCustomView();
		RelativeLayout fragMenuHeader = (RelativeLayout)actionBarLayout.findViewById(R.id.fragmentMenuHeader);
		actionBarLayout.removeView(fragMenuHeader);
		actionBarLayout.addView(activityHeaderView, 300, 52);
		final ImageView backToAddPlayerFragmentButton = (ImageView) activityHeaderView
				.findViewById(R.id.backToAddPlayerFragment);
		backToAddPlayerFragmentButton
				.setOnClickListener(new View.OnClickListener() {
				

					@Override
					public void onClick(View v) {
						FragmentManager fragmentManager = getFragmentManager();
						fragmentManager.popBackStack();
						FragmentTransaction ft = fragmentManager
								.beginTransaction();
						Bundle bundle = new Bundle();
						bundle.putBoolean("showSettingFragment", true);
						AddPlayersFragment addPlayersFragment = new AddPlayersFragment();
						addPlayersFragment.setArguments(bundle);
						ft.replace(R.id.menuFragment, addPlayersFragment);
						ft.commit();

					}
				});
		super.onActivityCreated(savedInstanceState);
	}
	
	
	@Override
	public void onStart() {

		super.onStart();
		List<String> playersIdList = SharedPreferencesUtil.getFavouriteInSharedPreferencesList((Context)getActivity(), "player");
		//quarterBackTeam
		List<String> quarterBackPlayers= new ArrayList<String>();
		quarterBackPlayers.add("12 Tom Brady");
		quarterBackPlayers.add("15 Ryan Mallett");
		quarterBackPlayers.add("5 Tim Tebow");
		
		//runningbackteam
		List<String> runningBackPlayers= new ArrayList<String>();
		runningBackPlayers.add("39 Ben Bartholomew");
		runningBackPlayers.add("29 LeGarrette Blout");
		runningBackPlayers.add("38 Brandon Bolden");
		runningBackPlayers.add("46 James Develin");
		runningBackPlayers.add("22 Steven Ridley");
		
		
		List<LiveClipsContentListItem> quarterBackaddPlayerContentList = new ArrayList<LiveClipsContentListItem>(); 
		List<LiveClipsContentListItem> runningBackaddPlayerContentList = new ArrayList<LiveClipsContentListItem>(); 
		
		
		for(String player: quarterBackPlayers){
			LiveClipsContentListItem quarterBackaddPlayerContent = new LiveClipsContentListItem();
			quarterBackaddPlayerContent.setCategoryType("player");
			quarterBackaddPlayerContent.setRowText(player);
			quarterBackaddPlayerContent.setEntityId(player.trim());
			if(playersIdList.contains(quarterBackaddPlayerContent.getEntityId())){
				quarterBackaddPlayerContent.setUsersFavourite(true);
			}
			quarterBackaddPlayerContent.setUpdateInSharedPreference(true);
			quarterBackaddPlayerContentList.add(quarterBackaddPlayerContent);
		}
		
		for(String player: runningBackPlayers){
			LiveClipsContentListItem runningBackaddPlayerContent = new LiveClipsContentListItem();
			runningBackaddPlayerContent.setCategoryType("player");
			runningBackaddPlayerContent.setRowText(player);
			runningBackaddPlayerContent.setEntityId(player.trim());
			if(playersIdList.contains(runningBackaddPlayerContent.getEntityId())){
				runningBackaddPlayerContent.setUsersFavourite(true);
			}
			runningBackaddPlayerContent.setUpdateInSharedPreference(true);
			runningBackaddPlayerContentList.add(runningBackaddPlayerContent);
		}
		
		
		SeparatedListAdapter adapter = new SeparatedListAdapter(getActivity());
		adapter.addSection("Quarter backs", new AddPlayerCategoriesBySettingsListAdapter(quarterBackaddPlayerContentList, (Context)getActivity()));
		adapter.addSection("Running backs", new AddPlayerCategoriesBySettingsListAdapter(runningBackaddPlayerContentList, (Context)getActivity()));
		//adapter.addSection("Wide Recievers", new AddPlayerCategoriesBySettingsListAdapter(addPlayerContentList, (Context)getActivity()));
		
		findPLayerByCategoryListView = (ListView) getActivity().findViewById(
				R.id.findPLayerByCategoryListView);
		ImageView favouriteTeamLogo = (ImageView) getActivity().findViewById(R.id.setting_addplayer_favteam_Logo);
		favouriteTeamLogo.setImageDrawable(ImageProcessingUtil.getTeamLogoImageDrawableByTeamId(getActivity(), favouriteTeamId));
		
		TextView favTeamHeader = (TextView)  getActivity().findViewById(R.id.setting_addplayer_favteam_Header);
		favTeamHeader.setText(favouriteTeamId);
		/* On click of category specific player selection*/
		findPLayerByCategoryListView
				.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int position, long arg3) {
						
					
						/*FragmentManager fragmentManager = getFragmentManager();
						FragmentTransaction ft = fragmentManager
								.beginTransaction();

						ShowAvailablePlayersFragment showAvailablePlayersFragment = new ShowAvailablePlayersFragment();
						ft.replace(R.id.menuFragment,
								showAvailablePlayersFragment);
						ft.addToBackStack(null);
						ft.commit();
*/
					}
				});

		findPLayerByCategoryListView.setAdapter(adapter);

	}

}
