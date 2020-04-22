package cn.tedu.music_player_2.util;

public interface IMusicPlayer {
	/**
	 * ����
	 */
	void play();

	/**
	 * ����ָ���ĸ���
	 * 
	 * @param position
	 *            �������б��е�λ��
	 */
	void play(int position);

	/**
	 * ��ָ���İٷֱȿ�ʼ���Ÿ���
	 * 
	 * @param progress
	 *            �ٷֱȣ�������Ϊ����������50%��ò���Ӧ����50
	 */
	void play(float progress);

	/**
	 * ��ͣ
	 */
	void pause();

	/**
	 * ������һ��
	 */
	void previous();

	/**
	 * ������һ��
	 */
	void next();

	/**
	 * �Ƿ����ڲ���
	 * 
	 * @return ������ڲ��ţ��򷵻�true����֮���򷵻�false
	 */
	boolean isPlaying();

	/**
	 * ��ȡ��ǰ���ŵ�λ��
	 * 
	 * @return ��ǰ���ŵ�λ�ã���λ������
	 */
	int getCurrentPosition();

	/**
	 * ��ȡ��ǰ���ŵĸ�������ʱ��
	 * 
	 * @return ��ǰ���ŵĸ�������ʱ������λ������
	 */
	int getDuration();

	/**
	 * ��ȡ��ǰ���ŵĸ���������
	 * 
	 * @return ��ǰ���ŵĸ���������
	 */
	int getCurrentMusicIndex();

}
