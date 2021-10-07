package com.mobilpogbead.model
import android.graphics.Bitmap
import android.util.Log
import com.mobilpogbead.entity.Barricade
import com.mobilpogbead.entity.Enemy
import com.mobilpogbead.entity.Entity
import com.mobilpogbead.entity.EntityFactory

class Model(private val entityFactory:EntityFactory)
{
    val objects=ArrayList<Entity?>()
    init
    {
        var ref=entityFactory.createEntity<Enemy>(0,0)
        if(ref!=null && ref.gfx!=null)
        {
            var shifty=0
            for(i in 0 until 5)
            {
                var shiftx=0
                shifty+=ref.getCurrGfx().height+15
                for(k in 0 until 8)
                {
                    var newEnemy=entityFactory.createEntity<Enemy>(shiftx,shifty)
                    if(newEnemy != null)
                    {
                        objects.add(newEnemy)
                        shiftx=newEnemy.x+newEnemy.getCurrGfx().width+15
                    }
                }
            }
        }
    }
    fun progress()
    {
        for(obj in objects)
        {
            if(obj != null)
            obj.moveDown()
        }
    }
    fun checkHits()
    {
        for(obj in objects)
        {
            if(false)//todo hitbox checkh
            {

            }
        }
    }
    fun clearDead()
    {
        for(obj in objects)
        {

        }
    }
}