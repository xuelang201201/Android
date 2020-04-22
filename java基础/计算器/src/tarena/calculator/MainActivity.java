package tarena.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Button c;
	private Button del;
	private Button btn_div;
	private Button btn_mul;
	private Button btn9;
	private Button btn8;
	private Button btn7;
	private Button btn_sub;
	private Button btn4;
	private Button btn5;
	private Button btn6;
	private Button btn_add;
	private Button btn1;
	private Button btn2;
	private Button btn3;
	private Button btn_equ;
	private Button btn0;
	private Button dot;
	private TextView window;

	
	double num1 = 0;
	double num2 = 0;
	double result = 0;//计算结果
	double resultType = 0;//判断输出的数是否有小数部分
	int pointCount = 0;
	int option = 0;//运算符状态
	boolean newdigital = true;//标记是否值新输入的数字
	boolean flag = true;//判断程序是否出错
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//去掉标题栏
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		c = (Button)findViewById(R.id.c);
		del = (Button)findViewById(R.id.del);
		btn_add = (Button)findViewById(R.id.btn_add);
		btn_div = (Button)findViewById(R.id.btn_div);
		btn_mul = (Button)findViewById(R.id.btn_mul);
		btn_sub = (Button)findViewById(R.id.btn_sub);
		btn_equ = (Button)findViewById(R.id.btn_equ);
		dot = (Button)findViewById(R.id.dot);
		btn0 = (Button)findViewById(R.id.btn0);
		btn1 = (Button)findViewById(R.id.btn1);
		btn2 = (Button)findViewById(R.id.btn2);
		btn3 = (Button)findViewById(R.id.btn3);
		btn4 = (Button)findViewById(R.id.btn4);
		btn5 = (Button)findViewById(R.id.btn5);
		btn6 = (Button)findViewById(R.id.btn6);
		btn7 = (Button)findViewById(R.id.btn7);
		btn8 = (Button)findViewById(R.id.btn8);
		btn9 = (Button)findViewById(R.id.btn9);
		window = (TextView)findViewById(R.id.window);
		
		c.setOnClickListener(listener);
		del.setOnClickListener(listener);
		dot.setOnClickListener(listener);
		btn1.setOnClickListener(listener);
		btn2.setOnClickListener(listener);
		btn3.setOnClickListener(listener);
		btn4.setOnClickListener(listener);
		btn5.setOnClickListener(listener);
		btn6.setOnClickListener(listener);
		btn7.setOnClickListener(listener);
		btn8.setOnClickListener(listener);
		btn9.setOnClickListener(listener);
		btn0.setOnClickListener(listener);
		btn_equ.setOnClickListener(listener);
		btn_add.setOnClickListener(listener);
		btn_sub.setOnClickListener(listener);
		btn_mul.setOnClickListener(listener);
		btn_div.setOnClickListener(listener);
	}
		
	
	
	@Override
	public void onClick(View v) {
		String s = window.getText().toString();//获得文本框显示的字符串
		Button btn = (Button) v;
		String t = (String)btn.getText();//获取按钮的字符
		
		//数字的输入
		if(btn.getId() == R.id.btn0 || btn.getId() == R.id.btn1 ||
	       btn.getId() == R.id.btn2 || btn.getId() == R.id.btn3 ||
		   btn.getId() == R.id.btn4 || btn.getId() == R.id.btn5 ||
		   btn.getId() == R.id.btn6 || btn.getId() == R.id.btn7 ||
		   btn.getId() == R.id.btn8 || btn.getId() == R.id.btn9 ||
		   (btn.getId() == R.id.dot && pointCount == 0)) {
			
			//Toast.makeText(MainActivity.this, btn.getText(), 1).show();
			//Toast.makeText(MainActivity.this, s, 1).show();
			
			if(btn.getId() == R.id.dot) {
				if(s == null || s.equals("")) {
					s += "0" + btn.getText();
				} else {
					s += btn.getText();
				}
				pointCount = 1;
			} else {
				s += btn.getText();
			}
			window.setText(s);
		}
		
		//运算符的输入
		if(btn.getId() == R.id.btn_add || btn.getId() == R.id.btn_div ||
		   btn.getId() == R.id.btn_mul || btn.getId() == R.id.btn_sub) {
			
			//Toast.makeText(MainActivity.this, btn.getText(), 1).show();
			
			//如果已经有两个数，再按运算符就直接把结果运算出来保存到 num1 中，然后继续运算
			if(s == null || s.equals("")) {
				s = "0";
			}
			
			if(option != 0) {
				num2 = Double.valueOf(s);
				
				switch(option) {
				case 1:
					result = num1 + num2;
					break;
				case 2:
					result = num1 - num2;
					break;
				case 3:
					result = num1 * num2;
					break;
				case 4:
					if(num2 == 0) {
						Toast.makeText(MainActivity.this, "0不能为除数", 1).show();
						window.setText("");
						break;
					}
					result = num1 / num2;
					break;
				
				default:
					break;
				}
				
				num1 = result;
			}
			
			if(option == 0) {
				num1 = Double.valueOf(s);
			}
			switch (btn.getId()) {
			case R.id.btn_add:
				option = 1;
				break;
			case R.id.btn_sub:
				option = 2;
				break;
			case R.id.btn_mul:
				option = 3;
				break;
			case R.id.btn_div:
				option = 4;
				break;
				
			default:
				break;
			}
			
			window.setText("");
		}
		
		// 等于，运算结果
		if(btn.getId() == R.id.btn_equ) {
			//Toast.makeText(MainActivity.this, btn.getText(), 1).show();
			
			if(s == null || s.equals("")){
				s = "0";
			}
			
			num2 = Double.valueOf(s);
			
			switch(option) {
			case 1: 
				result = num1 + num2;
				break;
			case 2:
				result = num1 - num2;
				break;
			case 3:
				result = num1 * num2;
				break;
			case 4:
				if(num2 == 0) {
					Toast.makeText(MainActivity.this, "除数不能为0", 1).show();
					window.setText("");
					flag = false;
					break;
				}
				
				result = num1 / num2;
				break;
			
			default:
				break;
			}
			
			resultType = result % 1;
			
			if(resultType > 0) {
				pointCount = 1;
			}
			
			s = " " + result;
			
			if(resultType == 0) {
				int end = (s.toString()).lastIndexOf(".");
				String str = (s.toString()).substring(0, end);
				
				s = " " + Integer.parseInt(str); 
				pointCount = 0;
			}
			
			if(flag) {
				window.setText(s);
			}
			
			num1 = Double.valueOf(s);
			option = 0;
			flag = true;
		}
		
		//清除
		if(btn.getId() == R.id.c) {
			//Toast.makeText(MainActivity.this, btn.getText(), 1).show();
			
			window.setText("");
			pointCount = 0;
			option = 0;
			flag = true;
		}
	}
}