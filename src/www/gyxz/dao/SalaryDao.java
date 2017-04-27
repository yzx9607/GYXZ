package www.gyxz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;

import www.gyxz.bean.PageBean;
import www.gyxz.bean.Salary;
import www.gyxz.util.JsonUtil;
/**
 * @杨振欣 教师薪资信息管理类
 *
 */
public class SalaryDao {
/**
 * @杨振欣 日志处理
 *
 */
	private static Logger log=Logger.getLogger("salaryDao");
	/**
	 * @throws SQLException 
	 * @杨振欣 教师薪资单查询（包含条件查询）
	 *
	 */
	public JSONArray ListSalary(Connection con,String name,String s_date,PageBean pagebean,String pay_status) throws SQLException{
		StringBuffer sb = new StringBuffer("SELECT s.id,s.s_date,s.teacher_id,t.teacher_name,s.base_pay,s.add_pay,s.bonus_pay,s.tax_pay,s.subsidy_pay,s.pay_status,s.sum_pay,s.other_pay2,s.other_pay1,s.deduction FROM y_salary AS s ,y_teacher AS t WHERE s.sarl_sta=0 and s.teacher_id = t.teacher_id");
		PreparedStatement ps =null;
		ResultSet rs=null;
		JSONArray json=null;
		if(name!=null&&name!=""){
			sb.append(" and t.teacher_name like '%"+name.replace(" ", "")+"%'");//根据员工姓名查询
		}
		if(s_date!=null&&s_date!=""){
			sb.append(" and s.s_date like'"+s_date+"%'");//根据薪资日期查询
		}
		if(pay_status!=null&&pay_status!=""){
			sb.append(" and s.pay_status like '"+pay_status+"%'");//根据发放状态查询
		}
		if(pagebean !=null){
			sb.append(" ORDER BY s.s_date,s.teacher_id asc limit "+pagebean.getStart()+","+pagebean.getRows());//分页查询
		}
	try{
		ps=con.prepareStatement(sb.toString());
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
	 * @杨振欣 给教师添加薪资单
	 *
	 */
	public int AddSalary(Connection con,Salary s) throws SQLException{
		PreparedStatement ps=null;
		int rows=0;
		String sql="INSERT INTO y_salary VALUES(0,?,?,?,?,?,?,?,?,?,?,?,?,0)";
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1, s.getS_date());
			ps.setInt(2, s.getteacher_id());
			ps.setDouble(3, s.getBase_pay());
			ps.setDouble(4, s.getAdd_pay());
			ps.setDouble(5, s.getBonus_pay());
			ps.setDouble(6, s.getTax_pay());
			ps.setDouble(7, s.getSubsidy_pay());
			ps.setDouble(8, s.getDeduction());
			ps.setDouble(9, s.getOther_pay1());
			ps.setDouble(10, s.getOther_pay2());
			ps.setDouble(11, s.getSum_pay());
			ps.setString(12, s.getPay_status());
			rows=ps.executeUpdate();
			/*	System.out.println(ps.toString());*/
			//做调试用
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
	 * @杨振欣 更改教师薪资单信息
	 *
	 */
	public int UpdateSalary(Connection con,Salary s) throws SQLException{
		PreparedStatement ps=null;
		int rows=0;
		String sql="update y_salary set s_date=?"
				+ ",base_pay=?,add_pay=?,bonus_pay=?,tax_pay=?,subsidy_pay=?,"
				+ "deduction=?,other_pay1=?,other_pay2=?,sum_pay=?,pay_status=? where id=?";
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1, s.getS_date());
			ps.setDouble(2, s.getBase_pay());
			ps.setDouble(3, s.getAdd_pay());
			ps.setDouble(4, s.getBonus_pay());
			ps.setDouble(5, s.getTax_pay());
			ps.setDouble(6, s.getSubsidy_pay());
			ps.setDouble(7, s.getDeduction());
			ps.setDouble(8, s.getOther_pay1());
			ps.setDouble(9, s.getOther_pay2());
			ps.setDouble(10, s.getSum_pay());
			ps.setString(11, s.getPay_status());
			ps.setInt(12, s.getId());
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
	 * @杨振欣 批量删除薪资单
	 *
	 */
	public int SalaryDel(Connection conn,String delIds) throws SQLException{
		String sql="update y_salary set sarl_sta=1 where id in("+delIds+")";
		int num=0;
		PreparedStatement ps =null;
		try {
			ps=conn.prepareStatement(sql);
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
		return num;
		
	}
	/**
	 * @throws SQLException 
	 * @杨振欣 更改教师薪资发放状态（发放或者取消发放）
	 *
	 */
	public int SalaryPay(Connection conn,String delIds,int a) throws SQLException{
		String sql=null;
		PreparedStatement ps =null;
		if(a==0){
			sql="update y_salary set pay_status='已发放' where id in("+delIds+")";
			log.debug("发放了工资");
		}
		else{
			 sql="update y_salary set pay_status='未发放' where id in("+delIds+")";
			 log.debug("撤消了工资");
		}
		int num=0;
		try {
			ps=conn.prepareStatement(sql);
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
		//返回影响的行数
		return num;
		
	}
	/**
	 * @throws SQLException 
	 * @杨振欣 统计查询（包括条件查询）
	 *
	 */
	 public int pageSum(Connection con,String name,String s_date,String pay_status) throws SQLException{
		 StringBuffer sb=new StringBuffer("select COUNT(s.id) as total FROM y_salary AS s ,y_teacher AS t WHERE s.sarl_sta=0 and s.teacher_id = t.teacher_id ");
		//当条件执行后，数据量的显示
		 if(name!=null&&name!=""){
				sb.append(" and t.teacher_name like '%"+name.replace(" ", "")+"%'");//根据姓名查询
			}
			if(s_date!=null&&s_date!=""){
				sb.append(" and s.s_date like'"+s_date+"%'");//根据薪资日期查询
			}
			if(pay_status!=null&&pay_status!=""){//根据发放状态查询
				sb.append(" and s.pay_status like '"+pay_status+"%'");
			}
		 ResultSet rs=null;
		 int sum=0;
		 PreparedStatement ps=null;
		 try {
			ps=con.prepareStatement(sb.toString());
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
		 //返回总数据条数
		return sum;
		 
	 }
	 
	 /**
	  * @throws SQLException 
	 * @杨振欣 判断该员工所属的薪资单是否存在，防止删除员工后薪资单数据丢失
	  * */
	 public boolean ListSalary(Connection con,String ids) throws SQLException{
		 PreparedStatement ps=null;
		 ResultSet rs=null;
		 boolean have = true;
		 String sql="select teacher_id from y_salary where sarl_sta=0 and teacher_id in ("+ids+")";
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

