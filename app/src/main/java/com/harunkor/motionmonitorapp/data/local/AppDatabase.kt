package com.harunkor.motionmonitorapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.harunkor.motionmonitorapp.data.local.dao.MoveDao
import com.harunkor.motionmonitorapp.domain.model.MoveEntity
import com.harunkor.motionmonitorapp.utils.ConverterHelper

@Database(entities = [MoveEntity::class], version = 1, exportSchema = false)
@TypeConverters(ConverterHelper::class)
abstract class AppDatabase : RoomDatabase() {

    abstract val moveDao: MoveDao

    companion object {
        const val DB_NAME = "MotionMonitorDatabase.db"
    }
}