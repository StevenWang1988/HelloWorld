package layout.change;


import steven.example.mytest.R;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SetLayout extends Activity{
	
	Button btn_back;
	TextView content;
	TextView userName;
	TextView detail_content;
	TextView detail_name;
	Activity activity;
	
	public void mySetLayout(View view){
		
		content = (TextView) view.findViewById(R.id.description);
		userName = (TextView) view.findViewById(R.id.title);
		activity = (Activity) view.getContext();
		activity.setContentView(R.layout.activity_user_data);
		
		detail_content = (TextView) activity.findViewById(R.id.detail_description);
		detail_content.setText((String) content.getText());
		detail_name = (TextView) activity.findViewById(R.id.detail_title);
		detail_name.setText((String) userName.getText());
		
		btn_back = (Button) activity.findViewById(R.id.btn_detail_back);
		btn_back.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				activity = (Activity) v.getContext();
				activity.finish();
			}
			
		});
		
		
	}
}
