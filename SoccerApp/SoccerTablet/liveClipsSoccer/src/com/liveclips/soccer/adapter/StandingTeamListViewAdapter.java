/**
 * 
 */
package com.liveclips.soccer.adapter;

import java.util.List;

import com.liveclips.nfl.R;
import com.liveclips.nfl.model.DivisionsItem;
import com.liveclips.nfl.model.TeamItem;
import com.liveclips.soccer.adapter.DivisionsListViewAdapter.DivisionViewHolder;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author mohitkumar
 *
 */
public class StandingTeamListViewAdapter extends ArrayAdapter<TeamItem>{

private Context context;
	
	private List<TeamItem> teamItems;
	
		
	public StandingTeamListViewAdapter(Context context, int textViewResourceId, List<TeamItem> teamItems) {
		super(context, textViewResourceId, teamItems);
		this.context = context;
		this.teamItems = teamItems;
		
	}
	
	
	@Override
	public int getCount() {
		return teamItems.size();
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		TeamItemViewHolder holder = null;
			
		TeamItem teamItem = getItem(position);
		
		LayoutInflater mInflater = (LayoutInflater) context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			
			if (convertView == null) {
				convertView = mInflater.inflate(
						R.layout.common_division_nfl_highlight_list_row_item_standing, null);
				holder = new TeamItemViewHolder();
				
				holder.teamName = (TextView) convertView
						.findViewById(R.id.team_name);
				
				holder.matchWins = (TextView) convertView
						.findViewById(R.id.match_wins);
				
				holder.matchLosses = (TextView) convertView
						.findViewById(R.id.match_losses);
				
				holder.team_logo = (ImageView) convertView.findViewById(R.id.team_logo);
				
				holder.disclouser = (ImageView) convertView.findViewById(R.id.disclouser);
				
				convertView.setTag( holder);
				
				
			} else{
				
				holder = (TeamItemViewHolder) convertView.getTag();
			}
				
				holder.teamName.setText(teamItem.getTeamName());
				
				holder.matchWins.setText("" + teamItem.getWins());
				
				holder.matchLosses.setText("" + teamItem.getLosses());
					
				holder.team_logo.setImageResource(teamItem.getTeamLogo());
				
				holder.disclouser.setImageResource(R.drawable.disclosure);
					
				
			
			
		
		return convertView;
	}
	
	
	
	
	public static class TeamItemViewHolder {
		
		TextView teamName;
		
		TextView matchWins;
		
		TextView matchLosses;
		
		ImageView team_logo;
		
		ImageView disclouser;
	}
	
	
	
	
}
