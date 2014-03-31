package steven.example.mytest;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Build;

public class UserData extends ActionBarActivity {
	private static final String TAG = "UserData";
	Bundle bundle;
	String description;
	String title;
	String startDate;
	String endDate;
	TextView tv_startDate;
	TextView tv_endDate;
	TextView tv_description;
	TextView tv_title;
	ImageView iv_imageURL;
	Button btn_back;
	Bitmap bitmap_headImage;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_data);
		
		bundle = this.getIntent().getExtras();
		if(bundle == null){
			Log.v(TAG, "bundle is null!");
		} else {
			description = bundle.getString("description");
			title = bundle.getString("title");
			startDate = bundle.getString("startDate");
			endDate = bundle.getString("endDate");
			bitmap_headImage = bundle.getParcelable("headImage");
		}
		tv_description = (TextView) findViewById(R.id.detail_description);
		tv_description.setText(description);
		
		tv_title = (TextView) findViewById(R.id.detail_title);
		tv_title.setText(title);
		
		tv_startDate = (TextView) findViewById(R.id.detail_startDate);
		tv_startDate.setText(startDate);
		tv_endDate = (TextView) findViewById(R.id.detail_endDate);
		tv_endDate.setText(endDate);
		iv_imageURL = (ImageView) findViewById(R.id.couponImage);
		iv_imageURL.setImageBitmap(bitmap_headImage);
		Log.v(TAG, "title: " + title);
		Log.v(TAG, "description: " + description);
		
		btn_back = (Button) findViewById(R.id.btn_detail_back);
		btn_back.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View v){
				UserData.this.finish();
			}
		});
		
		iv_imageURL.setOnClickListener(new ImageView.OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(UserData.this, FullscreenImageActivity.class);
				startActivity(intent);
			}
		});
	}
}
