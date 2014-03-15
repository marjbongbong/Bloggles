package marjbongbong.bloggles;

import java.util.ArrayList;

import marjbongbong.bloggles.adapters.FollowersArrayAdapter;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

public class FollowersActivity extends ListActivity {

	private ArrayList<User> users = new ArrayList<User>();
	private FollowersArrayAdapter listAdapter;
	private ListView list;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		getActionBar().setBackgroundDrawable(getResources().getDrawable(R.color.header_background));
		getActionBar().setTitle("Followers");
        setContentView(R.layout.fragment_followers);
        Typeface tf = Typeface.createFromAsset(getAssets(), "LetterGothicStd.otf");
        
		addUsers();		
		
		listAdapter = new FollowersArrayAdapter(FollowersActivity.this, R.layout.followers_list_single, users, tf);
		setListAdapter(listAdapter);
		list = (ListView) findViewById(android.R.id.list);
		list.setAdapter(listAdapter);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		Intent i = new Intent(FollowersActivity.this, UserProfileActivity.class);

		User user = (User) listAdapter.getItem(position);

		i.putExtra("default_photo", user.getImage());
		i.putExtra("username", user.getUsername());
		i.putExtra("name", user.getName());

		startActivity(i);
	}
	
	public void addUsers()
	{
		users.add(new User(
				R.drawable.blah,
				"@blah123",
				"Blah"));
		users.add(new User(
				R.drawable.claire,
				"@claire",
				"Claire"));
		users.add(new User(
				R.drawable.marj,
				"@marj",
				"Marjorie"));
		users.add(new User(
				R.drawable.uiz,
				"@uiz",
				"Louisse"));
		users.add(new User(
				R.drawable.kei,
				"@keila",
				"Michaella"));
		users.add(new User(
				R.drawable.cats,
				"@cats",
				"Grace"));
		users.add(new User(
				R.drawable.van,
				"@van",
				"Harley"));
		users.add(new User(
				R.drawable.pan,
				"@panda",
				"Panda"));
		users.add(new User(
				R.drawable.fish,
				"@fish",
				"Fish"));
		users.add(new User(
				R.drawable.dino,
				"@dinosaur",
				"Dino"));
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_home_out, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId())
		{
			case R.id.action_home:
				Intent HOME = new Intent(FollowersActivity.this, HomeActivity.class);
				HOME.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(HOME);
				return true;			
			case R.id.action_logout:
				Intent OUT = new Intent(FollowersActivity.this, LoginActivity.class);
				OUT.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(OUT);
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
}
