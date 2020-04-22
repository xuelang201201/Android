package cn.tedu.youlu.presenter.impl;

import java.util.List;

import cn.tedu.youlu.entity.Sms;
import cn.tedu.youlu.model.ISmsModel;
import cn.tedu.youlu.model.impl.SmsModel;
import cn.tedu.youlu.presenter.ISmsPresenter;
import cn.tedu.youlu.view.ISmsView;

public class SmsPresenter implements ISmsPresenter {
	private ISmsModel model;
	private ISmsView view;
	
	public SmsPresenter(ISmsView view) {
		this.view = view;
		this.model = new SmsModel();
	}
	
	@Override
	public void loadSmsByThreadId(int id) {
		List<Sms> smss = model.findSmsByThreadId(id);
		view.setList(smss);
		view.showList();
	}

}
