package steven.example.mytest;

import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import steven.fileSystem.LoadFile;
import steven.fileSystem.LoadFromServer;
import listview.newitem.MyArrayAdapter;
import listview.newitem.MyItem;
import listview.newitem.MyOnItemClickListener;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;


public class MainActivity extends Activity{
	
	ImageView headImage;
	ListView myListView;
	String userName;
	String userDetail;
	MyItem[] myitem;
	private static final String IP = "192.168.137.1";
	private static final int PORT = 8080;
	String uriAPI = "http://" + IP + ":" + PORT + "/userList";
	String JSONString = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		/* set ListView */
		myListView = (ListView) findViewById(R.id.mylistView);
		myListView.setBackgroundColor(Color.argb(255, 153, 255, 102));
		
		headImage = (ImageView) findViewById(R.id.headImage);
		/* read JSON file */
		LoadFromServer lfs = (LoadFromServer) new LoadFromServer().execute(uriAPI);
		try {
			JSONString = lfs.get();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ExecutionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			JSONObject jsonObject = new JSONObject(JSONString);
			JSONArray nameArray = jsonObject.getJSONArray("name");
			JSONArray detailArray = jsonObject.getJSONArray("detail");
			
			int length = nameArray.length();
			myitem = new MyItem[length];
			for(int i = 0; i < length; i++){
				userName = nameArray.getString(i);
				userDetail = detailArray.getString(i);
				myitem[i] = new MyItem(userName, userDetail, headImage);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/* new ArrayAdapter object */
		MyArrayAdapter adapter = 
				new MyArrayAdapter(this, R.layout.my_simple_list_item, myitem);
		/* add ArrayAdapter into ListView */
		myListView.setAdapter(adapter);
		myListView.setOnItemClickListener(new MyOnItemClickListener(this));
	}
	
	public void infoOnClick(View view){
		Intent intent = new Intent();
		intent.setClass(MainActivity.this, InfoActivity.class);
		startActivity(intent);
		
//		setContentView(R.layout.activity_info);
//		MainActivity.this.finish();
	}
	public void logoutOnClick(View view){
		Intent intent = new Intent();
		intent.setClass(MainActivity.this, LoginActivity.class);
		startActivity(intent);
		MainActivity.this.finish();
	}
}
