package steven.fileSystem;

import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.content.res.AssetManager;

public class LoadFile {

	AssetManager as;
	public String loadJSONFromAssets(Context ctx){
		String json = null;
		try{
			InputStream is = ctx.getAssets().open("json_userData.json");
			int size = is.available();
			byte[] buffer = new byte[size];
			is.read(buffer);
			is.close();
			json = new String(buffer, "UTF-8");
		} catch (IOException ex){
			ex.printStackTrace();
			return null;
		}
		
		return json;
	}
}
