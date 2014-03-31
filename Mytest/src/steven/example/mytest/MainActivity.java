package steven.example.mytest;

import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
import android.widget.ListView;
import steven.login.ServerSetting;


public class MainActivity extends Activity{
	private static final String TAG = "MainActivity";
//	String headImageUrl;
	ListView myListView;
	String title;
	String description;
	String startDate;
	String endDate;
	String imageURL;
	String userName;
	MyItem[] myitem;
	Bundle bundle = null;
	
	String uriAPI = ServerSetting.ServerLocation + ServerSetting.UserList;
	String JSONString = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		/* set ListView */
		myListView = (ListView) findViewById(R.id.mylistView);
		myListView.setBackgroundColor(Color.argb(255, 153, 255, 102));
		
		bundle = this.getIntent().getExtras();
		if(bundle == null){
			Log.v(TAG, "bundle is null!");
		} else {
			userName = bundle.getString("userName");
		}
		uriAPI += "userName=" + userName;
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
			if(JSONString != null){
				JSONObject jsonObject = new JSONObject(JSONString);
				JSONArray titleArray = jsonObject.getJSONArray("title");
				JSONArray descriptionArray = jsonObject.getJSONArray("description");
				JSONArray startDateArray = jsonObject.getJSONArray("startDate");
				JSONArray endDateArray = jsonObject.getJSONArray("endDate");
				JSONArray imageURLArray = jsonObject.getJSONArray("imageURL");
				
				int length = titleArray.length();
				myitem = new MyItem[length];
				
				for(int i = 0; i < length; i++){
					title = titleArray.getString(i);
					description = descriptionArray.getString(i);
					startDate = startDateArray.getString(i);
					endDate = endDateArray.getString(i);
					imageURL = imageURLArray.getString(i);
					
					myitem[i] = new MyItem();
					myitem[i].setTitle(title);
					myitem[i].setDescription(description);
					myitem[i].setStartDate(startDate);
					myitem[i].setEndDate(endDate);
					myitem[i].setImageURL(imageURL);
				}
			} else {
				myitem = new MyItem[0];
			}
		} catch (JSONException e) {
			Log.v(TAG, "JSONException: " + e.getMessage());
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
	}
	public void logoutOnClick(View view){
		Intent intent = new Intent();
		intent.setClass(MainActivity.this, LoginActivity.class);
		startActivity(intent);
		MainActivity.this.finish();
	}
	public void newSpaceOnClick(){
		
	}
	public void openMapOnClick(View view){
		Intent intent = new Intent();
		intent.setClass(MainActivity.this, MapActivity.class);
		startActivity(intent);
//		MainActivity.this.finish();
	}
	
