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
import www.gyxz.dao.SalaryDao;
import www.gyxz.dao.TeacherDao;
import www.gyxz.filter.XzLisen;

/**
 * Servlet implementation class TeacherDelete
 */
public class TeacherDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeacherDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String  delIds=request.getParameter("delIds");
		PrintWriter out=response.getWriter();
		Logger log=Logger.getLogger("TeacherDelete");
		HttpSession session = request.getSession();
		String name=(String) session.getAttribute("name");
		Connection conn=null;
		//连接数据库
		conn=XzLisen.getConnection();
		JSONObject result=new JSONObject();
		TeacherDao teacher=new TeacherDao();
		SalaryDao sd=new SalaryDao();
		boolean flag=true;
		try {
			flag = sd.ListSalary(conn, delIds);
		}catch (SQLException e) {
			log.error(e.getMessage(),e);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}
		if(flag){
			int num=0;
			try {
				num = teacher.teacherDelete(conn, delIds);
			}catch (SQLException e) {
				log.error(e.getMessage(),e);
			}catch(Exception e){
				log.error(e.getMessage(),e);
			}
			if(num>0){
				log.debug("管理员"+name+"删除了薪资单"+delIds);
				result.put("success", "删除成功");
				result.put("delNums", num);
			}else{
				result.put("errorMsg", "删除失败");
			}
		}else{
			result.put("errorMsg", "请将要删除员工的所有薪资单全部删除");
		}
		out.print(result);
		XzLisen.releaseConnection(conn);
		out.close();
	}

}
