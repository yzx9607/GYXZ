package www.gyxz.bean;


public class Salary {
	private int id;
	private String s_date;//日期
	private int teacher_id;//员工编号
	private String teacher_name;//员工姓名
	private double base_pay;//基础工资
	private double add_pay;//加班工资
	private double bonus_pay;//奖金
	private double tax_pay;//个人所得税
	private double subsidy_pay;//补贴
	private double deduction;//扣除工资
	private double other_pay1;//预留项目
	private double other_pay2;//预留项目
	private String pay_status;//状态
	public int getteacher_id() {
		return teacher_id;
	}
	public void setteacher_id(int teacher_id) {
		this.teacher_id = teacher_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getS_date() {
		return s_date;
	}
	public void setS_date(String s_date) {
		this.s_date = s_date;
	}
	public String getteacher_name() {
		return teacher_name;
	}
	public void setteacher_name(String teacher_name) {
		this.teacher_name = teacher_name;
	}
	public double getBase_pay() {
		return base_pay;
	}
	public void setBase_pay(double base_pay) {
		this.base_pay = base_pay;
	}
	public double getAdd_pay() {
		return add_pay;
	}
	public void setAdd_pay(double add_pay) {
		this.add_pay = add_pay;
	}
	public double getBonus_pay() {
		return bonus_pay;
	}
	public void setBonus_pay(double bonus_pay) {
		this.bonus_pay = bonus_pay;
	}
	public double getTax_pay() {
		return tax_pay;
	}
	public void setTax_pay(double tax_pay) {
		this.tax_pay = tax_pay;
	}
	public double getSubsidy_pay() {
		return subsidy_pay;
	}
	public void setSubsidy_pay(double subsidy_pay) {
		this.subsidy_pay = subsidy_pay;
	}
	public double getDeduction() {
		return deduction;
	}
	public void setDeduction(double deduction) {
		this.deduction = deduction;
	}
	public double getOther_pay1() {
		return other_pay1;
	}
	public void setOther_pay1(double other_pay1) {
		this.other_pay1 = other_pay1;
	}
	public double getOther_pay2() {
		return other_pay2;
	}
	public void setOther_pay2(double other_pay2) {
		this.other_pay2 = other_pay2;
	}
	/**
	 * 工资的算法，基础工资+加班工资+奖金-个人所得税+补贴
	 * 
	 * 
	 *      
	 */
	public double getSum_pay() {
		return this.base_pay+this.add_pay+this.bonus_pay-this.tax_pay+this.subsidy_pay-this.deduction+this.other_pay1+this.other_pay2;
	}
	public double setSum_pay(double sum_pay) {
		return (this.base_pay+this.add_pay+this.bonus_pay-this.tax_pay+this.subsidy_pay-this.deduction+this.other_pay1+this.other_pay2);
	}
	public String getPay_status() {
		return pay_status;
	}
	public void setPay_status(String pay_status) {
		this.pay_status = pay_status;
	}
	public Salary(int id, String s_date, String teacher_name, double base_pay,
			double add_pay, double bonus_pay, double tax_pay,
			double subsidy_pay, double deduction, double other_pay1,
			double other_pay2, String pay_status,int teacher_id) {
		super();
		this.id = id;
		this.s_date = s_date;
		this.teacher_name = teacher_name;
		this.base_pay = base_pay;
		this.add_pay = add_pay;
		this.bonus_pay = bonus_pay;
		this.tax_pay = tax_pay;
		this.subsidy_pay = subsidy_pay;
		this.deduction = deduction;
		this.other_pay1 = other_pay1;
		this.other_pay2 = other_pay2;
		this.pay_status = pay_status;
		this.teacher_id = teacher_id;
	}
	
}