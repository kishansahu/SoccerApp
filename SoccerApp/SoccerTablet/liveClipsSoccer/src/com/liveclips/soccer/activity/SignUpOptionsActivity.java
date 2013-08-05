package com.liveclips.soccer.activity;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Properties;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.android.AsyncFacebookRunner;
import com.facebook.android.AsyncFacebookRunner.RequestListener;
import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.FacebookError;
import com.liveclips.soccer.R;
import com.liveclips.soccer.utils.PropertyReader;
import com.liveclips.soccer.utils.SharedPreferencesUtil;

public class SignUpOptionsActivity extends Activity {
	Activity activity;
	Context context;
	Properties appCommonProperties;
	// Your Facebook APP ID
	private static String APP_ID;
	TextView existingUserSignInPageLink;
	// Instance of Facebook Class
	private Facebook facebook;
	@SuppressWarnings("deprecation")
	private AsyncFacebookRunner mAsyncRunner;
	String FILENAME = "AndroidSSO_data";
	private SharedPreferences mPrefs;
	ImageView signInfacebookButton, signUpEmailButton;
	EditText emailAddress, signInPasswordEditbox;
	TextView errorMessageForEmailSignIn;
	Button signInButton;
	ActionBar actionBar;

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		activity = this;
		context=this;
		
		actionBar = getActionBar();
		actionBar.hide();
		
		appCommonProperties= PropertyReader
				.getPropertiesFormAssetDirectory("appcommonproperties.properties",
						activity);
		 APP_ID= appCommonProperties.getProperty("facebookApplicationId");
		setContentView(R.layout.signup_option);
		facebook = new Facebook(APP_ID);
		mAsyncRunner = new AsyncFacebookRunner(facebook);

		signInfacebookButton = (ImageView) findViewById(R.id.signInfacebookButton);
		signInfacebookButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				loginToFacebook();
			}
		});

		signUpEmailButton = (ImageView) findViewById(R.id.signUpEmailButton);
		signUpEmailButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent myIntent = new Intent(SignUpOptionsActivity.this,
						AccountCreationByEmail.class);
				SignUpOptionsActivity.this.startActivity(myIntent);
			}
		});

		emailAddress = (EditText) findViewById(R.id.signInEmailEditbox);
		signInPasswordEditbox = (EditText) findViewById(R.id.signInPasswordEditbox);
		signInButton = (Button) findViewById(R.id.SignInButton);
		errorMessageForEmailSignIn = (TextView) findViewById(R.id.errorMessageForEmailSignIn);
		signInButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				/*if (!NflUtils.isEmailValid(emailAddress.getText().toString())) {
					errorMessageForEmailSignIn
							.setText("Please Enter Valid Email Address");
				} else {*/
					Intent userSelectTeamActivityIntent = new Intent(
							SignUpOptionsActivity.this,
							UserSelectTeam.class);
					SignUpOptionsActivity.this
							.startActivity(userSelectTeamActivityIntent);
			//	}

			}
		});
		
		
		
		/*existingUserSignInPageLink = (TextView) findViewById(R.id.existingUserSignInPageLink);
		existingUserSignInPageLink.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent myIntent = new Intent(SignUpOptionsActivity.this,
						ExistingUserSignIn.class);
				SignUpOptionsActivity.this.startActivity(myIntent);
				Intent myIntent = new Intent(SignUpOptionsActivity.this,
						GameActivity.class);
				SignUpOptionsActivity.this.startActivity(myIntent);
			}
		});*/
	}

	@SuppressWarnings("deprecation")
	public void loginToFacebook() {
		mPrefs = getPreferences(MODE_PRIVATE);
		String access_token = mPrefs.getString("access_token", null);
		long expires = mPrefs.getLong("access_expires", 0);

		if (access_token != null) {
			facebook.setAccessToken(access_token);
		}

		if (expires != 0) {
			facebook.setAccessExpires(expires);
		}

		if (!facebook.isSessionValid()) {
			
			facebook.authorize(this,
					new String[] { "email", "publish_stream" },
					new DialogListener() {

						@Override
						public void onCancel() {
							// Function to handle cancel event
						}

						@Override
						public void onComplete(Bundle values) {
							// Function to handle complete event
							// Edit Preferences and update facebook acess_token
							SharedPreferences.Editor editor = mPrefs.edit();
							editor.putString("access_token",
									facebook.getAccessToken());
							editor.putLong("access_expires",
									facebook.getAccessExpires());
							editor.commit();
							/*Toast.makeText(
									getApplicationContext(),
									"access_token: "
											+ facebook.getAccessToken()
											+ "\naccess_expires: "
											+ facebook.getAccessExpires(),
									Toast.LENGTH_LONG).show();*/
							getProfileInformation();
						}

						@Override
						public void onError(DialogError error) {
							// Function to handle error

						}

						@Override
						public void onFacebookError(FacebookError fberror) {
							// Function to handle Facebook errors

						}

					});
		}
	}
	
	@SuppressWarnings("deprecation")
	public void getProfileInformation() {
		mAsyncRunner.request("me", new RequestListener() {
			@Override
			public void onComplete(String response, Object state) {
				Log.d("Profile", response);
				String json = response;
				try {
					JSONObject profile = new JSONObject(json);
					// getting name of the user
					final String nameFromFaceBook = profile.getString("name");
					// getting email of the user
					final String emailIdFromFaceBook = profile.getString("email");
					
					String UserEmailId= appCommonProperties.getProperty("userEmailId");
					SharedPreferencesUtil.saveStringPreferences(context, UserEmailId, emailIdFromFaceBook);
					
					String nameOfUser= appCommonProperties.getProperty("nameOfUser");
					SharedPreferencesUtil.saveStringPreferences(context, nameOfUser, nameFromFaceBook);
					
					Intent userSelectTeamActivityIntent = new Intent(
							SignUpOptionsActivity.this,
							UserSelectTeam.class);
					SignUpOptionsActivity.this
							.startActivity(userSelectTeamActivityIntent);
					
					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							
						}

					});

				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onIOException(IOException e, Object state) {
			}

			@Override
			public void onFileNotFoundException(FileNotFoundException e,
					Object state) {
			}

			@Override
			public void onMalformedURLException(MalformedURLException e,
					Object state) {
			}

			@Override
			public void onFacebookError(FacebookError e, Object state) {
			}
		});
	}
}
