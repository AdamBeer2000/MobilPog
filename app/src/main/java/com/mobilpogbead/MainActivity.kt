package com.mobilpogbead

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintSet
import com.mobilpogbead.audio.SingletonAudioManager
//import com.mobilpogbead.audio.AudioManager
import com.mobilpogbead.controller.Controller
import com.mobilpogbead.database.DatabaseManager
import com.mobilpogbead.database.Score
import com.mobilpogbead.entity.SingletonEntityFactory
import com.mobilpogbead.entity.enemies.Enemy
import com.mobilpogbead.model.Boundaries
import java.util.*
import java.util.concurrent.locks.ReentrantLock
import kotlin.system.measureNanoTime

class MainActivity : AppCompatActivity() {

    private lateinit var db: DatabaseManager

    lateinit var controller:Controller
    lateinit var mTimer:Timer
    lateinit var img:ImageView
    lateinit var MainLayout: ConstraintSet.Layout
    lateinit var pointCountTextView:TextView
    lateinit var lifeCount:TextView

    lateinit var gameOverBtn: Button
    lateinit var gameOverText: TextView
    lateinit var gameOverRetryBtn: Button
    lateinit var gameOverPoints: TextView

    lateinit var highscoreTw: TextView
    lateinit var gamerName: TextView

    lateinit var timeCounter:TextView
    lateinit var gameOverTime:TextView

    var currTime=0L

    val lock = ReentrantLock()
    var onHold:Boolean = false

    init
    {
        Enemy.clearEnemyCash()
    }

    private fun loadResources()
    {
        val entityFactory=SingletonEntityFactory.getInstance()

        val bug1as= BitmapFactory.decodeResource(resources, R.drawable.bug1a)
        val bug1bs= BitmapFactory.decodeResource(resources, R.drawable.bug1b)

        entityFactory.addBitmap("Bug",bug1as)
        entityFactory.addBitmap("Bug",bug1bs)

        val bug2as= BitmapFactory.decodeResource(resources, R.drawable.bug2a)
        val bug2bs= BitmapFactory.decodeResource(resources, R.drawable.bug2b)

        entityFactory.addBitmap("Squid",bug2as)
        entityFactory.addBitmap("Squid",bug2bs)

        val bug3as= BitmapFactory.decodeResource(resources, R.drawable.bug3a)
        val bug3bs= BitmapFactory.decodeResource(resources, R.drawable.bug3b)

        entityFactory.addBitmap("Chonker",bug3as)
        entityFactory.addBitmap("Chonker",bug3bs)


        val bulleta= BitmapFactory.decodeResource(resources, R.drawable.bulleta)
        val bulletb= BitmapFactory.decodeResource(resources, R.drawable.bulletb)

        entityFactory.addBitmap("Bullet",bulleta)
        entityFactory.addBitmap("Bullet",bulletb)

        val player1= BitmapFactory.decodeResource(resources, R.drawable.player)

        entityFactory.addBitmap("Player",player1)

        val barrier= BitmapFactory.decodeResource(resources, R.drawable.barrier)

        entityFactory.addBitmap("Barricade",barrier)

        val sapceship= BitmapFactory.decodeResource(resources, R.drawable.spaceship)

        entityFactory.addBitmap("Spaceship",sapceship)
    }

    fun gameOver()
    {
        mTimer.cancel()

        gameOverPoints.text="Points: ${controller.model.pointCounter}"
        gameOverTime.text=timeCounter.text

        gameOverBtn.visibility=View.VISIBLE
        gameOverText.visibility=View.VISIBLE
        gameOverRetryBtn.visibility=View.VISIBLE
        gameOverPoints.visibility=View.VISIBLE
        gamerName.visibility=View.VISIBLE
        gameOverTime.visibility=View.VISIBLE

        if(controller.model.result == 1)
        {
            SingletonAudioManager.playWin(this)
            controller.model.result = 0
        }
        else if(controller.model.result == 2)
        {
            SingletonAudioManager.playLose(this)
            controller.model.result = 0
        }
    }

    fun back(v:View)
    {
        var name:String= gamerName.text.toString()
        if(name == "")
        {
            name="Guest"
        }
        val points:Int=controller.model.pointCounter
        val time:Float=currTime/1000F

        this.setData(Score(name, points, time))
        
        val i=Intent(this,MainMenu::class.java)
        startActivity(i)
    }

