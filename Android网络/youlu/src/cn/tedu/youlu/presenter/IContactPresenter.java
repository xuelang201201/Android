package cn.tedu.youlu.presenter;

/**
 * 联系人相关 Presenter接口
 * 接口中定义联系人view中需要实现的功能
 */
public interface IContactPresenter {
	/**
	 * 加载所有联系人
	 * 并且联系人加载成功后  调用view
	 * 更新UI
	 */
	void loadAllContacts();
	
}



