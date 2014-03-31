package listview.newitem;

import java.util.List;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.GroundOverlay;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class InfoItemOnClickListener implements OnItemClickListener {
	private static final String TAG = "InfoItemOnClickListener";
	private GroundOverlay mGroundOverlay;
	private final List<BitmapDescriptor> mImages;
	
	public InfoItemOnClickListener(GroundOverlay mGroundOverlay, List<BitmapDescriptor> mImages){
		this.mGroundOverlay = mGroundOverlay;
		this.mImages = mImages;
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		Log.v(TAG, "position: " + position + " image size: " + mImages.size());
		mGroundOverlay.setImage(mImages.get(position));
	}

}
