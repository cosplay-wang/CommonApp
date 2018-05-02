package com.cosplay.wang.commonapp.utils.timbertree;

import android.util.Log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import timber.log.Timber;

/**
 * Created by zhiwei.wang on 2018/5/2.
 * wechat 760560322
 * 作用：
 */

public class CommonTimberTree extends Timber.Tree {
	static Logger mLogger = LoggerFactory.getLogger(CommonTimberTree.class);
	@Override
	protected void log(int priority, String tag, String message, Throwable t) {
		if (priority == Log.VERBOSE) {
			return;
		}

		String logMessage = tag + ": " + message;
		switch (priority) {
			case Log.DEBUG:
				mLogger.debug(logMessage);
				break;
			case Log.INFO:
				mLogger.info(logMessage);
				break;
			case Log.WARN:
				mLogger.warn(logMessage);
				break;
			case Log.ERROR:
				mLogger.error(logMessage);
				break;
		}
	}
}
