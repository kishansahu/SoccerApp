package com.liveclips.soccer.commons;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.liveclips.soccer.R;
import com.liveclips.soccer.imageutils.DownloadImagesThreadPool;
import com.liveclips.soccer.utils.PlayCardView;
import com.liveclips.soccer.utils.SoccerUtils;

public class PlayCards {

	public static void getPlayCards(Activity activityName, Context contextName,
			String filterType) {

		ArrayList<String> playCardTopDetail = new ArrayList<String>();

		playCardTopDetail.add("GOAL");
		playCardTopDetail.add("Yellow Card");
		playCardTopDetail.add("Off Side");
		playCardTopDetail.add("Red Card");
		playCardTopDetail.add("Foul");
		playCardTopDetail.add("GOAL");
		playCardTopDetail.add("Yellow Card");
		playCardTopDetail.add("Off Side");
		playCardTopDetail.add("Red Card");
		playCardTopDetail.add("Foul");

		ArrayList<String> playCardBottomDetail = new ArrayList<String>();
		playCardBottomDetail.add("Messi (65')");
		playCardBottomDetail.add("Ronaldo (45')");
		playCardBottomDetail.add("Messi (65')");
		playCardBottomDetail.add("Ronaldo (45')");
		playCardBottomDetail.add("Messi (65')");
		playCardBottomDetail.add("Ronaldo (45')");
		playCardBottomDetail.add("Messi (65')");
		playCardBottomDetail.add("Ronaldo (45')");
		playCardBottomDetail.add("Messi (65')");
		playCardBottomDetail.add("Ronaldo (45')");

		// The parent LinearLayout for playCards
		LinearLayout playCardParentLinearLayout = (LinearLayout) activityName
				.findViewById(R.id.parentLayoutOfPlayCardsId);

		DownloadImagesThreadPool downloadImagesThreadPool = new DownloadImagesThreadPool();

		RelativeLayout selectedCategoryLayoutId = (RelativeLayout) activityName
				.findViewById(R.id.selectedCategoryLayoutId);

		int colors[] = SoccerUtils.getDefaultColorTheme();
		GradientDrawable gradientDrawable = new GradientDrawable(
				GradientDrawable.Orientation.LEFT_RIGHT, colors);
		selectedCategoryLayoutId.setBackgroundDrawable(gradientDrawable);

		TextView selectedCategoryTextView = (TextView) activityName
				.findViewById(R.id.selectedCategoryTextViewId);
		selectedCategoryTextView.setText("Arsenal");

		for (int index = 0; index < 6; index++) {

			playCardParentLinearLayout.addView(getPlayCardView(contextName,
					index, playCardTopDetail.get(index),
					playCardBottomDetail.get(index),
					activityName.getResources(), downloadImagesThreadPool,activityName));
			View marginView = new View(activityName);
			marginView.setLayoutParams(new LayoutParams(20, 0));
			playCardParentLinearLayout.addView(marginView);
		}

		downloadImagesThreadPool.tearDown(6);
	}

	private static View getPlayCardView(Context context2, int index,
			String playCardTopDetail, String playCardBottomDetail,
			Resources resources,
			DownloadImagesThreadPool downloadImagesThreadPool, Activity activity) {

		return new PlayCardView(context2, index, playCardTopDetail,
				playCardBottomDetail, resources, activity).getPlayCard(downloadImagesThreadPool);
	}
}
