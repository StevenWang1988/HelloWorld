package steven.example.mytest;

import java.util.ArrayList;
import java.util.List;

import listview.newitem.DepartInfoAdapter;
import listview.newitem.InfoItem;
import listview.newitem.InfoItemOnClickListener;
import listview.newitem.MyItem;
import listview.newitem.MyOnItemClickListener;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.GoogleMap.OnMyLocationButtonClickListener;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;

import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;
import android.widget.Toast;
import android.location.Location;
import android.os.Bundle;


public class MapActivity extends ActionBarActivity implements
			ConnectionCallbacks,
			OnConnectionFailedListener,
			LocationListener,
	        OnMyLocationButtonClickListener{
	private static final String TAG = "MapActivity";
    private GoogleMap mMap;
    private LocationClient mLocationClient;
    private CheckBox trafficOnOff;
    private ListView infoList;
    private InfoItem[] infoItem = new InfoItem[10];
    private static final LatLng MYPOSITION = new LatLng(25.026762, 121.476641);
    private final List<BitmapDescriptor> mImages = new ArrayList<BitmapDescriptor>();
    private GroundOverlay mGroundOverlay;
    private int mCurrentEntry = 0;
    
    // These settings are the same as the settings for the map. They will in fact give you updates
    // at the maximal rates currently possible.
    private static final LocationRequest REQUEST = LocationRequest.create()
            .setInterval(5000)         // 5 seconds
            .setFastestInterval(16)    // 16ms = 60fps
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		
		trafficOnOff = (CheckBox) findViewById(R.id.trafficOnOff);
		trafficOnOff.setOnCheckedChangeListener(new OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				mMap.setTrafficEnabled(isChecked);
			}
			
		});
		
		for(int i = 0; i < 10; i++){
			infoItem[i] = new InfoItem();
			infoItem[i].setInfo1("test info_1 " + (i + 1));
			infoItem[i].setInfo2("test info_2 " + (i + 1));
			infoItem[i].setInfo3("test info_3 " + (i + 1));
		}
		
		infoList = (ListView) findViewById(R.id.info_listView);
		DepartInfoAdapter infoAdapter = new DepartInfoAdapter(this, R.layout.simple_info_list_item, infoItem);
		infoList.setAdapter(infoAdapter);
		
	}
	@Override
    protected void onResume() {
        super.onResume();
        initMap();
    }
	
	@Override
    public void onPause() {
        super.onPause();
        if (mLocationClient != null) {
            mLocationClient.disconnect();
        }
    }

	@Override
	public void onLocationChanged(Location arg0) {
		// Do nothing
	}

	@Override
	public void onConnectionFailed(ConnectionResult arg0) {
		// Do nothing
	}

	@Override
	public void onConnected(Bundle arg0) {
		mLocationClient.requestLocationUpdates(
                REQUEST,
                this);  // LocationListener
	}

	@Override
	public void onDisconnected() {
		// Do nothing
	}
	
	private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                mMap.setMyLocationEnabled(true);
                mMap.setOnMyLocationButtonClickListener(this);
                setUpMap();
            }
        }
    }
	
	private void setUpMap() {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(MYPOSITION, 15));

        mImages.clear();
        mImages.add(BitmapDescriptorFactory.fromResource(R.drawable.image1));
        mImages.add(BitmapDescriptorFactory.fromResource(R.drawable.image2));
        mImages.add(BitmapDescriptorFactory.fromResource(R.drawable.image3));
        mImages.add(BitmapDescriptorFactory.fromResource(R.drawable.image4));
        mImages.add(BitmapDescriptorFactory.fromResource(R.drawable.image5));
        mImages.add(BitmapDescriptorFactory.fromResource(R.drawable.image6));
        mImages.add(BitmapDescriptorFactory.fromResource(R.drawable.image7));
        mImages.add(BitmapDescriptorFactory.fromResource(R.drawable.image8));
        mImages.add(BitmapDescriptorFactory.fromResource(R.drawable.image9));
        mImages.add(BitmapDescriptorFactory.fromResource(R.drawable.image10));

        mCurrentEntry = 0;
        mGroundOverlay = mMap.addGroundOverlay(new GroundOverlayOptions()
                .image(mImages.get(mCurrentEntry)).anchor(0, 1)
                .position(MYPOSITION, 600f, 500f));
        Log.v(TAG, "image size: " + mImages.size());
        infoList.setOnItemClickListener(new InfoItemOnClickListener(mGroundOverlay, mImages));
    }

    private void setUpLocationClientIfNeeded() {
        if (mLocationClient == null) {
            mLocationClient = new LocationClient(
                    getApplicationContext(),
                    this,  // ConnectionCallbacks
                    this); // OnConnectionFailedListener
        }
    }
    
    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT).show();
        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return false;
    }
    
    private void initMap(){
    	setUpMapIfNeeded();
        setUpLocationClientIfNeeded();
        mLocationClient.connect();
    }

}
