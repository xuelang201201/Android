package cn.tedu.youlu.activity;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import cn.tedu.youlu.fragment.CalllogFragment;
import cn.tedu.youlu.fragment.ContactFragment;
import cn.tedu.youlu.fragment.ConversationFragment;
import cn.tedu.youlu.fragment.DialFragment;

import com.tarena.youlu.R;

public class MainActivity extends FragmentActivity {
	private ViewPager viewPager;
	private ArrayList<Fragment> fragments;
	private MyPagerAdapter adapter;
	private RadioGroup radioGroup;
	private RadioButton rb1;
	private RadioButton rb2;
	private RadioButton rb3;
	private RadioButton rb4;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setViews();
		setAdapter();
		setListeners();
	}
	
	/**
	 * 添加监听  控制联动
	 */
	private void setListeners() {
		//滑动viewpager控制导航
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {
			public void onPageSelected(int position) {
				switch (position) {
				case 0:
					rb1.setChecked(true);
					break;
				case 1:
					rb2.setChecked(true);
					break;
				case 2:
					rb3.setChecked(true);
					break;
				case 3:
					rb4.setChecked(true);
					break;
				}
			}
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				
			}
			public void onPageScrollStateChanged(int arg0) {
				
			}
		});
		
		//点击导航  控制viewpager
		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.rb1:
					viewPager.setCurrentItem(0);
					break;
				case R.id.rb2:
					viewPager.setCurrentItem(1);
					break;
				case R.id.rb3:
					viewPager.setCurrentItem(2);
					break;
				case R.id.rb4:
					viewPager.setCurrentItem(3);
					break;
				}
			}
		});
	}

	/**
	 * 给viewPager设置适配器
	 */
	private void setAdapter() {
		//构建viewpager使用的fragment数据源
		fragments = new ArrayList<Fragment>();
		fragments.add(new CalllogFragment());
		fragments.add(new ContactFragment());
		fragments.add(new ConversationFragment());
		fragments.add(new DialFragment());
		adapter = new MyPagerAdapter(getSupportFragmentManager());
		viewPager.setAdapter(adapter);
	}

	/**
	 * 控件初始化
	 */
	private void setViews() {
		viewPager = (ViewPager) findViewById(R.id.viewPager);
		radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
		rb1 = (RadioButton) findViewById(R.id.rb1);
		rb2 = (RadioButton) findViewById(R.id.rb2);
		rb3 = (RadioButton) findViewById(R.id.rb3);
		rb4 = (RadioButton) findViewById(R.id.rb4);
		
	}

	/**
	 * viewpager适配器
	 */
	class MyPagerAdapter extends FragmentPagerAdapter{
		public MyPagerAdapter(FragmentManager fm) {
			super(fm);
		}
		public Fragment getItem(int arg0) {
			return fragments.get(arg0);
		}
		public int getCount() {
			return fragments.size();
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
