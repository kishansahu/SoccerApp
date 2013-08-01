/**
 * 
 */
package com.liveclips.soccer.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import android.app.Activity;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.util.Log;

/**
 * @author mohitkumar
 *
 */
public class PropertyReader{
	/**
	 * Read from the /assets directory
	 * @param fileName
	 * @param activity
	 * @return
	 */
	public static Properties getPropertiesFormAssetDirectory(String fileName, Activity activity){
		Resources resources = activity.getResources();
		AssetManager assetManager = resources.getAssets();
		Properties properties = null;
		try {
		    InputStream inputStream = assetManager.open(fileName);
		    properties = new Properties();
		    properties.load(inputStream);
		    Log.d("PropertyReader", fileName + " Loaded");
		} catch (IOException e) {
			 Log.d("PropertyReader", "Failed to open  " + fileName);
		     e.printStackTrace();
		}
		return properties;
		
	}
	/**
	 * Read from the /res/raw directory
	 * @param fileId
	 * @param activity
	 * @return
	 */
	public static Properties getPropertiesFormRawDirectory(int fileId, Activity activity){
		
		Resources resources = activity.getResources();
		Properties properties = null;
		try {
		    InputStream inputStream = resources.openRawResource(fileId);
		    properties = new Properties();
		    properties.load(inputStream);
		    Log.d("PropertyReader", fileId + " Loaded");
		} catch (IOException e) {
			 Log.d("PropertyReader", "Failed to open  " + fileId);
		     e.printStackTrace();
		}
		return properties;
		
	}

}
