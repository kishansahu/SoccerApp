package com.liveclips.soccer.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

public class SharedPreferencesUtil {
	  public static final int MODE_PRIVATE = 0;
	 
	  public static void saveObject(Context context,String key, Object object) {
		  
		  SharedPreferences appSharedPrefs = PreferenceManager
		  .getDefaultSharedPreferences(context.getApplicationContext());
		  Editor prefsEditor = appSharedPrefs.edit();
		  Gson gson = new Gson();
		  String json = gson.toJson(object);
		  prefsEditor.putString(key, json);
		  prefsEditor.commit();
 
	}
	  
	public static void saveStringPreferences(Context context, String key, String value) {
		SharedPreferences sPrefs=PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = sPrefs.edit();
		editor.putString(key, value);
		editor.commit();
	}
	
	public static void saveIntegerPreferences(Context context, String key, Integer value) {
		SharedPreferences sPrefs=PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = sPrefs.edit();
		editor.putInt(key, value);
		editor.commit();
	}

	public static void saveBooleanPreferences(Context context, String key, Boolean value) {
		SharedPreferences sPrefs=PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = sPrefs.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}
	
	public static void deletePreferences(Context context, String key) {
		SharedPreferences sPrefs=PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = sPrefs.edit();
		editor.remove(key);
		editor.commit();
	}

	public static Boolean getBooleanPreferences(Context context, String key) {
		SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(context);
		Boolean savedPref = sharedPreferences.getBoolean(key, false);
		return savedPref;
	}
	
	public static String getStringPreferences(Context context, String key) {
		SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(context);
		String savedPref = sharedPreferences.getString(key, "");
		return savedPref;
	}
	
	public static Integer getIntegerPreferences(Context context, String key) {
		SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(context);
		Integer savedPref = sharedPreferences.getInt(key, 0);
		return savedPref;
	}
	
	public static void saveFavouriteInSharedPreferencesList(Context context, String id, String entity){
		SharedPreferences sPrefs=PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor sEdit=sPrefs.edit();
		int size=Integer.parseInt(sPrefs.getString(entity+"size","0"));
		 size = size + 1;
		 sEdit.putString(entity+ size,id);
		 sEdit.remove((entity + "size"));
		 sEdit.putString(entity+"size",""+size);
		 sEdit.commit();
		 
	}
	
	public static void clearAllSharedPreferencesList(Context context){
		SharedPreferences sPrefs=PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor sEdit=sPrefs.edit();
		sEdit.clear();
		sEdit.commit();
	}
	
	public static List<String> getFavouriteInSharedPreferencesList(Context context, String entity){
		SharedPreferences sPrefs=PreferenceManager.getDefaultSharedPreferences(context);
		List<String> favList=new ArrayList<String>();
		 Integer size=Integer.parseInt((String)sPrefs.getString(entity+"size","0"));
		 if(size > 0){
		 int j = 1;
		 while(favList.size() != size){
			 String fav= sPrefs.getString((entity+j), "");
			 if(!fav.isEmpty()){
		        favList.add(fav);
			 }
			j=j+1; 
		 }
		 
		 }
		 Collections.sort(favList);
		 return favList;
	}
	
	public static void removeFavouriteFromSharedPreferencesList(Context context, String id, String entity){
				
		SharedPreferences sPrefs=PreferenceManager.getDefaultSharedPreferences(context);
		
		SharedPreferences.Editor sEdit=sPrefs.edit();
		int size=Integer.parseInt(sPrefs.getString(entity +"size","0"));
		if(size > 0){
			for (int j = 1; j <= sPrefs.getAll().size(); j++) {
				String favTeam = sPrefs.getString((entity + j),	"");
				if (id.equalsIgnoreCase(favTeam)) {
					sEdit.remove((entity + j));
					int sizeAtPresent = Integer.parseInt(sPrefs.getString(entity+"size", "0"));
					sEdit.remove((entity + "size"));
					int newSize=  sizeAtPresent - 1;
					sEdit.putString(entity+"size", ""+newSize);
					break;
				}
			}
			 sEdit.commit();
		}
		
}
}
