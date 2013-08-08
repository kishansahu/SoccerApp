/**
 * 
 */
package com.liveclips.soccer.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.liveclips.soccer.R;
import com.liveclips.soccer.model.WeekItem;


/**
 * @author mohitkumar
 * 
 */
public class HighlightsWeekListAdapter extends ArrayAdapter<WeekItem> {

	Context context;

	List<WeekItem> weekItems;

	public HighlightsWeekListAdapter(Context context, int resource,
			List<WeekItem> objects) {
		super(context, resource, objects);
		this.context = context;
		this.weekItems = objects;
	}

	@Override
	public int getCount() {
		return weekItems.size();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		WeekHolder holder = new WeekHolder();
		WeekItem weekItem = getItem(position);

		if (convertView == null) {

			LayoutInflater mInflater = (LayoutInflater) context
					.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

			convertView = mInflater.inflate(
					R.layout.highlights_popover_list_row_item_week, null);

			// convertView.setTag(holder);

		}
		holder.weekTextView = (TextView) convertView
				.findViewById(R.id.nfl_highlights_week_text);
	//	holder = (WeekHolder) convertView.getTag();
		holder.weekTextView.setText(weekItem.getWeekName());

		return convertView;
	}

	public static class WeekHolder {

		TextView weekTextView;

	}

}
