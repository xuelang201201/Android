package tarena.day1101;

import tarena.day1101.service.BranchService;

import com.tarena.abs.datasource.entities.Branch;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

public class BranchSearchActivity extends Activity {

	private String[] cities;
	private Spinner sp;
	private Button bt;
	private ListView lv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//去掉标题栏
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_branch_search);
		
		setData(); //准备要显示的城市列表数据
		setViews();//获得控件并显示城市列表数据
		setListeners();
	}

	private void setData() {
		cities = new String[] {
				"北京","上海","重庆"
		};
	}

	private void setViews() {
		sp = (Spinner)findViewById(R.id.sp1);
		bt = (Button)findViewById(R.id.bt1);
		lv = (ListView)findViewById(R.id.lv1);
		
		//下拉菜单sp显示城市数组cities
		//一段神秘的代码
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				this,
				android.R.layout.simple_spinner_item, 
				cities);
		
		adapter.setDropDownViewResource(
				android.R.layout.simple_spinner_dropdown_item);
		
		sp.setAdapter(adapter);
	}

	private void setListeners() {
		bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Inquiry(); //查询
			}
		});
	}

	protected void Inquiry() {
		/*
		 * 1.获得选择的城市赋给city
		 * 2.新建BranchService对象赋给serv
		 * 3.调用serv.findByCity(city)
		 *   获得它返回的Branch[]赋给branches
		 * 4.新建String[]数组赋给a
		 *   数组长度是branches.length
		 * 5.遍历branches数组
		 *     6.取出i位置的Branch对象赋给b
		 *     7.将对象b中的属性连接成字符串，
		 *       赋值到a[i]
		 * 8.在列表控件lv中显示a数组的数据	
		 */
		String city = (String) sp.getSelectedItem();
		
		BranchService serv = new BranchService();
		Branch[] branches = serv.findByCity(city);
		String[] a = new String[branches.length];
		
		for(int i=0; i<a.length; i++) {
			Branch b = branches[i];
			a[i] = 
					"\n编号："+b.getId()+
					"\n名称："+b.getName()+
					"\n地址："+b.getAddress()+
					"\n电话："+b.getTelephone()+
					"\n传真："+b.getFax();
		}
		
		//一段神秘的代码
		ArrayAdapter<String> adapter = 
				 new ArrayAdapter<String>(
				 this,
				 android.R.layout.simple_list_item_1,
				 a);
				
				lv.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.branch_search, menu);
		return true;
	}

}
