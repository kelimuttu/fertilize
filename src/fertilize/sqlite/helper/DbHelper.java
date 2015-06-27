package fertilize.sqlite.helper;

import java.util.ArrayList;
import java.util.List;

import fertilize.sqlite.helper.Question;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
	private static final int DATABASE_VERSION = 1;
	// Database Name
	private static final String DATABASE_NAME = "fertilizeQuiz";
	// tasks table name
	private static final String TABLE_QUEST = "quest";
	// tasks Table Columns names
	private static final String KEY_ID = "id";
	private static final String KEY_QUES = "question";
	private static final String KEY_ANSWER = "answer"; //correct option
	private static final String KEY_OPTA= "opta"; //option a
	private static final String KEY_OPTB= "optb"; //option b
	private static final String KEY_OPTC= "optc"; //option c
	private static final String KEY_OPTD= "optd"; //option d
	private static final String KEY_OPTE= "opte"; //option e
	private static final String KEY_OPTF= "optf"; //option f
	private SQLiteDatabase dbase;
	public DbHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		dbase=db;
		String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_QUEST + " ( "
				+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUES
				+ " TEXT, " + KEY_ANSWER+ " TEXT, "+KEY_OPTA +" TEXT, "
				+KEY_OPTB +" TEXT, "+KEY_OPTC+" TEXT, "+KEY_OPTD +" TEXT, "
				+KEY_OPTE +" TEXT, "+KEY_OPTF +" TEXT)";
		db.execSQL(sql);
		addQuestions();
		//db.close();
	}
	
	private void addQuestions()
	{
		Question q1=new Question("Apakah saat Anda haid terasa sakit?","Tidak", "Tidak Tahu", "Sedikit Yakin", "Cukup Yakin", "Yakin", "Sangat Yakin", 0.2);
		this.addQuestion(q1);
		Question q2=new Question("Apakah haid Anda jarang?", "Tidak", "Tidak Tahu", "Sedikit Yakin", "Cukup Yakin", "Yakin", "Sangat Yakin", 0.6);
		this.addQuestion(q2);
		Question q3=new Question("Apakah haid Anda banyak?","Tidak", "Tidak Tahu", "Sedikit Yakin", "Cukup Yakin", "Yakin", "Sangat Yakin", 0.4);
		this.addQuestion(q3);
		Question q4=new Question("Apakah haid Anda tidak teratur?","Tidak", "Tidak Tahu", "Sedikit Yakin", "Cukup Yakin", "Yakin", "Sangat Yakin", 0.4);
		this.addQuestion(q4);
		Question q5=new Question("Apakah berat badan Anda naik?","Tidak", "Tidak Tahu", "Sedikit Yakin", "Cukup Yakin", "Yakin", "Sangat Yakin", 0.2);
		this.addQuestion(q5);
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUEST);
		// Create tables again
		onCreate(db);
	}
	// Adding new question
	public void addQuestion(Question quest) {
		//SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_QUES, quest.getQUESTION()); 
		values.put(KEY_ANSWER, quest.getANSWER());
		values.put(KEY_OPTA, quest.getOPTA());
		values.put(KEY_OPTB, quest.getOPTB());
		values.put(KEY_OPTC, quest.getOPTC());
		values.put(KEY_OPTD, quest.getOPTD());
		values.put(KEY_OPTE, quest.getOPTE());
		values.put(KEY_OPTF, quest.getOPTF());
		// Inserting Row
		dbase.insert(TABLE_QUEST, null, values);		
	}
	
	public List<Question> getAllQuestions() {
		List<Question> quesList = new ArrayList<Question>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
		dbase=this.getReadableDatabase();
		Cursor cursor = dbase.rawQuery(selectQuery, null);
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Question quest = new Question();
				quest.setID(cursor.getInt(0));
				quest.setQUESTION(cursor.getString(1));
				quest.setANSWER(cursor.getDouble(2));
				quest.setOPTA(cursor.getString(3));
				quest.setOPTB(cursor.getString(4));
				quest.setOPTC(cursor.getString(5));
				quest.setOPTD(cursor.getString(6));
				quest.setOPTE(cursor.getString(7));
				quest.setOPTF(cursor.getString(8));
				quesList.add(quest);
			} while (cursor.moveToNext());
		}
		// return quest list
		return quesList;
	}
	
	public int rowcount()
	{
		int row=0;
		String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		row=cursor.getCount();
		return row;
	}
}