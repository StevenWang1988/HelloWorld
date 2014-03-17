package listview.newitem;

import android.widget.ImageView;

public class MyItem {
	private String title;
	private String content;
	private ImageView headImage;
	
	public MyItem(String title, String content, ImageView headImage){
		this.setTitle(title);
		this.setContent(content);
		this.setHeadImage(headImage);
	}

	public MyItem() {
		// TODO Auto-generated constructor stub
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public ImageView getHeadImage() {
		return headImage;
	}

	public void setHeadImage(ImageView headImage) {
		this.headImage = headImage;
	}
}
