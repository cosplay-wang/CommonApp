package com.cosplay.wang.commonapp.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cosplay.wang.commonapp.R;
import com.cosplay.wang.commonapp.base.CommonApplication;
import com.cosplay.wang.commonapp.bean.ImageList;
import com.cosplay.wang.commonapp.bean.NewsItem;
import com.cosplay.wang.commonapp.utils.ImageUtil;
import com.cosplay.wang.commonapp.utils.ScreenUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author:zhiwei.wang on 2018/5/21.
 * Email :cosplay.wang@gmail.com|760560322@qq.com
 * Description:
 */

public class ImageListRYAdapter extends RecyclerView.Adapter<ImageListRYAdapter.ViewHolder> {
	List<ImageList.ImgsBean> imageList;
	Context context;
	private Map<Integer, Integer> itemHeights = new HashMap<>();

	public ImageListRYAdapter(List<ImageList.ImgsBean> imageList, Context context) {
		this.imageList = imageList;
		this.context = context;
	}


	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(context).inflate(R.layout.image_list_item, parent, false);

		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
		if (itemHeights.get(position) != null && itemHeights.get(position) > 0) {
			ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();
			//设置图片的相对于屏幕的宽高比
			params.width = ScreenUtil.getDeviceWidth(context) / 3;
			params.height = itemHeights.get(position);
			holder.itemView.setLayoutParams(params);
		} else {
			ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();
			//设置图片的相对于屏幕的宽高比
			params.width = ScreenUtil.getDeviceWidth(context) / 3;
			params.height = (int) (200 + Math.random() * params.width) + 150;
			itemHeights.put(position, params.height);
			holder.itemView.setLayoutParams(params);
		}
		ViewGroup.LayoutParams params1 = holder.imageView.getLayoutParams();
		//设置图片的相对于屏幕的宽高比
		params1.width = ScreenUtil.getDeviceWidth(context) / 3;
		params1.height = itemHeights.get(position) - 100;
		holder.imageView.setLayoutParams(params1);
		holder.imageView.setPadding(40,0,40,40);
		ImageList.ImgsBean image = imageList.get(position);
		//	Glide.with(context).load(image.thumbnailUrl)..into(holder.imageView);
		ImageUtil.loadImage(image.thumbnailUrl, holder.imageView, context);
		holder.title.setText(image.title);
		//Log.e("asdasd", image.title + "\n" + image.thumbnailUrl);
	}

	@Override
	public int getItemCount() {
		return imageList == null ? 0 : imageList.size();
	}

	class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
		@BindView(R.id.imagelist_item_img)
		ImageView imageView;
		@BindView(R.id.imagelist_item_title)
		TextView title;

		public ViewHolder(View itemView) {
			super(itemView);
			ButterKnife.bind(this, itemView);
			itemView.setOnClickListener(this);
		}

		@Override
		public void onClick(View view) {
			if (onRYItemClickListener != null) {
				onRYItemClickListener.onRYItemClick(view, getAdapterPosition());
			}
		}
	}

	public interface OnRYItemClickListener {
		void onRYItemClick(View view, int position);
	}

	public void setOnItemClickListener(OnRYItemClickListener listener) {
		this.onRYItemClickListener = listener;
	}

	private OnRYItemClickListener onRYItemClickListener;
}
