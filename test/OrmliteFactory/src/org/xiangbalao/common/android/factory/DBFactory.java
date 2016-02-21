package org.xiangbalao.common.android.factory;

import android.content.Context;
import android.util.Log;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import java.sql.SQLException;

import org.xiangbalao.common.android.DatabaseHelper;
import org.xiangbalao.common.android.dao.SqliteDao;

public class DBFactory {
	public static <T, ID> SqliteDao<T, ID> getDao(Context context, Class<T> t) {
		Dao<T, ID> dao = null;
		try {

			dao = ((DatabaseHelper) OpenHelperManager.getHelper(context,
					DatabaseHelper.class)).getDao(t);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		Log.i(DBFactory.class.getSimpleName(), "SqliteDao");
		return new SqliteDao<T, ID>(dao);
	}
}