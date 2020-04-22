package cn.tedu.youlu.adapter;

import java.util.List;

import cn.tedu.youlu.entity.Calllog;
import cn.tedu.youlu.util.DateUtil;
import cn.tedu.youlu.util.ImageLoader;

import com.tarena.youlu.R;

import android.content.Context;
import android.graphics.Color;
import android.provider.CallLog.Calls;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class CalllogAdapter extends BaseAdapter{
	private Context context;
	private List<Calllog> logs;
	private ImageLoader imageLoader;
	
	public CalllogAdapter(Context context, ListView listView, List<Calllog> logs) {
		this.context = context;
		this.logs = logs;
		this.imageLoader = new ImageLoader(context, listView);
	}

	@Override
	public int getCount() {
		return logs.size();
	}

	@Override
	public Calllog getItem(int position) {
		return logs.get(position);
	}

	@Override
	public long getItemId(int position) {
		return logs.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if(convertView == null){
			convertView = View.inflate(context, R.layout.item_lv_calllog, null);
			holder = new ViewHolder();
			holder.ivPhoto = (ImageView) convertView.findViewById(R.id.ivPhoto);
			holder.ivIcon = (ImageView) convertView.findViewById(R.id.ivIcon);
			holder.tvName = (TextView) convertView.findViewById(R.id.tvName);
			holder.tvNumber = (TextView) convertView.findViewById(R.id.tvNumber);
			holder.tvDate = (TextView) convertView.findViewById(R.id.tvDate);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder)convertView.getTag();
		}
		//给holder中持有的控件赋值
		Calllog log = getItem(position);
		//设置头像
		imageLoader.displayImage(holder.ivPhoto, log.getPhotoId(), position);
		//设置名字
		holder.tvName.setText(log.getName()==null ? "未知号码":log.getName());
		//设置名字的颜色
		if(log.getType() == Calls.MISSED_TYPE){
			holder.tvName.setTextColor(Color.RED);
		}else{
			holder.tvName.setTextColor(Color.BLACK);
		}
		//设置是否显示icon
		if(log.getType() == Calls.OUTGOING_TYPE){
			holder.ivIcon.setVisibility(View.VISIBLE);
		}else{
			holder.ivIcon.setVisibility(View.INVISIBLE);
		}
		//设置电话号码
		holder.tvNumber.setText(log.getNumber());
		//设置时间
		holder.tvDate.setText(DateUtil.parse(log.getDate()));
		return convertView;
	}

	class ViewHolder{
		ImageView ivPhoto;
		ImageView ivIcon;
		TextView tvName;
		TextView tvNumber;
		TextView tvDate;
	}
	
}
