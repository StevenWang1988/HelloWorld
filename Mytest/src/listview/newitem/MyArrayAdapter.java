package listview.newitem;

import steven.example.mytest.R;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import biz.mercue.util.LoadBitmap;

public class MyArrayAdapter extends ArrayAdapter<MyItem>{
	private static final String TAG = "MyArrayAdapter";
	private static int adapterCount = 1;
	
	Context context;
	int resourceId;
	MyItem items[] = null;
	
	private TextView contentView;
	private TextView titleView;
	private ImageView headImage;
	
	public MyArrayAdapter(Context context, int resource, MyItem[] items) {
		super(context, resource, items);
		
		this.context = context;
		this.resourceId = resource;
		this.items = items;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		Log.v(TAG, "getView " + adapterCount);
		if(convertView == null){
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			convertView = inflater.inflate(resourceId, parent, false);
		}
		MyItem myItem = items[position]; 
		
		contentView = (TextView) convertView.findViewById(R.id.content);
		contentView.setText(myItem.getContent());
		
		titleView = (TextView) convertView.findViewById(R.id.title);
		titleView.setText(myItem.getTitle());
		
		headImage = (ImageView) convertView.findViewById(R.id.headImage);
		LoadBitmap loadbitmap = new LoadBitmap();
		loadbitmap.setImageView(headImage);
		loadbitmap.execute(items[position].getHeadImageUrl());
		
		adapterCount++;
		return convertView;
	}

}
