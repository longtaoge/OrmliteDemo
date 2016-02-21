package org.xiangbalao.test;

import java.util.List;

import org.xiangbalao.common.android.dao.SqliteDao;
import org.xiangbalao.common.android.factory.DBFactory;
import org.xiangbalao.model.DataTest;

import android.test.AndroidTestCase;
import android.util.Log;

public class DbTest extends AndroidTestCase {

	public void TestAA() {

		Log.i("TestAA", "testaa");

		SqliteDao<DataTest, Object> dao = DBFactory
				.getDao(getContext(),DataTest.class);
		
		DataTest model=new DataTest();
		model.setName("aadd");
		model.setId("10006");
		model.setAge("12234333");
		
		DataTest model1=new DataTest();
		
		model1.setName("aadd2222");
		model1.setId("102222006");
		model1.setAge("122343332222222222222222");
		
		
		try {
			dao.createOrUpdate(model);
			dao.createOrUpdate(model1);
			
		} catch (Exception e) {
			// TODO: handle exception
			Log.i("TestAA", e.toString());
		}
		
		
		List<DataTest> mDataTests = dao
				.queryForEq("name", "aadd"); 
		Log.i("TestAA", "testaEN"+mDataTests.get(0).getName());

	}

}
