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
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintSet
import com.mobilpogbead.controller.Controller
import com.mobilpogbead.entity.EntityFactory
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class MainActivity : AppCompatActivity() {

    lateinit var controller:Controller
    lateinit var mTimer:Timer
    lateinit var img:ImageView
    lateinit var MainLayout: ConstraintSet.Layout

    private fun loadResources():HashMap<String, ArrayList<Bitmap>>
    {
        var res=HashMap<String, ArrayList<Bitmap>>()
        val bug1as= BitmapFactory.decodeResource(resources, R.drawable.bug1as)
        val bug1bs= BitmapFactory.decodeResource(resources, R.drawable.bug1bs)
        val bug1=ArrayList<Bitmap>()
        bug1.add(bug1as)
        bug1.add(bug1bs)

        res["Enemy"]=bug1

        return res
    }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?)
    {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        controller=Controller(this, EntityFactory(loadResources()))

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
                        //Log.d("onstart","update")
                        controller.model.progress()
                        controller.view.update()
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

}