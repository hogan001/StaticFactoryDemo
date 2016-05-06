package com.hogan.staticfactorydemo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

/**
 * 
 * 特別注明 得到PeopleBean 一定是 PeopleBean.obtain()得到，不能New;
 * */

public class MainActivity extends Activity {
	private ListView listView;
	private ArrayList<PeopleBean> dataList;
	private TextView age, name;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		getDate();

	}

	private void getDate() {
		dataList = new ArrayList<PeopleBean>();
		for (int i = 0; i < 100; i++) {
			PeopleBean bean = PeopleBean.obtain();
			bean.setAge(i + " ");
			bean.setName("王" + i + "小");
			dataList.add(bean);
		}
		listView = (ListView) findViewById(R.id.listView);

		MyAdapter adapter = new MyAdapter(this, dataList, R.layout.item);
		listView.setAdapter(adapter);

	}

	class MyAdapter extends CommonAdapter<PeopleBean> {

		public MyAdapter(Context context, List<PeopleBean> datas, int layoutId) {
			super(context, datas, layoutId);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void convert(ViewHolder holder, PeopleBean t) {
			// TODO Auto-generated method stub
			age = holder.getView(R.id.age);
			name = holder.getView(R.id.name);
			age.setText(t.getAge());
			name.setText(t.getName());

		}

	}

}
