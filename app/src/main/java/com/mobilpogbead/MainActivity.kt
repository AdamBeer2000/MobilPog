package com.mobilpogbead

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.mobilpogbead.controller.Controller
import com.mobilpogbead.entity.EntityFactory


class MainActivity : AppCompatActivity() {

    lateinit var controller:Controller
    lateinit var defaultEnemyGraphics:Bitmap

    private fun loadResources():HashMap<String, Bitmap>
    {
        var res=HashMap<String, Bitmap>()
        res["Enemy"]=defaultEnemyGraphics
        return res
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val img=findViewById<ImageView>(R.id.battlegound)

        defaultEnemyGraphics= BitmapFactory.decodeResource(resources, R.drawable.default_enemy)

        controller=Controller(this, EntityFactory(loadResources()))

        controller.view.bind(img)
        controller.view.update()
        //controller.start()
    }
}