package com.cosplay.wang.commonapp.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;

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
	public static int getDeviceDpi(Context context) {
		DisplayMetrics metrics = new DisplayMetrics();
		((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
		int dpi = metrics.densityDpi;
		Log.e("dpi",dpi +":"+metrics.widthPixels+"-------"+metrics.heightPixels);
		return dpi;
	}
}
