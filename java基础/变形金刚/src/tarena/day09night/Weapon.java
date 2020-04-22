package tarena.day09night;

import android.widget.TextView;
/*武器接口*/
public interface Weapon {
	int TYPE_COLD = 1;
	int TYPE_HEAT = 2;
	int TYPE_NUCLEAR = 3;
	
	int getType(); //返回当前的武器类型
	String getName(); //武器的名称
	void kill(TextView textView); 
}
