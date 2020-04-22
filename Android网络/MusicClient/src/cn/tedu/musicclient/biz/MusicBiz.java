package cn.tedu.musicclient.biz;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import cn.tedu.musicclient.entity.Music;
import cn.tedu.musicclient.util.HttpUtils;
import cn.tedu.musicclient.util.JSONParser;

/**
 * 执行音乐列表下载业务
 */
public class MusicBiz extends AsyncTask<String, String, List<Music>>{
	private Callback callback;
	
	public MusicBiz(Callback callback) {
		this.callback = callback;
	}
	
	
	/**
	 * 在工作线程中执行  发送http请求 
	 * 解析http响应json字符串 
	 * 获取List<Music>
	 * @param url  传递uri请求资源路径
	 */
	protected List<Music> doInBackground(String... params) {
		String url=params[0];
		//发送http请求  HttpUtils		
		try {
			HttpEntity entity=HttpUtils.send(HttpUtils.METHOD_GET, url, null);
			//把entity转换成json字符串
			String json=EntityUtils.toString(entity);
			//解析json  
			//  {result:ok,  data:[{},{},{},{}]}
			JSONObject obj=new JSONObject(json);
			String res=obj.getString("result");
			if(res.equals("ok")){
				JSONArray ary=obj.getJSONArray("data");
				//解析jsonArray 生成一个List
				List<Music> musics=JSONParser.parse(ary);
				return musics;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 在异步工作完成后  接收传递的参数
	 * 在主线程中执行onPostExecute方法
	 */
	protected void onPostExecute(List<Music> musics) {
		callback.onSuccess(musics);
	}
	
	public interface Callback{
		void onSuccess(List<Music> musics);
	}
	
}




