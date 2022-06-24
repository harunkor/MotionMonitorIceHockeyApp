package com.harunkor.motionmonitorapp.utils

import android.hardware.SensorEvent
import android.util.Log
import android.widget.ImageView


class MoveAction() {

    private var sceneFrameWidth: Int = 0
    private var sceneFrameHeight: Int = 0
    private var screenWidth: Int = 0
    private var screenHeight: Int = 0
    private var cricketBallRadius:Int = 0
    private val borderSize:Int = 30
    private lateinit var cricketBallImageView: ImageView

    fun setCricketBallImageView(cricketBallImageView: ImageView) {
        this.cricketBallImageView=cricketBallImageView
    }

    fun setCricketBallRadius(cricketBallRadius:Int ){
        this.cricketBallRadius = cricketBallRadius
    }

    fun setSceneFrameWidth(sceneFrameWidth: Int){
        this.sceneFrameWidth = sceneFrameWidth
    }

    fun setSceneFrameHeight(sceneFrameHeight: Int){
        this.sceneFrameHeight = sceneFrameHeight
    }

    fun setScreenWidth(screenWidth: Int){
        this.screenWidth = screenWidth
    }

    fun setScreenHeight(screenHeight: Int){
        this.screenHeight = screenHeight
    }

    fun currentCoordinateX(): Float {
        return cricketBallImageView.x
    }

    fun currentCoordinateY(): Float {
        return  cricketBallImageView.y
    }


    fun setNewCoordinates(sensorEvent:SensorEvent?){
        sensorEvent?.let {

            val nextX: Float = currentCoordinateX() + it.values.get(0)
            val nextY: Float = currentCoordinateY() + it.values.get(1)
            val nextXLeft: Float = currentCoordinateX() - it.values.get(0)
            val nextYBottom: Float = currentCoordinateY() - it.values.get(1)
            setLeftMoveX(nextXLeft)
            setTopMoveY(nextY)
            setRightMoveX(nextX)
            setBottomMoveY(nextYBottom)
        }
    }

    fun setPreviewCoordinates(values: FloatArray){
            val nextX: Float = currentCoordinateX() + values.get(0)
            val nextY: Float = currentCoordinateY() + values.get(1)
            val nextXLeft: Float = currentCoordinateX() - values.get(0)
            val nextYBottom: Float = currentCoordinateY() -values.get(1)
            setLeftMoveX(nextXLeft)
            setTopMoveY(nextY)
            setRightMoveX(nextX)
            setBottomMoveY(nextYBottom)
    }


    private fun setLeftMoveX(nextX: Float){
        if((nextX - cricketBallRadius-borderSize) >= sceneFrameWidth/2){
            cricketBallImageView.setX(nextX)
        }
    }

    private fun setTopMoveY(nextY:Float){
        if((nextY - cricketBallRadius-borderSize) >= (sceneFrameHeight/2) + 20){
            cricketBallImageView.setY(nextY)
        }
    }

    private fun setRightMoveX(nextX: Float){
        if ((nextX + cricketBallRadius-borderSize) > screenWidth - (sceneFrameWidth/2)-120) {
            cricketBallImageView.setX(nextX-10)
        }
    }

    private fun setBottomMoveY(nextY: Float){
        if ((nextY + cricketBallRadius-borderSize) > screenHeight - (sceneFrameHeight/2)-220) {
            cricketBallImageView.setY(nextY-10)
        }
    }


}