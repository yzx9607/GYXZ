package www.gyxz.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import www.gyxz.bean.PageBean;
import www.gyxz.dao.GroupDao;
import www.gyxz.filter.XzLisen;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * Servlet implementation class ListGroup
 */
public class ListGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListGroup() {
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
	 * @杨振欣 查看所有系部信息
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//设置字符集
		String page=request.getParameter("page");
		String rows=request.getParameter("rows");
		Logger log=Logger.getLogger("ListGroup");
		//获取页码和数据量，作为分页
		String groupName=request.getParameter("searchGroupName");
		//条件查询数据获取
		Connection con=XzLisen.getConnection();
		//获取数据库连接
		PrintWriter out = response.getWriter();
		GroupDao gd=new GroupDao();	
		PageBean pagebean=new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		JSONObject result=new JSONObject();
		//查询系部信息
		JSONArray jsonarray =gd.ListGroup(con, groupName,pagebean);	
		//将数据送出去
		int total=0;
		try {
			total = gd.pageSum(con,groupName);
		} catch (SQLException e) {
			log.error(e.getMessage(),e);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}
		result.put("rows",jsonarray);//数据信息
		result.put("total", total);//数据条数
		out.println(result);
		//返回链接，回收连接对象
		XzLisen.releaseConnection(con);
	}

	

}
