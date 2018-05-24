package com.cosplay.wang.commonapp.utils.Recyclerview;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Author:zhiwei.wang on 2018/5/24.
 * Email :cosplay.wang@gmail.com|760560322@qq.com
 * Description:
 */

public class MyDecoration extends RecyclerView.ItemDecoration  {

	public MyDecoration() {
		super();
	}

	@Override
	public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
		super.onDraw(c, parent, state);
	}

	@Override
	public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
		super.getItemOffsets(outRect, view, parent, state);
		outRect.bottom = 30;
		outRect.left = 30;

	}
}
