package com.liveclips.soccer.activity;

import java.io.InputStream;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
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
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.liveclips.soccer.R;
import com.liveclips.soccer.commons.PlayCards;
import com.liveclips.soccer.popover.PopoverView;
import com.liveclips.soccer.popover.PopoverView.PopoverViewDelegate;
import com.liveclips.soccer.utils.SharedPreferencesUtil;
import com.liveclips.soccer.utils.SoccerUtils;



public class GameActivity extends BaseActivity implements PopoverViewDelegate {

	protected Context context = GameActivity.this;
	FragmentManager fragmentManager;
	Fragment mainMenuFragment;
	FragmentTransaction ft;
	boolean showSlider;
	ListView listView;
	PopoverView popoverView;
	PopoverView popoverViewDrive;
	TextView allPlaysTextView;
	TextView topPlaysTextView;
	TextView topRatedTextView;
	TextView watchAllTextView;
	RelativeLayout playCardLayout;
	VideoView playCardVideoView;
	TextView team1BtnPlayers;
	TextView team2BtnPlayers;
	LayoutInflater layoutInflater;
	View fragmentMenuHeaderView;
	View activityMenuHeaderView;
	int playCardVideoId = 0;
	LinearLayout statTab, playerTab, drivesTab, matchScoreBoardTabContainer;
	private Activity activity;
	private String leftSideTeamId = "cs_id";
	private String rightSideTeamId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_activity);

		activity = this;
		context = this;
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
		    rightSideTeamId = extras.getString("favouriteTeamId");
		}else{
			rightSideTeamId = "ks_id";
		}
		
		
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

		PlayCards.getPlayCards(activity,context, "AllPlays");
