package com.cosplay.wang.commonapp.view.base;

import com.cosplay.wang.commonapp.utils.okhttp.OkHttpUtil;

/**
 * Author:zhiwei.wang on 2018/5/7.
 * Email :cosplay.wang@gmail.com|760560322@qq.com
 * Description:
 */

public interface BaseView {
	/**
	 * show loading message
	 *
	 * @param msg
	 */
	void showLoading(String msg);

	/**
	 * hide loading
	 */
	void hideLoading();

	/**
	 * show error message
	 */
	void showError(String msg);

	/**
	 * show exception message
	 */
	void showException(String msg);

	/**
	 * show net error
	 */
	void showNetError();

}
