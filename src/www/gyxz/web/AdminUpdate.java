package www.gyxz.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import www.gyxz.dao.AdminDao;
import www.gyxz.filter.XzLisen;

/**
 * Servlet implementation class AdminUpdate
 */
public class AdminUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminUpdate() {
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
	 * @杨振欣 修改管理员姓名和密码
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置字符集
		Logger log= Logger.getLogger("AdminUpdte");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//获取各项信息
		String username=request.getParameter("username");
		String name=request.getParameter("name");
		String password=request.getParameter("password1");
		PrintWriter out=response.getWriter();
		Connection con=XzLisen.getConnection();
		AdminDao admin=new AdminDao();
		//更新信息
		int num=0;
		try {
			num = admin.UpdatePwd(con, username, name, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}
		if (num > 0) {
			log.debug(username+"将管理员密码修改为："+password);
			out.write("<script>alert('密码已成功修改,请重新登陆!');</script>");
			response.setHeader("refresh", "1;url=AdminExit");

		} else {
			log.debug(username+"修改管理员密码失败");
			out.write("<script>alert('密码修改失败,1秒后返回!');</script>");
			response.setHeader("refresh", "1;url=password.jsp");
		}
		//连接池数据回收
		XzLisen.releaseConnection(con);
	}

}
