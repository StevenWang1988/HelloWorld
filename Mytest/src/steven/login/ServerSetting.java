package steven.login;

public class ServerSetting {
//	public static final String IP = "192.168.2.104";
//	public static final int PORT = 8080;
	public static final String IP = "192.168.2.104:7070/MyWebProject";
	public static final int PORT = 7070;
	public static final String Login = "/LoginServlet?";
	public static final String UserList = "/UserListServlet?";
	public static final String HeadImage = "/headImageServlet?";
//	public static final String ServerLocation = "http://" + ServerSetting.IP + ":" + ServerSetting.PORT;
	public static final String ServerLocation = "http://" + ServerSetting.IP;
}
