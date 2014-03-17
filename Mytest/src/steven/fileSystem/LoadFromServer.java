package steven.fileSystem;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.os.AsyncTask;
import android.util.Log;

public class LoadFromServer extends AsyncTask<String, String, String>{
	
	
	@Override
	protected String doInBackground(String... uri) {
		// TODO Auto-generated method stub
		return LoadJSONFromServer(uri[0]);
	}
	
	protected void onPostExecute(String jsonString) {
        // TODO: check this.exception 
		Log.v("json", jsonString);
    }
	
	public String LoadJSONFromServer(String uriAPI){
		String json = null;
		Log.v("uriAPI", uriAPI);
		HttpGet httpRequest = new HttpGet(uriAPI);
		
		try {
			HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequest);
			if(httpResponse.getStatusLine().getStatusCode() == 200){
				json = EntityUtils.toString(httpResponse.getEntity());
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.v("ClientProtocolException", "err");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.v("IOException", "err");
		}
		return json;
	}
}
