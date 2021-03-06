package cn.tedu.listview_baseadapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ContactAdapter extends BaseAdapter {

	/**
	 * 数据源：联系人的数据List的集合
	 */
	private List<Contact> contacts;
	/**
	 * 上下文对象
	 */
	private Context context;
	
	/**
	 * 构造方法
	 * @param contacts 数据源：联系人的数据的List集合
	 */
	public ContactAdapter(Context context, List<Contact> contacts) {
		this.context = context;
		this.contacts = contacts;
	}

	@Override
	public int getCount() {
		//返回数据源的数据数量
		return contacts.size();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		//---- 返回每一个列表项的视图 ----
		// 1.准备好当前这一条列表项的数据
		Contact contact = contacts.get(position);
		// 2.准备好模板：将模板的XML加载为程序中的View对象
		ViewHolder holder;
		if(convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(context);
			convertView = inflater.inflate(R.layout.contact_item, null);
			holder = new ViewHolder();
			holder.photo = (ImageView) convertView.findViewById(R.id.iv_contact_item_photo);
			holder.name  = (TextView) convertView.findViewById(R.id.tv_contact_item_name);
			holder.number = (TextView) convertView.findViewById(R.id.tv_contact_item_number);
			convertView.setTag(holder); //ConvertViewTag
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		// 3.向模板中填充数据
		holder.photo.setImageResource(contact.photo);
		holder.name.setText(contact.name);
		holder.number.setText(contact.number);
		//4.返回
		return convertView;
	}

	private class ViewHolder {
		ImageView photo;
		TextView name;
		TextView number;
	}
	
	@Override
	public Object getItem(int position) {
		// 无视
		return null;
	}

	@Override
	public long getItemId(int position) {
		// 无视
		return 0;
	}
}
