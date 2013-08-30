package com.liveclips.soccer.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.liveclips.soccer.R;
import com.liveclips.soccer.model.PlayerItem;


public class TeamsPlayerListViewAdapter extends ArrayAdapter<PlayerItem> {

	Context context;
	List<PlayerItem> items;

	public TeamsPlayerListViewAdapter(Context context, int resourceId,
			List<PlayerItem> items) {
		super(context, resourceId, items);
		this.context = context;
		this.items = items;

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return items.size();
	}

	private class PlayerViewHolder {

		TextView playerName;
		ImageView teamImage;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		PlayerViewHolder holder = new PlayerViewHolder();

		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(
					R.layout.players_fragment_list_row_item_addplayer, parent,
					false);

		}
		holder.playerName = (TextView) convertView
				.findViewById(R.id.team_category_team_player_name);
		holder.teamImage = (ImageView) convertView
				.findViewById(R.id.team_category_team_icon);
		PlayerItem rowItem = getItem(position);

		holder.teamImage.setBackgroundResource(rowItem.getTeamLogo());
		holder.playerName.setText(rowItem.getPlayerName());

		return convertView;
	}

}
