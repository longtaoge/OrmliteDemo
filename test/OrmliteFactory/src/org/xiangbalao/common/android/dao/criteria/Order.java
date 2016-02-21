package org.xiangbalao.common.android.dao.criteria;

import java.io.Serializable;

import javax.persistence.Column;

import android.util.Log;

public class Order<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	private String field = "";
	private String property;
	private OrderType order;

	public Order(Class<T> class1, String property, OrderType order) {
		Column col = null;
		try {
			col = (Column) class1.getDeclaredField(property).getAnnotation(
					Column.class);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			Log.e(Order.class.getSimpleName(), "不存在" + property + "的字段!");
			e.printStackTrace();
		}
		if (col != null) {
			this.field = col.name();
			if (this.field.equals(""))
				this.field = property;
		}
		setField(this.field);
		setProperty(property);
		this.order = order;
	}

	public String getField() {
		return this.field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public OrderType getOrder() {
		return this.order;
	}

	public void setOrder(OrderType order) {
		this.order = order;
	}

	public String getProperty() {
		return this.property;
	}

	public void setProperty(String property) {
		this.property = property;
	}
}