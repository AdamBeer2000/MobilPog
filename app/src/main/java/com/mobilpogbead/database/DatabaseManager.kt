package com.mobilpogbead.database
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.P)
class DatabaseManager(context: Context?): SQLiteOpenHelper(context, "invfromspacedb.db", null, 1)
{

    //private val DB_NAME: String = "invfromspacedb.db";
    private val DB_TABLE: String = "PlayerData"
    private val PLAYERDATA_USERNAME: String = "Username"
    private val PLAYERDATA_SCORE: String = "Score"
    private val PLAYERDATA_TIME: String = "Time"

    override fun onCreate(p0: SQLiteDatabase?)
    {
        TODO("Not yet implemented")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int)
    {
        TODO("Not yet implemented")
    }

    fun getData(): ArrayList<Score>
    {
        val scores = ArrayList<Score>();

        val db: SQLiteDatabase = this.readableDatabase


        return scores;
    }

    fun setNewScore(data: Score)
    {
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
    }
}