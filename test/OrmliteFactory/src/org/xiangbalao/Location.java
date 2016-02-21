package org.xiangbalao;

import org.xiangbalao.common.android.DatabaseHelper;
import org.xiangbalao.common.android.util.LogUtils;
import org.xiangbalao.model.DataTest;

import android.app.Application;
import android.os.Environment;

public class Location extends Application {

	@Override
	public void onCreate() {

		LogUtils.init(true, true);
		super.onCreate();
		String databasesPath = Environment.getExternalStorageDirectory()
				.getAbsolutePath() + "/";

		// TODO 调试完毕后修改数据库路径
		DatabaseHelper.initialize(databasesPath + "/xiangbalao.db", 1,
				new Class<?>[] { DataTest.class });

	}

}