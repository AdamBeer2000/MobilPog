package com.mobilpogbead.database
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class Database {
    val query = "select sqlite_version() AS sqlite_version"
    //val db = SQLiteDatabase.openOrCreateDatabase(":memory:", null);
    //val cursor: Cursor = db.rawQuery(query, null);
}