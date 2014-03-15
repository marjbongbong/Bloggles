package marjbongbong.bloggles.adapters;

import java.util.ArrayList;

import marjbongbong.bloggles.R;
import marjbongbong.bloggles.User;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FollowersArrayAdapter extends ArrayAdapter<User> {
	private final Context context;
	private final ArrayList<User> users;
	private Typeface tf;
	
	TextView username, name;
	ImageView image;

	public FollowersArrayAdapter(Context context, int textViewResourceId, ArrayList<User> users, Typeface tf) {
		super(context, textViewResourceId, users);
		this.context = context;
		this.users = users;
		this.tf = tf;
	}
	

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView = convertView;

		if(rowView == null)
		{
			LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			rowView = inflater.inflate(R.layout.followers_list_single, parent, false);
		}
		
		username = (TextView) rowView.findViewById(R.id.username);
		name = (TextView) rowView.findViewById(R.id.name);
		image = (ImageView) rowView.findViewById(R.id.image);
		
		User u = users.get(position);

		if(username!=null)
		{
			username.setTypeface(tf);
			username.setText(u.getUsername());
		}
		
		if(name!=null)
		{
			name.setTypeface(tf);
			name.setText(u.getName());
		}
		
		if(image!=null)
		{
			image.setImageResource(u.getImage());
		}
		return rowView;
	}
}