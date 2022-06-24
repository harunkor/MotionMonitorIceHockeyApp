package com.harunkor.motionmonitorapp.domain.usecase

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log
import android.widget.ImageView
import com.harunkor.motionmonitorapp.domain.model.MoveEntity
import com.harunkor.motionmonitorapp.presentation.viewmodel.move.MoveViewModel
import com.harunkor.motionmonitorapp.utils.MoveAction
import com.harunkor.motionmonitorapp.utils.Response
import kotlinx.coroutines.delay
import java.util.*

class SensorUseCase(val sensor: Sensor,val sensorManager: SensorManager):SensorEventListener {

    private var moveAction: MoveAction
    private lateinit var moveViewModel: MoveViewModel
    private lateinit var recordListEvent: MutableList<FloatArray>


    init {
        moveAction = MoveAction()
    }

    fun setCricketImageView( ballImageView: ImageView) {
        moveAction.setCricketBallImageView(ballImageView)
    }

    fun setCricketBallRadius(cricketBallRadius:Int){
        moveAction.setCricketBallRadius(cricketBallRadius)
    }

    fun setSceneFrameWidth(sceneFrameWidth: Int){
        moveAction.setSceneFrameWidth(sceneFrameWidth)
    }

    fun setSceneFrameHeight(sceneFrameHeight: Int) {
        moveAction.setSceneFrameHeight(sceneFrameHeight)
    }

    fun setScreenWidth(screenWidth: Int){
        moveAction.setScreenWidth(screenWidth)
    }

    fun setScreenHeight(screenHeight: Int){
        moveAction.setScreenHeight(screenHeight)
    }

    fun startSensor() {
        recordListEvent = mutableListOf<FloatArray>()
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_GAME)
    }

    fun stopSensor() {
        sensorManager.unregisterListener(this)
        stopRecord()
    }

     fun setRecord(moveViewModel: MoveViewModel) {
        this.moveViewModel = moveViewModel
    }


    private fun stopRecord() {
        moveViewModel.addMove(MoveEntity(0,recordListEvent))
        when(val resp= moveViewModel.isAddMoveState.value){
            is Response.Loading -> {
                // Loading
            }
            is Response.Success -> {
                recordListEvent.clear()
            }
            is Response.Error -> {
                // resp.message
            }
        }
    }

     suspend fun playPreviewRecord(moveEntity: MoveEntity){
             moveEntity.values.iterator().forEach { sensorEvent ->
                 delay(15) // Preview speed.
                 moveAction.setPreviewCoordinates(sensorEvent)
             }
    }

    override fun onSensorChanged(event: SensorEvent) {
        addSensorValue(event)
        moveAction.setNewCoordinates(event)
        Log.v("DEGERS",Arrays.toString(event.values))
    }

    override fun onAccuracyChanged(sensor: Sensor?, p1: Int) {

    }

    private fun addSensorValue(event: SensorEvent){
        val valueArray = FloatArray(3)
        valueArray[0]= event.values[0]
        valueArray[1]= event.values[1]
        valueArray[2]= event.values[2]
        recordListEvent.add(valueArray)
    }
}





