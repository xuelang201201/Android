package cn.tedu.aidl_server;

import cn.tedu.aidl_server.Music;

interface IMusicPlayer {
	
	void play();
	
	void pause();

	long getDuration();
	
	Music getMusic();
	
}
