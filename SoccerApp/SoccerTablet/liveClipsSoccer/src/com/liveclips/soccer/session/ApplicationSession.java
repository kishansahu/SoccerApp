package com.liveclips.soccer.session;

import android.app.Application;
import android.app.Fragment;

public class ApplicationSession extends Application{

	private Fragment mainMenuFragment;
	private Fragment topicMenuFragment;

	public Fragment getMainMenuFragment() {
		return mainMenuFragment;
	}

	public void setMainMenuFragment(Fragment mainMenuFragment) {
		this.mainMenuFragment = mainMenuFragment;
	}

	public Fragment getTopicMenuFragment() {
		return topicMenuFragment;
	}

	public void setTopicMenuFragment(Fragment topicMenuFragment) {
		this.topicMenuFragment = topicMenuFragment;
	}

}
