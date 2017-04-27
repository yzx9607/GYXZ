package www.gyxz.filter;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import www.gyxz.util.ConnetFactory;

/**
 * 监听器
 *
 */
public class XzLisen implements ServletContextListener {
	public static ArrayList<Connection> pool;
	private static Logger log=Logger.getLogger("Xzlisen");
	ConnetFactory factory =new ConnetFactory();
    /**
     * Default constructor. 
     */
    public XzLisen() {
    	
    }
    /**
	 * 获取数据库连接
	 * @return
	 */
	public static Connection getConnection(){
		Connection conn=null;
		int len=pool.size();
		if(len>0){
			conn=pool.get(len-1);//获取最后一个
			pool.remove(conn);//标示为忙
		}
		return conn;
	}
	/**
	 * 返回链接，回收连接对象
	 * @return
	 */
	public static void releaseConnection(Connection conn){
		if(pool.contains(conn)){
			return;
		}
		pool.add(conn);
		
	}
	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
    	factory.close(pool);
		log.debug("连接池销毁");
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
    	//监听器启动
    			pool=factory.getPool();
    			log.debug("连接池创建成功");
    }	

}
