package com.cosplay.wang.commonapp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.INotificationSideChannel;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cosplay.wang.commonapp.R;
import com.cosplay.wang.commonapp.base.BaseFragment;
import com.cosplay.wang.commonapp.base.CommonApplication;
import com.cosplay.wang.commonapp.bean.NewsItem;
import com.cosplay.wang.commonapp.presenter.NewsPresenter;
import com.cosplay.wang.commonapp.ui.activity.WebViewActivity;
import com.cosplay.wang.commonapp.ui.adapter.NewsListRYAdapter;
import com.cosplay.wang.commonapp.view.NewsListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Author:zhiwei.wang on 2018/5/21.
 * Email :cosplay.wang@gmail.com|760560322@qq.com
 * Description:
 */

public class CommonContentFragment extends BaseFragment implements NewsListView {
	@BindView(R.id.recyclerview)
	RecyclerView recyclerview;
	@BindView(R.id.swipelayout)
	SwipeRefreshLayout swipelayout;
	Unbinder unbinder;
	NewsPresenter newsPresenter;
	NewsListRYAdapter newsListRYAdapter;
	String url;
	List<NewsItem> newsList = new ArrayList<>();
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

	@Override
	protected void initViewsAndEvents() {

		swipelayout.setRefreshing(true);
		newsPresenter = new NewsPresenter(url, this);
		newsPresenter.getData(currentPage);
		newsListRYAdapter = new NewsListRYAdapter(newsList, CommonApplication.context);
		LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
		linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		recyclerview.setLayoutManager(linearLayoutManager);
		//添加自定义分割线
		DividerItemDecoration divider = new DividerItemDecoration(CommonApplication.context, DividerItemDecoration.VERTICAL);
		divider.setDrawable(ContextCompat.getDrawable(CommonApplication.context, R.drawable.ry_item_divider));
		recyclerview.addItemDecoration(divider);
		recyclerview.setAdapter(newsListRYAdapter);
		newsListRYAdapter.setOnItemClickListener(new NewsListRYAdapter.OnRYItemClickListener() {
			@Override
			public void onRYItemClick(View view, int position) {
				Intent intent = new Intent();
				intent.setClass(getActivity(),WebViewActivity.class);
				intent.putExtra("title",newsList.get(position).title);
				intent.putExtra("url",newsList.get(position).link.weburl);
				startActivity(intent);
			}
		});
		recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
			@Override
			public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
				super.onScrolled(recyclerView, dx, dy);
				int lastVisiableItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();
				int totalItem =  recyclerView.getLayoutManager().getItemCount();
				if (lastVisiableItem >= totalItem - 4 && dy > 0) {
					if (loadMoreing) {

					} else {
						loadMoreing = true;
						currentPage = currentPage + 1;
						newsPresenter.getData(currentPage);
					}
				}
			}
			@Override
			public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
				//	super.onScrollStateChanged(recyclerView, newState);
				if (newState == RecyclerView.SCROLL_STATE_IDLE) {
					Glide.with(CommonApplication.context).resumeRequests();
				}else if(newState == RecyclerView.SCROLL_STATE_DRAGGING) {
					Glide.with(CommonApplication.context).resumeRequests();
				}else{
					Glide.with(CommonApplication.context).pauseRequests();
				}
			}

		});
		swipelayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {

				if (!isRefreshing) {
					swipelayout.setRefreshing(true);
					isRefreshing = true;
					newsList.clear();
					currentPage = 2;
					newsPresenter.getData(currentPage);
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

	@Override
	public void loadDataSuccess(final List<NewsItem> fnewsList) {

		getActivity().runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (isRefreshing) {
					newsList.clear();
					isRefreshing = false;
				//	Log.e("recyclerview", newsList.size() + "-+++++++++++++============");
				}
				loadMoreing = false;
				for (NewsItem news : fnewsList) {
					newsList.add(news);
				}
			//	Log.e("recyclerview", newsList.size() + "----------============");
				newsListRYAdapter.notifyDataSetChanged();
				swipelayout.setRefreshing(false);
			//	Log.e("recyclerview", newsList.size() + "============");
			}
		});
	}

	@Override
	public void loadDatFalture(final Exception e) {
		getActivity().runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Log.e("dff", e.getMessage());
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
