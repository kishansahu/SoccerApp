package com.liveclips.soccer.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.liveclips.soccer.R;
import com.liveclips.soccer.model.GameSchedule;

public class GameScheduleListViewAdaptor extends ArrayAdapter<GameSchedule> {

	Context context;
	List<GameSchedule> gameScheduleList;

	public GameScheduleListViewAdaptor(Context context, int textViewResourceId,
			List<GameSchedule> gameScheduleList) {
		super(context, textViewResourceId, gameScheduleList);
		this.context = context;
		this.gameScheduleList = gameScheduleList;

	}

	class GameScheduleViewHolder {
		TextView firstTeamName;
		TextView secondTeamName;
		TextView timeLeft;
		ImageView firstTeamLogo;
		ImageView secondTeamLogo;
		TextView firstTeamScore;
		TextView secondTeamScore;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		Log.d("adaptor", "gameschedulelistadaptor");
		GameScheduleViewHolder holder = null;
		GameSchedule rowItem = getItem(position);

		LayoutInflater mInflater = (LayoutInflater) context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.game_schedule_fragment_list_row_item,
					null);
			holder = new GameScheduleViewHolder();
			holder.firstTeamLogo = (ImageView) convertView
					.findViewById(R.id.first_team_logo);
			holder.secondTeamLogo = (ImageView) convertView
					.findViewById(R.id.second_team_logo);
			holder.firstTeamName = (TextView) convertView
					.findViewById(R.id.first_team_shortname);
			holder.secondTeamName = (TextView) convertView
					.findViewById(R.id.second_team_shortname);
			holder.firstTeamScore = (TextView) convertView
					.findViewById(R.id.first_team_score);
			holder.secondTeamScore = (TextView) convertView
					.findViewById(R.id.second_team_score);
			holder.timeLeft = (TextView) convertView
					.findViewById(R.id.timeLeft);

			convertView.setTag(holder);
		} else
			holder = (GameScheduleViewHolder) convertView.getTag();

		holder.firstTeamLogo.setImageResource(rowItem.getFirstTeamImageId());
		holder.secondTeamLogo.setImageResource(rowItem.getSecondTeamImageId());
		holder.firstTeamName.setText(rowItem.getFirstTeamName());
		holder.secondTeamName.setText(rowItem.getSecondTeamName());
		holder.firstTeamScore.setText(String.valueOf(rowItem.getFirstTeamScore()));
		holder.secondTeamScore.setText(String.valueOf(rowItem.getSecondTeamScore()));
		holder.timeLeft.setText(rowItem.getTimeLeft());

		return convertView;
	}

}
