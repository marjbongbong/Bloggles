package marjbongbong.bloggles;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AccountSettingsActivity extends Activity {
	Button change_photo_button, change_password_button, save_password_button, cancel_button;
	ImageView profile_photo;
	EditText current_password, new_password, retype_new_password;
	TextView username_label, name_label, first_name_label, last_name_label, password_label;
	
    public static final int MEDIA_TYPE_IMAGE = 1;
    private static int RESULT_LOAD_IMAGE = 1;
    private static final String IMAGE_DIRECTORY_NAME = "Hello Camera";
    private Uri fileUri; // file url to store image/video
 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		getActionBar().setBackgroundDrawable(getResources().getDrawable(R.color.header_background));
		getActionBar().setIcon(getResources().getDrawable(R.drawable.ic_account_settings));
		
		setContentView(R.layout.activity_account_settings);
		Typeface tf = Typeface.createFromAsset(getAssets(), "LetterGothicStd.otf");
		
		profile_photo = (ImageView)findViewById(R.id.profile_photo);
		
		change_photo_button = (Button)findViewById(R.id.change_photo_button);
		change_password_button = (Button)findViewById(R.id.change_password_button);
		save_password_button = (Button)findViewById(R.id.save_password_button);
		cancel_button = (Button)findViewById(R.id.cancel_button);
		
		current_password = (EditText)findViewById(R.id.current_password);
		new_password = (EditText)findViewById(R.id.new_password);
		retype_new_password = (EditText)findViewById(R.id.retype_new_password);

		username_label = (TextView)findViewById(R.id.username_label);
		name_label = (TextView)findViewById(R.id.name_label);
		first_name_label = (TextView)findViewById(R.id.first_name_label);
		last_name_label = (TextView)findViewById(R.id.last_name_label);
		password_label = (TextView)findViewById(R.id.password_label);
		
		current_password.setTypeface(tf);
		new_password.setTypeface(tf);
		retype_new_password.setTypeface(tf);
		username_label.setTypeface(tf);
		name_label.setTypeface(tf);
		first_name_label.setTypeface(tf);
		last_name_label.setTypeface(tf);
		password_label.setTypeface(tf);
		change_photo_button.setTypeface(tf);
		change_password_button.setTypeface(tf);
		save_password_button.setTypeface(tf);
		cancel_button.setTypeface(tf);
		
		
		change_photo_button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI); 
                startActivityForResult(i, RESULT_LOAD_IMAGE);
			}
		});	
		
		change_password_button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				change_password_button.setVisibility(View.GONE);
				current_password.setVisibility(View.VISIBLE);
				new_password.setVisibility(View.VISIBLE);
				retype_new_password.setVisibility(View.VISIBLE);
				save_password_button.setVisibility(View.VISIBLE);
				cancel_button.setVisibility(View.VISIBLE);
			}
		});	
		
		save_password_button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				change_password_button.setVisibility(View.VISIBLE);
				current_password.setVisibility(View.GONE);
				new_password.setVisibility(View.GONE);
				retype_new_password.setVisibility(View.GONE);
				save_password_button.setVisibility(View.GONE);
				cancel_button.setVisibility(View.GONE);
				
				saveButtonClicked();
			}
		});	
		
		cancel_button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				change_password_button.setVisibility(View.VISIBLE);
				current_password.setVisibility(View.GONE);
				new_password.setVisibility(View.GONE);
				retype_new_password.setVisibility(View.GONE);
				save_password_button.setVisibility(View.GONE);
				cancel_button.setVisibility(View.GONE);
				
				cancelButtonClicked();
			}
		});	
	}
	
	 @Override
	    protected void onSaveInstanceState(Bundle outState) {
	        super.onSaveInstanceState(outState);
	 
	        // save file url in bundle as it will be null on scren orientation
	        // changes
	        outState.putParcelable("file_uri", fileUri);
	    }
	 
	    @Override
	    protected void onRestoreInstanceState(Bundle savedInstanceState) {
	        super.onRestoreInstanceState(savedInstanceState);
	 
	        // get the file url
	        fileUri = savedInstanceState.getParcelable("file_uri");
	    }
	    
	    @Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	        // if the result is capturing Image
	        if(requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
	        	profile_photo.setVisibility(View.VISIBLE);
	         	Uri selectedImage = data.getData();
	            String[] filePathColumn = { MediaStore.Images.Media.DATA };

	            Cursor cursor = getContentResolver().query(selectedImage,
	                    filePathColumn, null, null, null);
	            cursor.moveToFirst();

	            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
	            String picturePath = cursor.getString(columnIndex);
	            cursor.close();
	             
	            ImageView imageView = (ImageView) findViewById(R.id.profile_photo);
	            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
	        }
	    }
	    
	    public Uri getOutputMediaFileUri(int type) {
	        return Uri.fromFile(getOutputMediaFile(type));
	    }
	    
	    private static File getOutputMediaFile(int type) {
	   	 
	        // External sdcard location
	        File mediaStorageDir = new File(
	                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
	                IMAGE_DIRECTORY_NAME);
	 
	        // Create the storage directory if it does not exist
	        if (!mediaStorageDir.exists()) {
	            if (!mediaStorageDir.mkdirs()) {
	                Log.d(IMAGE_DIRECTORY_NAME, "Oops! Failed create "
	                        + IMAGE_DIRECTORY_NAME + " directory");
	                return null;
	            }
	        }
	 
	        // Create a media file name
	        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
	                Locale.getDefault()).format(new Date());
	        File mediaFile;
	        if (type == MEDIA_TYPE_IMAGE) {
	            mediaFile = new File(mediaStorageDir.getPath() + File.separator
	                    + "IMG_" + timeStamp + ".jpg");
	      
	        } else {
	            return null;
	        }
	 
	        return mediaFile;
	    }
	    
	public void saveButtonClicked(){
		Toast.makeText(this, "Password Saved.", Toast.LENGTH_SHORT).show();
	}
	
	public void cancelButtonClicked(){
		Toast.makeText(this, "Password change cancelled.", Toast.LENGTH_SHORT).show();
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
				Toast.makeText(AccountSettingsActivity.this, "Settings Saved.", Toast.LENGTH_SHORT).show();
				Intent SAVE = new Intent(AccountSettingsActivity.this, ProfileActivity.class);
				SAVE.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(SAVE);
				return true;
			case R.id.action_cancel:
				Toast.makeText(AccountSettingsActivity.this, "Action cancelled", Toast.LENGTH_SHORT).show();
				Intent CANCEL = new Intent(AccountSettingsActivity.this, ProfileActivity.class);
				CANCEL.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(CANCEL);
				return true;
			case R.id.action_logout:
				Toast.makeText(AccountSettingsActivity.this, "Logged out.", Toast.LENGTH_SHORT).show();
				Intent OUT = new Intent(AccountSettingsActivity.this, LoginActivity.class);
				OUT.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(OUT);
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
}
