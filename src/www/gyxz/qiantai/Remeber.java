package www.gyxz.qiantai;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import www.gyxz.filter.XzLisen;


/**
 * Servlet implementation class Remeber
 */
public class Remeber extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Remeber() {
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
	 * @杨振欣 实现前台用户找回密码功能更
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//设置字符集
		PrintWriter out=response.getWriter();
		Logger log=Logger.getLogger("Remeber");
		//获取用户提供的用户名，新密码，以及注册时使用的手机号和身份证号码
		String newname=request.getParameter("newname");
		String newpwd=request.getParameter("newpwd");
		String newphone=request.getParameter("newphone");
		String newcard=request.getParameter("newcard");
		UserDao ud=new UserDao();
		Connection con=XzLisen.getConnection();
		//根据查询数据
		int rows=0;
		try {
			rows = ud.UserRemeber(con, newpwd, newname, newphone, newcard);
		}catch (SQLException e) {
			log.error(e.getMessage(),e);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}
		//如果一个条件出错，密码将找回失败，将处理结果送给前台
		if (rows > 0) {
			out.write("<script>alert('找回成功,请牢记密码"+newpwd+"!');</script>");
			log.debug(newname+"成功找回密码");
			response.setHeader("refresh", "1;url=qiantai/qianlogin.jsp");
			}
		else{
			out.write("<script>alert('找回密码失败,提供的证据不足!');</script>");
			log.debug(newname+"找回密码失败");
			response.setHeader("refresh", "1;url=qiantai/pwd.jsp");
		}
		//连接池回收
		XzLisen.releaseConnection(con);
	}

}
