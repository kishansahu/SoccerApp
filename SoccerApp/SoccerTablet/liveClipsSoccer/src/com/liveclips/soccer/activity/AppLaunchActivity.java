package com.liveclips.soccer.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;

import com.liveclips.soccer.R;
import com.liveclips.soccer.database.LCSQLiteHelper;

public class AppLaunchActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SQLiteDatabase sqLiteDatabase = new LCSQLiteHelper(this)
				.getReadableDatabase();
		sqLiteDatabase.close();
		setContentView(R.layout.app_launch_page);
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				final Intent mainIntent = new Intent(AppLaunchActivity.this,
						SignUpOptionsActivity.class);
				AppLaunchActivity.this.startActivity(mainIntent);
				AppLaunchActivity.this.finish();
			}
		}, 3000);

	}
}