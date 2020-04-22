package cn.tedu.youlu.fragment;

import java.util.List;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import cn.tedu.youlu.adapter.ContactAdapter;
import cn.tedu.youlu.entity.Contact;
import cn.tedu.youlu.presenter.IContactPresenter;
import cn.tedu.youlu.presenter.impl.ContactPresenter;
import cn.tedu.youlu.util.BitmapUtils;
import cn.tedu.youlu.view.IContactView;

import com.tarena.youlu.R;

public class ContactFragment extends Fragment implements IContactView{
	private IContactPresenter presenter;
	private GridView gridView;
	private List<Contact> contacts;
	
	public ContactFragment() {
		presenter = new ContactPresenter(this);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_contact, null);
		//初始化控件
		gridView = (GridView) view.findViewById(R.id.gvContact);
		//添加监听
		setListener();
		//onCreateView 加载所有联系人
		presenter.loadAllContacts();
		return view;
	}

	//添加监听
	private void setListener() {
		gridView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				//获取点击的item
				Contact contact=contacts.get(position);
				//弹窗
				AlertDialog.Builder builder = new Builder(getActivity());
				AlertDialog alertDialog=builder.create();
				alertDialog.show();
				//自定义alertDialog的界面
				Window window = alertDialog.getWindow();
				View dialogView = View.inflate(getActivity(),R.layout.contact_detail , null);
				//给dialogView中的imageView即textView赋值
				ImageView ivPhoto=(ImageView) dialogView.findViewById(R.id.photo);
				Bitmap bitmap=BitmapUtils.loadBitmap(getActivity(), contact.getPhotoId());
				//设置bitmap
				if(bitmap!=null){
					ivPhoto.setImageBitmap(bitmap);
				}
				TextView tvName=(TextView) dialogView.findViewById(R.id.textView1);
				tvName.setText(contact.getName());
				window.setContentView(dialogView);
			}
		});
	}

	@Override
	public void showContactList(List<Contact> contacts) {
		this.contacts = contacts;
		//自定义GridView的Adapter
		ContactAdapter adapter = new ContactAdapter(contacts, getActivity(), gridView);
		//设置Adapter
		gridView.setAdapter(adapter);
		//Log.i("info", "list:"+contacts);
	}
	
}
