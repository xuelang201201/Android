package tarena.day09night;

import android.widget.TextView;
/*�����ӿ�*/
public interface Weapon {
	int TYPE_COLD = 1;
	int TYPE_HEAT = 2;
	int TYPE_NUCLEAR = 3;
	
	int getType(); //���ص�ǰ����������
	String getName(); //����������
	void kill(TextView textView); 
}
