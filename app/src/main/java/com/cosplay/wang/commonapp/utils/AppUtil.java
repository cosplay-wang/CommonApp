package com.cosplay.wang.commonapp.utils;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;

import java.util.List;

/**
 * Created by zhiwei.wang on 2018/5/4.
 * wechat 760560322
 * 作用：
 */

public class AppUtil {
	private AppUtil() {
		throw new AssertionError();
	}

	/**
	 * whether this process is named with processName
	 *
	 * @param context
	 * @param processName
	 * @return <ul>
	 *         return whether this process is named with processName
	 *         <li>if context is null, return false</li>
	 *         <li>if {@link ActivityManager#getRunningAppProcesses()} is null, return false</li>
	 *         <li>if one process of {@link ActivityManager#getRunningAppProcesses()} is equal to processName, return
	 *         true, otherwise return false</li>
	 *         </ul>
	 */
	public static boolean isNamedProcess(Context context, String processName) {
		if (context == null) {
			return false;
		}

		int pid = android.os.Process.myPid();
		ActivityManager manager = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
		List<ActivityManager.RunningAppProcessInfo> processInfoList = manager.getRunningAppProcesses();
		if (ListUtil.isEmpty(processInfoList)) {
			return false;
		}

		for (ActivityManager.RunningAppProcessInfo processInfo : processInfoList) {
			if (processInfo != null && processInfo.pid == pid
					&& ObjectUtil.isEquals(processName, processInfo.processName)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * whether application is in background
	 * <ul>
	 * <li>need use permission android.permission.GET_TASKS in Manifest.xml</li>
	 * </ul>
	 *
	 * @param context
	 * @return if application is in background return true, otherwise return false
	 */
	public static boolean isApplicationInBackground(Context context) {
		ActivityManager am = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
		List<ActivityManager.RunningTaskInfo> taskList = am.getRunningTasks(1);
		if (taskList != null && !taskList.isEmpty()) {
			ComponentName topActivity = taskList.get(0).topActivity;
			if (topActivity != null && !topActivity.getPackageName().equals(context.getPackageName())) {
				return true;
			}
		}
		return false;
	}

}
