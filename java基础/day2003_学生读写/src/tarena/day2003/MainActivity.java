package tarena.day2003;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

import android.app.Activity;
import android.os.Bundle;
import android.util.Xml;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private EditText et1;
	private EditText et2;
	private EditText et3;
	private EditText et4;
	private Button bt1;
	private Button bt2;
	private TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		setViews();
		setListeners();
	}

	private void setViews() {
		et1 = (EditText)findViewById(R.id.editText1);
		et2 = (EditText)findViewById(R.id.editText2);
		et3 = (EditText)findViewById(R.id.editText3);
		et4 = (EditText)findViewById(R.id.editText4);
		bt1 = (Button)findViewById(R.id.button1);
		bt2 = (Button)findViewById(R.id.button2);
		tv = (TextView)findViewById(R.id.textView1);
	}

	private void setListeners() {
		bt1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				save();
			}
		});

		bt2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				read();
			}
		});
	}

	protected void save() {
		
		try {
			/*
			 *     /sdcard/stu.xml
			 * 
			 * <student>
			 * 		<id>xx</id>
			 * 		<name>xxx</name>
			 * 		<gender>xxx</gender>
			 * 		<age>x</age>
			 * </student>
			 */
			FileOutputStream out = new FileOutputStream("/sdcard/stu.xml");

			XmlSerializer s = Xml.newSerializer();

			s.setOutput(out, "GBK");
			s.startTag(null, "student")
			 .startTag(null, "id")
			 .text(et1.getText().toString())
			 .endTag(null, "id")
			 .startTag(null, "name")
			 .text(et2.getText().toString())
			 .endTag(null, "name")
			 .startTag(null, "gender")
			 .text(et3.getText().toString())
			 .endTag(null, "gender")
			 .startTag(null, "age")
		  	 .text(et4.getText().toString())
			 .endTag(null, "age")
			 .endTag(null, "student");

			s.flush();
			out.close();

			Toast.makeText(this, "保存成功", 0).show();
		} catch (Exception e) {
			Toast.makeText(this, "保存失败", 0).show();
			e.printStackTrace();
		}
	}

	protected void read() {

		try {
			FileInputStream in = new FileInputStream("/sdcard/stu.xml");
			XmlPullParser p = Xml.newPullParser();
			p.setInput(in, "GBK");
			
			tv.setText("");
			
			int type;
			while((type = p.next()) != 1) {
				if(type == XmlPullParser.START_TAG) {
					String name = p.getName();
					if("id".equals(name)) {
						tv.append("\n学号："+p.nextText());
					} else if("name".equals(name)){
						tv.append("\n姓名："+p.nextText());
					} else if("gender".equals(name)){
						tv.append("\n性别："+p.nextText());
					} else if("age".equals(name)){
						tv.append("\n年龄："+p.nextText());
					}
				}
			}
			
			in.close();
			
			Toast.makeText(this, "读取成功", 0).show();
		} catch (Exception e) {
			Toast.makeText(this, "读取失败", 0).show();
			e.printStackTrace();
		}
	}
}
