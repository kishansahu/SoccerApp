/**
 * 
 */
package com.liveclips.soccer.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.liveclips.soccer.model.LeadersTypeItem;
import com.liveclips.soccer.model.PassingLeaderItem;
import com.liveclips.soccer.model.TeamItem;
import com.liveclips.soccer.model.WeekItem;


/**
 * @author mohitkumar
 *
 */
public class HighlightsService {
	
	/**
	 * Get the week status details for highlights
	 * @param userID
	 * @return
	 */
	public static List<WeekItem> getWeeksDetails(String userID){
		
		List<WeekItem> weekItems = new ArrayList<WeekItem>();
		WeekItem item1 = new WeekItem();
		item1.setWeekName("Week 1");
		weekItems.add(item1);
		
		WeekItem item2 = new WeekItem();
		item2.setWeekName("Week 2");
		weekItems.add(item2);
		
		WeekItem item3 = new WeekItem();
		item3.setWeekName("Week 3");
		weekItems.add(item3);
		
		WeekItem item4 = new WeekItem();
		item4.setWeekName("Week 4");
		weekItems.add(item4);
		
		WeekItem item5 = new WeekItem();
		item5.setWeekName("Week 5");
		weekItems.add(item5);
		return weekItems;
		
	}

	public static Map<String, List<LeadersTypeItem>> getLeadersDetails(
			String string) {
		
		Map<String, List<LeadersTypeItem>> leadersInfoMap = new HashMap<String, List<LeadersTypeItem>>();
		List<LeadersTypeItem> offenceLeadersTypeItems = new ArrayList<LeadersTypeItem>();
		
		LeadersTypeItem item1 = new LeadersTypeItem();
		item1.setLeadersTypeName("Passing Yards");
		offenceLeadersTypeItems.add(item1);
		
		LeadersTypeItem item2= new LeadersTypeItem();
		item2.setLeadersTypeName("Rushing Yards");
		offenceLeadersTypeItems.add(item2);
		
		LeadersTypeItem item3 = new LeadersTypeItem();
		item3.setLeadersTypeName("Receiving Yards");
		offenceLeadersTypeItems.add(item3);
		
		LeadersTypeItem item4 = new LeadersTypeItem();
		item4.setLeadersTypeName("Touchdown");
		offenceLeadersTypeItems.add(item4);
		
		LeadersTypeItem item5 = new LeadersTypeItem();
		item5.setLeadersTypeName("Scoring");
		offenceLeadersTypeItems.add(item5);
		
		leadersInfoMap.put("offense", offenceLeadersTypeItems);
		
		List<LeadersTypeItem> defenceLeadersTypeItems = new ArrayList<LeadersTypeItem>();
		
		LeadersTypeItem item6 = new LeadersTypeItem();
		item6.setLeadersTypeName("Sacks");
		defenceLeadersTypeItems.add(item6);
		
		LeadersTypeItem item7 = new LeadersTypeItem();
		item7.setLeadersTypeName("Interceptions");
		defenceLeadersTypeItems.add(item7);
		
		LeadersTypeItem item8 = new LeadersTypeItem();
		item8.setLeadersTypeName("Tackels");
		defenceLeadersTypeItems.add(item8);
		
		leadersInfoMap.put("defense", defenceLeadersTypeItems);
		
		List<LeadersTypeItem> specialTeamLeadersTypeItems = new ArrayList<LeadersTypeItem>();
		
		LeadersTypeItem item9 = new LeadersTypeItem();
		item9.setLeadersTypeName("Punts");
		specialTeamLeadersTypeItems.add(item9);
		
		LeadersTypeItem item10 = new LeadersTypeItem();
		item10.setLeadersTypeName("Punts Returns");
		specialTeamLeadersTypeItems.add(item10);
		
		LeadersTypeItem item11 = new LeadersTypeItem();
		item11.setLeadersTypeName("Kick Returns");
		specialTeamLeadersTypeItems.add(item11);
		
		leadersInfoMap.put("specialTeams", specialTeamLeadersTypeItems);
		
		LeadersTypeItem item12 = new LeadersTypeItem();
		item12.setLeadersTypeName("Filed Goals");
		specialTeamLeadersTypeItems.add(item12);
		return leadersInfoMap;
	}

	public static List<PassingLeaderItem> getPassingLeadersDetails(String string) {
		List<PassingLeaderItem> items = new ArrayList<PassingLeaderItem>();
		
		PassingLeaderItem leaderItem1 = new PassingLeaderItem();
		leaderItem1.setLeaderName("Loe Flacco");
		leaderItem1.setYards(221);
		TeamItem leaderTeam1 = new TeamItem();
		leaderTeam1.setTeamName("BAL");
		leaderItem1.setLeaderTeam(leaderTeam1);
		items.add(leaderItem1);
		
		PassingLeaderItem leaderItem2 = new PassingLeaderItem();
		leaderItem2.setLeaderName("Tom Brady");
		leaderItem2.setYards(201);
		TeamItem leaderTeam2 = new TeamItem();
		leaderTeam2.setTeamName("NE");
		leaderItem2.setLeaderTeam(leaderTeam2);
		items.add(leaderItem2);
		
		PassingLeaderItem leaderItem3 = new PassingLeaderItem();
		leaderItem3.setLeaderName("Mat Rayana");
		leaderItem3.setYards(181);
		TeamItem leaderTeam3 = new TeamItem();
		leaderTeam3.setTeamName("EAI");
		leaderItem3.setLeaderTeam(leaderTeam3);
		items.add(leaderItem3);
		return items;
	}

}
