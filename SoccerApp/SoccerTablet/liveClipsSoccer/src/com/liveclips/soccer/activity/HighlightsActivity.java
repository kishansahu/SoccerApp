/**
 * 
 */
package com.liveclips.soccer.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.liveclips.soccer.R;
import com.liveclips.soccer.adapter.EmptyHeaderSeparatedListAdapter;
import com.liveclips.soccer.adapter.HighlightsLeadersListAdapter;
import com.liveclips.soccer.adapter.HighlightsMonthsListAdapter;
import com.liveclips.soccer.adapter.PassingLeaderListViewAdapter;
import com.liveclips.soccer.adapter.SeparatedListAdapter;
import com.liveclips.soccer.adapter.SeparatedSectionHeaderListAdapter;
import com.liveclips.soccer.adapter.StandingTeamListViewAdapter;
import com.liveclips.soccer.imageutils.DownloadImagesThreadPool;
import com.liveclips.soccer.model.LeadersTypeItem;
import com.liveclips.soccer.model.SectionHeaderItem;
import com.liveclips.soccer.model.TeamItem;
import com.liveclips.soccer.popover.PopoverView;
import com.liveclips.soccer.popover.PopoverView.PopoverViewDelegate;
import com.liveclips.soccer.services.HighlightsService;
import com.liveclips.soccer.utils.PlayCardView;
import com.liveclips.soccer.utils.SharedPreferencesUtil;
import com.liveclips.soccer.utils.SoccerUtils;

/**
 * @author mohitkumar
 * 
 */
