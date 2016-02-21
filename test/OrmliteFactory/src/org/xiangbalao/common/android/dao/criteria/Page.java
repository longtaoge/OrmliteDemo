package org.xiangbalao.common.android.dao.criteria;

import java.io.Serializable;

public class Page implements Serializable {
	private static final long serialVersionUID = 1L;
	private long curPage;
	private long perSize;

	public long getCurPage() {
		return this.curPage;
	}

	public void setCurPage(long curPage) {
		this.curPage = curPage;
	}

	public long getPerSize() {
		return this.perSize;
	}

	public void setPerSize(long pageSize) {
		this.perSize = pageSize;
	}

	public Page(long startPage, long perSize) {
		this.curPage = startPage;
		this.perSize = perSize;
	}
}