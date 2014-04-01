package steven.example.mytest;

import test.example.handler.ServiceRequest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import steven.login.LoginStates;
import steven.login.ServerSetting;

import com.enrique.stackblur.StackBlurManager;

public class LoginActivity extends Activity  {

	private static final String TAG = "LoginActivity";
//	String uriAPI = ServerSetting.ServerLocation + ServerSetting.Login;
	private static final int OFF = 0;
	private static final int ON = 1;
	private String uriAPI = "";
	private EditText userName;
	private EditText userPwd;
	private ProgressBar progressBar_login;
	private Button btn_login;
	private StackBlurManager _stackBlurManager;
	private ImageView loginImage;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		btn_login = (Button) this.findViewById(R.id.btn_login);
		progressBar_login = (ProgressBar) this.findViewById(R.id.progressBar_Login);
		loginImage = (ImageView) this.findViewById(R.id.loginImage);
		
		progressBar_login.setVisibility(View.INVISIBLE);
		_stackBlurManager = new StackBlurManager(BitmapFactory.decodeResource(loginImage.getResources(), R.drawable.image));
		loginImage.setImageBitmap(_stackBlurManager.process(50));
	}
	
	
	public void LoginOnClick(View view){
		Log.i(TAG,"LoginOnClick");
		uriAPI = ServerSetting.ServerLocation + ServerSetting.Login;
		userName = (EditText) LoginActivity.this.findViewById(R.id.editT_userName);
		userPwd = (EditText) LoginActivity.this.findViewById(R.id.editT_userPwd);
		uriAPI = uriAPI + "userName=" + userName.getText().toString() + "&" + "userPwd=" + userPwd.getText().toString();
		Log.v(TAG, uriAPI);
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
			Log.d("TAG", "handleMessage msg.what: " + msg.what);
			Log.d("TAG", "handleMessage msg.obj: " + msg.obj);
			
			switch(msg.what){
			case LoginStates.NORESPONSE:
				Log.v(TAG, "handleMessage msg is null");
				Toast.makeText(LoginActivity.this, "伺服器無回應!", Toast.LENGTH_LONG).show();
				progressOnOff(OFF);
				break;
			case LoginStates.SUCCESS:
				Intent intent = new Intent();
				intent.setClass(LoginActivity.this, MainActivity.class);
				Bundle bundle = new Bundle();
				bundle.putString("userName", userName.getText().toString());
				intent.putExtras(bundle);
				startActivity(intent);
				LoginActivity.this.finish();
				break;
			case LoginStates.USERNAME_ERR:
				Log.v(TAG,"handleMessage msg is userNameErr");
				Toast.makeText(LoginActivity.this, "使用者名稱錯誤!", Toast.LENGTH_LONG).show();
				progressOnOff(OFF);
				break;
			case LoginStates.USERPWD_ERR:
				Log.v(TAG,"handleMessage msg is userNameErr");
				Toast.makeText(LoginActivity.this, "密碼錯誤!", Toast.LENGTH_LONG).show();
				progressOnOff(OFF);
				break;
			case LoginStates.FAIL:
				Log.v(TAG, "unknow error");
				Toast.makeText(LoginActivity.this, "未知錯誤!", Toast.LENGTH_LONG).show();
				progressOnOff(OFF);
				break;
			}
		}
	}

}
