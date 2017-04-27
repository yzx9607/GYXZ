package www.gyxz.bean;
/**
 * @author 分页管理
 *
 */
public class PageBean {
/**
 * 页码
 *
 */
private int page;
/**
 * 数据条数
 *
 */
private int rows;
/**
 *数据开始值
 *
 */
private int start;

public int getPage() {
	return page;
}
public void setPage(int page) {
	this.page = page;
}
public int getRows() {
	return rows;
}
public void setRows(int rows) {
	this.rows = rows;
}
/**
 * @yang 获取页面开始数据初始值
 *
 */
public int getStart() {
	start=(page-1)*this.rows;
	return start;
}
/**
 * @yang 设置页面开始数据初始值
 *
 */
public void setStart(int start) {
	this.start = (page-1)*rows;
}
/**
 * @yang 获取页面开始数据初始值
 *
 */
public PageBean(int page, int rows) {
	super();
	this.page = page;
	this.rows = rows;
}

}
