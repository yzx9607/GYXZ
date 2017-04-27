package www.gyxz.qiantai;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import www.gyxz.filter.XzLisen;

/**
 * Servlet implementation class UpdateUser
 */
public class UpdateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUser() {
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
	 * @杨振欣 用来在前台用户进行信息修改时  在表单显示用户之前的数据
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Logger log=Logger.getLogger("UpdateUser");
		//设置字符集
		HttpSession session=request.getSession();
		//获取正在登陆的用户名
		String loginname=(String)session.getAttribute("loginname");
		UserDao ud=new UserDao();
		//从数据库连接池获取连接
		Connection con=XzLisen.getConnection();
		User user=null;
		try {
			user = ud.ListUsers(con, loginname);
		}catch (SQLException e) {
			log.error(e.getMessage(),e);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}
		session.setAttribute("upuser", user);
		log.debug(loginname+"获取要修改的信息");
		response.sendRedirect("qiantai/update.jsp");
		XzLisen.releaseConnection(con);
	}

}
