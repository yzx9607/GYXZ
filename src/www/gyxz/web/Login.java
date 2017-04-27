package www.gyxz.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import www.gyxz.bean.Admin;
import www.gyxz.dao.AdminDao;
import www.gyxz.dao.NoticeDao;
import www.gyxz.filter.XzLisen;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
	 * @杨振欣 管理员登陆处理
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("html/text;charset=utf-8");
		Logger log=Logger.getLogger("Login");
		Connection con=XzLisen.getConnection();
		String name=request.getParameter("userName");
		String password=request.getParameter("userPwd");
		HttpSession session = request.getSession();
		//判断用户名或者密码是否为空
		if(name==null||name==""||password==null||password==""){
			//判断用户名格式
			session.setAttribute("checkNull", "false");
			response.sendRedirect("login.jsp");
			return;
		}
		Admin admin=new Admin(0, name, password);
		AdminDao ad=new AdminDao();
		boolean have=false;
		try {
			have = ad.ListAdmin(con, admin);
		} catch (SQLException e) {
			log.error(e.getMessage(),e);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}
		//查询指定数据是否存在
		if(have){
			//判断是否记住密码
			String []remeber=request.getParameterValues("autoLogin");
			if(remeber!=null){
				//密码正确且记住密码框不为空，将用户输入的数据添加到Cookie中
				Cookie ckCookiename=new Cookie("checkname", name);
				Cookie ckCookiepwd=new Cookie("checkpwd", password);
				ckCookiename.setMaxAge(60*60*24);
				ckCookiepwd.setMaxAge(60*60*24);
				response.addCookie(ckCookiename);
				response.addCookie(ckCookiepwd);
			}
			session.setAttribute("name", name);
			NoticeDao nd=new NoticeDao();
			//查询公告信息
			ArrayList<Object> list=null;
			try {
				list = nd.ListNotice(con);
			} catch (SQLException e) {
				log.error(e.getMessage(),e);
			}catch(Exception e){
				log.error(e.getMessage(),e);
			}
			String notice=(String) list.get(0);
			int id=(int) list.get(1);
			//将公告信息存储到session供前台使用
			log.debug("管理员"+name+"登陆成功");
			session.setAttribute("notice", notice);
			session.setAttribute("notice_id", id);
			//转到主页面
			response.sendRedirect("admin-index.jsp");
			
		}else{
			session.setAttribute("flag", "false");
			response.sendRedirect("login.jsp");
			log.debug("管理员"+name+"登陆失败");
			return;
		}
		XzLisen.releaseConnection(con);
	}

}
