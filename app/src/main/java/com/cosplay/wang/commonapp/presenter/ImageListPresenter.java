package com.cosplay.wang.commonapp.presenter;

import android.util.Log;

import com.cosplay.wang.commonapp.bean.ImageList;
import com.cosplay.wang.commonapp.bean.NewsItem;
import com.cosplay.wang.commonapp.utils.HttpUtil;
import com.cosplay.wang.commonapp.utils.JsonUtil;
import com.cosplay.wang.commonapp.utils.okhttp.OkHttpUtil;
import com.cosplay.wang.commonapp.view.ImageListView;
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

public class ImageListPresenter extends BasePresenter {
	String url;
	ImageListView imageListView;


	public ImageListPresenter(String url, ImageListView imageListView) {
		this.url = url;
		this.imageListView = imageListView;
	}

	public void getData(int pageNum) {
		imageListView.showLoading("加载中");
		try {
			HttpUtil.httpRequest(url+(20*pageNum), new OkHttpUtil.HttpRequestCallBack() {
				@Override
				public void onSuccess(Response response) {

					try {
						String body = response.body().string();
						imageListView.loadDataSuccess(JsonUtil.GsonToBean(body, ImageList.class).imgs);
					} catch (Exception e) {
						e.printStackTrace();
						imageListView.loadDatFalture(e);
					} finally {
						//newsListView.loadDatFalture(new NullPointerException());
					}
				}

				@Override
				public void onIFailure(Exception e) {
					imageListView.loadDatFalture(e);
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
			imageListView.loadDatFalture(e);
		}


	}
}
