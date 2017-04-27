package www.gyxz.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @杨振欣 日期类转换
 *
 */
public class DateUtil {
	/**
	 * @杨振欣 Date类型转换为String类型
	 *
	 */
public static String formatDate(Date date,String format){
	String result="";
	SimpleDateFormat sdf=new SimpleDateFormat(format);
	if(date!=null){
		result=sdf.format(date);
	}
	return result;
}
/**
 * @杨振欣 String类型转换为Date类型
 *
 */
public static Date formatString (String str,String format) throws ParseException{
	SimpleDateFormat sdf=new SimpleDateFormat(format);
	return sdf.parse(str);
	
}
}
