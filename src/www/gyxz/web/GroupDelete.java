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

import www.gyxz.dao.GroupDao;
import www.gyxz.dao.TeacherDao;
import www.gyxz.filter.XzLisen;
import net.sf.json.JSONObject;


/**
 * Servlet implementation class GroupDelete
 */
public class GroupDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupDelete() {
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
	 * @杨振欣 系部信息删除处理
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置字符集
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Logger log=Logger.getLogger("GroupDelete");
		HttpSession session = request.getSession();
		String name=(String) session.getAttribute("name");
		//获取删除的系部编号
		String  delIds=request.getParameter("delIds");
		PrintWriter out=response.getWriter();
		//连接数据库
		Connection conn=XzLisen.getConnection();
		JSONObject result=new JSONObject();
		GroupDao gd=new GroupDao();
		TeacherDao td= new TeacherDao();
		//调用删除方法
		boolean flag=false;
		try {
			flag = td.ListTeacher(conn, delIds);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//判断该系部下是否有教师存在
		if(flag){
			int num=0;
			try {
				num = gd.GroupDelete(conn, delIds);
			} catch (SQLException e) {
				log.error(e.getMessage(),e);
			}catch(Exception e){
				log.error(e.getMessage(),e);
			}
			//判断是否删除成功
			if(num>0){
				log.debug("管理员"+name+"删除系部"+delIds+"成功");
				result.put("success", "删除成功");
				result.put("delNums", num);
			}else{
				log.debug("管理员"+name+"删除系部"+delIds+"失败");
				result.put("errorMsg", "删除失败");
			}
		}else{
			log.debug("管理员"+name+"删除系部"+delIds+"失败");
			//该系部下有教师存在的处理
			result.put("errorMsg", "请删除(或修改)该系部下所有教师后再进行删除操作");
		}
		out.print(result);
		//数据库连接池回收
		XzLisen.releaseConnection(conn);
		out.close();
	}

}
