package com.cosplay.wang.commonapp;

import android.os.Bundle;


import com.cosplay.wang.commonapp.utils.LogUtil;


public class MainActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		LogUtil.log("tag","message");
	}
}
