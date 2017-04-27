package www.gyxz.bean;

/**
 * 系部信息表
 * */

public class Group {
	private int group_id;//系部编号
	private String group_name;//系部名称
	private String group_desc;//系部简介(备注)
	/**
	 *  无参数的构造方法
	 *
	 */
	public Group(){
		
	}
	/**
	 *  有参数的构造方法
	 *
	 */
	public Group(int group_id, String group_name, String group_desc) {
		super();
		this.group_id = group_id;
		this.group_name = group_name;
		this.group_desc = group_desc;
	}

	
	/**
	 *  获取系部编号
	 *
	 */
	public int getGroup_id() {
		return group_id;
	}
	/**
	 *  设置系部编号
	 *
	 */
	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}
	/**
	 *  获取系部名称
	 *
	 */
	public String getGroup_name() {
		return group_name;
	}
	/**
	 *  设置系部编号
	 *
	 */
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}
	/**
	 *  获取系部简介
	 *
	 */
	public String getGroup_desc() {
		return group_desc;
	}
	/**
	 *  设置系部简介
	 *
	 */
	public void setGroup_desc(String group_desc) {
		this.group_desc = group_desc;
	}

	
}
