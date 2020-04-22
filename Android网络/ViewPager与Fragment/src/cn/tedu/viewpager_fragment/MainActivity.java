package cn.tedu.viewpager_fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

public class MainActivity extends FragmentActivity {
	
	private ViewPager vpViewPager;
	private PagerAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		vpViewPager = (ViewPager) findViewById(R.id.vp_view_pager);
		
		adapter = new InnerAdapter(getSupportFragmentManager());
		vpViewPager.setAdapter(adapter);
	}
	
	private class InnerAdapter extends FragmentPagerAdapter {

		public InnerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			Fragment fragment = null;
			switch (position) {
			case 0:
				fragment = new Page01Fragment();
				break;

			case 1:
				fragment = new Page02Fragment();
				break;
				
			case 2:
				fragment = new Page03Fragment();
				break;
			}
			return fragment;
		}

		@Override
		public int getCount() {
			return 3;
		}
		
	}
}
