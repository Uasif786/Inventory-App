package com.example.inventoryapp_umarasif;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "inventory.db";
    private static final int DB_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE items (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, quantity INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS items");
        onCreate(db);
    }

    public boolean checkLogin(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE username=? AND password=?", new String[]{username, password});
        boolean result = cursor.getCount() > 0;
        cursor.close();
        return result;
    }


    public void addItem(String name, int quantity) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("quantity", quantity);
        db.insert("items", null, values);
    }

    public void updateItem(int id, String name, int quantity) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("quantity", quantity);
        db.update("items", values, "id=?", new String[]{String.valueOf(id)});
    }

    public void deleteItem(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("items", "id=?", new String[]{String.valueOf(id)});
    }

    public ArrayList<InventoryItem> getAllItems() {
        ArrayList<InventoryItem> items = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM items", null);
        while (cursor.moveToNext()) {
            items.add(new InventoryItem(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getInt(2)
            ));
        }
        cursor.close();
        return items;
    }
}

