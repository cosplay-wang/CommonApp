package com.cosplay.wang.commonapp.view.widgits;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Author:zhiwei.wang on 2018/5/8.
 * Email :cosplay.wang@gmail.com|760560322@qq.com
 * Description:
 */

public class HomeViewPager extends ViewPager{
	boolean canScroll = false;
	public HomeViewPager(@NonNull Context context) {
		super(context);
	}

	public HomeViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
	}

	public void setCanScroll(boolean isCanScroll){
		canScroll = isCanScroll;
	}
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		if (!canScroll) {
			return false;
		}
		return super.onInterceptTouchEvent(ev);
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		if (!canScroll) {
			return false;
		}
		return super.onTouchEvent(ev);
	}


}
