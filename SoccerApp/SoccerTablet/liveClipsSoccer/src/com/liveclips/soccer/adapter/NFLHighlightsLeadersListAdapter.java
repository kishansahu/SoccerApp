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
import com.liveclips.nfl.model.LeadersTypeItem;

/**
 * @author mohitkumar
 *
 */
public class NFLHighlightsLeadersListAdapter extends ArrayAdapter<LeadersTypeItem>{
	
private Context context;
	
	private List<LeadersTypeItem> leadersTypeItems;
	
		
	public NFLHighlightsLeadersListAdapter(Context context, int textViewResourceId, List<LeadersTypeItem> leadersTypeItems) {
		super(context, textViewResourceId, leadersTypeItems);
		this.context = context;
		this.leadersTypeItems = leadersTypeItems;
		
	}
	
	
	@Override
	public int getCount() {
		return leadersTypeItems.size();
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		LeadersTypeViewHolder holder = null;
			
		LeadersTypeItem leadersTypeItem = getItem(position);
		
		LayoutInflater mInflater = (LayoutInflater) context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			
			if (convertView == null) {
				convertView = mInflater.inflate(
						R.layout.nfl_highlights_popover_list_row_item_leader, null);
				holder = new LeadersTypeViewHolder();
				
				holder.leadersText = (TextView) convertView
						.findViewById(R.id.leaderTypeName);
				holder.disclosureImage = (ImageView) convertView.findViewById(R.id.disclosure_button);
				
				convertView.setTag( holder);
				
				
			} else{
				
				holder = (LeadersTypeViewHolder) convertView.getTag();
			}
				
				holder.leadersText.setText(leadersTypeItem.getLeadersTypeName());
					
				holder.disclosureImage.setImageResource(R.drawable.disclosure);
	
		return convertView;
	}
	
	
	
	
	public static class LeadersTypeViewHolder {
		
		TextView leadersText;
		
		ImageView disclosureImage;
		
	}
	

}
