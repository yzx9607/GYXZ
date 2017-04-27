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
import www.gyxz.filter.XzLisen;

/**
 * Servlet implementation class SalaryDelete
 */
public class SalaryDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SalaryDelete() {
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
	 * @杨振欣 删除教师薪资单
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		//设置字符集
		Logger log=Logger.getLogger("SalaryList");
		HttpSession session = request.getSession();
		String name=(String) session.getAttribute("name");
		String id=request.getParameter("delIds");
		//获取删除的薪资单编号集合
		PrintWriter out=response.getWriter();
		Connection con=XzLisen.getConnection();
		SalaryDao sd=new SalaryDao();
		JSONObject result=new JSONObject();
		int num=0;
		try {
			num = sd.SalaryDel(con, id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//判断是否删除成功
				if(num>0){
					log.debug("管理员"+name+"删除了"+id+"薪资单成功");
					result.put("success", "true");
					result.put("delNums", num);
				}else{
					log.debug("管理员"+name+"删除"+id+"薪资单失败");
					result.put("errorMsg", "删除失败");
				}
		out.print(result);//返回结果
		//数据库连接池数据回收
		XzLisen.releaseConnection(con);
	}

}
