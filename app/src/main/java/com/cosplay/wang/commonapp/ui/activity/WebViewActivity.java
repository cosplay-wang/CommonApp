package com.cosplay.wang.commonapp.ui.activity;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.cosplay.wang.commonapp.R;
import com.cosplay.wang.commonapp.base.BaseActivity;

public class WebViewActivity extends BaseActivity {
	WebView webView;
	String url;
	LinearLayout linearLayout;
	String title;
    Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

		setContentView(R.layout.activity_web_view);
		linearLayout = findViewById(R.id.webview_lin);

		title = getIntent().getStringExtra("title");

		url = getIntent().getStringExtra("url");
		Log.e("adsada", url+ "-----------");
		//url = "https://blog.csdn.net/kernel_jim_wu/article/details/70149475";
		if (url != null && !url.equals("")) {
			webView = new WebView(this);
			linearLayout.addView(webView);

			//声明WebSettings子类
			WebSettings webSettings = webView.getSettings();

			//如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
			webSettings.setJavaScriptEnabled(true);
			// 若加载的 html 里有JS 在执行动画等操作，会造成资源浪费（CPU、电量）
			// 在 onStop 和 onResume 里分别把 setJavaScriptEnabled() 给设置成 false 和 true 即可


			//设置自适应屏幕，两者合用
			webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
			webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

			//缩放操作
			webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
			webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
			webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件

			//其他细节操作
			webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
			webSettings.setAllowFileAccess(true); //设置可以访问文件
			webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
			webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
			webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式

			webView.loadUrl(url);
			webView.setWebViewClient(new WebViewClient() {
				@Override
				public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
					if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
						view.loadUrl(request.getUrl().toString());
					} else {
						view.loadUrl(request.toString());
					}
					return true;
				}
			});
			webView.setWebChromeClient(new WebChromeClient() {
				@Override
				public void onProgressChanged(WebView view, int newProgress) {
					super.onProgressChanged(view, newProgress);
				}
			});

		} else {
			finish();
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (getMyToolbar() != null) {
			getMyToolbar().setTitle(title);
		}

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (webView != null) {
			webView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
			webView.clearHistory();

			((ViewGroup) webView.getParent()).removeView(webView);
			webView.destroy();
			webView = null;

		}
		linearLayout.removeAllViews();

	}
}
