package marjbongbong.bloggles;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewPostActivity extends Activity {

	private int default_photo, post_photo;
	private String username, post_title, post_content, time_posted;
	
	ImageView user_photo, photo;
	TextView author, title, content, time_label;
	EditText comment_field;
	Button post_button;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().setBackgroundDrawable(getResources().getDrawable(R.color.header_background));
		setContentView(R.layout.activity_view_post);
		Typeface tf = Typeface.createFromAsset(getAssets(), "LetterGothicStd.otf");
		Intent i = getIntent();
		
		comment_field = (EditText)findViewById(R.id.comment_field);
		post_button = (Button)findViewById(R.id.post_button);
		
		default_photo = i.getIntExtra("default_photo", R.drawable.default_photo);
		username = i.getStringExtra("username");
		post_title = i.getStringExtra("title");
		post_content = i.getStringExtra("details");
		post_photo = i.getIntExtra("image", R.drawable.ic_launcher);
		time_posted = i.getStringExtra("time_posted");
		
		user_photo = (ImageView)findViewById(R.id.user_photo);
		author = (TextView)findViewById(R.id.author);
		title = (TextView)findViewById(R.id.title);
		content = (TextView)findViewById(R.id.content);
		photo = (ImageView)findViewById(R.id.post_image);
		time_label = (TextView)findViewById(R.id.time_label);
		
		author.setTypeface(tf);
		title.setTypeface(tf);
		content.setTypeface(tf);
		time_label.setTypeface(tf);
		comment_field.setTypeface(tf);
		post_button.setTypeface(tf);
		
		user_photo.setImageResource(default_photo);
		author.setText(username);
		title.setText(post_title);
		content.setText(post_content);
		photo.setImageResource(post_photo);
		time_label.setText(time_posted);
		
		if(photo.getDrawable().equals(getResources().getDrawable(R.drawable.ic_launcher))){
			photo.setVisibility(View.GONE);
		} else
			photo.setVisibility(View.VISIBLE);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_home_out, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId())
		{
			case R.id.action_home:
				Intent HOME = new Intent(ViewPostActivity.this, HomeActivity.class);
				HOME.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(HOME);
				return true;
			case R.id.action_logout:
				Intent OUT = new Intent(ViewPostActivity.this, LoginActivity.class);
				OUT.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(OUT);
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
}