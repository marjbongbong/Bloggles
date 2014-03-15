package marjbongbong.bloggles;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends Activity {
	 
		Button capture_button, gallery_button, signup_button;
		ImageView photo_preview;
		TextView gender_label, photo_label;
		RadioButton male_button, female_button;
		
	    // Activity request codes
	    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
	    public static final int MEDIA_TYPE_IMAGE = 1;
	 
	    private static int RESULT_LOAD_IMAGE = 1;
	    
	    // directory name to store captured images and videos
	    private static final String IMAGE_DIRECTORY_NAME = "Hello Camera";
	 
	    private Uri fileUri; // file url to store image/video
	 
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_register);
	        Typeface tf = Typeface.createFromAsset(getAssets(), "LetterGothicStd.otf");
	        
	        getActionBar().setBackgroundDrawable(getResources().getDrawable(R.color.header_background));
			getActionBar().setTitle(R.string.register);

			photo_preview = (ImageView) findViewById(R.id.photo_preview);
	        gender_label = (TextView)findViewById(R.id.gender_label);
	        photo_label = (TextView)findViewById(R.id.photo_label);
	        male_button = (RadioButton)findViewById(R.id.male_button);
	        female_button = (RadioButton)findViewById(R.id.female_button);
			signup_button = (Button) findViewById(R.id.signup_button);
	        capture_button = (Button) findViewById(R.id.capture_button);
	        gallery_button = (Button) findViewById(R.id.gallery_button);

	        gender_label.setTypeface(tf);
	        photo_label.setTypeface(tf);
	        male_button.setTypeface(tf);
	        female_button.setTypeface(tf);
	        signup_button.setTypeface(tf);
	        capture_button.setTypeface(tf);
	        gallery_button.setTypeface(tf);
	        
	        signup_button.setOnClickListener(new View.OnClickListener() {
	        	 
	            @Override
	            public void onClick(View v) {
	            	Intent PROFILE = new Intent(RegisterActivity.this, ProfileActivity.class);
					PROFILE.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(PROFILE);
	            }
	        });

	        /**
	         * Capture image button click event
	         */
	        capture_button.setOnClickListener(new View.OnClickListener() {
	 
	            @Override
	            public void onClick(View v) {
	                // capture picture
	                captureImage();
	            }
	        });
	        
	        gallery_button.setOnClickListener(new View.OnClickListener() {
	            
	            @Override
	            public void onClick(View arg0) {
	                 
	                Intent i = new Intent(
	                        Intent.ACTION_PICK,
	                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
	                 
	                startActivityForResult(i, RESULT_LOAD_IMAGE);
	            }
	        });

	 
	        // Checking camera availability
	        if (!isDeviceSupportCamera()) {
	            Toast.makeText(getApplicationContext(),
	                    "Sorry! Your device doesn't support camera",
	                    Toast.LENGTH_LONG).show();
	            // will close the app if the device does't have camera
	            finish();
	        }
	    }
	 
	    /**
	     * Checking device has camera hardware or not
	     * */
	    private boolean isDeviceSupportCamera() {
	        if (getApplicationContext().getPackageManager().hasSystemFeature(
	                PackageManager.FEATURE_CAMERA)) {
	            // this device has a camera
	            return true;
	        } else {
	            // no camera on this device
	            return false;
	        }
	    }
	 
	    /**
	     * Capturing Camera Image will lauch camera app requrest image capture
	     */
	    private void captureImage() {
	        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	 
	        fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
	 
	        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
	 
	        // start the image capture Intent
	        startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
	    }
	 
	    /**
	     * Here we store the file url as it will be null after returning from camera
	     * app
	     */
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
	 

	 
	    /**
	     * Receiving activity result method will be called after closing the camera
	     * */
	    @Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	        // if the result is capturing Image
	        if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {
	            if (resultCode == RESULT_OK) {
	                // successfully captured the image
	                // display it in image view
	                previewCapturedImage();
	            } else if (resultCode == RESULT_CANCELED) {
	                // user cancelled Image capture
	                Toast.makeText(getApplicationContext(),
	                        "User cancelled image capture", Toast.LENGTH_SHORT)
	                        .show();
	            } else {
	                // failed to capture image
	                Toast.makeText(getApplicationContext(),
	                        "Sorry! Failed to capture image", Toast.LENGTH_SHORT)
	                        .show();
	            }
	        }else if(requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
	        	photo_preview.setVisibility(View.VISIBLE);
	         	Uri selectedImage = data.getData();
	            String[] filePathColumn = { MediaStore.Images.Media.DATA };

	            Cursor cursor = getContentResolver().query(selectedImage,
	                    filePathColumn, null, null, null);
	            cursor.moveToFirst();

	            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
	            String picturePath = cursor.getString(columnIndex);
	            cursor.close();
	             
	            ImageView imageView = (ImageView) findViewById(R.id.photo_preview);
	            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
	        }
	    }
	 
	    /**
	     * Display image from a path to ImageView
	     */
	    private void previewCapturedImage() {
	        try {
	        	photo_preview.setVisibility(View.VISIBLE);
	 
	            // bimatp factory
	            BitmapFactory.Options options = new BitmapFactory.Options();
	 
	            // downsizing image as it throws OutOfMemory Exception for larger
	            // images
	            options.inSampleSize = 8;
	 
	            final Bitmap bitmap = BitmapFactory.decodeFile(fileUri.getPath(),
	                    options);
	 
	            photo_preview.setImageBitmap(bitmap);
	        } catch (NullPointerException e) {
	            e.printStackTrace();
	        }
	    }

	 

	     
	    /**
	     * ------------ Helper Methods ---------------------- 
	     * */
	 
	    /**
	     * Creating file uri to store image/video
	     */
	    public Uri getOutputMediaFileUri(int type) {
	        return Uri.fromFile(getOutputMediaFile(type));
	    }
	 
	    /**
	     * returning image / video
	     */
	    private static File getOutputMediaFile(int type) {
	 
	        // External sdcard location
	        File mediaStorageDir = new File(
	                Environment
	                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
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
	    
	    @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	    	getMenuInflater().inflate(R.menu.menu_register, menu);
	    	return true;
	    }
	    
	    @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	    	switch(item.getItemId()){
	    		case R.id.action_cancel:
	    			Intent LOGIN = new Intent(RegisterActivity.this, LoginActivity.class);
					LOGIN.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(LOGIN);
					return true;
				default:
					return super.onOptionsItemSelected(item);
	    	}
	    }
}
