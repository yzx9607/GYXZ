package www.gyxz.qiantai;

/**
 * @杨振欣 前台教师信息类
 *
 */
public class User {
	public User(){
		
	}
	private int userid;
	private String loginnname;
	private String userpwd;
	private String username;
	private String userphone;
	private String usercard;
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getLoginnname() {
		return loginnname;
	}
	public void setLoginnname(String loginnname) {
		this.loginnname = loginnname;
	}
	public String getUserpwd() {
		return userpwd;
	}
	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserphone() {
		return userphone;
	}
	public void setUserphone(String userphone) {
		this.userphone = userphone;
	}
	public String getUsercard() {
		return usercard;
	}
	public void setUsercard(String usercard) {
		this.usercard = usercard;
	}
	public User(int userid, String loginnname, String userpwd, String username,
			String userphone, String usercard) {
		super();
		this.userid = userid;
		this.loginnname = loginnname;
		this.userpwd = userpwd;
		this.username = username;
		this.userphone = userphone;
		this.usercard = usercard;
	}
	

}
