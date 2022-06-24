package com.harunkor.motionmonitorapp.domain.repository

import com.harunkor.motionmonitorapp.domain.model.MoveEntity
import com.harunkor.motionmonitorapp.utils.Response
import kotlinx.coroutines.flow.Flow

interface MoveRepository {
    fun getAllMoves(): Flow<Response<MutableList<MoveEntity>>>
    fun addMove(moveEntity: MoveEntity): Flow<Response<Unit>>
}