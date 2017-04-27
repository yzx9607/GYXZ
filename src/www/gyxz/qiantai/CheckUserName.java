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
 * @杨振欣 判断用户名是否存在，用于前台注册和用户信息修改
 */
public class CheckUserName extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckUserName() {
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//判断注册时用户名是否存在
		response.setContentType("text/html;charset=utf-8");
		String name=request.getParameter("user");
		PrintWriter out = response.getWriter();
		Logger log=Logger.getLogger("CheckUserName");
		boolean flag=false;
		UserDao ud=new UserDao();
		Connection con=XzLisen.getConnection();;
		try {
			flag=ud.ListName(con, name);
		}catch (SQLException e) {
			log.error(e.getMessage(),e);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}
		//将查询结果打印给前台
		if(flag){
			out.print("no");
		}else{
			out.print("yes");
		}
		//连接池回收
		XzLisen.releaseConnection(con);
	}

}
