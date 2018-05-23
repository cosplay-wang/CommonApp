package com.cosplay.wang.commonapp.base;

import com.cosplay.wang.commonapp.view.base.BaseView;

import okhttp3.Response;

/**
 * Author:zhiwei.wang on 2018/5/7.
 * Email :cosplay.wang@gmail.com|760560322@qq.com
 * Description:
 */

public class BaseFragment extends BaseLazyFragment implements BaseView {
	@Override
	protected void onFirstUserVisible() {


	}

	@Override
	public void showLoading(String msg) {

	}

	@Override
	public void hideLoading() {

	}

	@Override
	public void showError(String msg) {

	}

	@Override
	public void showException(String msg) {

	}

	@Override
	public void showNetError() {

	}

	@Override
	protected void initViewsAndEvents() {

	}

	@Override
	protected int getContentViewLayoutID() {
		return 0;
	}


}
