package org.xiangbalao.common.android.dao.criteria;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;

import android.util.Log;

public class Where<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	private String fieldName;
	private DataType fieldType;
	private Object fieldValue;
	private MethodType method;
	private String propertyName;
	private List<Integer> andOrLengths = new ArrayList<Integer>();

	public int getAndOrLength(int index) {
		return ((Integer) this.andOrLengths.get(index)).intValue();
	}

	public void setAndOrLength(int andOrLength) {
		this.andOrLengths.add(Integer.valueOf(andOrLength));
	}

	public Where(Class<T> t, String propertyName, Object propertyValue,
			MethodType method) {
		this.propertyName = propertyName;
		this.fieldValue = propertyValue;
		this.method = method;

		String field = "";
		Column col = null;
		try {
			col = (Column) t.getDeclaredField(propertyName).getAnnotation(
					Column.class);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			Log.e("cn.com.ths.android", "不存在" + propertyName + "的字段!");
			e.printStackTrace();
		}
		if (col != null) {
			field = col.name();
			if (field.equals(""))
				field = propertyName;
		}
		setFieldName(field);
	}

	public Where(int andOrLength, MethodType method) {
		this.andOrLengths.add(Integer.valueOf(andOrLength));
		this.method = method;
	}

	public String getFieldName() {
		return this.fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public DataType getFieldType() {
		return this.fieldType;
	}

	public void setFieldType(DataType fieldType) {
		this.fieldType = fieldType;
	}

	public Object getFieldValue() {
		return this.fieldValue;
	}

	public void setFieldValue(Object fieldValue) {
		this.fieldValue = fieldValue;
	}

	public MethodType getMethod() {
		return this.method;
	}

	public void setMethod(MethodType method) {
		this.method = method;
	}

	public String getPropertyName() {
		return this.propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
}