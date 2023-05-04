package com.example.livewallpaper;

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Matrix
import android.os.BatteryManager
import android.os.Handler
import android.service.wallpaper.WallpaperService
import android.view.SurfaceHolder

var check = false
class Wallpaper : WallpaperService() {
    var m_batteryLevel : Float = 1F
    var m_fisheList = mutableListOf<Fishe>()

    override fun onCreate() {
        //super.onCreate()

        val receiver = object : BroadcastReceiver(){
            override fun onReceive(context: Context?, intent: Intent?) {
                if(intent != null) {
                    m_batteryLevel = 0.40f
                    //m_batteryLevel = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 100) / 100F
                }
            }
        }

        registerReceiver(receiver, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
    }

    override fun onCreateEngine(): Engine {
        return WallpaperEngine()
    }

    private inner class WallpaperEngine : WallpaperService.Engine() {
        private val m_handler: Handler = Handler()
        private var m_visible = true
        private var m_water : Bitmap = BitmapFactory.decodeResource(resources, R.drawable.water)

        private val drawRunner = Runnable {
            Draw()
        }

        override fun onCreate(surfaceHolder: SurfaceHolder?) {
                super.onCreate(surfaceHolder)
        }

        override fun onVisibilityChanged(visible: Boolean) {
            m_visible = visible
            if (visible)
            {
                m_handler.post(drawRunner)
            } else {
                m_handler.removeCallbacks(drawRunner)
            }
        }

        override fun onSurfaceDestroyed(holder: SurfaceHolder?) {
            super.onSurfaceDestroyed(holder)
            m_visible = false
            m_handler.removeCallbacks(drawRunner)
        }

        override fun onOffsetsChanged(
            xOffset: Float,
            yOffset: Float,
            xStep: Float,
            yStep: Float,
            xPixels: Int,
            yPixels: Int
        ) {
            m_handler.post(drawRunner)
        }

        fun Draw(){
            val holder = surfaceHolder
            var canvas : Canvas? = null

            try {
                canvas = holder.lockCanvas()

                if(canvas != null){
                    val offsetY = canvas.height * (1F - m_batteryLevel)
                    val scaledWater = Bitmap.createScaledBitmap(m_water, canvas.width, canvas.height - offsetY.toInt(), false)
                    val matrix = Matrix()
                    matrix.postTranslate(0F, offsetY)

                    if(check == false)
                    {
                        val bitmap1 = BitmapFactory.decodeResource(resources, R.drawable.fish1)
                        val bitmap2 = BitmapFactory.decodeResource(resources, R.drawable.fish2)

                        m_fisheList.add(Fishe(bitmap1, scaledWater.width, scaledWater.height, offsetY.toInt(), 4F))
                        m_fisheList.add(Fishe(bitmap2, scaledWater.width, scaledWater.height, offsetY.toInt(), 4F))
                        m_fisheList.add(Fishe(bitmap1, scaledWater.width, scaledWater.height, offsetY.toInt(), 4F))
                        m_fisheList.add(Fishe(bitmap2, scaledWater.width, scaledWater.height, offsetY.toInt(), 4F))
                        m_fisheList.add(Fishe(bitmap1, scaledWater.width, scaledWater.height, offsetY.toInt(), 4F))
                        check = true
                    }

                    val deadFisheCountNeed = ((1F - m_batteryLevel) / (1F / m_fisheList.size)).toInt()
                    var deadFisheCountCur = 0

                    for(fishe in m_fisheList){
                        if(fishe.IsDead()) deadFisheCountCur++
                    }

                    var i = 0
                    while(deadFisheCountCur != deadFisheCountNeed && i < m_fisheList.size){
                        if(deadFisheCountCur > deadFisheCountNeed){
                            if(m_fisheList[i].IsDead()) m_fisheList[i].Revive()
                            deadFisheCountCur--
                        }
                        else{
                            if(!m_fisheList[i].IsDead()) {
                                val bitmap = BitmapFactory.decodeResource(resources, R.drawable.fish3)
                                m_fisheList[i].BitMapSet(bitmap)
                                m_fisheList[i].Kill()
                            }
                            deadFisheCountCur++
                        }
                        i++
                    }

                    canvas.drawColor(Color.WHITE)
                    canvas.drawBitmap(scaledWater, matrix, null)

                    for(fishe in m_fisheList){
                        fishe.SetFieldSize(scaledWater.width, scaledWater.height, offsetY.toInt())
                        fishe.Move()
                        canvas.drawBitmap(fishe.GetBitmap(), fishe.GetMatrix(), null)
                    }
                }
            }
            finally {
                if(canvas != null){
                    holder.unlockCanvasAndPost(canvas)
                }
            }

            m_handler.removeCallbacks(drawRunner)

            if(m_visible){
                m_handler.postDelayed(drawRunner, 70)
            }
        }
    }
}
