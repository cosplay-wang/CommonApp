package com.cosplay.wang.commonapp.base;

import android.app.Application;
import android.content.Context;

import com.cosplay.wang.commonapp.BuildConfig;
import com.cosplay.wang.commonapp.utils.timbertree.CommonTimberDebugTree;
import com.cosplay.wang.commonapp.utils.timbertree.CommonTimberTree;
import com.squareup.leakcanary.LeakCanary;

import timber.log.Timber;

/**
 * Author:zhiwei.wang on 2018/5/7.
 * Email :cosplay.wang@gmail.com|760560322@qq.com
 * Description:
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

		if (LeakCanary.isInAnalyzerProcess(this)) {
			// This process is dedicated to LeakCanary for heap analysis.
			// You should not init your app in this process.
			return;
		}
		LeakCanary.install(this);
	}


}
