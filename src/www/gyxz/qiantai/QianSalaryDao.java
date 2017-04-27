package www.gyxz.qiantai;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import www.gyxz.util.JsonUtil;
import net.sf.json.JSONArray;

/**
 * @杨振欣 前台薪资信息查询
 *
 */

public class QianSalaryDao {
	/**
	 * @杨振欣 根据日期和教师编号查询薪资单
	 *
	 */
	public JSONArray ListSalary(Connection con,long id,String s_date){
		Logger log=Logger.getLogger("QianSalaryDao");
		//日志处理
		StringBuffer sb = new StringBuffer("SELECT s.id,s.s_date,s.teacher_id,t.teacher_name,s.base_pay,s.add_pay,s.bonus_pay,s.tax_pay,s.subsidy_pay,s.pay_status,s.sum_pay,s.other_pay2,s.other_pay1,s.deduction FROM y_salary AS s ,y_teacher AS t WHERE s.sarl_sta=0 and s.teacher_id = t.teacher_id and s.teacher_id = "+id+"");
		PreparedStatement ps =null;
		ResultSet rs=null;
		JSONArray json=null;
		//若果日期为空 则创建当前日期
		if(s_date!=null&&s_date!=""){
			sb.append(" and s.s_date like'"+s_date+"%'");//指定日期查询
		}
	try{
		ps=con.prepareStatement(sb.toString());
		rs=ps.executeQuery();
		log.debug(ps.toString());
		json=JsonUtil.formatRsJSONArray(rs);//ResultSet转json
	} catch (SQLException e) {
		log.error(e.getMessage(),e);
	}
		return json;
	}
	

}
