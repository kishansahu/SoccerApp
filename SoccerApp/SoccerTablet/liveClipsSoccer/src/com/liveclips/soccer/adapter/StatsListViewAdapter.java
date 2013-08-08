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

import com.liveclips.soccer.R;
import com.liveclips.soccer.model.StatsItem;



public class StatsListViewAdapter  extends ArrayAdapter<StatsItem> {

	Context context;
	List<StatsItem> items;
	
	public StatsListViewAdapter(Context context, int resourceId,
			List<StatsItem> items) {
		super(context, resourceId, items);
		this.context = context;
		this.items = items;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return items.size();
	}
	
	private class StatsViewHolder {
		TextView statType;
		TextView score1;
		TextView score2;
		ImageView disclosureButton;
		// ImageView imageView;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		
		StatsViewHolder holder = null;
		StatsItem rowItem = getItem(position);

		LayoutInflater mInflater = (LayoutInflater) context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		if (convertView == null) {
			convertView = mInflater
					.inflate(R.layout.game_popover_list_row_item_stats, null);
			holder = new StatsViewHolder();
			holder.statType = (TextView) convertView
					.findViewById(R.id.stat_type);
			holder.score1 = (TextView) convertView
					.findViewById(R.id.statScore1);
			holder.score2 = (TextView) convertView
					.findViewById(R.id.statScore2);
			holder.disclosureButton = (ImageView) convertView
					.findViewById(R.id.disclosure_button);

			convertView.setTag(holder);
		} else
			holder = (StatsViewHolder) convertView.getTag();

		holder.statType.setText(rowItem.getStatType());
		holder.score1.setText(rowItem.getStatScore1());
		holder.score2.setText(rowItem.getStatScore2());
		holder.disclosureButton.setImageResource(rowItem.getDisclosureImage());
		return convertView;
	}

}

