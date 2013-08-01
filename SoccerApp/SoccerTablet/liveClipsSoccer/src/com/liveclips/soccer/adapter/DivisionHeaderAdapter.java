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
import android.widget.ImageView;
import android.widget.TextView;

import com.liveclips.nfl.R;
import com.liveclips.nfl.model.DivisionHeader;

/**
 * @author mohitkumar
 *
 */
public class DivisionHeaderAdapter extends ArrayAdapter<DivisionHeader> {

	Context context;
	
	List<DivisionHeader> divisionHeaders;
	
	
	public DivisionHeaderAdapter(Context context, int resource,
			 List<DivisionHeader> objects) {
		super(context, resource, objects);
		this.context = context;
		this.divisionHeaders = objects;
	}

	
	@Override
	public int getCount() {
		return divisionHeaders.size();
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		DivisionHeaderHolder holder = null;
		
		DivisionHeader divisionHeader = getItem(position);
		
		LayoutInflater mInflater = (LayoutInflater) context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			
			if (convertView == null) {
				convertView = mInflater.inflate(
						R.layout.division_fragment_header, null);
				holder = new DivisionHeaderHolder();
				
				holder.divisionHeaderTextView = (TextView) convertView
						.findViewById(R.id.division_header_text);
				holder.divisionHeaderImageView = (ImageView) convertView.findViewById(R.id.division_header_backButton);
				
				convertView.setTag(holder);
				
				
			} else{
				
					holder = (DivisionHeaderHolder) convertView.getTag();
					
					DivisionHeader currentItem = (DivisionHeader) divisionHeader;
					
					holder.divisionHeaderTextView.setText(currentItem.getDivisionHeader());
					
					holder.divisionHeaderImageView.setImageResource(R.drawable.packers_helmet);
		
			}
	
		return convertView;
	}
	

	public static class DivisionHeaderHolder{
		
		TextView divisionHeaderTextView;
		
		ImageView divisionHeaderImageView;
	}
	

}
