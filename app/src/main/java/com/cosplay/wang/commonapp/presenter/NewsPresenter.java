package com.cosplay.wang.commonapp.presenter;

import android.util.Log;

import com.cosplay.wang.commonapp.bean.News;
import com.cosplay.wang.commonapp.bean.NewsItem;
import com.cosplay.wang.commonapp.utils.HttpUtil;
import com.cosplay.wang.commonapp.utils.JsonUtil;
import com.cosplay.wang.commonapp.utils.okhttp.OkHttpUtil;
import com.cosplay.wang.commonapp.view.NewsListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Response;

/**
 * Author:zhiwei.wang on 2018/5/21.
 * Email :cosplay.wang@gmail.com|760560322@qq.com
 * Description:
 */

public class NewsPresenter extends BasePresenter {
	String url;
	NewsListView newsListView;


	public NewsPresenter(String url, NewsListView newsListView) {
		this.url = url;
		this.newsListView = newsListView;
	}

	public void getData(int pageNum) {
		newsListView.showLoading("加载中");
		final List<NewsItem> newsItemDoc = new ArrayList<>();
		try {
			HttpUtil.httpRequest(url+pageNum, new OkHttpUtil.HttpRequestCallBack() {
				@Override
				public void onSuccess(Response response) {

					try {
						String body = response.body().string();
						JSONArray jsonArray = new JSONArray(body);
						if (jsonArray.length() > 0) {
							for (int i = 0; i < jsonArray.length(); i++) {
								JSONObject job = jsonArray.getJSONObject(i);
								if (job.get("type").toString().equals("list")) {

									JSONArray jsonArrayInner = job.getJSONArray("item");
									if (jsonArrayInner.length() > 0) {
										for (int j = 0; j < jsonArrayInner.length(); j++) {
											JSONObject jobj = jsonArrayInner.getJSONObject(j);
											if (jobj.get("type").toString().equals("doc")) {
												Log.e("sdasd",jobj.get("type").toString());
												newsItemDoc.add(JsonUtil.GsonToBean(jobj.toString(), NewsItem.class));
											}
										}
									}

								}
							}
						}

						newsListView.loadDataSuccess(newsItemDoc);
					} catch (Exception e) {
						e.printStackTrace();
						newsListView.loadDatFalture(e);
					} finally {
						//newsListView.loadDatFalture(new NullPointerException());
					}
				}

				@Override
				public void onIFailure(Exception e) {
					newsListView.loadDatFalture(e);
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
			newsListView.loadDatFalture(e);
		}


	}
}
