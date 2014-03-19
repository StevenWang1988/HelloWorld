package listview.newitem;

public class MyItem {
	private String title;
	private String content;
	private String headImageUrl;
	
	public MyItem(String title, String content, String headImageUrl){
		this.setTitle(title);
		this.setContent(content);
		this.setHeadImageUrl(headImageUrl);
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

	public String getHeadImageUrl() {
		return headImageUrl;
	}

	public void setHeadImageUrl(String headImageUrl) {
		this.headImageUrl = headImageUrl;
	}
}
