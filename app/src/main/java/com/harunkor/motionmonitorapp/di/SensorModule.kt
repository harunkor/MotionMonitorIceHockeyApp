package com.harunkor.motionmonitorapp.di

import android.content.Context
import android.hardware.*
import com.harunkor.motionmonitorapp.domain.usecase.SensorUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.FragmentScoped


@InstallIn(FragmentComponent::class)
@Module
class SensorModule {

    @Provides
    @FragmentScoped
    fun provideSensorManager(@ActivityContext context: Context): SensorManager {
        return context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    @Provides
    @FragmentScoped
    fun provideSensor(sensorManager: SensorManager): Sensor {
        return   sensorManager.getDefaultSensor(Sensor.TYPE_ALL)
    }

    @Provides
    @FragmentScoped
    fun provideSensorEventListener(sensorManager: SensorManager,sensor: Sensor): SensorUseCase {
        return SensorUseCase(sensor,sensorManager)
    }

}


