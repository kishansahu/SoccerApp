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
import com.liveclips.nfl.model.PlayerItem;

public class AddPlayersFavouritList extends ArrayAdapter<PlayerItem> {

	Context context;
	List<PlayerItem> items;

	public AddPlayersFavouritList(Context context, int resourceId,
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
		TextView playerNumber;
		TextView playerPosition;
		TextView playerName;
		ImageView playerFavourite;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		PlayerViewHolder holder = null;
		PlayerItem rowItem = getItem(position);

		LayoutInflater mInflater = (LayoutInflater) context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		if (convertView == null) {
			convertView = mInflater.inflate(
					R.layout.players_fragment_list_row_item_details, null);
			holder = new PlayerViewHolder();
			holder.playerFavourite = (ImageView) convertView
					.findViewById(R.id.addplayerFavInFragmentList);
			holder.playerName = (TextView) convertView
					.findViewById(R.id.playerNameInFragmentList);
			holder.playerNumber = (TextView) convertView
					.findViewById(R.id.addplayerNumInFragmentList);
			holder.playerPosition = (TextView) convertView
					.findViewById(R.id.addplayerposInFragmentList);
			convertView.setTag(holder);
		} else
			holder = (PlayerViewHolder) convertView.getTag();

		holder.playerFavourite.setImageResource(rowItem.playerFavourite);
		holder.playerName.setText(rowItem.playerName);
		holder.playerNumber.setText(rowItem.playerNumber);
		holder.playerPosition.setText(rowItem.playerPosition);
		
		return convertView;
	}

}
