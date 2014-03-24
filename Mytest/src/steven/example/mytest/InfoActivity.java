package steven.example.mytest;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InfoActivity extends ActionBarActivity {
	Button btn_back;
	Bundle bundle;
	String content;
	TextView tv_content;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_info);

		bundle = this.getIntent().getExtras();
		if(bundle == null){
			
		} else {
			content = bundle.getString("content");
		}
		tv_content = (TextView) findViewById(R.id.infoText);
		tv_content.setText(content);
		
		btn_back = (Button) findViewById(R.id.btn_infoBack);
		btn_back.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View v){
				InfoActivity.this.finish();
			}
		});
		
		
	}

}
