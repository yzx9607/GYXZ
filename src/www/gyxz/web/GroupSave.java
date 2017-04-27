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

import www.gyxz.bean.Group;
import www.gyxz.dao.GroupDao;
import www.gyxz.filter.XzLisen;
import net.sf.json.JSONObject;


/**
 * Servlet implementation class GroupSave
 */
public class GroupSave extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupSave() {
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
	 * @杨振欣 添加（修改）系部信息处理
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置字符集
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String groupid=request.getParameter("group_id");
		String groupname=request.getParameter("group_name");
		String groupdesc=request.getParameter("group_desc");
		Logger log=Logger.getLogger("GroupSave");
		//获取信息
		PrintWriter out=response.getWriter();
		GroupDao gd=new GroupDao();
		Connection con=XzLisen.getConnection();
		JSONObject result=new JSONObject();
		//根据传入的系部编号来判断是添加还是修改
		if(groupid!=null){
		//修改处理
			int id=Integer.parseInt(groupid);
			Group group=new Group(id, groupname, groupdesc);
			int num=0;
			try {
				num = gd.UpdateGroup(con, group);
			}  catch (SQLException e) {
				log.error(e.getMessage(),e);
			}catch(Exception e){
				log.error(e.getMessage(),e);
			}
			if(num>0){
				if(num>0){
					result.put("success", "true");
				}else{
					result.put("errorMsg", "修改失败");
				}
			}
		}else{
			//添加处理
			Group group=new Group(0, groupname, groupdesc);
			int num=0;
			try {
				num = gd.AddGroup(con, group);
			} catch (SQLException e) {
				log.error(e.getMessage(),e);
			}catch(Exception e){
				log.error(e.getMessage(),e);
			}
			if(num>0){
				if(num>0){
					result.put("success", "true");
				}else{
					result.put("errorMsg", "添加失败");
				}
			}
			
			}
		//给前台返回结果
		out.print(result);
		//数据库连接池回收数据
		XzLisen.releaseConnection(con);
		}
		
	}


