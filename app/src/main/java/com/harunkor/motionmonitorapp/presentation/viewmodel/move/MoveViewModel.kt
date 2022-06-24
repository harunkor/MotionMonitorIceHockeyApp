package com.harunkor.motionmonitorapp.presentation.viewmodel.move

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harunkor.motionmonitorapp.domain.model.MoveEntity
import com.harunkor.motionmonitorapp.domain.usecase.MoveUseCase
import com.harunkor.motionmonitorapp.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoveViewModel @Inject constructor(private val moveUseCase: MoveUseCase):ViewModel(){

    private val allMovementsTemp = mutableStateOf<Response<MutableList<MoveEntity>>>(Response.Loading)
    val allMovements: State<Response<MutableList<MoveEntity>>> = allMovementsTemp

    private val isAddMoveStateTemp = mutableStateOf<Response<Unit?>>(Response.Success(null))
    val isAddMoveState: State<Response<Unit?>> = isAddMoveStateTemp

    init {
        getAllMovements()
    }

    fun getAllMovements(){
        viewModelScope.launch {
            moveUseCase.allMovements().collect {
                response -> allMovementsTemp.value = response
            }
        }
    }

    fun addMove(moveEntity: MoveEntity){
        viewModelScope.launch {
            moveUseCase.addMove(moveEntity).collect {
                response -> isAddMoveStateTemp.value = response
            }
        }
    }


}