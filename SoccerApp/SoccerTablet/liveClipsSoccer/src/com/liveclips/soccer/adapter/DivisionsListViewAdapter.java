/**
 * 
 */
package com.liveclips.soccer.adapter;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.liveclips.nfl.R;
import com.liveclips.nfl.model.DivisionsItem;



/**
 * @author mohitkumar
 *
 */
@SuppressLint("NewApi")
public class DivisionsListViewAdapter  extends ArrayAdapter<DivisionsItem>{

	private Context context;
	
	private List<DivisionsItem> divisionsItems;
	
		
	public DivisionsListViewAdapter(Context context, int textViewResourceId, List<DivisionsItem> divisionsItems) {
		super(context, textViewResourceId, divisionsItems);
		this.context = context;
		this.divisionsItems = divisionsItems;
		
	}
	
	
	@Override
	public int getCount() {
		return divisionsItems.size();
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		DivisionViewHolder holder = null;
			
		DivisionsItem divisionsItem = getItem(position);
		
		LayoutInflater mInflater = (LayoutInflater) context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			
			if (convertView == null) {
				convertView = mInflater.inflate(
						R.layout.division_fragment_list_row_item_header, null);
				holder = new DivisionViewHolder();
				
				holder.divisionText = (TextView) convertView
						.findViewById(R.id.division_text);
				holder.divisionLogoImage = (ImageView) convertView.findViewById(R.id.division_logo);
				
				convertView.setTag( holder);
				
				
			} else{
				
				holder = (DivisionViewHolder) convertView.getTag();
			}
				
				holder.divisionText.setText(divisionsItem.getDivisionName());
					
				holder.divisionLogoImage.setImageResource(divisionsItem.getDivisionLogo());
					
				
			
			
		
		return convertView;
	}
	
	
	
	
	public static class DivisionViewHolder {
		TextView divisionText;
		ImageView divisionLogoImage;
	}
	
	
	
	
	

}
