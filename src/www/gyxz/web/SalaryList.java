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

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import www.gyxz.bean.PageBean;
import www.gyxz.dao.SalaryDao;
import www.gyxz.filter.XzLisen;

/**
 * Servlet implementation class SalaryList
 */
public class SalaryList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SalaryList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @杨振欣 薪资单查询
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		//设置字符集
		String teacher_name=request.getParameter("teacher_name");
		String s_date=request.getParameter("s_date");
		String pay_status=request.getParameter("pay_status");
		String page=request.getParameter("page");
		String rows=request.getParameter("rows");
		Logger log=Logger.getLogger("SalaryList");
		HttpSession session = request.getSession();
		String name=(String) session.getAttribute("name");
		//获取分页数据
		PageBean pagebean=new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		PrintWriter out= response.getWriter();
		Connection con=XzLisen.getConnection();
		SalaryDao sd=new SalaryDao();
		JSONObject result=new JSONObject();
		//分页查询
		JSONArray jsonarray=null;
		try {
			jsonarray = sd.ListSalary(con, teacher_name, s_date, pagebean, pay_status);
		}catch (SQLException e) {
			log.error(e.getMessage(),e);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}
		int total=0;
		try {
			total = sd.pageSum(con, teacher_name, s_date, pay_status);
		}catch (SQLException e) {
			log.error(e.getMessage(),e);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}
		//统计数据条数
		result.put("rows",jsonarray);
		result.put("total", total);
		out.println(result);
		log.debug("管理员"+name+"查看了薪资单");
		//数据库信息回收
		XzLisen.releaseConnection(con);
	}

}
