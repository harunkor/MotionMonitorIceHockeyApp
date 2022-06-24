package com.harunkor.motionmonitorapp.domain.usecase.move

import com.harunkor.motionmonitorapp.domain.repository.MoveRepository

class AllMovements(private val moveRepository: MoveRepository) {
    operator fun invoke() = moveRepository.getAllMoves()
}