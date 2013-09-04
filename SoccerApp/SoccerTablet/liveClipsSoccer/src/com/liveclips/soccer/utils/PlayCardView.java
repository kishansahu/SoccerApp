/**
 * 
 */
package com.liveclips.soccer.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.liveclips.soccer.R;
import com.liveclips.soccer.imageutils.DownloadImageTask;
import com.liveclips.soccer.imageutils.DownloadImagesThreadPool;

/**
 * @author abhijitsrivastava
 * 
 */
public class PlayCardView {

	private Context context;
	private String playCardTopDetail;
	private String playCardBottomDetail;
	private int index;
	Resources resources;
	Activity activity;
	VideoView videoView;
	RelativeLayout playCardFrontSidePlaySectionImageWithPlayButtonLayout;

	static RelativeLayout playCardFrontSidePlaySectionVideoViewLayout;

	public PlayCardView() {

	}

	public PlayCardView(Context context, int index, String playCardTopDetail,
			String playCardBottomDetail, Resources resources, Activity activity) {

		this.context = context;
		this.resources = resources;
		this.activity = activity;
		this.index = index;
		this.playCardTopDetail = playCardTopDetail;
		this.playCardBottomDetail = playCardBottomDetail;
	}

	public RelativeLayout getPlayCard(
			DownloadImagesThreadPool downloadImagesThreadPool) {

		// Creating a new RelativeLayout
		final RelativeLayout playCardLayout = new RelativeLayout(context);

		// Defining the RelativeLayout layout parameters.
		RelativeLayout.LayoutParams playCardLayoutParameters = new RelativeLayout.LayoutParams(
				500, 400);
		playCardLayoutParameters.addRule(RelativeLayout.CENTER_IN_PARENT);
		playCardLayoutParameters.setMargins(20, 10, 20, 10);

		playCardLayout.setId(index * 2);
		playCardLayout.setLayoutParams(playCardLayoutParameters);

		playCardLayout.setBackgroundResource(R.color.gray_play_card);

		playCardLayout.setPadding(15, 15, 15, 15);

		// play card front side detail ********************

		final LayoutInflater inflator = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		final RelativeLayout playCardFrontSide = (RelativeLayout) inflator
				.inflate(R.layout.play_card_front_side, null);

		ImageView playCardFrontSideTopLayoutClipType = (ImageView) playCardFrontSide
				.findViewById(R.id.playCardFrontSideTopLayoutClipLogoId);
		playCardFrontSideTopLayoutClipType
				.setBackgroundResource(R.drawable.teamlogo_ars_id_small);

		ImageView playCardFrontSideTopLayoutInfoButton = (ImageView) playCardFrontSide
				.findViewById(R.id.playCardFrontSideTopLayoutInfoButtonId);

		playCardFrontSideTopLayoutInfoButton.setId(index * 200);
		TextView topDetail = (TextView) playCardFrontSide
				.findViewById(R.id.playCardFrontSideTopLayoutClipInfoId);
		topDetail.setText(playCardTopDetail);
		topDetail.setTextColor(Color.WHITE);

		topDetail.setId(index * 4000);
		TextView bottomDetail = (TextView) playCardFrontSide
				.findViewById(R.id.playCardFrontSideBottomLayoutTextViewId);
		bottomDetail.setText(playCardBottomDetail);
		bottomDetail.setTextColor(Color.WHITE);

		bottomDetail.setId(index * 60000);
		playCardFrontSidePlaySectionImageWithPlayButtonLayout = (RelativeLayout) inflator
				.inflate(R.layout.play_card_front_side_thumbnail, null);

		ImageView playCardFrontSidePlaySectionPlayButton = (ImageView) playCardFrontSidePlaySectionImageWithPlayButtonLayout
				.findViewById(R.id.playCardFrontSidePlaySectionImageWithPlayButtonPlayButtonId);

		ImageView playCardFrontSidePlaySectionImage = (ImageView) playCardFrontSidePlaySectionImageWithPlayButtonLayout
				.findViewById(R.id.playCardFrontSidePlaySectionImageWithPlayButtonImageViewId);

		final ImageButton videoSizeButton = (ImageButton) playCardFrontSide
				.findViewById(R.id.playCardFrontSideBottomLayoutImageButtonId);
		videoSizeButton.setBackgroundResource(R.drawable.full_screen_button);
		videoSizeButton.setVisibility(View.INVISIBLE);

		new DownloadImageTask(playCardFrontSidePlaySectionImage)
				.execute("http://www4.pictures.zimbio.com/gi/Theo+Walcott+Arsenal+v+Manchester+United+Premier+r0XuCess83jl.jpg");

		// downloadImagesThreadPool.submit(playCardFrontSidePlaySectionImage,
		// "http://si.wsj.net/public/resources/images/NA-BU548_NFL_G_20130111183225.jpg");
		playCardFrontSidePlaySectionImage.setId(index * 90000);
		final RelativeLayout playCardFrontSidePlaySectionLayout = (RelativeLayout) playCardFrontSide
				.findViewById(R.id.playCardFrontSidePlaySectionlayoutId);
		playCardFrontSidePlaySectionLayout
				.addView(playCardFrontSidePlaySectionImageWithPlayButtonLayout);

		// playCardLayout.addView(playCardFrontSide);
		playCardLayout.addView(playCardFrontSide);

		// play card back side details ****************

		final RelativeLayout playCardBackSide = (RelativeLayout) inflator
				.inflate(R.layout.play_card_back_side, null);

		ImageView playCardBackSideDoneButton = (ImageView) playCardBackSide
				.findViewById(R.id.playCardBackSideTopLayoutDoneButtonId);

		TextView playCardBackSideTopLayoutTextView = (TextView) playCardBackSide
				.findViewById(R.id.playCardBackSideTopLayoutTextViewId);
		playCardBackSideTopLayoutTextView.setText(playCardTopDetail);
		playCardBackSideTopLayoutTextView.setTextColor(Color.WHITE);

		final WebView w = (WebView) playCardBackSide
				.findViewById(R.id.playCardBackSideTimeLineWebView);
		w.getSettings().setLoadWithOverviewMode(true);
		w.getSettings().setUseWideViewPort(true);
		WebSettings webSettings = w.getSettings();
		// webSettings.setBuiltInZoomControls(true);
		w.setWebChromeClient(new WebChromeClient());
		webSettings.setLoadsImagesAutomatically(true);
		webSettings.setPluginsEnabled(true);

		webSettings.setJavaScriptEnabled(true);
		w.loadUrl("file:///android_asset/handle.html");

		w.setWebViewClient(new WebViewClient() {

			public void onPageFinished(WebView view, String url) {
				Log.e("intent value", "start");

				loadValueToPage(w);

			}
		});

		TextView playCardBackSidePlayerCardsLayoutCurrentPlayTextView = (TextView) playCardBackSide
				.findViewById(R.id.playCardBackSidePlayerCardsLayoutCurrentPlayTextViewId);
		playCardBackSidePlayerCardsLayoutCurrentPlayTextView
				.setText("IN THIS PLAY");
		playCardBackSidePlayerCardsLayoutCurrentPlayTextView
				.setTextColor(Color.WHITE);

		TextView playCardBackSidePlayerCardsLayoutMorePlayTextView = (TextView) playCardBackSide
				.findViewById(R.id.playCardBackSidePlayerCardsLayoutMorePlayTextViewId);
		playCardBackSidePlayerCardsLayoutMorePlayTextView.setText("MORE PLAYS");
		playCardBackSidePlayerCardsLayoutMorePlayTextView
				.setTextColor(Color.WHITE);

		TextView playCardBackSideMorePlaysThisDriveTextView = (TextView) playCardBackSide
				.findViewById(R.id.playCardBackSideMorePlaysThisDriveTextView);
		playCardBackSideMorePlaysThisDriveTextView.setText("THIS DRIVE");
		playCardBackSideMorePlaysThisDriveTextView.setTextColor(Color.WHITE);

		TextView playCardBackSideMorePlaysLongPassesTextView = (TextView) playCardBackSide
				.findViewById(R.id.playCardBackSideMorePlaysLongPassesTextViewId);
		playCardBackSideMorePlaysLongPassesTextView.setText("LONG PASSES");
		playCardBackSideMorePlaysLongPassesTextView.setTextColor(Color.WHITE);

		TextView playCardBackSideMorePlays1stDownTextView = (TextView) playCardBackSide
				.findViewById(R.id.playCardBackSideMorePlays1stDownTextViewId);
		playCardBackSideMorePlays1stDownTextView.setText("1ST DOWN");
		playCardBackSideMorePlays1stDownTextView.setTextColor(Color.WHITE);

		LinearLayout playCardBackSidePlayerCardLayout = (LinearLayout) playCardBackSide
				.findViewById(R.id.playCardBackSidePlayerCardsLayoutHorizontalScrollViewLinearLayoutId);

		final HorizontalScrollView playCardsHorizontalScrollView = (HorizontalScrollView) activity
				.findViewById(R.id.horizontalScrollViewForVideosId);
		final HorizontalScrollView playerCardsInsidePlayCardHorizontalScrollView = (HorizontalScrollView) playCardBackSide
				.findViewById(R.id.playCardBackSidePlayerCardsLayoutHorizontalScrollViewId);

		RatingBar playCardFrontSideTopLayoutRatingBar = (RatingBar) playCardFrontSide
				.findViewById(R.id.playCardFrontSideTopLayoutRatingBarId);

		playCardsHorizontalScrollView
				.setOnTouchListener(new View.OnTouchListener() {

					public boolean onTouch(View v, MotionEvent event) {
						Log.v("playCardView", "PARENT TOUCH");
						playerCardsInsidePlayCardHorizontalScrollView
								.requestDisallowInterceptTouchEvent(false);
						return false;
					}
				});
		playerCardsInsidePlayCardHorizontalScrollView
				.setOnTouchListener(new View.OnTouchListener() {

					public boolean onTouch(View v, MotionEvent event) {
						Log.v("playCardView", "CHILD TOUCH");
						// Disallow the touch request for parent scroll on touch
						// of child view
						playCardsHorizontalScrollView
								.requestDisallowInterceptTouchEvent(true);
						return false;
					}
				});

		int playerPic[] = { R.drawable.messi, R.drawable.cri_ron_pic,
				R.drawable.messi, R.drawable.cri_ron_pic, R.drawable.messi };
		String playerName[] = { "Messi", "Ronaldo", "Messi", "Ronaldo", "Messi" };
		String playerPosition[] = { "#12 | QB", "#22 | RB 7REC-",
				"#83 | WR 7REC-", "#87 | TR", "#59 | LB" };
		String playerDetail[] = { "YDS , 2TD , 1 INT", "83 YDS 0 TD",
				"YDS , 2TD , 1 INT", "83 YDS 0 TD", "YDS , 2TD , 1 INT" };

		for (int i = 0; i < 5; i++) {
			RelativeLayout playCardBackSidePlayerCards = (RelativeLayout) inflator
					.inflate(R.layout.play_card_back_side_player_card, null);

			ImageView playCardBackSidePlayerCardLayoutPlayerImageView = (ImageView) playCardBackSidePlayerCards
					.findViewById(R.id.playCardBackSidePlayerCardLayoutPlayerImageViewId);
			playCardBackSidePlayerCardLayoutPlayerImageView
					.setImageResource(playerPic[i]);

			TextView playCardBackSidePlayerCardLayoutPlayerName = (TextView) playCardBackSidePlayerCards
					.findViewById(R.id.playCardBackSidePlayerCardLayoutPlayerNameId);
			playCardBackSidePlayerCardLayoutPlayerName.setText(playerName[i]);
			playCardBackSidePlayerCardLayoutPlayerName
					.setTextColor(Color.WHITE);

			TextView playCardBackSidePlayerCardLayoutPlayerInfo = (TextView) playCardBackSidePlayerCards
					.findViewById(R.id.playCardBackSidePlayerCardLayoutPlayInfoId);
			playCardBackSidePlayerCardLayoutPlayerInfo
					.setText(playerPosition[i]);
			playCardBackSidePlayerCardLayoutPlayerInfo
					.setTextColor(Color.WHITE);

			playCardBackSidePlayerCardLayout
					.addView(playCardBackSidePlayerCards);
		}

		playCardFrontSideTopLayoutInfoButton
				.setOnTouchListener(new OnTouchListener() {

					@Override
					public boolean onTouch(View v, MotionEvent event) {
						Animation animation = AnimationUtils.loadAnimation(
								context, R.anim.alpha);
						playCardLayout.removeAllViews();
						playCardLayout.addView(playCardBackSide);
						playCardLayout.startAnimation(animation);

						// AnimationFactory.flipTransition(playCardViewAnimator,
						// FlipDirection.LEFT_RIGHT,playCardLayout,playCardBackSide,playCardFrontSide);
						return false;
					}
				});

		playCardBackSideDoneButton.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				Animation animation = AnimationUtils.loadAnimation(context,
						R.anim.alpha);
				playCardLayout.removeAllViews();
				playCardLayout.addView(playCardFrontSide);
				playCardLayout.startAnimation(animation);

				// AnimationFactory.flipTransition(playCardViewAnimator,
				// FlipDirection.RIGHT_LEFT,playCardLayout,playCardFrontSide,playCardBackSide);

				return false;
			}
		});

		playCardFrontSidePlaySectionPlayButton
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						playCardFrontSidePlaySectionLayout
								.removeView(playCardFrontSidePlaySectionImageWithPlayButtonLayout);

						// playCardFrontSidePlaySectionVideoViewLayout =
						// getPlayCardFrontSidePlaySectionVideoLayout();

						// playCardFrontSidePlaySectionLayout.addView(playCardFrontSidePlaySectionVideoViewLayout);
						playCardFrontSidePlaySectionLayout
								.addView(getPlayCardFrontSidePlaySectionVideoLayout());
					}
				});

		playCardFrontSideTopLayoutRatingBar.setRating(3.0f);

		return playCardLayout;
	}

	private void loadValueToPage(WebView w) {
		w.loadUrl("javascript:loadValueToPage('Liver Pool','Aston Villa','76','18.00,Red card message|25,second message','35,first message|30.00,second message','23,mn','45,mn','34,bv','42,kj','45,mn','60,mn');executeCanvas();");
	}

	protected VideoView getPlayCardFrontSidePlaySectionVideoLayout() {
		final LayoutInflater inflator = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		/*
		 * RelativeLayout playCardFrontSidePlaySectionVideoLayout =
		 * (RelativeLayout) inflator
		 * .inflate(R.layout.play_card_front_side_play_section_video, null);
		 * videoView = new VideoView(context);
		 */
		// if(videoView == null){
		// videoView = (VideoView)
		// inflator.inflate(R.layout.play_card_front_side_play_section_video,
		// null);
		// }
		// videoView = (VideoView)
		// activity.findViewById(R.id.playCardFrontSidePlaySectionVideoLayoutVideoViewId);
		videoView = new VideoView(context);
		RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.MATCH_PARENT);
		videoView.setLayoutParams(layoutParams);
		videoView.setId(index * 15000);

		if (videoView.isPlaying()) {
			videoView.stopPlayback();
			RelativeLayout videoViewParentLayout = (RelativeLayout) videoView
					.getParent();
			videoViewParentLayout.removeAllViews();
			videoViewParentLayout
					.addView(playCardFrontSidePlaySectionImageWithPlayButtonLayout);
		}
		// MediaController mc = new
		// LiveClipsMediaController(videoView.getContext(), activity,
		// videoView);

		// mc.setMediaPlayer(videoView);
		// videoView.setMediaController(mc);
		RelativeLayout.LayoutParams playCardLayoutParameters = new RelativeLayout.LayoutParams(
				500, 400);

		videoView.setLayoutParams(playCardLayoutParameters);
		// playCardFrontSidePlaySectionVideoLayout.addView(videoView);
		// videoView.setVideoURI(Uri
		// .parse("http://commonsware.com/misc/test2.3gp"));
		videoView.setVideoURI(Uri
				.parse("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4"));

		// playCardFrontSidePlaySectionVideoView.setVideoURI(Uri.parse("http://localhost:8080/nflvideo.3gp"));
		videoView.start();

		// return playCardFrontSidePlaySectionVideoLayout;
		return videoView;
	}

}
