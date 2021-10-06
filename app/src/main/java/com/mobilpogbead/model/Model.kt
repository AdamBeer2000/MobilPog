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
        objects.add(entityFactory.createEntity<Enemy>(0,0))
        objects.add(entityFactory.createEntity<Enemy>(50,0))
        objects.add(entityFactory.createEntity<Barricade>(100,0))
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