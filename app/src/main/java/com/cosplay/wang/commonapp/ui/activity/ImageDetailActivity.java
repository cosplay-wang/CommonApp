package com.cosplay.wang.commonapp.ui.activity;

import android.os.Bundle;
import android.view.MenuItem;

import com.cosplay.wang.commonapp.R;
import com.cosplay.wang.commonapp.base.BaseActivity;
import com.cosplay.wang.commonapp.utils.ImageUtil;
import com.github.chrisbanes.photoview.PhotoView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageDetailActivity extends BaseActivity {

	@BindView(R.id.photo_view)
	PhotoView photoView;
	String url;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_detail);
		ButterKnife.bind(this);
		url = getIntent().getStringExtra("url");
		if (url != null) {
			ImageUtil.loadImage(url, photoView, this);
		}
	}


	/**
	 * 监听Back键按下事件,方法1:
	 * 注意:
	 * super.onBackPressed()会自动调用finish()方法,关闭
	 * 当前Activity.
	 * 若要屏蔽Back键盘,注释该行代码即可
	 */
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();
		System.out.println("按下了back键   onBackPressed()");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}


}
