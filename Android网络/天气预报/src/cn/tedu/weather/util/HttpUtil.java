package cn.tedu.weather.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import cn.tedu.weather.entity.MyWeather;

import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
/**
 * 网络访问请求工具类
 * @author wq
 */
public class HttpUtil {
	// 创建一个Volley的访问队列
	public static RequestQueue queue;
	
	public static void getWeather(final Context context, String city, final OnGetWeatherListener listener) {
		try {
			// 1) 判断有没有队列
			if (queue == null) {
				// 如果没有队列，就创建请求队列
				queue = Volley.newRequestQueue(context);
			}
			
			// 2) 创建网络请求
			// 对指定的城市名称进行UTF8转码
			// try-catch快捷键alt+shift+z
			String cityname = URLEncoder.encode(city, "utf8");
			// 利用转码后的城市，拼接URL
			String url = "http://op.juhe.cn/onebox/weather/query?cityname="+cityname+"&key=cc3d1f87750869e157f4ce01fc831fd2";
			StringRequest request = new StringRequest(url, new Listener<String>() {

				@Override
				public void onResponse(String result) {
					// TODO 网络访问成功后，会调用该方法
					// onResponse方法是在主线程上调用的
					// Log.d("TAG", result);
					// 使用gson，将result转为MyWeather对象
					Gson gson = new Gson();
					MyWeather myweather = gson.fromJson(result, MyWeather.class);
					Log.d("TAG", myweather.toString());
					listener.OnSuccess(myweather);
				}
			}, new ErrorListener() {

				@Override
				public void onErrorResponse(VolleyError arg0) {
					// 网络访问失败后，会调用该方法
					// onErrorResponse方法是在主线程上调用的
					Toast.makeText(context, "网络繁忙，稍后重试", Toast.LENGTH_LONG).show();
				}
			});
			
			// 3) 需要把网络访问请求对象放入队列中
			queue.add(request);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	// 定义一个监听器，当网络访问成功后，回调该监听器中的方法
	public interface OnGetWeatherListener {
		void OnSuccess(MyWeather myWeather);
	}
}
