package com.cosplay.wang.commonapp.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cosplay.wang.commonapp.R;
import com.cosplay.wang.commonapp.base.BaseFragment;
import com.cosplay.wang.commonapp.ui.adapter.NewsVPAdapter;
import com.cosplay.wang.commonapp.view.widgits.HomeViewPager;
import com.cosplay.wang.commonapp.view.widgits.NewsViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Author:zhiwei.wang on 2018/5/7.
 * Email :cosplay.wang@gmail.com|760560322@qq.com
 * Description:
 */

public class NewsFragment extends BaseFragment {
	@BindView(R.id.news_tablayout)
	TabLayout newsTablayout;
	@BindView(R.id.news_viewpager)
	HomeViewPager newsViewpager;
	Unbinder unbinder;
	String[] newsCategoryArrayId ;
	String[] newsCategoryArrayName ;
	List<CommonContentFragment> viewList = new ArrayList<>();
	NewsVPAdapter newsVPAdapter;
	@Override
	protected int getContentViewLayoutID() {
		return R.layout.news_layout;
	}


	@Override
	protected void initViewsAndEvents() {
		 newsCategoryArrayId = getResources().getStringArray(R.array.news_category_list);
		 newsCategoryArrayName = getResources().getStringArray(R.array.news_category_list);


		for(int i= 0;i<newsCategoryArrayName.length;i++){
			CommonContentFragment fragment = new CommonContentFragment();
			fragment.setUrl(getURLList().get(i));
			viewList.add(fragment);
		}
		newsVPAdapter = new NewsVPAdapter(getChildFragmentManager(),viewList,newsCategoryArrayName);
		newsViewpager.setAdapter(newsVPAdapter);
		newsViewpager.setCanScroll(true);
		newsViewpager.setOffscreenPageLimit(viewList.size());
		newsTablayout.setupWithViewPager(newsViewpager);
	}

	@Override
	protected void onFirstUserVisible() {
		super.onFirstUserVisible();
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		unbinder.unbind();
	}

	@Override
	public void showLoading(String msg) {
		super.showLoading(msg);
	}

	@Override
	public void hideLoading() {
		super.hideLoading();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO: inflate a fragment view
		View rootView = super.onCreateView(inflater, container, savedInstanceState);
		unbinder = ButterKnife.bind(this, rootView);
		return rootView;
	}
	public List<String> getURLList(){
		List<String> urlList = new ArrayList<>();
	//	http://api.iclient.ifeng.com/ClientNews?id=SYLB10,SYDT10&page=1
		urlList.add("http://api.iclient.ifeng.com/ClientNews?id=SYLB10,SYDT10&page=");

		urlList.add("http://api.iclient.ifeng.com/ClientNews?id=QC45,FOCUSQC45&page=");
		urlList.add("http://api.iclient.ifeng.com/ClientNews?id=FC81,FOCUSFC81&page=");
		urlList.add("http://api.iclient.ifeng.com/ClientNews?id=KJ123,FOCUSKJ123&page=");
		urlList.add("http://api.iclient.ifeng.com/ClientNews?id=XZ09,FOCUSXZ09&page=");
		urlList.add("http://api.iclient.ifeng.com/ClientNews?id=LY67,FOCUSLY67&page=");
		urlList.add("http://api.iclient.ifeng.com/ClientNews?id=SS78,FOCUSSS78&page=");
		urlList.add("http://api.iclient.ifeng.com/ClientNews?id=YL53,FOCUSYL53&page=");
		return urlList;
	}
}
