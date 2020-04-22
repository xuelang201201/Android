package cn.tedu.musicclient.dal;

import java.util.ArrayList;
import java.util.List;

import cn.tedu.musicclient.entity.Music;

/**
 * ≤‚ ‘”√£°£°£°
 */
class TestMusicDao implements IDao<Music> {

	@Override
	public List<Music> getData() {
		List<Music> musics = new ArrayList<Music>();
		Music music = new Music();
		music.setName("≤‚ ‘");
		musics.add(music);
		return musics;
	}

}
