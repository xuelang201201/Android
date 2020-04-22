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
 * �������������
 */
public class HttpUtil {
	// ����һ��Volley�ķ��ʶ���
	public static RequestQueue queue;
	
	public static void getNews(final Context context, final OnGetNewsListener listener) {
		
		// 1)�ж���û�ж���
		if(queue==null){
			// ���û�ж��У��ʹ����������
			queue = Volley.newRequestQueue(context);
		}
		
		// 2)������������
		String url = "http://op.juhe.cn/onebox/news/words?key=06c117d654e49b523d5e0337823f897f";
		StringRequest request = new StringRequest(url , new Listener<String>() {

			@Override
			public void onResponse(String result) {
				// ������ʳɹ��󣬵��ø÷���
				// onResponse�����������߳��ϵ��õ�
				// Log.d("TAG", result);
				// ʹ��gson����resultת��ΪMyNews����
				Gson gson = new Gson();
				MyNews myNews = gson.fromJson(result, MyNews.class);
				Log.d("TAG", myNews.toString());
				listener.OnSuccess(myNews);
			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError arg0) {
				// �������ʧ�ܺ󣬻���ø÷���
				// onErrorResponse�����������߳��ϵ��õ�
				Toast.makeText(context, "���緱æ���Ժ�����", Toast.LENGTH_LONG).show();
			}
		});
		
		queue.add(request);
	}
	
	public interface OnGetNewsListener {
		void OnSuccess(MyNews myNews);
	}
}
