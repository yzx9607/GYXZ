package www.gyxz.qiantai;

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

import www.gyxz.filter.XzLisen;

/**
 * Servlet implementation class UserSave
 */
public class UserSave extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserSave() {
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
	 * @杨振欣 前台教师注册信息处理
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置字符集
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		Logger log=Logger.getLogger("UserSave");
		//获取教师输入的各项信息
	 	String userid=request.getParameter("userid");
		String newname=request.getParameter("newname");
		String newpwd=request.getParameter("newpwd");
		String newrealname=request.getParameter("newrealname");
		String newphone=request.getParameter("newphone");
		String newcard=request.getParameter("newcard");
		HttpSession session=request.getSession();
		//获取数据库连接池
		Connection con=XzLisen.getConnection();
		UserDao ud=new UserDao();
		//根据用户是否传入教师登陆id来判断是增加还是修改
		if(userid==null||userid==""){
			User user=new User(0, newname, newpwd, newrealname, newphone, newcard);
			int rows=0;
			try {
				rows = ud.UserReg(con, user);
			}catch (SQLException e) {
				log.error(e.getMessage(),e);
			}catch(Exception e){
				log.error(e.getMessage(),e);
			}
			if (rows > 0) {
				log.debug(newname+"用户注册成功");
				out.write("<script>alert('注册成功,请牢记密码!');</script>");
				response.setHeader("refresh", "1;url=qiantai/qianlogin.jsp");
				}
			else{
				log.debug(newname+"用户注册失败");
				out.write("<script>alert('注册失败,请重新注册!');</script>");
				response.setHeader("refresh", "1;url=qiantai/reg.jsp");
			}	
		}else{
			User user=new User(Integer.parseInt(userid), newname, newpwd, newrealname, newphone, newcard);
			int rows=0;
			try {
				rows = ud.UserUpadte(con, user);
			}catch (SQLException e) {
				log.error(e.getMessage(),e);
			}catch(Exception e){
				log.error(e.getMessage(),e);
			}
			if (rows > 0) {
				log.debug(newname+"用户修改成功");
				session.removeAttribute("loginname");
				out.write("<script>alert('修改成功,请重新登录!');</script>");
				response.setHeader("refresh", "1;url=qiantai/qianlogin.jsp");
				}
			else{
				log.debug(newname+"用户修改失败");
				out.write("<script>alert('修改失败,请重新修改!');</script>");
				response.setHeader("refresh", "1;url=qiantai/update.jsp");
			}
		
		}
		//数据库连接池回收
		XzLisen.releaseConnection(con);
	}

}
