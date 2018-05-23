package com.cosplay.wang.commonapp.utils;

import android.content.Context;

/**
 * Created by zhiwei.wang on 2018/5/4.
 * wechat 760560322
 * 作用：
 */

public class DeviceUtil {
	public static double getStatusBarHeight(Context context){
		double statusBarHeight = Math.ceil(25 * context.getResources().getDisplayMetrics().density);
		return statusBarHeight;
	}
}
