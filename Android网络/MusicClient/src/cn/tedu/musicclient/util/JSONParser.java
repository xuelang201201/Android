package cn.tedu.musicclient.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.tedu.musicclient.entity.Music;

/**
 * ����json�ַ����Ĺ�����
 */
public class JSONParser {
	/**
	 * ��jsonArray����ΪList<Music>
	 * @param ary [{���ֶ���},{},{}]
	 * @return
	 * @throws JSONException 
	 */
	public static List<Music> parse(JSONArray ary) throws JSONException{
		List<Music> musics=new ArrayList<Music>();
		for(int i=0; i<ary.length(); i++){
			JSONObject obj=ary.getJSONObject(i);
			Music music=new Music(
					obj.getInt("id"), 
					obj.getString("album"), 
					obj.getString("albumpic"), 
					obj.getString("author"), 
					obj.getString("composer"), 
					obj.getString("downcount"), 
					obj.getString("durationtime"), 
					obj.getString("favcount"), 
					obj.getString("musicpath"), 
					obj.getString("name"), 
					obj.getString("singer"));
			musics.add(music);
		}
		return musics;
	}
}

