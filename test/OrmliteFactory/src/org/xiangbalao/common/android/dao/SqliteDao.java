package org.xiangbalao.common.android.dao;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.xiangbalao.common.android.dao.criteria.DataQuery;
import org.xiangbalao.common.android.dao.criteria.Order;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.QueryBuilder;

public class SqliteDao<T, ID> extends RuntimeExceptionDao<T, ID> {
	public SqliteDao(Dao<T, ID> dao) {
		super(dao);
	}

	public void Save(T t) {
		createOrUpdate(t);
	}

	public List<T> list(DataQuery<T> dataQuery) throws SQLException {
		return query(explainQuery(dataQuery).prepare());
	}

	public long getCount(DataQuery<T> dataQuery) {
		try {
			return explainQuery(dataQuery).countOf();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0L;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private QueryBuilder<T, ID> explainQuery(DataQuery<T> dataQuery)
			throws SQLException {
		QueryBuilder<T, ID> queryBuilder = queryBuilder();
		com.j256.ormlite.stmt.Where ormliteWhere = null;
		if (dataQuery.getWhere().size() > 0)
			ormliteWhere = queryBuilder.where();
		int andOrCount = 0;
		org.xiangbalao.common.android.dao.criteria.Where customWhere;
		for (int i = 0; i < dataQuery.getWhere().size(); i++) {
			customWhere = (org.xiangbalao.common.android.dao.criteria.Where) dataQuery
					.getWhere().get(i);
			switch (customWhere.getMethod().ordinal()) {

			case 1:
				ormliteWhere.eq(customWhere.getFieldName(),
						customWhere.getFieldValue());
				break;
			case 3:
				ormliteWhere.gt(customWhere.getFieldName(),
						customWhere.getFieldValue());
				break;
			case 2:
				ormliteWhere.lt(customWhere.getFieldName(),
						customWhere.getFieldValue());
				break;
			case 6:
				ormliteWhere.like(customWhere.getFieldName(),
						customWhere.getFieldValue());
				break;
			case 4:
				ormliteWhere.ge(customWhere.getFieldName(),
						customWhere.getFieldValue());
				break;
			case 5:
				ormliteWhere.lt(customWhere.getFieldName(),
						customWhere.getFieldValue());
				break;
			case 7:
				ormliteWhere.in(
						customWhere.getFieldName(),
						Arrays.asList(customWhere.getFieldValue().toString()
								.split("\002")));
				break;
			case 8:
				ormliteWhere.and(customWhere.getAndOrLength(andOrCount));
				andOrCount++;
			case 9:
				ormliteWhere.or(customWhere.getAndOrLength(andOrCount));
				andOrCount++;
			}

		}

		if ((dataQuery.getWhere().size() >= 1) && (andOrCount == 0)) {
			ormliteWhere.and(dataQuery.getWhere().size());
		}
		if (dataQuery.getSelect() != null) {
			queryBuilder.selectColumns(dataQuery.getSelect());
		}

		for (Order o : dataQuery.getOrder()) {
			switch (o.getOrder().ordinal()) {
			case 1:
				queryBuilder.orderBy(o.getField(), true);
				break;
			case 2:
				queryBuilder.orderBy(o.getField(), false);
				break;
			default:
				queryBuilder.orderBy(o.getField(), false);
			}

		}

		if (dataQuery.getPage() != null) {
			queryBuilder.offset(Long.valueOf(dataQuery.getPage().getCurPage()
					* dataQuery.getPage().getPerSize()));
			queryBuilder.limit(Long.valueOf(dataQuery.getPage().getPerSize()));
		}
		return queryBuilder;
	}
}