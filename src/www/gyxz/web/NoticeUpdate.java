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

import www.gyxz.dao.NoticeDao;
import www.gyxz.filter.XzLisen;

/**
 * Servlet implementation class NoticeUpdate
 */
public class NoticeUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeUpdate() {
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
	 * @杨振欣 公告信息处理（添加或修改）
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//设置字符集
		String notice=request.getParameter("newNotice");
		String id=request.getParameter("notice_id");
		int notice_id=Integer.parseInt(id);
		Logger log=Logger.getLogger("NoticeUpdate");
		HttpSession session = request.getSession();
		String name=(String) session.getAttribute("name");
		//获取公告信息
		PrintWriter out=response.getWriter();
		Connection con=XzLisen.getConnection();
		NoticeDao nd=new NoticeDao();
		if(notice_id==0){
		//判断添加还是修改
				int num=0;
				try {
					num = nd.AddNotice(con, notice);
				}catch (SQLException e) {
					log.error(e.getMessage(),e);
				}catch(Exception e){
					log.error(e.getMessage(),e);
				}
				if (num > 0) {
					log.debug("管理员"+name+"添加新公告成功");
					out.write("<script>alert('公告添加成功!请重新登陆查看!');</script>");
					response.setHeader("refresh", "1;url=login.jsp");

				} else {
					log.debug("管理员"+name+"添加新公告成功");
					out.write("<script>alert('公告添加失败,1秒后返回!');</script>");
					response.setHeader("refresh", "1;url=update.jsp");
				}
				
		}else{
		int num=0;
		try {
			num = nd.UpdateNotice(con, notice,notice_id);
		}catch (SQLException e) {
			log.error(e.getMessage(),e);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}
		if (num > 0) {
			log.debug("管理员"+name+"修改新公告成功");
			out.write("<script>alert('公告已成功修改!请重新登陆查看!');</script>");
			response.setHeader("refresh", "1;url=login.jsp");

		} else {
			log.debug("管理员"+name+"修改新公告失败");
			out.write("<script>alert('公告修改失败,1秒后返回!');</script>");
			response.setHeader("refresh", "1;url=update.jsp");
		}
	}	
		//返回链接，回收连接对象
		XzLisen.releaseConnection(con);
	}
}
