package com.liveclips.soccer.adapter;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.liveclips.soccer.R;
import com.liveclips.soccer.activity.PlayersActivity;
import com.liveclips.soccer.model.PlayerItem;
import com.liveclips.soccer.utils.SoccerUtils;


public class AddPlayerListAdapter extends BaseAdapter {
	boolean isplayerFavouriteActive;
	Context context;
	View favPlayerDetailHolderSeperator;
	RelativeLayout favPlayerDetailHolder, allMyPlayersIcon;
	LinearLayout emptyMyPlayersContainer, wrapper;
	String message = "";
	ImageView playerFavImage;
	PlayersActivity playersActivity;
	List<PlayerItem> playerDetailsListForFavourite;
	boolean isPlayerActivity;
	public AddPlayerListAdapter(Context context,
			List<PlayerItem> playerDetailsListForFavourite) {
		this.context = context;
		this.playerDetailsListForFavourite = playerDetailsListForFavourite;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return playerDetailsListForFavourite.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		
		try{
		playersActivity = (PlayersActivity) context;
		wrapper = (LinearLayout) playersActivity
				.findViewById(R.id.myPlayersContainer);
		isPlayerActivity = true;
		}catch(Exception e){
			System.out.println("Not a Player Activity");
		}
		View view = null;

		// TODO Auto-generated method stub
		if (view == null) {

			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			view = inflater.inflate(
					R.layout.players_fragment_list_row_item_details, arg2,
					false);
			PlayerItem PI = playerDetailsListForFavourite.get(arg0);
			int playerFav = PI.getPlayerFavourite();
			final String playerName = PI.getPlayerName();
			final String playerNum = PI.getPlayerNumber();
			final String playerPos = PI.getPlayerPosition();
			final String TeamCode = PI.getTeamName();
			isplayerFavouriteActive = PI.isPLayerFavouriteActive();
			TextView playerNameView = (TextView) view
					.findViewById(R.id.playerNameInFragmentList);
			playerNameView.setText(playerName);

			TextView playerNumView = (TextView) view
					.findViewById(R.id.addplayerNumInFragmentList);
			playerNumView.setText(playerNum);

			TextView playerPosView = (TextView) view
					.findViewById(R.id.addplayerposInFragmentList);
			playerPosView.setText(playerPos);

			if (isplayerFavouriteActive) {
				message = "Do you really want to remove this player from My Players ?";
			} else {
				message = "Do you really want to add this player to My Players ?";
			}

			playerFavImage = (ImageView) view
					.findViewById(R.id.addplayerFavInFragmentList);
			if (isplayerFavouriteActive) {
				playerFavImage.setImageResource(R.drawable.star_high);
			} else {
				playerFavImage.setImageResource(R.drawable.star_low);
			}

			playerFavImage.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {

					DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {

							switch (which) {
							case DialogInterface.BUTTON_POSITIVE:
								// Yes button clicked
								if (isplayerFavouriteActive) {
									// remove player
									playerFavImage
											.setImageResource(R.drawable.star_low);
									if(isPlayerActivity == true){
										playersActivity.favPlayersCount = playersActivity.favPlayersCount - 1;
										if (playersActivity.favPlayersCount == 0) {

											playersActivity
													.showEmptyMyPlayerBanner(wrapper);
										}
									}
									
								} else {
									playerFavImage.setImageResource(R.drawable.star_high);

									if(isPlayerActivity == true){
									// Add player
									if (playersActivity.favPlayersCount == 0) {

										allMyPlayersIcon = (RelativeLayout) playersActivity
												.findViewById(R.id.myPlayersBannerContainer);
										allMyPlayersIcon
												.setVisibility(View.VISIBLE);

										wrapper.removeAllViews();
									}
									playersActivity.favPlayersCount = playersActivity.favPlayersCount + 1;
									/*playerFavImage
											.setImageResource(R.drawable.star_high);
*/
									final LinearLayout inflatedView;
									inflatedView = (LinearLayout) View.inflate(
											context,
											R.layout.common_players_detail,
											null);

									((TextView) inflatedView
											.findViewById(R.id.myindividualPlayerName))
											.setText(playerName);
									int colors[] = SoccerUtils.getDefaultColorTheme();
									GradientDrawable gradientDrawable = new GradientDrawable(
											GradientDrawable.Orientation.TOP_BOTTOM, colors);

									((RelativeLayout) inflatedView
											.findViewById(R.id.favPlayerDetailHolder))
											.setBackgroundDrawable(gradientDrawable);
									
									((TextView) inflatedView
											.findViewById(R.id.myindividualPlayerDetails))
											.setText("#" + playerNum + " | "
													+ playerPos);
									((TextView) inflatedView
											.findViewById(R.id.myindividualPlayerGameDetails))
											.setText("20 / 29, 268 YDS,2 TD, 1 INT");
									/*((ImageView) inflatedView
											.findViewById(R.id.myindividualPlayerPic))
											.setImageResource(R.drawable.brad_jones);*/
									ImageView myindividualPlayerFavouritePic = (ImageView) inflatedView
											.findViewById(R.id.myindividualPlayerFavouritePic);
									myindividualPlayerFavouritePic
											.setOnClickListener(new View.OnClickListener() {
												PlayersActivity playersActivity = (PlayersActivity) context;

												@Override
												public void onClick(View v) {

													DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
														@Override
														public void onClick(
																DialogInterface dialog,
																int which) {

															switch (which) {
															case DialogInterface.BUTTON_POSITIVE:
																// remove player
																favPlayerDetailHolder = (RelativeLayout) inflatedView
																		.findViewById(R.id.favPlayerDetailHolder);
																favPlayerDetailHolder
																		.setVisibility(View.GONE);
																favPlayerDetailHolderSeperator = (View) inflatedView
																		.findViewById(R.id.favPlayerDetailHolderSeperator);
																favPlayerDetailHolderSeperator
																		.setVisibility(View.GONE);
																playersActivity.favPlayersCount = playersActivity.favPlayersCount - 1;
																if (playersActivity.favPlayersCount == 0) {
																	playersActivity
																			.showEmptyMyPlayerBanner(wrapper);
																}
																break;

															case DialogInterface.BUTTON_NEGATIVE:
																// No button
																// clicked
																break;
															}
														}

													};
													AlertDialog.Builder builder = new AlertDialog.Builder(
															context);
													String message = "Do you really want to remove this player from My Players ?";
													builder.setMessage(message)
															.setPositiveButton(
																	"Yes",
																	dialogClickListener)
															.setNegativeButton(
																	"No",
																	dialogClickListener)
															.show();
												}
											});
									wrapper.addView(inflatedView);
								}
							}
								break;

							case DialogInterface.BUTTON_NEGATIVE:
								// No button clicked
								break;
							}
						}
					};

					AlertDialog.Builder builder = new AlertDialog.Builder(
							context);
					builder.setMessage(message)
							.setPositiveButton("Yes", dialogClickListener)
							.setNegativeButton("No", dialogClickListener)
							.show();

				}

			});
		}
		return view;
	}

}
