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
 * 发放工资
 */
public class SalaryPay extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SalaryPay() {
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
	 * @杨振欣  工资单的发放与撤销
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		Logger log=Logger.getLogger("SalaryPay");
		HttpSession session = request.getSession();
		String name=(String) session.getAttribute("name");
		//设置字符集
		String id=request.getParameter("delIds");
		//发放的薪资的薪资单编号集合
		String a=request.getParameter("a");
		//判断是已发放还是未发放
		PrintWriter out=response.getWriter();
		Connection con=XzLisen.getConnection();
		SalaryDao sd=new SalaryDao();
		JSONObject result=new JSONObject();
		int num=0;
		try {
			num = sd.SalaryPay(con, id, Integer.parseInt(a));
		} catch (NumberFormatException e) {
			log.error(e.getMessage(),e);
		}catch (SQLException e) {
			log.error(e.getMessage(),e);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}
		//判断是否操作成功
				if(num>0){
					
					result.put("success", "true");
					result.put("delNums", num);
					log.debug("管理员"+name+"操作工资成功");
				}else{
					result.put("errorMsg", "工资操作失败");
					log.debug("管理员"+name+"操作工资失败");
				}
				out.print(result);
		//连接池回收数据
		XzLisen.releaseConnection(con);
	}
	}


