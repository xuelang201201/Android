package cn.tedu.youlu.adapter;

import java.util.List;

import cn.tedu.youlu.entity.Contact;
import cn.tedu.youlu.util.ImageLoader;

import com.tarena.youlu.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class ContactAdapter extends BaseAdapter{
	private List<Contact> contacts;
	private Context context;
	private ImageLoader imageLoader;
	
	public ContactAdapter(List<Contact> contacts, Context context, GridView gridView) {
		this.contacts = contacts;
		this.contacts.add(0, new Contact());
		this.context = context;
		this.imageLoader = new ImageLoader(context, gridView);
	}

	@Override
	public int getCount() {
		return contacts.size();
	}

	@Override
	public Contact getItem(int position) {
		return contacts.get(position);
	}

	@Override
	public long getItemId(int position) {
		return contacts.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if(convertView == null){
			convertView = View.inflate(context, R.layout.item_gv_contact, null);
			holder = new ViewHolder();
			holder.ivPhoto = (ImageView) convertView.findViewById(R.id.ivPhoto);
			holder.tvName = (TextView) convertView.findViewById(R.id.tvName);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		//如果是第一个的话
		if(position == 0){
			holder.ivPhoto.setImageResource(R.drawable.img02_07);
			holder.tvName.setText("添加联系人");
			return convertView;
		}
		
		//给holder中的内容进行赋值
		Contact contact = getItem(position);
		holder.tvName.setText(contact.getName());
		//设置头像
		imageLoader.displayImage(holder.ivPhoto, contact.getPhotoId(), position);
		return convertView;
	}
	
	class ViewHolder{
		ImageView ivPhoto;
		TextView tvName;
	}
	
}
