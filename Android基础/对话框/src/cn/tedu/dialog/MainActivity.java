package cn.tedu.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener, DialogInterface.OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		findViewById(R.id.btn_show_alert_dialog).setOnClickListener(this);
		findViewById(R.id.btn_show_confirm_dialog).setOnClickListener(this);
		findViewById(R.id.btn_show_custom_content_dialog).setOnClickListener(this);
		findViewById(R.id.btn_show_progress_dialog).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_show_alert_dialog:
			showAlertDialog();
			break;

		case R.id.btn_show_confirm_dialog:
			showConfirmDialog();
			break;
			
		case R.id.btn_show_custom_content_dialog:
			showCustomContentDialog();
			break;
			
		case R.id.btn_show_progress_dialog:
			showProgressDialog();
			break;
		}
	}

	private AlertDialog alertDialog;
	private AlertDialog confirmDialog;
	private ProgressDialog progressDialog;
	
	public void showProgressDialog() {
		progressDialog = new ProgressDialog(this);
		progressDialog.setIcon(R.drawable.ic_launcher);
		progressDialog.setTitle("��������");
		progressDialog.setMessage("������-���¹�.mp3");
		progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		progressDialog.setMax(100);
		// progressDialog.setButton(DialogInterface.BUTTON_POSITIVE, "ȷ��", this);
		progressDialog.show();
		
		new Thread() {
			class Runner implements Runnable {

				@Override
				public void run() {
					progressDialog.setProgress(i);
					
					if (i == 100) {
						progressDialog.dismiss();
					}
				}
			}
			
			private int i = 1;
			
			public void run() {
				Runner runner = new Runner();
				for (; i <= 100; i++) {
					runOnUiThread(runner);
					
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			
		}.start();
	}
	
	public void showAlertDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("����");
		builder.setIcon(R.drawable.ic_launcher);
		builder.setMessage("�������Ž������޶��������ã�" + "\n" +
				"��Ϊ��ȡ�����ţ��������������á�����ʹ���������š�����");
		builder.setPositiveButton("ȷ��", this);
		builder.setCancelable(false);
		alertDialog = builder.create();
		alertDialog.show();
	}
	
	public void showConfirmDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		confirmDialog = 
			builder
				.setTitle("��ȷ��")
				.setIcon(R.drawable.ic_launcher)
				.setMessage("�������㣬�Ƿ��ֵ��")
				.setPositiveButton("ȡ��", this)
				.setNegativeButton("ȷ��", this)
				.setCancelable(false)
				.create();
		confirmDialog.show();
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		if (dialog == alertDialog) {
			Toast.makeText(this, "������˾���Ի����еİ�ť��", Toast.LENGTH_LONG).show();
		} else if (dialog == confirmDialog) {
			switch (which) {
			case DialogInterface.BUTTON_POSITIVE:
				Toast.makeText(this, "�������ȷ�϶Ի����е�ȡ����ť��", Toast.LENGTH_LONG).show();
				break;

			case DialogInterface.BUTTON_NEGATIVE:
				Toast.makeText(this, "�������ȷ�϶Ի����е�ȷ�ϰ�ť��", Toast.LENGTH_LONG).show();
				break;
			}
		}
	}
	
	public void showCustomContentDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		View content = getLayoutInflater().inflate(R.layout.reg_dialog_content, null);
		AlertDialog dialog = 
			builder
				.setTitle("�û�ע��")
				.setView(content)
				.setPositiveButton("ע��", null)
				.setNegativeButton("ȡ��", null)
				.create();
		dialog.show();
	}
}
