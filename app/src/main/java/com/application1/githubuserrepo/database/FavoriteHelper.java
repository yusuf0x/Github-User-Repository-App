package com.application1.githubuserrepo.database;

import static com.application1.githubuserrepo.database.DatabaseContract.FavoriteColoumn.ID;
import static com.application1.githubuserrepo.database.DatabaseContract.FavoriteColoumn.IMAGE;
import static com.application1.githubuserrepo.database.DatabaseContract.FavoriteColoumn.TABLE_NAME;
import static com.application1.githubuserrepo.database.DatabaseContract.FavoriteColoumn.TITLE;
import static com.application1.githubuserrepo.database.DatabaseContract.FavoriteColoumn.URL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.application1.githubuserrepo.models.User;

import java.util.ArrayList;

public class FavoriteHelper {

    private static final String DATABASE_TABLE = TABLE_NAME;
    private static DatabaseHelper favoriteDbHelper;
    private static FavoriteHelper favoriteHelper;
    private static SQLiteDatabase db;

    public FavoriteHelper(Context context) {
        this.favoriteDbHelper = new DatabaseHelper(context);
    }
    public static FavoriteHelper getFavoriteHelper(Context context) {
        if (favoriteHelper == null) {
            synchronized (SQLiteOpenHelper.class) {
                if (favoriteHelper == null) {
                    favoriteHelper = new FavoriteHelper(context);
                }
            }
        }
        return favoriteHelper;
    }

    public void open() throws SQLException {
        db = favoriteDbHelper.getWritableDatabase();
    }

    public void close() {
        favoriteDbHelper.close();
        if (db.isOpen())
            db.close();
    }

    public ArrayList<User> getAllFavorites() {
        ArrayList<User> arrayList = new ArrayList<>();
        Cursor cursor = db.query(DATABASE_TABLE, null,
                null,
                null,
                null,
                null,
                ID + " ASC",
                null);
        cursor.moveToFirst();
        User modelUser;
        if (cursor.getCount() > 0) {
            do {
                modelUser = new User();
                modelUser.setId(cursor.getInt(cursor.getColumnIndexOrThrow(ID)));
                modelUser.setLogin(cursor.getString(cursor.getColumnIndexOrThrow(TITLE)));
                modelUser.setAvatarUrl(cursor.getString(cursor.getColumnIndexOrThrow(IMAGE)));
                modelUser.setHtmlUrl(cursor.getString(cursor.getColumnIndexOrThrow(URL)));
                arrayList.add(modelUser);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public long favoriteInsert(User userResponse) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, userResponse.getId());
        contentValues.put(TITLE, userResponse.getLogin());
        contentValues.put(IMAGE, userResponse.getAvatarUrl());
        contentValues.put(URL, userResponse.getHtmlUrl());

        return db.insert(DATABASE_TABLE, null, contentValues);
    }

    public int favoriteDelete(String title) {
        return db.delete(TABLE_NAME, TITLE + " = '" + title + "'", null);
    }

    public Cursor cursorFavoriteGet() {
        return db.query(DATABASE_TABLE, null, null,
                null, null, null, ID + " ASC");
    }

    public Cursor cursorFavoriteGetId(String id) {
        return db.query(DATABASE_TABLE, null
                , ID + " = ?"
                , new String[]{id}
                , null
                , null
                , null
                , null);
    }

    public int favoriteDeleteProvider(String id) {
        return db.delete(TABLE_NAME, ID + "=?", new String[]{id});
    }

    public int favoriteUpdateProvider(String id, ContentValues values) {
        return db.update(DATABASE_TABLE, values, ID + " =?", new String[]{id});
    }

    public long favoriteInsertProvider(ContentValues values) {
        return db.insert(DATABASE_TABLE, null, values);
    }

}
