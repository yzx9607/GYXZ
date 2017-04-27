package www.gyxz.qiantai;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
/**
 * @杨振欣 前台教师信息类
 *
 */
public class UserDao {
	/**
	 * @杨振欣 前台用户登陆，查询符合账号密码的教师的真实姓名
	 *
	 */
	Logger log=Logger.getLogger("ListUser");
public String ListUser(Connection con,String loginname,String pwd) throws SQLException{
	PreparedStatement ps=null;
	String realname=null;
	ResultSet rs=null;
	String sql="select realname from y_user where loginname='"+loginname+"' and userpwd='"+pwd+"' ";
	try {
		ps=con.prepareStatement(sql);
		rs=ps.executeQuery();
		log.debug(ps.toString());
		while(rs.next()){
			realname=rs.getString("realname");
		}
	}finally{
		//关闭连接
			try {
				if(rs!=null){
				rs.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.error(e.getMessage(),e);
		}
			try {
				if(ps!=null){
				ps.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.error(e.getMessage(),e);
		}
	}
	return realname;
}
/**
 * @throws SQLException 
 * @杨振欣 查询满足登录名的教师登录名
 *
 */
public boolean ListName(Connection con,String name) throws SQLException{
	PreparedStatement ps=null;
	ResultSet rs=null;
	boolean has=false;
	String sql="select userid from y_user where loginname = '"+name+"'";
	try {
		ps=con.prepareStatement(sql);
		rs=ps.executeQuery();
		while(rs.next()){
			has=true;
		}
	}finally{
		//关闭连接
			try {
				if(rs!=null){
				rs.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.error(e.getMessage(),e);
		}
			try {
				if(ps!=null){
				ps.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.error(e.getMessage(),e);
		}
	}
	return has;
	
}
/**
 * @throws SQLException 
 * @杨振欣 查询满足指定身份证号码的教师登陆信息是否存在
 *
 */
public boolean ListCard(Connection con,String card) throws SQLException{
	PreparedStatement ps=null;
	ResultSet rs=null;
	boolean has=false;
	String sql="select userid from y_user where usercard = '"+card+"'";
	try {
		ps=con.prepareStatement(sql);
		rs=ps.executeQuery();
		while(rs.next()){
			has=true;
		}
	}finally{
		//关闭连接
			try {
				if(rs!=null){
				rs.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.error(e.getMessage(),e);
		}
			try {
				if(ps!=null){
				ps.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.error(e.getMessage(),e);
		}
	}
	return has;
}
/**
 * @throws SQLException 
 * @杨振欣 查询满足指定教师登陆名的身份证号码
 *
 */
public String SearchCard(Connection con,String loginname) throws SQLException{
	PreparedStatement ps=null;
	ResultSet rs=null;
	String card=null;
	String sql="select usercard from y_user where loginname = '"+loginname+"'";
	try {
		ps=con.prepareStatement(sql);
		rs=ps.executeQuery();
		while(rs.next()){
			card=rs.getString("usercard");
		}
	}finally{
		//关闭连接
			try {
				if(rs!=null){
				rs.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.error(e.getMessage(),e);
		}
			try {
				if(ps!=null){
				ps.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.error(e.getMessage(),e);
		}
	}
	return card;
	
}
/*public static void main(String[] args) {
	UserDao ud=new UserDao();
	Connection con=db.AddDB();
	String name=ud.SearchCard(con, "yang");
	System.out.println(name);
}*/
/**
 * @throws SQLException 
 * @杨振欣 查询满足指定教师登陆名的所有信息，供前台修改信息时提醒
 *
 */
public User ListUsers(Connection con,String loginname) throws SQLException{
	PreparedStatement ps=null;
	ResultSet rs=null;
	Logger log=Logger.getLogger("ListUses");
	String sql="SELECT userid,loginname,userpwd,realname,userphone,usercard FROM y_user where loginname='"+loginname+"'";
	User user=new User();
	try {
		ps=con.prepareStatement(sql);
		rs=ps.executeQuery();
		log.debug(ps.toString());
		while(rs.next()){
			user.setUserid(rs.getInt("userid"));
			user.setUserpwd(rs.getString("userpwd"));
			user.setLoginnname(rs.getString("loginname"));
			user.setUsername(rs.getString("realname"));
			user.setUserphone(rs.getString("userphone"));
			user.setUsercard(rs.getString("usercard"));
		}
	}finally{
		//关闭连接
			try {
				if(rs!=null){
				rs.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.error(e.getMessage(),e);
		}
			try {
				if(ps!=null){
				ps.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.error(e.getMessage(),e);
		}
	}
	return user;

	
	
}
/**
 * @throws SQLException 
 * @杨振欣 前台教师注册
 *
 */
public int UserReg(Connection con,User user) throws SQLException{
	PreparedStatement ps=null;
	int rows=0;
	String sql="insert into y_user values(0,?,?,?,?,?)";
	try {
		ps=con.prepareStatement(sql);
		ps.setString(1, user.getLoginnname());
		ps.setString(2, user.getUserpwd());
		ps.setString(3, user.getUsername());
		ps.setString(4, user.getUserphone());
		ps.setString(5, user.getUsercard());
		rows=ps.executeUpdate();
		log.debug(ps.toString());
	}finally{
		//关闭连接
			try {
				if(ps!=null){
				ps.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.error(e.getMessage(),e);
		}
	}
	return rows;
	
}

public int UserUpadte(Connection con,User user) throws SQLException{
	PreparedStatement ps=null;
	int rows=0;
	String sql="update y_user set loginname=?,userpwd=?,realname=?,userphone=?,usercard=? where userid=?";
	try {
		ps=con.prepareStatement(sql);
		ps.setString(1, user.getLoginnname());
		ps.setString(2, user.getUserpwd());
		ps.setString(3, user.getUsername());
		ps.setString(4, user.getUserphone());
		ps.setString(5, user.getUsercard());
		ps.setInt(6, user.getUserid());
	/*	System.out.println(ps.toString());*/
		rows=ps.executeUpdate();
		log.debug(ps.toString());
	}finally{
		//关闭连接
			try {
				if(ps!=null){
				ps.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.error(e.getMessage(),e);
		}
	}
	
	return rows;
	
}
/**
 * @throws SQLException 
 * @杨振欣 前台找回密码
 *
 */

public int UserRemeber(Connection con,String pwd,String loginname,String userphone,String usercard) throws SQLException{
	PreparedStatement ps=null;
	int rows=0;
	Logger log=Logger.getLogger("UserRemeber");
	String sql="update y_user set userpwd=? where loginname=? and userphone=? and usercard=?";
	try {
		ps=con.prepareStatement(sql);
		ps.setString(1, pwd);
		ps.setString(2, loginname);
		ps.setString(3, userphone);
		ps.setString(4, usercard);
/*		System.out.println(ps.toString());*/
		log.debug(ps.toString());
		rows=ps.executeUpdate();
	}finally{
		//关闭连接
			try {
				if(ps!=null){
				ps.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.error(e.getMessage(),e);
		}
	}
	return rows;
	
}

}
