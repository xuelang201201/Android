package cn.tedu.file_io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {
	
	private EditText etContent;
	private Button btnSave;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		etContent = (EditText) findViewById(R.id.et_content);
		btnSave = (Button) findViewById(R.id.btn_save);
		
		btnSave.setOnClickListener(this);
		
		// ��ȡ�ļ�
		readFile();
	}
	
	// ��ȡ�ļ�������
	public void readFile() {
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		
		try {
			fis = openFileInput("content.txt");
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);
					
			StringBuffer content = new StringBuffer();
			String line = null;
			while((line = br.readLine()) != null){
				content.append(line);
			}
			
			etContent.setText(content);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		Toast.makeText(this, "��ȡ�ļ��ɹ�", Toast.LENGTH_LONG).show();
	}

	@Override
	public void onClick(View v) {
		// 1. ��ȡEditText�е�����
		String content = etContent.getText().toString();
		// 2. д�뵽�ļ�
		FileOutputStream fos = null;
		try {
			fos = openFileOutput("content.txt", MODE_PRIVATE);
			fos.write(content.getBytes());
			fos.flush();
			Toast.makeText(this, "д���ļ��ɹ�", Toast.LENGTH_SHORT).show();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
