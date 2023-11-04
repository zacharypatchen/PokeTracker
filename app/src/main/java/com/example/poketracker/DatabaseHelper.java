package com.example.poketracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "PokemonDatabase";
    private static final String TABLE_NAME = "Pokemon";
    private static final String COL_NATIONAL_NUMBER = "national_number";
    private static final String COL_NAME = "name";
    private static final String COL_SPECIES = "species";
    private static final String COL_HEIGHT = "height";
    private static final String COL_WEIGHT = "weight";
    private static final String COL_HP = "hp";
    private static final String COL_ATTACK = "attack";
    private static final String COL_DEFENSE = "defense";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_NATIONAL_NUMBER + " TEXT PRIMARY KEY, " +
                COL_NAME + " TEXT, " +
                COL_SPECIES + " TEXT, " +
                COL_HEIGHT + " TEXT, " +
                COL_WEIGHT + " TEXT, " +
                COL_HP + " INTEGER, " +
                COL_ATTACK + " INTEGER, " +
                COL_DEFENSE + " INTEGER)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // If you need to upgrade the database in the future, add logic here
    }

    // Method to insert data into the database
    public boolean insertData(String nationalNumber, String name, String species,
                              String height, String weight, int hp, int attack, int defense) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NATIONAL_NUMBER, nationalNumber);
        contentValues.put(COL_NAME, name);
        contentValues.put(COL_SPECIES, species);
        contentValues.put(COL_HEIGHT, height);
        contentValues.put(COL_WEIGHT, weight);
        contentValues.put(COL_HP, hp);
        contentValues.put(COL_ATTACK, attack);
        contentValues.put(COL_DEFENSE, defense);

        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1; // Returns true if the data is inserted successfully, otherwise false
    }
}
