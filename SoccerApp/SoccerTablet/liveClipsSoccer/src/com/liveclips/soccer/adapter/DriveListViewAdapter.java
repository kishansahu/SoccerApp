package com.liveclips.soccer.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.liveclips.nfl.R;
import com.liveclips.nfl.model.DriveItem;

public class DriveListViewAdapter extends ArrayAdapter<DriveItem> {

	Context context;
	List<DriveItem> items;

	public DriveListViewAdapter(Context context, int resourceId,
			List<DriveItem> items) {
		super(context, resourceId, items);
		this.context = context;
		this.items = items;

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return items.size();
	}

	private class DriveViewHolder {
		TextView teamShortName;
		TextView driveEvent;
		TextView driveTime;
		TextView driveYardsCovered;
		ImageView teamLogo;
		// ImageView imageView;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		DriveViewHolder holder = null;
		DriveItem rowItem = getItem(position);

		LayoutInflater mInflater = (LayoutInflater) context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		if (convertView == null) {
			convertView = mInflater.inflate(
					R.layout.game_popover_list_row_item_drives, null);
			holder = new DriveViewHolder();
			holder.teamLogo = (ImageView) convertView
					.findViewById(R.id.team_logo);
			holder.teamShortName = (TextView) convertView
					.findViewById(R.id.team_shortname);
			holder.driveTime = (TextView) convertView
					.findViewById(R.id.drive_time);
			holder.driveYardsCovered = (TextView) convertView
					.findViewById(R.id.drive_yard_covered);
			holder.driveEvent = (TextView) convertView
					.findViewById(R.id.drive_event);

			convertView.setTag(holder);
		} else
			holder = (DriveViewHolder) convertView.getTag();
		holder.teamLogo.setImageDrawable(rowItem.teamLogoDrawable);
		holder.teamShortName.setText(rowItem.teamShortName);
		holder.driveYardsCovered.setText(rowItem.driveYardsCovered);
		holder.driveTime.setText(rowItem.driveTime);
		holder.driveEvent.setText(rowItem.driveEvent);

		return convertView;
	}

}