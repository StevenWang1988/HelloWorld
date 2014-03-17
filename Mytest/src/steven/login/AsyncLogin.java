package steven.login;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.os.AsyncTask;
import android.util.Log;

public class AsyncLogin extends AsyncTask<String, String, String>{
	private static final String TAG = "AsyncLogin";
	
	HttpURLConnection conn = null;

	@Override
	protected String doInBackground(String... uri) {
		// TODO Auto-generated method stub
		return userLogin(uri[0]);
	}

	private String userLogin(String uriAPI) {
		// TODO Auto-generated method stub
		String conf = null;
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
			conf = reader.readLine();
			reader.close();
		} catch (IOException | InterruptedException e) {
			Log.e(TAG, e.getMessage());
		}
		conn.disconnect();
		return conf;
	}

	protected void onPostExecute(String conf){
		Log.v(TAG, "onPostExecute :"+ conf);
	}
	
}
