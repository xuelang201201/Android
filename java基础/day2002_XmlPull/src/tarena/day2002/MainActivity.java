package tarena.day2002;

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
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

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
		bt1 = (Button)findViewById(R.id.button1);
		bt2 = (Button)findViewById(R.id.button2);
		tv = (TextView)findViewById(R.id.textView1);
	}

	private void setListeners() {
		bt1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				duQu();//读取
			}
		});
		bt2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				shengCheng();//生成
			}
		});
	}

	protected void duQu() {
		try {

			//   /sdcard/email.xml
			FileInputStream in = new FileInputStream("/sdcard/email.xml");
			XmlPullParser p = Xml.newPullParser();//解析器，用来读取、解析xml文档

			p.setInput(in, "GBK");//设置输入流,GBK编码

			tv.setText("");

			int type;
			while((type = p.next()) != XmlPullParser.END_DOCUMENT) {
				if(type == XmlPullParser.START_TAG) {
					String name = p.getName();
					if("email".equals(name)) {
						String d = p.getAttributeValue(null, "date");
						String t = p.getAttributeValue(null, "time");
						tv.append("\n日期："+d);
						tv.append("\n时间："+t);
					} else if("from".equals(name)) {
						tv.append("\n发件人："+p.nextText());
					} else if("to-email".equals(name)) {
						tv.append("\n收件人："+p.nextText());
					} else if("subject".equals(name)) {
						tv.append("\n标题："+p.nextText());
					} else if("body".equals(name)) {
						tv.append("\n内容："+p.nextText());
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

	protected void shengCheng() {
		try {
			
			//   /sdcard/e.xml
			FileOutputStream out = new FileOutputStream("/sdcard/e.xml");
			
			XmlSerializer s = Xml.newSerializer();
			
			s.setOutput(out, "GBK");
			s.startDocument("GBK", true);
			s.startTag(null, "email")
			 .attribute(null, "date", "2016-1-26")
			 .attribute(null, "time", "15:34:30")
			 .startTag(null, "from")
			 .text("aaa@aaa.com")
			 .endTag(null, "from")
			 .startTag(null, "to")
			 .startTag(null, "to-email")
			 .text("aaa@aaa.com")
			 .endTag(null, "to-email")
			 .startTag(null, "to-email")
			 .text("bbb@bbb.com")
			 .endTag(null, "to-email")
			 .startTag(null, "to-email")
			 .text("ccc@ccc.com")
			 .endTag(null, "to-email")
			 .endTag(null, "to")
			 
			 .startTag(null, "subject")
			 .text("Hello Xml Pull")
			 .endTag(null, "subject")
			 .startTag(null, "body")
			 .text(">>>Hello Xml Pull!!!<<<");
			
			s.cdsect(">>>你好埃克斯爱慕哎呦普！！！<<<");
			
			s.endTag(null, "body")
			 .endTag(null, "email");
			
			s.flush();
			out.close();
			
			Toast.makeText(this, "生成成功", 0).show();
		} catch (Exception e) {
			Toast.makeText(this, "生成失败", 0).show();
			e.printStackTrace();
		}

	}
}
