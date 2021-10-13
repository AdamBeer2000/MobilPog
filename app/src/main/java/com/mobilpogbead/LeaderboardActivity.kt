package com.mobilpogbead

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mobilpogbead.database.Score

class LeaderboardActivity : AppCompatActivity()
{

    fun dataLoad():ArrayList<Score>
    {
        //todo adatb lekérdezés
        val scores=ArrayList<Score>()

        scores.add(Score("Placeholder1",1500,70.0F))
        scores.add(Score("Placeholder2",1500,69.0F))
        scores.add(Score("Placeholder3",1500,69.99F))
        scores.add(Score("Placeholder4",890,80.0F))
        scores.add(Score("Placeholder5",720,65.0F))

        return scores
    }
    val scores=dataLoad()
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leaderboard)

    }


}