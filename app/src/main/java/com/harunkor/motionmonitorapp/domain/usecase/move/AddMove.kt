package com.harunkor.motionmonitorapp.domain.usecase.move

import com.harunkor.motionmonitorapp.domain.model.MoveEntity
import com.harunkor.motionmonitorapp.domain.repository.MoveRepository

class AddMove(private val moveRepository: MoveRepository) {
    operator  fun invoke(moveEntity: MoveEntity) = moveRepository.addMove(moveEntity)
}