package com.liveclips.soccer.utils;

import android.app.Activity;
import android.graphics.drawable.Drawable;

public class ImageProcessingUtil {

	/**
	 * get the team banner from drawable 
	 * @param activity
	 * @param teamId
	 * @return
	 */
	public static Drawable getTeamLogoImageDrawableByTeamId(Activity activity, String teamId) {
		String teamLogoName = "teamlogo_" + teamId;
		String uri = "@drawable/" + teamLogoName;
		int imageResource = activity.getResources().getIdentifier(uri, null, activity.getPackageName());
		Drawable drawable = activity.getResources().getDrawable(imageResource);
		return drawable;
	}
	
	/**
	 * get the team banner from resourece folder
	 * @param activity
	 * @param teamId
	 * @return
	 */
	public static int getTeamLogoImageResourceByTeamId(Activity activity, String teamId) {
		String teamLogoName = "teamlogo_" + teamId;
		String uri = "@drawable/" + teamLogoName;
		int imageResource = activity.getResources().getIdentifier(uri, null, activity.getPackageName());
		return imageResource;
	}
	
	public static int getImageResourceByImageName(Activity activity, String imageName) {
		String uri = "@drawable/" + imageName;
		int imageResource = activity.getResources().getIdentifier(uri, null, activity.getPackageName());
		return imageResource;
	}
}
