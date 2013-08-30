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
import com.liveclips.soccer.imageutils.ImageLoader;
import com.liveclips.soccer.model.PlayerItem;


public class PlayerListViewAdapter extends ArrayAdapter<PlayerItem> {

	Context context;
	List<PlayerItem> items;
	ImageLoader imgLoader;

	public PlayerListViewAdapter(Context context, int resourceId,
			List<PlayerItem> items) {
		super(context, resourceId, items);
		this.context = context;
		this.items = items;
		 imgLoader = new ImageLoader(context);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return items.size();
	}

	private class PlayerViewHolder {
		TextView playerData1;
		TextView playerData2;
		TextView playerData3;
		TextView playerData4;
		TextView playerDetails;
		TextView playerName;
		ImageView playerImage;
		// ImageView imageView;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		PlayerViewHolder holder = null;
		PlayerItem rowItem = getItem(position);

		LayoutInflater mInflater = (LayoutInflater) context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		if (convertView == null) {
			convertView = mInflater.inflate(
					R.layout.game_popover_list_row__item_player, null);
			holder = new PlayerViewHolder();
			
			
			ImageView playerImage = ((ImageView) convertView
					.findViewById(R.id.popover_player_pic));
			imgLoader.DisplayImage(rowItem.getPlayerImage(), playerImage);
			
			
			/*holder.playerImage = (ImageView) convertView
					.findViewById(R.id.popover_player_pic);*/
			
			
			holder.playerName = (TextView) convertView
					.findViewById(R.id.popover_player_name);
			holder.playerDetails = (TextView) convertView
					.findViewById(R.id.popover_player_details);
			holder.playerData1 = (TextView) convertView
					.findViewById(R.id.popover_player_data1);
			holder.playerData2 = (TextView) convertView
					.findViewById(R.id.popover_player_data2);
			holder.playerData3 = (TextView) convertView
					.findViewById(R.id.popover_player_data3);
			holder.playerData4 = (TextView) convertView
					.findViewById(R.id.popover_player_data4);

			convertView.setTag(holder);
		} else
			holder = (PlayerViewHolder) convertView.getTag();

		ImageView playerImage = ((ImageView) convertView
				.findViewById(R.id.popover_player_pic));
		imgLoader.DisplayImage(rowItem.getPlayerImage(), playerImage);
		
		
		
		//holder.playerImage.setImageResource(rowItem.playerImage);
		holder.playerName.setText(rowItem.getPlayerName());
		holder.playerDetails.setText(rowItem.getPlayerDetails());
		holder.playerData1.setText(rowItem.getPlayerdata1());
		holder.playerData2.setText(rowItem.getPlayerdata2());
		holder.playerData3.setText(rowItem.getPlayerdata3());
		holder.playerData4.setText(rowItem.getPlayerdata4());
		
		return convertView;
	}

}
