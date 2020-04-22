package cn.tedu.myy.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import cn.tedu.myy.R;
import cn.tedu.myy.entity.PageModule;
import cn.tedu.myy.util.GlobalConsts;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

public class ModuleAdapter extends BaseAdapter {

	private Context context;
	private List<PageModule> modules;
	private LayoutInflater inflater;
	private DisplayImageOptions options;
	private ImageLoader imageLoader = ImageLoader.getInstance();
	
	public ModuleAdapter(Context context, List<PageModule> modules) {
		super();
		this.context = context;
		this.modules = modules;
		this.inflater = LayoutInflater.from(context);
		options = new DisplayImageOptions.Builder()
			.showImageForEmptyUri(R.drawable.ic_launcher)
			.showImageOnFail(R.drawable.ic_launcher)
			.cacheInMemory(true)
			.cacheOnDisc(true)
			.build();
	}

	@Override
	public int getCount() {
		return modules.size();
	}

	@Override
	public Object getItem(int position) {
		return modules.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ImageView ivModule;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.module_item, null);
			ivModule = (ImageView) convertView.findViewById(R.id.iv_module);
		} else {
			ivModule = (ImageView) convertView.findViewById(R.id.iv_module);
		}
		
		// 将图片显示任务增加到执行池，图片将会被显示到ImageView，当轮到该ImageView时
		imageLoader.displayImage(GlobalConsts.BASEURL + modules.get(position).getSnapshot(), ivModule, options);
		
		return convertView;
	}

}
