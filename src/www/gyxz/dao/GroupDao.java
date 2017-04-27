package www.gyxz.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import net.sf.json.JSONArray;
import org.apache.log4j.Logger;
import www.gyxz.bean.Group;
import www.gyxz.bean.PageBean;
import www.gyxz.util.JsonUtil;
/**
 * @杨振欣 系部信息类
 *
 */

public class GroupDao {
/**
 * @杨振欣 日志处理
 *
 */
	private static Logger log=Logger.getLogger("GroupDao");
	/**
	 * @杨振欣 查询系部信息
	 *
	 */
	public  JSONArray ListGroup(Connection con,String group_name,PageBean pagebean){
		StringBuffer sb=new StringBuffer("select group_name,group_id,group_desc from y_group where group_sta=0");
		PreparedStatement ps=null;
		ResultSet rs=null;
		JSONArray json =null;
		//模糊查询，查询指定系部名称的信息
		if(group_name!=null&&!group_name.equals("")){
			sb.append(" and group_name like '%"+group_name.replace(" ", "")+"%'");
		}
		if(pagebean !=null){
			sb.append(" ORDER BY group_id limit "+pagebean.getStart()+","+pagebean.getRows());
		}
		try {
			ps=con.prepareStatement(sb.toString());
			rs=ps.executeQuery();
			json=JsonUtil.formatRsJSONArray(rs);
			log.debug("查看所有系部"+ps);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		return json;
		
	}
	/**
	 * @throws SQLException 
	 * @杨振欣 添加新的系部
	 *
	 */
	public int AddGroup(Connection con,Group group) throws SQLException{
		String sql="insert into y_group values(null,?,?,0)";
		PreparedStatement ps=null;
		int rows=0;;
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1,group.getGroup_name());
			ps.setString(2, group.getGroup_desc());
			rows=ps.executeUpdate();
			log.debug("添加了新的系部"+ps);
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
	 * @杨振欣 更新指定系部id的系部信息
	 *
	 */
	public int UpdateGroup(Connection con,Group group) throws SQLException{
		String sql="update y_group set group_name=?,group_desc=? where group_id=?";
		PreparedStatement ps=null;
		int rows=0;
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1, group.getGroup_name());
			ps.setString(2, group.getGroup_desc());
			ps.setInt(3, group.getGroup_id());
			rows=ps.executeUpdate();
			log.debug("修改了系部"+ps);
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
	 * @杨振欣 删除指定系部的信息
	 *
	 */
	public int GroupDelete(Connection conn,String delIds) throws SQLException{
		String sql="update y_group set group_sta=1 where group_id in("+delIds+")";
		int num=0;
		PreparedStatement ps =null;
		try {
			ps=conn.prepareStatement(sql);
			num=ps.executeUpdate();
			log.debug("删除了系部"+ps);
		} finally{
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
		//返回影响的行数
		return num;
		
	}
	/**
	 * @throws SQLException 
	 * @杨振欣 统计系部的信息条数，包括模糊查询后的信息量，供前台统计显示
	 *
	 */
		 public int pageSum( Connection conn,String group_name) throws SQLException{
			 String sql="select COUNT(group_id) as total from y_group where group_sta=0";
			//当条件执行后，数据量的显示
			 if(group_name!=null){
				 sql=sql+" and group_name like '%"+group_name.replace(" ", "")+"%'";
			 }
			 ResultSet rs=null;
			 int sum=0;
			 PreparedStatement ps=null;
			 try {
				ps=conn.prepareStatement(sql);
				rs=ps.executeQuery();
				log.debug("统计满足条件的系部数量"+ps);
				while(rs.next()){
					sum=rs.getInt("total");
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
			 //返回查询后数据条数
			return sum;
			 
		 }
	/**
	 * @throws SQLException 
	 * @杨振欣 查询所有的系部信息，用来在前台显示下拉框
	 *
	 */	 
		 public JSONArray ListGroupName(Connection con) throws SQLException{
				String sql="select group_id,group_name from y_group where group_sta=0";
				//查询出所有的系部信息
				ResultSet rs=null;
				PreparedStatement ps=null;
				JSONArray json=null;
				try {
					ps=con.prepareStatement(sql);
					rs=ps.executeQuery();
					log.debug("查询出所有的系部信息"+ps);
					json=JsonUtil.formatRsJSONArray(rs);
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
				//返回查询结果
				return json;
				
			}
		 
}
