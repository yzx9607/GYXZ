package www.gyxz.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class LoginFillter
 */
public class LoginFillter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFillter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse res=(HttpServletResponse)response;
		res.setContentType("text/html;charset=utf-8");
		//改过滤器防止用户未登录就敲地址进入系统
		HttpSession session = req.getSession();
		//获取是否有用户名存在
		String names =(String)session.getAttribute("name");
		//如果不存在，提示未登录，并返回登录页面
		if(names==null||names==""){
			PrintWriter out = res.getWriter();
			out.write("<script>alert('对不起,你未登录!')</script>");
			res.setHeader("refresh", "0;url=login.jsp");
			/*res.sendRedirect("error.jsp");*/
			out.close();
		}else{
			//如果存在，交给下一个过滤器处理
		   chain.doFilter(request, response);
		   }
	}



	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
