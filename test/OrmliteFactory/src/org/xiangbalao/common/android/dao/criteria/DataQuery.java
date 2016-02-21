package org.xiangbalao.common.android.dao.criteria;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DataQuery<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<Where<T>> where = new ArrayList<Where<T>>();
	private List<String> selectColumns;
	private List<Order<T>> order = new ArrayList<Order<T>>();

	private Page page = null;

	public List<Where<T>> getWhere() {
		return this.where;
	}

	public void setSelect(List<String> columns) {
		this.selectColumns = columns;
	}

	public List<String> getSelect() {
		return this.selectColumns;
	}

	public void addWhere(Where<T> w) {
		this.where.add(w);
	}

	public void addOrder(Order<T> o) {
		this.order.add(o);
	}

	public List<Order<T>> getOrder() {
		return this.order;
	}

	public Page getPage() {
		return this.page;
	}

	public void setPage(Page page) {
		this.page = page;
	}
}