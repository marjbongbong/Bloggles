package marjbongbong.bloggles;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class BlogSettingsActivity extends Activity {
	
	Button save_button, cancel_button;
	CheckBox checkBox1, checkBox2, checkBox3, checkBox4, checkBox5;
	TextView spinnertext;
	
	List<String> categories = new ArrayList<String>();
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getActionBar().setBackgroundDrawable(getResources().getDrawable(R.color.header_background));
		getActionBar().setTitle(R.string.blog_settings);
		getActionBar().setIcon(getResources().getDrawable(R.drawable.ic_blog_settings));
		
		setContentView(R.layout.activity_blog_settings);
		Typeface tf = Typeface.createFromAsset(getAssets(), "LetterGothicStd.otf");
		
		Spinner spinner = (Spinner) findViewById(R.id.spinner1);
		spinnertext = (TextView)findViewById(R.id.spinnertext);
		checkBox1 = (CheckBox)findViewById(R.id.checkBox1);
		checkBox2 = (CheckBox)findViewById(R.id.checkBox2);
		checkBox3 = (CheckBox)findViewById(R.id.checkBox3);
		checkBox4 = (CheckBox)findViewById(R.id.checkBox4);
		checkBox5 = (CheckBox)findViewById(R.id.checkBox5);
		
		addCategory();
		
		spinnertext.setTypeface(tf);
		checkBox1.setTypeface(tf);
		checkBox2.setTypeface(tf);
		checkBox3.setTypeface(tf);
		checkBox4.setTypeface(tf);
		checkBox5.setTypeface(tf);
		
		 // Creating adapter for spinner
       ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(BlogSettingsActivity.this, android.R.layout.simple_spinner_item, categories);

       // Drop down layout style - list view with radio button
       dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       
       // attaching data adapter to spinner
       spinner.setAdapter(dataAdapter);
	}

	public void addCategory(){
		categories.add("GMT-12:00");
        categories.add("GMT-11:00");
        categories.add("GMT-10:00");
        categories.add("GMT-9:30");
        categories.add("GMT-9:00");
        categories.add("GMT-8:00");
        categories.add("GMT-7:00");
        categories.add("GMT-6:00");
        categories.add("GMT-5:00");
        categories.add("GMT-4:00");
        categories.add("GMT-3:00");
        categories.add("GMT-2:00");
        categories.add("GMT-1:00");
        categories.add("GMT");
        categories.add("GMT+1:00");
        categories.add("GMT+2:00");
        categories.add("GMT+3:00");
        categories.add("GMT+4:00");
        categories.add("GMT+5:00");
        categories.add("GMT+6:00");
        categories.add("GMT+7:00");
        categories.add("GMT+8:00");
        categories.add("GMT+9:00");
        categories.add("GMT+10:00");
        categories.add("GMT+11:00");
        categories.add("GMT+12:00");
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_save_cancel, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId())
		{
		case R.id.action_save:
			Toast.makeText(BlogSettingsActivity.this, "Settings Saved.", Toast.LENGTH_SHORT).show();
			Intent SAVE = new Intent(BlogSettingsActivity.this, BlogPostsActivity.class);
			SAVE.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(SAVE);
			return true;
		case R.id.action_cancel:
			Toast.makeText(BlogSettingsActivity.this, "Action cancelled", Toast.LENGTH_SHORT).show();
			Intent CANCEL = new Intent(BlogSettingsActivity.this, BlogPostsActivity.class);
			CANCEL.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(CANCEL);
			return true;
		case R.id.action_logout:
			Toast.makeText(BlogSettingsActivity.this, "Logged out.", Toast.LENGTH_SHORT).show();
			Intent OUT = new Intent(BlogSettingsActivity.this, LoginActivity.class);
			OUT.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(OUT);
			return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
}
