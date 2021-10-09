package com.mobilpogbead

import android.app.ActionBar
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintSet
import com.mobilpogbead.controller.Controller
import com.mobilpogbead.entity.EntityFactory
import com.mobilpogbead.model.Boundaries
import java.lang.Exception
import java.util.*
import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class MainActivity : AppCompatActivity() {

    lateinit var controller:Controller
    lateinit var mTimer:Timer
    lateinit var img:ImageView
    lateinit var MainLayout: ConstraintSet.Layout

    val sharedCounterLock = ReentrantLock()

    private fun loadResources():HashMap<String, ArrayList<Bitmap>>
    {
        var res=HashMap<String, ArrayList<Bitmap>>()

        val bug1as= BitmapFactory.decodeResource(resources, R.drawable.bug1as)
        val bug1bs= BitmapFactory.decodeResource(resources, R.drawable.bug1bs)
        val bug1=ArrayList<Bitmap>()

        bug1.add(bug1as)
        bug1.add(bug1bs)

        res["Enemy"]=bug1

        val bulleta= BitmapFactory.decodeResource(resources, R.drawable.bulleta)
        val bulletb= BitmapFactory.decodeResource(resources, R.drawable.bulletb)
        val bullet=ArrayList<Bitmap>()

        bullet.add(bulleta)
        bullet.add(bulletb)

        res["Bullet"]=bullet

        val player1= BitmapFactory.decodeResource(resources, R.drawable.player)
        val player=ArrayList<Bitmap>()

        player.add(player1)

        res["Player"]=player

        return res
    }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)

        controller=Controller(this,
            EntityFactory(loadResources()),
            Boundaries(0,windowManager.defaultDisplay.width,0,windowManager.defaultDisplay.height)
        )
        img= ImageView(this)
        img.maxWidth=windowManager.defaultDisplay.width
        img.maxHeight=windowManager.defaultDisplay.height

        addContentView(img,ViewGroup.LayoutParams(img.maxWidth,img.maxHeight))

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
                        sharedCounterLock.lock()
                        try
                        {
                            controller.model.progress()
                            controller.view.update()
                            controller.model.checkHits()
                            controller.model.cleanOutOfBounsObjects()
                        }
                        catch (e:Exception)
                        {

                        }
                        finally
                        {
                            sharedCounterLock.unlock()
                        }
                    }
                )
            }
        }, 0, 120)
    }


}