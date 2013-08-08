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
import android.widget.Toast;

import com.liveclips.soccer.R;
import com.liveclips.soccer.adapter.EmptyHeaderSeparatedListAdapter;
import com.liveclips.soccer.adapter.HighlightsLeadersListAdapter;
import com.liveclips.soccer.adapter.HighlightsWeekListAdapter;
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

	private ArrayList<String> playCardTopDetail = new ArrayList<String>();

	private ArrayList<String> playCardBottomDetail = new ArrayList<String>();
	private Activity highlightsActivity;
	private Context context;

	private ListView listView;

	private PopoverView popoverView;

	private DownloadImagesThreadPool downloadImagesThreadPool;

	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.highlights_activity);

		context = this;
		highlightsActivity= this;
		downloadImagesThreadPool = new DownloadImagesThreadPool();

		createCustomActionBar();

		prepareVideoView();

	}

	protected void createCustomActionBar() {

		ActionBar actionBar = getActionBar();
		View mActionBarView = getLayoutInflater().inflate(
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
		SoccerUtils.setAlertPopover(highlightsActivity, popoverView, alertButton);

		Drawable d = getResources().getDrawable(
				R.drawable.orange_gradient_background);
		actionBar.setBackgroundDrawable(d);

		LinearLayout weekLinearLayout = (LinearLayout) mActionBarView
				.findViewById(R.id.weekLayout);
		weekLinearLayout.setOnClickListener(weekOnClickListener);

		TextView leaderLinearLayout = (TextView) mActionBarView
				.findViewById(R.id.leadersLayout);
		leaderLinearLayout.setOnClickListener(leaderOnClickListener);

		TextView milestonesLinearLayout = (TextView) mActionBarView
				.findViewById(R.id.milestonesLayout);
		milestonesLinearLayout.setOnClickListener(milestonesOnClickListener);

		TextView standingLinearLayout = (TextView) mActionBarView
				.findViewById(R.id.standingLayout);
		standingLinearLayout.setOnClickListener(standingOnClickListener);
	}

	/**
	 * Week listener
	 */
	private OnClickListener weekOnClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			RelativeLayout rootView = (RelativeLayout) findViewById(R.id.gameRootView);
			if (popoverView != null) {
				popoverView.removeAllViews();
			}
			popoverView = new PopoverView(HighlightsActivity.this,
					R.layout.highlights_popover_view_week);

			popoverView.setContentSizeForViewInPopover(new Point(210, 300));
			popoverView.setDelegate(HighlightsActivity.this);
			View button = (View) findViewById(R.id.weekLayout);
			popoverView.showPopoverFromRectInViewGroup(rootView,
					PopoverView.getFrameForView(button),
					PopoverView.PopoverArrowDirectionUp, true);
			ImageView doneImageView = (ImageView) popoverView
					.findViewById(R.id.doneButtonImage);
			doneImageView.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					popoverView.removeAllViews();

				}
			});

		}
	};

	/**
	 * leaders listener
	 */
	private OnClickListener leaderOnClickListener = new OnClickListener() {

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
			View button = (View) findViewById(R.id.leadersLayout);
			popoverView.showPopoverFromRectInViewGroup(rootView,
					PopoverView.getFrameForView(button),
					PopoverView.PopoverArrowDirectionUp, true);
			ImageView doneImageView = (ImageView) popoverView
					.findViewById(R.id.doneButtonImage);
			doneImageView.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					popoverView.removeAllViews();

				}
			});

		}
	};

	private OnClickListener milestonesOnClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			Toast.makeText(context, "Milestone Handler", Toast.LENGTH_SHORT);
			/*
			 * RelativeLayout rootView = (RelativeLayout)
			 * findViewById(R.id.highlightsRootView); if (popoverView != null) {
			 * popoverView.removeAllViews(); } popoverView = new
			 * PopoverView(NFLHighlightsActivity.this,
			 * R.layout.popover_nfl_week_item);
			 * 
			 * popoverView.setContentSizeForViewInPopover(new Point(320, 400));
			 * popoverView.setDelegate(NFLHighlightsActivity.this); View button
			 * = (View) findViewById(R.id.weekLayout);
			 * popoverView.showPopoverFromRectInViewGroup(rootView,
			 * PopoverView.getFrameForView(button),
			 * PopoverView.PopoverArrowDirectionUp, true);
			 */

		}
	};

	/**
	 * standing listener
	 */
	private OnClickListener standingOnClickListener = new OnClickListener() {

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
			View button = (View) findViewById(R.id.standingLayout);
			popoverView.showPopoverFromRectInViewGroup(rootView,
					PopoverView.getFrameForView(button),
					PopoverView.PopoverArrowDirectionUp, true);

			ImageView doneImageView = (ImageView) popoverView
					.findViewById(R.id.doneButtonImage);
			doneImageView.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					popoverView.removeAllViews();

				}
			});

		}
	};

	private void prepareVideoView() {

		LinearLayout playCardParentLinearLayout = (LinearLayout) findViewById(R.id.parentLayoutOfPlayCardsId);

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
		Log.d("layoutId", String.valueOf(view.getLayoutId()));

	}

	@Override
	public void popoverViewDidShow(PopoverView view) {
		Log.d("layoutId", String.valueOf(view.getLayoutId()));
		if (view.getLayoutId() == R.layout.highlights_popover_view_week) {
			Log.d("id", String.valueOf(view.getId()));

			listView = (ListView) findViewById(R.id.week_list);
			HighlightsWeekListAdapter weekListAdapter = new HighlightsWeekListAdapter(
					context,
					R.layout.highlights_popover_list_row_item_week,
					HighlightsService.getWeeksDetails("UserId"));

			listView.setAdapter(weekListAdapter);

		} else if (view.getLayoutId() == R.layout.highlights_popover_view_leaders) {

			SeparatedListAdapter separatedListAdapter = new SeparatedListAdapter(
					context);
			Map<String, List<LeadersTypeItem>> leadersInfoMap = HighlightsService
					.getLeadersDetails("");
			separatedListAdapter
					.addSection(
							"Offense",
							new HighlightsLeadersListAdapter(
									context,
									R.layout.highlights_popover_list_row_item_leader,
									leadersInfoMap.get("offense")));

			separatedListAdapter
					.addSection(
							"Defense",
							new HighlightsLeadersListAdapter(
									context,
									R.layout.highlights_popover_list_row_item_leader,
									leadersInfoMap.get("defense")));
			separatedListAdapter
					.addSection(
							"Special Teams",
							new HighlightsLeadersListAdapter(
									context,
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
			View button = (View) findViewById(R.id.leadersLayout);
			popoverView.showPopoverFromRectInViewGroup(rootView,
					PopoverView.getFrameForView(button),
					PopoverView.PopoverArrowDirectionUp, true);

			ImageView backImageView = (ImageView) popoverView
					.findViewById(R.id.backButtonImage);

			backImageView.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					leaderOnClickListener.onClick(popoverView);

				}
			});

			ImageView doneImageView = (ImageView) popoverView
					.findViewById(R.id.doneButtonImage);

			doneImageView.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					popoverView.removeAllViews();

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

}
