package cn.tedu.myy.activity;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import cn.tedu.myy.R;
import cn.tedu.myy.adapter.ModuleAdapter;
import cn.tedu.myy.biz.PageModuleBiz;
import cn.tedu.myy.entity.PageModule;

public class ChooseActivity extends Activity {

	private GridView gvModules;
	private List<PageModule> modules;
	private boolean isFirst = true;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choose);
		isFirst = getIntent().getBooleanExtra("isFirst", true);
		setViews();
		setListeners();
		new PageModuleBiz(this).loadAllModules();
	}

	private void setListeners() {
		gvModules.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	private void setViews() {
		gvModules = (GridView) findViewById(R.id.gv_modules);
	}

	public void updateGridView(List<PageModule> modules) {
		this.modules = modules;
		//¡¡setAdapter
		ModuleAdapter adapter = new ModuleAdapter(this, modules);
		gvModules.setAdapter(adapter);
	}

}
