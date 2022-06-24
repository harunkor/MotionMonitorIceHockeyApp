package com.harunkor.motionmonitorapp.utils

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class ConverterHelper {
    companion object {

        @TypeConverter
        @JvmStatic
        fun fromString(valueList: String?): MutableList<FloatArray> {
            val listType = object :
                TypeToken<MutableList<FloatArray>>() {}.type
            return Gson()
                .fromJson<MutableList<FloatArray>>(valueList, listType)
        }

        @TypeConverter
        @JvmStatic
        fun sensorEventToString(valueList: MutableList<FloatArray>): String {
            val gson = Gson()
            return gson.toJson(valueList)
        }
    }
}