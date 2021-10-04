package com.mobilpogbead.model
import com.mobilpogbead.entity.Entity

class Model()
{
    val objects=ArrayList<Entity>()
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
            if(obj.hp<=0)
            {
                objects.remove(obj)
            }
        }
    }
}