//	public final static String[] imageThumbUrls = new String[] {
//        "https://lh6.googleusercontent.com/-55osAWw3x0Q/URquUtcFr5I/AAAAAAAAAbs/rWlj1RUKrYI/s240-c/A%252520Photographer.jpg",
//        "https://lh4.googleusercontent.com/--dq8niRp7W4/URquVgmXvgI/AAAAAAAAAbs/-gnuLQfNnBA/s240-c/A%252520Song%252520of%252520Ice%252520and%252520Fire.jpg",
//        "https://lh5.googleusercontent.com/-7qZeDtRKFKc/URquWZT1gOI/AAAAAAAAAbs/hqWgteyNXsg/s240-c/Another%252520Rockaway%252520Sunset.jpg",
//        "https://lh3.googleusercontent.com/--L0Km39l5J8/URquXHGcdNI/AAAAAAAAAbs/3ZrSJNrSomQ/s240-c/Antelope%252520Butte.jpg",
//        "https://lh6.googleusercontent.com/-8HO-4vIFnlw/URquZnsFgtI/AAAAAAAAAbs/WT8jViTF7vw/s240-c/Antelope%252520Hallway.jpg",
//        "https://lh4.googleusercontent.com/-WIuWgVcU3Qw/URqubRVcj4I/AAAAAAAAAbs/YvbwgGjwdIQ/s240-c/Antelope%252520Walls.jpg",
//        "https://lh6.googleusercontent.com/-UBmLbPELvoQ/URqucCdv0kI/AAAAAAAAAbs/IdNhr2VQoQs/s240-c/Apre%2525CC%252580s%252520la%252520Pluie.jpg",
//        "https://lh3.googleusercontent.com/-s-AFpvgSeew/URquc6dF-JI/AAAAAAAAAbs/Mt3xNGRUd68/s240-c/Backlit%252520Cloud.jpg",
//        "https://lh5.googleusercontent.com/-bvmif9a9YOQ/URquea3heHI/AAAAAAAAAbs/rcr6wyeQtAo/s240-c/Bee%252520and%252520Flower.jpg",
//        "https://lh5.googleusercontent.com/-n7mdm7I7FGs/URqueT_BT-I/AAAAAAAAAbs/9MYmXlmpSAo/s240-c/Bonzai%252520Rock%252520Sunset.jpg",
//        "https://lh6.googleusercontent.com/-4CN4X4t0M1k/URqufPozWzI/AAAAAAAAAbs/8wK41lg1KPs/s240-c/Caterpillar.jpg",
//        "https://lh3.googleusercontent.com/-rrFnVC8xQEg/URqufdrLBaI/AAAAAAAAAbs/s69WYy_fl1E/s240-c/Chess.jpg",
//        "https://lh5.googleusercontent.com/-WVpRptWH8Yw/URqugh-QmDI/AAAAAAAAAbs/E-MgBgtlUWU/s240-c/Chihuly.jpg",
//        "https://lh5.googleusercontent.com/-0BDXkYmckbo/URquhKFW84I/AAAAAAAAAbs/ogQtHCTk2JQ/s240-c/Closed%252520Door.jpg",
//        "https://lh3.googleusercontent.com/-PyggXXZRykM/URquh-kVvoI/AAAAAAAAAbs/hFtDwhtrHHQ/s240-c/Colorado%252520River%252520Sunset.jpg",
//        "https://lh3.googleusercontent.com/-ZAs4dNZtALc/URquikvOCWI/AAAAAAAAAbs/DXz4h3dll1Y/s240-c/Colors%252520of%252520Autumn.jpg",
//        "https://lh4.googleusercontent.com/-GztnWEIiMz8/URqukVCU7bI/AAAAAAAAAbs/jo2Hjv6MZ6M/s240-c/Countryside.jpg",
//        "https://lh4.googleusercontent.com/-bEg9EZ9QoiM/URquklz3FGI/AAAAAAAAAbs/UUuv8Ac2BaE/s240-c/Death%252520Valley%252520-%252520Dunes.jpg",
//        "https://lh6.googleusercontent.com/-ijQJ8W68tEE/URqulGkvFEI/AAAAAAAAAbs/zPXvIwi_rFw/s240-c/Delicate%252520Arch.jpg",
//        "https://lh5.googleusercontent.com/-Oh8mMy2ieng/URqullDwehI/AAAAAAAAAbs/TbdeEfsaIZY/s240-c/Despair.jpg",
//        "https://lh5.googleusercontent.com/-gl0y4UiAOlk/URqumC_KjBI/AAAAAAAAAbs/PM1eT7dn4oo/s240-c/Eagle%252520Fall%252520Sunrise.jpg",
//        "https://lh3.googleusercontent.com/-hYYHd2_vXPQ/URqumtJa9eI/AAAAAAAAAbs/wAalXVkbSh0/s240-c/Electric%252520Storm.jpg",
//        "https://lh5.googleusercontent.com/-PyY_yiyjPTo/URqunUOhHFI/AAAAAAAAAbs/azZoULNuJXc/s240-c/False%252520Kiva.jpg",
//        "https://lh6.googleusercontent.com/-PYvLVdvXywk/URqunwd8hfI/AAAAAAAAAbs/qiMwgkFvf6I/s240-c/Fitzgerald%252520Streaks.jpg",
//        "https://lh4.googleusercontent.com/-KIR_UobIIqY/URquoCZ9SlI/AAAAAAAAAbs/Y4d4q8sXu4c/s240-c/Foggy%252520Sunset.jpg",
//        "https://lh6.googleusercontent.com/-9lzOk_OWZH0/URquoo4xYoI/AAAAAAAAAbs/AwgzHtNVCwU/s240-c/Frantic.jpg",
//        "https://lh3.googleusercontent.com/-0X3JNaKaz48/URqupH78wpI/AAAAAAAAAbs/lHXxu_zbH8s/s240-c/Golden%252520Gate%252520Afternoon.jpg",
//        "https://lh6.googleusercontent.com/-95sb5ag7ABc/URqupl95RDI/AAAAAAAAAbs/g73R20iVTRA/s240-c/Golden%252520Gate%252520Fog.jpg",
//        "https://lh3.googleusercontent.com/-JB9v6rtgHhk/URqup21F-zI/AAAAAAAAAbs/64Fb8qMZWXk/s240-c/Golden%252520Grass.jpg",
//        "https://lh4.googleusercontent.com/-EIBGfnuLtII/URquqVHwaRI/AAAAAAAAAbs/FA4McV2u8VE/s240-c/Grand%252520Teton.jpg",
//        "https://lh4.googleusercontent.com/-WoMxZvmN9nY/URquq1v2AoI/AAAAAAAAAbs/grj5uMhL6NA/s240-c/Grass%252520Closeup.jpg",
//        "https://lh3.googleusercontent.com/-6hZiEHXx64Q/URqurxvNdqI/AAAAAAAAAbs/kWMXM3o5OVI/s240-c/Green%252520Grass.jpg",
//        "https://lh5.googleusercontent.com/-6LVb9OXtQ60/URquteBFuKI/AAAAAAAAAbs/4F4kRgecwFs/s240-c/Hanging%252520Leaf.jpg",
//        "https://lh4.googleusercontent.com/-zAvf__52ONk/URqutT_IuxI/AAAAAAAAAbs/D_bcuc0thoU/s240-c/Highway%2525201.jpg",
//        "https://lh6.googleusercontent.com/-H4SrUg615rA/URquuL27fXI/AAAAAAAAAbs/4aEqJfiMsOU/s240-c/Horseshoe%252520Bend%252520Sunset.jpg",
//        "https://lh4.googleusercontent.com/-JhFi4fb_Pqw/URquuX-QXbI/AAAAAAAAAbs/IXpYUxuweYM/s240-c/Horseshoe%252520Bend.jpg",
//        "https://lh5.googleusercontent.com/-UGgssvFRJ7g/URquueyJzGI/AAAAAAAAAbs/yYIBlLT0toM/s240-c/Into%252520the%252520Blue.jpg",
//        "https://lh3.googleusercontent.com/-CH7KoupI7uI/URquu0FF__I/AAAAAAAAAbs/R7GDmI7v_G0/s240-c/Jelly%252520Fish%2525202.jpg",
//        "https://lh4.googleusercontent.com/-pwuuw6yhg8U/URquvPxR3FI/AAAAAAAAAbs/VNGk6f-tsGE/s240-c/Jelly%252520Fish%2525203.jpg",
//        "https://lh5.googleusercontent.com/-GoUQVw1fnFw/URquv6xbC0I/AAAAAAAAAbs/zEUVTQQ43Zc/s240-c/Kauai.jpg",
//        "https://lh6.googleusercontent.com/-8QdYYQEpYjw/URquwvdh88I/AAAAAAAAAbs/cktDy-ysfHo/s240-c/Kyoto%252520Sunset.jpg",
//        "https://lh4.googleusercontent.com/-vPeekyDjOE0/URquwzJ28qI/AAAAAAAAAbs/qxcyXULsZrg/s240-c/Lake%252520Tahoe%252520Colors.jpg",
//        "https://lh4.googleusercontent.com/-xBPxWpD4yxU/URquxWHk8AI/AAAAAAAAAbs/ARDPeDYPiMY/s240-c/Lava%252520from%252520the%252520Sky.jpg",
//        "https://lh3.googleusercontent.com/-897VXrJB6RE/URquxxxd-5I/AAAAAAAAAbs/j-Cz4T4YvIw/s240-c/Leica%25252050mm%252520Summilux.jpg",
//        "https://lh5.googleusercontent.com/-qSJ4D4iXzGo/URquyDWiJ1I/AAAAAAAAAbs/k2pBXeWehOA/s240-c/Leica%25252050mm%252520Summilux.jpg",
//        "https://lh6.googleusercontent.com/-dwlPg83vzLg/URquylTVuFI/AAAAAAAAAbs/G6SyQ8b4YsI/s240-c/Leica%252520M8%252520%252528Front%252529.jpg",
//        "https://lh3.googleusercontent.com/-R3_EYAyJvfk/URquzQBv8eI/AAAAAAAAAbs/b9xhpUM3pEI/s240-c/Light%252520to%252520Sand.jpg",
//        "https://lh3.googleusercontent.com/-fHY5h67QPi0/URqu0Cp4J1I/AAAAAAAAAbs/0lG6m94Z6vM/s240-c/Little%252520Bit%252520of%252520Paradise.jpg",
//        "https://lh5.googleusercontent.com/-TzF_LwrCnRM/URqu0RddPOI/AAAAAAAAAbs/gaj2dLiuX0s/s240-c/Lone%252520Pine%252520Sunset.jpg",
//        "https://lh3.googleusercontent.com/-4HdpJ4_DXU4/URqu046dJ9I/AAAAAAAAAbs/eBOodtk2_uk/s240-c/Lonely%252520Rock.jpg",
//        "https://lh6.googleusercontent.com/-erbF--z-W4s/URqu1ajSLkI/AAAAAAAAAbs/xjDCDO1INzM/s240-c/Longue%252520Vue.jpg",
//        "https://lh6.googleusercontent.com/-0CXJRdJaqvc/URqu1opNZNI/AAAAAAAAAbs/PFB2oPUU7Lk/s240-c/Look%252520Me%252520in%252520the%252520Eye.jpg",
//        "https://lh3.googleusercontent.com/-D_5lNxnDN6g/URqu2Tk7HVI/AAAAAAAAAbs/p0ddca9W__Y/s240-c/Lost%252520in%252520a%252520Field.jpg",
//        "https://lh6.googleusercontent.com/-flsqwMrIk2Q/URqu24PcmjI/AAAAAAAAAbs/5ocIH85XofM/s240-c/Marshall%252520Beach%252520Sunset.jpg",
//        "https://lh4.googleusercontent.com/-Y4lgryEVTmU/URqu28kG3gI/AAAAAAAAAbs/OjXpekqtbJ4/s240-c/Mono%252520Lake%252520Blue.jpg",
//        "https://lh4.googleusercontent.com/-AaHAJPmcGYA/URqu3PIldHI/AAAAAAAAAbs/lcTqk1SIcRs/s240-c/Monument%252520Valley%252520Overlook.jpg",
//        "https://lh4.googleusercontent.com/-vKxfdQ83dQA/URqu31Yq_BI/AAAAAAAAAbs/OUoGk_2AyfM/s240-c/Moving%252520Rock.jpg",
//        "https://lh5.googleusercontent.com/-CG62QiPpWXg/URqu4ia4vRI/AAAAAAAAAbs/0YOdqLAlcAc/s240-c/Napali%252520Coast.jpg",
//        "https://lh6.googleusercontent.com/-wdGrP5PMmJQ/URqu5PZvn7I/AAAAAAAAAbs/m0abEcdPXe4/s240-c/One%252520Wheel.jpg",
//        "https://lh6.googleusercontent.com/-6WS5DoCGuOA/URqu5qx1UgI/AAAAAAAAAbs/giMw2ixPvrY/s240-c/Open%252520Sky.jpg",
//        "https://lh6.googleusercontent.com/-u8EHKj8G8GQ/URqu55sM6yI/AAAAAAAAAbs/lIXX_GlTdmI/s240-c/Orange%252520Sunset.jpg",
//        "https://lh6.googleusercontent.com/-74Z5qj4bTDE/URqu6LSrJrI/AAAAAAAAAbs/XzmVkw90szQ/s240-c/Orchid.jpg",
//        "https://lh6.googleusercontent.com/-lEQE4h6TePE/URqu6t_lSkI/AAAAAAAAAbs/zvGYKOea_qY/s240-c/Over%252520there.jpg",
//        "https://lh5.googleusercontent.com/-cauH-53JH2M/URqu66v_USI/AAAAAAAAAbs/EucwwqclfKQ/s240-c/Plumes.jpg",
//        "https://lh3.googleusercontent.com/-eDLT2jHDoy4/URqu7axzkAI/AAAAAAAAAbs/iVZE-xJ7lZs/s240-c/Rainbokeh.jpg",
//        "https://lh5.googleusercontent.com/-j1NLqEFIyco/URqu8L1CGcI/AAAAAAAAAbs/aqZkgX66zlI/s240-c/Rainbow.jpg",
//        "https://lh5.googleusercontent.com/-DRnqmK0t4VU/URqu8XYN9yI/AAAAAAAAAbs/LgvF_592WLU/s240-c/Rice%252520Fields.jpg",
//        "https://lh3.googleusercontent.com/-hwh1v3EOGcQ/URqu8qOaKwI/AAAAAAAAAbs/IljRJRnbJGw/s240-c/Rockaway%252520Fire%252520Sky.jpg",
//        "https://lh5.googleusercontent.com/-wjV6FQk7tlk/URqu9jCQ8sI/AAAAAAAAAbs/RyYUpdo-c9o/s240-c/Rockaway%252520Flow.jpg",
//        "https://lh6.googleusercontent.com/-6cAXNfo7D20/URqu-BdzgPI/AAAAAAAAAbs/OmsYllzJqwo/s240-c/Rockaway%252520Sunset%252520Sky.jpg",
//        "https://lh3.googleusercontent.com/-sl8fpGPS-RE/URqu_BOkfgI/AAAAAAAAAbs/Dg2Fv-JxOeg/s240-c/Russian%252520Ridge%252520Sunset.jpg",
//        "https://lh6.googleusercontent.com/-gVtY36mMBIg/URqu_q91lkI/AAAAAAAAAbs/3CiFMBcy5MA/s240-c/Rust%252520Knot.jpg",
//        "https://lh6.googleusercontent.com/-GHeImuHqJBE/URqu_FKfVLI/AAAAAAAAAbs/axuEJeqam7Q/s240-c/Sailing%252520Stones.jpg",
//        "https://lh3.googleusercontent.com/-hBbYZjTOwGc/URqu_ycpIrI/AAAAAAAAAbs/nAdJUXnGJYE/s240-c/Seahorse.jpg",
//        "https://lh3.googleusercontent.com/-Iwi6-i6IexY/URqvAYZHsVI/AAAAAAAAAbs/5ETWl4qXsFE/s240-c/Shinjuku%252520Street.jpg",
//        "https://lh6.googleusercontent.com/-amhnySTM_MY/URqvAlb5KoI/AAAAAAAAAbs/pFCFgzlKsn0/s240-c/Sierra%252520Heavens.jpg",
//        "https://lh5.googleusercontent.com/-dJgjepFrYSo/URqvBVJZrAI/AAAAAAAAAbs/v-F5QWpYO6s/s240-c/Sierra%252520Sunset.jpg",
//        "https://lh4.googleusercontent.com/-Z4zGiC5nWdc/URqvBdEwivI/AAAAAAAAAbs/ZRZR1VJ84QA/s240-c/Sin%252520Lights.jpg",
//        "https://lh4.googleusercontent.com/-_0cYiWW8ccY/URqvBz3iM4I/AAAAAAAAAbs/9N_Wq8MhLTY/s240-c/Starry%252520Lake.jpg",
//        "https://lh3.googleusercontent.com/-A9LMoRyuQUA/URqvCYx_JoI/AAAAAAAAAbs/s7sde1Bz9cI/s240-c/Starry%252520Night.jpg",
//        "https://lh3.googleusercontent.com/-KtLJ3k858eY/URqvC_2h_bI/AAAAAAAAAbs/zzEBImwDA_g/s240-c/Stream.jpg",
//        "https://lh5.googleusercontent.com/-dFB7Lad6RcA/URqvDUftwWI/AAAAAAAAAbs/BrhoUtXTN7o/s240-c/Strip%252520Sunset.jpg",
//        "https://lh5.googleusercontent.com/-at6apgFiN20/URqvDyffUZI/AAAAAAAAAbs/clABCx171bE/s240-c/Sunset%252520Hills.jpg",
//        "https://lh4.googleusercontent.com/-7-EHhtQthII/URqvEYTk4vI/AAAAAAAAAbs/QSJZoB3YjVg/s240-c/Tenaya%252520Lake%2525202.jpg",
//        "https://lh6.googleusercontent.com/-8MrjV_a-Pok/URqvFC5repI/AAAAAAAAAbs/9inKTg9fbCE/s240-c/Tenaya%252520Lake.jpg",
//        "https://lh5.googleusercontent.com/-B1HW-z4zwao/URqvFWYRwUI/AAAAAAAAAbs/8Peli53Bs8I/s240-c/The%252520Cave%252520BW.jpg",
//        "https://lh3.googleusercontent.com/-PO4E-xZKAnQ/URqvGRqjYkI/AAAAAAAAAbs/42nyADFsXag/s240-c/The%252520Fisherman.jpg",
//        "https://lh4.googleusercontent.com/-iLyZlzfdy7s/URqvG0YScdI/AAAAAAAAAbs/1J9eDKmkXtk/s240-c/The%252520Night%252520is%252520Coming.jpg",
//        "https://lh6.googleusercontent.com/-G-k7YkkUco0/URqvHhah6fI/AAAAAAAAAbs/_taQQG7t0vo/s240-c/The%252520Road.jpg",
//        "https://lh6.googleusercontent.com/-h-ALJt7kSus/URqvIThqYfI/AAAAAAAAAbs/ejiv35olWS8/s240-c/Tokyo%252520Heights.jpg",
//        "https://lh5.googleusercontent.com/-Hy9k-TbS7xg/URqvIjQMOxI/AAAAAAAAAbs/RSpmmOATSkg/s240-c/Tokyo%252520Highway.jpg",
//        "https://lh6.googleusercontent.com/-83oOvMb4OZs/URqvJL0T7lI/AAAAAAAAAbs/c5TECZ6RONM/s240-c/Tokyo%252520Smog.jpg",
//        "https://lh3.googleusercontent.com/-FB-jfgREEfI/URqvJI3EXAI/AAAAAAAAAbs/XfyweiRF4v8/s240-c/Tufa%252520at%252520Night.jpg",
//        "https://lh4.googleusercontent.com/-vngKD5Z1U8w/URqvJUCEgPI/AAAAAAAAAbs/ulxCMVcU6EU/s240-c/Valley%252520Sunset.jpg",
//        "https://lh6.googleusercontent.com/-DOz5I2E2oMQ/URqvKMND1kI/AAAAAAAAAbs/Iqf0IsInleo/s240-c/Windmill%252520Sunrise.jpg",
//        "https://lh5.googleusercontent.com/-biyiyWcJ9MU/URqvKculiAI/AAAAAAAAAbs/jyPsCplJOpE/s240-c/Windmill.jpg",
//        "https://lh4.googleusercontent.com/-PDT167_xRdA/URqvK36mLcI/AAAAAAAAAbs/oi2ik9QseMI/s240-c/Windmills.jpg",
//        "https://lh5.googleusercontent.com/-kI_QdYx7VlU/URqvLXCB6gI/AAAAAAAAAbs/N31vlZ6u89o/s240-c/Yet%252520Another%252520Rockaway%252520Sunset.jpg",
//        "https://lh4.googleusercontent.com/-e9NHZ5k5MSs/URqvMIBZjtI/AAAAAAAAAbs/1fV810rDNfQ/s240-c/Yosemite%252520Tree.jpg",
//};
}
