package mainpackage.fertilize;

import java.util.List;

import fertilize.sqlite.helper.DbHelper;
import fertilize.sqlite.helper.Question;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class QuestActivity extends Activity {
	List<Question> QuestList;
	double score=0;
	int qid=0;
	double cfuser=0;
	double cfpakar=0;
	double cf, cfcombine;
	Question currentQ;
	TextView txtQuestion;
	RadioButton rda, rdb, rdc, rdd, rde, rdf;
	Button butNext;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quest);
		DbHelper db=new DbHelper(this);
		QuestList=db.getAllQuestions();
		currentQ=QuestList.get(qid);
		txtQuestion=(TextView)findViewById(R.id.textView1);
		rda=(RadioButton)findViewById(R.id.radio0);
		rdb=(RadioButton)findViewById(R.id.radio1);
		rdc=(RadioButton)findViewById(R.id.radio2);
		rdd=(RadioButton)findViewById(R.id.radio3);
		rde=(RadioButton)findViewById(R.id.radio4);
		rdf=(RadioButton)findViewById(R.id.radio5);
		butNext=(Button)findViewById(R.id.button1);
		setQuestionView();
		butNext.setOnClickListener(new View.OnClickListener() {		
			@Override
			public void onClick(View v) {
				RadioGroup grp=(RadioGroup)findViewById(R.id.radioGroup1);
				RadioButton answer=(RadioButton)findViewById(grp.getCheckedRadioButtonId());
				
				//Log.d("yourans", currentQ.getANSWER()+" "+answer.getText());
				
//				if(currentQ.getANSWER().equals(answer.getText()))
//				{
//					score++;
//					Log.d("score", "Your score"+score);
//				}
				cfpakar = currentQ.getANSWER();
				if(answer == rda){
					cfuser = 0;
				} else if(answer == rdb) {
					cfuser = 0.1;
				} else if (answer == rdc){
					cfuser = 0.2;
				} else if (answer == rdd){
					cfuser = 0.6;
				} else if (answer == rde){
					cfuser = 0.8;
				} else {
					cfuser = 1;
				}
				
				//score = score + (cfpakar * cfuser);
				cf = cfpakar * cfuser;
				if (qid == 1){
					cfcombine = cfpakar * cfuser;
				}else {
					cfcombine = cfcombine + (cf  * (1-cfcombine)); 
					score = cfcombine * 100;
				}
				
				if(qid<5){					
					cf = cfpakar * cfuser;
					currentQ=QuestList.get(qid);
					setQuestionView();
				}else{
					Intent intent = new Intent(QuestActivity.this, Result.class);
					Bundle b = new Bundle();
					b.putDouble("score", score); //Your score
					intent.putExtras(b); //Put your score to your next Intent
					startActivity(intent);
					finish();
				}
			}
		});
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.quest, menu);
		return true;
	}
	private void setQuestionView()
	{
		txtQuestion.setText(currentQ.getQUESTION());
		rda.setText(currentQ.getOPTA());
		rdb.setText(currentQ.getOPTB());
		rdc.setText(currentQ.getOPTC());
		rdd.setText(currentQ.getOPTD());
		rde.setText(currentQ.getOPTE());
		rdf.setText(currentQ.getOPTF());
		qid++;
	}
}