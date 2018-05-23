package com.cosplay.wang.commonapp.view;

import com.cosplay.wang.commonapp.bean.News;
import com.cosplay.wang.commonapp.bean.NewsItem;
import com.cosplay.wang.commonapp.view.base.BaseView;

import java.util.List;

/**
 * Author:zhiwei.wang on 2018/5/21.
 * Email :cosplay.wang@gmail.com|760560322@qq.com
 * Description:
 */

public interface NewsListView extends BaseView {
	void loadDataSuccess(List<NewsItem> newsList);
	void loadDatFalture(Exception e);
}
