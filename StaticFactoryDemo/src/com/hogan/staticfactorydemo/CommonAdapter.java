package com.hogan.staticfactorydemo;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class CommonAdapter<T> extends BaseAdapter {
	protected Context mContext;
	protected List<T> mDatas;
	protected LayoutInflater mInflater;
	protected int layoutId;


	public CommonAdapter(Context context, List<T> datas, int layoutId) {
		this.mContext = context;
		this.mDatas = datas;
		this.layoutId = layoutId;
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if (mDatas.size() > 0) {
			return mDatas.size();
		}
		return 0;
	}

	@Override
	public T getItem(int position) {
		// TODO Auto-generated method stub
		return mDatas.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder = ViewHolder.get(mContext, convertView, parent,
				layoutId, position);
		convert(holder, getItem(position));

		return holder.getConvertView();

	}

	public abstract void convert(ViewHolder holder, T t);

}
