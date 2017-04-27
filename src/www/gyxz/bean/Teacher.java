package www.gyxz.bean;

/**
 * @杨振欣 教师信息类
 *
 */

public class Teacher {
	private int teacher_id;//教师编号
	private String teacher_name;//教师姓名
	private String teacher_sex;//教师性别
	private int teacher_group;//教师部门编号
	private String teacher_tel;//教师电话
	private String teacher_email;//教师邮箱
	private String teacher_card;//教师身份证号码
	private String teacher_desc;//教师备注
	/**
	 * @杨振欣 无参构造
	 *
	 */
	public Teacher() {
			super();
		}
	/**
	 * @杨振欣 有参构造
	 *
	 */
	public Teacher(int teacher_id, String teacher_name, String teacher_sex,
			int teacher_group, String teacher_tel, String teacher_email,
			String teacher_card, String teacher_desc) {
		super();
		this.teacher_id = teacher_id;
		this.teacher_name = teacher_name;
		this.teacher_sex = teacher_sex;
		this.teacher_group = teacher_group;
		this.teacher_tel = teacher_tel;
		this.teacher_email = teacher_email;
		this.teacher_card = teacher_card;
		this.teacher_desc = teacher_desc;
	}
	public int getteacher_id() {
		return teacher_id;
	}
	
	public void setteacher_id(int teacher_id) {
		this.teacher_id = teacher_id;
	}
	public String getteacher_name() {
		return teacher_name;
	}
	public void setteacher_name(String teacher_name) {
		this.teacher_name = teacher_name;
	}
	public String getteacher_sex() {
		return teacher_sex;
	}
	public void setteacher_sex(String teacher_sex) {
		this.teacher_sex = teacher_sex;
	}
	public int getteacher_group() {
		return teacher_group;
	}
	public void setteacher_group(int teacher_group) {
		this.teacher_group = teacher_group;
	}
	public String getteacher_tel() {
		return teacher_tel;
	}
	public void setteacher_tel(String teacher_tel) {
		this.teacher_tel = teacher_tel;
	}
	public String getteacher_email() {
		return teacher_email;
	}
	public void setteacher_email(String teacher_email) {
		this.teacher_email = teacher_email;
	}
	public String getteacher_card() {
		return teacher_card;
	}
	public void setteacher_card(String teacher_card) {
		this.teacher_card = teacher_card;
	}
	public String getteacher_desc() {
		return teacher_desc;
	}
	public void setteacher_desc(String teacher_desc) {
		this.teacher_desc = teacher_desc;
	}
	
}
