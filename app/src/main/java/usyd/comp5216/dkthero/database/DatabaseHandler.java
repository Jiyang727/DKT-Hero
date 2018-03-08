package usyd.comp5216.dkthero.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import usyd.comp5216.dkthero.models.BestScoresModel;

public class DatabaseHandler extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "dktkero";

    // Labels table name
    private static final String TABLE_SCORES = "scores";

    // Labels Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_CATEGORY = "category";
    private static final String KEY_SCORE = "scores";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Scores table create query
        String CREATE_CATEGORIES_TABLE = "CREATE TABLE " + TABLE_SCORES + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_CATEGORY + " TEXT," + KEY_SCORE + " TEXT)";
        db.execSQL(CREATE_CATEGORIES_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCORES);

        // Create tables again
        onCreate(db);
    }

    /**
     * Inserting new score into scores table
     */
    public void insertScore(String category, String score) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_CATEGORY, category);
        values.put(KEY_SCORE, score);

        // Inserting Row
        db.insert(TABLE_SCORES, null, values);
        db.close(); // Closing database connection
    }

    public int updateScore(String category , String score) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_CATEGORY, category);
        values.put(KEY_SCORE, score);

        // updating row
        return db.update(TABLE_SCORES, values, KEY_CATEGORY + " = ?",
                new String[]{category});
    }

    // Getting single  Score
    public BestScoresModel getScoreForCategory(String category) {
        SQLiteDatabase db = this.getReadableDatabase();
        BestScoresModel contact = null;
        Cursor cursor = db.query(TABLE_SCORES, new String[]{KEY_ID,
                        KEY_CATEGORY, KEY_SCORE}, KEY_CATEGORY + "=?",
                new String[]{category}, null, null, null, null);
        if (cursor.moveToFirst()) {
            contact = new BestScoresModel(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
        }

        // return Score
        return contact;
    }

    /**
     * Getting all scores
     * returns list of scores
     */
    public List<BestScoresModel> getAllScores() {
        List<BestScoresModel> data = new ArrayList<BestScoresModel>();
        BestScoresModel bsm;
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_SCORES;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                bsm = new BestScoresModel();
                bsm.setId(cursor.getInt(0));
                bsm.setCategory(cursor.getString(1));
                bsm.setScore(cursor.getString(2));
                data.add(bsm);
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning scores
        return data;
    }
}