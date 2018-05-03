package com.cosplay.wang.commonapp;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.TextView;
import android.widget.Toast;

import com.cosplay.wang.commonapp.utils.LogUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class MainActivity extends BaseActivity {

	@BindView(R.id.adbad)
	TextView adbad;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);
		LogUtil.log("tag", "message");
		MainActivityPermissionsDispatcher.readSDcardWithPermissionCheck(this);
	}

	@NeedsPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
	public void readSDcard() {
		startCamera();
	}

	@OnPermissionDenied(Manifest.permission.READ_EXTERNAL_STORAGE)
	void readSDcardDenied() {
		// NOTE: Deal with a denied permission, e.g. by showing specific UI
		// or disabling certain functionality
		Toast.makeText(this, "R.string.permission_camera_denied", Toast.LENGTH_SHORT).show();
	}

	@OnNeverAskAgain(Manifest.permission.READ_EXTERNAL_STORAGE)
	void readSDcardNever() {
		// NOTE: Deal with a denied permission, e.g. by showing specific UI
		// or disabling certain functionality
		Toast.makeText(this, "R.string.permission_camera_never", Toast.LENGTH_SHORT).show();
	}

	// 启动相机
	private void startCamera() {
		Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
		startActivity(intent);
	}

	// 第一次出现授权提示出现后, 选择拒绝, 下次再进入才会出现
	@OnShowRationale(Manifest.permission.READ_EXTERNAL_STORAGE)
	void show(final PermissionRequest request) {
		//Log.d(TAG, "OnShowRationale");
		new AlertDialog.Builder(this)
				.setMessage("再次请求授权吗？")
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// 再次请求授权（带有不再提示选项）
						request.proceed();
					}
				})
				.setNegativeButton("取消", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// 执行 OnPermissionDenied注解下面的方法
						request.cancel();
					}
				})
				.show();
	}


	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		// 代理权限处理到自动生成的方法
		MainActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
	}


}
