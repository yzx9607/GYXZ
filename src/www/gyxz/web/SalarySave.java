package www.gyxz.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import net.sf.json.JSONObject;
import www.gyxz.bean.Salary;
import www.gyxz.dao.SalaryDao;
import www.gyxz.dao.TeacherDao;
import www.gyxz.filter.XzLisen;

/**
 * Servlet implementation class SalarySave
 */
public class SalarySave extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SalarySave() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @杨振欣 薪资单修改或者保存
	 *     
	 */
	protected void doPost(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		Logger log=Logger.getLogger("SalarySave");
		//设置字符集
		SalaryDao sd = new SalaryDao();
		JSONObject result = new JSONObject();
		TeacherDao teacher=new TeacherDao();
		PrintWriter out = response.getWriter();
		//获取连接
		Connection con = XzLisen.getConnection();
		String id = request.getParameter("id");
		String teacher_id=request.getParameter("teacher_id");//教师号
		String teacher_name="";
		try {
			teacher_name = teacher.ListName_id(con, teacher_id);
		}catch (SQLException e) {
			log.error(e.getMessage(),e);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}
		// 根据id获取教师姓名
		String s_date = request.getParameter("s_date");// 月份
		String base_pay = request.getParameter("base_pay");// 基本工资
		String add_pay = request.getParameter("add_pay");// 加班工资
		String bonus_pay = request.getParameter("bonus_pay");// 奖金
		String tax_pay = request.getParameter("tax_pay");// 个人所得税
		String subsidy_pay = request.getParameter("subsidy_pay");// 补贴
		String dedu = request.getParameter("deduction");// 扣除工资
		double deduction=Double.parseDouble(dedu);// 扣除工资
		String other_pay1 = request.getParameter("other_pay1");// 其他1
		String other_pay2 = request.getParameter("other_pay2");// 其他2
		String pay_status = request.getParameter("pay_status");
		HttpSession session = request.getSession();
		String name=(String) session.getAttribute("name");
		//获取薪资单信息
	//判断要添加（修改）薪资的教师号是否存在
		//判断添加还是修改
		if (id != null) {
			//修改薪资单
			Salary salary = new Salary(Integer.parseInt(id), s_date,
					teacher_name, Double.parseDouble(base_pay),
					Double.parseDouble(add_pay), Double.parseDouble(bonus_pay),
					Double.parseDouble(tax_pay),
					Double.parseDouble(subsidy_pay), deduction,
					Double.parseDouble(other_pay1),
					Double.parseDouble(other_pay2), pay_status,0);
			int rows=0;
			try {
				rows = sd.UpdateSalary(con, salary);
			}catch (SQLException e) {
				log.error(e.getMessage(),e);
			}catch(Exception e){
				log.error(e.getMessage(),e);
			}
			if (rows > 0) {
				if (rows > 0) {
					log.debug(name+"修改了薪资单");
					result.put("success", "修改成功");
				} else {
					result.put("errorMsg", "修改失败");
				}
			}

		} else {
			if(teacher_name!=null){
			//添加薪资单
			Salary salary = new Salary(0, s_date, teacher_name,
					Double.parseDouble(base_pay), Double.parseDouble(add_pay),
					Double.parseDouble(bonus_pay), Double.parseDouble(tax_pay),
					Double.parseDouble(subsidy_pay), deduction,
					Double.parseDouble(other_pay1),
					Double.parseDouble(other_pay2), pay_status,Integer.parseInt(teacher_id));
			int rows=0;
			try {
				rows = sd.AddSalary(con, salary);
			}catch (SQLException e) {
				log.error(e.getMessage(),e);
			}catch(Exception e){
				log.error(e.getMessage(),e);
			}
			if (rows > 0) {
				if (rows > 0) {
					log.debug(name+"添加了薪资单");
					result.put("success", "添加成功");
				} else {
					result.put("errorMsg", "添加失败");
				}
			}

		}
		
		else{
			
			result.put("errorMsg", "该教师号不存在");
		
		}
		}
		out.print(result);
		XzLisen.releaseConnection(con);
		/*
		 * System.out.println(salary.getId()+"\t"+salary.getteacher_name()+"\t"+salary
		 * .getBase_pay()+"\n"+salary.getAdd_pay()+"\t"+
		 * salary.getBonus_pay()+"\t"
		 * +salary.getTax_pay()+"\t"+salary.getSubsidy_pay()+"\t"+
		 * salary.getDeduction
		 * ()+"\n"+salary.getOther_pay1()+"\t"+salary.getOther_pay2()+"\t"
		 * +salary.getSum_pay()+"\t"+salary.getPay_status());
		 */

	}

}
