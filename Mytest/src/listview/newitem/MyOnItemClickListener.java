package listview.newitem;

import steven.example.mytest.R;
import steven.example.mytest.UserData;
import layout.change.SetLayout;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class MyOnItemClickListener implements OnItemClickListener {
	
	Activity activity;
	TextView content;
	TextView userName;
	ImageView headImage;
	Bitmap bitmap_headImage;
	SetLayout setLayout = new SetLayout();
	
	public MyOnItemClickListener(Activity activity){
		this.activity = activity;
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub

		Intent intent = new Intent();
		intent.setClass(view.getContext(), UserData.class);
		
		Bundle bundle = new Bundle();
		content = (TextView) view.findViewById(R.id.content);
		userName = (TextView) view.findViewById(R.id.title);
		headImage = (ImageView) view.findViewById(R.id.headImage);
		headImage.buildDrawingCache();
		bitmap_headImage = headImage.getDrawingCache();
		
		bundle.putString("content", (String) content.getText());
		bundle.putString("username", (String) userName.getText());
		bundle.putParcelable("headImage", bitmap_headImage);
		
		intent.putExtras(bundle);

//		setLayout.mySetLayout(view);
		
		view.getContext().startActivity(intent);
//		activity.finish();
	}
	
}
