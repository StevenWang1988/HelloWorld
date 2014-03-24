package steven.example.mytest;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
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
	
	Bundle bundle;
	String content;
	String userName;
	TextView tv_content;
	TextView tv_userName;
	ImageView iv_headImage;
	Button btn_back;
	Bitmap bitmap_headImage;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_data);
		
		bundle = this.getIntent().getExtras();
		if(bundle == null){
			
		} else {
			content = bundle.getString("content");
			userName = bundle.getString("username");
			bitmap_headImage = bundle.getParcelable("headImage");
		}
		tv_content = (TextView) findViewById(R.id.detail_UserDetail);
		tv_content.setText(content);
		tv_userName = (TextView) findViewById(R.id.detail_UserName);
		tv_userName.setText(userName);
		iv_headImage = (ImageView) findViewById(R.id.headImage);
		iv_headImage.setImageBitmap(bitmap_headImage);
		
		btn_back = (Button) findViewById(R.id.btn_detail_back);
		btn_back.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View v){
				UserData.this.finish();
			}
		});
		
		iv_headImage.setOnClickListener(new ImageView.OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(UserData.this, FullscreenImageActivity.class);
				startActivity(intent);
			}
		});
	}
}
