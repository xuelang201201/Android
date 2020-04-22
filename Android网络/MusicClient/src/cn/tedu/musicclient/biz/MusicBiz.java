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
 * ִ�������б�����ҵ��
 */
public class MusicBiz extends AsyncTask<String, String, List<Music>>{
	private Callback callback;
	
	public MusicBiz(Callback callback) {
		this.callback = callback;
	}
	
	
	/**
	 * �ڹ����߳���ִ��  ����http���� 
	 * ����http��Ӧjson�ַ��� 
	 * ��ȡList<Music>
	 * @param url  ����uri������Դ·��
	 */
	protected List<Music> doInBackground(String... params) {
		String url=params[0];
		//����http����  HttpUtils		
		try {
			HttpEntity entity=HttpUtils.send(HttpUtils.METHOD_GET, url, null);
			//��entityת����json�ַ���
			String json=EntityUtils.toString(entity);
			//����json  
			//  {result:ok,  data:[{},{},{},{}]}
			JSONObject obj=new JSONObject(json);
			String res=obj.getString("result");
			if(res.equals("ok")){
				JSONArray ary=obj.getJSONArray("data");
				//����jsonArray ����һ��List
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
	 * ���첽������ɺ�  ���մ��ݵĲ���
	 * �����߳���ִ��onPostExecute����
	 */
	protected void onPostExecute(List<Music> musics) {
		callback.onSuccess(musics);
	}
	
	public interface Callback{
		void onSuccess(List<Music> musics);
	}
	
}




