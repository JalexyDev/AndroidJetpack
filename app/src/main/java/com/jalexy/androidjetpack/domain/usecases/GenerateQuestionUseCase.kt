package com.jalexy.androidjetpack.domain.usecases

import com.jalexy.androidjetpack.domain.models.Question
import com.jalexy.androidjetpack.domain.repository.GameRepository

class GenerateQuestionUseCase(
    private val repository: GameRepository
) {

    operator fun invoke(maxSumValue: Int): Question =
        repository.generateQuestion(maxSumValue, COUNT_OF_OPTIONS)

    private companion object {

        private const val COUNT_OF_OPTIONS = 6
    }
}