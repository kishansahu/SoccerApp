package com.liveclips.soccer.database;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class LCSQLiteHelper extends SQLiteOpenHelper {

	private static final String tag = "LCSQLiteHelper";
	private static final String DATABASE_NAME = "system.db";
	private static final int DATABASE_VERSION = 2;
	private Context context;

	public LCSQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.context = context;
	}

	@Override
	public void onCreate(SQLiteDatabase database) {

		Log.e(tag, " onCreate ");
		copyDatabase(context);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		Log.e(tag, " onUpgrade ");
		deleteDatabase(context);
		onCreate(db);

	}

	private void deleteDatabase(Context context) {

		File f = new File("/data/data/" + context.getPackageName()
				+ "/databases/soccer_app.sqlite");

		if (f.exists()) {
			f.delete();
			Log.e(tag, " file deleted");
		}
	}

	private void copyDatabase(Context context) {
		Log.e(tag, " copyDatabase ");
		AssetManager am = context.getAssets();
		OutputStream os;
		File f = new File("/data/data/" + context.getPackageName()
				+ "/databases/");
		if (!f.exists()) {
			f.mkdirs();
		}

		try {
			os = new FileOutputStream("/data/data/" + context.getPackageName()
					+ "/databases/soccer_app.sqlite");
			byte[] b = new byte[100];
			int r;
			InputStream is = am.open("soccer_app.sqlite");
			while ((r = is.read(b)) != -1) {
				os.write(b, 0, r);
			}
			is.close();
			os.close();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			Log.e(tag, "1:" + e1);
		}

	}

}
