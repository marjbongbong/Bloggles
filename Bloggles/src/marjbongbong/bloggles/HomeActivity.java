package marjbongbong.bloggles;

import java.util.ArrayList;

import marjbongbong.bloggles.adapters.PostArrayAdapter;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

public class HomeActivity extends ListActivity {

	private ArrayList<Post> posts = new ArrayList<Post>();
	private PostArrayAdapter listAdapter;
	private ListView list;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		getActionBar().setBackgroundDrawable(getResources().getDrawable(R.color.header_background));
		getActionBar().setTitle("Home");
		getActionBar().setIcon(getResources().getDrawable(R.drawable.ic_home));
		
        setContentView(R.layout.activity_home);
        Typeface tf = Typeface.createFromAsset(getAssets(), "LetterGothicStd.otf");
        
		listAdapter = new PostArrayAdapter(HomeActivity.this, R.layout.post_row_layout, posts, tf);
		setListAdapter(listAdapter);
		
		addPosts();
		
		list = (ListView) findViewById(android.R.id.list);
		list.setAdapter(listAdapter);
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		Intent i = new Intent(HomeActivity.this, ViewPostActivity.class);

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
				R.drawable.blah,
				"@blah123",
				"Second Post",
				"Say something about this new outfit I got from the thrift shop around the corner!",
				R.drawable.thrift,
				"1:12"));
		posts.add(new Post(
				R.drawable.fish,
				"@fish",
				"Fishy fishy",
				"Check out the fish-like outfit by clicking this link: http://http://seevanessacraft.com/wp-content/uploads/2013/10/DIY-Baby-Fish-Halloween-Costume.jpg",
				R.drawable.ic_launcher,
				"1:15"));
		posts.add(new Post(
				R.drawable.claire,
				"@claire",
				"Yo Yo Yo",
				"So yesterday, I saw this really vintage-themed outfit. I thought it was so vintage but it really is! Hahahaha. Okay.",
				R.drawable.vintage,
				"1:30"));
		posts.add(new Post(
				R.drawable.marj,
				"@marj",
				"Fours",
				"First Post Ever! Hahahahaha. Kbye.",
				R.drawable.fours,
				"2:05"));
		posts.add(new Post(
				R.drawable.kei,
				"@keila",
				"Fall",
				"Outfits for this fall season",
				R.drawable.fall,
				"2:07"));
		posts.add(new Post(
				R.drawable.van,
				"@van",
				"While skating",
				"Comfy outfit while doing my thing",
				R.drawable.skater,
				"2:15"));
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_home, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId())
		{
			case R.id.action_profile:
				Intent PROFILE = new Intent(HomeActivity.this, ProfileActivity.class);
				PROFILE.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(PROFILE);
				return true;
			case R.id.action_add_post:
				Intent ADD = new Intent(HomeActivity.this, AddPostActivity.class);
				ADD.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(ADD);
				return true;
			case R.id.action_logout:
				Intent OUT = new Intent(HomeActivity.this, LoginActivity.class);
				OUT.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(OUT);
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
}