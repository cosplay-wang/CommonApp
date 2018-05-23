package com.cosplay.wang.commonapp.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;

import java.util.List;

/**
 * Author:zhiwei.wang on 2018/5/8.
 * Email :cosplay.wang@gmail.com|760560322@qq.com
 * Description:
 */

public class MainVPAdapter extends FragmentPagerAdapter {
	List<Fragment> fragmentList;

	public MainVPAdapter(FragmentManager fm, List<Fragment> fragmentList) {
		super(fm);
		this.fragmentList = fragmentList;
	}

	@Override
	public int getCount() {
		return fragmentList == null ? 0 : fragmentList.size();
	}



	@Override
	public Fragment getItem(int position) {
		if (fragmentList != null && position > -1 && position < fragmentList.size()) {
			return fragmentList.get(position);
		} else {
			return null;
		}
	}
}
