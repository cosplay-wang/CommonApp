package com.cosplay.wang.commonapp.ui.fragment;

import com.cosplay.wang.commonapp.R;
import com.cosplay.wang.commonapp.base.BaseFragment;

/**
 * Author:zhiwei.wang on 2018/5/7.
 * Email :cosplay.wang@gmail.com|760560322@qq.com
 * Description:
 */

public class MusicFragment extends BaseFragment {
	@Override
	protected int getContentViewLayoutID() {
		return R.layout.music_layout;
	}

	@Override
	protected void initViewsAndEvents() {

	}

	@Override
	protected void onFirstUserVisible() {
		super.onFirstUserVisible();
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
	}

	@Override
	public void showLoading(String msg) {
		super.showLoading(msg);
	}

	@Override
	public void hideLoading() {
		super.hideLoading();
	}
}
