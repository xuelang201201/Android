package cn.tedu.music_player_2.util;

/**
 * �ṩ�����Ľӿ�
 */
public interface Consts {
	/**
	 * ָ�����/��ͣ
	 */
	int ACTION_PLAY_OR_PAUSE = 1;
	/**
	 * ָ���һ��
	 */
	int ACTION_PREVIOUS = 2;
	/**
	 * ָ���һ��
	 */
	int ACTION_NEXT = 3;
	/**
	 * ָ������µĸ�����ͨ������Ҫ��Intent�з�װ�µĸ���������
	 */
	int ACTION_PLAY_NEW = 4;

	/**
	 * Intent�з�װ���ݵ����ƣ�ָ��
	 */
	String EXTRA_ACTION = "cn.tedu.intent.EXTRA_ACTION";
	/**
	 * Intent�з�װ���ݵ����ƣ�����������
	 */
	String EXTRA_POSITION = "cn.tedu.intent.EXTRA_POSITION";

}
