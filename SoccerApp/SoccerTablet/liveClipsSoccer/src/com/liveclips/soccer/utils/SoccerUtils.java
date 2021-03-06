package com.liveclips.soccer.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.liveclips.soccer.R;
import com.liveclips.soccer.adapter.SeparatedListAdapter;
import com.liveclips.soccer.adapter.TeamMenuListViewAdapter;
import com.liveclips.soccer.database.DatabaseHelper;
import com.liveclips.soccer.dto.LeagueTeamDto;
import com.liveclips.soccer.model.League;
import com.liveclips.soccer.model.TeamAlertSetting;
import com.liveclips.soccer.model.TeamItem;
import com.liveclips.soccer.model.TeamMenuItems;
import com.liveclips.soccer.popover.PopoverView;
import com.liveclips.soccer.popover.PopoverView.PopoverViewDelegate;

public class SoccerUtils {

	private static boolean isScoreBannerShrinked;
	
	public static boolean isScoreBannerShrinked() {
		return isScoreBannerShrinked;
	}

	static ToggleButton allPLaysToggleButton, topPLaysToggleButton,
			scoringPLaysToggleButton, savesToggleButton,
			redCardsToggleButton,yellowCardsToggleButton, playsOfTheGameToggleButton;

	static SeekBar basicAlertLevelSeekbar;

	public static void setScoreBannerShrinked(boolean isScoreBannerShrinked) {
		SoccerUtils.isScoreBannerShrinked = isScoreBannerShrinked;
	}

	public static int getScreenWidthDimensions(Activity activity) {
		DisplayMetrics displaymetrics = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay()
				.getMetrics(displaymetrics);
		int width = displaymetrics.widthPixels;
		return width;
	}

	/*public static void changeAbilityContentOfView(View v, boolean ability) {
		int size = (int) ((ViewGroup) v).getChildCount();
		for (int j = 0; j <= size; j++) {
			View child = null;
			try {
				child = ((ViewGroup) v).getChildAt(j);
			} catch (Exception e) {
				child.setEnabled(ability);
			}
			if (child != null) {
				try {

					for (int i = 0; i <= ((ViewGroup) child).getChildCount(); i++) {
						int childsize = 0;
						View nextChild = null;
						try {
							nextChild = ((ViewGroup) child).getChildAt(i);
						} catch (Exception e) {
							nextChild.setEnabled(ability);
						}

						try {
							childsize = ((ViewGroup) nextChild).getChildCount();
							if (childsize > 0) {
								changeAbilityContentOfView(nextChild, ability);
							} else if (nextChild != null) {
								nextChild.setEnabled(ability);
							}
						} catch (Exception e) {
							if (nextChild != null) {
								nextChild.setEnabled(ability);
							}
						}
					}
				} catch (Exception e) {
					child.setEnabled(ability);
				}
			}
		}

	}*/

	public static int getScreenHeightDimensions(Activity activity) {
		DisplayMetrics displaymetrics = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay()
				.getMetrics(displaymetrics);
		int height = displaymetrics.heightPixels;
		return height;
	}

	public static int convertDensityPixelToPixel(Context context, int i) {
		return (int) ((i * context.getResources().getDisplayMetrics().density) + 0.5);
	}

	public static int convertPixelToDensityPixel(Context context, int px) {
		return (int) ((px / context.getResources().getDisplayMetrics().density) + 0.5);
	}

	/**
	 * Method calculates the width of the bar which is used to indicate the
	 * YardsBarWidth.
	 */
	public static Integer getYardsBarWidth(String score, String highestScore) {

		Float barWidthNum = 1 + (425 / Float.parseFloat(highestScore))
				* (Float.parseFloat(score));
		Integer barWidth = Math.round(barWidthNum);
		return barWidth;
	}

	public static String getHighestNumber(String score1, String score2) {
		Float scoreFirst = Float.parseFloat(score1);
		Float scoreSecond = Float.parseFloat(score2);
		Float highestScore;
		if (scoreFirst >= scoreSecond) {
			highestScore = scoreFirst;
		} else {
			highestScore = scoreSecond;
		}
		return highestScore.toString();
	}

