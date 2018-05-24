package com.cosplay.wang.commonapp.ui.fragment;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cosplay.wang.commonapp.R;
import com.cosplay.wang.commonapp.base.BaseFragment;
import com.cosplay.wang.commonapp.base.CommonApplication;
import com.cosplay.wang.commonapp.bean.ImageList;
import com.cosplay.wang.commonapp.bean.NewsItem;
import com.cosplay.wang.commonapp.presenter.ImageListPresenter;
import com.cosplay.wang.commonapp.presenter.NewsPresenter;
import com.cosplay.wang.commonapp.ui.activity.ImageDetailActivity;
import com.cosplay.wang.commonapp.ui.activity.WebViewActivity;
import com.cosplay.wang.commonapp.ui.adapter.ImageListRYAdapter;
import com.cosplay.wang.commonapp.ui.adapter.NewsListRYAdapter;
import com.cosplay.wang.commonapp.utils.Recyclerview.ItemDecoration;
import com.cosplay.wang.commonapp.utils.Recyclerview.MyDecoration;
import com.cosplay.wang.commonapp.view.ImageListView;
import com.cosplay.wang.commonapp.view.NewsListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Author:zhiwei.wang on 2018/5/21.
 * Email :cosplay.wang@gmail.com|760560322@qq.com
 * Description:
 */

public class CommonContentImageFragment extends BaseFragment implements ImageListView {
	@BindView(R.id.recyclerview)
	RecyclerView recyclerview;
	@BindView(R.id.swipelayout)
	SwipeRefreshLayout swipelayout;
	Unbinder unbinder;
	ImageListPresenter imageListPresenter;
	ImageListRYAdapter imageListRYAdapter;
	String url;
	ImageListFragment imageListFragment;
	List<ImageList.ImgsBean> imgsBeanArrayList = new ArrayList<>();
	int currentPage = 2;
	boolean isRefreshing = false;
	boolean loadMoreing = false;

