package cn.tedu.youlu.presenter.impl;

import java.util.List;

import cn.tedu.youlu.entity.Calllog;
import cn.tedu.youlu.model.ICalllogModel;
import cn.tedu.youlu.model.impl.CalllogModel;
import cn.tedu.youlu.presenter.ICalllogPresenter;
import cn.tedu.youlu.presenter.IDialPresenter;
import cn.tedu.youlu.view.ICalllogView;
import cn.tedu.youlu.view.IDialView;

public class DialPresenter implements IDialPresenter{
	private ICalllogModel model;
	private IDialView view;
	
	public DialPresenter(IDialView view) {
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
