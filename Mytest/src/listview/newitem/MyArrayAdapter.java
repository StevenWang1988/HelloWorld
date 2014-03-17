package listview.newitem;

import steven.example.mytest.R;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyArrayAdapter extends ArrayAdapter<MyItem>{
	
	Context context;
	int resourceId;
	MyItem items[] = null;
	
	public MyArrayAdapter(Context context, int resource, MyItem[] items) {
		super(context, resource, items);
		// TODO Auto-generated constructor stub
		
		this.context = context;
		this.resourceId = resource;
		this.items = items;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		
		if(convertView == null){
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			convertView = inflater.inflate(resourceId, parent, false);
		}
		MyItem myItem = items[position]; 
		
		TextView contentView = (TextView) convertView.findViewById(R.id.content);
		contentView.setText(myItem.getContent());
		
		TextView titleView = (TextView) convertView.findViewById(R.id.title);
		titleView.setText(myItem.getTitle());
		
		ImageView headImage = (ImageView) convertView.findViewById(R.id.headImage);
		headImage = myItem.getHeadImage();
		
		return convertView;
	}

}
