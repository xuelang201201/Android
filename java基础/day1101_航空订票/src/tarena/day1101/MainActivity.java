package tarena.day1101;

import tarena.day1101.service.UserService;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends Activity {

	private EditText et1;
	private EditText et2;
	private ImageButton ib1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//ȥ��������
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		setViews();
		setListeners();	
	}

	private void setViews() {
		et1 = (EditText)findViewById(R.id.et1);
		et2 = (EditText)findViewById(R.id.et2);
		ib1 = (ImageButton)findViewById(R.id.ib1);
	}

	private void setListeners() {
		ib1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Login(); //��¼
			}
		});
	}

	protected void Login() {
		/* //������̵Ĵ���
		 * 1.��������ȡ�û�������
		 * 2.�ӷ������˻��ϵͳ���е��û���Ϣ
		 * 3.�����ȶ�
		 *     4.�ҵ���ͬ���û�������
		 *         5.��¼�ɹ���������һ������
		 *         6.��������
		 * 7.��¼ʧ�ܣ���ʾʧ����ʾ
		 * 
		 * -------------------------
		 * //�������Ĵ���
		 * 1.��������ȡ�û�������
		 * 2.�½�UserService ʵ������serv
		 * 3.����serv.login(�û���������)
		 *   ���õ����ķ���ֵ����b
		 * 4.��� b ��true
		 *     5.��ת����
		 * 6.����
		 *     7.��ʾʧ����ʾ
		 */
		
		String n = et1.getText().toString();
		String p = et2.getText().toString();
		UserService serv = new UserService();
		boolean b = serv.login(n, p);
		if(b) {
			startActivity(new Intent(this, MenuActivity.class));
			//���ٵ�¼����
			finish(); //��Activity����̳еķ���
		} else {
			Toast.makeText(this, "��¼ʧ�ܣ������û�������", 0).show();
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
