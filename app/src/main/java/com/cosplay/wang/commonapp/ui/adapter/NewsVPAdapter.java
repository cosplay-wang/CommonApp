package com.cosplay.wang.commonapp.ui.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.cosplay.wang.commonapp.base.BaseFragment;
import com.cosplay.wang.commonapp.ui.fragment.CommonContentFragment;

import java.util.List;

/**
 * Author:zhiwei.wang on 2018/5/8.
 * Email :cosplay.wang@gmail.com|760560322@qq.com
 * Description:
 */

public class NewsVPAdapter extends FragmentPagerAdapter {
	List<CommonContentFragment> viewList ;
	String[] tabs;


	public NewsVPAdapter(FragmentManager fm, List<CommonContentFragment> viewList, String[] tabs) {
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