    fun retryEvent(v:View)
    {
        var name:String= gamerName.text.toString()
        if(name == "")
        {
            name="Guest"
        }
        val points:Int=controller.model.pointCounter
        val time:Float=currTime/1000F

        this.setData(Score(name, points, time))

        val i=Intent(this,MainActivity::class.java)
        startActivity(i)
    }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {

        db = DatabaseManager(this)
        super.onCreate(savedInstanceState)
        loadResources()

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main)

        this.getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)



        pointCountTextView = findViewById(R.id.pointCountTextView)
        lifeCount = findViewById(R.id.lifeCounter)

        controller = Controller(
            this,
            Boundaries(
                0,
                windowManager.defaultDisplay.width,
                0,
                windowManager.defaultDisplay.height
            ),
            pointCountTextView, lifeCount, BitmapFactory.decodeResource(resources, R.drawable.boom1)
        )

        controller.setUpSensor();

        img = ImageView(this)
        img.maxWidth = windowManager.defaultDisplay.width
        img.maxHeight = windowManager.defaultDisplay.height

        addContentView(img, ViewGroup.LayoutParams(img.maxWidth, img.maxHeight))

        gameOverBtn=findViewById(R.id.buttonNext)
        gameOverText=findViewById(R.id.textView2)

        gameOverRetryBtn=findViewById(R.id.buttonRetry)
        gameOverPoints=findViewById(R.id.gameOverPointsTw)
        highscoreTw=findViewById(R.id.highscoreTw)
        gamerName=findViewById(R.id.gamerName)
        timeCounter=findViewById(R.id.timeCounterTw2)
        gameOverTime=findViewById(R.id.gameOverTimeTw)

        highscoreTw.setText("HI: ${db.getHighscore().toString()}")

        gameOverBtn.visibility=View.INVISIBLE
        gameOverText.visibility=View.INVISIBLE
        gameOverRetryBtn.visibility=View.INVISIBLE
        gameOverPoints.visibility=View.INVISIBLE
        gamerName.visibility=View.INVISIBLE
        gameOverTime.visibility=View.INVISIBLE

        controller.view.bind(img)
    }

    override fun onStart()
    {
        super.onStart()
        mTimer = Timer()
        mTimer.schedule(object : TimerTask()
        {
            override fun run()
            {
                runOnUiThread(
                    Runnable {
                        Log.d("onstart","update")
                        try
                        {
                            //if(onHold)
                            if(controller.model.winCheckh()||controller.model.failCheckh())
                            {
                                gameOver()
                                mTimer.purge()
                            }
                            else
                            {
                                lock.lock()
                                var progress= measureNanoTime {
                                    controller.model.progress()
                                }
                                var move=measureNanoTime {
                                    controller.move()
                                }
                                var update=measureNanoTime {
                                    controller.view.update()
                                }
                                var checkHits=measureNanoTime {
                                    controller.model.checkHits()
                                }
                                var cleanObjects=measureNanoTime {
                                    controller.model.cleanObjects()
                                }
                                lock.unlock()
                                currTime=controller.model.getCurrTimeMillis()

                                timeCounter.text="Time: ${currTime/1000.0}s"
                                Log.d("Stat","progress:$progress nano")
                                Log.d("Stat","move:$move nano")
                                Log.d("Stat","update:$update nano")
                                Log.d("Stat","checkHits:$checkHits nano")
                                Log.d("Stat","cleanOutOfBounsObjects:$cleanObjects nano")
                            }
                        }
                        catch (e:Exception)
                        {
                            Log.e("Fail",e.message.toString())
                        }
                    }
                )
            }
        }, 0, 60)
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
        }
    }

    fun setData(setScore: Score)
    {
        db.setNewScore(setScore)
    }

    fun onTouch(view: View) {
        Log.d("TOUCH", "MainActivity - touched")
        controller.model.shoot()
    }

    override fun onPause()
    {
        Log.d("Life","Pause")
        super.onPause()
        mTimer.cancel()
    }

    override fun onResume()
    {
        Log.d("Life","Resume")
        super.onResume()
    }

}