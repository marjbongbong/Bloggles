package marjbongbong.bloggles;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class UserProfileActivity extends Activity{

	private int default_photo;
	private String username, name;
	
	ImageView profile_photo;
	TextView username_label, name_label, membership_date_label,
	 		 last_login_label, blog_posts_label, date_last_posted_label,
	 		 followers_label, membership_date, last_login, blog_posts, 
	 		 date_last_posted, followers;
	Button follow_button;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().setBackgroundDrawable(getResources().getDrawable(R.color.header_background));
		setContentView(R.layout.activity_user_profile);
		Typeface tf = Typeface.createFromAsset(getAssets(), "LetterGothicStd.otf");
		
		Intent i = getIntent();
		
		default_photo = i.getIntExtra("default_photo", R.drawable.default_photo);
		username = i.getStringExtra("username");
		name = i.getStringExtra("name");
		
		profile_photo = (ImageView)findViewById(R.id.profile_photo);
		username_label = (TextView)findViewById(R.id.username_label);
		name_label = (TextView)findViewById(R.id.name_label);
		last_login_label = (TextView)findViewById(R.id.last_login_label);
		blog_posts_label = (TextView)findViewById(R.id.blog_posts_label);
		date_last_posted_label = (TextView)findViewById(R.id.date_last_posted_label);
		followers_label = (TextView)findViewById(R.id.followers_label);
		membership_date = (TextView)findViewById(R.id.membership_date);
		last_login = (TextView)findViewById(R.id.last_login);
		blog_posts = (TextView)findViewById(R.id.blog_posts);
		date_last_posted = (TextView)findViewById(R.id.date_last_posted);
		followers = (TextView)findViewById(R.id.followers);
		follow_button = (Button)findViewById(R.id.follow_button);
		
		username_label.setTypeface(tf);
		name_label.setTypeface(tf);
		membership_date_label.setTypeface(tf);
		last_login_label.setTypeface(tf);
		blog_posts_label.setTypeface(tf);
		date_last_posted_label.setTypeface(tf);
		followers_label.setTypeface(tf);
		membership_date.setTypeface(tf);
		last_login.setTypeface(tf);
		blog_posts.setTypeface(tf);
		date_last_posted.setTypeface(tf);
		followers.setTypeface(tf);
		
		profile_photo.setImageResource(default_photo);
		username_label.setText(username);
		name_label.setText(name);
		
		follow_button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(follow_button.getText().equals("Follow"))
				{
					follow_button.setText("Unfollow");
					follow_button.setBackgroundColor(follow_button.getContext().getResources().getColor(R.color.unfollow_button));
				}
				else if(follow_button.getText().equals("Unfollow"))
				{
					follow_button.setText("Follow");
					follow_button.setBackgroundColor(follow_button.getContext().getResources().getColor(R.color.follow_button));
				}
			}
		});
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
				Intent HOME = new Intent(UserProfileActivity.this, HomeActivity.class);
				HOME.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(HOME);
				return true;
			case R.id.action_logout:
				Intent OUT = new Intent(UserProfileActivity.this, LoginActivity.class);
				OUT.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(OUT);
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
}
