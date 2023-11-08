package com.example.poketracker;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class DatabaseListViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_list_view);
        showDatabase();
    }
    public void showDatabase() {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        Cursor cursor = dbHelper.getAllData();
        String[] columns = new String[]{
                "_id", // The alias for national_number in your SQL query
                DatabaseHelper.COL_NAME,
                DatabaseHelper.COL_SPECIES,
                DatabaseHelper.COL_HEIGHT,
                DatabaseHelper.COL_WEIGHT,
                DatabaseHelper.COL_HP,
                DatabaseHelper.COL_ATTACK,
                DatabaseHelper.COL_DEFENSE,
                DatabaseHelper.COL_LEVEL,
                DatabaseHelper.COL_GENDER
        };

        int[] to = new int[]{
                R.id.textViewNationalNumber,
                R.id.textViewName,
                R.id.textViewSpecies,
                R.id.textViewPokemonHeight,
                R.id.textViewPokemonWeight,
                R.id.textViewPokemonHP,
                R.id.textViewPokemonAttack,
                R.id.textViewPokemonDefense,
                R.id.textViewPokemonLevel,
                R.id.textViewPokemonGender
        };

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.row, cursor, columns, to, 0);
        ListView databaseListView = findViewById(R.id.databaseListView);
        databaseListView.setAdapter(adapter);
    }


}