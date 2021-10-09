package com.mobilpogbead.controller

import android.content.Context
import android.content.Context.*
import android.graphics.Bitmap
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat.getSystemService
import com.mobilpogbead.entity.EntityFactory
import com.mobilpogbead.model.Model
import com.mobilpogbead.view.View
import kotlin.system.exitProcess
import kotlin.time.*

class Controller(private var context: Context,entityFactory: EntityFactory)
{
    private lateinit var sensorManager: SensorManager;

    val model=Model(entityFactory)
    val view=View(model)

    var gyro_x: Any = 0.0
    var gyro_y: Any = 0.0
    var gyro_z: Any = 0.0

    init
    {

    }

    fun start()
    {
        //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        view.update()
        //todo késleltetés
        //todo szenzor model.()

        for(k in 0 until 10)
        {
            Log.d("controller","refresh")
            model.progress()
        }
    }

    fun setUpSensor()
    {
        sensorManager = this.context.getSystemService(SENSOR_SERVICE) as SensorManager
        val gyroSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE_UNCALIBRATED)//TYPE_GYROSCOPE
        sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE_UNCALIBRATED)//TYPE_GYROSCOPE
        sensorManager.registerListener(gyroSensorListener,
            gyroSensor, SensorManager.SENSOR_DELAY_FASTEST);

        Log.d("SETUP-SENSOR","STARTED")

        if(gyroSensor == null) {
            Log.e("SENSOR", "Gyro sensor not available.");
            Log.d("SENSOR", "Gyro sensor not available.");
            exitProcess(420); // Close app
        }

        Log.d("SETUP-SENSOR","SUCCESSFUL")
    }

    var gyroSensorListener: SensorEventListener = object : SensorEventListener {
        override fun onSensorChanged(p0: SensorEvent?) {
            if(p0?.sensor?.type == Sensor.TYPE_GYROSCOPE_UNCALIBRATED)//TYPE_GYROSCOPE
            {
                val rightLeft = p0.values[0]
                val upDown = p0.values[1]
                val frontBehind = p0.values[2]

                Log.d("SENSOR ","x: $rightLeft")
                Log.d("SENSOR ","y: $upDown")
                Log.d("SENSOR ","z: $frontBehind")

                gyro_x = p0.values[0]
                gyro_y = p0.values[1]
                gyro_z = p0.values[2]
            }
        }

        override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
            Log.d("SENSOR: ", "ACCURACY CHANGED")
        }
    }

    fun getGyroArray(): Array<Any>
    {
        val result = arrayOf(gyro_x, gyro_y, gyro_z)
        return result
    }

}