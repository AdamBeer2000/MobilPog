package com.mobilpogbead

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintSet
import com.mobilpogbead.controller.Controller
import com.mobilpogbead.entity.SingletonEntityFactory
import com.mobilpogbead.model.Boundaries
import java.util.*
import java.util.concurrent.locks.ReentrantLock
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.system.measureNanoTime
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {

    lateinit var controller:Controller
    lateinit var mTimer:Timer
    lateinit var img:ImageView
    lateinit var MainLayout: ConstraintSet.Layout
    lateinit var pointCountTextView:TextView
    lateinit var lifeCount:TextView
    val lock = ReentrantLock()
    private fun loadResources()
    {
        val entityFactory=SingletonEntityFactory.getInstance()

        val bug1as= BitmapFactory.decodeResource(resources, R.drawable.bug1a)
        val bug1bs= BitmapFactory.decodeResource(resources, R.drawable.bug1b)

        entityFactory.addBitmap("Bug",bug1as)
        entityFactory.addBitmap("Bug",bug1bs)

        val bug2as= BitmapFactory.decodeResource(resources, R.drawable.bug2a)
        val bug2bs= BitmapFactory.decodeResource(resources, R.drawable.bug2b)

        entityFactory.addBitmap("Squid",bug2as)
        entityFactory.addBitmap("Squid",bug2bs)

        val bug3as= BitmapFactory.decodeResource(resources, R.drawable.bug3a)
        val bug3bs= BitmapFactory.decodeResource(resources, R.drawable.bug3b)

        entityFactory.addBitmap("Chonker",bug3as)
        entityFactory.addBitmap("Chonker",bug3bs)


        val bulleta= BitmapFactory.decodeResource(resources, R.drawable.bulleta)
        val bulletb= BitmapFactory.decodeResource(resources, R.drawable.bulletb)

        entityFactory.addBitmap("Bullet",bulleta)
        entityFactory.addBitmap("Bullet",bulletb)

        val player1= BitmapFactory.decodeResource(resources, R.drawable.player)

        entityFactory.addBitmap("Player",player1)

        val barrier= BitmapFactory.decodeResource(resources, R.drawable.barrier)

        entityFactory.addBitmap("Barricade",barrier)

        val sapceship= BitmapFactory.decodeResource(resources, R.drawable.spaceship)

        entityFactory.addBitmap("Spaceship",sapceship)
    }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?)
    {
        loadResources()
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pointCountTextView=findViewById(R.id.pointCountTextView)
        lifeCount=findViewById(R.id.lifeCounter)

        controller=Controller(this,
            Boundaries(0,windowManager.defaultDisplay.width,0,windowManager.defaultDisplay.height),
            pointCountTextView,lifeCount,BitmapFactory.decodeResource(resources, R.drawable.boom1)
        )

        controller.setUpSensor();

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
                        try
                        {
                            lock.lock()
                            var progress= measureNanoTime {
                                controller.model.progress()
                            }
                            var move=measureNanoTime {
                                controller.move()
                            }
                            var update=measureNanoTime {
                                controller.view.update()
                            }
                            var checkHits=measureNanoTime {
                                controller.model.checkHits()
                            }
                            var cleanObjects=measureNanoTime {
                                controller.model.cleanObjects()
                            }
                            lock.unlock()
                            Log.d("Stat","progress:$progress nano")
                            Log.d("Stat","move:$move nano")
                            Log.d("Stat","update:$update nano")
                            Log.d("Stat","checkHits:$checkHits nano")
                            Log.d("Stat","cleanOutOfBounsObjects:$cleanObjects nano")
                        }
                        catch (e:Exception)
                        {
                            Log.e("Fail",e.message.toString())
                        }
                    }
                )
            }
        }, 0, 120)
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
        }
    }

    fun onTouch(view: View) {
        Log.d("TOUCH", "MainActivity - touched")
        //TODO: shooting
    }

}