package tarena.day0904;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/*
 * ��������ʱ��
 * ϵͳ���Զ����� MainActivity ʵ����
 * Ȼ��������������� onCreate() ����
 */
public class MainActivity extends Activity {
	/*
	 * ���ڸ��� Activity �ж���ķ�����
	 * ���������У�������д�������
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		/* �����ȵ��ø���� onCreate() ����
		 * �������д��룬��������Ƿ���ù����෽��
		 */
		super.onCreate(savedInstanceState);
		/* �ڵ�ǰ�����У���ʾ�ƶ��Ľ���
		*/
		setContentView(R.layout.activity_main);
		
		//��ý����ϵİ�ť�ռ����
		Button bt = (Button) findViewById(R.id.button1);
		
		//Ϊ��ť���ü�����
		bt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//1.дֽ��
				Intent intent = new Intent(
						MainActivity.this,      //��ǰ���ڶ���
						SecondActivity.class);  //Ŀ�������
				//2.��ֽ��
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
