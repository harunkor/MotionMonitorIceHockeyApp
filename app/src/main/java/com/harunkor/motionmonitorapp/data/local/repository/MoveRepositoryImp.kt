package com.harunkor.motionmonitorapp.data.local.repository

import com.harunkor.motionmonitorapp.data.local.dao.MoveDao
import com.harunkor.motionmonitorapp.domain.model.MoveEntity
import com.harunkor.motionmonitorapp.domain.repository.MoveRepository
import com.harunkor.motionmonitorapp.utils.Response
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MoveRepositoryImp @Inject constructor(private val moveDao: MoveDao): MoveRepository {
    override fun getAllMoves(): Flow<Response<MutableList<MoveEntity>>> {
        return callbackFlow {
            val allMoves:MutableList<MoveEntity>
            try {
                trySend(Response.Loading)
                allMoves = moveDao.loadAllMoves()
                trySend(Response.Success(allMoves))
            }catch (e: Exception){
                trySend(Response.Error(e.message ?: e.toString()))
            }
            awaitClose {

            }
        }
    }

    override fun addMove(moveEntity: MoveEntity): Flow<Response<Unit>> = flow {
        try {
            emit(Response.Loading)
            val insertSuccess = moveDao.insertMove(moveEntity)
            emit(Response.Success(insertSuccess))

        }catch (e: Exception){
            emit(Response.Error(e.message ?: e.toString()))
        }
    }


}