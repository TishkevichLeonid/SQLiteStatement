package com.leo.android.sqlitestatement;

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

            }
        });
    }

    private void initDB(){
        mDatabase = this.openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);
        mDatabase.execSQL("create table if not exist " + TABLE_NAME + "(FirstNumber INT, SecondNumber INT, Result INT);");
        mDatabase.delete(DB_NAME, null, null);

    }
}
