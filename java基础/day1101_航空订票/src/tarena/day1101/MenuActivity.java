package tarena.day1101;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;

public class MenuActivity extends Activity {

	private ImageButton ib1;
	private ImageButton ib2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//»•µÙ±ÍÃ‚¿∏
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_menu);
		
		setViews();
		setListeners();
	}

	private void setViews() {
		ib1 = (ImageButton)findViewById(R.id.ib1);
		ib2 = (ImageButton)findViewById(R.id.ib2);
	}

	private void setListeners() {
		ib1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(
						new Intent(
								MenuActivity.this,
								BranchSearchActivity.class));	
			}
		});
		
		ib2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(
						new Intent(
								MenuActivity.this,
								FlightSearchActivity.class));	
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

}
