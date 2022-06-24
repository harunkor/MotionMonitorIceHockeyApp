package com.harunkor.motionmonitorapp.data.local.dao

import androidx.room.*
import com.harunkor.motionmonitorapp.domain.model.MoveEntity


@Dao
interface MoveDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMove(moveEntity: MoveEntity)

    @Query("SELECT * FROM movements_table")
    fun loadAllMoves(): MutableList<MoveEntity>


    @Query("DELETE FROM movements_table")
    fun deleteAllMoves()

    @Query("SELECT * FROM movements_table where id = :id")
    fun getByMoveId(id: Long): MoveEntity?

    @Update
    fun updateMove(moveEntity: MoveEntity)
}