	public static Bitmap getBitmapFromURL(String src) {
		try {
			URL url = new URL(src);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setDoInput(true);
			connection.connect();
			InputStream input = connection.getInputStream();
			Bitmap myBitmap = BitmapFactory.decodeStream(input);
			return myBitmap;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static TeamAlertSetting customizeAlertSettingForTeam(
			final Activity activity, final TeamAlertSetting teamAlertSetting) {

		allPLaysToggleButton = (ToggleButton) activity
				.findViewById(R.id.all_plays_toggle_button);
		allPLaysToggleButton.setChecked(teamAlertSetting.isAllPlays());
		allPLaysToggleButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				teamAlertSetting.setAllPlays(allPLaysToggleButton.isChecked());
			}
		});

		topPLaysToggleButton = (ToggleButton) activity
				.findViewById(R.id.top_plays_toggle_button);
		topPLaysToggleButton.setChecked(teamAlertSetting.isTopPlays());
		topPLaysToggleButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				teamAlertSetting.setTopPlays(topPLaysToggleButton.isChecked());
			}
		});

		scoringPLaysToggleButton = (ToggleButton) activity
				.findViewById(R.id.scoring_plays_toggle_button);
		scoringPLaysToggleButton.setChecked(teamAlertSetting.isScoringPlays());
		scoringPLaysToggleButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				teamAlertSetting.setScoringPlays(scoringPLaysToggleButton
						.isChecked());
			}
		});

		savesToggleButton = (ToggleButton) activity
				.findViewById(R.id.saves_toggle_button);
		savesToggleButton.setChecked(teamAlertSetting
				.isTurnOversPlays());
		savesToggleButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				teamAlertSetting.setTurnOversPlays(savesToggleButton
						.isChecked());
			}
		});
		redCardsToggleButton = (ToggleButton) activity
				.findViewById(R.id.red_cards_toggle_button);
		redCardsToggleButton.setChecked(teamAlertSetting.isRedZonePlays());
		redCardsToggleButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				teamAlertSetting.setRedZonePlays(redCardsToggleButton
						.isChecked());
			}
		});
		playsOfTheGameToggleButton = (ToggleButton) activity
				.findViewById(R.id.plays_of_the_game_toggle_button);
		playsOfTheGameToggleButton.setChecked(teamAlertSetting.isPlaysOfGame());
		playsOfTheGameToggleButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				teamAlertSetting.setPlaysOfGame(playsOfTheGameToggleButton
						.isChecked());
			}
		});

		/**
		 * Basic alert seek bar handler
		 */

		basicAlertLevelSeekbar = (SeekBar) activity
				.findViewById(R.id.basic_alertlevel_seekBar);
		basicAlertLevelSeekbar.setProgress(teamAlertSetting.getBasicAlert());
		basicAlertLevelSeekbar
				.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
					int progressChanged = 0;
					int progressBarMaxRange = 0;

					public void onProgressChanged(SeekBar seekBar,
							int progress, boolean fromUser) {
						progressChanged = progress;
						progressBarMaxRange = seekBar.getMax();
					}

					public void onStopTrackingTouch(SeekBar seekBar) {
						teamAlertSetting.setBasicAlert(progressChanged);
					}

					@Override
					public void onStartTrackingTouch(SeekBar seekBar) {
						// TODO Auto-generated method stub

					}
				});
		return teamAlertSetting;
	}

	public static boolean isEmailValid(String email) {
		boolean isValid = false;
		String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
		CharSequence inputStr = email;
		Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(inputStr);
		if (matcher.matches()) {
			isValid = true;
		}
		return isValid;
	}

	public static int[] getDefaultColorTheme() {
		int[] colors = { 0xff7A7979, 0xff969191, 0xffB5B1B1 };
		return colors;
	}
	public static void setAlertSettingForJsonString(final Activity activity,
			 final TeamAlertSetting teamAlertSetting) {
		
		allPLaysToggleButton = (ToggleButton) activity
				.findViewById(R.id.all_plays_toggle_button);
		allPLaysToggleButton.setChecked(teamAlertSetting.isAllPlays());
	

		topPLaysToggleButton = (ToggleButton) activity
				.findViewById(R.id.top_plays_toggle_button);
		topPLaysToggleButton.setChecked(teamAlertSetting.isTopPlays());


		scoringPLaysToggleButton = (ToggleButton) activity
				.findViewById(R.id.scoring_plays_toggle_button);
		scoringPLaysToggleButton.setChecked(teamAlertSetting.isScoringPlays());


		savesToggleButton = (ToggleButton) activity
				.findViewById(R.id.saves_toggle_button);
		savesToggleButton.setChecked(teamAlertSetting.isTurnOversPlays());
	
		redCardsToggleButton = (ToggleButton) activity
				.findViewById(R.id.red_cards_toggle_button);
		redCardsToggleButton.setChecked(teamAlertSetting.isRedZonePlays());

		playsOfTheGameToggleButton = (ToggleButton) activity
				.findViewById(R.id.plays_of_the_game_toggle_button);
		playsOfTheGameToggleButton.setChecked(teamAlertSetting.isPlaysOfGame());


		/**
		 * Basic alert seek bar handler
		 */

		basicAlertLevelSeekbar = (SeekBar) activity
				.findViewById(R.id.basic_alertlevel_seekBar);
		basicAlertLevelSeekbar.setProgress(teamAlertSetting.getBasicAlert());
	
	}

	public static void setUserPreferredAlertSettings(final Activity activity,
			final TextView alertButton) {
		allPLaysToggleButton.setChecked(SharedPreferencesUtil
				.getBooleanPreferences(activity, "all_plays_toggle_button"));
		topPLaysToggleButton.setChecked(SharedPreferencesUtil
				.getBooleanPreferences(activity, "top_plays_toggle_button"));
		scoringPLaysToggleButton
				.setChecked(SharedPreferencesUtil.getBooleanPreferences(
						activity, "scoring_plays_toggle_button"));
		savesToggleButton.setChecked(SharedPreferencesUtil
				.getBooleanPreferences(activity,
						"turnovers_plays_toggle_button"));
		redCardsToggleButton
				.setChecked(SharedPreferencesUtil.getBooleanPreferences(
						activity, "red_zone_plays_toggle_button"));
		playsOfTheGameToggleButton.setChecked(SharedPreferencesUtil
				.getBooleanPreferences(activity,
						"plays_of_the_game_toggle_button"));
		basicAlertLevelSeekbar.setProgress(SharedPreferencesUtil
				.getIntegerPreferences(activity,
						"basicAlertLevelSeekbarPosition"));	
	}

	public static void customizeAlertSetting(final Activity activity,
			final TextView alertButton) {
		allPLaysToggleButton = (ToggleButton) activity
				.findViewById(R.id.all_plays_toggle_button);
		allPLaysToggleButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				SharedPreferencesUtil.saveBooleanPreferences(activity,
						"all_plays_toggle_button",
						allPLaysToggleButton.isChecked());
			}
		});

		topPLaysToggleButton = (ToggleButton) activity
				.findViewById(R.id.top_plays_toggle_button);
		topPLaysToggleButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				SharedPreferencesUtil.saveBooleanPreferences(activity,
						"top_plays_toggle_button",
						topPLaysToggleButton.isChecked());
			}
		});

		scoringPLaysToggleButton = (ToggleButton) activity
				.findViewById(R.id.scoring_plays_toggle_button);
		scoringPLaysToggleButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				SharedPreferencesUtil.saveBooleanPreferences(activity,
						"scoring_plays_toggle_button",
						scoringPLaysToggleButton.isChecked());
			}
		});

		savesToggleButton = (ToggleButton) activity
				.findViewById(R.id.saves_toggle_button);
		savesToggleButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				SharedPreferencesUtil.saveBooleanPreferences(activity,
						"turnovers_plays_toggle_button",
						savesToggleButton.isChecked());
			}
		});
		redCardsToggleButton = (ToggleButton) activity
				.findViewById(R.id.red_cards_toggle_button);
		redCardsToggleButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				SharedPreferencesUtil.saveBooleanPreferences(activity,
						"red_zone_plays_toggle_button",
						redCardsToggleButton.isChecked());
			}
		});
		playsOfTheGameToggleButton = (ToggleButton) activity
				.findViewById(R.id.plays_of_the_game_toggle_button);
		playsOfTheGameToggleButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				SharedPreferencesUtil.saveBooleanPreferences(activity,
						"plays_of_the_game_toggle_button",
						playsOfTheGameToggleButton.isChecked());
			}
		});

		/**
		 * Basic alert seek bar handler
		 */

		basicAlertLevelSeekbar = (SeekBar) activity
				.findViewById(R.id.basic_alertlevel_seekBar);
		basicAlertLevelSeekbar
				.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
					int progressChanged = 0;
					int progressBarMaxRange = 0;

					public void onProgressChanged(SeekBar seekBar,
							int progress, boolean fromUser) {
						progressChanged = progress;
						progressBarMaxRange = seekBar.getMax();
					}

					public void onStopTrackingTouch(SeekBar seekBar) {
						if ((progressChanged >= 0)
								&& (progressChanged <= (progressBarMaxRange / 4))) {
							SharedPreferencesUtil.saveStringPreferences(
									activity,
									"alert_button_status",
									activity.getString(R.string.alert_level_first));
						} else if ((progressChanged > (progressBarMaxRange / 4))
								&& (progressChanged <= (progressBarMaxRange / 2))) {
							SharedPreferencesUtil.saveStringPreferences(
									activity,
									"alert_button_status",
									activity.getString(R.string.alert_level_second));
						} else if ((progressChanged > (progressBarMaxRange / 2))
								&& (progressChanged <= ((3 * progressBarMaxRange) / 4))) {
							SharedPreferencesUtil.saveStringPreferences(
									activity,
									"alert_button_status",
									activity.getString(R.string.alert_level_third));
						} else if (progressChanged > ((3 * progressBarMaxRange) / 4)) {
							SharedPreferencesUtil.saveStringPreferences(
									activity,
									"alert_button_status",
									activity.getString(R.string.alert_level_fourth));
						}
						SharedPreferencesUtil.saveIntegerPreferences(activity,
								"basicAlertLevelSeekbarPosition",
								progressChanged);
						alertButton.setText("Alerts: "
								+ SharedPreferencesUtil.getStringPreferences(
										activity, "alert_button_status"));
					}

					@Override
					public void onStartTrackingTouch(SeekBar seekBar) {
						// TODO Auto-generated method stub

					}
				});
	
	}

	public static void setAlertPopover(final Activity activity,
			final PopoverView popoverView, final Button alertButton) {

		alertButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				RelativeLayout rootView = (RelativeLayout) activity
						.findViewById(R.id.gameRootView);
				if (popoverView != null) {
					popoverView.dissmissPopover(false);
				}
				PopoverView popoverView = new PopoverView(activity,
						R.layout.common_popover_view_alerts);

				popoverView.setContentSizeForViewInPopover(new Point(370, 630));
				popoverView.setDelegate((PopoverViewDelegate) activity);
				View button = (View) activity.findViewById(R.id.alertButton);
				popoverView.showPopoverFromRectInViewGroup(rootView,
						PopoverView.getFrameForView(button),
						PopoverView.PopoverArrowDirectionUp, true);
				SoccerUtils.customizeAlertSetting(activity, alertButton);
				SoccerUtils
						.setUserPreferredAlertSettings(activity, alertButton);

			}
		});
	}
	
	public static void setTeamsContent(ListView listView, Activity activity, boolean showteamSelectedOption) {

		DatabaseHelper databaseHelper = new DatabaseHelper((Context)activity);
		LeagueTeamDto leagueTeamDto = databaseHelper.getLeagueWithTeams();

		List<String> favouriteTeamsList = SharedPreferencesUtil
				.getFavouriteInSharedPreferencesList((Context)activity, "team");
		SeparatedListAdapter adapter = new SeparatedListAdapter((Context)activity);
		for (League league : leagueTeamDto.getLeagueList()) {
			List<TeamMenuItems> rowItemsForQ = new ArrayList<TeamMenuItems>();
			for (TeamItem teamItem : league.getTeamList()) {
				if (favouriteTeamsList.contains(teamItem.getTeamId())) {
					teamItem.setUserFavourite(true);
				}
				int teamLogo = ImageProcessingUtil
						.getTeamLogoImageResourceByTeamId(activity,
								teamItem.getTeamId());
				TeamMenuItems teamMenuItems = new TeamMenuItems(teamLogo,
						teamItem.getTeamName(), teamItem.isUserFavourite(),
						teamItem.getTeamId());
				rowItemsForQ.add(teamMenuItems);
			}
			adapter.addSection(league.getLeagueName(),
					new TeamMenuListViewAdapter((Context)activity,
							R.layout.team_menu_list_item, rowItemsForQ, showteamSelectedOption));
		}
		listView.setAdapter(adapter);
	}
}
