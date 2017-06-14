package com.leo.android.sqlitestatement;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String DB_NAME = "myDatabase";
    public static final String TABLE_NAME = "myTable";
    private SQLiteDatabase mDatabase;
    private TextView mTimeView;
    private Button mInsertBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDB();
        mTimeView = (TextView) findViewById(R.id.Time_text);
        mInsertBtn = (Button) findViewById(R.id.Insert_Button);
        mInsertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabase.delete(TABLE_NAME, null, null);
                long startTime = System.currentTimeMillis();
                insertRecords();
                long deltaTime = System.currentTimeMillis() - startTime;
                mTimeView.setText("Time: " + deltaTime + "ms");

            }
        });
    }

    private void initDB(){
        mDatabase = this.openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);
        mDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(FirstNumber INT, SecondNumber INT, Result INT);");
        mDatabase.delete(TABLE_NAME, null, null);

    }

    private void insertRecords(){
        mDatabase.beginTransaction();
        try {
            for (int i = 0; i < 1000; i++){
                ContentValues contentValues = new ContentValues();
                contentValues.put("Firstnumber", i);
                contentValues.put("SecondNumber", i);
                contentValues.put("Result", i*i);
                mDatabase.insert(TABLE_NAME, null, contentValues);
            }
            mDatabase.setTransactionSuccessful();
        } finally {
            mDatabase.endTransaction();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDatabase.close();
    }
}
