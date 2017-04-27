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
 * @杨振欣 检查身份证号码是否存在，用于前台注册和信息修改
 */
public class CheckCard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckCard() {
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
		response.setContentType("text/html;charset=utf-8");
		//设置编码格式
		//获取前台处过来的身份证号码
		String card=request.getParameter("card");
		Logger log=Logger.getLogger("CheckCard");
		PrintWriter out = response.getWriter();
		boolean flag=false;
		UserDao ud=new UserDao();
		Connection con=XzLisen.getConnection();
		//查询是否存在
		try {
			flag=ud.ListCard(con, card);
		}catch (SQLException e) {
			log.error(e.getMessage(),e);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}
		if(flag){
			out.print("no");
		}else{
			out.print("yes");
		}
		//将结果返回给前台
		XzLisen.releaseConnection(con);
		//连接池回收
	}

}
