package www.gyxz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;

import www.gyxz.bean.PageBean;
import www.gyxz.bean.Teacher;
import www.gyxz.util.JsonUtil;

/**
 * @杨振欣 教师信息管理类
 *
 */
public class TeacherDao {
	private Logger log=Logger.getLogger("TeacherDao");
/**
 * @throws SQLException 
 * @杨振欣 教师信息查询（包含条件查询）
 *
 */
	public JSONArray teacherList(Connection con,String teacher_name,String teacher_group,String teacher_sex,PageBean pagebean) throws SQLException{
		StringBuffer sb=new StringBuffer("SELECT a.teacher_id,a.teacher_name,b.group_name,a.teacher_sex,a.teacher_group,a.teacher_tel,a.teacher_email,a.teacher_card,a.teacher_desc FROM y_teacher  a,y_group  b where a.tea_sta=0 and a.teacher_group=b.group_id ");
		PreparedStatement ps=null;
		ResultSet rs=null;
		JSONArray json =null;
		if(teacher_name!=null){
			sb.append(" and a.teacher_name like '%"+teacher_name.replace(" ", "")+"%'");//根据教师姓名查询
	
		}
		if(teacher_group!=null&&teacher_group!=""){
			int teacher_id=Integer.parseInt(teacher_group);
			sb.append(" and a.teacher_group = "+teacher_id+"");//根据教师编号查询
			
		}
		if(teacher_sex!=null&&teacher_sex!=""){
			sb.append(" and a.teacher_sex = '"+teacher_sex+"'");//根据教师性别查询
	}
		if(pagebean !=null){
			sb.append(" ORDER BY a.teacher_id limit "+pagebean.getStart()+","+pagebean.getRows());//分页查询
		}
		try {
			ps=con.prepareStatement(sb.toString());
			/*System.out.println(sb.toString());*/
			rs=ps.executeQuery();
			log.debug(ps);
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
		
		return json;	
	}
/**
 * @throws SQLException 
 * @杨振欣 添加新的教师信息
 *
 */
	public int teacherAdd(Connection con,Teacher teacher) throws SQLException{
		String sql="insert into y_teacher values(null,?,?,?,?,?,?,?,0)";
		PreparedStatement ps=null;
		int rows=0;
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1, teacher.getteacher_name().replace(" ", ""));
			ps.setString(2, teacher.getteacher_sex());
			ps.setInt(3, teacher.getteacher_group());
			ps.setString(4, teacher.getteacher_tel());
			ps.setString(5, teacher.getteacher_email());
			ps.setString(6, teacher.getteacher_card());
			ps.setString(7, teacher.getteacher_desc().replace(" ", ""));
			rows=ps.executeUpdate();
			log.debug(ps);
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
 * @杨振欣 修改教师信息
 *
 */
	public int teacherUpdate(Connection con,Teacher teacher) throws SQLException{
		String sql="update y_teacher set teacher_name=?,teacher_sex=?,teacher_group=?,teacher_tel=?,teacher_email=?,teacher_card=?,teacher_desc=? where teacher_id=?";
		PreparedStatement ps=null;
		int rows=0;
		try {
			ps=con.prepareStatement(sql);
			ps=con.prepareStatement(sql);
			ps.setString(1, teacher.getteacher_name().replace(" ", ""));
			ps.setString(2, teacher.getteacher_sex());
			ps.setInt(3, teacher.getteacher_group());
			ps.setString(4, teacher.getteacher_tel());
			ps.setString(5, teacher.getteacher_email());
			ps.setString(6, teacher.getteacher_card());
			ps.setString(7, teacher.getteacher_desc().replace(" ", ""));
			ps.setInt(8, teacher.getteacher_id());
			rows=ps.executeUpdate();
			log.debug(ps);
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
 * @杨振欣 删除指定的教师信息
 *
 */
	public int teacherDelete(Connection conn,String delIds) throws SQLException{
		String sql="update y_teacher set tea_sta=1 where teacher_id in("+delIds+")";
		int num=0;
		PreparedStatement ps =null;
		try {
			ps= conn.prepareStatement(sql);
			num=ps.executeUpdate();
			log.debug(ps);
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
		//返回影响的数据条数
		return num;
		
	}
	/**
	 * @throws SQLException 
	 * @杨振欣 统计查询后的数据条数
	 *
	 */
		 public int pageSum( Connection conn,String teacher_name,String teacher_group,String teacher_sex) throws SQLException{
			 StringBuffer sb=new StringBuffer("select COUNT(teacher_id) as total from y_teacher where tea_sta=0");
			 ResultSet rs=null;
			 int sum=0;
			 PreparedStatement ps=null;	
			 if(teacher_name!=null&&teacher_name!=""){
					sb.append(" and teacher_name like '%"+teacher_name.replace(" ", "")+"%'");
			
				}
				if(teacher_group!=null&&teacher_group!=""){
					int teacher_id=Integer.parseInt(teacher_group);
					sb.append(" and teacher_group = "+teacher_id+"");
					
				}
				if(teacher_sex!=null&&teacher_sex!=""){
					sb.append(" and teacher_sex = '"+teacher_sex+"'");
			}
			 try {
				ps=conn.prepareStatement(sb.toString());
				rs=ps.executeQuery();
				log.debug(ps);
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
			 //返回影响的数据条数
			return sum;
			 
		 }
/**
 * @throws SQLException 
 * @杨振欣 用来在添加薪资单时查看某一个教师编号是否存在
 *
 */
		 public String ListName_id(Connection con,String teacher_id) throws SQLException{
			 PreparedStatement ps=null;
			 String teacher_name=null;
			 String sql="SELECT teacher_name FROM y_teacher WHERE tea_sta=0 and teacher_id = '"+teacher_id+"'";
			 try {
				ps=con.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();
				log.debug(ps);
				while(rs.next()){
					teacher_name=rs.getString("teacher_name").replace(" ", "");
				}
				
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
			 
			return teacher_name;
			 
			 
		 }
 /**
  * @杨振欣 
  *
  */
/*		 public ResultSet ListName(Connection con){
			 PreparedStatement ps=null;
			 ResultSet rs=null;
			 String sql="select teacher_id,teacher_name from y_teacher";
			 try {
				ps=con.prepareStatement(sql);
				rs=ps.executeQuery();
			} catch (SQLException e) {
				log.error(e.getMessage(),e);
			}
			 
			return rs;
			 
		 }
		 */
 /**
  * @throws SQLException 
 * @杨振欣 给前台用户提供查询满足该身份证号码信息的教师编号
  *
  */
		 public long Searchid(Connection con,String card) throws SQLException{
			 PreparedStatement ps=null;
			 ResultSet rs=null;
			 long teacher_id = 0;
			 String sql="select teacher_id from y_teacher where tea_sta=0 and teacher_card='"+card+"'";
			 try {
				ps=con.prepareStatement(sql);
				rs=ps.executeQuery();
				log.debug(ps);
				while(rs.next()){
					teacher_id=rs.getInt("teacher_id");
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
			return teacher_id;
			 
		 }
		 
		 
		 /**
		  * @throws SQLException 
		 * @杨振欣 查询该身份证号是否存在，用来控制用户添加教师信息的唯一性
		  * */
		 public boolean ListTeacherCard(Connection con,String card) throws SQLException{
			 PreparedStatement ps=null;
			 ResultSet rs=null;
			 boolean have = true;
			 String sql="select teacher_id  from y_teacher where tea_sta=0 and teacher_card ='"+card+"'";
			 try {
				ps=con.prepareStatement(sql);
				rs=ps.executeQuery();
				log.debug(ps);
				if(rs.next()){
					have=false;
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
			return have;
		 }
		 
		/**
		 * @throws SQLException 
		 * @杨振欣 判断要删除的系部有没有所属教师 防止级联删除（删除教师后薪资单不能正常显示）
		 * 
		 * **/
		 public boolean ListTeacher(Connection con,String ids) throws SQLException{
			 PreparedStatement ps=null;
			 ResultSet rs=null;
			 boolean have = true;
			 String sql="select teacher_id  from y_teacher where tea_sta=0 and teacher_group in("+ids+")";
			 try {
				ps=con.prepareStatement(sql);
				rs=ps.executeQuery();
				log.debug(ps);
				if(rs.next()){
					have=false;
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
			return have;
		 }
}
