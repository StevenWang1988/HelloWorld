package listview.newitem;

import steven.example.mytest.R;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
	private TextView startDateView;
	private TextView endDateView;
	private ImageView headImage;
	private Animation myFadeInAnimation;
	
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

		titleView = (TextView) convertView.findViewById(R.id.title);
		contentView = (TextView) convertView.findViewById(R.id.description);
		startDateView = (TextView) convertView.findViewById(R.id.startDate);
		endDateView = (TextView) convertView.findViewById(R.id.endDate);
		
		titleView.setText(items[position].getTitle());
		contentView.setText(items[position].getDescription());
		startDateView.setText(items[position].getStartDate());
		endDateView.setText(items[position].getEndDate());
		
		headImage = (ImageView) convertView.findViewById(R.id.couponImage);
		LoadBitmap.loadBitmap(position, headImage, this.items);
		
		myFadeInAnimation = AnimationUtils.loadAnimation(headImage.getContext(), R.anim.fadein);
		headImage.startAnimation(myFadeInAnimation);

		adapterCount++;
		return convertView;
	}

}


