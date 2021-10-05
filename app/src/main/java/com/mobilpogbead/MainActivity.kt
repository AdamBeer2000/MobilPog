package com.mobilpogbead

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
<<<<<<< Updated upstream
import android.util.Log
import com.mobilpogbead.controller.Controller

class MainActivity : AppCompatActivity() {

    var controller = Controller(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        controller.view.
=======
import android.widget.ImageView
import com.mobilpogbead.controller.Controller
import android.graphics.drawable.BitmapDrawable
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.mobilpogbead.entity.Enemy


class MainActivity : AppCompatActivity() {

    var controller= Controller()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val img=findViewById<ImageView>(R.id.battlegound)
        val icon = BitmapFactory.decodeResource(resources, R.drawable.default_enemy)



        controller.view.binde(img)
        controller.view.render["Enemy"]=icon
>>>>>>> Stashed changes
        controller.view.update()
        //controller.start()
    }
}