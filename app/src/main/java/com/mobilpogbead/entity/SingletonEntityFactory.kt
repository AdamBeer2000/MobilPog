package com.mobilpogbead.entity

import android.graphics.Bitmap
import android.graphics.Color
import android.util.Log
import androidx.core.graphics.get
import com.mobilpogbead.entity.bullet.Bullet
import com.mobilpogbead.entity.bullet.EnemyBullet
import com.mobilpogbead.entity.bullet.PlayerBullet
import com.mobilpogbead.entity.enemies.Bug
import com.mobilpogbead.entity.enemies.Chonker
import com.mobilpogbead.entity.enemies.Squid
import java.lang.Exception
import java.util.ArrayList

class SingletonEntityFactory private constructor()
{
    val resources=HashMap<String, ArrayList<Bitmap>>()

    companion object {
        @JvmStatic
        private var singleInstance: SingletonEntityFactory? = null

        @JvmStatic
        fun getInstance(): SingletonEntityFactory {
            if (singleInstance != null)
            {
                return singleInstance as SingletonEntityFactory
            } else
            {
                singleInstance=SingletonEntityFactory()
                return singleInstance as SingletonEntityFactory
            }
        }
    }

    fun addBitmap(name:String,bitmap:Bitmap)
    {
        val where=resources[name]
        if(where!=null)
        {
            if(!where.contains(bitmap)) where.add(bitmap)
        }
        else
        {
            resources[name]= arrayListOf(bitmap)
        }
    }

    fun bitmapToHitbox(bit:Bitmap):Array<BooleanArray>
    {
        var hitbox: Array<BooleanArray>
        hitbox=Array(bit.width){BooleanArray(bit.height)}
        for(x in 0 until bit.width)
        {
            for(y in 0 until bit.height )
            {
                hitbox[x][y] = (bit[x,y]!=Color.BLACK)
            }
        }
        return hitbox
    }

    inline fun<reified T : Entity>createEntity(x:Int,y:Int):Entity
    {
        var str:String
        when (T::class)
        {
            Barricade::class->str="Barricade"
            EnemyBullet::class->str="Bullet"
            PlayerBullet::class->str="Bullet"
            Squid::class->str="Squid"
            Chonker::class->str="Chonker"
            Bug::class->str="Bug"
            Player::class->str="Player"

            else->throw Exception("NO enemy type found")
        }
        val bitmap=resources[str]

        if(bitmap!=null)
        {
            when (T::class)
            {
                Barricade::class->return Barricade(x,y,bitmap,bitmapToHitbox(bitmap[0]))
                EnemyBullet::class->return EnemyBullet(x,y,bitmap,bitmapToHitbox(bitmap[0]))
                PlayerBullet::class->return PlayerBullet(x,y,bitmap,bitmapToHitbox(bitmap[0]))
                Squid::class->return Squid(x,y,bitmap,bitmapToHitbox(bitmap[0]))
                Chonker::class->return Chonker(x,y,bitmap,bitmapToHitbox(bitmap[0]))
                Bug::class->return Bug(x,y,bitmap,bitmapToHitbox(bitmap[0]))
                Player::class->return Player(x,y,bitmap,bitmapToHitbox(bitmap[0]))
                else->throw Exception("NO resource found")
            }
        }

        Log.d("EntityFactory","Entity not found")

        throw Exception("NO resource found ${resources.size}")
    }
}

