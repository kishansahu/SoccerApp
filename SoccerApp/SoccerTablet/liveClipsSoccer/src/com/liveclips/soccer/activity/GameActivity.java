package com.liveclips.soccer.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.liveclips.soccer.R;
import com.liveclips.soccer.adapter.PlayerListViewAdapter;
import com.liveclips.soccer.adapter.ScheduleListViewAdapter;
import com.liveclips.soccer.adapter.SeparatedListAdapter;
import com.liveclips.soccer.adapter.StatsListViewAdapter;
import com.liveclips.soccer.commons.PlayCards;
import com.liveclips.soccer.database.DatabaseHelper;
import com.liveclips.soccer.dto.SoccerData;
import com.liveclips.soccer.model.PlayerItem;
import com.liveclips.soccer.model.ScheduleItem;
import com.liveclips.soccer.model.StatsItem;
import com.liveclips.soccer.model.TeamItem;
import com.liveclips.soccer.popover.PopoverView;
import com.liveclips.soccer.popover.PopoverView.PopoverViewDelegate;
import com.liveclips.soccer.utils.ImageProcessingUtil;
import com.liveclips.soccer.utils.SharedPreferencesUtil;
import com.liveclips.soccer.utils.SoccerUtils;
import com.liveclips.soccer.utils.UpdateTeamInformation;

public class GameActivity extends BaseActivity implements PopoverViewDelegate {

	protected Context context = GameActivity.this;
	ListView listView;
	PopoverView popoverView, popoverViewDrive;
	TextView allPlaysTextView, topPlaysTextView, topRatedTextView,
			watchAllTextView;
	RelativeLayout commonFragmentMenuHeader;
	TextView team1BtnPlayers, secondTeamSecondName, team2BtnPlayers;
	LayoutInflater layoutInflater;

