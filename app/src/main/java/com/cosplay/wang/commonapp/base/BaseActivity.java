package com.cosplay.wang.commonapp.base;
/**
 * Author:zhiwei.wang on 2018/5/7.
 * Email :cosplay.wang@gmail.com|760560322@qq.com
 * Description:
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.WindowManager;

import com.cosplay.wang.commonapp.R;

import butterknife.ButterKnife;

public class BaseActivity extends AppCompatActivity {
	Toolbar toolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//	getSupportActionBar().hide();
		//透明状态栏
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		//getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
	}

	@Override
	public void setContentView(int layoutResID) {
		super.setContentView(layoutResID);
		toolbar = ButterKnife.findById(this, R.id.common_toolbar);
		if (null != toolbar) {
			setSupportActionBar(toolbar);
			getSupportActionBar().setHomeButtonEnabled(true);
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
			Log.e("asdad", "---------------");
		} else {
			Log.e("asdad", "ffffffffffffffffff---------------");
		}
	}

	public Toolbar getMyToolbar() {
		return toolbar;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {


			finish();


			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
