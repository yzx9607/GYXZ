package www.gyxz.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class AdminExit
 */
public class AdminExit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminExit() {
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
	 * @杨振欣 后台管理员退出
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//设置字符集
		Cookie[] cookies = request.getCookies();
		Logger log=Logger.getLogger("AdminExit");
		//删除cookie中存储的管理员登陆信息
		if (cookies != null && cookies.length > 0) {
			for (Cookie c : cookies) {
				if (c.getName().equals("checkname")) {
					c.setMaxAge(0);
					response.addCookie(c);
				} else if (c.getName().equals("checkpwd")) {
					c.setMaxAge(0);
					response.addCookie(c);
				}
			}
			HttpSession session = request.getSession();
			String name=(String) session.getAttribute("name");
			log.debug("管理员"+name+"退出了系统");
		//删除session中保存的管理员登录名
		    session.removeAttribute("name");
			PrintWriter out = response.getWriter();
			out.write("<script language=javascript>top.location.href='index.jsp'</script>");
		}
	}

}
