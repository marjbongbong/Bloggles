package marjbongbong.bloggles;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity {

	EditText username_field, password_field;
	Button login_button;
	TextView register_link, register_label;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		getActionBar().hide();
		setContentView(R.layout.activity_login);
		Typeface tf = Typeface.createFromAsset(getAssets(), "LetterGothicStd.otf");
		
		username_field = (EditText) findViewById(R.id.username_field);
		password_field = (EditText) findViewById(R.id.password_field);
		login_button =  (Button)findViewById(R.id.login_button);
		register_link = (TextView)findViewById(R.id.register_link);
		register_label = (TextView)findViewById(R.id.register_label);
		
		username_field.setTypeface(tf);
		password_field.setTypeface(tf);
		login_button.setTypeface(tf);
		register_label.setTypeface(tf);
		register_link.setTypeface(tf);
		
		login_button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if(username_field.getText().toString().equals("") ||
				   username_field.getText().toString().equals(" ") ||
				   password_field.getText().toString().equals("") || 
				   password_field.getText().toString().equals(" "))
					warning();
				else {
					Intent HOME = new Intent(LoginActivity.this, HomeActivity.class);
					HOME.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(HOME);
				}
			}
		});	
		
		register_link.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent REGISTER = new Intent(LoginActivity.this, RegisterActivity.class);
				REGISTER.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(REGISTER);
			}
		});	
	}

	public void warning()
	{
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(LoginActivity.this);

		alertDialogBuilder.setTitle("Warning!");

		alertDialogBuilder
				.setMessage("Please enter username and password.")
				.setCancelable(false)
				.setNeutralButton("Close", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						
					}
				});

		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();
	}
}