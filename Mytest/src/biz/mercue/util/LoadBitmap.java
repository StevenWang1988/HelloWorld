package biz.mercue.util;


import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

public class LoadBitmap extends AsyncTask<String, String, String>{
	private static final String TAG = "LoadBitmap";
	
	private ImageView image= null;
	
	private Bitmap mBitmap;
	public void setImageView(ImageView view){
		this.image = view;
	}
	
	public static Bitmap getURLBitmap(String imageUrl){
		Log.v(TAG, "getURLBitmap");
		Bitmap bitmap = null;
		
		try {
			Log.v(TAG, "imageUrl: " + imageUrl);
			URL url = new URL(imageUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.connect();
			InputStream is = conn.getInputStream();
			
			bitmap = BitmapFactory.decodeStream(is);
			is.close();
		} catch (IOException e) {
			Log.v(TAG, "MalformedURLException" + e.getMessage());
		}
		
		return bitmap;
	}

	@Override
	protected String doInBackground(String... params) {
		mBitmap = getURLBitmap(params[0]);
		return null;
	}

	@Override
	protected void onPostExecute(String result) {
		this.image.setImageBitmap(mBitmap);
		super.onPostExecute(result);
	}
	
	
}
