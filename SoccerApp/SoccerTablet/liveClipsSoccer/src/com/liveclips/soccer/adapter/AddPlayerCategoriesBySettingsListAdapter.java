package com.liveclips.soccer.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.liveclips.soccer.R;
import com.liveclips.soccer.model.LiveClipsContentListItem;
import com.liveclips.soccer.utils.SharedPreferencesUtil;


public class AddPlayerCategoriesBySettingsListAdapter extends BaseAdapter {

	Context context;
	List<LiveClipsContentListItem> items;
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return items.size();
	}

	public AddPlayerCategoriesBySettingsListAdapter(List<LiveClipsContentListItem> addPlayerContentList, Context context){
		items = addPlayerContentList;
		this.context = context;
	}
	
	public View getView(int position, View view, ViewGroup parent) {
		
		if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            view = inflater.inflate(R.layout.common_list_row_item, parent, false);
        }
		
		final LiveClipsContentListItem liveClipsContentListItem = items.get(position);

        TextView textView = (TextView) view.findViewById(R.id.textForList);
        textView.setText(liveClipsContentListItem.getRowText());
        
       if(liveClipsContentListItem.isUpdateInSharedPreference()){
    	 final ImageView leftImage=(ImageView) view.findViewById(R.id.ListLeftImageView);
    	 if(liveClipsContentListItem.isUsersFavourite()){
    		 leftImage.setImageResource(R.drawable.star_high);
    	 }else{
    		 leftImage.setImageResource(R.drawable.star_low);
    	 }
    	 leftImage.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(liveClipsContentListItem.isUsersFavourite() == false){
					leftImage.setImageResource(R.drawable.star_high);
					liveClipsContentListItem.setUsersFavourite(true);
					SharedPreferencesUtil.saveFavouriteInSharedPreferencesList(context, liveClipsContentListItem.getEntityId(), "player");
					}else{
						leftImage.setImageResource(R.drawable.star_low);
						liveClipsContentListItem.setUsersFavourite(false);
						SharedPreferencesUtil.removeFavouriteFromSharedPreferencesList(context, liveClipsContentListItem.getEntityId(), "player");
					}
				
			}
		} );
        }
		return view;
	}

	@Override
	public Object getItem(int i) {
		// TODO Auto-generated method stub
		 return items.get(i);
	}

	@Override
    public long getItemId(int i) {
        return i;                   // index number
    }

}
