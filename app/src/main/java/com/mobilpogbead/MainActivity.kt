package com.mobilpogbead

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.mobilpogbead.controller.Controller

class MainActivity : AppCompatActivity() {

    var controller = Controller(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        controller.view.
        controller.view.update()
        //controller.start()
    }
}