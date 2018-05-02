package com.cosplay.wang.commonapp.utils.glideModule;

/**
 * Created by zhiwei.wang on 2018/5/2.
 * wechat 760560322
 * 作用：
 */

import com.bumptech.glide.annotation.GlideExtension;
import com.bumptech.glide.annotation.GlideOption;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.cosplay.wang.commonapp.R;

@GlideExtension
public class CommonGlideExtension {
	/**
	 * 将这个类的构造函数声明成private，这是必须要求的写法
	 */
	private CommonGlideExtension() {
	}

	@GlideOption
	public static void commonGlideExtension(RequestOptions options) {
		//占位图
		options.placeholder(R.drawable.ic_launcher_background);
		// 错误图片
		options.error(R.mipmap.ic_launcher);
		// 表示只缓存原始图片
		options.diskCacheStrategy(DiskCacheStrategy.DATA);
		// 切圆
		options.circleCrop();
		//...等等所有的属性
	}


}
