package mainpackage.fertilize;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void changeActivity(View v) {
		Intent it; 
		if(findViewById(R.id.quest) == v){
			it = new Intent(this, QuestActivity.class);
		} else {
			it = new Intent(this, MainActivity.class);
		}
		startActivity(it);
	}
}
