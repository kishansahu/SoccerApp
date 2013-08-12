/**
 * 
 */
package com.liveclips.soccer.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.liveclips.soccer.R;
import com.liveclips.soccer.database.DatabaseHelper;
import com.liveclips.soccer.model.TeamItem;



/**
 * @author mohit kumar
 *
 */
public class UpdateTeamInformation {
	
	public static void updateTeamInfo(View rowObject, Activity activity, Context context){
		TextView weekTextTextView = (TextView) rowObject.findViewById(R.id.match_date);
		String weekTextTextViewValue = (String) weekTextTextView.getText();
		
		TextView versusTextTextView = (TextView) rowObject.findViewById(R.id.versus_text);
		String versusTextTextViewValue = (String) versusTextTextView.getText();
		
		
		//String teamLogoTextViewValue = (String) weekTextTextView.getText();
		//teamLogoTextView.
		
		TextView teamNameTextView = (TextView) rowObject.findViewById(R.id.team_name);
		String teamNameTextViewValue = (String) teamNameTextView.getText();
				
		TextView teamIdTextView = (TextView) rowObject.findViewById(R.id.team_id);
		String teamId = (String) teamIdTextView.getText();
		TeamItem teamItem = new DatabaseHelper(context).getTeamInfoByTeamId(teamId);
		
		TextView firstTeamSecondNameTextView = (TextView) activity.findViewById(R.id.firstTeamSecondName);
		firstTeamSecondNameTextView.setText(teamItem.getTeamAbbreviation());
		
		TextView firstTeamFirstNameTextView = (TextView) activity.findViewById(R.id.firstTeamSecondName);
		firstTeamFirstNameTextView.setText(teamItem.getTeamName());
		
		/*ImageView teamLogoTextView = (ImageView) activity.findViewById(R.id.firstTeamLargeIcon);
		teamLogoTextView.setImageDrawable(ImageProcessingUtil.getTeamLogoImageDrawableByTeamId(activity, teamId));*/
		
	}

}
