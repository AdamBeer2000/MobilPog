package com.mobilpogbead

import android.database.sqlite.SQLiteException
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.view.ViewGroup
import android.view.Window
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.view.setPadding
import com.mobilpogbead.database.DatabaseManager
import com.mobilpogbead.database.Score

class LeaderboardActivity : AppCompatActivity()
{
    fun dataLoad():ArrayList<Score>
    {
        //todo adatb lekérdezés

        val db = DatabaseManager(this)
        val scores = db.getBestTen()

        return scores
    }

    lateinit var scoresLayout:LinearLayout

    fun createStdTextView(string:String,lp:LinearLayout.LayoutParams):TextView
    {
        val tx1=TextView(this)
        tx1.text=string
        tx1.layoutParams=lp
        tx1.setTextColor(Color.GREEN)
        tx1.setBackgroundResource(R.drawable.textview_border)
        tx1.textSize= 15F
        tx1.setPadding(10)

        return tx1
    }


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_leaderboard)
        val scores=dataLoad()
        scoresLayout= findViewById(R.id.scoresLayout)

        for(score in scores)
        {
            val hr=LinearLayout(this)
            hr.orientation=LinearLayout.HORIZONTAL
            hr.gravity=Gravity.CENTER_HORIZONTAL
            val lp =LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
            lp.weight=1F

            hr.layoutParams=lp

            hr.addView(createStdTextView("${score.name}",lp))
            hr.addView(createStdTextView("${score.score}",lp))
            hr.addView(createStdTextView("${score.time}",lp))

            scoresLayout.addView(hr)
        }
    }



}