package marjbongbong.bloggles.adapters;

import java.util.ArrayList;

import marjbongbong.bloggles.Post;
import marjbongbong.bloggles.R;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyBlogArrayAdapter extends ArrayAdapter<Post> {
	private final Context context;
	private final ArrayList<Post> posts;
	private Typeface tf;
	
	TextView username, time_posted, post_title, post_content;
	ImageView default_photo, post_photo;

	public MyBlogArrayAdapter(Context context, int textViewResourceId, ArrayList<Post> posts, Typeface tf) {
		super(context, textViewResourceId, posts);
		this.context = context;
		this.posts = posts;
		this.tf = tf;
	}
	

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.post_row_layout, parent, false);
		
		username = (TextView) rowView.findViewById(R.id.username);
		time_posted = (TextView) rowView.findViewById(R.id.time_posted);
		post_title = (TextView) rowView.findViewById(R.id.post_title);
		post_content = (TextView) rowView.findViewById(R.id.post_content);
		post_photo = (ImageView) rowView.findViewById(R.id.default_photo);
		
		Post p = posts.get(position);

		username.setTypeface(tf);
		time_posted.setTypeface(tf);
		post_title.setTypeface(tf);
		post_content.setTypeface(tf);
		
		username.setText(p.getUsername());
		time_posted.setText(p.getTimePosted());
		post_title.setText(p.getPostTitle());
		post_content.setText(p.getPostContent());
		post_photo.setImageResource(p.getPostPhoto());
		
		return rowView;
	}
}
