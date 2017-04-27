package www.gyxz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.log4j.Logger;


/**
 * @杨振欣 公告信息类
 *
 */
public class NoticeDao {
	/**
	 * @杨振欣 日志信息管理
	 *
	 */
	private static Logger log=Logger.getLogger("NoticeDao");
	/**
	 * @throws SQLException 
	 * @杨振欣 日志信息查询
	 *
	 */
	public ArrayList<Object> ListNotice(Connection con) throws SQLException{
		String sql="select notice_id,notice_desc from y_notice";
		PreparedStatement ps=null;
		ResultSet rs=null;
		String notice=null;
		int id=0;
		ArrayList<Object> list=new ArrayList<Object>();
		try {
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			log.debug(ps);
				while(rs.next()){
					notice=rs.getString("notice_desc");
					id=rs.getInt("notice_id");
					list.add(notice);
					list.add(id);
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
		return list;
		
	}
	/**
	 * @throws SQLException 
	 * @杨振欣 日志信息修改
	 *
	 */
	public int UpdateNotice(Connection con,String notice_desc,int notice_id) throws SQLException{
		String sql="update y_notice set notice_desc='"+notice_desc+"' where notice_id="+notice_id+"";
		PreparedStatement ps=null;
		int rows=0;
		try {
			ps=con.prepareStatement(sql);
			rows=ps.executeUpdate();
			log.debug(ps.toString());
		}finally{
			if(ps!=null){
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					log.error(e.getMessage(),e);
				}
			}
		}
		return rows;
		
	}
	/**
	 * @throws SQLException 
	 * @杨振欣 日志信息添加，首次配置时需要添加
	 *
	 */
	public int AddNotice(Connection con,String notice_desc) throws SQLException{
		String sql="insert into y_notice values(0,?)";
		PreparedStatement ps=null;
		int rows=0;
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1, notice_desc);
			log.debug(ps);
		}finally{
				if(ps!=null){
					try {
						ps.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						log.error(e.getMessage(),e);
					}
				}
			}
		return rows;
	}
	
}
