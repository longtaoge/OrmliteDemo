package org.xiangbalao;

import java.util.List;

import org.xiangbalao.common.android.dao.SqliteDao;
import org.xiangbalao.common.android.factory.DBFactory;
import org.xiangbalao.model.DataTest;
import org.xiangbalao.ormlitetest.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class MainActivity extends Activity {

	

	private SqliteDao<DataTest, Object> dao; //= DBFactory
//			.getDao(getApplicationContext(),EnterpriseBaseInfoModel.class);

	@SuppressWarnings("unused")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//

		setContentView(R.layout.activity_main);
		dao = DBFactory
				.getDao(this,DataTest.class);
		// 查询数据库
		List<DataTest> mSteelEnterpriseBaseInfoModels = dao
				.queryForEq("name", "10007"); // 水泥厂暂时没有,先用10007

	}

	@Override
	protected void onResume() {

		super.onResume();
	}

}