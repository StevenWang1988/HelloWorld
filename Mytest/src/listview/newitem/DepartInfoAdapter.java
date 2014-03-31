package listview.newitem;

import steven.example.mytest.R;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class DepartInfoAdapter extends ArrayAdapter<InfoItem>{
	private static final String TAG = "DepartInfoAdapter";
	private TextView info1;
	private TextView info2;
	private TextView info3;
	private InfoItem[] infoItem = null;
	Context context;
	int resourceId;
	
	public DepartInfoAdapter(Context context, int resource, InfoItem[] infoItem) {
		super(context, resource, infoItem);

		this.context = context;
		this.resourceId = resource;
		this.infoItem = infoItem;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		Log.v(TAG, "getView position: " + position);
		if(convertView == null){
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			convertView = inflater.inflate(resourceId, parent, false);
		}
		info1 = (TextView) convertView.findViewById(R.id.info1);
		info2 = (TextView) convertView.findViewById(R.id.info2);
		info3 = (TextView) convertView.findViewById(R.id.info3);
		
		info1.setText(infoItem[position].getInfo1());
		info2.setText(infoItem[position].getInfo2());
		info3.setText(infoItem[position].getInfo3());
		
		Log.v(TAG, infoItem[position].getInfo1());
		
		return convertView;
	}
	
	
	
}
