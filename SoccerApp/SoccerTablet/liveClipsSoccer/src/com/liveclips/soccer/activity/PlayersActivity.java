package com.liveclips.soccer.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

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
import com.liveclips.soccer.fragment.AddPlayersFragment;
import com.liveclips.soccer.imageutils.ImageLoader;
import com.liveclips.soccer.model.PlayerItem;
import com.liveclips.soccer.popover.PopoverView;
import com.liveclips.soccer.popover.PopoverView.PopoverViewDelegate;
import com.liveclips.soccer.utils.PropertyReader;
import com.liveclips.soccer.utils.SoccerUtils;
import com.liveclips.soccer.utils.SharedPreferencesUtil;

public class PlayersActivity extends BaseActivity implements
		PopoverViewDelegate {

	protected Context context = PlayersActivity.this;
	public int favPlayersCount = 0;
	FragmentManager fragmentManager;
	Fragment mainMenuFragment, addPlayersFragment;
	FragmentTransaction ft;
	ImageLoader imgLoader;
	PopoverView popoverView;
	LinearLayout wrapper, emptyView;
	RelativeLayout favPlayerDetailHolder;
	private Activity activity;
	Properties appCommonProperties;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.players_activity);
		imgLoader = new ImageLoader(this);
		activity = this;
		fragmentManager = getFragmentManager();
		playCards();
		appCommonProperties = PropertyReader.getPropertiesFormAssetDirectory(
				"appcommonproperties.properties", activity);
		List<String> favouritePlayerIds = SharedPreferencesUtil
				.getFavouriteInSharedPreferencesList(context,
						appCommonProperties.getProperty("playerEntityName"));
		wrapper = (LinearLayout) findViewById(R.id.myPlayersContainer);
		if (!favouritePlayerIds.isEmpty()) {
			List<PlayerItem> PlayerList = new ArrayList<PlayerItem>();

			for (String favouritePlayerId : favouritePlayerIds) {
				PlayerItem player = new PlayerItem();
				player.setPlayerName(favouritePlayerId + "name");
				player.setPlayerDetails("#12 | QB");
				player.setPlayerGameDetails("20 / 29, 268 YDS");
				player.setPlayerImage("http://a.espncdn.com/combiner/i?img=/i/headshots/nfl/players/full/13229.png&w=350&h=254");
				PlayerList.add(player);
			}
			/** include a xml multiple time in a parent xml */

			
			for (PlayerItem playerItem : PlayerList) {

				{	int colors[] = SoccerUtils.getDefaultColorTheme();
					GradientDrawable gradientDrawable = new GradientDrawable(
							GradientDrawable.Orientation.TOP_BOTTOM, colors);
					final LinearLayout inflatedView = (LinearLayout) View
							.inflate(this, R.layout.common_players_detail, null);
					((RelativeLayout) inflatedView
							.findViewById(R.id.favPlayerDetailHolder))
							.setBackgroundDrawable(gradientDrawable);
					((TextView) inflatedView
							.findViewById(R.id.myindividualPlayerName))
							.setText(playerItem.getPlayerName());
					((TextView) inflatedView
							.findViewById(R.id.myindividualPlayerDetails))
							.setText(playerItem.getplayerDetails());
					((TextView) inflatedView
							.findViewById(R.id.myindividualPlayerGameDetails))
							.setText(playerItem.getPlayerGameDetails());
					ImageView playerImage = ((ImageView) inflatedView
							.findViewById(R.id.myindividualPlayerPic));
					imgLoader.DisplayImage(playerItem.getPlayerImage(), playerImage);
					ImageView myindividualPlayerFavouritePic = (ImageView) inflatedView
							.findViewById(R.id.myindividualPlayerFavouritePic);
					myindividualPlayerFavouritePic
							.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
										@Override
										public void onClick(
												DialogInterface dialog,
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
		String alertButtonText = SharedPreferencesUtil.getStringPreferences(
				activity, "alert_button_status");
		if (alertButtonText != null && !alertButtonText.isEmpty()) {
			alertButton.setText("Alerts: " + alertButtonText);
		} else {
			alertButton.setText("Alerts: "
					+ activity.getString(R.string.alert_level_first));
		}
		SoccerUtils.setAlertPopover(activity, popoverView, alertButton);
	}

	public void showAddPlayersFragment() {

		ft = fragmentManager.beginTransaction();
		addPlayersFragment = new AddPlayersFragment();
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
