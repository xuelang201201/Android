package cn.tedu.android_day03_ems;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class EmpAdapter extends BaseAdapter {

	private List<Emp> emps;
	private Context context;
	private LayoutInflater inflater;
	
	public EmpAdapter(Context context, List<Emp> emps) {
		this.context = context;
		this.emps = emps;
		this.inflater = LayoutInflater.from(context);
	}
	

	@Override
	public int getCount() {
		return emps.size();
	}

	@Override
	public Emp getItem(int position) {
		return emps.get(position);
	}

	@Override
	public long getItemId(int position) {
		return getItem(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.emp_item, null);
			holder = new ViewHolder();
			holder.tvId = (TextView) convertView.findViewById(R.id.tv_id);
			holder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
			holder.tvAge = (TextView) convertView.findViewById(R.id.tv_age);
			holder.tvSalary = (TextView) convertView.findViewById(R.id.tv_salary);
			holder.tvGender = (TextView) convertView.findViewById(R.id.tv_gender);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		//给holder中的控件设置值
		Emp emp = getItem(position);
		holder.tvId.setText("" + emp.getId());
		holder.tvName.setText(emp.getName());
		holder.tvAge.setText("" + emp.getAge());
		holder.tvSalary.setText("" + emp.getSalary());
		holder.tvGender.setText(emp.getGender().equals("m") ? "男士" : "女士");
		return convertView;
	}

	class ViewHolder {
		TextView tvId;
		TextView tvName;
		TextView tvAge;
		TextView tvSalary;
		TextView tvGender;
	}
}
