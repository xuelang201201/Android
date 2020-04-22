package tarena.day09night;

import android.widget.TextView;

public class AK47 implements Weapon {

	@Override
	public int getType() {
		return TYPE_HEAT;
	}

	@Override
	public String getName() {
		return "AK47";
	}

	@Override
	public void kill(TextView textView) {
		textView.append("\n¥Ú«π");
	}
	
}
