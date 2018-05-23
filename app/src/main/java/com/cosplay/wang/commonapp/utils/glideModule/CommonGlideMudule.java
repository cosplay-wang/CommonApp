package com.cosplay.wang.commonapp.utils.glideModule;

import android.content.Context;
import android.support.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.ExternalPreferredCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.module.AppGlideModule;

/**
 * Created by zhiwei.wang on 2018/5/2.
 * wechat 760560322
 * 作用：
 */
@GlideModule

public class CommonGlideMudule extends AppGlideModule {
	public static final int DISK_CACHE_SIZE = 50 * 1024 * 1024;
	public static final String DISK_CACHE_NAME = "cosplay";


	@Override
	public boolean isManifestParsingEnabled() {
		return super.isManifestParsingEnabled();
	}

	@Override
	public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
		super.applyOptions(context, builder);
		/** 将所有Glide加载的图片缓存到SD卡上, 默认硬盘缓存大小都是250M,这里改为500 * */
		//builder.setDiskCache(new ExternalCacheDiskCacheFactory(context));
		/** ExternalCacheDiskCacheFactory的默认缓存路径 是在sdcard/Android/data/包名/cache/image_manager_disk_cache目录当中 */
		//builder.setDiskCache(new ExternalCacheDiskCacheFactory(context, DISK_CACHE_SIZE));
		/** * 更改缓存最总文件夹名称 * * 是在sdcard/Android/data/包名/cache/DISK_CACHE_NAME目录当中 */
		//此方法在Glide4已过时
		//builder.setDiskCache(new ExternalCacheDiskCacheFactory(context, DISK_CACHE_NAME, DISK_CACHE_SIZE));
		builder.setDiskCache(new ExternalPreferredCacheDiskCacheFactory(context, DISK_CACHE_NAME, DISK_CACHE_SIZE));

		int maxMemory = (int) Runtime.getRuntime().maxMemory();
		int memoryCacheSize = maxMemory / 16;
		//设置Glide的内存缓存大小
		builder.setMemoryCache(new LruResourceCache(memoryCacheSize));


		MemorySizeCalculator.Builder memorySizeCalculatorBuilder = new MemorySizeCalculator.Builder(context);
		MemorySizeCalculator calculator = memorySizeCalculatorBuilder.build();
		int defaultBitmapPoolSize = calculator.getBitmapPoolSize();
		//设置Glide的bitmap缓存池大小
		builder.setBitmapPool(new LruBitmapPool((int)(0.8 * defaultBitmapPoolSize)));
	}

	@Override
	public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
		super.registerComponents(context, glide, registry);
	}
}
