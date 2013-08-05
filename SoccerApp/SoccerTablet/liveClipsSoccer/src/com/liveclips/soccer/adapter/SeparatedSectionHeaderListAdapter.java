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
import com.liveclips.soccer.model.SectionHeaderItem;

/**
 * @author mohitkumar
 *
 */
public class SeparatedSectionHeaderListAdapter extends  ArrayAdapter<SectionHeaderItem>{

	private Context context;
	
	private List<SectionHeaderItem> sectionHeaderItem;
	
		
	public SeparatedSectionHeaderListAdapter(Context context, int textViewResourceId, List<SectionHeaderItem> sectionHeaderItem) {
		super(context, textViewResourceId, sectionHeaderItem);
		//super(headerName, context, textViewResourceId, sectionHeaderItem);
		this.context = context;
		this.sectionHeaderItem = sectionHeaderItem;
		
	}
	
	
	@Override
	public int getCount() {
		return sectionHeaderItem.size();
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		HeaderViewHolder holder = null;
			
		SectionHeaderItem currentItem = (SectionHeaderItem) getItem(position);
		
		LayoutInflater mInflater = (LayoutInflater) context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			
			if (convertView == null) {
				convertView = mInflater.inflate(
						R.layout.nfl_highlights_popover_header_standing, null);
				holder = new HeaderViewHolder();
				
				holder.textView1 = (TextView) convertView
						.findViewById(R.id.list_header_title);
				
				holder.textView2 = (TextView) convertView
						.findViewById(R.id.match_wins_text);
				
				holder.textView3 = (TextView) convertView
						.findViewById(R.id.match_losses_text);
				
				
				convertView.setTag( holder);
				
				
			} else{
				
				holder = (HeaderViewHolder) convertView.getTag();
			}
				
				holder.textView1.setText(currentItem.getHeadingText1());
					
				holder.textView2.setText(currentItem.getHeadingText2());
				
				holder.textView3.setText(currentItem.getHeadingText3());
					
				
			
			
		
		return convertView;
	}
	
	
	
	
	public static class HeaderViewHolder {
		
		TextView textView1;
		
		TextView textView2;
		
		TextView textView3;
	}
	
	
	
	
	


}
