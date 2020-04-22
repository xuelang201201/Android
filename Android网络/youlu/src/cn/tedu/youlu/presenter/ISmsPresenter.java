package cn.tedu.youlu.presenter;

public interface ISmsPresenter {
	
	/**
	 * 通过threadId  查询所有的短信息
	 * @param id
	 */
	void loadSmsByThreadId(int id);
	
}

