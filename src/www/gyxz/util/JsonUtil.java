package www.gyxz.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Date;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @杨振欣 将result数组转化为JSON数组工具类
 *
 */
public class JsonUtil {
	
 public static JSONArray formatRsJSONArray(ResultSet rs) throws SQLException{
	 JSONArray jsonarray=new JSONArray();
		ResultSetMetaData md=rs.getMetaData();
		int num=md.getColumnCount();
		while(rs.next()){
			JSONObject mapOfColValues=new JSONObject();
			for(int i=1;i<=num;i++){
				Object obj=rs.getObject(i);
				if(obj instanceof Date){
				mapOfColValues.put(md.getColumnName(i), DateUtil.formatDate((Date)obj, "yyyy-MM-dd"));
				}else if(obj instanceof Double){
					mapOfColValues.put(md.getColumnName(i), obj.toString());	
				}else{
					mapOfColValues.put(md.getColumnName(i), rs.getObject(i));	
				}
				}
			jsonarray.add(mapOfColValues);
		}
	return jsonarray;
 
 }
}
