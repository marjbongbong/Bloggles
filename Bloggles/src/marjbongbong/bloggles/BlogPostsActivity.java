package marjbongbong.bloggles;

import java.util.ArrayList;

import marjbongbong.bloggles.adapters.MyBlogArrayAdapter;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

public class BlogPostsActivity extends ListActivity {
	Button add_post_button;
	
	private ArrayList<Post> posts = new ArrayList<Post>();
	private MyBlogArrayAdapter listAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_posts);
        Typeface tf = Typeface.createFromAsset(getAssets(), "LetterGothicStd.otf");
        
        getActionBar().setBackgroundDrawable(getResources().getDrawable(R.color.header_background));
		getActionBar().setTitle("My Blog");
		
		add_post_button = (Button)findViewById(R.id.add_post_button);

		addPosts();
		listAdapter = new MyBlogArrayAdapter(BlogPostsActivity.this, R.layout.post_row_layout, posts, tf);
		setListAdapter(listAdapter);		
		
		add_post_button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent ADD = new Intent(BlogPostsActivity.this, AddPostActivity.class);
				ADD.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(ADD);
			}
		});
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		Intent i = new Intent(BlogPostsActivity.this, ViewPostActivity.class);

		Post post = (Post) listAdapter.getItem(position);

		i.putExtra("default_photo", post.getDefaultPhoto());
		i.putExtra("username", post.getUsername());
		i.putExtra("title", post.getPostTitle());
		i.putExtra("details", post.getPostContent());
		i.putExtra("image", post.getPostPhoto());
		i.putExtra("time_posted", post.getTimePosted());

		startActivity(i);
	}
	
	public void addPosts()
	{
		posts.add(new Post(
				R.drawable.jane,
				"@janedoe99",
				"Click here!",
				"Here is a short preview of something awesome.",
				R.drawable.awesome,
				"12:35"));
		posts.add(new Post(
				R.drawable.jane,
				"@janedoe99",
				"Pandajama",
				"Sleeping with a panda",
				R.drawable.panda_pj,
				"1:12"));
		posts.add(new Post(
				R.drawable.jane,
				"@janedoe99",
				"Casual Panda",
				"I wore this during the party last night.",
				R.drawable.panda,
				"1:30"));
		posts.add(new Post(
				R.drawable.jane,
				"@janedoe99",
				"Some Post",
				"This is some post. Click here for more details http://tumblr.com",
				R.drawable.ic_launcher,
				"1:45"));
		posts.add(new Post(
				R.drawable.jane,
				"@janedoe99",
				"Awesome OOTD!",
				"Got this outfit from the nearest thrift shop. Awesome!",
				R.drawable.shop,
				"2:12"));
		posts.add(new Post(
				R.drawable.jane,
				"@janedoe99",
				"I swear.",
				"Review to this outfit, external link here: http://blah.com",
				R.drawable.ic_launcher,
				"2:30"));
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_my_blog, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId())
		{
			case R.id.action_home:
				Intent HOME = new Intent(BlogPostsActivity.this, HomeActivity.class);
				HOME.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(HOME);
				return true;
			case R.id.action_blog_settings:
				Intent BLOG_SETTINGS = new Intent(BlogPostsActivity.this, BlogSettingsActivity.class);
				BLOG_SETTINGS.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(BLOG_SETTINGS);
				return true;
			case R.id.action_logout:
				Intent OUT = new Intent(BlogPostsActivity.this, LoginActivity.class);
				OUT.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(OUT);
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
}
