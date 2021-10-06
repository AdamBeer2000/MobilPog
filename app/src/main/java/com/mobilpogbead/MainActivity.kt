package com.mobilpogbead

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.annotation.UiThread
import androidx.core.graphics.set
import com.mobilpogbead.controller.Controller
import com.mobilpogbead.entity.EntityFactory
import java.util.*
import kotlin.collections.HashMap

class MainActivity : AppCompatActivity() {

    lateinit var controller:Controller
    lateinit var mTimer:Timer
    lateinit var img:ImageView

    private fun loadResources():HashMap<String, Bitmap>
    {
        var res=HashMap<String, Bitmap>()
        val defaultEnemyGraphics= BitmapFactory.decodeResource(resources, R.drawable.default_enemy)
        val barrierGraphics= BitmapFactory.decodeResource(resources, R.drawable.barrier)
        res["Enemy"]=defaultEnemyGraphics
        res["Barricade"]=barrierGraphics

        return res
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        controller=Controller(this, EntityFactory(loadResources()))
        img=findViewById<ImageView>(R.id.battlegound)

        controller.view.bind(img)
    }

    override fun onStart()
    {
        super.onStart()
        mTimer = Timer()
        mTimer.schedule(object : TimerTask()
        {
            override fun run()
            {
                runOnUiThread(
                    Runnable {
                        Log.d("onstart","update")
                        controller.model.progress()
                        controller.view.update()
                    }
                )
            }
        }, 0, 1000)
    }


}