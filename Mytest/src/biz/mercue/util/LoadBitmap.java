package biz.mercue.util;


import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

import steven.example.mytest.R;
import listview.newitem.MyItem;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class LoadBitmap extends AsyncTask<Integer, String, Bitmap>{
	private WeakReference<ImageView> imageViewReference;
	private static final String TAG = "LoadBitmap";
	
	private String ImageURL;
	public int data = 0;
	private Animation myFadeInAnimation;

	public LoadBitmap(ImageView view, String url){
		imageViewReference = new WeakReference<ImageView>(view);
		this.ImageURL = url;
	}
	
	private Bitmap getURLBitmap(){
		Log.v(TAG, "getURLBitmap");
		Bitmap bitmap = null;
		
		try {
			Log.v(TAG, "imageUrl: " + this.ImageURL);
			URL url = new URL(this.ImageURL);
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
	protected Bitmap doInBackground(Integer... params) {
		data = params[0];
		return getURLBitmap();
	}

	@Override
	protected void onPostExecute(Bitmap mBitmap) {
		if (isCancelled()) {
			mBitmap = null;
        }
		if (imageViewReference != null && mBitmap != null) {
            final ImageView imageView = imageViewReference.get();
            final LoadBitmap bitmapWorkerTask =
                    getBitmapWorkerTask(imageView);
            if (this == bitmapWorkerTask && imageView != null) {
            	imageView.setImageBitmap(mBitmap);
            	myFadeInAnimation = AnimationUtils.loadAnimation(imageViewReference.get().getContext(), R.anim.fadein);
            	imageView.startAnimation(myFadeInAnimation);
            }
        }
	}
	
	private static LoadBitmap getBitmapWorkerTask(ImageView imageView) {
	   if (imageView != null) {
	       final Drawable drawable = imageView.getDrawable();
	       if (drawable instanceof AsyncDrawable) {
	           final AsyncDrawable asyncDrawable = (AsyncDrawable) drawable;
	           return asyncDrawable.getBitmapWorkerTask();
	       }
	    }
	    return null;
	}
	
	public static boolean cancelPotentialWork(int data, ImageView imageView) {
	    final LoadBitmap bitmapWorkerTask = getBitmapWorkerTask(imageView);

	    if (bitmapWorkerTask != null) {
	        final int bitmapData = bitmapWorkerTask.data;
	        // If bitmapData is not yet set or it differs from the new data
	        if (bitmapData == 0 || bitmapData != data) {
	            // Cancel previous task
	            bitmapWorkerTask.cancel(true);
	        } else {
	            // The same work is already in progress
	            return false;
	        }
	    }
	    // No task associated with the ImageView, or an existing task was cancelled
	    return true;
	}
	
	public static void loadBitmap(int resId, ImageView imageView, MyItem items[]) {
	    if (cancelPotentialWork(resId, imageView)) {
	        final LoadBitmap loadbitmap = new LoadBitmap(imageView, items[resId].getImageURL());
	        final AsyncDrawable asyncDrawable =
	                new AsyncDrawable(null, null, loadbitmap);
	        imageView.setImageDrawable(asyncDrawable);
	        loadbitmap.execute(resId);
	    }
	}
	
}
