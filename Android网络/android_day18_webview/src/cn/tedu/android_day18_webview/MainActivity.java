package cn.tedu.android_day18_webview;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {

	private WebView webView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// 初始化webView
		setViews();

		// 调用loadUrl方法 加载一张网页
		// webView.loadUrl("http://www.baidu.com");
		// webView.loadUrl("file:///android_asset/www/form.html");
		webView.loadUrl("file:///android_asset/www/index.html");
		// webView.loadUrl("file:///android_asset/www/RetroSnaker/worm.html");

		// 操作WebSettings属性
		setWebSettings();

		// 操作WebViewClient属性
		setWebViewClient();

		// 操作WebChromeClient属性
		setWebChromeClient();

		// 定义JavaScript接口
		setJavascriptInterface();
	}

	// 定义JavaScript接口
	private void setJavascriptInterface() {
		// 注册JavaScript接口
		webView.addJavascriptInterface(new MyObject(), "phone");
	}

	class MyObject {
		@JavascriptInterface
		public void call (String number) {
			Intent intent = new Intent(Intent.ACTION_CALL);
			intent.setData(Uri.parse("tel:" + number));
			startActivity(intent);
		}
	}

	// 操作WebChromeClient属性
	private void setWebChromeClient() {
		webView.setWebChromeClient(new WebChromeClient() {
			// 重要关注 onConsoleMessage 方法
			// 如果不给webView设置默认的WebChromeClient，那么console.log
			// 打印的内容将不会再logcat中显示
		});
	}

	//操作WebViewClient属性
	private void setWebViewClient() {
		webView.setWebViewClient(new WebViewClient() {
			// 覆盖webViewClient的方法 从而在webView触发某些事件后执行
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}
		});
	}

	// 操作WebSettings属性
	private void setWebSettings() {
		WebSettings settings = webView.getSettings();
		// 启用js
		settings.setJavaScriptEnabled(true);
		settings.setDisplayZoomControls(true);
		settings.setBuiltInZoomControls(true);
		settings.setSupportZoom(true);
	}

	// 初始化webView
	private void setViews() {
		webView = (WebView) findViewById(R.id.webView);
	}

	public void doClick(View v) {
		switch (v.getId()) {
		case R.id.button1:
			// 调用页面中的updateDiv函数
			webView.loadUrl("javascript:updateDiv()");
			break;
		}
	}
}
