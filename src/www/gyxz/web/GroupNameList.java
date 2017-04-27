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

import www.gyxz.dao.GroupDao;
import www.gyxz.filter.XzLisen;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class GroupNameList
 */
public class GroupNameList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupNameList() {
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
	 * @杨振欣 遍历系部数据，用来做员工查询或增加修改信息时 的系部信息提醒
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Logger log=Logger.getLogger("GroupNameList");
		//该servlet用来在做员工管理的下拉列表
		Connection con=XzLisen.getConnection();
		GroupDao gd=new GroupDao();
		JSONObject result=new JSONObject();
		JSONArray jsonarray =new JSONArray();
		//将查询过来的所有项目组的名称以及Id的Result数组转换成JSON数组
		result.put("group_id", "");
		result.put("group_name", "---请选择---");
		//添加一个为空的提示列
		jsonarray.add(result);
		try {
			jsonarray.addAll(gd.ListGroupName(con));
		} catch (SQLException e) {
			log.error(e.getMessage(),e);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}
		PrintWriter out = response.getWriter();
		out.print(jsonarray);
		//将结果转出去
		XzLisen.releaseConnection(con);
	}
	

}
