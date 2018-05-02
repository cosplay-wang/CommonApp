package com.cosplay.wang.commonapp.utils;

import timber.log.Timber;

/**
 * Created by zhiwei.wang on 2018/5/2.
 * wechat 760560322
 * 作用：
 */

public class LogUtil {
	public static void log(String tag ,String message){
		Timber.e(message,tag);
	}

}
