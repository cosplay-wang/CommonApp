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

public class NewsViewPager extends ViewPager {
	public NewsViewPager(@NonNull Context context) {
		super(context);
	}

	public NewsViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		super.onInterceptTouchEvent(ev);
		return true;
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		if (getChildCount() <= 1) {
			super.onTouchEvent(ev);
		}
		switch (ev.getAction()) {
			case MotionEvent.ACTION_DOWN:
				if (getParent() != null) {
					getParent().requestDisallowInterceptTouchEvent(true);   //让事件不再分发
				}
				break;
			case MotionEvent.ACTION_MOVE:
				if (getParent() != null) {
					getParent().requestDisallowInterceptTouchEvent(true);   //让事件不再分发
				}
				break;
			case MotionEvent.ACTION_CANCEL:
			case MotionEvent.ACTION_UP:
				if (getParent() != null) {
					getParent().requestDisallowInterceptTouchEvent(true);   //让事件不再分发
				}
				break;
		}
		super.onTouchEvent(ev);
		return true;        //让事件不再分发
	}
}
