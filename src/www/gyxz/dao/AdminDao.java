package www.gyxz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import www.gyxz.bean.Admin;
/**
 * @杨振欣 管理员信息管理
 * */

public class AdminDao {
	/**
	 * @杨振欣 日志处理
	 *
	 */
	private static Logger log=Logger.getLogger("AdminDao");
	/**
	 *@throws SQLException 
	 * @杨振欣 查找符合用户名以及密码的管理员信息     登录验证
	 * */
public boolean ListAdmin(Connection con,Admin admin) throws SQLException{
	String sql="select login_id from admin where login_name=? and login_password=?";
	 PreparedStatement ps=null;
	 ResultSet rs=null;
	 try {
		ps=con.prepareStatement(sql);
		ps.setString(1, admin.getLogin_name());
		ps.setString(2, admin.getLogin_passoword());
		rs=ps.executeQuery();
		log.debug(admin.getLogin_name()+"用户登录:"+ps);//记录登录信息
		while(rs.next()){
			return true;//返回满足该信息的教师
		}
	} finally{
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
	return false;
}
/**
 * 修改符合条件的管理员信息（姓名和密码）
 * @throws SQLException 
 * */
public int UpdatePwd(Connection con,String username,String name,String pwd) throws SQLException{
	String sql="update admin set login_name='"+username+"',login_password='"+pwd+"' where  login_name='"+name+"'";
	PreparedStatement ps=null;
	 int rows=0;
	 try {
		ps=con.prepareStatement(sql);
		rows=ps.executeUpdate();
		log.debug("修改用户"+ps);
	}finally{
		//关闭连接
		try {
			if(ps!=null){
			ps.close();
			}
		} catch (SQLException e) {
			log.error(e.getMessage(),e);
	}
}
	return rows;
}
	
}
