package com.cosplay.wang.commonapp.utils.okhttp;

import com.cosplay.wang.commonapp.utils.HttpUtil;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by zhiwei.wang on 2018/5/2.
 * wechat 760560322
 * 作用：
 */

public class OkHttpUtil {
	public OkHttpUtil() {
	}

	static long cacheSize = 10 * 1024 * 1024;
	static Cache cache = new Cache(new File("netCache"), cacheSize);
	private static HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC);
	private static Interceptor headerInterceptor = new Interceptor() {
		@Override
		public Response intercept(Chain chain) throws IOException {
			Request original = chain.request();
			HttpUrl originalHttpUrl = original.url();
			HttpUrl url = null;
			String surl = originalHttpUrl.url().toString();
			if (!surl.contains("sn")) {
				url = originalHttpUrl.newBuilder()
						//	.addQueryParameter("sn", SharedPrefencesUtils.getDeviceInfo(getContext(), "SN"))
						.build();
			} else {
				url = originalHttpUrl;
			}
			Request.Builder requestBuilder = original.newBuilder()
					.url(url);
			//	requestBuilder.addHeader("User-Agent", SharedPrefencesUtils.getDeviceInfo(getContext(), "DEVICETYPE"));
			requestBuilder.addHeader("Accept", "application/json");
			Request request = requestBuilder.build();
			return chain.proceed(request);
		}
	};
	public  static OkHttpClient okHttpClient = new OkHttpClient.Builder()
			.cache(cache)
			.addInterceptor(new RetryIntercepter(3))
			.retryOnConnectionFailure(true)
			.connectTimeout(3, TimeUnit.SECONDS)
			.writeTimeout(60, TimeUnit.SECONDS)
			.readTimeout(60, TimeUnit.SECONDS)
			.addInterceptor(loggingInterceptor)
			.addNetworkInterceptor(new StethoInterceptor())
			.addInterceptor(headerInterceptor)
			.build();

	interface HttpRequestSuccessCallBack {
		void onSuccess(Response response);
	}

	interface HttpRequestFailureCallBack {
		void onFailure(Exception e);
	}

	public interface HttpRequestCallBack {
		void onSuccess(Response response);

		void onIFailure(Exception e);
	}

	static HttpRequestSuccessCallBack httpRequestSuccessCallBack;
	static HttpRequestFailureCallBack httpRequestFailureCallBack;
	public  HttpRequestCallBack httpRequestCallBack;

	public void setHttpRequestSuccessCallBack(HttpRequestSuccessCallBack httpRequestSuccessCallBack) {
		this.httpRequestSuccessCallBack = httpRequestSuccessCallBack;
	}

	public void setHttpRequestFailureCallBack(HttpRequestFailureCallBack httpRequestFailureCallBack) {
		this.httpRequestFailureCallBack = httpRequestFailureCallBack;
	}

	public  void setHttpRequestCallBack(HttpRequestCallBack httpRequestCallBack) {
		this.httpRequestCallBack = httpRequestCallBack;
	}

}
