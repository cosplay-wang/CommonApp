package com.cosplay.wang.commonapp;

import android.app.Application;
import android.content.Context;

import com.cosplay.wang.commonapp.utils.timbertree.CommonTimberDebugTree;
import com.cosplay.wang.commonapp.utils.timbertree.CommonTimberTree;

import timber.log.Timber;

/**
 * Created by zhiwei.wang on 2018/5/2.
 * wechat 760560322
 * 作用：
 */

public class CommonApplication extends Application {
    public static Context context;

	@Override
	public void onCreate() {
		super.onCreate();
		context = getApplicationContext();
		if (BuildConfig.DEBUG) {
			Timber.plant(new CommonTimberDebugTree());
		} else {
			Timber.plant(new CommonTimberTree());
		}
	}

}
