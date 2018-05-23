package com.cosplay.wang.commonapp.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.cosplay.wang.commonapp.base.BaseFragment;
import com.cosplay.wang.commonapp.ui.fragment.CommonContentImageFragment;

import java.util.List;

/**
 * Author:zhiwei.wang on 2018/5/8.
 * Email :cosplay.wang@gmail.com|760560322@qq.com
 * Description:
 */

public class ImageVPAdapter extends FragmentPagerAdapter {
	List<CommonContentImageFragment> viewList ;
	String[] tabs;


	public ImageVPAdapter(FragmentManager fm, List<CommonContentImageFragment> viewList, String[] tabs) {
		super(fm);
		this.viewList = viewList;
		this.tabs = tabs;
	}

	@Nullable
	@Override
	public CharSequence getPageTitle(int position) {
		return tabs[position];
	}

	@Override
	public int getCount() {
		return viewList == null ? 0 :viewList.size();
	}

	@Override
	public Fragment getItem(int position) {
		return viewList.get(position);
	}
}
