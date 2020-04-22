package tarena.day09night;

import android.widget.TextView;

public class Lyb implements Weapon {

	@Override
	public int getType() {
		return TYPE_NUCLEAR;
	}

	@Override
	public String getName() {
		return "ÀÇÑÀ°ô";
	}

	@Override
	public void kill(TextView textView) {
		textView.append("\n¶ªÕ¨µ¯");
	}
	
}
