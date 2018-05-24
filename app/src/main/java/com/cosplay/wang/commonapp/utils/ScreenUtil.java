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

public class ScreenUtil {
	private ScreenUtil() {
		throw new AssertionError();
	}

	public static float dpToPx(Context context, float dp) {
		if (context == null) {
			return -1;
		}
		return dp * context.getResources().getDisplayMetrics().density;
	}

	public static float pxToDp(Context context, float px) {
		if (context == null) {
			return -1;
		}
		return px / context.getResources().getDisplayMetrics().density;
	}

	public static int dpToPxInt(Context context, float dp) {
		return (int)(dpToPx(context, dp) + 0.5f);
	}

	public static int pxToDpCeilInt(Context context, float px) {
		return (int)(pxToDp(context, px) + 0.5f);
	}


	public static int getDeviceWidth(Context context) {
		DisplayMetrics metrics = new DisplayMetrics();
		((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
		int width = metrics.widthPixels;
		return width;
	}
	public static int getDeviceHeight(Context context) {
		DisplayMetrics metrics = new DisplayMetrics();
		((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
		int height = metrics.heightPixels;
		return height;
	}


}
