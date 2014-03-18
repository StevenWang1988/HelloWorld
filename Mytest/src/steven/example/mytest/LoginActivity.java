package steven.example.mytest;

import java.util.concurrent.ExecutionException;

import steven.login.AsyncLogin;
import test.example.handler.ServiceRequest;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import steven.login.LoginStates;
import steven.login.ServerSetting;

public class LoginActivity extends ActionBarActivity {

	private static final String TAG = "LoginActivity";
	String uriAPI = ServerSetting.ServerLocation + ServerSetting.Login;
	EditText userName;
	EditText userPwd;
	ProgressBar progressBar_login;
	Button btn_login;
	private static final int OFF = 0;
	private static final int ON = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		btn_login = (Button) LoginActivity.this.findViewById(R.id.btn_login);
		progressBar_login = (ProgressBar) LoginActivity.this.findViewById(R.id.progressBar_login);
		progressBar_login.setVisibility(View.INVISIBLE);
	}
	
	
	public void LoginOnClick(View view){
		Log.i(TAG,"LoginOnClick");
		userName = (EditText) LoginActivity.this.findViewById(R.id.editT_userName);
		userPwd = (EditText) LoginActivity.this.findViewById(R.id.editT_userPwd);
		uriAPI = uriAPI + "userName=" + userName.getText().toString() + "&" + "userPwd=" + userPwd.getText().toString();
		progressOnOff(ON);
		ServiceRequest.request(new PostHandler(), uriAPI);
	}
	
	private void progressOnOff(int OnOff){
		switch(OnOff){
		case ON:
			progressBar_login.setVisibility(View.VISIBLE);
			btn_login.setVisibility(View.INVISIBLE);
			userName.setVisibility(View.INVISIBLE);
			userPwd.setVisibility(View.INVISIBLE);
			break;
		case OFF:
			progressBar_login.setVisibility(View.INVISIBLE);
			btn_login.setVisibility(View.VISIBLE);
			userName.setVisibility(View.VISIBLE);
			userPwd.setVisibility(View.VISIBLE);
			break;
		}
	}
	
	class PostHandler extends Handler{
		public void handleMessage(Message msg){
			Log.d("handleMessage", "msg.what: " + msg.what);
			int State = msg.what;
			
			switch(State){
			case LoginStates.NORESPONSE:
				Log.v(TAG,"msg is null");
				Toast.makeText(LoginActivity.this, "登入失敗!", Toast.LENGTH_LONG).show();
				progressOnOff(OFF);
				break;
			case LoginStates.SUCCESS:
				Intent intent = new Intent();
				intent.setClass(LoginActivity.this, MainActivity.class);
				startActivity(intent);
				LoginActivity.this.finish();
				break;
			case LoginStates.USERNAME_ERR:
				Log.v(TAG,"msg is userNameErr");
				Toast.makeText(LoginActivity.this, "使用者名稱錯誤!", Toast.LENGTH_LONG).show();
				progressOnOff(OFF);
				break;
			case LoginStates.USERPWD_ERR:
				Log.v(TAG,"msg is userNameErr");
				Toast.makeText(LoginActivity.this, "密碼錯誤!", Toast.LENGTH_LONG).show();
				progressOnOff(OFF);
				break;
			}
		}
	}

}