public class HighlightsActivity extends BaseActivity implements
		PopoverViewDelegate {
	
	private Activity highlightsActivity;
	private Context context;
	private PopoverView popoverView;
	
	private TextView allPlaysTextView, topPlaysTextView, topRatedTextView,
			watchAllTextView;

	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.highlights_activity);

		context = this;
		highlightsActivity = this;
		
		fullScreenView = (RelativeLayout) findViewById(R.id.fullScreenView);
		fragmentManager = getFragmentManager();		

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
		prepareVideoView();

		allPlaysTextView = (TextView) findViewById(R.id.allPlaysId);
		topPlaysTextView = (TextView) findViewById(R.id.topPlaysId);
		topRatedTextView = (TextView) findViewById(R.id.topRatedId);
		watchAllTextView = (TextView) findViewById(R.id.watchAllId);

		allPlaysTextView.setOnClickListener(allPlaysClickListener);
		topPlaysTextView.setOnClickListener(topPlaysCilckListener);
		topRatedTextView.setOnClickListener(topRatedClickListener);
		watchAllTextView.setOnClickListener(watchAllClickListener);

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
		mActionBarView = getLayoutInflater().inflate(
				R.layout.highlights_actionbar, null);
		actionBar.setCustomView(mActionBarView);
		actionBar.setBackgroundDrawable(new ColorDrawable(0xFFFF8B1D));
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

		Button alertButton = (Button) mActionBarView
				.findViewById(R.id.alertButton);
		String alertButtonText = SharedPreferencesUtil.getStringPreferences(
				highlightsActivity, "alert_button_status");
		if (!alertButtonText.isEmpty() && alertButtonText != null) {
			alertButton.setText("Alerts: " + alertButtonText);
		} else {
			alertButton.setText("Alerts: "
					+ highlightsActivity.getString(R.string.alert_level_first));
		}
		SoccerUtils.setAlertPopover(highlightsActivity, popoverView,
				alertButton);

		Drawable d = getResources().getDrawable(
				R.drawable.orange_gradient_background);
		actionBar.setBackgroundDrawable(d);

		TextView monthsPopover = (TextView) mActionBarView
				.findViewById(R.id.monthsPopover);
		monthsPopover.setOnClickListener(monthsOnClickListener);

		TextView leadersPopover = (TextView) mActionBarView
				.findViewById(R.id.leadersPopover);
		leadersPopover.setOnClickListener(leadersOnClickListener);

		TextView tablesPopover = (TextView) mActionBarView
				.findViewById(R.id.tablesPopover);
		tablesPopover.setOnClickListener(tablesOnClickListener);
	}

	/**
	 * months listener
	 */
	private OnClickListener monthsOnClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			RelativeLayout rootView = (RelativeLayout) findViewById(R.id.gameRootView);
			if (popoverView != null) {
				popoverView.removeAllViews();
			}
			popoverView = new PopoverView(HighlightsActivity.this,
					R.layout.highlights_popover_view_months);

			popoverView.setContentSizeForViewInPopover(new Point(250, 400));
			popoverView.setDelegate(HighlightsActivity.this);
			
			View button = (View) findViewById(R.id.monthsPopover);
			
			popoverView.showPopoverFromRectInViewGroup(rootView,
					PopoverView.getFrameForView(button),
					PopoverView.PopoverArrowDirectionUp, true);
		}
	};

	
	/**
	 *  leaders Listener
	 */
	
	private OnClickListener leadersOnClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			RelativeLayout rootView = (RelativeLayout) findViewById(R.id.gameRootView);
			if (popoverView != null) {
				popoverView.removeAllViews();
			}
			popoverView = new PopoverView(HighlightsActivity.this,
					R.layout.highlights_popover_view_leaders);

			popoverView.setContentSizeForViewInPopover(new Point(250, 400));
			popoverView.setDelegate(HighlightsActivity.this);
			View button = (View) findViewById(R.id.leadersPopover);
			popoverView.showPopoverFromRectInViewGroup(rootView,
					PopoverView.getFrameForView(button),
					PopoverView.PopoverArrowDirectionUp, true);
		}
	};

	/**
	 * standing listener
	 */
	private OnClickListener tablesOnClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			RelativeLayout rootView = (RelativeLayout) findViewById(R.id.gameRootView);
			if (popoverView != null) {
				popoverView.removeAllViews();
			}
			popoverView = new PopoverView(HighlightsActivity.this,
					R.layout.highlights_popover_view_standing);

			popoverView.setContentSizeForViewInPopover(new Point(320, 400));
			popoverView.setDelegate(HighlightsActivity.this);
			View button = (View) findViewById(R.id.tablesPopover);
			popoverView.showPopoverFromRectInViewGroup(rootView,
					PopoverView.getFrameForView(button),
					PopoverView.PopoverArrowDirectionUp, true);
		}
	};

	private void prepareVideoView() {

		LinearLayout playCardParentLinearLayout = (LinearLayout) findViewById(R.id.parentLayoutOfPlayCardsId);

		ArrayList<String> playCardTopDetail = new ArrayList<String>();
		ArrayList<String> playCardBottomDetail = new ArrayList<String>();

		playCardParentLinearLayout.removeAllViews();

		DownloadImagesThreadPool downloadImagesThreadPool = new DownloadImagesThreadPool();

		TextView selectedCategoryTextView = (TextView) findViewById(R.id.selectedCategoryTextViewId);
		selectedCategoryTextView.setText("NFL Highlights");
		for (int index = 0; index < 4; index++) {
			playCardTopDetail.add("Video Text on Top " + index);

			playCardBottomDetail.add("Video Text on Bottom " + index);
			playCardParentLinearLayout.addView(getPlayCardView(context, index,
					playCardTopDetail.get(index),
					playCardBottomDetail.get(index), getResources(),
					downloadImagesThreadPool));
			View marginView = new View(context);
			marginView.setLayoutParams(new LayoutParams(20, 0));
			playCardParentLinearLayout.addView(marginView);
		}
	}

	private View getPlayCardView(Context context2, int index,
			String playCardTopDetail, String playCardBottomDetail,
			Resources resources,
			DownloadImagesThreadPool downloadImagesThreadPool) {

		return new PlayCardView(context2, index, playCardTopDetail,
				playCardBottomDetail, resources, this)
				.getPlayCard(downloadImagesThreadPool);
	}

	@Override
	public void popoverViewWillShow(PopoverView view) {
		Log.d("HighlightsActivity", "layoutId : " + String.valueOf(view.getLayoutId()));

	}

	@Override
	public void popoverViewDidShow(PopoverView view) {
		ListView listView;
		Log.d("layoutId", String.valueOf(view.getLayoutId()));
		if (view.getLayoutId() == R.layout.highlights_popover_view_months) {
			Log.d("id", String.valueOf(view.getId()));

			listView = (ListView) findViewById(R.id.months_list);
			HighlightsMonthsListAdapter monthsListAdapter = new HighlightsMonthsListAdapter(
					context, R.layout.highlights_popover_list_row_item_month,
					HighlightsService.getMonthDetails("UserId"));

			listView.setAdapter(monthsListAdapter);

		} else if (view.getLayoutId() == R.layout.highlights_popover_view_leaders) {

			SeparatedListAdapter separatedListAdapter = new SeparatedListAdapter(
					context);
			Map<String, List<LeadersTypeItem>> leadersInfoMap = HighlightsService
					.getLeadersDetails("");
			separatedListAdapter.addSection("Offense",
					new HighlightsLeadersListAdapter(context,
							R.layout.highlights_popover_list_row_item_leader,
							leadersInfoMap.get("offense")));

			separatedListAdapter.addSection("Defense",
					new HighlightsLeadersListAdapter(context,
							R.layout.highlights_popover_list_row_item_leader,
							leadersInfoMap.get("defense")));
			separatedListAdapter.addSection("Special Teams",
					new HighlightsLeadersListAdapter(context,
							R.layout.highlights_popover_list_row_item_leader,
							leadersInfoMap.get("specialTeams")));

			
			listView = (ListView) findViewById(R.id.leaders_list);

			listView.setAdapter(separatedListAdapter);
			listView.setOnItemClickListener(leadersItemClickListener);

		} else if (view.getLayoutId() == R.layout.highlights_popover_view_passing_leaders) {

			listView = (ListView) findViewById(R.id.passing_leaders_list);
			PassingLeaderListViewAdapter adapter = new PassingLeaderListViewAdapter(
					context,
					R.layout.highlights_popover_list_row_item_passing_leader,
					HighlightsService.getPassingLeadersDetails(""));
			listView.setAdapter(adapter);

			listView.setOnItemClickListener(passingLeadersItemClickListener);

		} else if (view.getLayoutId() == R.layout.highlights_popover_view_standing) {
			List<TeamItem> conferenceTeamItems = new ArrayList<TeamItem>();

			TeamItem teamItem1 = new TeamItem();
			teamItem1.setTeamName("New England");
			teamItem1.setLosses(0);
			teamItem1.setWins(5);
			teamItem1.setTeamLogo(R.drawable.teamlogo_ars_id);
			conferenceTeamItems.add(teamItem1);

			TeamItem teamItem2 = new TeamItem();
			teamItem2.setTeamName("Ny Jets");
			teamItem2.setLosses(1);
			teamItem2.setWins(4);
			teamItem2.setTeamLogo(R.drawable.teamlogo_ars_id);
			conferenceTeamItems.add(teamItem2);

			TeamItem teamItem3 = new TeamItem();
			teamItem3.setTeamName("Miami");
			teamItem3.setLosses(2);
			teamItem3.setWins(3);
			teamItem3.setTeamLogo(R.drawable.teamlogo_ars_id);
			conferenceTeamItems.add(teamItem3);

			TeamItem teamItem4 = new TeamItem();
			teamItem4.setTeamName("Buffalo");
			teamItem4.setLosses(1);
			teamItem4.setWins(0);
			teamItem4.setTeamLogo(R.drawable.teamlogo_ars_id);
			conferenceTeamItems.add(teamItem4);

			List<SectionHeaderItem> popover_nfl_standing_header = new ArrayList<SectionHeaderItem>();
			SectionHeaderItem headerItem = new SectionHeaderItem();
			headerItem.setHeadingText1("East Division");
			headerItem.setHeadingText2("W");
			headerItem.setHeadingText3("L");
			popover_nfl_standing_header.add(headerItem);

			SeparatedSectionHeaderListAdapter eastHeaderListAdapter = new SeparatedSectionHeaderListAdapter(
					context, R.layout.highlights_popover_header_standing,
					popover_nfl_standing_header);

			EmptyHeaderSeparatedListAdapter separatedListAdapter = new EmptyHeaderSeparatedListAdapter(
					context, true);

			separatedListAdapter.addSection("0", eastHeaderListAdapter);
			separatedListAdapter
					.addSection(
							"1",
							new StandingTeamListViewAdapter(
									context,
									R.layout.common_division_nfl_highlight_list_row_item_standing,
									conferenceTeamItems));

			List<SectionHeaderItem> north_popover_nfl_standing_header = new ArrayList<SectionHeaderItem>();
			SectionHeaderItem northItem = new SectionHeaderItem();
			northItem.setHeadingText1("North Division");
			northItem.setHeadingText2("W");
			northItem.setHeadingText3("L");
			north_popover_nfl_standing_header.add(northItem);
			SeparatedSectionHeaderListAdapter northHeaderListAdapter = new SeparatedSectionHeaderListAdapter(
					context, R.layout.highlights_popover_header_standing,
					north_popover_nfl_standing_header);
			separatedListAdapter.addSection("7", northHeaderListAdapter);
			separatedListAdapter
					.addSection(
							"2",
							new StandingTeamListViewAdapter(
									context,
									R.layout.common_division_nfl_highlight_list_row_item_standing,
									conferenceTeamItems));

			List<SectionHeaderItem> southPopover_nfl_standing_header = new ArrayList<SectionHeaderItem>();
			SectionHeaderItem south_HeaderItem = new SectionHeaderItem();
			south_HeaderItem.setHeadingText1("South Division");
			south_HeaderItem.setHeadingText2("W");
			south_HeaderItem.setHeadingText3("L");
			southPopover_nfl_standing_header.add(south_HeaderItem);
			SeparatedSectionHeaderListAdapter southHeaderListAdapter = new SeparatedSectionHeaderListAdapter(
					context, R.layout.highlights_popover_header_standing,
					southPopover_nfl_standing_header);
			separatedListAdapter.addSection("8", southHeaderListAdapter);
			separatedListAdapter
					.addSection(
							"3",
							new StandingTeamListViewAdapter(
									context,
									R.layout.common_division_nfl_highlight_list_row_item_standing,
									conferenceTeamItems));

			List<SectionHeaderItem> westPopover_nfl_standing_header = new ArrayList<SectionHeaderItem>();
			SectionHeaderItem westHeaderItem = new SectionHeaderItem();
			westHeaderItem.setHeadingText1("West Division");
			westHeaderItem.setHeadingText2("W");
			westHeaderItem.setHeadingText3("L");
			westPopover_nfl_standing_header.add(westHeaderItem);
			SeparatedSectionHeaderListAdapter westHeaderListAdapter = new SeparatedSectionHeaderListAdapter(
					context, R.layout.highlights_popover_header_standing,
					westPopover_nfl_standing_header);
			separatedListAdapter.addSection("9", westHeaderListAdapter);
			separatedListAdapter
					.addSection(
							"4",
							new StandingTeamListViewAdapter(
									context,
									R.layout.common_division_nfl_highlight_list_row_item_standing,
									conferenceTeamItems));

			listView = (ListView) findViewById(R.id.standing_list);

			listView.setAdapter(separatedListAdapter);
			listView.setOnItemClickListener(standingItemClickListener);
		}

	}

	/**
	 * Leaders item listner
	 */
	private OnItemClickListener leadersItemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			RelativeLayout rootView = (RelativeLayout) findViewById(R.id.gameRootView);
			if (popoverView != null) {
				popoverView.removeAllViews();
			}
			popoverView = new PopoverView(HighlightsActivity.this,
					R.layout.highlights_popover_view_passing_leaders);

			popoverView.setContentSizeForViewInPopover(new Point(330, 400));
			popoverView.setDelegate(HighlightsActivity.this);
			View button = (View) findViewById(R.id.leadersPopover);
			popoverView.showPopoverFromRectInViewGroup(rootView,
					PopoverView.getFrameForView(button),
					PopoverView.PopoverArrowDirectionUp, true);

			ImageView backImageView = (ImageView) popoverView
					.findViewById(R.id.backButtonImage);

			backImageView.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					leadersOnClickListener.onClick(popoverView);

				}
			});
		}

	};

	@Override
	public void popoverViewWillDismiss(PopoverView view) {

	}

	@Override
	public void popoverViewDidDismiss(PopoverView view) {

	}

	private OnItemClickListener passingLeadersItemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			Intent intent = new Intent(context, PlayersActivity.class);
			startActivity(intent);

		}

	};

	private OnItemClickListener standingItemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			Intent intent = new Intent(context, GameActivity.class);
			startActivity(intent);

		}

	};

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
