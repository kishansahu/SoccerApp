package com.liveclips.soccer.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.liveclips.soccer.R;
import com.liveclips.soccer.utils.NflUtils;


public class AccountCreationByEmail extends Activity {
	EditText emailAddress, signUpPasswordEditbox;
	Button createAccountSignupButton, backToSignUpScreenButton;
	TextView errorMessageForEmailSignUp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.createaccount_via_email);
		emailAddress = (EditText) findViewById(R.id.signUpEmailEditbox);
		signUpPasswordEditbox = (EditText) findViewById(R.id.signUpPasswordEditbox);
		createAccountSignupButton = (Button) findViewById(R.id.createAccountSignupButton);
		errorMessageForEmailSignUp = (TextView) findViewById(R.id.errorMessageForEmailSignUp);
		createAccountSignupButton
				.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						if (!NflUtils.isEmailValid(emailAddress.getText()
								.toString())) {
							errorMessageForEmailSignUp
									.setText("Please Enter Valid Email Address");
						}

					}
				});
		
		backToSignUpScreenButton = (Button) findViewById(R.id.backToSignUpScreenButton);
		backToSignUpScreenButton
				.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						Intent myIntent = new Intent(AccountCreationByEmail.this,
								SignUpOptionsActivity.class);
						AccountCreationByEmail.this.startActivity(myIntent);
					}
				});

	}

}
