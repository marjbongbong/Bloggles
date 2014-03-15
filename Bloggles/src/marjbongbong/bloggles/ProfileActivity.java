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
import android.widget.TextView;

public class ProfileActivity extends Activity{
	
	Button followers_button, following_button, blog_posts_button;
	TextView username_label, name_label, membership_date_label,
			 last_login_label, blog_posts_label, date_last_posted_label,
			 followers_label, following_label, membership_date,
			 last_login, blog_posts, date_last_posted, followers,
			 following;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		getActionBar().setBackgroundDrawable(getResources().getDrawable(R.color.header_background));
		getActionBar().setTitle("Profile");
		getActionBar().setIcon(getResources().getDrawable(R.drawable.ic_profile));
		
		setContentView(R.layout.activity_profile);
		Typeface tf = Typeface.createFromAsset(getAssets(), "LetterGothicStd.otf");
		
		followers_button = (Button)findViewById(R.id.followers_button);
		following_button = (Button)findViewById(R.id.following_button);
		blog_posts_button = (Button)findViewById(R.id.blog_posts_button);
		
		username_label = (TextView)findViewById(R.id.username_label);
		name_label = (TextView)findViewById(R.id.name_label);
		membership_date_label = (TextView)findViewById(R.id.membership_date_label);
		last_login_label = (TextView)findViewById(R.id.last_login_label);
		blog_posts_label = (TextView)findViewById(R.id.blog_posts_label);
		date_last_posted_label = (TextView)findViewById(R.id.date_last_posted_label);
		followers_label = (TextView)findViewById(R.id.followers_label);
		following_label = (TextView)findViewById(R.id.following_label);
		membership_date = (TextView)findViewById(R.id.membership_date);
		last_login = (TextView)findViewById(R.id.last_login);
		blog_posts = (TextView)findViewById(R.id.blog_posts);
		date_last_posted = (TextView)findViewById(R.id.date_last_posted);
		followers = (TextView)findViewById(R.id.followers);
		following = (TextView)findViewById(R.id.following);
		 
		username_label.setTypeface(tf);
		name_label.setTypeface(tf);
		membership_date_label.setTypeface(tf);
		last_login_label.setTypeface(tf);
		blog_posts_label.setTypeface(tf);
		date_last_posted_label.setTypeface(tf);
		followers_label.setTypeface(tf);
		following_label.setTypeface(tf);
		membership_date.setTypeface(tf);
		last_login.setTypeface(tf);
		blog_posts.setTypeface(tf);
		date_last_posted.setTypeface(tf);
		followers.setTypeface(tf);
		following.setTypeface(tf);
		 
		followers_button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent FOLLOWERS = new Intent(ProfileActivity.this, FollowersActivity.class);
				FOLLOWERS.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(FOLLOWERS);
			}
		});	
		
		following_button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent FOLLOWING = new Intent(ProfileActivity.this, FollowingActivity.class);
				FOLLOWING.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(FOLLOWING);
			}
		});	
		
		blog_posts_button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent POSTS = new Intent(ProfileActivity.this, BlogPostsActivity.class);
				POSTS.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(POSTS);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_profile, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId())
		{
			case R.id.action_home:
				Intent HOME = new Intent(ProfileActivity.this, HomeActivity.class);
				HOME.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(HOME);
				return true;
			case R.id.action_account_settings:
				Intent ACCOUNT_SETTINGS = new Intent(ProfileActivity.this, AccountSettingsActivity.class);
				ACCOUNT_SETTINGS.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(ACCOUNT_SETTINGS);
				return true;
			case R.id.action_logout:
				Intent OUT = new Intent(ProfileActivity.this, LoginActivity.class);
				OUT.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(OUT);
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
}
