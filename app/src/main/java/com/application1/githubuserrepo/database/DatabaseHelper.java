package com.application1.githubuserrepo.database;

import static com.application1.githubuserrepo.database.DatabaseContract.FavoriteColoumn.TABLE_NAME;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "favoritedb";
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_TABLE_FAVORITE = String.format(
            "CREATE TABLE %s" +
                    " (%s INTEGER PRIMARY KEY," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL)",
            TABLE_NAME,
            DatabaseContract.FavoriteColoumn.ID,
            DatabaseContract.FavoriteColoumn.TITLE,
            DatabaseContract.FavoriteColoumn.IMAGE,
            DatabaseContract.FavoriteColoumn.URL
    );
    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_FAVORITE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
