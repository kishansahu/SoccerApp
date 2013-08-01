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
import com.liveclips.nfl.model.ConferenceItem;

/**
 * @author mohitkumar
 *
 */
public class ConferenceListViewAdapter extends ArrayAdapter<ConferenceItem>{

	private Context context;
	
	private  List<ConferenceItem> conferenceItems;
	
	
	public ConferenceListViewAdapter(Context context, int resource,
			 List<ConferenceItem> objects) {
		super(context, resource, objects);
		this.context = context;
		
		this.conferenceItems = objects;
	}
	
	@Override
	public int getCount() {
		return conferenceItems.size();
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ConferenceHolder holder = null;
		
		ConferenceItem conferenceItem = getItem(position);
		
		LayoutInflater mInflater = (LayoutInflater) context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			
			if (convertView == null) {
				convertView = mInflater.inflate(
						R.layout.conference_menu_row_layout, null);
				holder = new ConferenceHolder();
				holder.conferenceName = (TextView)convertView
						.findViewById(R.id.conferenceName);
				
				holder.team_1_name = (TextView)convertView
						.findViewById(R.id.team_1_name);
				
				holder.team_2_name = (TextView)convertView
						.findViewById(R.id.team_2_name);
				
				holder.team_3_name = (TextView)convertView
						.findViewById(R.id.team_3_name);
				
				holder.team_4_name = (TextView)convertView
						.findViewById(R.id.team_4_name);
				
				holder.team_1_logo = (ImageView) convertView
						.findViewById(R.id.team_1_logo);
								
				holder.team_2_logo = (ImageView) convertView
						.findViewById(R.id.team_2_logo);
				
				holder.team_3_logo = (ImageView) convertView
						.findViewById(R.id.team_3_logo);
				holder.team_4_logo = (ImageView) convertView
						.findViewById(R.id.team_4_logo);
				convertView.setTag( holder);
				
				
			} else{
				
				holder = (ConferenceHolder) convertView.getTag();
			}
					
					holder.conferenceName.setText(conferenceItem.getConferenceName());
					
					holder.team_1_name.setText(conferenceItem.getTeam_1().getTeamName());
					
					holder.team_2_name.setText(conferenceItem.getTeam_2().getTeamName());
					
					holder.team_3_name.setText(conferenceItem.getTeam_3().getTeamName());
					
					holder.team_4_name.setText(conferenceItem.getTeam_4().getTeamName());
					
					holder.team_1_logo.setImageResource(conferenceItem.getTeam_1().getTeamLogo());
					
					holder.team_2_logo.setImageResource(conferenceItem.getTeam_2().getTeamLogo());
					
					holder.team_3_logo.setImageResource(conferenceItem.getTeam_3().getTeamLogo());
					
					holder.team_4_logo.setImageResource(conferenceItem.getTeam_4().getTeamLogo());
	
		return convertView;
	}
	
	public static class ConferenceHolder {
		
		TextView conferenceName;
		
		ImageView team_1_logo;
		
		ImageView team_2_logo;
		
		ImageView team_3_logo;
		
		ImageView team_4_logo;
		
		TextView team_1_name;
		
		TextView team_2_name;
		
		TextView team_3_name;
		
		TextView team_4_name;
		
	}
	

}
