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
 * Servlet implementation class UserLogin
 */
public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLogin() {
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
	 * @杨振欣 前台用户登陆处理
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置字符集
		Logger log=Logger.getLogger("UserLogin");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//获取登录名和密码
		String loginname=request.getParameter("username");
		String userpwd=request.getParameter("pwd");
		HttpSession session=request.getSession();
		UserDao ud=new UserDao();
		//获取数据库连接池
		Connection con=XzLisen.getConnection();
		if(loginname==null||loginname==""||loginname==null||loginname==""){
		//判断用户名或者密码输入是否为空
			session.setAttribute("checkNull", "false");
			response.sendRedirect("qiantai/qianlogin.jsp");
			return;
		}
		String realname="";
		try {
			realname = ud.ListUser(con, loginname, userpwd);
		}catch(SQLException e) {
			log.error(e.getMessage(),e);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}
		//查询结果不存在处理
		if(realname==null){
			session.setAttribute("flag", "false");
            response.setHeader("refresh","0;url=./qiantai/qianlogin.jsp");	
            log.debug(loginname+"登录失败");
			return;
		}
		else{
		//查询结果存在，把登录名存放在session
			session.setAttribute("loginname", loginname);
			session.setAttribute("realname",realname );
			response.sendRedirect("qiantai/json.jsp");
			log.debug(loginname+"登录成功");
		}
		//连接池数据回收
		XzLisen.releaseConnection(con);
	}

}
