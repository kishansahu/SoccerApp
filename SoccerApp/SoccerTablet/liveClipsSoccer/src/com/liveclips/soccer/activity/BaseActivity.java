package com.liveclips.soccer.activity;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.liveclips.soccer.R;
import com.liveclips.soccer.R.color;
import com.liveclips.soccer.fragment.TopicMenuFragment;
import com.liveclips.soccer.session.ApplicationSession;



public class BaseActivity extends Activity {

	RelativeLayout commonFragmentMenuHeader;
	View fragmentMenuHeaderView;
	View sliderView, mActionBarView;
	FragmentManager fragmentManager;
	FragmentTransaction fragmentTransaction;
	Fragment mainMenuFragment;
	RelativeLayout fullScreenView, actionbarcontentscontainer;
	Animation animMove;

	private void performSliderAction() {

		fragmentManager = getFragmentManager();
		fragmentTransaction = fragmentManager.beginTransaction();
		Fragment mainMenuFragment = null;

		if (mainMenuFragment == null) {
			mainMenuFragment = new TopicMenuFragment();
			((ApplicationSession) getApplication())
					.setMainMenuFragment(mainMenuFragment);
		}
		fragmentTransaction.replace(R.id.menuFragment, mainMenuFragment);
		fragmentTransaction.commit();

		ImageView closeBtnImageView = (ImageView) getActionBar()
				.getCustomView().findViewById(R.id.closeButtonHeader);
		closeBtnImageView.setVisibility(View.VISIBLE);
		closeBtnImageView.setOnClickListener(closeButtonListener);

		View sliderView = findViewById(R.id.sliderView);
		if (sliderView.getVisibility() == View.VISIBLE) {
			sliderView.setVisibility(View.INVISIBLE);
		}
	};

	private OnClickListener closeButtonListener = new OnClickListener() {

		@Override
		public void onClick(View v) {

			fragmentTransaction = fragmentManager.beginTransaction();
			mainMenuFragment = getFragmentManager().findFragmentById(
					R.id.menuFragment);
			if (mainMenuFragment.isVisible()) {

				fragmentTransaction.hide(mainMenuFragment);
				commonFragmentMenuHeader.setVisibility(View.INVISIBLE);
				sliderView.setVisibility(View.VISIBLE);
			} else {
				fragmentTransaction.show(mainMenuFragment);
				fragmentTransaction.addToBackStack(null);
			}
			fragmentTransaction.commit();
			fullScreenView.setVisibility(View.INVISIBLE);
		}

	};

	@Override
	protected void onStart() {
		super.onStart();
		fragmentManager = getFragmentManager();
		ActionBar actionBar = getActionBar();
		mActionBarView = actionBar.getCustomView();
		Drawable d = getResources().getDrawable(R.drawable.black_bar);
		actionBar.setBackgroundDrawable(d);
		fullScreenView = (RelativeLayout) findViewById(R.id.fullScreenView);
		sliderView = mActionBarView.findViewById(R.id.sliderView);
		commonFragmentMenuHeader = (RelativeLayout) mActionBarView
				.findViewById(R.id.commonFragmentMenuHeader);
		
		sliderView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				fragmentTransaction = fragmentManager.beginTransaction();
				mainMenuFragment = getFragmentManager().findFragmentById(
						R.id.menuFragment);
				mainMenuFragment = new TopicMenuFragment();
				((ApplicationSession) getApplication())
						.setMainMenuFragment(mainMenuFragment);

				commonFragmentMenuHeader.setVisibility(View.VISIBLE);
				performSliderAction();
				
				fullScreenView.setVisibility(View.VISIBLE);
				//fullScreenView.setOnClickListener(closeButtonListener);
			}
		});

	}

}
