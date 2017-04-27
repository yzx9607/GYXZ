package www.gyxz.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.log4j.Logger;


/**
 * @杨振欣 数据库连接池
 *
 */
public class ConnetFactory {

	private static int size;
	private static String url=null;//链接字符（数据类型，位置，端口号，数据库）
	private static String driverClass=null;//主驱动类
	private static String username=null;
	private static String password=null;
	private static Logger log=Logger.getLogger("ConnetFactory");
	//读
	private static ArrayList<Connection> pool;
	static{
		try {
			init();
		} catch (IOException e) {
			log.error(e.getMessage(),e);
		}
	}

	public ConnetFactory(){
		super();
	}
	
	/**
	 * 初始化连接池
	 * @throws IOException 
	 */
	
	private static void init() throws IOException{
		Properties p=new Properties();
		p.load(ConnetFactory.class.getClassLoader().getResourceAsStream("jdbc.properties"));
		driverClass=p.getProperty("driverclass");
		url=p.getProperty("url");
		username=p.getProperty("name");
		password=p.getProperty("password");
		size=Integer.parseInt(p.getProperty("size"));
		pool=new ArrayList<Connection>(size);
		try {
			for (int i = 0; i < size; i++) {
				Class.forName(driverClass);
				Connection conn=DriverManager.getConnection(url,username,password);
				pool.add(conn);
			}
		}catch (Exception e) {
			log.error(e.getMessage(),e);
		}
	}
	
	public  ArrayList<Connection> getPool() {
		return pool;
	}
	/**
	 * @杨振欣 关闭连接
	 *
	 */
	public void close( ArrayList<Connection> pool){
		try {
			for (int i = 0; i < size; i++) {
				Connection conn=pool.get(i);
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
