package tarena.day09night;

import android.widget.TextView;

public class Sword implements Weapon {

	@Override
	public int getType() {
		return TYPE_COLD;
	}

	@Override
	public String getName() {
		return "ÒÐÌì½£";
	}

	@Override
	public void kill(TextView textView) {
		textView.append("\nË£½£");		
	}
}
