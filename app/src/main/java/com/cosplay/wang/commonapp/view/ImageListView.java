package com.cosplay.wang.commonapp.view;

import com.cosplay.wang.commonapp.bean.ImageList;
import com.cosplay.wang.commonapp.bean.NewsItem;
import com.cosplay.wang.commonapp.view.base.BaseView;

import java.util.List;

/**
 * Author:zhiwei.wang on 2018/5/21.
 * Email :cosplay.wang@gmail.com|760560322@qq.com
 * Description:
 */

public interface ImageListView extends BaseView {
	void loadDataSuccess(List<ImageList.ImgsBean> imageList);
	void loadDatFalture(Exception e);
}
