package com.cosplay.wang.commonapp.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cosplay.wang.commonapp.R;
import com.cosplay.wang.commonapp.base.CommonApplication;
import com.cosplay.wang.commonapp.bean.ImageList;
import com.cosplay.wang.commonapp.bean.NewsItem;
import com.cosplay.wang.commonapp.utils.ImageUtil;

import java.util.List;

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

	public ImageListRYAdapter(List<ImageList.ImgsBean> imageList, Context context) {
		this.imageList = imageList;
		this.context = context;
	}

	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(CommonApplication.context).inflate(R.layout.image_list_item, parent,false);

		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
		ImageList.ImgsBean image = imageList.get(position);
		Glide.with(CommonApplication.context).load(image.imageUrl).into(holder.imageView);
	//	ImageUtil.loadImage(newsItem.thumbnail, holder.imageView,context);
		holder.title.setText(image.title);
	//	holder.from.setText("新华日报");
		Log.e("asdasd",image.title+"\n"+image.imageUrl);
	}

	@Override
	public int getItemCount() {
		return imageList == null ? 0 : imageList.size();
	}

	class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
		@BindView(R.id.imagelist_item_img)
		ImageView imageView;
		@BindView(R.id.imagelist_item_title)
		TextView title;

		public ViewHolder(View itemView) {
			super(itemView);
			ButterKnife.bind(this,itemView);
			itemView.setOnClickListener(this);
		}

		@Override
		public void onClick(View view) {
			if (onRYItemClickListener != null) {
				onRYItemClickListener.onRYItemClick(view,getAdapterPosition());
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
