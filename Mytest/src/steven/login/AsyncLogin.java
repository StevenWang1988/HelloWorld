package steven.login;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class AsyncLogin extends AsyncTask<String, String, String>{
	private static final String TAG = "AsyncLogin";
	
	HttpURLConnection conn = null;
	Handler handler;
	int what;
	
	public AsyncLogin(Handler handler){
		this.handler = handler;
	}

	@Override
	protected String doInBackground(String... uri) {
		// TODO Auto-generated method stub
		return userLogin(uri[0]);
	}

	private String userLogin(String uriAPI) {
		// TODO Auto-generated method stub
		what = 0;
		try {
			URL url = new URL(uriAPI);
			Log.i(TAG, "1 start");
			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(10 * 1000);
			conn.setRequestMethod("GET");
			Log.i(TAG, "2 setting");
			conn.connect();
			Log.i(TAG, "3 connected");
			if(Thread.interrupted()){
				throw new InterruptedException();
			}
			
			// read data
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			what = Integer.parseInt(reader.readLine());
			reader.close();
		} catch (IOException | InterruptedException e) {
			Log.e(TAG, e.getMessage());
		}
		conn.disconnect();
		Message msg = handler.obtainMessage();
		msg.what = what;
		handler.sendMessage(msg);
		return null;
	}

	protected void onPostExecute(String conf){
//		Log.v(TAG, "onPostExecute :"+ conf);
	}
	
}
