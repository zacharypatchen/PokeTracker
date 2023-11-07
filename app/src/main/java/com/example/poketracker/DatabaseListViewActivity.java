package com.example.poketracker;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

public class DatabaseListViewActivity extends AppCompatActivity {
ListView databaseListView = findViewById(R.id.database_list_view);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_list_view);
    }
    public void showDatabase(){
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        Cursor cursor = dbHelper.getAllData();
        //finish writing this method. it should populate a list view named database_list_view
    }

}