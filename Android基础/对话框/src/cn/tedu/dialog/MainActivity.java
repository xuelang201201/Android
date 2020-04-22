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
		progressDialog.setTitle("正在下载");
		progressDialog.setMessage("张信哲-白月光.mp3");
		progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		progressDialog.setMax(100);
		// progressDialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定", this);
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
		builder.setTitle("警告");
		builder.setIcon(R.drawable.ic_launcher);
		builder.setMessage("继续播放将产生巨额流量费用！" + "\n" +
				"已为您取消播放，可在设置中设置“允许使用流量播放”功能");
		builder.setPositiveButton("确定", this);
		builder.setCancelable(false);
		alertDialog = builder.create();
		alertDialog.show();
	}
	
	public void showConfirmDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		confirmDialog = 
			builder
				.setTitle("请确认")
				.setIcon(R.drawable.ic_launcher)
				.setMessage("假期余额不足，是否充值？")
				.setPositiveButton("取消", this)
				.setNegativeButton("确认", this)
				.setCancelable(false)
				.create();
		confirmDialog.show();
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		if (dialog == alertDialog) {
			Toast.makeText(this, "您点击了警告对话框中的按钮！", Toast.LENGTH_LONG).show();
		} else if (dialog == confirmDialog) {
			switch (which) {
			case DialogInterface.BUTTON_POSITIVE:
				Toast.makeText(this, "您点击了确认对话框中的取消按钮！", Toast.LENGTH_LONG).show();
				break;

			case DialogInterface.BUTTON_NEGATIVE:
				Toast.makeText(this, "您点击了确认对话框中的确认按钮！", Toast.LENGTH_LONG).show();
				break;
			}
		}
	}
	
	public void showCustomContentDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		View content = getLayoutInflater().inflate(R.layout.reg_dialog_content, null);
		AlertDialog dialog = 
			builder
				.setTitle("用户注册")
				.setView(content)
				.setPositiveButton("注册", null)
				.setNegativeButton("取消", null)
				.create();
		dialog.show();
	}
}
