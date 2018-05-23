package com.cosplay.wang.commonapp.utils;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.request.RequestOptions;
import com.cosplay.wang.commonapp.utils.glideModule.GlideApp;


/**
 * Created by zhiwei.wang on 2018/5/2.
 * wechat 760560322
 * 作用：
 */

public class ImageUtil {
	public static void loadImage(String url, ImageView imageView, Activity activity) {
		GlideApp.with(activity).load(url).into(imageView);
	}
	public static void loadImage(String url, ImageView imageView, Fragment fragment) {
		GlideApp.with(fragment).load(url).into(imageView);
	}
	public static void loadImage(String url, ImageView imageView, Context context) {
		GlideApp.with(context).load(url).skipMemoryCache(true).into(imageView);
	}
	public static void loadImage(int url, ImageView imageView, Context context) {
		GlideApp.with(context).load(url).into(imageView);
	}
	public static void loadImage(String url, ImageView imageView) {
		GlideApp.with(imageView.getContext()).load(url).into(imageView);
	}

	/**
	 * 自定义配置
	 * @param url
	 * @param imageView
	 */
	public static void loadImageCus(String url, ImageView imageView) {
		GlideApp.with(imageView).load(url).commonGlideExtension().into(imageView);
	}

	/**
	 * 原有的选项 放在了option里
	 * @param url
	 * @param imageView
	 * @param placeHolder
	 * @param errorHolder
	 */
	public static void loadImage(String url, ImageView imageView, int placeHolder, int errorHolder) {
		RequestOptions options = new RequestOptions();
		options.placeholder(placeHolder);
		options.error(errorHolder);
		//切圆
		options.circleCrop();

		GlideApp.with(imageView).load(url).apply(options).into(imageView);

//		//预加载
//		GlideApp.with(imageView)
//				.load("https://unsplash.it/200/200?random&22")
//				.preload();
//
//		//预加载后显示
//		GlideApp.with(imageView.getContext())
//				.load("https://unsplash.it/200/200?random&22")
//				.into(imageView);
	}




}
