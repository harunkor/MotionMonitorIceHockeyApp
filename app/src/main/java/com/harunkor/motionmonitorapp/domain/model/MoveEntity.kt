package com.harunkor.motionmonitorapp.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "movements_table")
data class MoveEntity (
    @PrimaryKey(autoGenerate = true)
    var id: Long,

    @ColumnInfo(name = "sensorevent")
    val values: MutableList<FloatArray>

) : Parcelable