package cn.tedu.news.util;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import cn.tedu.news.entity.MyNews;

import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

/**
 * 网络访问请求类
 */
public class HttpUtil {
	// 创建一个Volley的访问队列
	public static RequestQueue queue;
	
	public static void getNews(final Context context, final OnGetNewsListener listener) {
		
		// 1)判断有没有队列
		if(queue==null){
			// 如果没有队列，就创建请求队列
			queue = Volley.newRequestQueue(context);
		}
		
		// 2)创建网络请求
		String url = "http://op.juhe.cn/onebox/news/words?key=06c117d654e49b523d5e0337823f897f";
		StringRequest request = new StringRequest(url , new Listener<String>() {

			@Override
			public void onResponse(String result) {
				// 网络访问成功后，调用该方法
				// onResponse方法是在主线程上调用的
				// Log.d("TAG", result);
				// 使用gson，将result转化为MyNews对象
				Gson gson = new Gson();
				MyNews myNews = gson.fromJson(result, MyNews.class);
				Log.d("TAG", myNews.toString());
				listener.OnSuccess(myNews);
			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError arg0) {
				// 网络访问失败后，会调用该方法
				// onErrorResponse方法是在主线程上调用的
				Toast.makeText(context, "网络繁忙，稍后重试", Toast.LENGTH_LONG).show();
			}
		});
		
		queue.add(request);
	}
	
	public interface OnGetNewsListener {
		void OnSuccess(MyNews myNews);
	}
}
