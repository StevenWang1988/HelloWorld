package biz.mercue.loadBigBitmap;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.annotation.Resource;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.util.Log;
import android.util.TypedValue;
import android.widget.ImageView;

/**
 * @author Steven
 *
 */
public class AsyncLoadBigBitmap extends AsyncTask<Integer, Object, Bitmap>{
	private static final String TAG = "AsyncLoadBigBitmap";
	private String ImageURL;
	private ImageView imageView;
	private Bitmap bitmap;
	
	public AsyncLoadBigBitmap(String url, ImageView view){
		this.ImageURL = url;
		this.imageView = view;
	}
	@Override
	protected Bitmap doInBackground(Integer... params) {
		bitmap = null;
		Log.v(TAG, "doInBackground START");
		try {
			Log.v(TAG, "imageUrl: " + this.ImageURL);
			URL url = new URL(this.ImageURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.connect();
			InputStream is = conn.getInputStream();
			
//			// First decode with inJustDecodeBounds=true to check dimensions
//		    final BitmapFactory.Options options = new BitmapFactory.Options();
//		    options.inJustDecodeBounds = true;
//		    BitmapFactory.decodeStream(is, new Rect(), options);
//		    // Calculate inSampleSize
//		    options.inSampleSize = calculateInSampleSize(options, 150, 100);
//		    // Decode bitmap with inSampleSize set
//		    options.inJustDecodeBounds = false;
//			
//			bitmap = BitmapFactory.decodeStream(is, new Rect(), options);
			
			bitmap = BitmapFactory.decodeStream(is);
			is.close();
		} catch (IOException e) {
			Log.v(TAG, "MalformedURLException" + e.getMessage());
		}
		Log.v(TAG, "doInBackground END");
		return bitmap;
	}
	@Override
	protected void onPostExecute(Bitmap bitmap) {
		Log.v(TAG, "onPostExecute");
		
		imageView.setImageBitmap(bitmap);
	}
	
	public int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
	    // Raw height and width of image
	    final int height = options.outHeight;
	    final int width = options.outWidth;
	    int inSampleSize = 1;
	
	    if (height > reqHeight || width > reqWidth) {
	
	        final int halfHeight = height / 2;
	        final int halfWidth = width / 2;
	
	        // Calculate the largest inSampleSize value that is a power of 2 and keeps both
	        // height and width larger than the requested height and width.
	        while ((halfHeight / inSampleSize) > reqHeight
	                && (halfWidth / inSampleSize) > reqWidth) {
	            inSampleSize *= 2;
	        }
	    }
	    Log.v(TAG, "calculateInSampleSize: " + inSampleSize);
	    return inSampleSize;
	}
}
