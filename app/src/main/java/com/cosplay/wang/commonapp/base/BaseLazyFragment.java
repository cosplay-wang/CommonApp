package com.cosplay.wang.commonapp.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Author:zhiwei.wang on 2018/5/7.
 * Email :cosplay.wang@gmail.com|760560322@qq.com
 * Description:
 */

public abstract class BaseLazyFragment extends Fragment {

	private boolean onViewCreate = false;
	private boolean onFirstLoad = true;


	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		Log.e("saudgsyfsd","onAttach");
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		Log.e("saudgsyfsd","onCreateView");
		if (getContentViewLayoutID() != 0) {
			return inflater.inflate(getContentViewLayoutID(), null);
		} else {
			return super.onCreateView(inflater, container, savedInstanceState);
		}

	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		//onFirstUserVisible();
	}

	@Override
	public void onDetach() {
		super.onDetach();
	}

	@Override
	public void onStart() {
		super.onStart();
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onStop() {
		super.onStop();
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	// TODO: 2018/5/7 在fragment切换到时候调用
	// TODO: 2018/5/7 isVisibleToUser 代表fragment是否可见
	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		if (isVisibleToUser && onFirstLoad) {
			onFirstLoad = false;
			onFirstUserVisible();
		}
	}

	// TODO: 2018/5/7 setUserVisibleHint  在 oncreateview之前执行

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		//super.onViewCreated(view, savedInstanceState);
		Log.e("saudgsyfsd","onViewCreated");
		onFirstUserVisible();
		ButterKnife.bind(view);
		initViewsAndEvents();
	}


	// TODO: 2018/5/7 加载数据的方法
	//protected abstract  void lazyLoad();

	// TODO: 2018/5/7 首次可见 加载数据 
	protected abstract void onFirstUserVisible();

	// TODO: 2018/5/7 初始化view和事件
	protected abstract void initViewsAndEvents();

	/**
	 * bind layout resource file
	 *
	 * @return id of layout resource
	 */
	protected abstract int getContentViewLayoutID();
}
