package cn.tedu.music_player_2.dal;

import java.util.ArrayList;
import java.util.List;

import cn.tedu.music_player_2.entity.Music;

/**
 * ≤‚ ‘”√£°£°£°
 */
class TestMusicDao implements IDao<Music> {

	@Override
	public List<Music> getData() {
		List<Music> musics = new ArrayList<Music>();
		Music music = new Music();
		music.setTitle("≤‚ ‘");
		musics.add(music);
		return musics;
	}

}