	View fragmentMenuHeaderView, activityMenuHeaderView;
	int playCardVideoId = 0;
	LinearLayout statTab, playerTab, drivesTab, matchScoreBoardTabContainer;
	private Activity activity;
	private String leftSideTeamId = "cs_id";
	private String rightSideTeamId;
	private TextView schedulePopoverHeader;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_activity);
		ImageView RightSideTeamLargeIcon;
		activity = this;
		context = this;
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			rightSideTeamId = extras.getString("favouriteTeamId");
		} else {
			rightSideTeamId = "man_uni_id";
		}

		fullScreenView = (RelativeLayout) findViewById(R.id.fullScreenView);
		DatabaseHelper databaseHelper = new DatabaseHelper((Context) activity);
		TeamItem rightSideTeamItem = databaseHelper
				.getTeamInfoByTeamId(rightSideTeamId);
		RightSideTeamLargeIcon = (ImageView) findViewById(R.id.secondTeamLargeIcon);
		RightSideTeamLargeIcon.setImageDrawable(ImageProcessingUtil
				.getTeamLogoImageDrawableByTeamId(activity, rightSideTeamId));
		
		secondTeamSecondName = (TextView) findViewById(R.id.secondTeamSecondName);
		secondTeamSecondName.setText(rightSideTeamItem.getTeamName());
		
		allPlaysTextView = (TextView) findViewById(R.id.allPlaysId);
		topPlaysTextView = (TextView) findViewById(R.id.topPlaysId);
		topRatedTextView = (TextView) findViewById(R.id.topRatedId);
		watchAllTextView = (TextView) findViewById(R.id.watchAllId);
		
		statTab = (LinearLayout) findViewById(R.id.statTab);
		playerTab = (LinearLayout) findViewById(R.id.playerTab);
		drivesTab = (LinearLayout) findViewById(R.id.drivesTab);

		matchScoreBoardTabContainer = (LinearLayout) findViewById(R.id.matchScoreBoardTabContainer);
		
		allPlaysTextView.setOnClickListener(allPlaysClickListener);
		topPlaysTextView.setOnClickListener(topPlaysCilckListener);
		topRatedTextView.setOnClickListener(topRatedClickListener);
		watchAllTextView.setOnClickListener(watchAllClickListener);
		
		RelativeLayout matchScoreBoardBackground = (RelativeLayout) findViewById(R.id.matchScoreBoardBackground);
		matchScoreBoardBackground
				.setOnClickListener(matchScoreBoardBackgroundClickListener);

		createCustomActionBar();
		commonFragmentMenuHeader = (RelativeLayout) mActionBarView
				.findViewById(R.id.commonFragmentMenuHeader);
		
		fullScreenView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				screenHandelingForFragment();
				ActionBar actionBar = getActionBar();
				final RelativeLayout actionBarLayout = (RelativeLayout) actionBar
						.getCustomView();
				final RelativeLayout fragMenuHeader = (RelativeLayout) actionBarLayout
						.findViewById(R.id.fragmentMenuHeader);
				if (fragMenuHeader != null) {
					actionBarLayout.removeView(fragMenuHeader);
				}
				mActionBarView.findViewById(R.id.activityMenuHeader)
						.setVisibility(View.VISIBLE);
			}
		});
		PlayCards.getPlayCards(activity, context, "AllPlays");

	}

	private void screenHandelingForFragment() {
		commonFragmentMenuHeader.setVisibility(View.INVISIBLE);
		mActionBarView.findViewById(R.id.activityMenuHeader).setVisibility(
				View.VISIBLE);
		fragmentManager = getFragmentManager();
		fragmentTransaction = fragmentManager.beginTransaction();
		mainMenuFragment = fragmentManager.findFragmentById(R.id.menuFragment);
		if (mainMenuFragment.isVisible()) {
			fragmentTransaction.hide(mainMenuFragment);
			fragmentTransaction.commit();
			commonFragmentMenuHeader.setVisibility(View.INVISIBLE);
			sliderView.setVisibility(View.VISIBLE);
		}
		fullScreenView.setVisibility(View.INVISIBLE);

	}

	protected void createCustomActionBar() {

		ActionBar actionBar = getActionBar();
		mActionBarView = getLayoutInflater().inflate(R.layout.game_actionbar,
				null);
		actionBar.setCustomView(mActionBarView);
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

		fullScreenView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				commonFragmentMenuHeader = (RelativeLayout) mActionBarView
						.findViewById(R.id.commonFragmentMenuHeader);
				commonFragmentMenuHeader.setVisibility(View.INVISIBLE);
				mActionBarView.findViewById(R.id.activityMenuHeader)
						.setVisibility(View.VISIBLE);
				fragmentManager = getFragmentManager();
				fragmentTransaction = fragmentManager.beginTransaction();
				mainMenuFragment = fragmentManager
						.findFragmentById(R.id.menuFragment);
				if (mainMenuFragment.isVisible()) {

					fragmentTransaction.hide(mainMenuFragment);
					fragmentTransaction.commit();
					commonFragmentMenuHeader.setVisibility(View.INVISIBLE);
					sliderView.setVisibility(View.VISIBLE);
				}

				fullScreenView.setVisibility(View.INVISIBLE);
			}
		});

		View scheduleView = mActionBarView.findViewById(R.id.scheduleView);

		scheduleView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				RelativeLayout rootView = (RelativeLayout) findViewById(R.id.gameRootView);
				if (popoverView != null) {
					popoverView.dissmissPopover(false);
				}
				popoverView = new PopoverView(GameActivity.this,
						R.layout.game_popover_view_schedule);

				popoverView.setContentSizeForViewInPopover(new Point(
						SoccerUtils.convertDensityPixelToPixel(context, 400),
						SoccerUtils.convertDensityPixelToPixel(context, 460)));
				popoverView.setDelegate(GameActivity.this);
				View scheduleViewButton = (View) findViewById(R.id.scheduleView);
				popoverView.showPopoverFromRectInViewGroup(rootView,
						PopoverView.getFrameForView(scheduleViewButton),
						PopoverView.PopoverArrowDirectionUp, true);

			}
		});

		View statusView = mActionBarView.findViewById(R.id.statsView);

		statusView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				RelativeLayout rootView = (RelativeLayout) findViewById(R.id.gameRootView);
				if (popoverView != null) {
					popoverView.dissmissPopover(false);
				}
				popoverView = new PopoverView(GameActivity.this,
						R.layout.game_popover_view_stats);

				popoverView.setContentSizeForViewInPopover(new Point(
						SoccerUtils.convertDensityPixelToPixel(context, 400),
						SoccerUtils.convertDensityPixelToPixel(context, 460)));
				popoverView.setDelegate(GameActivity.this);
				View button = (View) findViewById(R.id.statsView);
				popoverView.showPopoverFromRectInViewGroup(rootView,
						PopoverView.getFrameForView(button),
						PopoverView.PopoverArrowDirectionUp, true);

			}
		});

		View playersView = mActionBarView.findViewById(R.id.playersView);

		playersView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				RelativeLayout rootView = (RelativeLayout) findViewById(R.id.gameRootView);
				if (popoverView != null) {
					popoverView.dissmissPopover(false);
				}
				popoverView = new PopoverView(GameActivity.this,
						R.layout.game_popover_view_player);

				popoverView.setContentSizeForViewInPopover(new Point(
						SoccerUtils.convertDensityPixelToPixel(context, 400),
						SoccerUtils.convertDensityPixelToPixel(context, 460)));
				popoverView.setDelegate(GameActivity.this);
				View button = (View) findViewById(R.id.playersView);
				popoverView.showPopoverFromRectInViewGroup(rootView,
						PopoverView.getFrameForView(button),
						PopoverView.PopoverArrowDirectionUp, true);

			}
		});
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

	OnClickListener matchScoreBoardBackgroundClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {

			if (SoccerUtils.isScoreBannerShrinked()) {
				enlargeScoreBanner();
			}
		}
	};

	private OnItemClickListener scheduleItemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parentView, View viewObject,
				int arg2, long arg3) {
			UpdateTeamInformation.updateTeamInfo(viewObject, activity, context);

			popoverView.removeAllViews();

		}

	};

	public void enlargeScoreBanner() {
		matchScoreBoardTabContainer.setVisibility(View.INVISIBLE);
		RelativeLayout matchScoreBoardBackground = (RelativeLayout) findViewById(R.id.matchScoreBoardBackground);
		matchScoreBoardBackground
				.setLayoutParams(new RelativeLayout.LayoutParams(
						LayoutParams.FILL_PARENT, SoccerUtils
								.convertPixelToDensityPixel(context, 160)));

		ImageView firstTeamIconImage = (ImageView) findViewById(R.id.firstTeamLargeIcon);
		firstTeamIconImage.setImageResource(ImageProcessingUtil
				.getTeamLogoImageResourceByTeamId(activity, leftSideTeamId));

		firstTeamIconImage.setLayoutParams(new LinearLayout.LayoutParams(
				SoccerUtils.convertPixelToDensityPixel(context, 130),
				LayoutParams.WRAP_CONTENT));

		ImageView secondTeamIconImage = (ImageView) findViewById(R.id.secondTeamLargeIcon);
		secondTeamIconImage.setImageResource(ImageProcessingUtil
				.getTeamLogoImageResourceByTeamId(activity, rightSideTeamId));
		secondTeamIconImage.setLayoutParams(new LinearLayout.LayoutParams(
				SoccerUtils.convertPixelToDensityPixel(context, 130),
				LayoutParams.WRAP_CONTENT));

		TextView firstTeamScore = (TextView) findViewById(R.id.firstTeamScore);
		firstTeamScore.setTextSize(SoccerUtils.convertPixelToDensityPixel(
				context, 45));

		TextView secondTeamScore = (TextView) findViewById(R.id.secondTeamScore);
		secondTeamScore.setTextSize(SoccerUtils.convertPixelToDensityPixel(
				context, 45));

		TextView gameQuarterTime = (TextView) findViewById(R.id.gameQuarterTime);
		gameQuarterTime.setTextSize(SoccerUtils.convertPixelToDensityPixel(
				context, 22));

		TextView firstTeamSecondName = (TextView) findViewById(R.id.firstTeamSecondName);
		firstTeamSecondName.setTextSize(SoccerUtils.convertPixelToDensityPixel(
				context, 26));

		TextView secondTeamSecondName = (TextView) findViewById(R.id.secondTeamSecondName);
		secondTeamSecondName.setTextSize(SoccerUtils
				.convertPixelToDensityPixel(context, 26));
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.RIGHT_OF,
				R.id.firstTeamLargeIconContainer);
		params.topMargin = (SoccerUtils.convertPixelToDensityPixel(context, 55));
		params.leftMargin = (SoccerUtils
				.convertPixelToDensityPixel(context, 25));
		LinearLayout firstTeamDescriptionContainer = (LinearLayout) findViewById(R.id.firstTeamDescriptionContainer);
		firstTeamDescriptionContainer.setLayoutParams(params);

		RelativeLayout.LayoutParams relParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		relParams.addRule(RelativeLayout.LEFT_OF,
				R.id.secondTeamLargeIconContainer);
		relParams.topMargin = (SoccerUtils.convertPixelToDensityPixel(context,
				55));
		relParams.rightMargin = (SoccerUtils.convertPixelToDensityPixel(
				context, 25));
		LinearLayout secondTeamDescriptionContainer = (LinearLayout) findViewById(R.id.secondTeamDescriptionContainer);
		secondTeamDescriptionContainer.setLayoutParams(relParams);
		SoccerUtils.setScoreBannerShrinked(false);

	}

	public void shrinkScoreBanner() {
		matchScoreBoardTabContainer.setVisibility(View.VISIBLE);
		RelativeLayout matchScoreBoardBackground = (RelativeLayout) findViewById(R.id.matchScoreBoardBackground);
		matchScoreBoardBackground.getLayoutParams().width = SoccerUtils
				.convertDensityPixelToPixel(context, 470);

		ImageView firstTeamIconImage = (ImageView) findViewById(R.id.firstTeamLargeIcon);
		firstTeamIconImage.setImageResource(ImageProcessingUtil
				.getTeamLogoImageResourceByTeamId(activity, leftSideTeamId));
		firstTeamIconImage.getLayoutParams().width = SoccerUtils
				.convertPixelToDensityPixel(context, 65);
		firstTeamIconImage.getLayoutParams().height = SoccerUtils
				.convertPixelToDensityPixel(context, 65);

		ImageView secondTeamIconImage = (ImageView) findViewById(R.id.secondTeamLargeIcon);
		secondTeamIconImage.setImageResource(ImageProcessingUtil
				.getTeamLogoImageResourceByTeamId(activity, rightSideTeamId));
		secondTeamIconImage.getLayoutParams().width = SoccerUtils
				.convertPixelToDensityPixel(context, 65);
		secondTeamIconImage.getLayoutParams().height = SoccerUtils
				.convertPixelToDensityPixel(context, 65);

		TextView firstTeamScore = (TextView) findViewById(R.id.firstTeamScore);
		firstTeamScore.setTextSize(SoccerUtils.convertPixelToDensityPixel(
				context, 25));

		TextView secondTeamScore = (TextView) findViewById(R.id.secondTeamScore);
		secondTeamScore.setTextSize(SoccerUtils.convertPixelToDensityPixel(
				context, 25));

		TextView gameQuarterTime = (TextView) findViewById(R.id.gameQuarterTime);
		gameQuarterTime.setTextSize(SoccerUtils.convertPixelToDensityPixel(
				context, 22));

		TextView firstTeamSecondName = (TextView) findViewById(R.id.firstTeamSecondName);
		firstTeamSecondName.setTextSize(SoccerUtils.convertPixelToDensityPixel(
				context, 20));

		TextView secondTeamSecondName = (TextView) findViewById(R.id.secondTeamSecondName);
		secondTeamSecondName.setTextSize(SoccerUtils
				.convertPixelToDensityPixel(context, 20));

		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM,
				R.id.firstTeamLargeIconContainer);
		params.addRule(RelativeLayout.ALIGN_PARENT_LEFT,
				R.id.firstTeamLargeIconContainer);
		params.bottomMargin = (SoccerUtils.convertPixelToDensityPixel(context,
				15));
		params.leftMargin = (SoccerUtils.convertPixelToDensityPixel(context, 5));

		LinearLayout firstTeamDescriptionContainer = (LinearLayout) findViewById(R.id.firstTeamDescriptionContainer);
		firstTeamDescriptionContainer.setLayoutParams(params);

		RelativeLayout.LayoutParams relParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		relParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM,
				R.id.secondTeamLargeIconContainer);
		relParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,
				R.id.secondTeamLargeIconContainer);
		relParams.bottomMargin = (SoccerUtils.convertPixelToDensityPixel(
				context, 15));
		params.rightMargin = (SoccerUtils.convertPixelToDensityPixel(context,
				10));
		LinearLayout secondTeamDescriptionContainer = (LinearLayout) findViewById(R.id.secondTeamDescriptionContainer);
		secondTeamDescriptionContainer.setLayoutParams(relParams);
		SoccerUtils.setScoreBannerShrinked(true);

	}

	@Override
	public void popoverViewWillShow(PopoverView view) {
		Log.i("POPOVER", "Will show : " + view.getChildCount());
	}

	public List<PlayerItem> getPlayers(String teamName, String teamType) {

		List<PlayerItem> playerList = null;

		if (teamName.equals("team1") && teamType.equals("offensive")) {
			playerList = SoccerData.getPlayers("team1", "offensive");
		} else if (teamName.equals("team1") && teamType.equals("defensive")) {
			playerList = SoccerData.getPlayers("team1", "defensive");
		} else if (teamName.equals("team2") && teamType.equals("offensive")) {
			playerList = SoccerData.getPlayers("team2", "offensive");
		} else if (teamName.equals("team2") && teamType.equals("defensive")) {
			playerList = SoccerData.getPlayers("team2", "defensive");
		}
		return playerList;
	}

	@Override
	public void popoverViewDidShow(PopoverView view) {

		Log.d("layoutId", String.valueOf(view.getLayoutId()));

		if (view.getLayoutId() == R.layout.game_popover_view_schedule) {

			schedulePopoverHeader = (TextView) findViewById(R.id.game_activity_schedule_popover_header);
			schedulePopoverHeader.setText("Arsenal Schedule");

			Log.d("id", String.valueOf(view.getId()));
			List<ScheduleItem> rowItems = new ArrayList<ScheduleItem>();
			rowItems = SoccerData.getSchedule();
			
			listView = (ListView) findViewById(R.id.game_schedule_list);
			ScheduleListViewAdapter adapter = new ScheduleListViewAdapter(this,
					R.layout.game_popover_list_row_item_schedule, rowItems);
			listView.setAdapter(adapter);
			listView.setOnItemClickListener(scheduleItemClickListener);

		} else if (view.getLayoutId() == R.layout.game_popover_view_stats) {
			TextView statFirstTeamLabel = (TextView) findViewById(R.id.statFirstTeam);
			statFirstTeamLabel.setText("Arsenal");

			TextView statSecondTeamLabel = (TextView) findViewById(R.id.statSecondTeam);
			statSecondTeamLabel.setText("Man-Unit");

			List<StatsItem> rowItemsForTeamStats = new ArrayList<StatsItem>();
			List<StatsItem> rowItemsForKeyPlays = new ArrayList<StatsItem>();
			
			rowItemsForTeamStats = SoccerData.getTeamStats();
			rowItemsForKeyPlays = SoccerData.getKeyPlaysStats();

			SeparatedListAdapter adapter = new SeparatedListAdapter(this);
			adapter.addSection("Team Plays", new StatsListViewAdapter(this,
					R.layout.game_popover_list_row_item_stats,
					rowItemsForTeamStats));
			adapter.addSection("Key Plays", new StatsListViewAdapter(this,
					R.layout.game_popover_list_row_item_stats,
					rowItemsForKeyPlays));
			listView = (ListView) findViewById(R.id.game_stats_list);
			listView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {

					if (!SoccerUtils.isScoreBannerShrinked()) {
						shrinkScoreBanner();
					}

					final LinearLayout inflatedView = (LinearLayout) View
							.inflate(
									context,
									R.layout.game_activity_statsyards_match_scoreboard,
									null);
					matchScoreBoardTabContainer.setVisibility(View.VISIBLE);
					matchScoreBoardTabContainer.removeAllViews();
					matchScoreBoardTabContainer.addView(inflatedView);

					TextView statType = (TextView) (arg1
							.findViewById(R.id.stat_type));

					TextView statScore1 = (TextView) (arg1
							.findViewById(R.id.statScore1));
					TextView statScore2 = (TextView) (arg1
							.findViewById(R.id.statScore2));

					TextView statCategoryTeamLabelInPopUp = (TextView) findViewById(R.id.statCategory);
					statCategoryTeamLabelInPopUp.setText(statType.getText());

					TextView firstTeamStatYardsScoreLabelInPopUp = (TextView) findViewById(R.id.firstTeamStatYardsScore);
					firstTeamStatYardsScoreLabelInPopUp.setText(statScore1
							.getText());

					TextView secondTeamStatYardsScoreLabelInPopUp = (TextView) findViewById(R.id.secondTeamStatYardsScore);
					secondTeamStatYardsScoreLabelInPopUp.setText(statScore2
							.getText());

					TextView firstTeamStatYardsWidthLabel = (TextView) findViewById(R.id.firstTeamStatYardsWidth);
					firstTeamStatYardsWidthLabel.setWidth(20 * Integer
							.parseInt(statScore1.getText().toString()));

					TextView secondTeamStatYardsWidthLabel = (TextView) findViewById(R.id.secondTeamStatYardsWidth);
					secondTeamStatYardsWidthLabel.setWidth(20 * Integer
							.parseInt(statScore2.getText().toString()));

					TextView statFirstTeamLabel = (TextView) findViewById(R.id.statFirstTeam);
					TextView firstTeamNameStatYardsCategoryLabel = (TextView) findViewById(R.id.firstTeamNameStatYardsCategory);
					firstTeamNameStatYardsCategoryLabel.setText("MU");

					TextView statSecondTeamLabel = (TextView) findViewById(R.id.statSecondTeam);
					TextView secondTeamNameStatYardsCategoryLabel = (TextView) findViewById(R.id.secondTeamNameStatYardsCategory);
					secondTeamNameStatYardsCategoryLabel.setText("AR");
					popoverView.dissmissPopover(false);
				}
			});
			listView.setAdapter(adapter);

		}

		else if (view.getLayoutId() == R.layout.game_popover_view_player) {

			// Offensive players for team1

			// Defensive playes for team1

			team1BtnPlayers = (TextView) findViewById(R.id.team1BtnPlayers);
			team1BtnPlayers.setText("Arsenal");

			team2BtnPlayers = (TextView) findViewById(R.id.team2BtnPlayers);
			team2BtnPlayers.setText("Man-Uni");

			final SeparatedListAdapter adapter = new SeparatedListAdapter(this);
			adapter.addSection("OFFENSE",
					new PlayerListViewAdapter(this,
							R.layout.game_popover_list_row__item_player,
							getPlayers("team1", "offensive")));
			adapter.addSection("DEFENSE",
					new PlayerListViewAdapter(GameActivity.this,
							R.layout.game_popover_list_row__item_player,
							getPlayers("team1", "defensive")));

			listView = (ListView) findViewById(R.id.game_player_list);

			listView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {

					if (!SoccerUtils.isScoreBannerShrinked()) {
						shrinkScoreBanner();
					}
					matchScoreBoardTabContainer.setVisibility(View.VISIBLE);
					matchScoreBoardTabContainer.removeAllViews();
					final LinearLayout inflatedView = (LinearLayout) View
							.inflate(
									context,
									R.layout.game_activity_team_player_match_scoreboard,
									null);
					matchScoreBoardTabContainer.addView(inflatedView);

					TextView popoverPlayerName = (TextView) (arg1
							.findViewById(R.id.popover_player_name));

					TextView selectedPlayerName = (TextView) findViewById(R.id.selectedPlayerName);
					selectedPlayerName.setText(popoverPlayerName.getText());

					TextView popoverPlayerDetails = (TextView) (arg1
							.findViewById(R.id.popover_player_details));

					TextView firstTeamStatYardsScoreLabelInPopUp = (TextView) findViewById(R.id.selectedPlayerDetails);
					firstTeamStatYardsScoreLabelInPopUp
							.setText(popoverPlayerDetails.getText());

					TextView popoverPlayerData1 = (TextView) (arg1
							.findViewById(R.id.popover_player_data1));

					TextView selectedPlayerGameDetailsIndexFirst = (TextView) findViewById(R.id.selectedPlayerGameDetailsIndexFirst);
					selectedPlayerGameDetailsIndexFirst
							.setText(popoverPlayerData1.getText());

					TextView popoverPlayerData2 = (TextView) (arg1
							.findViewById(R.id.popover_player_data2));

					TextView selectedPlayerGameDetailsIndexTwo = (TextView) findViewById(R.id.selectedPlayerGameDetailsIndexSecond);
					selectedPlayerGameDetailsIndexTwo
							.setText(popoverPlayerData2.getText());

					TextView popoverPlayerData3 = (TextView) (arg1
							.findViewById(R.id.popover_player_data3));

					TextView selectedPlayerGameDetailsIndexThree = (TextView) findViewById(R.id.selectedPlayerGameDetailsIndexThird);
					selectedPlayerGameDetailsIndexThree
							.setText(popoverPlayerData3.getText());

					TextView popoverPlayerData4 = (TextView) (arg1
							.findViewById(R.id.popover_player_data4));

					TextView selectedPlayerGameDetailsIndexFour = (TextView) findViewById(R.id.selectedPlayerGameDetailsIndexFour);
					selectedPlayerGameDetailsIndexFour
							.setText(popoverPlayerData4.getText());

					ImageView popover_player_pic = (ImageView) arg1
							.findViewById(R.id.popover_player_pic);

					ImageView selectedPlayerPic = (ImageView) findViewById(R.id.selectedPlayerPic);
					selectedPlayerPic.setImageDrawable(popover_player_pic
							.getDrawable());
					selectedPlayerPic.getLayoutParams().width = SoccerUtils
							.convertDensityPixelToPixel(context, 130);

					popoverView.dissmissPopover(false);
				}
			});

			listView.setAdapter(adapter);

			team1BtnPlayers = (TextView) findViewById(R.id.team1BtnPlayers);
			team1BtnPlayers.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					adapter.removeAllSections();
					adapter.addSection("OFFENSE", new PlayerListViewAdapter(
							GameActivity.this,
							R.layout.game_popover_list_row__item_player,
							getPlayers("team1", "offensive")));
					adapter.addSection("DEFENSE", new PlayerListViewAdapter(
							GameActivity.this,
							R.layout.game_popover_list_row__item_player,
							getPlayers("team1", "defensive")));
					adapter.notifyDataSetChanged();
					listView.setSelection(0);

					team1BtnPlayers.setTextColor(Color.WHITE);
					team2BtnPlayers.setTextColor(getResources().getColor(
							R.color.grey_light));
				}
			});

			team2BtnPlayers = (TextView) findViewById(R.id.team2BtnPlayers);
			team2BtnPlayers.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					adapter.removeAllSections();
					adapter.addSection("OFFENSE", new PlayerListViewAdapter(
							GameActivity.this,
							R.layout.game_popover_list_row__item_player,
							getPlayers("team2", "offensive")));
					adapter.addSection("DEFENSE", new PlayerListViewAdapter(
							GameActivity.this,
							R.layout.game_popover_list_row__item_player,
							getPlayers("team2", "defensive")));
					adapter.notifyDataSetChanged();
					listView.setSelection(0);

					team2BtnPlayers.setTextColor(Color.WHITE);
					team1BtnPlayers.setTextColor(getResources().getColor(
							R.color.grey_light));
				}
			});

		}
	}

	@Override
	public void popoverViewWillDismiss(PopoverView view) {
		Log.i("POPOVER", "Will dismiss");
	}

	@Override
	public void popoverViewDidDismiss(PopoverView view) {
		Log.i("POPOVER", "Did dismiss");
	}

	private OnClickListener allPlaysClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			allPlaysTextView.setTextColor(getResources()
					.getColor(R.color.white));
			topPlaysTextView.setTextColor(getResources().getColor(
					R.color.grey_light));
			topRatedTextView.setTextColor(getResources().getColor(
					R.color.grey_light));
			watchAllTextView.setTextColor(getResources().getColor(
					R.color.grey_light));
		}
	};

	private OnClickListener topPlaysCilckListener = new OnClickListener() {

		@Override
		public void onClick(View v) {

			topPlaysTextView.setTextColor(getResources()
					.getColor(R.color.white));
			allPlaysTextView.setTextColor(getResources().getColor(
					R.color.grey_light));
			topRatedTextView.setTextColor(getResources().getColor(
					R.color.grey_light));
			watchAllTextView.setTextColor(getResources().getColor(
					R.color.grey_light));

		}
	};

	private OnClickListener topRatedClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {

			topRatedTextView.setTextColor(getResources()
					.getColor(R.color.white));
			allPlaysTextView.setTextColor(getResources().getColor(
					R.color.grey_light));
			topPlaysTextView.setTextColor(getResources().getColor(
					R.color.grey_light));
			watchAllTextView.setTextColor(getResources().getColor(
					R.color.grey_light));

		}
	};

	private OnClickListener watchAllClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			watchAllTextView.setTextColor(getResources()
					.getColor(R.color.white));
			allPlaysTextView.setTextColor(getResources().getColor(
					R.color.grey_light));
			topPlaysTextView.setTextColor(getResources().getColor(
					R.color.grey_light));
			topRatedTextView.setTextColor(getResources().getColor(
					R.color.grey_light));

		}
	};

}