	@Override
	protected int getContentViewLayoutID() {
		return R.layout.layout;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	public void setFragment(ImageListFragment imageListFragment) {
		this.imageListFragment = imageListFragment;
	}

	@Override
	protected void initViewsAndEvents() {

		swipelayout.setRefreshing(true);
		imageListPresenter = new ImageListPresenter(url, this);
		imageListPresenter.getData(currentPage);
		imageListRYAdapter = new ImageListRYAdapter(imgsBeanArrayList, getActivity());
		StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
	//	gridLayoutManager.setOrientation();
		gridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
		recyclerview.setLayoutManager(gridLayoutManager);
		//添加自定义分割线
//		DividerItemDecoration divider = new DividerItemDecoration(CommonApplication.context, DividerItemDecoration.VERTICAL);
//		divider.setDrawable(ContextCompat.getDrawable(CommonApplication.context, R.drawable.ry_item_divider));
	//	recyclerview.addItemDecoration(new MyDecoration());
		recyclerview.setAdapter(imageListRYAdapter);
		((SimpleItemAnimator)recyclerview.getItemAnimator()).setSupportsChangeAnimations(false);
		imageListRYAdapter.setOnItemClickListener(new ImageListRYAdapter.OnRYItemClickListener() {
			@Override
			public void onRYItemClick(View view, int position) {
				Intent intent = new Intent();
				intent.setClass(getActivity(),ImageDetailActivity.class);
				intent.putExtra("url",imgsBeanArrayList.get(position).imageUrl);
				startActivity(intent);
			}
		});
		recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
			@Override
			public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
				super.onScrollStateChanged(recyclerView, newState);
			}

			@Override
			public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
				super.onScrolled(recyclerView, dx, dy);
				int lastVisiableItem = getLastVisibleItemPosition(null);
				int totalItem =  recyclerView.getLayoutManager().getItemCount();
				if (lastVisiableItem >= totalItem - 4 && dy > 0) {
					if (loadMoreing) {

					} else {
						loadMoreing = true;
						currentPage = currentPage + 1;
						imageListPresenter.getData(currentPage);
					}
				}
			}
		});
		swipelayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				if (!isRefreshing) {
					swipelayout.setRefreshing(true);
					isRefreshing = true;
					imgsBeanArrayList.clear();
					currentPage = 2;
					imageListPresenter.getData(currentPage);
				}
			}
		});

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
		swipelayout.setRefreshing(false);
	}


	/**
	 * 获取底部可见项的位置
	 * 获取最后一个条目的索引
	 * @param lastPos 如果是瀑布流的话就返回每一列最后一个条目的索引
	 * @return
	 */
	private int getLastVisibleItemPosition(int[] lastPos) {
		RecyclerView.LayoutManager lm = recyclerview.getLayoutManager();
		int lastVisibleItemPosition = 0;
		if (lm instanceof GridLayoutManager) {
			lastVisibleItemPosition = ((GridLayoutManager) lm).findLastVisibleItemPosition();
		} else if (lm instanceof LinearLayoutManager) {
			lastVisibleItemPosition = ((LinearLayoutManager) lm).findLastVisibleItemPosition();
		}else if(lm instanceof StaggeredGridLayoutManager){
			StaggeredGridLayoutManager layoutManager = (StaggeredGridLayoutManager)lm;
			int columnCount = layoutManager.getColumnCountForAccessibility(null, null);//获取瀑布流的列数
			//int columnCount = layoutManager.getSpanCount();//获取瀑布流的列数
			int[] positions = new int[columnCount];
			layoutManager.findLastVisibleItemPositions(positions);//获取瀑布流的每一列的最下面的条目的索引（并不是最后n个(n为瀑布流的列数)），有的条目可能会很长
		     lastVisibleItemPosition = maxInteger(positions);//返回其中最大的一个（它是乱序的，并不是按顺序保存的）
			//System.arraycopy(positions,0,lastPos,0,positions.length);
			//瀑布流的布局方式是哪一列的高度最小，下一个条目就排到哪一列的后面
		}
		return lastVisibleItemPosition;
	}
	private int maxInteger(int[] positions){
        int temp = -1;
		for(int i=0;i<positions.length;i++) {
			for(int j=i+1;j<positions.length;j++) {
				if(positions[i]>positions[j]) {
					temp = positions[i];
					positions[i] = positions[j];
					positions[j] = temp;
				}
			}
		}
		return  positions[0];
	}
	@Override
	public void loadDataSuccess(final List<ImageList.ImgsBean> fnewsList) {

		getActivity().runOnUiThread(new Runnable() {
			@Override
			public void run() {

				if (isRefreshing) {
					imgsBeanArrayList.clear();
					Log.e("recyclerview", imgsBeanArrayList.size() + "-+++++++++++++============");
				}

					loadMoreing = false;
					for (ImageList.ImgsBean news : fnewsList) {
						imgsBeanArrayList.add(news);
					}
				//imageListRYAdapter.getRandomHeight(imgsBeanArrayList);
					if(isRefreshing){
						isRefreshing = false;
						imageListRYAdapter.notifyDataSetChanged();
					}else {
						Log.e("recyclerview", imgsBeanArrayList.size() + "----------============");
						//	imageListRYAdapter.notifyDataSetChanged();
						imageListRYAdapter.notifyItemInserted(imgsBeanArrayList.size() - 20);
					}
					//	imageListRYAdapter.notifyItemRangeChanged(0,imgsBeanArrayList.size());


				swipelayout.setRefreshing(false);
				Log.e("recyclerview", imgsBeanArrayList.size() + "============");
			}
		});
	}

	@Override
	public void loadDatFalture(final Exception e) {
		getActivity().runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Log.e("dff","exception"+ e.getMessage());
				loadMoreing = false;
				Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
			}
		});
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO: inflate a fragment view
		View rootView = super.onCreateView(inflater, container, savedInstanceState);
		unbinder = ButterKnife.bind(this, rootView);
		return rootView;
	}
}
