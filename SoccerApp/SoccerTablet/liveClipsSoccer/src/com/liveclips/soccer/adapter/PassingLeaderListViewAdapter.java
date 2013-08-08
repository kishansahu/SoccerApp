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

import com.liveclips.soccer.R;
import com.liveclips.soccer.model.PassingLeaderItem;


/**
 * @author mohitkumar
 *
 */
public class PassingLeaderListViewAdapter extends ArrayAdapter<PassingLeaderItem>{
	
private Context context;
	
	private List<PassingLeaderItem> passingLeaderItems;
	
		
	public PassingLeaderListViewAdapter(Context context, int textViewResourceId, List<PassingLeaderItem> passingLeaderItems) {
		super(context, textViewResourceId, passingLeaderItems);
		this.context = context;
		this.passingLeaderItems = passingLeaderItems;
		
	}
	
	
	@Override
	public int getCount() {
		return passingLeaderItems.size();
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		PassingLeaderHolder holder = null;
			
		PassingLeaderItem passingLeaderItem = getItem(position);
		
		LayoutInflater mInflater = (LayoutInflater) context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			
			if (convertView == null) {
				convertView = mInflater.inflate(
						R.layout.highlights_popover_list_row_item_passing_leader, null);
				holder = new PassingLeaderHolder();
				
				holder.passingPlayeText = (TextView) convertView
						.findViewById(R.id.passing_player_name);
				
				holder.passingTeamText = (TextView) convertView
						.findViewById(R.id.passing_team_name);
				
				holder.passingYardsText = (TextView) convertView
						.findViewById(R.id.passing_yards);
				
				holder.disclosureImage = (ImageView) convertView.findViewById(R.id.disclosure_button);
				
				convertView.setTag( holder);
				
				
			} else{
				
				holder = (PassingLeaderHolder) convertView.getTag();
			}
			
			holder.passingPlayeText.setText(passingLeaderItem.getLeaderName());
			holder.passingTeamText.setText(passingLeaderItem.getLeaderTeam().getTeamName());
			holder.passingYardsText.setText("" + passingLeaderItem.getYards());
			holder.disclosureImage.setImageResource(R.drawable.disclosure);
					
				
			
			
		
		return convertView;
	}
	
	
	
	
	public static class PassingLeaderHolder {
		
		TextView passingPlayeText;
		
		TextView passingTeamText;
		
		TextView passingYardsText;
		
		ImageView disclosureImage;
	}
	
	
	
	
	
	

}