//		playCards();

	}

	protected void createCustomActionBar() {

		ActionBar actionBar = getActionBar();
		View mActionBarView = getLayoutInflater().inflate(
				R.layout.game_actionbar, null);
		actionBar.setCustomView(mActionBarView);
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		
		View scheduleView = mActionBarView.findViewById(R.id.scheduleView);

		scheduleView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {/*
				RelativeLayout rootView = (RelativeLayout) findViewById(R.id.gameRootView);
				if (popoverView != null) {
					popoverView.dissmissPopover(false);
				}
				popoverView = new PopoverView(GameActivity.this,
						R.layout.game_popover_view_schedule);

				popoverView.setContentSizeForViewInPopover(new Point(NflUtils
						.convertDensityPixelToPixel(context, 400), NflUtils
						.convertDensityPixelToPixel(context, 460)));
				popoverView.setDelegate(GameActivity.this);
				View button = (View) findViewById(R.id.scheduleView);
				popoverView.showPopoverFromRectInViewGroup(rootView,
						PopoverView.getFrameForView(button),
						PopoverView.PopoverArrowDirectionUp, true);

			*/}
		});

		View statusView = mActionBarView.findViewById(R.id.statsView);

		statusView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {/*
				RelativeLayout rootView = (RelativeLayout) findViewById(R.id.gameRootView);
				if (popoverView != null) {
					popoverView.dissmissPopover(false);
				}
				popoverView = new PopoverView(GameActivity.this,
						R.layout.game_popover_view_stats);

				popoverView.setContentSizeForViewInPopover(new Point(NflUtils
						.convertDensityPixelToPixel(context, 400), NflUtils
						.convertDensityPixelToPixel(context, 460)));
				popoverView.setDelegate(GameActivity.this);
				View button = (View) findViewById(R.id.statsView);
				popoverView.showPopoverFromRectInViewGroup(rootView,
						PopoverView.getFrameForView(button),
						PopoverView.PopoverArrowDirectionUp, true);

			*/}
		});

		/*View drivesView = mActionBarView.findViewById(R.id.drivesView);

		drivesView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				RelativeLayout rootView = (RelativeLayout) findViewById(R.id.gameRootView);
				if (popoverView != null) {
					popoverView.dissmissPopover(false);
				}
				popoverView = new PopoverView(GameActivity.this,
						R.layout.game_popover_view_drives);

				popoverView.setContentSizeForViewInPopover(new Point(NflUtils
						.convertDensityPixelToPixel(context, 400), NflUtils
						.convertDensityPixelToPixel(context, 460)));
				popoverView.setDelegate(GameActivity.this);
				View button = (View) findViewById(R.id.drivesView);
				popoverView.showPopoverFromRectInViewGroup(rootView,
						PopoverView.getFrameForView(button),
						PopoverView.PopoverArrowDirectionUp, true);

			}
		});*/

		View playersView = mActionBarView.findViewById(R.id.playersView);

		playersView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {/*
				RelativeLayout rootView = (RelativeLayout) findViewById(R.id.gameRootView);
				if (popoverView != null) {
					popoverView.dissmissPopover(false);
				}
				popoverView = new PopoverView(GameActivity.this,
						R.layout.game_popover_view_player);

				popoverView.setContentSizeForViewInPopover(new Point(NflUtils
						.convertDensityPixelToPixel(context, 400), NflUtils
						.convertDensityPixelToPixel(context, 460)));
				popoverView.setDelegate(GameActivity.this);
				View button = (View) findViewById(R.id.playersView);
				popoverView.showPopoverFromRectInViewGroup(rootView,
						PopoverView.getFrameForView(button),
						PopoverView.PopoverArrowDirectionUp, true);

			*/}
		});
		Button alertButton = (Button) mActionBarView
				.findViewById(R.id.alertButton);
		String alertButtonText = SharedPreferencesUtil.getStringPreferences(activity, "alert_button_status");
		if(!alertButtonText.isEmpty() && alertButtonText!=null){
			alertButton.setText("Alerts: " +alertButtonText);
		}else{
			alertButton.setText("Alerts: " + activity.getString(R.string.alert_level_first));
		}
	//	NflUtils.setAlertPopover(activity, popoverView, alertButton);
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
				int arg2, long arg3) {/*
			UpdateTeamInformation.updateTeamInfo(viewObject, activity, context);

			popoverView.removeAllViews();

		*/}

	};

	public void enlargeScoreBanner() {/*
		matchScoreBoardTabContainer.setVisibility(View.INVISIBLE);
		RelativeLayout matchScoreBoardBackground = (RelativeLayout) findViewById(R.id.matchScoreBoardBackground);
		matchScoreBoardBackground
				.setLayoutParams(new RelativeLayout.LayoutParams(
						LayoutParams.FILL_PARENT, NflUtils
								.convertPixelToDensityPixel(context, 160)));

		ImageView firstTeamIconImage = (ImageView) findViewById(R.id.firstTeamLargeIcon);
		firstTeamIconImage.setImageResource(ImageProcessingUtil.getTeamLogoImageResourceByTeamId(activity, leftSideTeamId));

		firstTeamIconImage.setLayoutParams(new LinearLayout.LayoutParams(
				NflUtils.convertPixelToDensityPixel(context, 130),
				LayoutParams.WRAP_CONTENT));

		ImageView secondTeamIconImage = (ImageView) findViewById(R.id.secondTeamLargeIcon);
		secondTeamIconImage.setImageResource(ImageProcessingUtil.getTeamLogoImageResourceByTeamId(activity, rightSideTeamId));
		secondTeamIconImage.setLayoutParams(new LinearLayout.LayoutParams(
				NflUtils.convertPixelToDensityPixel(context, 130),
				LayoutParams.WRAP_CONTENT));

		TextView firstTeamScore = (TextView) findViewById(R.id.firstTeamScore);
		firstTeamScore.setTextSize(NflUtils.convertPixelToDensityPixel(context,
				45));

		TextView secondTeamScore = (TextView) findViewById(R.id.secondTeamScore);
		secondTeamScore.setTextSize(NflUtils.convertPixelToDensityPixel(
				context, 45));

		TextView gameQuarterIndex = (TextView) findViewById(R.id.gameQuarterIndex);
		gameQuarterIndex.setTextSize(NflUtils.convertPixelToDensityPixel(
				context, 18));

		TextView gameQuarterTime = (TextView) findViewById(R.id.gameQuarterTime);
		gameQuarterTime.setTextSize(NflUtils.convertPixelToDensityPixel(
				context, 22));

		TextView gameScoreDescription = (TextView) findViewById(R.id.gameScoreDescription);
		gameScoreDescription.setTextSize(NflUtils.convertPixelToDensityPixel(
				context, 18));

		TextView firstTeamFirstName = (TextView) findViewById(R.id.firstTeamFirstName);
		firstTeamFirstName.setTextSize(NflUtils.convertPixelToDensityPixel(
				context, 15));

		TextView firstTeamSecondName = (TextView) findViewById(R.id.firstTeamSecondName);
		firstTeamSecondName.setTextSize(NflUtils.convertPixelToDensityPixel(
				context, 26));

		TextView secondTeamFirstName = (TextView) findViewById(R.id.secondTeamFirstName);
		secondTeamFirstName.setTextSize(NflUtils.convertPixelToDensityPixel(
				context, 15));

		TextView secondTeamSecondName = (TextView) findViewById(R.id.secondTeamSecondName);
		secondTeamSecondName.setTextSize(NflUtils.convertPixelToDensityPixel(
				context, 26));
		//
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.RIGHT_OF,
				R.id.firstTeamLargeIconContainer);
		params.topMargin = (NflUtils.convertPixelToDensityPixel(context, 55));
		params.leftMargin = (NflUtils.convertPixelToDensityPixel(context, 25));
		LinearLayout firstTeamDescriptionContainer = (LinearLayout) findViewById(R.id.firstTeamDescriptionContainer);
		firstTeamDescriptionContainer.setLayoutParams(params);

		RelativeLayout.LayoutParams relParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		relParams.addRule(RelativeLayout.LEFT_OF,
				R.id.secondTeamLargeIconContainer);
		relParams.topMargin = (NflUtils.convertPixelToDensityPixel(context, 55));
		relParams.rightMargin = (NflUtils.convertPixelToDensityPixel(context,
				25));
		LinearLayout secondTeamDescriptionContainer = (LinearLayout) findViewById(R.id.secondTeamDescriptionContainer);
		secondTeamDescriptionContainer.setLayoutParams(relParams);
		NflUtils.setScoreBannerShrinked(false);
	*/}

	public void shrinkScoreBanner() {/*
		matchScoreBoardTabContainer.setVisibility(View.VISIBLE);
		RelativeLayout matchScoreBoardBackground = (RelativeLayout) findViewById(R.id.matchScoreBoardBackground);
		matchScoreBoardBackground.getLayoutParams().width = NflUtils
				.convertDensityPixelToPixel(context, 470);

		ImageView firstTeamIconImage = (ImageView) findViewById(R.id.firstTeamLargeIcon);
		firstTeamIconImage.setImageResource(ImageProcessingUtil.getTeamLogoImageResourceByTeamId(activity, leftSideTeamId));
		firstTeamIconImage.getLayoutParams().width = NflUtils
				.convertPixelToDensityPixel(context, 65);
		firstTeamIconImage.getLayoutParams().height = NflUtils
				.convertPixelToDensityPixel(context, 65);

		ImageView secondTeamIconImage = (ImageView) findViewById(R.id.secondTeamLargeIcon);
		secondTeamIconImage.setImageResource(ImageProcessingUtil.getTeamLogoImageResourceByTeamId(activity, rightSideTeamId));
		secondTeamIconImage.getLayoutParams().width = NflUtils
				.convertPixelToDensityPixel(context, 65);
		secondTeamIconImage.getLayoutParams().height = NflUtils
				.convertPixelToDensityPixel(context, 65);

		TextView firstTeamScore = (TextView) findViewById(R.id.firstTeamScore);
		firstTeamScore.setTextSize(NflUtils.convertPixelToDensityPixel(context,
				25));

		TextView secondTeamScore = (TextView) findViewById(R.id.secondTeamScore);
		secondTeamScore.setTextSize(NflUtils.convertPixelToDensityPixel(
				context, 25));

		TextView gameQuarterIndex = (TextView) findViewById(R.id.gameQuarterIndex);
		gameQuarterIndex.setTextSize(NflUtils.convertPixelToDensityPixel(
				context, 15));

		TextView gameQuarterTime = (TextView) findViewById(R.id.gameQuarterTime);
		gameQuarterTime.setTextSize(NflUtils.convertPixelToDensityPixel(
				context, 22));

		TextView gameScoreDescription = (TextView) findViewById(R.id.gameScoreDescription);
		gameScoreDescription.setTextSize(NflUtils.convertPixelToDensityPixel(
				context, 15));

		TextView firstTeamFirstName = (TextView) findViewById(R.id.firstTeamFirstName);
		firstTeamFirstName.setTextSize(NflUtils.convertPixelToDensityPixel(
				context, 13));

		TextView firstTeamSecondName = (TextView) findViewById(R.id.firstTeamSecondName);
		firstTeamSecondName.setTextSize(NflUtils.convertPixelToDensityPixel(
				context, 20));

		TextView secondTeamFirstName = (TextView) findViewById(R.id.secondTeamFirstName);
		secondTeamFirstName.setTextSize(NflUtils.convertPixelToDensityPixel(
				context, 13));

		TextView secondTeamSecondName = (TextView) findViewById(R.id.secondTeamSecondName);
		secondTeamSecondName.setTextSize(NflUtils.convertPixelToDensityPixel(
				context, 20));

		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM,
				R.id.firstTeamLargeIconContainer);
		params.addRule(RelativeLayout.ALIGN_PARENT_LEFT,
				R.id.firstTeamLargeIconContainer);
		params.bottomMargin = (NflUtils.convertPixelToDensityPixel(context, 15));
		params.leftMargin = (NflUtils.convertPixelToDensityPixel(context, 5));

		LinearLayout firstTeamDescriptionContainer = (LinearLayout) findViewById(R.id.firstTeamDescriptionContainer);
		firstTeamDescriptionContainer.setLayoutParams(params);

		RelativeLayout.LayoutParams relParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		relParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM,
				R.id.secondTeamLargeIconContainer);
		relParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,
				R.id.secondTeamLargeIconContainer);
		relParams.bottomMargin = (NflUtils.convertPixelToDensityPixel(context,
				15));
		params.rightMargin = (NflUtils.convertPixelToDensityPixel(context, 10));
		LinearLayout secondTeamDescriptionContainer = (LinearLayout) findViewById(R.id.secondTeamDescriptionContainer);
		secondTeamDescriptionContainer.setLayoutParams(relParams);
		NflUtils.setScoreBannerShrinked(true);
	*/}

	@Override
	public void popoverViewWillShow(PopoverView view) {
		Log.i("POPOVER", "Will show : " + view.getChildCount());
	}

	public void getPlayers(String teamName, String teamType) {/*

		List<PlayerItem> playerList = null;

		if (teamName.equals("team1") && teamType.equals("offensive")) {
			final List<PlayerItem> offensivePlayerList = new ArrayList<PlayerItem>();
			String offensivePlayerNamesForTeam1[] = { "Graham Harrell",
					"Alex Green", "Randall Cobb", "Andrew Quarless" };
			String offensivePlayerNumbersForTeam1[] = { "#6 | QB", "#20 | RB",
					"#83 | WR", "#87 | TE" };
			String offensivePlayerData1ForTeam1[] = { "20/29", "11 CAR",
					"9 REC", "8 REC" };
			String offensivePlayerData2ForTeam1[] = { "329 YDS", "64 YDS",
					"53 YDS", "89 YDS" };
			String offensivePlayerData3ForTeam1[] = { "2 TD", "1 TD", "0 TD",
					"1 TD" };

			String offensivPlayerImagesForTeam1[] = {
					"http://172.16.9.39:8081/nfl-images/nfl_players_images/00-0000108.png",
					"http://172.16.9.39:8081/nfl-images/nfl_players_images/00-0000585.png",
					"http://172.16.9.39:8081/nfl-images/nfl_players_images/00-0004091.png",
					"http://172.16.9.39:8081/nfl-images/nfl_players_images/00-0004541.png", };

			PlayerItem item;

			for (int i = 0; i < offensivePlayerNamesForTeam1.length; i++) {
				item = new PlayerItem();
				item.setPlayerdata1(offensivePlayerData1ForTeam1[i]);
				item.setPlayerdata2(offensivePlayerData2ForTeam1[i]);
				item.setPlayerdata3(offensivePlayerData3ForTeam1[i]);
				item.setPlayerImage(offensivPlayerImagesForTeam1[i]);
				item.setPlayerName(offensivePlayerNamesForTeam1[i]);
				item.setplayerDetails(offensivePlayerNumbersForTeam1[i]);
				offensivePlayerList.add(item);
			}

			playerList = offensivePlayerList;
		} else if (teamName.equals("team1") && teamType.equals("defensive")) {
			final List<PlayerItem> defensivePlayerList = new ArrayList<PlayerItem>();
			String defensivePlayerNamesForTeam1[] = { "Mike Daniels",
					"Josh Bell", "Johnny Jolly" };
			String defensivePlayerNumbersForTeam1[] = { "#92 | DE", "#75 | DT",
					"#50 | DL" };
			String defensivePlayerData1ForTeam1[] = { "20/29", "11 CAR",
					"9 REC" };
			String defensivePlayerData2ForTeam1[] = { "329 YDS", "64 YDS",
					"53 YDS" };
			String defensivePlayerData3ForTeam1[] = { "2 TD", "1 TD", "0 TD" };

			String defensivePlayerImages[] = {
					"http://172.16.9.39:8081/nfl-images/nfl_players_images/00-0019536.png",
					"http://172.16.9.39:8081/nfl-images/nfl_players_images/00-0019548.png",
					"http://172.16.9.39:8081/nfl-images/nfl_players_images/00-0020962.png" };

			PlayerItem item = new PlayerItem();

			for (int i = 0; i < defensivePlayerNamesForTeam1.length; i++) {
				item = new PlayerItem();
				item.setPlayerdata1(defensivePlayerData1ForTeam1[i]);
				item.setPlayerdata2(defensivePlayerData2ForTeam1[i]);
				item.setPlayerdata3(defensivePlayerData3ForTeam1[i]);
				item.setPlayerImage(defensivePlayerImages[i]);
				item.setPlayerName(defensivePlayerNamesForTeam1[i]);
				item.setplayerDetails(defensivePlayerNumbersForTeam1[i]);
				defensivePlayerList.add(item);
			}
			playerList = defensivePlayerList;
		} else if (teamName.equals("team2") && teamType.equals("offensive")) {
			final List<PlayerItem> offensivePlayerList = new ArrayList<PlayerItem>();
			String offensivePlayerNamesForTeam1[] = { "Tom Brady",
					"Stevan Ridley", "Wes Welker" };
			String offensivePlayerNumbersForTeam1[] = { "#91 | QB", "#22 | RB",
					"#83 | WR", "#87 | TE", "#43 | QB" };
			String offensivePlayerData1ForTeam1[] = { "20/29", "11 CAR",
					"8 REC" };
			String offensivePlayerData2ForTeam1[] = { "64 YDS", "53 YDS",
					"89 YDS" };
			String offensivePlayerData3ForTeam1[] = { "0 TD", "0 TD", "1 TD" };

			String offensivPlayerImagesForTeam1[] = {
					"http://172.16.9.39:8081/nfl-images/nfl_players_images/00-0000585.png",
					"http://172.16.9.39:8081/nfl-images/nfl_players_images/00-0004091.png",
					"http://172.16.9.39:8081/nfl-images/nfl_players_images/00-0004541.png",
					"http://172.16.9.39:8081/nfl-images/nfl_players_images/00-0000585.png" };

			PlayerItem item;

			for (int i = 0; i < offensivePlayerNamesForTeam1.length; i++) {
				item = new PlayerItem();
				item.setPlayerdata1(offensivePlayerData1ForTeam1[i]);
				item.setPlayerdata2(offensivePlayerData2ForTeam1[i]);
				item.setPlayerdata3(offensivePlayerData3ForTeam1[i]);
				item.setPlayerImage(offensivPlayerImagesForTeam1[i]);
				item.setPlayerName(offensivePlayerNamesForTeam1[i]);
				item.setplayerDetails(offensivePlayerNumbersForTeam1[i]);
				offensivePlayerList.add(item);
			}

			playerList = offensivePlayerList;
		} else if (teamName.equals("team2") && teamType.equals("defensive")) {
			final List<PlayerItem> defensivePlayerList = new ArrayList<PlayerItem>();
			String defensivePlayerNamesForTeam1[] = { "Jake Bequette",
					"Vince Wilfork", "Rob Ninkovich" };
			String defensivePlayerNumbersForTeam1[] = { "#92 | DE", "#75 | DT",
					"#50 | DL" };
			String defensivePlayerData1ForTeam1[] = { "20/29", "11 CAR",
					"9 REC" };
			String defensivePlayerData2ForTeam1[] = { "329 YDS", "64 YDS",
					"53 YDS" };
			String defensivePlayerData3ForTeam1[] = { "2 TD", "1 TD", "0 TD" };

			String defensivePlayerImages[] = {
					"http://172.16.9.39:8081/nfl-images/nfl_players_images/00-0004091.png",
					"http://172.16.9.39:8081/nfl-images/nfl_players_images/00-0004541.png",
					"http://172.16.9.39:8081/nfl-images/nfl_players_images/00-0000585.png" };
			PlayerItem item = new PlayerItem();

			for (int i = 0; i < defensivePlayerNamesForTeam1.length; i++) {
				item = new PlayerItem();
				item.setPlayerdata1(defensivePlayerData1ForTeam1[i]);
				item.setPlayerdata2(defensivePlayerData2ForTeam1[i]);
				item.setPlayerdata3(defensivePlayerData3ForTeam1[i]);
				item.setPlayerImage(defensivePlayerImages[i]);
				item.setPlayerName(defensivePlayerNamesForTeam1[i]);
				item.setplayerDetails(defensivePlayerNumbersForTeam1[i]);
				defensivePlayerList.add(item);
			}
			playerList = defensivePlayerList;
		}
		return playerList;
	*/}

	@Override
	public void popoverViewDidShow(PopoverView view) {/*

		Log.d("layoutId", String.valueOf(view.getLayoutId()));
		if (view.getLayoutId() == R.layout.game_popover_view_schedule) {
			Log.d("id", String.valueOf(view.getId()));
			List<ScheduleItem> rowItems = new ArrayList<ScheduleItem>();
			String[] teamNames = { "Titans", "Cardinals", "Ravens", "Bills",
					"Packers", "Seehawks", "Jets", "Rams" };
			int[] teamLogo = { R.drawable.ten, R.drawable.arz, R.drawable.blt,
					R.drawable.buf, R.drawable.gb, R.drawable.sea,
					R.drawable.nyj, R.drawable.sl };
			String[] weekText = { "WEEK 1", "WEEK 2", "WEEK 3", "WEEK 4",
					"WEEK 5", "WEEK 6", "WEEK 7", "WEEK 8" };
			String[] teamStatus = { "W 34-13", "L 20-18", "L 31-30", "W 52-28",
					"Live 21-17", "10/14 4:05 PM", "10/21 4/25 PM",
					"10/21 1:00 PM" };
			String[] versusTexts = { "@", "vs", "@", "@", "vs", "@", "vs", "@" };
			String[] teamIds = {"wf_id", "ns_id", "mh_id", "tc_id", "bb_id", "kj_id", "ks_id", "ss_id"};
			for (int i = 0; i < 8; i++) {

				ScheduleItem item = new ScheduleItem(teamNames[i], teamLogo[i],
						teamStatus[i], weekText[i], versusTexts[i]);
				item.setTeamId(teamIds[i]);
				rowItems.add(item);

			}
			listView = (ListView) findViewById(R.id.game_schedule_list);
			ScheduleListViewAdapter adapter = new ScheduleListViewAdapter(this,
					R.layout.game_popover_list_row_item_schedule, rowItems);
			listView.setAdapter(adapter);
			listView.setOnItemClickListener(scheduleItemClickListener);

			
			 * listView = (ListView) findViewById(R.id.game_schedule_list2);
			 * adapter = new CourseListViewAdapter(this,
			 * R.layout.my_course_list_item, rowItems);
			 * listView.setAdapter(adapter);
			 

		} else if (view.getLayoutId() == R.layout.game_popover_view_drives) {

			List<DriveItem> rowItemsForQ1 = new ArrayList<DriveItem>();
			List<DriveItem> rowItemsForQ2 = new ArrayList<DriveItem>();
			List<DriveItem> rowItemsForQ3 = new ArrayList<DriveItem>();
			List<DriveItem> rowItemsForQ4 = new ArrayList<DriveItem>();

			String[] teamShortNamesForQ1 = { "CS", "CS", "KS", "CS" };
			String[] teamShortNamesForQ2 = { "KS", "KS", "CS", "KS" };
			String[] teamShortNamesForQ3 = { "KS", "KS", "CS", "KS", "KS" };
			String[] teamShortNamesForQ4 = { "CS", "CS", "KS", "CS"};

			String[] driveEventsForQ1 = { "LIVE", "TOUCHDOWN", "PUNT", "PUNT" };
			String[] driveEventsForQ2 = { "LIVE", "TOUCHDOWN", "PUNT", "PUNT" };
			String[] driveEventsForQ3 = { "TOUCHDOWN", "INTERCEPTION", "PUNT",
					"FIELD GOAL", "FUMBLE" };
			String[] driveEventsForQ4 = { "LIVE", "TOUCHDOWN", "PUNT", "PUNT" };

			String[] driveYardsForQ1 = { "53 YDS", "52 YDS", "31 YDS", "22 YDS" };
			String[] driveTimeForQ1 = { "8:32", "8:55", "2:46", "1:32" };
			String[] driveYardsForQ2 = { "53 YDS", "52 YDS", "31 YDS", "22 YDS" };
			String[] driveTimeForQ2 = { "8:32", "8:55", "2:46", "1:32" };

			String[] driveYardsForQ3 = { "53 YDS", "52 YDS", "31 YDS",
					"22 YDS", "52 YDS" };
			String[] driveTimeForQ3 = { "8:32", "8:55", "2:46", "1:32", "1:32" };

			String[] driveYardsForQ4 = { "53 YDS", "52 YDS", "31 YDS",
					"22 YDS", "52 YDS" };
			String[] driveTimeForQ4 = { "8:32", "8:55", "2:46", "1:32", "1:32" };

			Drawable[] teamLogoForQ1 = {ImageProcessingUtil.getTeamLogoImageDrawableByTeamId(activity, "cs_id"),ImageProcessingUtil.getTeamLogoImageDrawableByTeamId(activity, "cs_id"),
					ImageProcessingUtil.getTeamLogoImageDrawableByTeamId(activity, "ks_id"),ImageProcessingUtil.getTeamLogoImageDrawableByTeamId(activity, "cs_id") };
			Drawable[] teamLogoForQ2 = {ImageProcessingUtil.getTeamLogoImageDrawableByTeamId(activity, "ks_id"),ImageProcessingUtil.getTeamLogoImageDrawableByTeamId(activity, "ks_id"),
					ImageProcessingUtil.getTeamLogoImageDrawableByTeamId(activity, "cs_id"),ImageProcessingUtil.getTeamLogoImageDrawableByTeamId(activity, "ks_id") };
			Drawable[] teamLogoForQ3 = {ImageProcessingUtil.getTeamLogoImageDrawableByTeamId(activity, "ks_id"), ImageProcessingUtil.getTeamLogoImageDrawableByTeamId(activity, "ks_id"),
					ImageProcessingUtil.getTeamLogoImageDrawableByTeamId(activity, "cs_id"), ImageProcessingUtil.getTeamLogoImageDrawableByTeamId(activity, "ks_id"),ImageProcessingUtil.getTeamLogoImageDrawableByTeamId(activity, "ks_id") };
			Drawable[] teamLogoForQ4 = { ImageProcessingUtil.getTeamLogoImageDrawableByTeamId(activity, "cs_id"),ImageProcessingUtil.getTeamLogoImageDrawableByTeamId(activity, "cs_id"),
					ImageProcessingUtil.getTeamLogoImageDrawableByTeamId(activity, "ks_id"),ImageProcessingUtil.getTeamLogoImageDrawableByTeamId(activity, "cs_id") };

			for (int i = 0; i < teamShortNamesForQ1.length; i++) {
				DriveItem item = new DriveItem(teamShortNamesForQ1[i],
						driveEventsForQ1[i], teamLogoForQ1[i],
						driveTimeForQ1[i], driveYardsForQ1[i]);

				rowItemsForQ1.add(item);
			}
			for (int i = 0; i < teamShortNamesForQ2.length; i++) {
				DriveItem item = new DriveItem(teamShortNamesForQ2[i],
						driveEventsForQ2[i], teamLogoForQ2[i],
						driveTimeForQ2[i], driveYardsForQ2[i]);

				rowItemsForQ2.add(item);
			}
			for (int i = 0; i < teamShortNamesForQ3.length; i++) {
				DriveItem item = new DriveItem(teamShortNamesForQ3[i],
						driveEventsForQ3[i], teamLogoForQ3[i],
						driveTimeForQ3[i], driveYardsForQ3[i]);

				rowItemsForQ3.add(item);
			}
			for (int i = 0; i < teamShortNamesForQ4.length; i++) {
				DriveItem item = new DriveItem(teamShortNamesForQ4[i],
						driveEventsForQ4[i], teamLogoForQ4[i],
						driveTimeForQ4[i], driveYardsForQ4[i]);

				rowItemsForQ4.add(item);
			}

			SeparatedListAdapter adapter = new SeparatedListAdapter(this);
			adapter.addSection("4TH QUARTER", new DriveListViewAdapter(this,
					R.layout.game_popover_list_row_item_drives, rowItemsForQ4));
			adapter.addSection("3RD QUARTER", new DriveListViewAdapter(this,
					R.layout.game_popover_list_row_item_drives, rowItemsForQ3));
			adapter.addSection("2ND QUARTER", new DriveListViewAdapter(this,
					R.layout.game_popover_list_row_item_drives, rowItemsForQ2));
			adapter.addSection("1ST QUARTER", new DriveListViewAdapter(this,
					R.layout.game_popover_list_row_item_drives, rowItemsForQ1));
			listView = (ListView) findViewById(R.id.game_drive_list);
			listView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					if (!NflUtils.isScoreBannerShrinked()) {
						shrinkScoreBanner();
					}
					*//**
					 * Show Drives banner
					 *//*

					matchScoreBoardTabContainer.setVisibility(View.VISIBLE);
					matchScoreBoardTabContainer.removeAllViews();
					final LinearLayout inflatedView = (LinearLayout) View
							.inflate(context,
									R.layout.team_drives_match_scoreboard, null);
					matchScoreBoardTabContainer.addView(inflatedView);
					TextView driveYardCovered = (TextView) (arg1
							.findViewById(R.id.drive_yard_covered));
					TextView driveTime = (TextView) (arg1
							.findViewById(R.id.drive_time));
					// showYardsCoveredInDrives(String startingYards, String
					// driveYardCovered, View arg1){
					TextView driveYardCoveredIndicator = (TextView) (findViewById(R.id.yards_covered_drive_indicator));
					TextView driveScoreLabel = (TextView) (findViewById(R.id.drive_score_label));
					driveScoreLabel.setText("X" + " Plays, "
							+ driveYardCovered.getText().toString() + ", "
							+ driveTime.getText().toString());
					int driveYardCoveredIndicatorWidth = Integer
							.parseInt(driveYardCovered.getText().toString()
									.replaceAll("[^\\d]", ""));
					int driveYardCoveredIndicatorWidthInDp = NflUtils
							.convertDensityPixelToPixel(context,
									driveYardCoveredIndicatorWidth);
					LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
							LayoutParams.WRAP_CONTENT,
							LayoutParams.WRAP_CONTENT);
					params.rightMargin = NflUtils.convertDensityPixelToPixel(
							context, 31);
					params.width = ((driveYardCoveredIndicatorWidth * 3) - 10);
					params.height = NflUtils.convertDensityPixelToPixel(
							context, 20);
					driveYardCoveredIndicator.setLayoutParams(params);
					driveYardCoveredIndicator.setBackgroundColor(0xFF2d81a6);
					popoverView.dissmissPopover(false);
				}
			});
			listView.setAdapter(adapter);

		}

		else if (view.getLayoutId() == R.layout.game_popover_view_stats) {
			TextView statFirstTeamLabel = (TextView) findViewById(R.id.statFirstTeam);
			statFirstTeamLabel.setText("Packers");

			TextView statSecondTeamLabel = (TextView) findViewById(R.id.statSecondTeam);
			statSecondTeamLabel.setText("Patriots");

			List<StatsItem> rowItemsForTeamStats = new ArrayList<StatsItem>();
			List<StatsItem> rowItemsForKeyPlays = new ArrayList<StatsItem>();

			String[] teamStatsType = { "Scoring", "Passing yards",
					"Rushing Yards", "First Downs", "Third Down Efficiency",
					"Red Zone Efficiency", "Turnovers" };
			String[] teamStatsScore1 = { "21", "232", "248", "3", "6/13",
					"2/3", "2" };
			String[] teamStatsScore2 = { "21", "22", "108", "3", "6/13", "2/3",
					"2" };

			String[] keyPlaysStatstype = { "Long Passes", "Long Rushes",
					"Defensive Plays" };
			String[] keyPlaysStatsScore1 = { "12", "3", "8" };
			String[] keyPlaysStatsScore2 = { "6", "7", "7" };

			for (int i = 0; i < teamStatsType.length; i++) {
				StatsItem item = new StatsItem(teamStatsType[i],
						teamStatsScore1[i], teamStatsScore2[i],
						R.drawable.disclosure);

				rowItemsForTeamStats.add(item);
			}
			for (int i = 0; i < keyPlaysStatstype.length; i++) {
				StatsItem item = new StatsItem(keyPlaysStatstype[i],
						keyPlaysStatsScore1[i], keyPlaysStatsScore2[i],
						R.drawable.disclosure);

				rowItemsForKeyPlays.add(item);
			}
			
			 * listView = (ListView) findViewById(R.id.game_schedule_list);
			 * adapter = new CourseListViewAdapter(this,
			 * R.layout.my_course_list_item, rowItems);
			 * listView.setAdapter(adapter);
			 

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

					if (!NflUtils.isScoreBannerShrinked()) {
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
							.getText() + " Yards");

					TextView secondTeamStatYardsScoreLabelInPopUp = (TextView) findViewById(R.id.secondTeamStatYardsScore);
					secondTeamStatYardsScoreLabelInPopUp.setText(statScore2
							.getText() + " Yards");

					// String highestScore=
					// NflUtils.getHighestNumber(statScore1.getText().toString(),
					// statScore2.getText().toString());
					// Integer firstTeamBarWidth=
					// NflUtils.getYardsBarWidth(statScore1.getText().toString(),
					// highestScore);
					// Integer secondTeamBarWidth=
					// NflUtils.getYardsBarWidth(statScore2.getText().toString(),
					// highestScore);

					TextView firstTeamStatYardsWidthLabel = (TextView) findViewById(R.id.firstTeamStatYardsWidth);
					firstTeamStatYardsWidthLabel.setWidth(Integer
							.parseInt(statScore1.getText().toString()));

					TextView secondTeamStatYardsWidthLabel = (TextView) findViewById(R.id.secondTeamStatYardsWidth);
					secondTeamStatYardsWidthLabel.setWidth(Integer
							.parseInt(statScore2.getText().toString()));

					TextView statFirstTeamLabel = (TextView) findViewById(R.id.statFirstTeam);
					TextView firstTeamNameStatYardsCategoryLabel = (TextView) findViewById(R.id.firstTeamNameStatYardsCategory);
					// firstTeamNameStatYardsCategoryLabel.setText(statFirstTeamLabel.getText());
					firstTeamNameStatYardsCategoryLabel.setText("GB");

					TextView statSecondTeamLabel = (TextView) findViewById(R.id.statSecondTeam);
					TextView secondTeamNameStatYardsCategoryLabel = (TextView) findViewById(R.id.secondTeamNameStatYardsCategory);
					// secondTeamNameStatYardsCategoryLabel.setText(statSecondTeamLabel.getText());
					secondTeamNameStatYardsCategoryLabel.setText("NE");
					popoverView.dissmissPopover(false);
				}
			});
			listView.setAdapter(adapter);

		}

		else if (view.getLayoutId() == R.layout.game_popover_view_player) {

			// Offensive players for team1

			// Defensive playes for team1

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

					if (!NflUtils.isScoreBannerShrinked()) {
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
					selectedPlayerPic.getLayoutParams().width = NflUtils
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
					team1BtnPlayers.setBackgroundColor(0xFFFF8B1D);
					team2BtnPlayers.setBackgroundColor(0xFFFFFFFF);

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
					team2BtnPlayers.setBackgroundColor(0xFFFF8B1D);
					team1BtnPlayers.setBackgroundColor(0xFFFFFFFF);
				}
			});

		}

	*/}

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
			allPlaysTextView.setTextColor(getResources().getColor(R.color.white));
			topPlaysTextView.setTextColor(getResources().getColor(R.color.grey_light));
			topRatedTextView.setTextColor(getResources().getColor(R.color.grey_light));
			watchAllTextView.setTextColor(getResources().getColor(R.color.grey_light));
			}
	};

	private OnClickListener topPlaysCilckListener = new OnClickListener() {

		@Override
		public void onClick(View v) {

			topPlaysTextView.setTextColor(getResources().getColor(R.color.white));
			allPlaysTextView.setTextColor(getResources().getColor(R.color.grey_light));
			topRatedTextView.setTextColor(getResources().getColor(R.color.grey_light));
			watchAllTextView.setTextColor(getResources().getColor(R.color.grey_light));

			Toast.makeText(GameActivity.this, "top plays button clicked",
					Toast.LENGTH_SHORT).show();
		}
	};

	private OnClickListener topRatedClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {

			topRatedTextView.setTextColor(getResources().getColor(R.color.white));
			allPlaysTextView.setTextColor(getResources().getColor(R.color.grey_light));
			topPlaysTextView.setTextColor(getResources().getColor(R.color.grey_light));
			watchAllTextView.setTextColor(getResources().getColor(R.color.grey_light));
			
			Toast.makeText(GameActivity.this, "top Rated button clicked",
					Toast.LENGTH_SHORT).show();
		}
	};

	private OnClickListener watchAllClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			watchAllTextView.setTextColor(getResources().getColor(R.color.white));
			allPlaysTextView.setTextColor(getResources().getColor(R.color.grey_light));
			topPlaysTextView.setTextColor(getResources().getColor(R.color.grey_light));
			topRatedTextView.setTextColor(getResources().getColor(R.color.grey_light));

			Toast.makeText(GameActivity.this, "watch all button clicked",
					Toast.LENGTH_SHORT).show();
		}
	};

	private void playCards() {/*

		ArrayList<String> playCardTopDetail = new ArrayList<String>();
		playCardTopDetail.add("45-Yard Pass, 1st Down");
		playCardTopDetail.add("2-Yard Run");
		playCardTopDetail.add("16-Yard, TouchDown");
		playCardTopDetail.add("18-Yard, TouchDown");
		playCardTopDetail.add("45-Yard Pass, 1st Down");
		playCardTopDetail.add("2-Yard Run");
		playCardTopDetail.add("16-Yard, TouchDown");
		playCardTopDetail.add("18-Yard, TouchDown");
		playCardTopDetail.add("45-Yard Pass, 1st Down");
		playCardTopDetail.add("2-Yard Run");
		playCardTopDetail.add("16-Yard, TouchDown");
		playCardTopDetail.add("18-Yard, TouchDown");
		playCardTopDetail.add("45-Yard Pass, 1st Down");
		playCardTopDetail.add("2-Yard Run");
		playCardTopDetail.add("16-Yard, TouchDown");
		playCardTopDetail.add("18-Yard, TouchDown");
		playCardTopDetail.add("45-Yard Pass, 1st Down");
		playCardTopDetail.add("2-Yard Run");
		playCardTopDetail.add("16-Yard, TouchDown");
		playCardTopDetail.add("18-Yard, TouchDown");
		playCardTopDetail.add("45-Yard Pass, 1st Down");
		playCardTopDetail.add("2-Yard Run");
		playCardTopDetail.add("16-Yard, TouchDown");
		playCardTopDetail.add("18-Yard, TouchDown");

		ArrayList<String> playCardBottomDetail = new ArrayList<String>();
		playCardBottomDetail
				.add("Tom Brady pass complete to Wes Welker for 45-yards to the GB 10 for a 1st down (6:38 4th)");
		playCardBottomDetail
				.add("Stevan Ridley rush for 2-yards to the NE 45 (6:42 4th)");
		playCardBottomDetail
				.add("Arian Foster rush for 16-yards for a TOUCHDOWN (3:17 4th)");
		playCardBottomDetail
				.add("Shayne Graham kicks 63-yards from HOU 35 to ATL 2.Jacquizz Rodgers to ATL 20 for 18-yards (3:09 4th)");
		playCardBottomDetail
				.add("Tom Brady pass complete to Wes Welker for 45-yards to the GB 10 for a 1st down (6:38 4th)");
		playCardBottomDetail
				.add("Stevan Ridley rush for 2-yards to the NE 45 (6:42 4th)");
		playCardBottomDetail
				.add("Arian Foster rush for 16-yards for a TOUCHDOWN (3:17 4th)");
		playCardBottomDetail
				.add("Shayne Graham kicks 63-yards from HOU 35 to ATL 2.Jacquizz Rodgers to ATL 20 for 18-yards (3:09 4th)");
		playCardBottomDetail
				.add("Tom Brady pass complete to Wes Welker for 45-yards to the GB 10 for a 1st down (6:38 4th)");
		playCardBottomDetail
				.add("Stevan Ridley rush for 2-yards to the NE 45 (6:42 4th)");
		playCardBottomDetail
				.add("Arian Foster rush for 16-yards for a TOUCHDOWN (3:17 4th)");
		playCardBottomDetail
				.add("Shayne Graham kicks 63-yards from HOU 35 to ATL 2.Jacquizz Rodgers to ATL 20 for 18-yards (3:09 4th)");
		playCardBottomDetail
				.add("Tom Brady pass complete to Wes Welker for 45-yards to the GB 10 for a 1st down (6:38 4th)");
		playCardBottomDetail
				.add("Stevan Ridley rush for 2-yards to the NE 45 (6:42 4th)");
		playCardBottomDetail
				.add("Arian Foster rush for 16-yards for a TOUCHDOWN (3:17 4th)");
		playCardBottomDetail
				.add("Shayne Graham kicks 63-yards from HOU 35 to ATL 2.Jacquizz Rodgers to ATL 20 for 18-yards (3:09 4th)");
		playCardBottomDetail
				.add("Tom Brady pass complete to Wes Welker for 45-yards to the GB 10 for a 1st down (6:38 4th)");
		playCardBottomDetail
				.add("Stevan Ridley rush for 2-yards to the NE 45 (6:42 4th)");
		playCardBottomDetail
				.add("Arian Foster rush for 16-yards for a TOUCHDOWN (3:17 4th)");
		playCardBottomDetail
				.add("Shayne Graham kicks 63-yards from HOU 35 to ATL 2.Jacquizz Rodgers to ATL 20 for 18-yards (3:09 4th)");
		playCardBottomDetail
				.add("Tom Brady pass complete to Wes Welker for 45-yards to the GB 10 for a 1st down (6:38 4th)");
		playCardBottomDetail
				.add("Stevan Ridley rush for 2-yards to the NE 45 (6:42 4th)");
		playCardBottomDetail
				.add("Arian Foster rush for 16-yards for a TOUCHDOWN (3:17 4th)");
		playCardBottomDetail
				.add("Shayne Graham kicks 63-yards from HOU 35 to ATL 2.Jacquizz Rodgers to ATL 20 for 18-yards (3:09 4th)");

		// The parent LinearLayout for playCards
		LinearLayout playCardParentLinearLayout = (LinearLayout) findViewById(R.id.parentLayoutOfPlayCardsId);

		DownloadImagesThreadPool downloadImagesThreadPool = new DownloadImagesThreadPool();

		RelativeLayout selectedCategoryLayoutId = (RelativeLayout) findViewById(R.id.selectedCategoryLayoutId);

		int colors[] = NflUtils.getDefaultColorTheme();
		GradientDrawable gradientDrawable = new GradientDrawable(
				GradientDrawable.Orientation.LEFT_RIGHT, colors);
		selectedCategoryLayoutId.setBackgroundDrawable(gradientDrawable);

		TextView selectedCategoryTextView = (TextView) findViewById(R.id.selectedCategoryTextViewId);
		selectedCategoryTextView.setText("NEW ENGLAND PATRIOTS");

		for (int index = 0; index < 6; index++) {

			playCardParentLinearLayout.addView(getPlayCardView(context, index,
					playCardTopDetail.get(index),
					playCardBottomDetail.get(index), getResources(),
					downloadImagesThreadPool));
			View marginView = new View(context);
			marginView.setLayoutParams(new LayoutParams(20, 0));
			playCardParentLinearLayout.addView(marginView);
		}

		downloadImagesThreadPool.tearDown(6);

	*/}

	/*private View getPlayCardView(Context context2, int index,
			String playCardTopDetail, String playCardBottomDetail,
			Resources resources,
			DownloadImagesThreadPool downloadImagesThreadPool) {

		return new PlayCardView(context2, index, playCardTopDetail,
				playCardBottomDetail, resources, this)
				.getPlayCard(downloadImagesThreadPool);
	}*/

	public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
		ImageView bmImage;

		public DownloadImageTask(ImageView bmImage) {
			this.bmImage = bmImage;
		}

		protected Bitmap doInBackground(String... urls) {
			String urldisplay = urls[0];
			Bitmap mIcon11 = null;
			try {
				InputStream in = new java.net.URL(urldisplay).openStream();
				mIcon11 = BitmapFactory.decodeStream(in);
			} catch (Exception e) {
				Log.e("Error", e.getMessage());
				e.printStackTrace();
			}
			return mIcon11;
		}

		protected void onPostExecute(Bitmap result) {
			bmImage.setImageBitmap(result);
		}
	}
}