package com.example.kuri.myapplication.activity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.example.kuri.myapplication.R;

import java.util.ArrayList;
import java.util.HashMap;

public class TrainingActivity extends AppCompatActivity {


    private ListView lv;
    private BaseAdapter adapter;
    private String[] datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        lv = (ListView) findViewById(R.id.listviewsimple1);
        DBHelper dbHelper = new DBHelper(this);

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("training", "swimming");
        values.put("trainer", "coach wong");

        db.insert("train", null, values);
        db.close();

        db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT " +
                "training" + " FROM " + "train";
        Cursor cursor = db.rawQuery(selectQuery, null);

        ArrayList trainList=new ArrayList();

        if (cursor.moveToFirst()) {
            do {
                trainList.add(cursor.getString(cursor.getColumnIndex("training")));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        datas = new String[trainList.size()];
        for (int i = 0; i<trainList.size(); i++) {
            datas[i] = String.valueOf(trainList.get(i));
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, datas);
        lv.setAdapter(adapter);
    }
}
