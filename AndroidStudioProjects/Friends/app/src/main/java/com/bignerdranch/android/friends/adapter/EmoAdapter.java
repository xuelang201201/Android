package com.bignerdranch.android.friends.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bignerdranch.android.friends.R;
import com.bignerdranch.android.friends.bean.EmoBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class EmoAdapter extends MyBaseAdapter<EmoBean> {

	public EmoAdapter(Context context, List<EmoBean> datasource) {
		super(context, datasource);
	}

	@Override
	public View getItemView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.item_emo, parent, false);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		EmoBean emo = getItem(position);
		String id = emo.getId(); // [emo]ue057
		// [emo]ue057 -- 字符串截取 --> ue057 -- 方法 --> R.drawable.ue057
		String emoId = id.substring(5); // ue057
		// context.getResources().getIdentifier 可以通过该方法查出该资源名字所对应的资源id
		int resId = context.getResources().getIdentifier(emoId, "drawable", context.getPackageName());
		holder.ivEmo.setImageResource(resId);
		return convertView;
	}

	public class ViewHolder {
		
		@Bind(R.id.item_emo_image_view)
		ImageView ivEmo;

		public ViewHolder(View convertView) {
			ButterKnife.bind(this, convertView);
		}
	}
}
