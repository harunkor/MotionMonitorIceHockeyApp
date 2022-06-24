package com.harunkor.motionmonitorapp.domain.usecase

import com.harunkor.motionmonitorapp.domain.usecase.move.AddMove
import com.harunkor.motionmonitorapp.domain.usecase.move.AllMovements

data class MoveUseCase(
    val addMove: AddMove,
    val allMovements: AllMovements
)
