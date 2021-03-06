package cn.tedu.listview_section;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;

public class ContactAdapter extends BaseAdapter implements SectionIndexer {

	private List<Contact> contacts;

	private Context context;

	public void setContacts(List<Contact> contacts) {
		if (contacts == null) {
			contacts = new ArrayList<Contact>();
		}
		this.contacts = contacts;
	}

	public void setContext(Context context) {
		if (context == null) {
			throw new IllegalArgumentException("参数Context不可以为null！！！");
		}
		this.context = context;
	}

	public ContactAdapter(Context context, List<Contact> contacts) {
		super();
		this.contacts = contacts;
		this.context = context;
	}

	@Override
	public int getCount() {
		//返回数据源的数量
		return contacts.size();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		//1.获取列表项的数据
		Contact contact = contacts.get(position);
		//2.获取显示列表项的View对象，通过模板加载得到
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.contact_item, null);
		//3.组装数据中的各个属性与View对象中的各个控件
		TextView sortKey = (TextView) view.findViewById(R.id.tv_contact_item_sort_key);
		ImageView photo = (ImageView) view.findViewById(R.id.iv_contact_item_photo);
		TextView name = (TextView) view.findViewById(R.id.tv_contact_item_name);
		TextView number = (TextView) view.findViewById(R.id.tv_contact_item_number);

		photo.setImageResource(contact.getPhoto());
		sortKey.setText("" + contact.getName().charAt(0));
		name.setText(contact.getName());
		number.setText(contact.getNumber());
		//-- 进阶 --
		// 1. 根据当前position获取应该显示的section(首字母)
		int section = getSectionForPosition(position);
		// 2. 获取该section应该出现的位置
		int pos = getPositionForSection(section);
		// 3. 判断当前列表项的position是否和该首字母应该出现的position是否相等
		if(position == pos) {
			sortKey.setVisibility(View.VISIBLE);
		} else {
			sortKey.setVisibility(View.GONE);
		}

		// -- 传统思路：判断是否显示分类字母 --
		// 逻辑：
		// 1) 第1条数据(position=0)一定显示
		// 2) 与上一条首字母相同，则不显示，反之，则显示

		/* 以下是精简版代码 */
		/*sortKey.setVisibility(View.VISIBLE);
		int currentSection = contact.getName().charAt(0);
		if (position != 0 && contacts.get(position - 1).getName().charAt(0) == currentSection) {
			sortKey.setVisibility(View.GONE);
		}*/

		/* 以下是一般代码 */
		/*if(position == 0) {
			// 显示
			sortKey.setVisibility(View.VISIBLE);
		} else {
			// 判断首字母
			int lastSection = contacts.get(position - 1).getName().charAt(0);
			int currentSection = contact.getName().charAt(0);
			if(lastSection == currentSection) {
				sortKey.setVisibility(View.GONE);
			} else {
				sortKey.setVisibility(View.VISIBLE);
			}
		}*/

		//4.返回组装数据后的View对象
		return view;
	}

	@Override
	public int getPositionForSection(int section) {
		// 为Section(首字母)获取Position(位置)：获取某个首字母应该出现的位置
		for (int i = 0; i < contacts.size(); i++) {
			int ch = contacts.get(i).getName().charAt(0);
			if(ch == section) {
				return i;
			}
		}
		return NO_SUCH_SECTION;
	}

	public static final int NO_SUCH_SECTION = -999;
	
	@Override
	public int getSectionForPosition(int position) {
		// 为Position(位置)获取Section(首字母)：获取某个位置上应该出现的首字母
		return contacts.get(position).getName().charAt(0);
	}
	
	@Override
	public Object[] getSections() {
		// 问题1：数组的长度未知
		// 解决方案：使用集合
		// 问题2：重复的字母如何处理
		// 解决方案：集合有contains()方法 / Set集合的内容不可重复
		
		Set<String> set = new TreeSet<String>();
		for(int i = 0; i < contacts.size(); i++) {
			set.add("" + (char)getSectionForPosition(i));
		}
		
		String[] sections = new String[set.size() + 2];
		sections[0] = "#";
		sections[sections.length - 1] = "#";
		int i = 1;
		for(String str : set) {
			sections[i] = str;
			i++;
		}
		
		return sections;
	}
	
	@Override
	public Object getItem(int position) {
		//（无视）
		return null;
	}

	@Override
	public long getItemId(int position) {
		//（无视）
		return 0;
	}
}
