package com.cosplay.wang.commonapp.utils;


import android.util.Log;

import com.cosplay.wang.commonapp.base.CommonApplication;
import com.cosplay.wang.commonapp.R;
import com.cosplay.wang.commonapp.utils.okhttp.OkHttpUtil;
import com.cosplay.wang.commonapp.utils.okhttp.OkhttpHeader;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static okhttp3.Protocol.HTTP_2;

/**
 * Created by zhiwei.wang on 2018/5/2.
 * wechat 760560322
 * 作用：
 */

public class HttpUtil {

	public static Response httpRequest(String url, RequestBody body, List<OkhttpHeader> okhttpHeaderList) throws IOException {
		boolean netIsWork = NetWorkUtil.isNetworkConnected(CommonApplication.context);
		if (netIsWork) {
			Request.Builder builder = new Request.Builder();
			if (okhttpHeaderList != null && okhttpHeaderList.size() > 0) {
				for (OkhttpHeader okhttpHeader : okhttpHeaderList) {
					builder.addHeader(okhttpHeader.getName(), okhttpHeader.getValue());
				}
			}
			if (body != null) {
				builder.method("POST", body);
			}
			Call call = OkHttpUtil.okHttpClient.newCall(builder.url(url).build());
			Response response = call.execute();
			return response;
		} else {
			Response noNetResponse = new Response.Builder()
					.request(new Request.Builder().url(url).build())
					.message(CommonApplication.context.getString(R.string.no_net_message))
					.protocol(HTTP_2).code(R.string.no_net_code).build();
			return noNetResponse;
		}
	}

	public static void httpRequest(String url,final OkHttpUtil.HttpRequestCallBack httpRequestCallBack) throws IOException {
		boolean netIsWork = NetWorkUtil.isNetworkConnected(CommonApplication.context);
		if (netIsWork) {
			Log.e("dadasd",url);
			Request.Builder builder = new Request.Builder();
			Call call = OkHttpUtil.okHttpClient.newCall(builder.url(url).build());
			call.enqueue(new Callback() {
				@Override
				public void onFailure(Call call, IOException e) {
					httpRequestCallBack.onIFailure(e);
				}

				@Override
				public void onResponse(Call call, Response response) throws IOException {
					httpRequestCallBack.onSuccess(response);
				}
			});
		} else {
			Response noNetResponse = new Response.Builder()
					.request(new Request.Builder().url(url).build())
					.message(CommonApplication.context.getString(R.string.no_net_message))
					.protocol(HTTP_2).code(R.string.no_net_code).build();
		}
	}


}
