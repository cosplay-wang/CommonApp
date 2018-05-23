package com.cosplay.wang.commonapp.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cosplay.wang.commonapp.R;
import com.cosplay.wang.commonapp.base.BaseFragment;
import com.cosplay.wang.commonapp.base.Constants;
import com.cosplay.wang.commonapp.bean.ImageList;
import com.cosplay.wang.commonapp.ui.adapter.ImageVPAdapter;
import com.cosplay.wang.commonapp.utils.ImageUtil;
import com.cosplay.wang.commonapp.view.widgits.HomeViewPager;
import com.github.chrisbanes.photoview.PhotoView;

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

public class ImageListFragment extends BaseFragment {
	@BindView(R.id.image_tablayout)
	TabLayout imageTablayout;
	@BindView(R.id.image_viewpager)
	HomeViewPager imageViewpager;
	Unbinder unbinder;
	String[] newsCategoryArrayName;
	List<CommonContentImageFragment> viewList = new ArrayList<>();
	ImageVPAdapter imageVPAdapter;


	@Override
	protected int getContentViewLayoutID() {
		return R.layout.image_layout;
	}

	@Override
	protected void initViewsAndEvents() {
		newsCategoryArrayName = getResources().getStringArray(R.array.images_category_list_id);


		for (int i = 0; i < newsCategoryArrayName.length; i++) {
			CommonContentImageFragment fragment = new CommonContentImageFragment();
			fragment.setUrl(Constants.ImageListUrl + "&col=" + newsCategoryArrayName[i] + "&pn=");
			fragment.setFragment(ImageListFragment.this);
			viewList.add(fragment);
		}
		imageVPAdapter = new ImageVPAdapter(getChildFragmentManager(), viewList, newsCategoryArrayName);
		imageViewpager.setAdapter(imageVPAdapter);
		imageViewpager.setCanScroll(true);
		imageTablayout.setupWithViewPager(imageViewpager);
		imageViewpager.setOffscreenPageLimit(newsCategoryArrayName.length);

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


}
