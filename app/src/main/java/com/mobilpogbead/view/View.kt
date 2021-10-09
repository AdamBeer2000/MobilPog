package com.mobilpogbead.view

import android.graphics.Bitmap
import android.graphics.Canvas
import android.util.Log
import android.widget.ImageView
import androidx.core.graphics.get
import androidx.core.graphics.set

import com.mobilpogbead.model.Model
import kotlin.system.measureTimeMillis

class View(private val model:Model)
{
    private  var height:Int=0
    private  var width:Int=0

    lateinit var imgv:ImageView

    lateinit var background:Bitmap

    fun bind(imgv: ImageView)
    {
        this.imgv=imgv
        height=imgv.maxHeight
        width=imgv.maxWidth
        imgv.setImageBitmap(Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888))
    }

    fun update()
    {
        val elapsed = measureTimeMillis {
            val renderedImage = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(renderedImage)

            for (obj in model.objects) {
                if (obj != null) {
                    canvas.drawBitmap(obj.getCurrGfx(), obj.x.toFloat(), obj.y.toFloat(), null)
                }
            }
            imgv.setImageBitmap(renderedImage)
        }

        //Log.d("Update","Time:$elapsed s")
    }
}