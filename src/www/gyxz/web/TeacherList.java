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
import www.gyxz.dao.TeacherDao;
import www.gyxz.filter.XzLisen;

/**
 * Servlet implementation class TeacherList
 */
public class TeacherList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeacherList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @杨振欣 教师信息查询
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置字符集
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		String page=request.getParameter("page");
		String rows=request.getParameter("rows");
		Logger log=Logger.getLogger("TeacherList");
		HttpSession session = request.getSession();
		String name=(String) session.getAttribute("name");
		//分页查询条件
		String teacher_name=request.getParameter("stuName");//教师姓名
		String groupId=request.getParameter("groupId");//所属系部
		String StuSex=request.getParameter("StuSex");//性别
		//条件查询条件
		PageBean pagebean=new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		Connection con=XzLisen.getConnection();
		TeacherDao teacher=new TeacherDao();
		JSONObject result=new JSONObject();
		JSONArray jsonarray=null;
		try {
			jsonarray = teacher.teacherList(con, teacher_name, groupId,StuSex,pagebean);
		}catch (SQLException e) {
			log.error(e.getMessage(),e);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}
		int total=0;
		try {
			total = teacher.pageSum(con, teacher_name, groupId, StuSex);
		}catch (SQLException e) {
			log.error(e.getMessage(),e);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}
		result.put("rows",jsonarray);
		result.put("total", total);
		//返回结果
		out.println(result);
		log.debug("管理员"+name+"查看了所有薪资单");
		//数据库连接池回收
		XzLisen.releaseConnection(con);
	
	}

}
