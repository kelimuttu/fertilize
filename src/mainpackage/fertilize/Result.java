package mainpackage.fertilize;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RatingBar;
import android.widget.TextView;

public class Result extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_result);
//		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		
		//get rating bar object
//				RatingBar bar=(RatingBar)findViewById(R.id.ratingBar1); 
//				bar.setNumStars(4);
//				bar.setStepSize(1f);
				//get text view
				TextView t=(TextView)findViewById(R.id.textResult);
				TextView thasil=(TextView)findViewById(R.id.percentage);
				//get score
				Bundle b = getIntent().getExtras();
				double score= b.getDouble("score");
				int precentage = (int) score;
				//int score= b.getInt("score");
				t.setText(String.valueOf(precentage) + " %");
				//t.setText(score);
				
				if( (precentage > 0) && (precentage <= 50)){
					thasil.setText("Anda berkemungkinan kecil mengalami infertilitas");
				} else if((precentage > 50) && (precentage <= 79)){
					thasil.setText("Anda berkemungkinan cukup mengalami infertilitas");
				} else if((precentage > 79) && (precentage <= 99)){
					thasil.setText("Anda berkemungkinan besar mengalami infertilitas");
				} else if(precentage == 100){
					thasil.setText("Anda diyakini mengalami infertilitas");
				}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.result, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
	    startActivityForResult(myIntent, 0);
	    return true;
	}
}
