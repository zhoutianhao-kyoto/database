package com.example.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLiteOpenHelper dbHelper = new DatabaseHelper(getApplicationContext());

        TextView mainView = (TextView) findViewById(R.id.mainView);

        try{
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor cursor = db.query ("DRINK",
                    new String[] {"NAME", "DESCRIPTION", "IMAGE_RESOURCE_ID"},
                    "name = ?",
                    new String[] {"Latte"},
                    null, null,null);
            if (cursor.moveToFirst()) {
                mainView.setText("Latte's description is " + cursor.getString(1));
            }
        } catch(SQLiteException e) {
            mainView.setText("SQL error happened:\n" + e.toString());
        }
    }
}
