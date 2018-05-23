package com.cosplay.wang.commonapp;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.cosplay.wang.commonapp.base.BaseActivity;
import com.cosplay.wang.commonapp.ui.adapter.MainVPAdapter;
import com.cosplay.wang.commonapp.ui.fragment.ImageListFragment;
import com.cosplay.wang.commonapp.ui.fragment.MusicFragment;
import com.cosplay.wang.commonapp.ui.fragment.NewsFragment;
import com.cosplay.wang.commonapp.ui.fragment.VideoFragment;
import com.cosplay.wang.commonapp.utils.DeviceUtil;
import com.cosplay.wang.commonapp.view.widgits.HomeViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class HomeActivity extends BaseActivity {


	@BindView(R.id.main)
	HomeViewPager viewPager;
	@BindView(R.id.navigation)
	NavigationView navigation;
	@BindView(R.id.drawer_layout)
	DrawerLayout drawerLayout;
	// 抽屉菜单对象
	private ActionBarDrawerToggle drawerbar;
	private MainVPAdapter vpAdapter;
	List<Fragment> fragmentList = new ArrayList<>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);
		initViewAndEvent();
		//HomeActivityPermissionsDispatcher.readSDcardWithPermissionCheck(this);
	}

	void initViewAndEvent() {
		//设置菜单内容之外其他区域的背景色
		drawerLayout.setScrimColor(Color.TRANSPARENT);
		drawerbar = new ActionBarDrawerToggle(this, drawerLayout, null, R.string.app_name, R.string.no_net_message) {
			@Override
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
			}

			@Override
			public void onDrawerClosed(View drawerView) {
				super.onDrawerClosed(drawerView);
			}
		};
		drawerLayout.addDrawerListener(drawerbar);
		navigation.setItemIconTintList(null);
		View headerview = navigation.getHeaderView(0);
//		LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) headerview.getLayoutParams();
//		lp.setMargins(0, (int) DeviceUtil.getStatusBarHeight(this), 0, 0);
//		headerview.setLayoutParams(lp);

		headerview.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//drawerLayout.closeDrawer(navigation);
			}
		});

		navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
			@Override
			public boolean onNavigationItemSelected(@NonNull MenuItem item) {
				String name = item.getTitle().toString();
				if (getResources().getString(R.string.menu_news).equals(name)) {
					viewPager.setCurrentItem(0);
				} else if (getResources().getString(R.string.menu_image).equals(name)) {
					viewPager.setCurrentItem(1);
				} else if (getResources().getString(R.string.menu_video).equals(name)) {
					viewPager.setCurrentItem(2);
				} else if (getResources().getString(R.string.menu_music).equals(name)) {
					viewPager.setCurrentItem(3);
				} else {
					viewPager.setCurrentItem(0);
				}

				drawerLayout.closeDrawer(navigation);
				return true;
			}
		});
		fragmentList.add(new NewsFragment());
		fragmentList.add(new ImageListFragment());
		fragmentList.add(new VideoFragment());
		fragmentList.add(new MusicFragment());
		vpAdapter = new MainVPAdapter(getSupportFragmentManager(), fragmentList);
		viewPager.setAdapter(vpAdapter);
		viewPager.setCanScroll(false);
		viewPager.setOffscreenPageLimit(fragmentList.size());
		viewPager.setCurrentItem(0);
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
		HomeActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
	}


}
