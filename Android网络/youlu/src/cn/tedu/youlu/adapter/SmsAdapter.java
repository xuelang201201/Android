package cn.tedu.youlu.adapter;

import cn.tedu.youlu.entity.Sms;

import com.tarena.youlu.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SmsAdapter extends BaseAdapter {
	private Context context;
	private List<Sms> smss;
	private LayoutInflater inflater;

	public SmsAdapter(Context context, List<Sms> smss) {
		this.context = context;
		this.smss = smss;
		this.inflater = LayoutInflater.from(context);
	}

	public int getCount() {
		return smss.size();
	}

	@Override
	public Sms getItem(int position) {
		return smss.get(position);
	}

	@Override
	public long getItemId(int position) {
		return smss.get(position).getId();
	}
	
	public static final int TYPE_LEFT = 0;
	public static final int TYPE_RIGHT = 1;
	/**
	 * 判断当前position的item属于什么类型
	 * @param position
	 * @return
	 */
	public int getType(int position){
		Sms sms = getItem(position);
		if(sms.getType()==1){ //别人发的放左边
			return TYPE_LEFT;
		}else{
			return TYPE_RIGHT;
		}
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		//如果没有可以重用的convertView
		//或者   即将加载的item与当前
		//重用的item的布局不相同时都需要
		//重新加载布局。
		if(convertView == null  ||  
				getType(position) != ((ViewHolder)convertView.getTag()).type){
			//如果是左边的类型
			if(getType(position)==TYPE_LEFT){
				convertView = inflater.inflate(R.layout.item_lv_sms_left, null);
			}else{
				convertView = inflater.inflate(R.layout.item_lv_sms_right, null);
			}
			holder = new ViewHolder();
			holder.ivPhoto = (ImageView) convertView.findViewById(R.id.ivPhoto);
			holder.tvDate = (TextView) convertView.findViewById(R.id.tvDate);
			holder.tvBody = (TextView) convertView.findViewById(R.id.tvBody);
			holder.type = getType(position);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		//给控件赋值
		Sms sms = getItem(position);
		holder.tvDate.setText(new SimpleDateFormat("MM-dd HH:mm:ss").format(new Date(sms.getDate())));
		holder.tvBody.setText(sms.getBody());
		return convertView;
	}

	class ViewHolder{
		ImageView ivPhoto;
		TextView tvDate;
		TextView tvBody;
		int type;
	}
}
