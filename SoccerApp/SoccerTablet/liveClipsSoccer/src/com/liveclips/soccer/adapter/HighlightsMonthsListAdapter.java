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
import com.liveclips.soccer.model.MonthItem;


/**
 * @author mohitkumar
 * 
 */
public class HighlightsMonthsListAdapter extends ArrayAdapter<MonthItem> {

	Context context;

	List<MonthItem> monthItems;

	public HighlightsMonthsListAdapter(Context context, int resource,
			List<MonthItem> objects) {
		super(context, resource, objects);
		this.context = context;
		this.monthItems = objects;
	}

	@Override
	public int getCount() {
		return monthItems.size();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		MonthHolder holder = new MonthHolder();
		MonthItem weekItem = getItem(position);

		if (convertView == null) {

			LayoutInflater mInflater = (LayoutInflater) context
					.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

			convertView = mInflater.inflate(
					R.layout.highlights_popover_list_row_item_month, null);

			// convertView.setTag(holder);

		}
		holder.monthTextView = (TextView) convertView
				.findViewById(R.id.nfl_highlights_month_text);
	//	holder = (WeekHolder) convertView.getTag();
		holder.monthTextView.setText(weekItem.getWeekName());

		return convertView;
	}

	public static class MonthHolder {

		TextView monthTextView;

	}

}
