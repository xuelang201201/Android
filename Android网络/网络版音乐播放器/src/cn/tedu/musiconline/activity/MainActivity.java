package cn.tedu.musiconline.activity;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import cn.tedu.musiconline.R;
import cn.tedu.musiconline.adapter.MusicAdapter;
import cn.tedu.musiconline.biz.MusicBiz;
import cn.tedu.musiconline.biz.MusicBiz.Callback;
import cn.tedu.musiconline.entity.Music;
import cn.tedu.musiconline.util.GlobalConsts;

public class MainActivity extends Activity {
	
	private ListView lvMusics;
	private List<Music> musics;
	private MusicAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		setViews();
		// �����첽���񣬷������󣬻�ȡ�����б�
		String url = GlobalConsts.BASEURL + "loadMusics.jsp";
		MusicBiz biz = new MusicBiz(new Callback() {

			@Override
			public void onSuccess(List<Music> musics) {
				// �Զ���adapter������UI�����߳���ִ��
				Log.i("info", "onSuccess:" + musics.toString());
				MainActivity.this.musics = musics;
				adapter = new MusicAdapter(MainActivity.this, musics);
				lvMusics.setAdapter(adapter);
			}
		}); 
		biz.execute(url); // ִ���첽����
	}

	// �ؼ���ʼ��
	private void setViews() {
		lvMusics = (ListView) findViewById(R.id.lv_musics);
	}
}
