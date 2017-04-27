package www.gyxz.bean;
/**
 * 管理员登陆表
 * */
public class Admin {
private int login_id;//管理员编号
private String login_name;//管理员登陆名
private String login_passoword;//管理员密码
/**
 * 无参数的构造函数
 * **/
public Admin() {
	super();
}
/**
 * 有参数的构造函数
 * **/
public Admin(int login_id, String login_name, String login_passoword) {
	super();
	this.login_id = login_id;
	this.login_name = login_name;
	this.login_passoword = login_passoword;
}
/**
 * 获取管理员id
 * */
public int getLogin_id() {
	return login_id;
}
/**
 * 设置管理员id
 * */
public void setLogin_id(int login_id) {
	this.login_id = login_id;
}
/**
 * 获取管理员登录名
 * */
public String getLogin_name() {
	return login_name;
}
/**
 * 设置登录名
 * */
public void setLogin_name(String login_name) {
	this.login_name = login_name;
}
/**
 * 获取管理员密码
 * */
public String getLogin_passoword() {
	return login_passoword;
}
/**
 * 设置管理员密码
 * **/
public void setLogin_passoword(String login_passoword) {
	this.login_passoword = login_passoword;
}
}
