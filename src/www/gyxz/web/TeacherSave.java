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

import www.gyxz.bean.Teacher;
import www.gyxz.dao.TeacherDao;
import www.gyxz.filter.XzLisen;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class StaffSava
 */
public class TeacherSave extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TeacherSave() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @杨振欣  教师信息保存（添加和修改）
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//设置字符集
		PrintWriter out = response.getWriter();
		String id = request.getParameter("id");
		String teacher_name = request.getParameter("teacher_name");
		String teacher_sex = request.getParameter("teacher_sex");
		String teacher_group = request.getParameter("teacher_group");
		int groupId = Integer.parseInt(teacher_group);
		String teacher_email = request.getParameter("teacher_email");
		String teacher_tel = request.getParameter("teacher_tel");
		String teacher_desc = request.getParameter("teacher_desc");
		String teacher_card = request.getParameter("teacher_card");
		String teacher_cards = request.getParameter("teacher_cards");
		Logger log=Logger.getLogger("TeacherSave");
		HttpSession session = request.getSession();
		String name=(String) session.getAttribute("name");
		//获取教师信息
		JSONObject result = new JSONObject();
		TeacherDao td = new TeacherDao();
		Connection con = XzLisen.getConnection();
		boolean flag=false;
		try {
			flag = td.ListTeacherCard(con, teacher_card);
		}catch (SQLException e) {
			log.error(e.getMessage(),e);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}
		if (id != null) {
			// 判断是修改还是添加
			// 修改方法
			Teacher teacher = new Teacher(Integer.parseInt(id), teacher_name,
					teacher_sex, groupId, teacher_tel, teacher_email,
					teacher_card, teacher_desc);
			if (teacher_card.equals(teacher_cards)) {
				// 判断修改后身份证号码是否与修改前一致，如果一致，则不做判断
				int num=0;
				try {
					num = td.teacherUpdate(con, teacher);
				}catch (SQLException e) {
					log.error(e.getMessage(),e);
				}catch(Exception e){
					log.error(e.getMessage(),e);
				}
				if (num > 0) {
					if (num > 0) {
						log.debug("管理员"+name+"修改了教师"+id);
						result.put("success", "修改成功");
					} else {
						result.put("errorMsg", "修改失败");
					}
				}
			} else {
				// 修改后身份证号码改变，判断修改后的数据库是否存在
				if (flag) {
					int num=0;
					try {
						num = td.teacherUpdate(con, teacher);
					}catch (SQLException e) {
						log.error(e.getMessage(),e);
					}catch(Exception e){
						log.error(e.getMessage(),e);
					}
					if (num > 0) {
						if (num > 0) {
							log.debug("管理员"+name+"修改了教师"+id);
							result.put("success", "修改成功");
						} else {
							result.put("errorMsg", "修改失败");
						}
					}
				} else {
					log.debug("管理员"+name+"修改教师失败");
					result.put("errorMsg", "该身份证号码已经存在");
				}
			}
		} else {
			// 添加方法
			Teacher teacher = new Teacher(0, teacher_name, teacher_sex,
					groupId, teacher_tel, teacher_email, teacher_card,
					teacher_desc);
			if (flag) {
				// 判断身份证号码是否存在
				int num=0;
				try {
					num = td.teacherAdd(con, teacher);
				}catch (SQLException e) {
					log.error(e.getMessage(),e);
				}catch(Exception e){
					log.error(e.getMessage(),e);
				}
				if (num > 0) {
					if (num > 0) {
						log.debug("管理员"+name+"添加了教师"+teacher_name);
						result.put("success", "添加成功");
					} else {
						result.put("errorMsg", "添加失败");
					}
				}
			} else {//身份证号存在的情况
				log.debug("管理员"+name+"添加教师失败");
				result.put("errorMsg", "该身份证号码已经存在");
			}
		}
		out.print(result);
		//数据库连接池
		XzLisen.releaseConnection(con);
	}

}
