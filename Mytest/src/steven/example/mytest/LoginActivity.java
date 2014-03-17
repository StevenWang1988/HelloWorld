package steven.example.mytest;

import java.util.concurrent.ExecutionException;

import steven.login.AsyncLogin;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class LoginActivity extends ActionBarActivity {

	private static final String TAG = "LoginActivity";
	private static final String IP = "192.168.137.1";
	private static final int PORT = 8080;
	String uriAPI = "http://" + IP + ":" + PORT + "/login?";
	EditText userName;
	EditText userPwd;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		
	}
	
	public void LoginOnClick(View view){
		userName = (EditText) LoginActivity.this.findViewById(R.id.editT_userName);
		userPwd = (EditText) LoginActivity.this.findViewById(R.id.editT_userPwd);
		
		uriAPI = uriAPI + "userName=" + userName.getText().toString() + "&" + "userPwd=" + userPwd.getText().toString(); 
		
		AsyncLogin asylogin = (AsyncLogin) new AsyncLogin().execute(uriAPI);
		
		Log.i(TAG,"login end");
		String msg = null;
		try {
			msg = asylogin.get();
			Log.i(TAG,"login get");
			Log.i(TAG,"msg :"+msg);
		} catch (InterruptedException e) {
			Log.e(TAG,e.getMessage());
		} catch (ExecutionException e) {
			Log.e(TAG,e.getMessage());
		}

		if("login".equals(msg) ){
		 
			Intent intent = new Intent();
			intent.setClass(LoginActivity.this, MainActivity.class);
			startActivity(intent);
			LoginActivity.this.finish();
		} else if ("userNameErr".equals(msg)){
			Log.v(TAG,"msg is userNameErr");
			Toast.makeText(LoginActivity.this, "使用者名稱錯誤!", Toast.LENGTH_LONG).show();
		} else if ("pwdErr".equals(msg)){
			Log.v(TAG,"msg is userNameErr");
			Toast.makeText(LoginActivity.this, "密碼錯誤!", Toast.LENGTH_LONG).show();
		} else if (msg == null){
			Log.v(TAG,"msg is null");
			Toast.makeText(LoginActivity.this, "登入失敗!", Toast.LENGTH_LONG).show();
		} else {
			Log.v(TAG,"Unknow Err");
			Toast.makeText(LoginActivity.this, "發生未知錯誤!", Toast.LENGTH_LONG).show();
		}
		
	}

}
