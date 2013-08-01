package com.liveclips.soccer.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.liveclips.soccer.R;

public class ExistingUserSignIn extends Activity {
	EditText emailAddress, signInPasswordEditbox;
	Button signInButton, backToSignUpScreenButton;
	TextView errorMessageForEmailSignIn;
	Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.existing_user_signin);
		context = this;
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
							ExistingUserSignIn.this,
							UserSelectTeam.class);
					ExistingUserSignIn.this
							.startActivity(userSelectTeamActivityIntent);
			//	}

			}
		});

		backToSignUpScreenButton = (Button) findViewById(R.id.backToSignUpScreenButton);
		backToSignUpScreenButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent signUpOptionsActivityIntent = new Intent(
						ExistingUserSignIn.this, SignUpOptionsActivity.class);
				ExistingUserSignIn.this
						.startActivity(signUpOptionsActivityIntent);
			}
		});

	}

}
