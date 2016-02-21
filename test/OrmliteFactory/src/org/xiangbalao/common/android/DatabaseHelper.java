package org.xiangbalao.common.android;

import android.content.Context;

import android.database.sqlite.SQLiteDatabase.CursorFactory;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;

import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import java.sql.SQLException;

import org.xiangbalao.common.android.util.LogUtils;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	public DatabaseHelper(Context context, String databaseName,
			CursorFactory factory, int databaseVersion) {
		super(context, databaseName, null, databaseVersion);

	}

	private static Class<?>[] clazzes;
	private static String databaseName;
	private static int databaseVersion;
	private static String password;

	public DatabaseHelper(Context context) {
		//super(context, databaseName, null, databaseVersion, password);
		super(context, databaseName, null, databaseVersion);
	}

	public static void initialize(String dbName, int dbVersion, Class<?>[] clazz) {
		databaseName = dbName.length() == 0 ? "longtaoge.db" : dbName;
		databaseVersion = dbVersion;
		clazzes = clazz;
		password = "";
	}

	public static void initialize(String dbName, int dbVersion,
			Class<?>[] clazz, String mPassword) {
		databaseName = dbName.length() == 0 ? "longtaoge.db" : dbName;
		databaseVersion = dbVersion;
		clazzes = clazz;
		password = mPassword;
	}

	/*
	 * public void onCreate(SQLiteDatabase database, ConnectionSource
	 * connectionSource) { try { if (clazzes != null) { for (Class<?> clazz :
	 * clazzes) { TableUtils.createTable(connectionSource, clazz); } } } catch
	 * (SQLException e) { LogUtils.d("无法创建数据库 :" + e); } }
	 * 
	 * public void onUpgrade(SQLiteDatabase database, ConnectionSource
	 * connectionSource, int oldVersion, int newVersion) { try { if (clazzes !=
	 * null) { for (Class<?> clazz : clazzes) {
	 * TableUtils.dropTable(connectionSource, clazz, true); } onCreate(database,
	 * connectionSource); } } catch (SQLException e) { LogUtils.d("升级数据库失败：" +
	 * e); } }
	 */

	public void close() {
		super.close();
		for (Class<?> clazz : clazzes) {
			try {
				getDao(clazz).clearObjectCache();
			} catch (SQLException e) {
				LogUtils.d("无法清除Dao的缓存" + e);
			}
		}
	}

	public void onCreate(android.database.sqlite.SQLiteDatabase database,
			ConnectionSource connectionSource) {

		try {
			if (clazzes != null) {
				for (Class<?> clazz : clazzes) {
					TableUtils.createTable(connectionSource, clazz);
				}
			}
		} catch (SQLException e) {
			LogUtils.d("无法创建数据库 :" + e);
		}

	}

	public void onUpgrade(android.database.sqlite.SQLiteDatabase database,
			ConnectionSource connectionSource, int oldVersion, int newVersion) {
		try {
			if (clazzes != null) {
				for (Class<?> clazz : clazzes) {
					TableUtils.dropTable(connectionSource, clazz, true);
				}
				onCreate(database, connectionSource);
			}
		} catch (SQLException e) {
			LogUtils.d("升级数据库失败：" + e);
		}
	}
}