package cn.tedu.musicclient.util;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * 发送请求的工具类
 */
public class HttpUtils {
	public static final int METHOD_GET=0;
	public static final int METHOD_POST=1;
	
	/**
	 * 发送http请求 
	 * @param method  请求方式
	 *  	METHOD_GET  = 0
	 *   	mETHOD_POST  = 1
	 * @param url  请求资源路径
	 * @param list  请求参数 (post请求)
	 *    如果没有参数则传递null
	 * @return 接收响应后的响应实体对象
	 * @throws IOException 
	 */
	public static HttpEntity send(int method,String url, List<NameValuePair> list) throws IOException{
		HttpClient client =new DefaultHttpClient();
		HttpResponse resp = null;
		switch (method) {
		case METHOD_GET:
			HttpGet get = new HttpGet(url);
			resp=client.execute(get);
			break;
		case METHOD_POST:
			HttpPost post=new HttpPost(url);
			post.setHeader("Content-Type", "application/x-www-form-urlencoded");
			if(list != null){
				HttpEntity reqEntity=new UrlEncodedFormEntity(list, "utf-8");
				post.setEntity(reqEntity);
			}
			resp=client.execute(post);
			break;
		}
		HttpEntity entity=resp.getEntity();
		return entity;
	}
}





