package com.x64technology.linex.database.noroom;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.x64technology.linex.models.Message;

import java.util.ArrayList;
import java.util.List;

public class DBService {
    DBHelper dbHelper;
    SQLiteDatabase writableDb, readableDb;

    public DBService(Context context) {
        dbHelper = new DBHelper(context);
        writableDb = dbHelper.getWritableDatabase();
        readableDb = dbHelper.getReadableDatabase();
    }

    public void newChat(String tableName) {
        writableDb.execSQL(
                String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT, %s TEXT, %s TEXT)",
                        tableName, DBStrings.ID, DBStrings.SENDER_ID, DBStrings.SENDER_USERNAME, DBStrings.CONTENT, DBStrings.TIME)
        );
    }

    public void insertMsg(String tableName, Message message) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(DBStrings.SENDER_ID, message.senderId);
        contentValues.put(DBStrings.SENDER_USERNAME, message.senderUsername);
        contentValues.put(DBStrings.CONTENT, message.content);
        contentValues.put(DBStrings.TIME, message.time);

        writableDb.insert(tableName, null, contentValues);

        System.out.println("message added");
    }

    public List<Message> getRangedChat(String tableName) {
        String[] proj = {DBStrings.ID, DBStrings.SENDER_ID, DBStrings.SENDER_USERNAME, DBStrings.CONTENT, DBStrings.TIME};
        Cursor cursor = readableDb.query(tableName, proj, null, null, null, null, null);
        List<Message> messages = new ArrayList<>();
        while (cursor.moveToNext()) {
            messages.add(new Message(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4)).setId(cursor.getInt(0)));
        }
        cursor.close();
        return messages;
    }
}