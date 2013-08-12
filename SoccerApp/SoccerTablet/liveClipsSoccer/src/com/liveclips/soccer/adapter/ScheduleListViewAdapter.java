package com.liveclips.soccer.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.liveclips.soccer.R;
import com.liveclips.soccer.model.ScheduleItem;



public class ScheduleListViewAdapter extends ArrayAdapter<ScheduleItem> {

	Context context;

	public ScheduleListViewAdapter(Context context, int resourceId,
			List<ScheduleItem> items) {
		super(context, resourceId, items);
		this.context = context;

	}

	/* private view holder class */
	private class ViewHolder {
		TextView textviewCousreTitle;
		ProgressBar progressBarModuleLevel;
		TextView textviewModuleStatus;
		TextView textviewModuleAverage;
		TextView textviewStartDate;
		TextView textviewEndDate;
		// ImageView imageView;
	}

	private class ScheduleViewHolder {
		TextView weekText;
		/*ImageView teamLogoImage;*/
		TextView versusText;
		TextView teamNameText;
		TextView teamStatusText;
		TextView textviewEndDate;
		TextView teamIdTextView;
		// ImageView imageView;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ScheduleViewHolder holder = null;
		ScheduleItem rowItem = getItem(position);

		LayoutInflater mInflater = (LayoutInflater) context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		if (convertView == null) {
			convertView = mInflater.inflate(
					R.layout.game_popover_list_row_item_schedule, null);
			holder = new ScheduleViewHolder();
			holder.weekText = (TextView) convertView
					.findViewById(R.id.match_date);
			holder.versusText = (TextView) convertView
					.findViewById(R.id.versus_text);
			holder.teamNameText = (TextView) convertView
					.findViewById(R.id.team_name);
			holder.teamStatusText = (TextView) convertView
					.findViewById(R.id.team_status);
			/*holder.teamLogoImage = (ImageView) convertView
					.findViewById(R.id.team_logo);*/
			holder.teamIdTextView = (TextView) convertView
					.findViewById(R.id.team_id);
			convertView.setTag(holder);
		} else
			holder = (ScheduleViewHolder) convertView.getTag();

		holder.teamNameText.setText(rowItem.getTeamName());
		// holder.teamLogoImage.getResources().getDrawable(rowItem.getTeamLogo());
		/*holder.teamLogoImage.setImageResource(rowItem.getTeamLogo())*/;
		holder.teamStatusText.setText(rowItem.getTeamStatus());
		holder.weekText.setText(rowItem.getWeekText());
		holder.versusText.setText(rowItem.getVersusText());
		holder.teamIdTextView.setText(rowItem.getTeamId());

		return convertView;
	}

}
