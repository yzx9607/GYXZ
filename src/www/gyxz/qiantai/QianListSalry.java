package www.gyxz.qiantai;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import www.gyxz.dao.TeacherDao;
import www.gyxz.filter.XzLisen;

/**
 * Servlet implementation class QianListSalry
 */
public class QianListSalry extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QianListSalry() {
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
	 * @杨振欣 用作前台用户查询自己薪资单
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//设置字符集
		HttpSession session=request.getSession();
		PrintWriter out=response.getWriter();
		Connection con=XzLisen.getConnection();
		Logger log=Logger.getLogger("QianListSalary");
		//从session中获取用户登录名和指定日期
		String name=(String) session.getAttribute("loginname");
		String newdate=request.getParameter("newdate");
		//如果日期为空，创建当前日期
		if(newdate==""||newdate==null){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM"); 
			newdate=formatter.format(new Date());
		}
		//根据登录名查找登录用户身份证号码
		UserDao ud=new UserDao();
		String card="";
		try {
			card = ud.SearchCard(con, name);
		}catch (SQLException e) {
			log.error(e.getMessage(),e);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}
		TeacherDao teacher=new TeacherDao();
		//根据身份证号码查询教师编号
		long teacher_id=0;
		try {
			teacher_id = teacher.Searchid(con, card);
		}catch (SQLException e) {
			log.error(e.getMessage(),e);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}
		QianSalaryDao sd=new QianSalaryDao();
		session.setAttribute("date", newdate);
		JSONObject result=new JSONObject();
		//根据日期和编号查找出该教师的薪资单
		JSONArray jsonarray =sd.ListSalary(con, teacher_id, newdate);;
		log.debug(name+"查询了自己的薪资");
		result.put("salary",jsonarray);
		result.put("date", newdate);
		out.print(result);
		//将薪资单和date放在json数组中送给前台
		out.close();
		//连接池回收
		XzLisen.releaseConnection(con);
	}

}
