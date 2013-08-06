package com.liveclips.soccer.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.liveclips.soccer.R;
import com.liveclips.soccer.imageutils.ImageLoader;
import com.liveclips.soccer.popover.PopoverView;
import com.liveclips.soccer.popover.PopoverView.PopoverViewDelegate;
import com.liveclips.soccer.utils.SoccerUtils;
import com.liveclips.soccer.utils.SharedPreferencesUtil;


public class PlayersActivity extends BaseActivity implements PopoverViewDelegate{

	protected Context context = PlayersActivity.this;
	public int favPlayersCount = 0;
	FragmentManager fragmentManager;
	Fragment mainMenuFragment, addPlayersFragment;
	FragmentTransaction ft;
	ImageLoader imgLoader;
	PopoverView popoverView;
	LinearLayout wrapper, emptyView;
	RelativeLayout favPlayerDetailHolder;
	private Activity playerActivity;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.players_activity);
		/*DatabaseHelper a = new DatabaseHelper(this);
		List<TeamItem> b = a.getAllTeams();
		System.out.println(b.toString());
		*/
		imgLoader = new ImageLoader(this);
		playerActivity = this;
		fragmentManager = getFragmentManager();
		playCards();

		String[] playersName = { "Tom Brady", "Rob Gronkowski" };
		String[] teamCode = { "gb", "blt" };
		String[] playersDetails = { "#12 | QB", "#87 | TE" };
		String[] playerGameDetails = { "20 / 29, 268 YDS,2 TD, 1 INT",
				"5 REC, 56 YDS, 2 TD" };
		String[] playerPicUrl = {
				"http://172.16.9.39:8081/nfl-images/nfl_players_images/00-0000108.png",
				"http://a.espncdn.com/combiner/i?img=/i/headshots/nfl/players/full/13229.png&w=350&h=254" };

		/**
		 * include a xml multiple time in a parent xml
		 */

		wrapper = (LinearLayout) findViewById(R.id.myPlayersContainer);
		favPlayersCount = playersName.length;
		if (playersName.length != 0) {
			int i;
			for (i = 0; i < playersName.length; i++) {

				int colors[] = SoccerUtils.getDefaultColorTheme();
				GradientDrawable gradientDrawable = new GradientDrawable(
						GradientDrawable.Orientation.TOP_BOTTOM, colors);
				final LinearLayout inflatedView = (LinearLayout) View.inflate(
						this, R.layout.common_players_detail, null);
				((RelativeLayout) inflatedView
						.findViewById(R.id.favPlayerDetailHolder))
						.setBackgroundDrawable(gradientDrawable);
				((TextView) inflatedView
						.findViewById(R.id.myindividualPlayerName))
						.setText(playersName[i]);
				((TextView) inflatedView
						.findViewById(R.id.myindividualPlayerDetails))
						.setText(playersDetails[i]);
				((TextView) inflatedView
						.findViewById(R.id.myindividualPlayerGameDetails))
						.setText(playerGameDetails[i]);
				ImageView playerImage = ((ImageView) inflatedView
						.findViewById(R.id.myindividualPlayerPic));
				imgLoader.DisplayImage(playerPicUrl[i], playerImage);
				ImageView myindividualPlayerFavouritePic = (ImageView) inflatedView
						.findViewById(R.id.myindividualPlayerFavouritePic);
				myindividualPlayerFavouritePic
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,
											int which) {

										switch (which) {
										case DialogInterface.BUTTON_POSITIVE:
											// remove player
											favPlayerDetailHolder = (RelativeLayout) inflatedView
													.findViewById(R.id.favPlayerDetailHolder);
											favPlayerDetailHolder
													.setVisibility(View.GONE);
											View favPlayerDetailHolderSeperator = (View) inflatedView
													.findViewById(R.id.favPlayerDetailHolderSeperator);
											favPlayerDetailHolderSeperator
													.setVisibility(View.GONE);
											favPlayersCount = favPlayersCount - 1;
											if (favPlayersCount == 0) {
												showEmptyMyPlayerBanner(wrapper);
											}

											break;

										case DialogInterface.BUTTON_NEGATIVE:
											// No button clicked
											break;
										}
									}

								};
								AlertDialog.Builder builder = new AlertDialog.Builder(
										context);
								String message = "Do you really want to remove this player from My Players ?";
								builder.setMessage(message)
										.setPositiveButton("Yes",
												dialogClickListener)
										.setNegativeButton("No",
												dialogClickListener).show();
							}
						});

				wrapper.addView(inflatedView);

			}
		} else {
			showEmptyMyPlayerBanner(wrapper);
		}

		ImageView addaplayerButton = (ImageView) findViewById(R.id.addaplayerButton);
		addaplayerButton.setOnClickListener((new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				showAddPlayersFragment();

			}
		}));

		createCustomActionBar();

	}

	public void showEmptyMyPlayerBanner(LinearLayout wrapper) {
		RelativeLayout allMyPlayersIcon = (RelativeLayout) findViewById(R.id.myPlayersBannerContainer);
		allMyPlayersIcon.setVisibility(View.GONE);
		emptyView = (LinearLayout) View.inflate(this,
				R.layout.players_activity_empty_myplayers_banner, null);
		wrapper.addView(emptyView);
	}

	protected void createCustomActionBar() {

		ActionBar actionBar = getActionBar();
		View mActionBarView = getLayoutInflater().inflate(
				R.layout.players_actionbar, null);
		actionBar.setCustomView(mActionBarView);
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
				
		Button alertButton = (Button) mActionBarView
				.findViewById(R.id.alertButton);
		String alertButtonText = SharedPreferencesUtil.getStringPreferences(playerActivity, "alert_button_status");
		if(!alertButtonText.isEmpty() && alertButtonText!=null){
			alertButton.setText("Alerts: " +alertButtonText);
		}else{
			alertButton.setText("Alerts: " + playerActivity.getString(R.string.alert_level_first));
		}
		SoccerUtils.setAlertPopover(playerActivity, popoverView, alertButton);
	}

	public void showAddPlayersFragment() {

		ft = fragmentManager.beginTransaction();
	//	addPlayersFragment = new AddPlayersFragment();
		ft.replace(R.id.menuFragment, addPlayersFragment);
		ft.commit();

	}

	private void playCards() {
		
	}

	

	@Override
	public void popoverViewWillShow(PopoverView view) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void popoverViewDidShow(PopoverView view) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void popoverViewWillDismiss(PopoverView view) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void popoverViewDidDismiss(PopoverView view) {
		// TODO Auto-generated method stub
		
	}
}
