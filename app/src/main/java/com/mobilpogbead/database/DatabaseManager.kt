package com.mobilpogbead.database
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi

//@RequiresApi(Build.VERSION_CODES.P)
class DatabaseManager(context: Context?): SQLiteOpenHelper(context, "invfromspacedb.db", null, 1)
{

    //private val DB_NAME: String = "invfromspacedb.db";
    private val DB_TABLE: String = "PlayerData"
    private val PLAYERDATA_USERNAME: String = "Username"
    private val PLAYERDATA_SCORE: String = "Score"
    private val PLAYERDATA_TIME: String = "Time"

    override fun onCreate(p0: SQLiteDatabase?)
    {
        val db: SQLiteDatabase = this.writableDatabase
        val query = "CREATE TABLE IF NOT EXISTS" + this.DB_TABLE + " (" + this.PLAYERDATA_USERNAME + " TEXT, " + this.PLAYERDATA_SCORE + " INTEGER, "+ this.PLAYERDATA_TIME +" REAL)"
        //db.execSQL(query)
        db.close()
        Log.d("DATABASE", "onCeate")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int)
    {
        //p0?.execSQL("DROP TABLE IF EXISTS PlayerData")
        this.onCreate(p0)
        Log.d("DATABASE", "onUpgrade")
    }

    fun getData(): Cursor
    {
        createTable()
        val result: Cursor;

        val db: SQLiteDatabase = this.readableDatabase
        result = db.rawQuery("SELECT * FROM " + this.DB_TABLE, null)

        db.close()
        return result;
    }

    fun setNewScore(data: Score)
    {
        try
        {
            createTable()

            val db: SQLiteDatabase = this.writableDatabase
            val cv = ContentValues()

            cv.put(this.PLAYERDATA_USERNAME, data.name)
            cv.put(this.PLAYERDATA_SCORE, data.score)
            cv.put(this.PLAYERDATA_TIME, data.time)

            val res: Long = db.insert(this.DB_TABLE, null, cv)

            if(res == -1L)
            {
                Log.d("DATABASE", "New Score insert - failed");
            }
            else
            {
                Log.d("DATABASE", "New Score insert - successful");
            }

            db.close()
        }
        catch (e: SQLiteException)
        {
            Log.d("DATABASE EXCEPTION", e.toString())
        }
    }

    private fun createTable()
    {
        val db: SQLiteDatabase = this.writableDatabase
        val query = "CREATE TABLE " + this.DB_TABLE + " (" + this.PLAYERDATA_USERNAME + " TEXT, " + this.PLAYERDATA_SCORE + " INTEGER, "+ this.PLAYERDATA_TIME +" REAL)"
        try
        {
            db.execSQL(query)
        }
        catch(e: SQLiteException)
        {
            Log.d("DATABASE", e.toString())
        }
        db.close()
    }
}