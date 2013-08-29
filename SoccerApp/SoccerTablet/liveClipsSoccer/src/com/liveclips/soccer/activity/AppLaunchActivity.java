package com.liveclips.soccer.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;

import com.liveclips.soccer.R;
import com.liveclips.soccer.database.LCSQLiteHelper;

public class AppLaunchActivity extends Activity {

	ActionBar actionBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/**
		 * Create application database if it don't exists
		 */
		SQLiteDatabase sqLiteDatabase = new LCSQLiteHelper(this)
				.getReadableDatabase();
		sqLiteDatabase.close();

		setContentView(R.layout.app_launch_page);
		getActionBar().hide();
		/**
		 * Start the Sign Up options activity for user to login application.
		 */
		
		  new Handler().postDelayed(new Runnable() {
		  
		  @Override public void run() { final Intent mainIntent = new
		  Intent(AppLaunchActivity.this, SignUpOptionsActivity.class);
		  AppLaunchActivity.this.startActivity(mainIntent);
		  AppLaunchActivity.this.finish(); } }, 3000);
		 /*
		final WebView w = (WebView) findViewById(R.id.playCardBackSideTimeLineWebView);
		w.getSettings().setLoadWithOverviewMode(true);
		w.getSettings().setUseWideViewPort(true);
		WebSettings webSettings = w.getSettings();
		webSettings.setBuiltInZoomControls(true);
		w.setWebChromeClient(new WebChromeClient());
		webSettings.setLoadsImagesAutomatically(true);
		webSettings.setPluginsEnabled(true);

		webSettings.setJavaScriptEnabled(true);
		w.loadUrl("file:///android_asset/handle.html");

		w.setWebViewClient(new WebViewClient() {

			public void onPageFinished(WebView view, String url) {
				Log.e("intent value", "start");

				loadValueToPage(w);

			}
		});

	}

	private void loadValueToPage(WebView w) {
		w.loadUrl("javascript:loadValueToPage('Liver Pool','Aston Villa','75','18.00,Red card message|25,second message','35,first message|30.00,second message','23,mn','45,mn','34,bv','42,kj','45,mn','60,mn');executeCanvas();");
	}
*/
}}
