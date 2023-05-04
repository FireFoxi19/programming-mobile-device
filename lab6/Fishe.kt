package com.example.livewallpaper;

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import kotlin.math.abs
import kotlin.math.asin
import kotlin.math.pow
import kotlin.math.sqrt

class Fishe(image : Bitmap, fieldWidth : Int, fieldHeight : Int, offsetY : Int, speed : Float = 2F) {
    private var m_posX = 0.0f
    private var m_posY = 0.0f
    private var m_angle : Float = 0.0f
    private var m_prevAngle : Float = 0.0f
    private val m_speed : Float
    private var m_xDirSpeed  : Float = 1.0f
    private var m_yDirSpeed  : Float = 1.0f
    private var m_image : Bitmap
    private var m_dead = false
    private var m_matrix = Matrix()
    private var m_width = 0
    private var m_height = 0
    private var m_fieldWidth = 0
    private var m_fieldHeight = 0
    private val m_border = 200
    private var m_offsetY = 0
    private var m_frameCounter = 300
    private var m_framesForChangeDir = 300

    init {
        m_image = image
        m_speed = speed
        m_width = m_image.width
        m_height = m_image.height
        m_fieldWidth = fieldWidth
        m_fieldHeight = fieldHeight
        m_offsetY = offsetY

        val heightViaOffset = m_fieldHeight + m_offsetY - m_border
        val widthWithBorder = fieldWidth - m_border

        val posX = (m_border..widthWithBorder).random()
        val posY = (m_offsetY..heightViaOffset).random()

        m_matrix.postTranslate(posX.toFloat(), posY.toFloat())
        m_posX = posX.toFloat() + m_image.width / 2
        m_posY = posY.toFloat() + m_image.height / 2
    }

    fun Move(){
        if(!m_dead) {
            Collision()
            if (m_frameCounter >= m_framesForChangeDir) {
                ChangeDirection()
                m_frameCounter = 0
            }

            m_posX += m_xDirSpeed
            m_posY += m_yDirSpeed
            m_matrix.postTranslate(m_xDirSpeed, m_yDirSpeed)
            m_frameCounter++
        }
        else{
            MoveToSurface()
        }
    }

    fun MoveToSurface(){
        if(m_posY.toInt() < m_offsetY){
            m_yDirSpeed = m_speed
        }
        else if(m_posY.toInt() > m_offsetY){
            m_yDirSpeed = -m_speed
        }

        m_posY += m_yDirSpeed
        m_matrix.postTranslate(0F, m_yDirSpeed)
    }
    fun BitMapSet(image : Bitmap){
        m_image = image
    }
    fun Kill(){
        m_dead = true
        m_matrix = Matrix()
        m_matrix.preRotate(180F, m_image.width / 2F, m_image.height / 2F)
        m_matrix.postTranslate(m_posX, m_posY)
    }

    fun Revive(){
        m_dead = false
    }

    fun ChangeDirection(){
        var sinus = 0.0f
        do {
            m_xDirSpeed = m_speed * Math.random().toFloat() * if ((0..1).random() == 0) 1.0f else -1.0f
            m_yDirSpeed = m_speed * Math.random().toFloat() * if ((0..1).random() == 0) 1.0f else -1.0f
            sinus = sqrt((m_xDirSpeed / m_speed).pow(2) + (m_yDirSpeed / m_speed).pow(2)) * if(m_yDirSpeed > 0) -1.0f else 1.0f
        }while(abs(sinus) >= 1.0f)

        var offset = 0.0f
        if(sinus > 0 && m_xDirSpeed > 0){
            offset = 180.0f * (m_xDirSpeed / m_speed)
        }
        else if(sinus < 0 && m_xDirSpeed > 0){
            offset = -180.0f * (m_xDirSpeed / m_speed)
        }

        m_angle = 57.3f * asin(sinus) + offset
        m_matrix.preRotate(m_angle - m_prevAngle, m_image.width / 2.0f, m_image.height / 2.0f)
        m_prevAngle = m_angle
    }

    fun Collision() : Boolean{
        if(m_posX < m_border || m_posX + m_border > m_fieldWidth ){
            m_xDirSpeed *= -1.0f
            return true
        }
        else if( m_posY < m_offsetY + m_border || m_posY + m_border > m_fieldHeight + m_offsetY){
            m_yDirSpeed *= -1.0f
            return true
        }
        return false
    }

    fun SetFieldSize(width : Int, height : Int, offsetY : Int){
        m_fieldWidth = width
        m_fieldHeight = height
        m_offsetY = offsetY
    }

    fun IsDead() :Boolean{
        return m_dead
    }

    fun GetBitmap() : Bitmap {
        return m_image
    }

    fun GetMatrix() : Matrix {
        return m_matrix
    }
}