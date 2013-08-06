package com.liveclips.soccer.adapter;

import java.util.List;
import java.util.Properties;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.liveclips.soccer.R;
import com.liveclips.soccer.model.TeamMenuItems;
import com.liveclips.soccer.utils.SharedPreferencesUtil;

public class TeamMenuListViewAdapter extends ArrayAdapter<TeamMenuItems> {

	Context context;
	List<TeamMenuItems> items;
	private Properties appCommonProperties;
	
	public TeamMenuListViewAdapter(Context context, int resourceId,
			List<TeamMenuItems> items) {
		super(context, resourceId, items);
		this.context = context;
		this.items = items;

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return items.size();
	}

	private class MenuViewHolder {
		
		ImageView teamLogo;
		TextView teamShortName;
		ImageView teamSelectedOption;
		
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		//appCommonProperties = PropertyReader.getPropertiesFormAssetDirectory("appcommonproperties.properties", (Activity) context);
		final MenuViewHolder holder;

		final TeamMenuItems rowItem = getItem(position);

		LayoutInflater mInflater = (LayoutInflater) context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.team_menu_list_item, null);
			holder = new MenuViewHolder();
			holder.teamSelectedOption = (ImageView) convertView
					.findViewById(R.id.team_selected_option);
			
			holder.teamLogo = (ImageView) convertView
					.findViewById(R.id.team_logo);
			holder.teamShortName = (TextView) convertView
					.findViewById(R.id.team_name);
			
			
			convertView.setTag(holder);
		} else
			holder = (MenuViewHolder) convertView.getTag();
		if(rowItem.isUsersFavourite == false){
		holder.teamSelectedOption.setImageResource(R.drawable.star_low);
		}else{
			holder.teamSelectedOption.setImageResource(R.drawable.star_high);
		}
		
		holder.teamLogo.setImageResource(rowItem.teamLogo);
		holder.teamShortName.setText(rowItem.teamShortName);
		
		holder.teamSelectedOption.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) { 
				List<String> favTeams = SharedPreferencesUtil
						.getFavouriteInSharedPreferencesList(context, "team");
				if(favTeams.contains(rowItem.teamId)){
					SharedPreferencesUtil.removeFavouriteFromSharedPreferencesList(context, rowItem.teamId, "team");
					holder.teamSelectedOption.setImageResource(R.drawable.star_low);
				}else{
					SharedPreferencesUtil.saveFavouriteInSharedPreferencesList(context, rowItem.teamId, "team");
					holder.teamSelectedOption.setImageResource(R.drawable.star_high);
				}
			}
		});
		return convertView;
	}

}
