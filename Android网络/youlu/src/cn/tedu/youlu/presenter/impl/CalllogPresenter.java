package cn.tedu.youlu.presenter.impl;

import java.util.List;

import cn.tedu.youlu.entity.Calllog;
import cn.tedu.youlu.model.ICalllogModel;
import cn.tedu.youlu.model.impl.CalllogModel;
import cn.tedu.youlu.presenter.ICalllogPresenter;
import cn.tedu.youlu.view.ICalllogView;

public class CalllogPresenter implements ICalllogPresenter{
	private ICalllogModel model;
	private ICalllogView view;
	
	public CalllogPresenter(ICalllogView view) {
		this.view = view;
		this.model = new CalllogModel();
	}
	
	@Override
	public void loadAllCalllogs() {
		List<Calllog> logs = model.findAllCalllogs();
		//给视图层设置集合
		view.setCalllogList(logs);
		//更新view
		view.showList();
	}
	
}
