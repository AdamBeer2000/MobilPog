package com.mobilpogbead.view

import android.graphics.Bitmap
import android.graphics.Color
import android.widget.ImageView
import androidx.core.graphics.get
import androidx.core.graphics.set
import com.mobilpogbead.entity.Enemy
import com.mobilpogbead.entity.Entity
import com.mobilpogbead.model.Model

class View(private val model:Model)
{
    val render=HashMap<String,Bitmap>()

    var renderedImage = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888)
        private set

    init
    {
        for(x in 0 until 500)
        {
            for(y in 0 until 500)
            {
                renderedImage[x, y] = Color.BLACK
            }
        }
    }

    fun binde(imgv: ImageView)
    {
        imgv.setImageBitmap(renderedImage)
    }

    fun update()
    {
        for (obj in model.objects)
        {
            if(obj is Enemy)
            {
                val rend=render["Enemy"]
                if(rend!=null)
                {
                    for(x in obj.x until rend.width)
                    {
                        for(y in obj.y until rend.height)
                        {
                            renderedImage[x, y] = rend[x, y]
                        }
                    }
                }
            }

        }
    }
}