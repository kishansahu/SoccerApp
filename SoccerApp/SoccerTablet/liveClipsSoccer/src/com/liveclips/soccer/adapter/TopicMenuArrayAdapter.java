package com.liveclips.soccer.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.liveclips.soccer.R;
import com.liveclips.soccer.model.LiveClipsContentListItem;
import com.liveclips.soccer.utils.ImageProcessingUtil;

public class TopicMenuArrayAdapter extends BaseAdapter {

	private List<LiveClipsContentListItem> LiveClipsContentListItems;
	private final Context context;
	int layoutResourceId;
	static int selectedIndex = -1;

	public int getSelectedIndex() {
		return selectedIndex;
	}

	public void setSelectedIndex(int selectedIndex) {
		this.selectedIndex = selectedIndex;
	}

	public TopicMenuArrayAdapter(Context context, int layoutResourceId,
			List<LiveClipsContentListItem> LiveClipsContentListItems) {
		super();
		this.context = context;
		this.LiveClipsContentListItems = LiveClipsContentListItems;
		this.layoutResourceId = layoutResourceId;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (convertView == null) {
			convertView = inflater.inflate(layoutResourceId, parent, false);
		}

		View parentView = (View) convertView.findViewById(R.id.parentTextView);

		TextView textForList = (TextView) convertView
				.findViewById(R.id.textForList);
		ImageView ListLeftImageView = (ImageView) convertView
				.findViewById(R.id.ListLeftImageView);
		ImageView ListRightImageView = (ImageView) convertView
				.findViewById(R.id.ListRightImageView);

		textForList.setText(LiveClipsContentListItems.get(position)
				.getRowText());
		
		ListLeftImageView.setVisibility(View.INVISIBLE);
// If Category chosen is favourite Teams
		if (LiveClipsContentListItems.get(position).getCategoryType().equalsIgnoreCase("favouriteTeams")) {
			ListLeftImageView.setVisibility(View.VISIBLE);
			if(LiveClipsContentListItems.get(position).isShowLeftSideDefaultIcon() == false){
			ListLeftImageView.setImageResource(ImageProcessingUtil.getTeamLogoImageResourceByTeamId((Activity)context,
							LiveClipsContentListItems.get(position).getLeftSideImage()));
			}else{
				ListLeftImageView.setImageResource(ImageProcessingUtil.getImageResourceByImageName((Activity)context, "horizontal_circular_icon"));
				ListRightImageView.setImageResource(ImageProcessingUtil.getImageResourceByImageName((Activity)context, "delete_button"));
				ListRightImageView.setVisibility(View.INVISIBLE);
			}
		}
		
// If Category chosen is favourite Players
		if (LiveClipsContentListItems.get(position).getCategoryType().equalsIgnoreCase("favouritePlayers")) {
			ListLeftImageView.setVisibility(View.VISIBLE);
			ListLeftImageView.setImageResource(ImageProcessingUtil.getImageResourceByImageName((Activity)context, "horizontal_circular_icon"));
			ListRightImageView.setImageResource(ImageProcessingUtil.getImageResourceByImageName((Activity)context, "delete_button"));
			ListRightImageView.setVisibility(View.INVISIBLE);
		}
		
		
// If Category chosen is activities
				if (LiveClipsContentListItems.get(position).isTopicMenu() == true) {
					String imageName =  LiveClipsContentListItems.get(position).getLeftSideImage().trim().toLowerCase().replaceAll("\\s",""); 
					
					ListLeftImageView.setVisibility(View.VISIBLE);
					ListLeftImageView.setImageResource(ImageProcessingUtil.getImageResourceByImageName((Activity)context, imageName.substring(1,imageName.length())));
				}
				

		if (selectedIndex != -1 && position == selectedIndex) {

			parentView.setSelected(true);

		} else {

			parentView.setSelected(false);

		}
		return convertView;
	}

	@Override
	public int getCount() {

		return LiveClipsContentListItems.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

}
